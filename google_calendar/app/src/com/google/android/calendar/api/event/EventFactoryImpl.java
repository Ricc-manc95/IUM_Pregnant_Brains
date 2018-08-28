// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.common.FieldModification;
import com.google.android.calendar.api.habit.Habit;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventFactory, EventModificationsImpl, Event, EventModifications

public final class EventFactoryImpl
    implements EventFactory
{

    public EventFactoryImpl()
    {
    }

    public final EventModifications modifyEvent(Event event)
    {
        if (event == null)
        {
            throw new NullPointerException();
        } else
        {
            return new EventModificationsImpl(event);
        }
    }

    public final EventModifications modifyIcsImport(Event event, String s, int i, String s1)
    {
        if (event == null)
        {
            throw new NullPointerException();
        }
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        EventModificationsImpl eventmodificationsimpl = new EventModificationsImpl(event);
        eventmodificationsimpl.icsOrganizerEmail = s;
        if (event.getSequenceNumber() == i)
        {
            s = new FieldModification();
        } else
        {
            s = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(i));
        }
        eventmodificationsimpl.sequenceNumber = s;
        event = event.getICalDtStamp();
        if (event == s1 || event != null && event.equals(s1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            event = new FieldModification();
        } else
        {
            event = new com.google.android.calendar.api.common.FieldModification._cls1(s1);
        }
        eventmodificationsimpl.iCalDtStamp = event;
        eventmodificationsimpl.icsImportOrUpdate = true;
        return eventmodificationsimpl;
    }

    public final EventModifications newEvent(CalendarListEntry calendarlistentry)
    {
        if (calendarlistentry == null)
        {
            throw new NullPointerException();
        } else
        {
            return new EventModificationsImpl(calendarlistentry);
        }
    }

    public final EventModifications newHabitInstance(Habit habit, CalendarListEntry calendarlistentry)
    {
        if (habit == null)
        {
            throw new NullPointerException();
        }
        if (calendarlistentry == null)
        {
            throw new NullPointerException();
        } else
        {
            return new EventModificationsImpl(habit, calendarlistentry);
        }
    }

    public final EventModifications newIcsImport(CalendarListEntry calendarlistentry, String s, String s1, int i, String s2)
    {
        if (calendarlistentry == null)
        {
            throw new NullPointerException();
        }
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        if (s2 == null)
        {
            throw new NullPointerException();
        } else
        {
            calendarlistentry = new EventModificationsImpl(calendarlistentry);
            calendarlistentry.icsOrganizerEmail = s;
            calendarlistentry.iCalUid = new com.google.android.calendar.api.common.FieldModification._cls1(s1);
            calendarlistentry.sequenceNumber = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(i));
            calendarlistentry.iCalDtStamp = new com.google.android.calendar.api.common.FieldModification._cls1(s2);
            calendarlistentry.icsImportOrUpdate = true;
            return calendarlistentry;
        }
    }
}
