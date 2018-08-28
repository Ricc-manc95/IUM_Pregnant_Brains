// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.habit.Habit;

// Referenced classes of package com.google.android.calendar.api.event:
//            Event, EventModifications

public interface EventFactory
{

    public abstract EventModifications modifyEvent(Event event);

    public abstract EventModifications modifyIcsImport(Event event, String s, int i, String s1);

    public abstract EventModifications newEvent(CalendarListEntry calendarlistentry);

    public abstract EventModifications newHabitInstance(Habit habit, CalendarListEntry calendarlistentry);

    public abstract EventModifications newIcsImport(CalendarListEntry calendarlistentry, String s, String s1, int i, String s2);
}
