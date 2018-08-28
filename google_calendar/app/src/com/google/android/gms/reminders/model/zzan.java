// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders.model:
//            YearlyPattern, zzam, zzv, MonthlyPattern

public class zzan extends zza
    implements YearlyPattern
{

    public static final android.os.Parcelable.Creator CREATOR = new zzam();
    public final int mVersionCode;
    public final zzv zzciS;
    public final List zzcjK;

    zzan(int i, zzv zzv1, List list)
    {
        zzciS = zzv1;
        zzcjK = list;
        mVersionCode = i;
    }

    public zzan(MonthlyPattern monthlypattern, List list, boolean flag)
    {
        mVersionCode = 1;
        if (flag)
        {
            zzciS = (zzv)monthlypattern;
        } else
        {
            if (monthlypattern == null)
            {
                monthlypattern = null;
            } else
            {
                monthlypattern = new zzv(monthlypattern);
            }
            zzciS = monthlypattern;
            if (list == null)
            {
                list = null;
            } else
            {
                list = new ArrayList(list);
            }
        }
        zzcjK = list;
    }

    public zzan(YearlyPattern yearlypattern)
    {
        this(yearlypattern.getMonthlyPattern(), yearlypattern.getYearMonth(), false);
    }

    public static int zza(YearlyPattern yearlypattern)
    {
        return Arrays.hashCode(new Object[] {
            yearlypattern.getMonthlyPattern(), yearlypattern.getYearMonth()
        });
    }

    public static boolean zza(YearlyPattern yearlypattern, YearlyPattern yearlypattern1)
    {
        MonthlyPattern monthlypattern = yearlypattern.getMonthlyPattern();
        MonthlyPattern monthlypattern1 = yearlypattern1.getMonthlyPattern();
        boolean flag;
        if (monthlypattern == monthlypattern1 || monthlypattern != null && monthlypattern.equals(monthlypattern1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            yearlypattern = yearlypattern.getYearMonth();
            yearlypattern1 = yearlypattern1.getYearMonth();
            if (yearlypattern == yearlypattern1 || yearlypattern != null && yearlypattern.equals(yearlypattern1))
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
        return false;
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
            return zza(this, (YearlyPattern)obj);
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

    public final MonthlyPattern getMonthlyPattern()
    {
        return zzciS;
    }

    public final List getYearMonth()
    {
        return zzcjK;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            getMonthlyPattern(), getYearMonth()
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzciS, i, false);
        zzc.zza(parcel, 3, zzcjK, false);
        zzc.zzJ(parcel, j);
    }

}
