// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.CalendarListHolder;
import com.google.android.calendar.newapi.model.EventHolder;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            BottomBarController, AssistBottomBar, BottomBar

public final class EveryoneDeclinedBottomBarController extends BottomBarController
    implements android.view.View.OnClickListener
{

    private static final int ACTION_IDS[] = {
        0x7f100277, 0x7f100278
    };
    private boolean isLogged;

    public EveryoneDeclinedBottomBarController(Callback callback)
    {
        super(callback);
        isLogged = false;
    }

    private final void setActionText(int i, int j)
    {
        ((Button)((AssistBottomBar)super.commandBar).findViewById(0x7f100277)).setText(i);
        ((Button)((AssistBottomBar)super.commandBar).findViewById(0x7f100278)).setText(j);
    }

    private final boolean shouldShowFat(EventHolder eventholder)
    {
        Event event = ((EventHolder)super.model).getEvent();
        eventholder = ((CalendarListHolder)eventholder).getCalendarList();
        com.google.android.calendar.api.calendarlist.CalendarDescriptor calendardescriptor = event.getCalendar();
        boolean flag;
        if (((CalendarList) (eventholder)).fatSupportMap == null)
        {
            eventholder = null;
        } else
        {
            eventholder = (Boolean)((CalendarList) (eventholder)).fatSupportMap.get(calendardescriptor);
        }
        if (eventholder != null && eventholder.booleanValue())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (event.isAllDayEvent())
            {
                flag = false;
            } else
            if (event.isEndTimeUnspecified())
            {
                flag = false;
            } else
            {
                long l = event.getEndMillis() - event.getStartMillis();
                if (l > 0x5265c00L)
                {
                    flag = false;
                } else
                if (l == 0L)
                {
                    flag = false;
                } else
                {
                    flag = true;
                }
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    protected final int getActionsLayout()
    {
        return 0x7f0500d2;
    }

    protected final int[] getPrimaryActionIds()
    {
        return ACTION_IDS;
    }

    protected final BottomBar inflateCommandBar(Context context, ViewGroup viewgroup)
    {
        return (AssistBottomBar)LayoutInflater.from(context).inflate(0x7f0500d1, viewgroup, false);
    }

    public final void onClick(View view)
    {
        Context context = super.commandBar.getContext();
        if (AttendeeUtils.isOrganizerAndListed(((EventHolder)super.model).getEvent()))
        {
            view = "dismissed_organizer";
        } else
        {
            view = "dismissed_guest";
        }
        LoggingUtils.logEveryoneDeclined(context, view, true, ((EventHolder)super.model).getEvent().getAttendees());
        ((Callback)super.callback).onDismiss();
    }

    protected final void onCommandBarActionClick(Object obj, int i)
    {
        obj = (Callback)obj;
        boolean flag = AttendeeUtils.isOrganizerAndListed(((EventHolder)super.model).getEvent());
        com.google.common.collect.ImmutableList immutablelist = ((EventHolder)super.model).getEvent().getAttendees();
        if (i == 0x7f100277)
        {
            ((Callback) (obj)).onDelete();
            Context context = super.commandBar.getContext();
            if (flag)
            {
                obj = "delete";
            } else
            {
                obj = "remove";
            }
            LoggingUtils.logEveryoneDeclined(context, ((String) (obj)), true, immutablelist);
        } else
        if (i == 0x7f100278)
        {
            if (flag)
            {
                if (shouldShowFat((EventHolder)super.model))
                {
                    LoggingUtils.logEveryoneDeclined(super.commandBar.getContext(), "reschedule", true, immutablelist);
                    ((Callback) (obj)).onReschedule();
                    return;
                } else
                {
                    LoggingUtils.logEveryoneDeclined(super.commandBar.getContext(), "email_guests_organizer", true, immutablelist);
                    ((Callback) (obj)).onEmailGuests();
                    return;
                }
            } else
            {
                LoggingUtils.logEveryoneDeclined(super.commandBar.getContext(), "email_guests", true, immutablelist);
                ((Callback) (obj)).onEmailGuests();
                return;
            }
        }
    }

    protected final void onModelChanged(Object obj)
    {
        obj = (EventHolder)obj;
        if (!AttendeeUtils.isOrganizerAndListed(((EventHolder)super.model).getEvent())) goto _L2; else goto _L1
_L1:
        int i;
        if (shouldShowFat(((EventHolder) (obj))))
        {
            i = 0x7f1301e1;
        } else
        {
            i = 0x7f1301de;
        }
        setActionText(0x7f1301dd, i);
        if (!isLogged)
        {
            LoggingUtils.logEveryoneDeclined(super.commandBar.getContext(), "shown_organizer", true, ((EventHolder) (obj)).getEvent().getAttendees());
            isLogged = true;
        }
_L4:
        obj = super.commandBar;
        if (obj != null)
        {
            ((View) (obj)).setVisibility(0);
        }
        return;
_L2:
        setActionText(0x7f1301dd, 0x7f1301de);
        if (!isLogged)
        {
            LoggingUtils.logEveryoneDeclined(super.commandBar.getContext(), "shown_invitee", true, ((EventHolder) (obj)).getEvent().getAttendees());
            isLogged = true;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected final void setupCommandBar(BottomBar bottombar)
    {
        ((AssistBottomBar)super.commandBar).setDescription(super.commandBar.getContext().getString(0x7f1301df));
        ((AssistBottomBar)super.commandBar).findViewById(0x7f100275).setOnClickListener(this);
    }


    private class Callback
    {

        public abstract void onDelete();

        public abstract void onDismiss();

        public abstract void onEmailGuests();

        public abstract void onReschedule();
    }

}
