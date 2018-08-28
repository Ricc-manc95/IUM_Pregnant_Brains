// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.DailyPattern;
import com.google.android.gms.reminders.model.MonthlyPattern;
import com.google.android.gms.reminders.model.Recurrence;
import com.google.android.gms.reminders.model.RecurrenceEnd;
import com.google.android.gms.reminders.model.RecurrenceStart;
import com.google.android.gms.reminders.model.WeeklyPattern;
import com.google.android.gms.reminders.model.YearlyPattern;
import com.google.android.gms.reminders.model.zzz;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zzn, zzk, zzd, 
//            zzr, zzj, zzs

public class zzm extends zzo
    implements Recurrence
{

    private boolean zzchb;
    private zzn zzchc;
    private boolean zzchd;
    private zzk zzche;
    private boolean zzchf;
    private zzd zzchg;
    private boolean zzchh;
    private zzr zzchi;
    private boolean zzchj;
    private zzj zzchk;
    private boolean zzchl;
    private zzs zzchm;

    public zzm(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzchb = false;
        zzchd = false;
        zzchf = false;
        zzchh = false;
        zzchj = false;
        zzchl = false;
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = zzaw(s, "recurrence_frequency");
        dataholder.zzj(s1, i);
        if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            String s2 = zzaw(s, "recurrence_every");
            dataholder.zzj(s2, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s2)) && zzn.zza(dataholder, i, j, s) && zzk.zza(dataholder, i, j, s) && zzd.zza(dataholder, i, j, s))
            {
                String s3 = zzr.zzaw(s, "weekly_pattern_weekday");
                dataholder.zzj(s3, i);
                if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s3)) && zzj.zza(dataholder, i, j, s) && zzs.zza(dataholder, i, j, s))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int describeContents()
    {
        return 0;
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
            return zzz.zza(this, (Recurrence)obj);
        }
    }

    public final Object freeze()
    {
        return new zzz(this);
    }

    public final DailyPattern getDailyPattern()
    {
        if (!zzchf)
        {
            zzchf = true;
            if (zzd.zza(zzaKT, zzaNQ, zzaNR, zzchp))
            {
                zzchg = null;
            } else
            {
                zzchg = new zzd(zzaKT, zzaNQ, zzchp);
            }
        }
        return zzchg;
    }

    public final Integer getEvery()
    {
        String s = zziU("recurrence_every");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final Integer getFrequency()
    {
        String s = zziU("recurrence_frequency");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final MonthlyPattern getMonthlyPattern()
    {
        if (!zzchj)
        {
            zzchj = true;
            if (zzj.zza(zzaKT, zzaNQ, zzaNR, zzchp))
            {
                zzchk = null;
            } else
            {
                zzchk = new zzj(zzaKT, zzaNQ, zzchp);
            }
        }
        return zzchk;
    }

    public final RecurrenceEnd getRecurrenceEnd()
    {
        if (!zzchd)
        {
            zzchd = true;
            if (zzk.zza(zzaKT, zzaNQ, zzaNR, zzchp))
            {
                zzche = null;
            } else
            {
                zzche = new zzk(zzaKT, zzaNQ, zzchp);
            }
        }
        return zzche;
    }

    public final RecurrenceStart getRecurrenceStart()
    {
        if (!zzchb)
        {
            zzchb = true;
            if (zzn.zza(zzaKT, zzaNQ, zzaNR, zzchp))
            {
                zzchc = null;
            } else
            {
                zzchc = new zzn(zzaKT, zzaNQ, zzchp);
            }
        }
        return zzchc;
    }

    public final WeeklyPattern getWeeklyPattern()
    {
        if (!zzchh)
        {
            zzchh = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int j = zzaNR;
            String s = zzr.zzaw(zzchp, "weekly_pattern_weekday");
            dataholder.zzj(s, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
            {
                zzchi = null;
            } else
            {
                zzchi = new zzr(zzaKT, zzaNQ, zzchp);
            }
        }
        return zzchi;
    }

    public final YearlyPattern getYearlyPattern()
    {
        if (!zzchl)
        {
            zzchl = true;
            if (zzs.zza(zzaKT, zzaNQ, zzaNR, zzchp))
            {
                zzchm = null;
            } else
            {
                zzchm = new zzs(zzaKT, zzaNQ, zzchp);
            }
        }
        return zzchm;
    }

    public int hashCode()
    {
        return zzz.zzb(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzz(this)).writeToParcel(parcel, i);
    }
}
