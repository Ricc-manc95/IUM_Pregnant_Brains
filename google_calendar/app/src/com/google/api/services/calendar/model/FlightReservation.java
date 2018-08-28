// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId

public final class FlightReservation extends GenericJson
{

    public AssignedId assignedId;
    public List flightSegments;

    public FlightReservation()
    {
    }

    public final volatile GenericJson clone()
    {
        return (FlightReservation)clone();
    }

    public final volatile GenericData clone()
    {
        return (FlightReservation)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (FlightReservation)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (FlightReservation)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (FlightReservation)super.set(s, obj);
    }
}
