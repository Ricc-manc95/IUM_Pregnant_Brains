// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            Person

public final class FlightPassenger
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final Person passenger;
    private final String seatNumber;

    FlightPassenger(Parcel parcel)
    {
        passenger = (Person)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/Person.getClassLoader());
        seatNumber = parcel.readString();
    }

    public FlightPassenger(Person person, String s)
    {
        passenger = person;
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            seatNumber = (String)s;
            return;
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag1;
        if (obj == null) goto _L4; else goto _L3
_L3:
        flag = flag1;
        if (getClass() != obj.getClass()) goto _L4; else goto _L5
_L5:
        obj = (FlightPassenger)obj;
        if (passenger == null) goto _L7; else goto _L6
_L6:
        flag = flag1;
        if (!passenger.equals(((FlightPassenger) (obj)).passenger)) goto _L4; else goto _L8
_L8:
        return seatNumber.equals(((FlightPassenger) (obj)).seatNumber);
_L7:
        if (((FlightPassenger) (obj)).passenger != null)
        {
            return false;
        }
        if (true) goto _L8; else goto _L9
_L9:
    }

    public final int hashCode()
    {
        int i;
        if (passenger != null)
        {
            i = passenger.hashCode();
        } else
        {
            i = 0;
        }
        return i * 31 + seatNumber.hashCode();
    }

    public final String toString()
    {
        return String.format("FlightPassengerInfo{passenger=%s, seatNumber='%s'}", new Object[] {
            passenger, seatNumber
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(passenger, i);
        parcel.writeString(seatNumber);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FlightPassenger(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FlightPassenger[i];
        }

        _cls1()
        {
        }
    }

}
