// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.text.TextUtils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.utils.account.AccountUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            Event, EventModifications, EventDescriptor

public final class EventPermissionUtils
{

    public static final List DEFAULT_VSIBILITITY_LIST = Collections.unmodifiableList(Arrays.asList(new Integer[] {
        Integer.valueOf(0), Integer.valueOf(2), Integer.valueOf(1)
    }));

    public static boolean attendeeEmailMatchesCalendar(AttendeeDescriptor attendeedescriptor, Event event)
    {
        if (!TextUtils.isEmpty(attendeedescriptor.email))
        {
            attendeedescriptor = attendeedescriptor.email;
            event = event.getCalendar().calendarId;
            if (attendeedescriptor != null && attendeedescriptor.equalsIgnoreCase(event))
            {
                return true;
            }
        }
        return false;
    }

    static boolean canChangeOrganizer(Event event)
    {
        return (event instanceof EventModifications) && ((EventModifications)event).isNewEvent();
    }

    static List getAllowedModificationScopesForEvent(Event event)
    {
        if ((event instanceof EventModifications) && ((EventModifications)event).isNewEvent())
        {
            return Collections.emptyList();
        }
        if (!event.getDescriptor().isRecurringPhantom())
        {
            return Collections.singletonList(Integer.valueOf(0));
        }
        ArrayList arraylist = new ArrayList();
        if (!TextUtils.isEmpty(event.getSyncId()))
        {
            arraylist.add(Integer.valueOf(0));
        }
        if (event.isRecurring() && event.getRecurrence().exrules.isEmpty() && !event.getRecurrence().exdates.contains(Long.valueOf(event.getRecurringFirstStartMillis())))
        {
            arraylist.add(Integer.valueOf(1));
        }
        arraylist.add(Integer.valueOf(2));
        return Collections.unmodifiableList(arraylist);
    }

    public static boolean isExchangeEvent(Event event)
    {
        boolean flag1 = false;
        event = event.getCalendar();
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        if (AccountUtil.isGoogleExchangeAccount(((CalendarDescriptor) (event)).account) || AccountUtil.isExchangeAccount(((CalendarDescriptor) (event)).account))
        {
            flag1 = true;
        }
        return flag1;
    }

    public static boolean isGoogleEvent(Event event)
    {
        event = event.getCalendar();
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            return AccountUtil.isGoogleAccount(((CalendarDescriptor) (event)).account);
        }
    }

    public static boolean isGoogleExchangeEvent(Event event)
    {
        event = event.getCalendar();
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            return AccountUtil.isGoogleExchangeAccount(((CalendarDescriptor) (event)).account);
        }
    }

}
