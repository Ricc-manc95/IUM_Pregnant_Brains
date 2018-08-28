// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;

import android.os.Parcel;
import android.os.Parcelable;

public class GeoCoordinates
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final double latitude;
    public final double longitude;

    public GeoCoordinates(double d, double d1)
    {
        latitude = d;
        longitude = d1;
    }

    GeoCoordinates(Parcel parcel)
    {
        this(parcel.readDouble(), parcel.readDouble());
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof GeoCoordinates))
            {
                return false;
            }
            obj = (GeoCoordinates)obj;
            if (Double.compare(latitude, ((GeoCoordinates) (obj)).latitude) != 0 || Double.compare(longitude, ((GeoCoordinates) (obj)).longitude) != 0)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        long l = Double.doubleToLongBits(latitude);
        long l1 = Double.doubleToLongBits(longitude);
        return ((int)(l ^ l >>> 32) + 527) * 31 + (int)(l1 ^ l1 >>> 32);
    }

    public String toString()
    {
        return String.format("GeoCoordinates{latitude=%s, longitude=%s}", new Object[] {
            Double.valueOf(latitude), Double.valueOf(longitude)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new GeoCoordinates(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new GeoCoordinates[i];
        }

        _cls1()
        {
        }
    }

}
