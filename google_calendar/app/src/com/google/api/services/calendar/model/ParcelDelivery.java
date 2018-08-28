// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, Organization, Time

public final class ParcelDelivery extends GenericJson
{

    public AssignedId assignedId;
    public Organization carrier;
    public String deliveryStatus;
    public Time expectedArrivalDate;
    public List itemShippeds;
    public Time purchaseDate;
    public Organization seller;
    public String totalPrice;
    public String trackingNumber;

    public ParcelDelivery()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ParcelDelivery)clone();
    }

    public final volatile GenericData clone()
    {
        return (ParcelDelivery)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ParcelDelivery)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ParcelDelivery)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ParcelDelivery)super.set(s, obj);
    }
}
