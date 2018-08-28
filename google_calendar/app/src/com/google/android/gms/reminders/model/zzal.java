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
//            WeeklyPattern, zzak

public class zzal extends zza
    implements WeeklyPattern
{

    public static final android.os.Parcelable.Creator CREATOR = new zzak();
    public final int mVersionCode;
    public final List zzcjJ;

    zzal(int i, List list)
    {
        zzcjJ = list;
        mVersionCode = i;
    }

    public zzal(WeeklyPattern weeklypattern)
    {
        this(weeklypattern.getWeekDay(), false);
    }

    public zzal(List list, boolean flag)
    {
        mVersionCode = 1;
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
        zzcjJ = list;
    }

    public static int zza(WeeklyPattern weeklypattern)
    {
        return Arrays.hashCode(new Object[] {
            weeklypattern.getWeekDay()
        });
    }

    public static boolean zza(WeeklyPattern weeklypattern, WeeklyPattern weeklypattern1)
    {
        weeklypattern = weeklypattern.getWeekDay();
        weeklypattern1 = weeklypattern1.getWeekDay();
        return weeklypattern == weeklypattern1 || weeklypattern != null && weeklypattern.equals(weeklypattern1);
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof WeeklyPattern)
        {
            if (this == obj)
            {
                return true;
            }
            Object obj1 = (WeeklyPattern)obj;
            obj = getWeekDay();
            obj1 = ((WeeklyPattern) (obj1)).getWeekDay();
            if (obj == obj1 || obj != null && obj.equals(obj1))
            {
                return true;
            }
        }
        return false;
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

    public final List getWeekDay()
    {
        return zzcjJ;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            getWeekDay()
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzcjJ, false);
        zzc.zzJ(parcel, i);
    }

}
