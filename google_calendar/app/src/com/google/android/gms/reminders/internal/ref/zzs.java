// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.model.MonthlyPattern;
import com.google.android.gms.reminders.model.YearlyPattern;
import com.google.android.gms.reminders.model.zzan;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zzj

public class zzs extends zzo
    implements YearlyPattern
{

    private boolean zzchj;
    private zzj zzchk;

    public zzs(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzchj = false;
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = String.valueOf(s);
        String s2 = String.valueOf("yearly_pattern_");
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        if (zzj.zza(dataholder, i, j, s1))
        {
            s = zzaw(s, "yearly_pattern_year_month");
            dataholder.zzj(s, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
            {
                return true;
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
        if (!(obj instanceof YearlyPattern))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzan.zza(this, (YearlyPattern)obj);
        }
    }

    public final Object freeze()
    {
        return new zzan(this);
    }

    public final MonthlyPattern getMonthlyPattern()
    {
        if (!zzchj)
        {
            zzchj = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int k = zzaNR;
            String s = String.valueOf(zzchp);
            String s2 = String.valueOf("yearly_pattern_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zzj.zza(dataholder, i, k, s))
            {
                zzchk = null;
            } else
            {
                DataHolder dataholder1 = zzaKT;
                int j = zzaNQ;
                String s1 = String.valueOf(zzchp);
                String s3 = String.valueOf("yearly_pattern_");
                if (s3.length() != 0)
                {
                    s1 = s1.concat(s3);
                } else
                {
                    s1 = new String(s1);
                }
                zzchk = new zzj(dataholder1, j, s1);
            }
        }
        return zzchk;
    }

    public final List getYearMonth()
    {
        return zziV(zziU("yearly_pattern_year_month"));
    }

    public int hashCode()
    {
        return zzan.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzan(this)).writeToParcel(parcel, i);
    }
}
