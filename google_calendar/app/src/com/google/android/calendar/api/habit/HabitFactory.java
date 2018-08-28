// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import com.google.android.calendar.api.calendarlist.CalendarDescriptor;

// Referenced classes of package com.google.android.calendar.api.habit:
//            Habit, HabitModifications

public interface HabitFactory
{

    public abstract HabitModifications modifyHabit(Habit habit);

    public abstract HabitModifications newHabit(CalendarDescriptor calendardescriptor);
}
