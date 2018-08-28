// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, Organization, Event2

public final class EventReservation extends GenericJson
{

    public AssignedId assignedId;
    public Organization bookingAgent;
    public Event2 event;
    public String reservationNumber;
    public String reservationStatus;
    public Integer seatCount;
    public List seatingInformations;

    public EventReservation()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EventReservation)clone();
    }

    public final volatile GenericData clone()
    {
        return (EventReservation)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EventReservation)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EventReservation)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EventReservation)super.set(s, obj);
    }
}
