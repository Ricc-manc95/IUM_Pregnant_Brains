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
//            MonthlyPattern, zzu

public class zzv extends zza
    implements MonthlyPattern
{

    public static final android.os.Parcelable.Creator CREATOR = new zzu();
    public final int mVersionCode;
    public final List zzcix;
    public final Integer zzciy;
    public final Integer zzciz;

    zzv(int i, List list, Integer integer, Integer integer1)
    {
        zzcix = list;
        zzciy = integer;
        zzciz = integer1;
        mVersionCode = i;
    }

    public zzv(MonthlyPattern monthlypattern)
    {
        this(monthlypattern.getMonthDay(), monthlypattern.getWeekDay(), monthlypattern.getWeekDayNumber(), false);
    }

    public zzv(List list, Integer integer, Integer integer1, boolean flag)
    {
        mVersionCode = 1;
        zzciy = integer;
        zzciz = integer1;
        if (!flag)
        {
            if (list == null)
            {
                list = null;
            } else
            {
                list = new ArrayList(list);
            }
        }
        zzcix = list;
    }

    public static int zza(MonthlyPattern monthlypattern)
    {
        return Arrays.hashCode(new Object[] {
            monthlypattern.getMonthDay(), monthlypattern.getWeekDay(), monthlypattern.getWeekDayNumber()
        });
    }

    public static boolean zza(MonthlyPattern monthlypattern, MonthlyPattern monthlypattern1)
    {
        List list = monthlypattern.getMonthDay();
        List list1 = monthlypattern1.getMonthDay();
        boolean flag;
        if (list == list1 || list != null && list.equals(list1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Integer integer = monthlypattern.getWeekDay();
            Integer integer1 = monthlypattern1.getWeekDay();
            if (integer == integer1 || integer != null && integer.equals(integer1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                monthlypattern = monthlypattern.getWeekDayNumber();
                monthlypattern1 = monthlypattern1.getWeekDayNumber();
                if (monthlypattern == monthlypattern1 || monthlypattern != null && monthlypattern.equals(monthlypattern1))
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
        return false;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof MonthlyPattern))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (MonthlyPattern)obj);
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

    public final List getMonthDay()
    {
        return zzcix;
    }

    public final Integer getWeekDay()
    {
        return zzciy;
    }

    public final Integer getWeekDayNumber()
    {
        return zzciz;
    }

    public int hashCode()
    {
        return zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzcix, false);
        Integer integer = zzciy;
        if (integer != null)
        {
            zzc.zzb(parcel, 4, 4);
            parcel.writeInt(integer.intValue());
        }
        integer = zzciz;
        if (integer != null)
        {
            zzc.zzb(parcel, 5, 4);
            parcel.writeInt(integer.intValue());
        }
        zzc.zzJ(parcel, i);
    }

}
