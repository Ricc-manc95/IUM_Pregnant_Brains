// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            Time, AssignedId, Image

public final class FlightReservationFlightSegment extends GenericJson
{

    public Time actualArrivalTime;
    public Time actualDepartureTime;
    public String airlineCode;
    public String airlineName;
    public String arrivalAirportCode;
    public String arrivalCity;
    public String arrivalGate;
    public String arrivalTerminal;
    public Time arrivalTime;
    public AssignedId assignedId;
    public String bookingReference;
    public String departureAirportCode;
    public String departureCity;
    public String departureGate;
    public String departureTerminal;
    public Time departureTime;
    public String divertedArrivalAirportCode;
    public String divertedArrivalCity;
    public String flightNumber;
    public String flightStatusMessage;
    public Image image;
    public Time lastUpdated;
    public List passengerInfos;
    public String statusCode;
    public String ticketToken;

    public FlightReservationFlightSegment()
    {
    }

    public final volatile GenericJson clone()
    {
        return (FlightReservationFlightSegment)clone();
    }

    public final volatile GenericData clone()
    {
        return (FlightReservationFlightSegment)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (FlightReservationFlightSegment)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (FlightReservationFlightSegment)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (FlightReservationFlightSegment)super.set(s, obj);
    }
}
