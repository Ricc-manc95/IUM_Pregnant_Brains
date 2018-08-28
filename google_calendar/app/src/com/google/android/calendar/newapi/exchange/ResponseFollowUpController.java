// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.exchange;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.screen.ResponseSaver;
import com.google.android.calendar.utils.snackbar.SnackbarUtils;

// Referenced classes of package com.google.android.calendar.newapi.exchange:
//            ResponseFollowUpUtils

public class ResponseFollowUpController extends Fragment
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/exchange/ResponseFollowUpController);
    public BasicViewScreenModel data;
    public Response pendingResponse;
    public boolean redirectedToFollowUpFlow;
    public Handler rsvpDelayedSaveHandler;
    public int updateScope;

    public ResponseFollowUpController()
    {
        redirectedToFollowUpFlow = false;
        updateScope = 0;
        rsvpDelayedSaveHandler = new _cls1();
    }

    public final void onActivityResult(int i, int j, Intent intent)
    {
        Object obj;
        Object obj2;
        boolean flag;
        flag = false;
        obj = null;
        obj2 = null;
        if (i != 1006) goto _L2; else goto _L1
_L1:
        redirectedToFollowUpFlow = false;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        intent = ResponseFollowUpUtils.createResponseFromProposeTimeResultOrShowError(((android.content.Context) (obj)), data.event, j, intent, pendingResponse);
        if (intent == null) goto _L4; else goto _L3
_L3:
        pendingResponse = null;
        ((ResponseSaver)super.mParentFragment).saveResponse(intent, updateScope, false);
        if (super.mHost == null)
        {
            intent = obj2;
        } else
        {
            intent = (FragmentActivity)super.mHost.mActivity;
        }
        Toast.makeText(intent, 0x7f130348, 0).show();
_L6:
        return;
_L4:
        rsvpDelayedSaveHandler.removeCallbacksAndMessages(null);
        if (pendingResponse != null)
        {
            intent = pendingResponse;
            pendingResponse = null;
            ((ResponseSaver)super.mParentFragment).saveResponse(intent, updateScope, false);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (i != 1009)
        {
            break; /* Loop/switch isn't completed */
        }
        redirectedToFollowUpFlow = false;
        Response response = ResponseFollowUpUtils.createResponseFromAddNoteResult(data.event, j, intent, pendingResponse);
        if (response != null)
        {
            intent = AnalyticsLoggerHolder.instance;
            if (intent == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            AnalyticsLogger analyticslogger = (AnalyticsLogger)intent;
            if (super.mHost == null)
            {
                intent = null;
            } else
            {
                intent = (FragmentActivity)super.mHost.mActivity;
            }
            analyticslogger.trackEvent(intent, "rsvp_commenting", "send_note");
            pendingResponse = null;
            ((ResponseSaver)super.mParentFragment).saveResponse(response, updateScope, false);
            if (super.mHost == null)
            {
                intent = ((Intent) (obj));
            } else
            {
                intent = (FragmentActivity)super.mHost.mActivity;
            }
            Toast.makeText(intent, 0x7f130094, 0).show();
            return;
        }
        rsvpDelayedSaveHandler.removeCallbacksAndMessages(null);
        if (pendingResponse != null)
        {
            intent = pendingResponse;
            pendingResponse = null;
            ((ResponseSaver)super.mParentFragment).saveResponse(intent, updateScope, false);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
        if (i == 1011)
        {
            Object obj1 = ResponseFollowUpUtils.createResponseFromProposeNewTimeResult(data.event, j, intent);
            if (obj1 != null)
            {
                ((ResponseSaver)super.mParentFragment).saveResponse(((Response) (obj1)), 0, true);
                if (super.mHost == null)
                {
                    intent = null;
                } else
                {
                    intent = (FragmentActivity)super.mHost.mActivity;
                }
                if (super.mHost == null)
                {
                    obj1 = null;
                } else
                {
                    obj1 = (FragmentActivity)super.mHost.mActivity;
                }
                SnackbarUtils.showSnackbar(intent, ((FragmentActivity) (obj1)).getString(0x7f130348), 0, null, null, null);
                return;
            }
            i = ((flag) ? 1 : 0);
            if (intent != null)
            {
                intent = (TimeProposal)intent.getParcelableExtra("propose_new_time_proposal");
                i = ((flag) ? 1 : 0);
                if (j == -1)
                {
                    i = ((flag) ? 1 : 0);
                    if (intent == null)
                    {
                        i = 1;
                    }
                }
            }
            if (i != 0)
            {
                ((com.google.android.calendar.newapi.commandbar.SmartRsvpBottomBarController.Callback)super.mParentFragment).onProposeNewTimeChipClosed();
                return;
            }
        } else
        {
            super.onActivityResult(i, j, intent);
            return;
        }
        if (true) goto _L6; else goto _L7
_L7:
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            pendingResponse = (Response)bundle.getParcelable("PendingResponse");
            redirectedToFollowUpFlow = bundle.getBoolean("RedirectedToFollowUp");
            updateScope = bundle.getInt("SAVED_INSTANCE_UPDATE_SCOPE");
        }
        data = (BasicViewScreenModel)getArguments().getParcelable("EventData");
    }

    public final void onPause()
    {
        super.onPause();
        if (!redirectedToFollowUpFlow)
        {
            rsvpDelayedSaveHandler.removeCallbacksAndMessages(null);
            if (pendingResponse != null)
            {
                Response response = pendingResponse;
                pendingResponse = null;
                ((ResponseSaver)super.mParentFragment).saveResponse(response, updateScope, false);
            }
            return;
        } else
        {
            rsvpDelayedSaveHandler.removeCallbacksAndMessages(null);
            return;
        }
    }

    public final void onResume()
    {
        super.onResume();
        redirectedToFollowUpFlow = false;
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("PendingResponse", pendingResponse);
        bundle.putBoolean("RedirectedToFollowUp", redirectedToFollowUpFlow);
        bundle.putInt("SAVED_INSTANCE_UPDATE_SCOPE", updateScope);
        super.onSaveInstanceState(bundle);
    }

    final void saveDelayedResponses()
    {
        rsvpDelayedSaveHandler.removeCallbacksAndMessages(null);
        if (pendingResponse != null)
        {
            Response response = pendingResponse;
            pendingResponse = null;
            ((ResponseSaver)super.mParentFragment).saveResponse(response, updateScope, false);
        }
    }


    private class _cls1 extends Handler
    {

        private final ResponseFollowUpController this$0;

        public final void handleMessage(Message message)
        {
            saveDelayedResponses();
        }

        _cls1()
        {
            this$0 = ResponseFollowUpController.this;
            super();
        }
    }

}
