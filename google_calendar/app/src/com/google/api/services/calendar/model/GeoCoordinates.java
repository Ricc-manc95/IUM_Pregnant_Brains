// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class GeoCoordinates extends GenericJson
{

    public Double latitude;
    public Double longitude;

    public GeoCoordinates()
    {
    }

    public final volatile GenericJson clone()
    {
        return (GeoCoordinates)clone();
    }

    public final volatile GenericData clone()
    {
        return (GeoCoordinates)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (GeoCoordinates)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (GeoCoordinates)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (GeoCoordinates)super.set(s, obj);
    }
}