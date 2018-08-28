// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            AutoValue_TimeRange, TimeBoxUtil

public abstract class TimeRange
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private static final int DAY_MINUTES;

    public TimeRange()
    {
    }

    public static TimeRange newAllDayFromJulianDay(TimeZone timezone, int i, int j)
    {
        return new AutoValue_TimeRange(timezone, true, TimeBoxUtil.utcJulianDayToMs(i), TimeBoxUtil.utcJulianDayToMs(j + 1), i, j, 0, DAY_MINUTES);
    }

    public static TimeRange newInstance(TimeZone timezone, boolean flag, long l, long l1)
    {
        if (flag)
        {
            return new AutoValue_TimeRange(timezone, true, l, l1, TimeBoxUtil.msToUtcJulianDay(l), TimeBoxUtil.msToUtcJulianDay(l1) - 1, 0, DAY_MINUTES);
        } else
        {
            return newNonAllDay(timezone, l, l1);
        }
    }

    public static TimeRange newInstanceUnsafe(TimeZone timezone, boolean flag, long l, int i, int j, long l1, 
            int k, int i1)
    {
        return new AutoValue_TimeRange(timezone, flag, l, l1, i, k, j, i1);
    }

    public static TimeRange newNonAllDay(TimeZone timezone, long l, long l1)
    {
        if (l > l1)
        {
            throw new IllegalArgumentException((new StringBuilder(74)).append("Start time ").append(l).append("ms is after end time ").append(l1).append("ms").toString());
        }
        int i;
        int j;
        int k;
        if (TimeBoxUtil.isMidnight(timezone, l1) && l1 > l)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            i = DAY_MINUTES;
        } else
        {
            i = TimeBoxUtil.msToMinuteOfDay(timezone, l1);
        }
        k = TimeBoxUtil.msToJulianDay(timezone, l1);
        if (j != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        return new AutoValue_TimeRange(timezone, false, l, l1, TimeBoxUtil.msToJulianDay(timezone, l), k - j, TimeBoxUtil.msToMinuteOfDay(timezone, l), i);
    }

    public static TimeRange span(Iterable iterable, TimeZone timezone)
    {
        Iterator iterator = iterable.iterator();
        iterable = null;
        while (iterator.hasNext()) 
        {
            TimeRange timerange = (TimeRange)iterator.next();
            if (iterable == null)
            {
                iterable = timerange;
            } else
            {
                iterable = iterable.span(timerange, timezone);
            }
        }
        return iterable;
    }

    public int describeContents()
    {
        return 0;
    }

    public abstract int getEndDay();

    public abstract int getEndMinute();

    public long getLocalEndMillis()
    {
        long l;
        if (isAllDay())
        {
            l = getTimeZone().getOffset(getUtcEndMillis());
        } else
        {
            l = 0L;
        }
        return getUtcEndMillis() - l;
    }

    public long getLocalStartMillis()
    {
        long l;
        if (isAllDay())
        {
            l = getTimeZone().getOffset(getUtcStartMillis());
        } else
        {
            l = 0L;
        }
        return getUtcStartMillis() - l;
    }

    public abstract int getStartDay();

    public abstract int getStartMinute();

    public abstract TimeZone getTimeZone();

    public abstract long getUtcEndMillis();

    public abstract long getUtcStartMillis();

    public final boolean intersects(TimeRange timerange)
    {
        while (getLocalStartMillis() > getLocalEndMillis() || timerange.getLocalStartMillis() > timerange.getLocalEndMillis() || getLocalEndMillis() <= timerange.getLocalStartMillis() || timerange.getLocalEndMillis() <= getLocalStartMillis()) 
        {
            return false;
        }
        return true;
    }

    public abstract boolean isAllDay();

    public final TimeRange span(TimeRange timerange, TimeZone timezone)
    {
        if (timerange.equals(this))
        {
            return this;
        }
        if (isAllDay())
        {
            return newAllDayFromJulianDay(timezone, Math.min(getStartDay(), timerange.getStartDay()), Math.max(getEndDay(), timerange.getEndDay()));
        } else
        {
            return newNonAllDay(timezone, Math.min(getUtcStartMillis(), timerange.getUtcStartMillis()), Math.max(getUtcEndMillis(), timerange.getUtcEndMillis()));
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getTimeZone().getID());
        if (isAllDay())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeLong(getUtcStartMillis());
        parcel.writeInt(getStartDay());
        parcel.writeInt(getStartMinute());
        parcel.writeLong(getUtcEndMillis());
        parcel.writeInt(getEndDay());
        parcel.writeInt(getEndMinute());
    }

    static 
    {
        DAY_MINUTES = (int)TimeUnit.DAYS.toMinutes(1L);
    }

    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            TimeZone timezone = TimeZone.getTimeZone(parcel.readString());
            boolean flag;
            if (parcel.readInt() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            return TimeRange.newInstanceUnsafe(timezone, flag, parcel.readLong(), parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readInt(), parcel.readInt());
        }

        public final Object[] newArray(int i)
        {
            return new TimeRange[i];
        }

        _cls1()
        {
        }
    }

}
