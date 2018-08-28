// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            Address, GeoCoordinates

public final class EventLocation extends GenericJson
{

    public Address address;
    public GeoCoordinates geo;
    public String mapsClusterId;
    public String name;
    public String placeId;
    public Boolean serverGeocoded;
    public String url;

    public EventLocation()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EventLocation)clone();
    }

    public final volatile GenericData clone()
    {
        return (EventLocation)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EventLocation)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EventLocation)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EventLocation)super.set(s, obj);
    }
}
