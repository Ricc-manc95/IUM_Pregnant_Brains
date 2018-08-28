// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.exchange;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventPermissionUtils;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeePermissions;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.event.ProposeTimeActivity;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.newapi.exchange:
//            EasSupport

public class ResponseFollowUpUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/exchange/ResponseFollowUpUtils);

    public ResponseFollowUpUtils()
    {
    }

    public static Intent createProposeTimeIntent(Context context, BasicViewScreenModel basicviewscreenmodel)
    {
        Intent intent = new Intent(context, com/google/android/calendar/event/ProposeTimeActivity);
        intent.putExtra("start_millis", basicviewscreenmodel.event.getStartMillis());
        intent.putExtra("end_millis", basicviewscreenmodel.event.getEndMillis());
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        ImmutableList immutablelist = (ImmutableList)basicviewscreenmodel.event.getAttendees();
        int k = immutablelist.size();
        int i = 0;
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
        do
        {
            if (i >= k)
            {
                break;
            }
            Object obj = immutablelist.get(i);
            int j = i + 1;
            obj = (Attendee)obj;
            String s = ((Attendee) (obj)).attendeeDescriptor.email;
            i = j;
            if (!TextUtils.isEmpty(s))
            {
                arraylist.add(s);
                arraylist1.add(((Attendee) (obj)).displayName);
                i = j;
            }
        } while (true);
        intent.putStringArrayListExtra("attendees", arraylist);
        intent.putStringArrayListExtra("attendee_display_names", arraylist1);
        intent.putExtra("event_color", basicviewscreenmodel.getColor(context));
        return intent;
    }

    public static Response createResponseFromAddNoteResult(Event event, int i, Intent intent, Response response)
    {
        if (i == -1)
        {
            if ((event = getResponseBuilderForCurrentAttendee(event, response)) != null)
            {
                event.comment = Platform.nullToEmpty(intent.getStringExtra("note"));
                return new Response(event);
            }
        }
        return null;
    }

    public static Response createResponseFromProposeNewTimeResult(Event event, int i, Intent intent)
    {
        if (i == -1 && intent != null)
        {
            TimeProposal timeproposal = (TimeProposal)intent.getParcelableExtra("propose_new_time_proposal");
            if (timeproposal == null || timeproposal.getStartTimeMillis() > timeproposal.getEndTimeMillis())
            {
                return null;
            }
            if (AttendeeUtils.getCurrentAttendee(event) == null)
            {
                return null;
            }
            event = (com.google.android.calendar.api.event.attendee.Response.ResponseStatus)intent.getSerializableExtra("propose_new_time_response_status");
            if (event == null)
            {
                return null;
            } else
            {
                intent = new com.google.android.calendar.api.event.attendee.Response.Builder();
                intent.status = event;
                intent.comment = Platform.nullToEmpty(timeproposal.getComment());
                return new Response(intent.setProposedTime(Long.valueOf(timeproposal.getStartTimeMillis()), Long.valueOf(timeproposal.getEndTimeMillis())));
            }
        } else
        {
            return null;
        }
    }

    public static Response createResponseFromProposeTimeResultOrShowError(Context context, Event event, int i, Intent intent, Response response)
    {
        i;
        JVM INSTR tableswitch -1 1: default 28
    //                   -1 30
    //                   0 28
    //                   1 134;
           goto _L1 _L2 _L1 _L3
_L1:
        return null;
_L2:
        long l = intent.getLongExtra("start_millis", 0L);
        long l1 = intent.getLongExtra("end_millis", 0L);
        context = intent.getStringExtra("intent_key_note");
        event = getResponseBuilderForCurrentAttendee(event, response);
        if (event != null)
        {
            if (l > 0L && l1 > 0L)
            {
                event.setProposedTime(Long.valueOf(l), Long.valueOf(l1));
            } else
            {
                event.setProposedTime(null, null);
            }
            if (TextUtils.isEmpty(context))
            {
                context = null;
            }
            event.comment = Platform.nullToEmpty(context);
            return new Response(event);
        }
          goto _L1
_L3:
        i = intent.getIntExtra("invalid_email_count", 0);
        Toast.makeText(context, context.getResources().getQuantityString(0x7f120016, i), 1).show();
        return null;
    }

    public static int getFollowUpAction(EventHolder eventholder, Context context)
    {
        boolean flag;
        Event event = eventholder.getEvent();
        if (EventPermissionUtils.isExchangeEvent(event))
        {
            context = eventholder.getEvent();
            if (shouldShowEasActions(context))
            {
label0:
                {
                    if (EasSupport.proposeTimeSupported == null)
                    {
                        throw new IllegalStateException("load() has to be called first.");
                    }
                    if (EasSupport.proposeTimeSupported.booleanValue() && !context.isRecurring() && !context.isAllDayEvent() && ((PermissionHolder)eventholder).getPermissions().getAttendeePermissions().canProposeNewTime() && context.getEndMillis() - context.getStartMillis() < 0x5265c00L)
                    {
                        flag = true;
                        break label0;
                    }
                }
            }
            flag = false;
            continue;
        } else
        {
            if (EventPermissionUtils.isGoogleEvent(event) && ExperimentalOptions.isProposeNewTimeEnabled(context))
            {
                flag = ((PermissionHolder)eventholder).getPermissions().getAttendeePermissions().canProposeNewTime();
            } else
            {
                flag = false;
            }
            continue;
        }
        while (true) 
        {
            boolean flag1;
            if (!shouldShowEasActions(eventholder.getEvent()))
            {
                flag1 = false;
            } else
            if (!((PermissionHolder)eventholder).getPermissions().getAttendeePermissions().canModifyResponseComment())
            {
                flag1 = false;
            } else
            {
                if (EasSupport.addNoteSupported == null)
                {
                    throw new IllegalStateException("load() has to be called first.");
                }
                flag1 = EasSupport.addNoteSupported.booleanValue();
            }
            if (flag && flag1)
            {
                return 1;
            }
            if (flag)
            {
                return 2;
            }
            if (flag1)
            {
                return 3;
            }
            return 0;
        }
    }

    public static int getFollowUpStringId(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Unsupported follow up action.");

        case 1: // '\001'
            return 0x7f1303c3;

        case 2: // '\002'
            return 0x7f1303c2;

        case 3: // '\003'
            return 0x7f130422;
        }
    }

    public static int getLeftActionStringId(com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus)
    {
        switch (responsestatus.ordinal())
        {
        default:
            throw new IllegalArgumentException("Unsupported RSVP status.");

        case 1: // '\001'
            return 0x7f1303fb;

        case 2: // '\002'
            return 0x7f1303f8;

        case 3: // '\003'
            return 0x7f1303f9;
        }
    }

    private static com.google.android.calendar.api.event.attendee.Response.Builder getResponseBuilderForCurrentAttendee(Event event, Response response)
    {
        if (response != null)
        {
            event = new com.google.android.calendar.api.event.attendee.Response.Builder();
            event.status = response.status;
            return event;
        }
        event = AttendeeUtils.getCurrentAttendee(event);
        if (event == null)
        {
            return null;
        } else
        {
            response = new com.google.android.calendar.api.event.attendee.Response.Builder();
            response.status = ((Attendee) (event)).response.status;
            return response;
        }
    }

    private static boolean shouldShowEasActions(Event event)
    {
        if (AccountUtil.isGoogleExchangeAccount(event.getCalendar().account))
        {
            boolean flag;
            if (EasSupport.proposeTimeSupported != null && EasSupport.addNoteSupported != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                LogUtils.wtf(TAG, "EasSupport is not loaded!", new Object[0]);
                return false;
            }
            if (event.getStatus() != 2)
            {
                return true;
            }
        }
        return false;
    }

}
