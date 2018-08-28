// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            Person

public final class FlightReservationFlightSegmentFlightPassengerInfo extends GenericJson
{

    public String boardingGroup;
    public String boardingTime;
    public Person passenger;
    public String seatNumber;
    public String sequenceNumber;
    public String ticketNumber;

    public FlightReservationFlightSegmentFlightPassengerInfo()
    {
    }

    public final volatile GenericJson clone()
    {
        return (FlightReservationFlightSegmentFlightPassengerInfo)clone();
    }

    public final volatile GenericData clone()
    {
        return (FlightReservationFlightSegmentFlightPassengerInfo)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (FlightReservationFlightSegmentFlightPassengerInfo)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (FlightReservationFlightSegmentFlightPassengerInfo)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (FlightReservationFlightSegmentFlightPassengerInfo)super.set(s, obj);
    }
}
