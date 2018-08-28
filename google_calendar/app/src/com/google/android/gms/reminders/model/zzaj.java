// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            Time, zzai

public class zzaj extends zza
    implements Time
{

    public static final android.os.Parcelable.Creator CREATOR = new zzai();
    public final int mVersionCode;
    public final Integer zzcjG;
    public final Integer zzcjH;
    public final Integer zzcjI;

    zzaj(int i, Integer integer, Integer integer1, Integer integer2)
    {
        zzcjG = integer;
        zzcjH = integer1;
        zzcjI = integer2;
        mVersionCode = i;
    }

    public zzaj(Time time)
    {
        this(time.getHour(), time.getMinute(), time.getSecond());
    }

    public zzaj(Integer integer, Integer integer1, Integer integer2)
    {
        this(1, integer, integer1, integer2);
    }

    public static boolean zza(Time time, Time time1)
    {
        Integer integer = time.getHour();
        Integer integer2 = time1.getHour();
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
            Integer integer1 = time.getMinute();
            Integer integer3 = time1.getMinute();
            if (integer1 == integer3 || integer1 != null && integer1.equals(integer3))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                time = time.getSecond();
                time1 = time1.getSecond();
                if (time == time1 || time != null && time.equals(time1))
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

    public static int zzc(Time time)
    {
        return Arrays.hashCode(new Object[] {
            time.getHour(), time.getMinute(), time.getSecond()
        });
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Time))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (Time)obj);
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

    public final Integer getHour()
    {
        return zzcjG;
    }

    public final Integer getMinute()
    {
        return zzcjH;
    }

    public final Integer getSecond()
    {
        return zzcjI;
    }

    public int hashCode()
    {
        return zzc(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        Integer integer = zzcjG;
        if (integer != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 2, 4);
            parcel.writeInt(integer.intValue());
        }
        integer = zzcjH;
        if (integer != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 3, 4);
            parcel.writeInt(integer.intValue());
        }
        integer = zzcjI;
        if (integer != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 4, 4);
            parcel.writeInt(integer.intValue());
        }
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, i);
    }

}
