// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, Time, Organization

public final class Order extends GenericJson
{

    public AssignedId assignedId;
    public Time expectedArrivalDate;
    public List products;
    public Time purchaseDate;
    public Organization seller;
    public String totalPrice;

    public Order()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Order)clone();
    }

    public final volatile GenericData clone()
    {
        return (Order)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Order)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Order)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Order)super.set(s, obj);
    }
}
