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
import com.google.android.gms.reminders.model.Time;
import com.google.android.gms.reminders.model.zzj;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zzq

public class zzd extends zzo
    implements DailyPattern
{

    private boolean zzcgJ;
    private zzq zzcgK;

    public zzd(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzcgJ = false;
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = String.valueOf(s);
        String s2 = String.valueOf("daily_pattern_");
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        if (zzq.zza(dataholder, i, j, s1))
        {
            s1 = zzaw(s, "daily_pattern_period");
            dataholder.zzj(s1, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
            {
                s = zzaw(s, "daily_pattern_all_day");
                dataholder.zzj(s, i);
                if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
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
        if (!(obj instanceof DailyPattern))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzj.zza(this, (DailyPattern)obj);
        }
    }

    public final Object freeze()
    {
        return new zzj(this);
    }

    public final Boolean getAllDay()
    {
        return Boolean.valueOf(getBoolean(zziU("daily_pattern_all_day")));
    }

    public final Integer getDayPeriod()
    {
        String s = zziU("daily_pattern_period");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final Time getTimeOfDay()
    {
        if (!zzcgJ)
        {
            zzcgJ = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int k = zzaNR;
            String s = String.valueOf(zzchp);
            String s2 = String.valueOf("daily_pattern_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zzq.zza(dataholder, i, k, s))
            {
                zzcgK = null;
            } else
            {
                DataHolder dataholder1 = zzaKT;
                int j = zzaNQ;
                String s1 = String.valueOf(zzchp);
                String s3 = String.valueOf("daily_pattern_");
                if (s3.length() != 0)
                {
                    s1 = s1.concat(s3);
                } else
                {
                    s1 = new String(s1);
                }
                zzcgK = new zzq(dataholder1, j, s1);
            }
        }
        return zzcgK;
    }

    public int hashCode()
    {
        return zzj.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzj(this)).writeToParcel(parcel, i);
    }
}
