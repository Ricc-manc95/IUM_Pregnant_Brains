// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.tracking;

import android.os.Parcel;
import android.os.Parcelable;

public final class TrackingIntervalData
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public int completedInstances;
    public long startMillis;

    public TrackingIntervalData(long l, int i)
    {
        startMillis = l;
        completedInstances = i;
    }

    TrackingIntervalData(Parcel parcel)
    {
        startMillis = parcel.readLong();
        completedInstances = parcel.readInt();
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(startMillis);
        parcel.writeInt(completedInstances);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TrackingIntervalData(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TrackingIntervalData[i];
        }

        _cls1()
        {
        }
    }

}
