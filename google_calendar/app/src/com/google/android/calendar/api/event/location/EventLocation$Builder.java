// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;


// Referenced classes of package com.google.android.calendar.api.event.location:
//            EventLocation, Address, GeoCoordinates

public final class ocoded
{

    public Address address;
    public GeoCoordinates geo;
    public String mapsClusterId;
    public String name;
    public String placeId;
    public boolean serverGeocoded;
    public String url;

    public ()
    {
        mapsClusterId = "";
        placeId = "";
        name = "";
        url = "";
        serverGeocoded = false;
    }

    public serverGeocoded(EventLocation eventlocation)
    {
        mapsClusterId = "";
        placeId = "";
        name = "";
        url = "";
        serverGeocoded = false;
        mapsClusterId = eventlocation.mapsClusterId;
        placeId = eventlocation.placeId;
        name = eventlocation.name;
        address = eventlocation.address;
        geo = eventlocation.geo;
        url = eventlocation.url;
        serverGeocoded = eventlocation.serverGeocoded;
    }
}
