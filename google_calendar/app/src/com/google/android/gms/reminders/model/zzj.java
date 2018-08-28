// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            DailyPattern, zzi, zzaj, Time

public class zzj extends zza
    implements DailyPattern
{

    public static final android.os.Parcelable.Creator CREATOR = new zzi();
    public final int mVersionCode;
    public final Integer zzchS;
    public final Boolean zzchT;
    public final zzaj zzchU;

    zzj(int i, zzaj zzaj1, Integer integer, Boolean boolean1)
    {
        zzchU = zzaj1;
        zzchS = integer;
        zzchT = boolean1;
        mVersionCode = i;
    }

    public zzj(DailyPattern dailypattern)
    {
        this(dailypattern.getTimeOfDay(), dailypattern.getDayPeriod(), dailypattern.getAllDay(), false);
    }

    public zzj(Time time, Integer integer, Boolean boolean1, boolean flag)
    {
        mVersionCode = 1;
        zzchS = integer;
        zzchT = boolean1;
        if (flag)
        {
            time = (zzaj)time;
        } else
        if (time == null)
        {
            time = null;
        } else
        {
            time = new zzaj(time);
        }
        zzchU = time;
    }

    public static int zza(DailyPattern dailypattern)
    {
        return Arrays.hashCode(new Object[] {
            dailypattern.getTimeOfDay(), dailypattern.getDayPeriod(), dailypattern.getAllDay()
        });
    }

    public static boolean zza(DailyPattern dailypattern, DailyPattern dailypattern1)
    {
        Time time = dailypattern.getTimeOfDay();
        Time time1 = dailypattern1.getTimeOfDay();
        boolean flag;
        if (time == time1 || time != null && time.equals(time1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Integer integer = dailypattern.getDayPeriod();
            Integer integer1 = dailypattern1.getDayPeriod();
            if (integer == integer1 || integer != null && integer.equals(integer1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                dailypattern = dailypattern.getAllDay();
                dailypattern1 = dailypattern1.getAllDay();
                if (dailypattern == dailypattern1 || dailypattern != null && dailypattern.equals(dailypattern1))
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
        if (!(obj instanceof DailyPattern))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (DailyPattern)obj);
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

    public final Boolean getAllDay()
    {
        return zzchT;
    }

    public final Integer getDayPeriod()
    {
        return zzchS;
    }

    public final Time getTimeOfDay()
    {
        return zzchU;
    }

    public int hashCode()
    {
        return zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzchU, i, false);
        Object obj = zzchS;
        if (obj != null)
        {
            zzc.zzb(parcel, 3, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        obj = zzchT;
        if (obj != null)
        {
            zzc.zzb(parcel, 4, 4);
            if (((Boolean) (obj)).booleanValue())
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
        }
        zzc.zzJ(parcel, j);
    }

}
