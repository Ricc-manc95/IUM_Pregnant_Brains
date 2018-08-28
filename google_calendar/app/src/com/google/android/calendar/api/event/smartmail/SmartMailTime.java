// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

public class SmartMailTime
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final boolean dateOnly;
    public final long timeMillis;
    public final int timeZoneOffsetMinutes;

    public SmartMailTime(long l, int i, boolean flag)
    {
        boolean flag1;
        if (l >= 0L)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf("timeMillis must be positive"));
        } else
        {
            timeMillis = l;
            timeZoneOffsetMinutes = i;
            dateOnly = flag;
            return;
        }
    }

    SmartMailTime(Parcel parcel)
    {
        this(parcel.readLong(), parcel.readInt(), ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue());
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (SmartMailTime)obj;
            if (timeMillis != ((SmartMailTime) (obj)).timeMillis)
            {
                return false;
            }
            if (timeZoneOffsetMinutes != ((SmartMailTime) (obj)).timeZoneOffsetMinutes)
            {
                return false;
            }
            if (dateOnly != ((SmartMailTime) (obj)).dateOnly)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int j = (int)(timeMillis ^ timeMillis >>> 32);
        int k = timeZoneOffsetMinutes;
        int i;
        if (dateOnly)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return i + (j * 31 + k) * 31;
    }

    public String toString()
    {
        return String.format("SmartMailTime{timeMillis=%d, timeZoneOffsetMinutes=%d, dateOnly=%s}", new Object[] {
            Long.valueOf(timeMillis), Integer.valueOf(timeZoneOffsetMinutes), Boolean.valueOf(dateOnly)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(timeMillis);
        parcel.writeInt(timeZoneOffsetMinutes);
        parcel.writeValue(Boolean.valueOf(dateOnly));
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SmartMailTime(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SmartMailTime[i];
        }

        _cls1()
        {
        }
    }

}
