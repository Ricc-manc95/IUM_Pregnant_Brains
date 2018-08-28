// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.newapi.exchange.ResponseFollowUpUtils;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.segment.attendee.AttendeesUtils;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            CommandBarController, MoreOptionsSheetController, BottomBarController, BottomBar, 
//            CommandBar, MoreOptionsSheet

public final class EventCommandBarController extends CommandBarController
    implements com.google.android.calendar.event.scope.ScopeSelectionDialog.OnScopeSelectedCallback
{

    private static final int ACTION_IDS[] = {
        0x7f10026c, 0x7f10026d, 0x7f10026e
    };
    private int followUpAction;
    private final MoreOptionsSheetController moreOptionsSheetController;

    public EventCommandBarController(Callback callback, MoreOptionsSheetController moreoptionssheetcontroller)
    {
        super(callback);
        moreOptionsSheetController = moreoptionssheetcontroller;
        moreOptionsSheetController.listener = callback;
    }

    private final String getRespondedContentDescription(com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus)
    {
        switch (responsestatus.ordinal())
        {
        default:
            return "";

        case 1: // '\001'
            return super.commandBar.getContext().getString(0x7f130420);

        case 3: // '\003'
            return super.commandBar.getContext().getString(0x7f130421);

        case 2: // '\002'
            return super.commandBar.getContext().getString(0x7f130423);
        }
    }

    private final void handleRsvpClick(Callback callback, com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus, int i)
    {
        setActionSelectedStates(responsestatus);
        if (followUpAction != 0)
        {
            CommandBar commandbar = (CommandBar)super.commandBar;
            int j = ResponseFollowUpUtils.getLeftActionStringId(responsestatus);
            int k = ResponseFollowUpUtils.getFollowUpStringId(followUpAction);
            commandbar.leftActionView.setText(j);
            commandbar.followUpActionView.setText(k);
            commandbar.animate(true);
            callback.onRsvpClicked(responsestatus, i, true);
            return;
        } else
        {
            callback.onRsvpClicked(responsestatus, i, false);
            return;
        }
    }

    private final void setActionSelectedStates(com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus)
    {
        boolean flag1 = true;
        Object obj;
        Button button;
        boolean flag;
        if (responsestatus == com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((CommandBar)super.commandBar).setActionSelectionState(0x7f10026c, flag);
        obj = getRespondedContentDescription(responsestatus);
        button = (Button)((CommandBar)super.commandBar).findViewById(0x7f10026c);
        if (!flag || TextUtils.isEmpty(((CharSequence) (obj))))
        {
            button.setContentDescription(button.getText());
        } else
        {
            button.setContentDescription(((CharSequence) (obj)));
        }
        if (responsestatus == com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((CommandBar)super.commandBar).setActionSelectionState(0x7f10026d, flag);
        obj = getRespondedContentDescription(responsestatus);
        button = (Button)((CommandBar)super.commandBar).findViewById(0x7f10026d);
        if (!flag || TextUtils.isEmpty(((CharSequence) (obj))))
        {
            button.setContentDescription(button.getText());
        } else
        {
            button.setContentDescription(((CharSequence) (obj)));
        }
        if (responsestatus == com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        ((CommandBar)super.commandBar).setActionSelectionState(0x7f10026e, flag);
        responsestatus = getRespondedContentDescription(responsestatus);
        obj = (Button)((CommandBar)super.commandBar).findViewById(0x7f10026e);
        if (!flag || TextUtils.isEmpty(responsestatus))
        {
            ((Button) (obj)).setContentDescription(((Button) (obj)).getText());
            return;
        } else
        {
            ((Button) (obj)).setContentDescription(responsestatus);
            return;
        }
    }

    protected final int getActionsLayout()
    {
        return 0x7f0500ce;
    }

    protected final int[] getPrimaryActionIds()
    {
        return ACTION_IDS;
    }

    protected final void onCommandBarActionClick(Object obj, int i)
    {
        Object obj1 = (Callback)obj;
        if (i == 0x7f100256)
        {
            ((CommandBar)super.commandBar).animate(false);
            return;
        }
        if (i == 0x7f100257)
        {
            switch (followUpAction)
            {
            default:
                throw new IllegalStateException("No follow up action available");

            case 1: // '\001'
                obj = moreOptionsSheetController.moreOptionsSheet;
                ((MoreOptionsSheet) (obj)).setVisibility(0);
                ((MoreOptionsSheet) (obj)).panelView.setVisibility(0);
                ((MoreOptionsSheet) (obj)).scrimView.setVisibility(0);
                Animation animation = AnimationUtils.loadAnimation(((MoreOptionsSheet) (obj)).getContext(), 0x7f06000f);
                animation.setInterpolator(QuantumInterpolators.FAST_OUT_GENTLE_IN);
                animation.setAnimationListener(new MoreOptionsSheet._cls1(((MoreOptionsSheet) (obj))));
                obj1 = AnimationUtils.loadAnimation(((MoreOptionsSheet) (obj)).getContext(), 0x7f06000c);
                ((MoreOptionsSheet) (obj)).panelView.startAnimation(animation);
                ((MoreOptionsSheet) (obj)).scrimView.startAnimation(((Animation) (obj1)));
                return;

            case 2: // '\002'
                ((Callback) (obj1)).onProposeNewTimeClicked();
                return;

            case 3: // '\003'
                ((Callback) (obj1)).onAddNoteClicked();
                break;
            }
            return;
        }
        obj = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION;
        String s;
        AnalyticsLogger analyticslogger;
        if (i == 0x7f10026c)
        {
            obj = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED;
            s = "tap_rsvp_yes";
        } else
        if (i == 0x7f10026d)
        {
            obj = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED;
            s = "tap_rsvp_no";
        } else
        if (i == 0x7f10026e)
        {
            obj = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE;
            s = "tap_rsvp_maybe";
        } else
        {
            s = "tap_rsvp";
        }
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)analyticslogger).trackEvent(super.commandBar.getContext(), "event_action", s);
        if (super.model != null && CalendarApi.EventPermissionsFactory.create(((EventHolder)super.model).getEvent()).getAllowedModificationScopes().size() > 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            ((Callback) (obj1)).showRsvpUpdateScopeSelectionDialog(((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj)));
            return;
        } else
        {
            handleRsvpClick(((Callback) (obj1)), ((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj)), 0);
            return;
        }
    }

    protected final void onModelChanged(Object obj)
    {
        Object obj1 = (EventHolder)obj;
        if (!AttendeesUtils.canRespond(((PermissionHolder)obj1).getPermissions(), ((EventHolder) (obj1)).getEvent()))
        {
            ((CommandBar)super.commandBar).setVisibility(8);
            return;
        }
        obj = AttendeeUtils.getCurrentAttendee(((EventHolder) (obj1)).getEvent());
        followUpAction = ResponseFollowUpUtils.getFollowUpAction(((EventHolder) (obj1)), super.commandBar.getContext());
        obj1 = super.commandBar;
        if (obj1 != null)
        {
            ((View) (obj1)).setVisibility(0);
        }
        setActionSelectedStates(((Attendee) (obj)).response.status);
        Object obj2 = ((Attendee) (obj)).response.status;
        obj = ((BottomBar) ((CommandBar)super.commandBar)).descriptionView;
        obj1 = String.valueOf(((TextView) (obj)).getText());
        obj2 = getRespondedContentDescription(((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj2)));
        ((TextView) (obj)).setContentDescription((new StringBuilder(String.valueOf(obj1).length() + 1 + String.valueOf(obj2).length())).append(((String) (obj1))).append(" ").append(((String) (obj2))).toString());
    }

    public final void onScopeSelected(int i, com.google.android.calendar.event.scope.ScopeSelectionDialog.Config config)
    {
        config = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.createFromInteger(config.additionalArguments().getInt("ARGUMENT_RSVP_STATUS"));
        handleRsvpClick((Callback)super.callback, config, i);
    }

    protected final void setupCommandBar(BottomBar bottombar)
    {
        bottombar = (CommandBar)bottombar;
        bottombar.setDescription(bottombar.getResources().getString(0x7f1303fa));
    }


    private class Callback
        implements MoreOptionsSheetController.Callback
    {

        public abstract void onAddNoteClicked();

        public abstract void onProposeNewTimeClicked();

        public abstract void onRsvpClicked(com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus, int i, boolean flag);

        public abstract void showRsvpUpdateScopeSelectionDialog(com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus);
    }

}
