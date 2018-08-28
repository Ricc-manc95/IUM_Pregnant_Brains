// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            CustomizedSnoozePreset, zzg, zzaj, Time

public class zzh extends zza
    implements CustomizedSnoozePreset
{

    public static final android.os.Parcelable.Creator CREATOR = new zzg();
    public final int mVersionCode;
    public final zzaj zzchO;
    public final zzaj zzchP;
    public final zzaj zzchQ;

    zzh(int i, zzaj zzaj1, zzaj zzaj2, zzaj zzaj3)
    {
        zzchO = zzaj1;
        zzchP = zzaj2;
        zzchQ = zzaj3;
        mVersionCode = i;
    }

    public zzh(Time time, Time time1, Time time2, boolean flag)
    {
        mVersionCode = 2;
        zzchO = (zzaj)time;
        zzchP = (zzaj)time1;
        zzchQ = (zzaj)time2;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof CustomizedSnoozePreset)
        {
            if (this == obj)
            {
                return true;
            }
            obj = (CustomizedSnoozePreset)obj;
            Time time = getMorningCustomizedTime();
            Time time3 = ((CustomizedSnoozePreset) (obj)).getMorningCustomizedTime();
            boolean flag;
            if (time == time3 || time != null && time.equals(time3))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Time time1 = getAfternoonCustomizedTime();
                Time time4 = ((CustomizedSnoozePreset) (obj)).getAfternoonCustomizedTime();
                if (time1 == time4 || time1 != null && time1.equals(time4))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    Time time2 = getEveningCustomizedTime();
                    obj = ((CustomizedSnoozePreset) (obj)).getEveningCustomizedTime();
                    if (time2 == obj || time2 != null && time2.equals(obj))
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

    public final Time getAfternoonCustomizedTime()
    {
        return zzchP;
    }

    public final Time getEveningCustomizedTime()
    {
        return zzchQ;
    }

    public final Time getMorningCustomizedTime()
    {
        return zzchO;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            getMorningCustomizedTime(), getAfternoonCustomizedTime(), getEveningCustomizedTime()
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
        zzc.zza(parcel, 2, zzchO, i, false);
        zzc.zza(parcel, 3, zzchP, i, false);
        zzc.zza(parcel, 4, zzchQ, i, false);
        zzc.zzJ(parcel, j);
    }

}
