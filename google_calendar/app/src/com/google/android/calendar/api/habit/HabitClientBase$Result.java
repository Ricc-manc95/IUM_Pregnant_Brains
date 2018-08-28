// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import com.google.android.gms.common.api.Status;
import com.google.common.base.Throwables;

// Referenced classes of package com.google.android.calendar.api.habit:
//            Habit

public final class status
    implements ult, lt, lt, lt
{

    private final int count;
    private final Habit habit;
    private final Habit habits[];
    private final Status status;

    public final int getCount()
    {
        return count;
    }

    public final Habit getHabit()
    {
        return habit;
    }

    public final Habit[] getHabits()
    {
        return habits;
    }

    public final Status getStatus()
    {
        return status;
    }

    lt(int i, int j, Habit habit1, Habit ahabit[])
    {
        count = j;
        status = new Status(i);
        habit = habit1;
        habits = ahabit;
    }

    protected habits(Throwable throwable)
    {
        count = 0;
        habit = null;
        habits = null;
        status = new Status(8, Throwables.getStackTraceAsString(throwable));
    }
}
