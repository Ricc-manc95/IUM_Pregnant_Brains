// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitContract

public final class HabitContractImpl
    implements Parcelable, HabitContract
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public int dailyPatternBitmask;
    public int durationMinutes;
    public int interval;
    public int numInstancesPerInterval;
    public long untilMillisUtc;

    HabitContractImpl()
    {
        durationMinutes = 30;
        interval = 2;
        numInstancesPerInterval = 1;
    }

    static int calculateBitmask(HabitContract habitcontract)
    {
        byte byte1 = 0;
        boolean flag;
        byte byte0;
        if (habitcontract.isMorningPreferable())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (habitcontract.isAfternoonPreferable())
        {
            byte0 = 2;
        } else
        {
            byte0 = 0;
        }
        if (habitcontract.isEveningPreferable())
        {
            byte1 = 4;
        }
        return byte0 | (flag | false) | byte1;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final int getDurationMinutes()
    {
        return durationMinutes;
    }

    public final int getInterval()
    {
        return interval;
    }

    public final int getNumInstancesPerInterval()
    {
        return numInstancesPerInterval;
    }

    public final long getUntilMillisUtc()
    {
        return untilMillisUtc;
    }

    public final int hashCode()
    {
        return ((((getDurationMinutes() + 527) * 31 + getInterval()) * 31 + getNumInstancesPerInterval()) * 31 + calculateBitmask(this)) * 31 + Long.valueOf(getUntilMillisUtc()).hashCode();
    }

    public final boolean isAfternoonPreferable()
    {
        return (dailyPatternBitmask & 2) != 0;
    }

    public final boolean isAnyDayTimeAcceptable()
    {
        return dailyPatternBitmask == 0;
    }

    public final boolean isEveningPreferable()
    {
        return (dailyPatternBitmask & 4) != 0;
    }

    public final boolean isMorningPreferable()
    {
        return (dailyPatternBitmask & 1) != 0;
    }

    public final String toString()
    {
        int i = durationMinutes;
        int j = interval;
        int k = numInstancesPerInterval;
        int l = dailyPatternBitmask;
        long l1 = untilMillisUtc;
        return (new StringBuilder(180)).append("HabitContractImpl{mDurationMinutes=").append(i).append(", mInterval=").append(j).append(", mNumInstancesPerInterval=").append(k).append(", mDailyPatternBitmask=").append(l).append(", mUntilMillisUtc=").append(l1).append('}').toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(durationMinutes);
        parcel.writeInt(interval);
        parcel.writeInt(numInstancesPerInterval);
        parcel.writeInt(dailyPatternBitmask);
        parcel.writeLong(untilMillisUtc);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            HabitContractImpl habitcontractimpl = new HabitContractImpl();
            habitcontractimpl.durationMinutes = parcel.readInt();
            habitcontractimpl.interval = HabitUtil.checkInterval(parcel.readInt());
            habitcontractimpl.numInstancesPerInterval = parcel.readInt();
            habitcontractimpl.dailyPatternBitmask = parcel.readInt();
            habitcontractimpl.untilMillisUtc = parcel.readLong();
            return habitcontractimpl;
        }

        public final Object[] newArray(int i)
        {
            return new HabitContract[i];
        }

        _cls1()
        {
        }
    }

}
