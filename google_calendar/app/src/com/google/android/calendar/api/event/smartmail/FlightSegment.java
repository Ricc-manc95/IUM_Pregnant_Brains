// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            FlightEndpoint, FlightPassenger, SmartMailImage

public final class FlightSegment
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String airlineCode;
    public final String airlineName;
    public final FlightEndpoint arrival;
    public final String bookingReference;
    public final FlightEndpoint departure;
    public final String flightNumber;
    public final SmartMailImage image;
    private final List passengers;
    public final int statusCode;

    public FlightSegment(int i, String s, String s1, String s2, String s3, List list, SmartMailImage smartmailimage, 
            FlightEndpoint flightendpoint, FlightEndpoint flightendpoint1)
    {
        statusCode = checkStatusCode(i);
        if (s == null)
        {
            throw new NullPointerException();
        }
        bookingReference = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        airlineName = (String)s1;
        if (s2 == null)
        {
            throw new NullPointerException();
        }
        airlineCode = (String)s2;
        if (s3 == null)
        {
            throw new NullPointerException();
        }
        flightNumber = (String)s3;
        if (list == null)
        {
            throw new NullPointerException();
        }
        passengers = (List)list;
        image = smartmailimage;
        if (flightendpoint == null)
        {
            throw new NullPointerException();
        }
        departure = (FlightEndpoint)flightendpoint;
        if (flightendpoint1 == null)
        {
            throw new NullPointerException();
        } else
        {
            arrival = (FlightEndpoint)flightendpoint1;
            return;
        }
    }

    FlightSegment(Parcel parcel)
    {
        int i = checkStatusCode(parcel.readInt());
        String s = parcel.readString();
        String s1 = parcel.readString();
        String s2 = parcel.readString();
        String s3 = parcel.readString();
        Object obj = parcel.createTypedArrayList(FlightPassenger.CREATOR);
        if (((List) (obj)).isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            obj = Collections.unmodifiableList(((List) (obj)));
        }
        this(i, s, s1, s2, s3, ((List) (obj)), (SmartMailImage)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailImage.getClassLoader()), (FlightEndpoint)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/FlightEndpoint.getClassLoader()), (FlightEndpoint)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/FlightEndpoint.getClassLoader()));
    }

    private static int checkStatusCode(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder(32)).append("Invalid status code ").append(i).append(".").toString());

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
            return i;
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
        if (obj == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (getClass() != obj.getClass())
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (FlightSegment)obj;
        flag = flag1;
        if (statusCode != ((FlightSegment) (obj)).statusCode)
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!bookingReference.equals(((FlightSegment) (obj)).bookingReference))
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!airlineName.equals(((FlightSegment) (obj)).airlineName))
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!airlineCode.equals(((FlightSegment) (obj)).airlineCode))
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!flightNumber.equals(((FlightSegment) (obj)).flightNumber))
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!passengers.equals(((FlightSegment) (obj)).passengers))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (image == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!image.equals(((FlightSegment) (obj)).image))
        {
            continue; /* Loop/switch isn't completed */
        }
_L5:
        flag = flag1;
        if (departure.equals(((FlightSegment) (obj)).departure))
        {
            return arrival.equals(((FlightSegment) (obj)).arrival);
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (((FlightSegment) (obj)).image != null)
        {
            return false;
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    public final int hashCode()
    {
        int j = statusCode;
        int k = bookingReference.hashCode();
        int l = airlineName.hashCode();
        int i1 = airlineCode.hashCode();
        int j1 = flightNumber.hashCode();
        int k1 = passengers.hashCode();
        int i;
        if (image != null)
        {
            i = image.hashCode();
        } else
        {
            i = 0;
        }
        return ((i + (((((j * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + k1) * 31) * 31 + departure.hashCode()) * 31 + arrival.hashCode();
    }

    public final String toString()
    {
        return String.format("FlightSegment{statusCode=%s, bookingReference='%s', airlineName='%s', airlineCode='%s', flightNumber='%s', passengers=%s, image=%s, departure=%s, arrival=%s}", new Object[] {
            Integer.valueOf(statusCode), bookingReference, airlineName, airlineCode, flightNumber, passengers, image, departure, arrival
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(statusCode);
        parcel.writeString(bookingReference);
        parcel.writeString(airlineName);
        parcel.writeString(airlineCode);
        parcel.writeString(flightNumber);
        parcel.writeTypedList(passengers);
        parcel.writeParcelable(image, i);
        parcel.writeParcelable(departure, i);
        parcel.writeParcelable(arrival, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FlightSegment(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FlightSegment[i];
        }

        _cls1()
        {
        }
    }

}
