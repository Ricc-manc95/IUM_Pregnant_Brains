// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;

// Referenced classes of package com.google.android.calendar.api.habit:
//            Habit, HabitDescriptor

public final class FitIntegrationStore
{

    public final SharedPreferences sharedPreferences;

    public FitIntegrationStore(Context context)
    {
        this(context.getSharedPreferences("fit_integration", 0));
    }

    private FitIntegrationStore(SharedPreferences sharedpreferences)
    {
        sharedPreferences = sharedpreferences;
    }

    public static String toKey(String s, String s1, String s2)
    {
        return (new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(s1).length() + String.valueOf(s2).length())).append(s).append("|").append(s1).append("|").append(s2).toString();
    }

    public final void setIntegration(Habit habit)
    {
        HabitDescriptor habitdescriptor = habit.getDescriptor();
        setIntegration(habitdescriptor.calendar.account.name, habitdescriptor.calendar.calendarId, habitdescriptor.habitId, habit.getFitIntegrationStatus());
    }

    public final void setIntegration(String s, String s1, String s2, int i)
    {
        s = toKey(s, s1, s2);
        if (i == 0)
        {
            sharedPreferences.edit().remove(s).apply();
            return;
        } else
        {
            sharedPreferences.edit().putInt(s, i).apply();
            return;
        }
    }
}
