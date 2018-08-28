// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class FindTimeTimeframe
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final Long date;
    public final long durationMillis;
    public final long endTimeMillis;
    public final long startTimeMillis;
    public final int timeframeType;

    public FindTimeTimeframe(int i, long l, Long long1, long l1, Long long2)
    {
        timeframeType = i;
        startTimeMillis = l;
        endTimeMillis = long1.longValue();
        durationMillis = l1;
        date = long2;
    }

    FindTimeTimeframe(Parcel parcel)
    {
        timeframeType = parcel.readInt();
        startTimeMillis = parcel.readLong();
        endTimeMillis = parcel.readLong();
        durationMillis = parcel.readLong();
        long l = parcel.readLong();
        if (l == -1L)
        {
            parcel = null;
        } else
        {
            parcel = Long.valueOf(l);
        }
        date = parcel;
    }

    public static FindTimeTimeframe other(int i, long l, long l1, long l2)
    {
        if (i == 4)
        {
            throw new IllegalArgumentException("Around a date timeframes must be built with other factory method.");
        } else
        {
            return new FindTimeTimeframe(i, l, Long.valueOf(l1), l2, null);
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof FindTimeTimeframe))
        {
            return false;
        }
        Object obj1 = (FindTimeTimeframe)obj;
        if (timeframeType != ((FindTimeTimeframe) (obj1)).timeframeType || startTimeMillis != ((FindTimeTimeframe) (obj1)).startTimeMillis || endTimeMillis != ((FindTimeTimeframe) (obj1)).endTimeMillis || durationMillis != ((FindTimeTimeframe) (obj1)).durationMillis)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = date;
        obj1 = ((FindTimeTimeframe) (obj1)).date;
        boolean flag;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(timeframeType), Long.valueOf(startTimeMillis), Long.valueOf(endTimeMillis), Long.valueOf(durationMillis), date
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(timeframeType);
        parcel.writeLong(startTimeMillis);
        parcel.writeLong(endTimeMillis);
        parcel.writeLong(durationMillis);
        long l;
        if (date == null)
        {
            l = -1L;
        } else
        {
            l = date.longValue();
        }
        parcel.writeLong(l);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FindTimeTimeframe(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FindTimeTimeframe[i];
        }

        _cls1()
        {
        }
    }

}
