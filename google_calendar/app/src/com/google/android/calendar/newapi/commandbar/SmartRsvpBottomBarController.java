// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.proposenewtime.utils.ProposeNewTimeUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeePermissions;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.segment.attendee.AttendeesUtils;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            BottomBarController, SmartRsvpBottomBar, BottomBar

public final class SmartRsvpBottomBarController extends BottomBarController
    implements com.google.android.calendar.event.scope.ScopeSelectionDialog.OnScopeSelectedCallback, SmartRsvpBottomBar.RemoveProposalListener
{

    private static final int ACTION_IDS[] = {
        0x7f10026c, 0x7f10026d, 0x7f10026e
    };

    public SmartRsvpBottomBarController(Callback callback)
    {
        super(callback);
    }

    private final void setActionSelectedStates(com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus)
    {
        Object obj;
        Button button;
        boolean flag;
        boolean flag1 = true;
        if (responsestatus == com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((SmartRsvpBottomBar)super.commandBar).setActionSelectionState(0x7f10026c, flag);
        responsestatus.ordinal();
        JVM INSTR tableswitch 1 3: default 56
    //                   1 290
    //                   2 322
    //                   3 306;
           goto _L1 _L2 _L3 _L4
_L1:
        obj = "";
_L13:
        button = (Button)((SmartRsvpBottomBar)super.commandBar).findViewById(0x7f10026c);
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
        ((SmartRsvpBottomBar)super.commandBar).setActionSelectionState(0x7f10026d, flag);
        responsestatus.ordinal();
        JVM INSTR tableswitch 1 3: default 148
    //                   1 352
    //                   2 384
    //                   3 368;
           goto _L5 _L6 _L7 _L8
_L5:
        obj = "";
_L14:
        button = (Button)((SmartRsvpBottomBar)super.commandBar).findViewById(0x7f10026d);
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
        ((SmartRsvpBottomBar)super.commandBar).setActionSelectionState(0x7f10026e, flag);
        responsestatus.ordinal();
        JVM INSTR tableswitch 1 3: default 244
    //                   1 414
    //                   2 446
    //                   3 430;
           goto _L9 _L10 _L11 _L12
_L11:
        break MISSING_BLOCK_LABEL_446;
_L9:
        responsestatus = "";
_L15:
        obj = (Button)((SmartRsvpBottomBar)super.commandBar).findViewById(0x7f10026e);
        if (!flag || TextUtils.isEmpty(responsestatus))
        {
            ((Button) (obj)).setContentDescription(((Button) (obj)).getText());
            return;
        } else
        {
            ((Button) (obj)).setContentDescription(responsestatus);
            return;
        }
_L2:
        obj = super.commandBar.getContext().getString(0x7f130420);
          goto _L13
_L4:
        obj = super.commandBar.getContext().getString(0x7f130421);
          goto _L13
_L3:
        obj = super.commandBar.getContext().getString(0x7f130423);
          goto _L13
_L6:
        obj = super.commandBar.getContext().getString(0x7f130420);
          goto _L14
_L8:
        obj = super.commandBar.getContext().getString(0x7f130421);
          goto _L14
_L7:
        obj = super.commandBar.getContext().getString(0x7f130423);
          goto _L14
_L10:
        responsestatus = super.commandBar.getContext().getString(0x7f130420);
          goto _L15
_L12:
        responsestatus = super.commandBar.getContext().getString(0x7f130421);
          goto _L15
        responsestatus = super.commandBar.getContext().getString(0x7f130423);
          goto _L15
    }

    protected final int getActionsLayout()
    {
        return 0x7f05012d;
    }

    protected final int[] getPrimaryActionIds()
    {
        return ACTION_IDS;
    }

    protected final BottomBar inflateCommandBar(Context context, ViewGroup viewgroup)
    {
        return (SmartRsvpBottomBar)LayoutInflater.from(context).inflate(0x7f05015f, viewgroup, false);
    }

    protected final void onCommandBarActionClick(Object obj, int i)
    {
        Callback callback = (Callback)obj;
        if (i == 0x7f10035f)
        {
            callback.onProposeNewTimeClicked();
        } else
        {
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
                callback.showRsvpUpdateScopeSelectionDialog(((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj)));
                return;
            }
            if (obj != com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION)
            {
                setActionSelectedStates(((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj)));
                callback.onRsvpClicked(((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj)), 0, false);
                return;
            }
        }
    }

    protected final void onModelChanged(Object obj)
    {
        Object obj2;
        Object obj3;
        obj2 = null;
        obj3 = (EventHolder)obj;
        if (AttendeesUtils.canRespond(((PermissionHolder)obj3).getPermissions(), ((EventHolder) (obj3)).getEvent())) goto _L2; else goto _L1
_L1:
        ((SmartRsvpBottomBar)super.commandBar).setVisibility(8);
_L6:
        return;
_L2:
        SmartRsvpBottomBar smartrsvpbottombar;
        setActionSelectedStates(AttendeeUtils.getCurrentAttendee(((EventHolder) (obj3)).getEvent()).response.status);
        smartrsvpbottombar = (SmartRsvpBottomBar)super.commandBar;
        if (!((PermissionHolder)(EventHolder)super.model).getPermissions().getAttendeePermissions().canProposeNewTime()) goto _L4; else goto _L3
_L3:
        Object obj1;
        long l;
        obj = AttendeeUtils.getCurrentAttendee(((EventHolder)super.model).getEvent());
        boolean flag;
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((Attendee) (obj)).response;
        }
        if (!ProposeNewTimeUtils.responseHasProposal(((Response) (obj)))) goto _L4; else goto _L5
_L5:
        if (((SmartRsvpBottomBar)super.commandBar).currentMode == SmartRsvpBottomBar.RsvpBottomBarMode.RSVP_WITH_PROPOSAL)
        {
            obj = SmartRsvpBottomBar.RsvpBottomBarMode.RSVP_WITH_PROPOSAL;
        } else
        {
            obj = SmartRsvpBottomBar.RsvpBottomBarMode.FOLLOW_UP_WITH_PROPOSAL;
        }
_L7:
        obj1 = AttendeeUtils.getCurrentAttendee(((EventHolder)super.model).getEvent());
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((Attendee) (obj1)).response;
        }
        l = ((EventHolder) (obj3)).getEvent().getStartMillis();
        flag = ((SmartRsvpBottomBar.RsvpBottomBarMode) (obj)).canHaveFollowUps;
        obj3 = smartrsvpbottombar.toggle;
        if (obj3 != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            ((View) (obj3)).setVisibility(i);
        }
        if (!((SmartRsvpBottomBar.RsvpBottomBarMode) (obj)).canHaveFollowUps)
        {
            obj1 = smartrsvpbottombar.proposeNewTimeChip;
            if (obj1 != null)
            {
                ((View) (obj1)).setVisibility(8);
            }
        } else
        {
            Object obj4 = smartrsvpbottombar.getContext().getString(0x7f1303c0);
            boolean flag1;
            if (((SmartRsvpBottomBar.RsvpBottomBarMode) (obj)).hasProposal)
            {
                obj1 = ProposeNewTimeUtils.getProposalString(((Response) (obj1)), l, smartrsvpbottombar.getContext());
                flag1 = true;
            } else
            {
                flag1 = false;
                obj1 = obj4;
            }
            obj4 = smartrsvpbottombar.proposeNewTimeChip;
            if (obj4 != null)
            {
                ((View) (obj4)).setVisibility(0);
            }
            smartrsvpbottombar.proposeNewTimeChip.setText(((CharSequence) (obj1)));
            obj1 = smartrsvpbottombar.proposeNewTimeChip;
            if (((Chip) (obj1)).chipDrawable != null)
            {
                ((Chip) (obj1)).chipDrawable.setCloseIconVisible(flag1);
            }
            obj4 = smartrsvpbottombar.proposeNewTimeChip;
            obj1 = obj2;
            if (flag1)
            {
                obj1 = new SmartRsvpBottomBar..Lambda._cls1(smartrsvpbottombar);
            }
            obj4.onCloseIconClickListener = ((android.view.View.OnClickListener) (obj1));
        }
        smartrsvpbottombar.moveBottomBar(((SmartRsvpBottomBar.RsvpBottomBarMode) (obj)));
        obj = super.commandBar;
        if (obj != null)
        {
            ((View) (obj)).setVisibility(0);
            return;
        }
        if (true) goto _L6; else goto _L4
