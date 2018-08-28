// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            Recurrence, zzw, zzad, zzy, 
//            zzj, zzal, zzv, zzan, 
//            RecurrenceStart, RecurrenceEnd, DailyPattern, WeeklyPattern, 
//            MonthlyPattern, YearlyPattern

public class zzz extends zza
    implements Recurrence
{

    public static final android.os.Parcelable.Creator CREATOR = new zzw();
    public final int mVersionCode;
    public final Integer zzciA;
    public final Integer zzciB;
    public final zzad zzciO;
    public final zzy zzciP;
    public final zzj zzciQ;
    public final zzal zzciR;
    public final zzv zzciS;
    public final zzan zzciT;

    zzz(int i, Integer integer, Integer integer1, zzad zzad1, zzy zzy1, zzj zzj1, zzal zzal1, 
            zzv zzv1, zzan zzan1)
    {
        zzciA = integer;
        zzciB = integer1;
        zzciO = zzad1;
        zzciP = zzy1;
        zzciQ = zzj1;
        zzciR = zzal1;
        zzciS = zzv1;
        zzciT = zzan1;
        mVersionCode = i;
    }

    public zzz(Recurrence recurrence)
    {
        this(recurrence.getFrequency(), recurrence.getEvery(), recurrence.getRecurrenceStart(), recurrence.getRecurrenceEnd(), recurrence.getDailyPattern(), recurrence.getWeeklyPattern(), recurrence.getMonthlyPattern(), recurrence.getYearlyPattern(), false);
    }

    zzz(Integer integer, Integer integer1, RecurrenceStart recurrencestart, RecurrenceEnd recurrenceend, DailyPattern dailypattern, WeeklyPattern weeklypattern, MonthlyPattern monthlypattern, 
            YearlyPattern yearlypattern, boolean flag)
    {
        mVersionCode = 1;
        zzciA = integer;
        zzciB = integer1;
        if (flag)
        {
            zzciO = (zzad)recurrencestart;
            zzciP = (zzy)recurrenceend;
            zzciQ = (zzj)dailypattern;
            zzciR = (zzal)weeklypattern;
            zzciS = (zzv)monthlypattern;
            integer = (zzan)yearlypattern;
        } else
        {
            if (recurrencestart == null)
            {
                integer = null;
            } else
            {
                integer = new zzad(recurrencestart);
            }
            zzciO = integer;
            if (recurrenceend == null)
            {
                integer = null;
            } else
            {
                integer = new zzy(recurrenceend);
            }
            zzciP = integer;
            if (dailypattern == null)
            {
                integer = null;
            } else
            {
                integer = new zzj(dailypattern);
            }
            zzciQ = integer;
            if (weeklypattern == null)
            {
                integer = null;
            } else
            {
                integer = new zzal(weeklypattern);
            }
            zzciR = integer;
            if (monthlypattern == null)
            {
                integer = null;
            } else
            {
                integer = new zzv(monthlypattern);
            }
            zzciS = integer;
            if (yearlypattern == null)
            {
                integer = null;
            } else
            {
                integer = new zzan(yearlypattern);
            }
        }
        zzciT = integer;
    }

    public static boolean zza(Recurrence recurrence, Recurrence recurrence1)
    {
        Integer integer = recurrence.getFrequency();
        Integer integer2 = recurrence1.getFrequency();
        boolean flag;
        if (integer == integer2 || integer != null && integer.equals(integer2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Integer integer1 = recurrence.getEvery();
            Integer integer3 = recurrence1.getEvery();
            if (integer1 == integer3 || integer1 != null && integer1.equals(integer3))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                RecurrenceStart recurrencestart = recurrence.getRecurrenceStart();
                RecurrenceStart recurrencestart1 = recurrence1.getRecurrenceStart();
                if (recurrencestart == recurrencestart1 || recurrencestart != null && recurrencestart.equals(recurrencestart1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    RecurrenceEnd recurrenceend = recurrence.getRecurrenceEnd();
                    RecurrenceEnd recurrenceend1 = recurrence1.getRecurrenceEnd();
                    if (recurrenceend == recurrenceend1 || recurrenceend != null && recurrenceend.equals(recurrenceend1))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        DailyPattern dailypattern = recurrence.getDailyPattern();
                        DailyPattern dailypattern1 = recurrence1.getDailyPattern();
                        if (dailypattern == dailypattern1 || dailypattern != null && dailypattern.equals(dailypattern1))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            WeeklyPattern weeklypattern = recurrence.getWeeklyPattern();
                            WeeklyPattern weeklypattern1 = recurrence1.getWeeklyPattern();
                            if (weeklypattern == weeklypattern1 || weeklypattern != null && weeklypattern.equals(weeklypattern1))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                MonthlyPattern monthlypattern = recurrence.getMonthlyPattern();
                                MonthlyPattern monthlypattern1 = recurrence1.getMonthlyPattern();
                                if (monthlypattern == monthlypattern1 || monthlypattern != null && monthlypattern.equals(monthlypattern1))
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (flag)
                                {
                                    recurrence = recurrence.getYearlyPattern();
                                    recurrence1 = recurrence1.getYearlyPattern();
                                    if (recurrence == recurrence1 || recurrence != null && recurrence.equals(recurrence1))
                                    {
                                        flag = true;
                                    } else
                                    {
                                        flag = false;
                                    }
                                    if (flag)
                                    {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static int zzb(Recurrence recurrence)
    {
        return Arrays.hashCode(new Object[] {
            recurrence.getFrequency(), recurrence.getEvery(), recurrence.getRecurrenceStart(), recurrence.getRecurrenceEnd(), recurrence.getDailyPattern(), recurrence.getWeeklyPattern(), recurrence.getMonthlyPattern(), recurrence.getYearlyPattern()
        });
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Recurrence))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (Recurrence)obj);
        }
    }

    public final Object freeze()
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }

    public final DailyPattern getDailyPattern()
    {
        return zzciQ;
    }

    public final Integer getEvery()
    {
        return zzciB;
    }

    public final Integer getFrequency()
    {
        return zzciA;
    }

    public final MonthlyPattern getMonthlyPattern()
    {
        return zzciS;
    }

    public final RecurrenceEnd getRecurrenceEnd()
    {
        return zzciP;
    }

    public final RecurrenceStart getRecurrenceStart()
    {
        return zzciO;
    }

    public final WeeklyPattern getWeeklyPattern()
    {
        return zzciR;
    }

    public final YearlyPattern getYearlyPattern()
    {
        return zzciT;
    }

    public int hashCode()
    {
        return zzb(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        Integer integer = zzciA;
        if (integer != null)
        {
            zzc.zzb(parcel, 2, 4);
            parcel.writeInt(integer.intValue());
        }
        integer = zzciB;
        if (integer != null)
        {
            zzc.zzb(parcel, 3, 4);
            parcel.writeInt(integer.intValue());
        }
        zzc.zza(parcel, 4, zzciO, i, false);
        zzc.zza(parcel, 5, zzciP, i, false);
        zzc.zza(parcel, 6, zzciQ, i, false);
        zzc.zza(parcel, 7, zzciR, i, false);
        zzc.zza(parcel, 8, zzciS, i, false);
        zzc.zza(parcel, 9, zzciT, i, false);
        zzc.zzJ(parcel, j);
    }

}
