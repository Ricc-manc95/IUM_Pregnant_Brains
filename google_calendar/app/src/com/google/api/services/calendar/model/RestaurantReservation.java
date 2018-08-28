// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, Organization, Restaurant, Person, 
//            Time

public final class RestaurantReservation extends GenericJson
{

    public AssignedId assignedId;
    public Organization bookingAgent;
    public Restaurant foodEstablishment;
    public Integer partySize;
    public String preferredLanguage;
    public Person reservationFor;
    public String reservationNumber;
    public String reservationStatus;
    public String reservationUrl;
    public Time startTime;

    public RestaurantReservation()
    {
    }

    public final volatile GenericJson clone()
    {
        return (RestaurantReservation)clone();
    }

    public final volatile GenericData clone()
    {
        return (RestaurantReservation)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (RestaurantReservation)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (RestaurantReservation)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (RestaurantReservation)super.set(s, obj);
    }
}
