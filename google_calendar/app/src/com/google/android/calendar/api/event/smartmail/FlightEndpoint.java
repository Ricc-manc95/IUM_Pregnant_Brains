// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            SmartMailTime

public class FlightEndpoint
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final SmartMailTime actualTime;
    public final String airportCode;
    public final String city;
    public final String gate;
    public final String terminal;
    public final SmartMailTime time;

    FlightEndpoint(Parcel parcel)
    {
        this(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), (SmartMailTime)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailTime.getClassLoader()), (SmartMailTime)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailTime.getClassLoader()));
    }

    public FlightEndpoint(String s, String s1, String s2, String s3, SmartMailTime smartmailtime, SmartMailTime smartmailtime1)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        airportCode = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        city = (String)s1;
        if (s2 == null)
        {
            throw new NullPointerException();
        }
        terminal = (String)s2;
        if (s3 == null)
        {
            throw new NullPointerException();
        }
        gate = (String)s3;
        if (smartmailtime == null)
        {
            throw new NullPointerException();
        } else
        {
            time = (SmartMailTime)smartmailtime;
            actualTime = smartmailtime1;
            return;
        }
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
            obj = (FlightEndpoint)obj;
            if (!airportCode.equals(((FlightEndpoint) (obj)).airportCode))
            {
                return false;
            }
            if (!city.equals(((FlightEndpoint) (obj)).city))
            {
                return false;
            }
            if (!terminal.equals(((FlightEndpoint) (obj)).terminal))
            {
                return false;
            }
            if (!gate.equals(((FlightEndpoint) (obj)).gate))
            {
                return false;
            }
            if (!time.equals(((FlightEndpoint) (obj)).time))
            {
                return false;
            }
            if (actualTime != null)
            {
                return actualTime.equals(((FlightEndpoint) (obj)).actualTime);
            }
            if (((FlightEndpoint) (obj)).actualTime != null)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int j = airportCode.hashCode();
        int k = city.hashCode();
        int l = terminal.hashCode();
        int i1 = gate.hashCode();
        int j1 = time.hashCode();
        int i;
        if (actualTime != null)
        {
            i = actualTime.hashCode();
        } else
        {
            i = 0;
        }
        return i + ((((j * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31;
    }

    public String toString()
    {
        return String.format("Endpoint{airportCode='%s', city='%s', terminal='%s', gate='%s', time=%s, actualTime=%s}", new Object[] {
            airportCode, city, terminal, gate, time, actualTime
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(airportCode);
        parcel.writeString(city);
        parcel.writeString(terminal);
        parcel.writeString(gate);
        parcel.writeParcelable(time, i);
        parcel.writeParcelable(actualTime, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FlightEndpoint(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FlightEndpoint[i];
        }

        _cls1()
        {
        }
    }

}
