// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class EventReservationSeatingInformation extends GenericJson
{

    public String row;
    public String seat;
    public String section;

    public EventReservationSeatingInformation()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EventReservationSeatingInformation)clone();
    }

    public final volatile GenericData clone()
    {
        return (EventReservationSeatingInformation)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EventReservationSeatingInformation)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EventReservationSeatingInformation)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EventReservationSeatingInformation)super.set(s, obj);
    }
}
