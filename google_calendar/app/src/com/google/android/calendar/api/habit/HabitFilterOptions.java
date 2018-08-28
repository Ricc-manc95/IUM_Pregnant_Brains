// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;


public final class HabitFilterOptions
{

    public static final HabitFilterOptions DEFAULT = new HabitFilterOptions();
    public String accountName;
    public Long activeAfterFilter;
    public String calendarId;
    public Boolean dirtyFilter;
    public int fitIntegrationStatusFilter[];

    public HabitFilterOptions()
    {
        fitIntegrationStatusFilter = null;
        accountName = null;
        calendarId = null;
    }

    public HabitFilterOptions(String s)
    {
        fitIntegrationStatusFilter = null;
        accountName = null;
        calendarId = null;
        accountName = s;
    }

    public final HabitFilterOptions setFitIntegrationStatus(Integer integer)
    {
label0:
        {
            int ai[];
            if (integer == null)
            {
                integer = null;
            } else
            {
                int ai1[] = new int[1];
                ai1[0] = integer.intValue();
                integer = ai1;
            }
            if (integer != null)
            {
                ai = integer;
                if (integer.length != 0)
                {
                    break label0;
                }
            }
            ai = null;
        }
        fitIntegrationStatusFilter = ai;
        return this;
    }

}
