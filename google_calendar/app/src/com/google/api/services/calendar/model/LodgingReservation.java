// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, Time, Image, Organization

public final class LodgingReservation extends GenericJson
{

    public AssignedId assignedId;
    public Time checkinDate;
    public Time checkoutDate;
    public Image image;
    public Organization lodging;
    public String reservationNumber;
    public String reservationStatus;
    public String type;

    public LodgingReservation()
    {
    }

    public final volatile GenericJson clone()
    {
        return (LodgingReservation)clone();
    }

    public final volatile GenericData clone()
    {
        return (LodgingReservation)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (LodgingReservation)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (LodgingReservation)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (LodgingReservation)super.set(s, obj);
    }
}
