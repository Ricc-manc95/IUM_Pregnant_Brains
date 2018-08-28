// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            SmartMailAddress, AssignedId, Time, Organization

public final class Offer extends GenericJson
{

    public SmartMailAddress address;
    public AssignedId assignedId;
    public Time endTime;
    public String highPrice;
    public String lowPrice;
    public Integer offerCount;
    public String phoneNumber;
    public String price;
    public Organization seller;
    public Time startTime;
    public String summary;

    public Offer()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Offer)clone();
    }

    public final volatile GenericData clone()
    {
        return (Offer)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Offer)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Offer)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Offer)super.set(s, obj);
    }
}
