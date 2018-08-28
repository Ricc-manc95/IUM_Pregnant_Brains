// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;


// Referenced classes of package com.google.android.gms.reminders.model:
//            Recurrence, zzad, zzy, zzj, 
//            zzal, zzv, zzan, zzz, 
//            RecurrenceStart, RecurrenceEnd, DailyPattern, WeeklyPattern, 
//            MonthlyPattern, YearlyPattern

public static final class zzciH
{

    public Integer zzciA;
    public Integer zzciB;
    public RecurrenceStart zzciC;
    public RecurrenceEnd zzciD;
    public DailyPattern zzciE;
    public WeeklyPattern zzciF;
    public MonthlyPattern zzciG;
    public YearlyPattern zzciH;

    public final Recurrence build()
    {
        return new zzz(zzciA, zzciB, zzciC, zzciD, zzciE, zzciF, zzciG, zzciH, true);
    }

    public ()
    {
    }

    public (Recurrence recurrence)
    {
        Object obj1 = null;
        super();
        zzciA = recurrence.getFrequency();
        zzciB = recurrence.getEvery();
        Object obj;
        if (recurrence.getRecurrenceStart() == null)
        {
            obj = null;
        } else
        {
            obj = new zzad(recurrence.getRecurrenceStart());
        }
        zzciC = ((RecurrenceStart) (obj));
        if (recurrence.getRecurrenceEnd() == null)
        {
            obj = null;
        } else
        {
            obj = new zzy(recurrence.getRecurrenceEnd());
        }
        zzciD = ((RecurrenceEnd) (obj));
        if (recurrence.getDailyPattern() == null)
        {
            obj = null;
        } else
        {
            obj = new zzj(recurrence.getDailyPattern());
        }
        zzciE = ((DailyPattern) (obj));
        if (recurrence.getWeeklyPattern() == null)
        {
            obj = null;
        } else
        {
            obj = new zzal(recurrence.getWeeklyPattern());
        }
        zzciF = ((WeeklyPattern) (obj));
        if (recurrence.getMonthlyPattern() == null)
        {
            obj = null;
        } else
        {
            obj = new zzv(recurrence.getMonthlyPattern());
        }
        zzciG = ((MonthlyPattern) (obj));
        if (recurrence.getYearlyPattern() == null)
        {
            recurrence = obj1;
        } else
        {
            recurrence = new zzan(recurrence.getYearlyPattern());
        }
        zzciH = recurrence;
    }
}
