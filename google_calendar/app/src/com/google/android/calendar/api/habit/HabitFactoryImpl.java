// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.utils.account.AccountUtil;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitFactory, Habit, HabitModifications

public final class HabitFactoryImpl
    implements HabitFactory
{

    public HabitFactoryImpl()
    {
    }

    public final HabitModifications modifyHabit(Habit habit)
    {
        boolean flag;
        if (habit != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return new HabitImpl.Modification(habit);
        }
    }

    public final HabitModifications newHabit(CalendarDescriptor calendardescriptor)
    {
        if (calendardescriptor == null)
        {
            throw new NullPointerException();
        }
        if (!AccountUtil.isGoogleAccount(calendardescriptor.account))
        {
            throw new IllegalArgumentException();
        } else
        {
            return new HabitImpl.Modification(calendardescriptor);
        }
    }
}
