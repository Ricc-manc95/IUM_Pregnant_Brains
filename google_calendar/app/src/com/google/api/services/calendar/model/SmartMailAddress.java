// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            FormattedText, CalendarGoTo

public final class SmartMailAddress extends GenericJson
{

    public String country;
    public FormattedText formattedAddress;
    public CalendarGoTo googleMapLink;
    public String latitude;
    public String locality;
    public String longitude;
    public String mapImageUrl;
    public String name;
    public String postalCode;
    public String rawAddress;
    public String region;
    public String streetAddress;
    public String streetName;
    public String streetNumber;

    public SmartMailAddress()
    {
    }

    public final volatile GenericJson clone()
    {
        return (SmartMailAddress)clone();
    }

    public final volatile GenericData clone()
    {
        return (SmartMailAddress)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (SmartMailAddress)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (SmartMailAddress)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (SmartMailAddress)super.set(s, obj);
    }
}
