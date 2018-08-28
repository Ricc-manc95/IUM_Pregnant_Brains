// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class Address extends GenericJson
{

    public String country;
    public String formattedAddress;
    public String locality;
    public String postOfficeBoxNumber;
    public String postalCode;
    public String region;
    public String streetAddress;

    public Address()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Address)clone();
    }

    public final volatile GenericData clone()
    {
        return (Address)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Address)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Address)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Address)super.set(s, obj);
    }
}