_L4:
        if (((PermissionHolder)(EventHolder)super.model).getPermissions().getAttendeePermissions().canProposeNewTime() && ((SmartRsvpBottomBar)super.commandBar).currentMode.isExpanded)
        {
            obj = SmartRsvpBottomBar.RsvpBottomBarMode.FOLLOW_UP_WITHOUT_PROPOSAL;
        } else
        if (((PermissionHolder)(EventHolder)super.model).getPermissions().getAttendeePermissions().canProposeNewTime())
        {
            obj = SmartRsvpBottomBar.RsvpBottomBarMode.RSVP_WITH_TOGGLE;
        } else
        {
            obj = SmartRsvpBottomBar.RsvpBottomBarMode.RSVP_WITHOUT_TOGGLE;
        }
          goto _L7
    }

    public final void onScopeSelected(int i, com.google.android.calendar.event.scope.ScopeSelectionDialog.Config config)
    {
        config = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.createFromInteger(config.additionalArguments().getInt("ARGUMENT_RSVP_STATUS"));
        Callback callback = (Callback)super.callback;
        if (config != com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION)
        {
            setActionSelectedStates(config);
            callback.onRsvpClicked(config, i, false);
        }
    }

    public final void removeTimeProposal()
    {
        ((Callback)super.callback).onProposeNewTimeChipClosed();
    }

    protected final void setupCommandBar(BottomBar bottombar)
    {
        ((SmartRsvpBottomBar)commandBar).setDescription(((SmartRsvpBottomBar)commandBar).getResources().getString(0x7f1303fa));
        ((SmartRsvpBottomBar)commandBar).removeProposalListener = this;
    }


    private class Callback
        implements EventCommandBarController.Callback
    {

        public abstract void onProposeNewTimeChipClosed();
    }

}
