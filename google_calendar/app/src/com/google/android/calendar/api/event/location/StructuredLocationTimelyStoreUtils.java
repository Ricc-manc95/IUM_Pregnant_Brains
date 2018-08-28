// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;

import com.google.api.services.calendar.model.Address;
import com.google.api.services.calendar.model.EventLocation;
import com.google.api.services.calendar.model.GeoCoordinates;
import com.google.common.base.Platform;

// Referenced classes of package com.google.android.calendar.api.event.location:
//            EventLocation, Address, GeoCoordinates

public final class StructuredLocationTimelyStoreUtils
{

    public static com.google.android.calendar.api.event.location.EventLocation toApiEventLocation(EventLocation eventlocation)
    {
        Object obj1 = null;
        EventLocation.Builder builder = new EventLocation.Builder();
        Object obj = Platform.nullToEmpty(eventlocation.url);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        builder.url = (String)obj;
        obj = Platform.nullToEmpty(eventlocation.placeId);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        builder.placeId = (String)obj;
        obj = Platform.nullToEmpty(eventlocation.mapsClusterId);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        builder.mapsClusterId = (String)obj;
        obj = Platform.nullToEmpty(eventlocation.name);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        builder.name = (String)obj;
        Object obj2 = eventlocation.address;
        boolean flag;
        if (obj2 == null)
        {
            obj = null;
        } else
        {
            obj = new Address.Builder();
            String s = Platform.nullToEmpty(((Address) (obj2)).formattedAddress);
            if (s == null)
            {
                throw new NullPointerException();
            }
            obj.formattedAddress = (String)s;
            s = Platform.nullToEmpty(((Address) (obj2)).country);
            if (s == null)
            {
                throw new NullPointerException();
            }
            obj.country = (String)s;
            s = Platform.nullToEmpty(((Address) (obj2)).locality);
            if (s == null)
            {
                throw new NullPointerException();
            }
            obj.locality = (String)s;
            s = Platform.nullToEmpty(((Address) (obj2)).postalCode);
            if (s == null)
            {
                throw new NullPointerException();
            }
            obj.postalCode = (String)s;
            s = Platform.nullToEmpty(((Address) (obj2)).postOfficeBoxNumber);
            if (s == null)
            {
                throw new NullPointerException();
            }
            obj.postOfficeBoxNumber = (String)s;
            s = Platform.nullToEmpty(((Address) (obj2)).region);
            if (s == null)
            {
                throw new NullPointerException();
            }
            obj.region = (String)s;
            obj2 = Platform.nullToEmpty(((Address) (obj2)).streetAddress);
            if (obj2 == null)
            {
                throw new NullPointerException();
            }
            obj.streetAddress = (String)obj2;
            obj = new com.google.android.calendar.api.event.location.Address(((Address.Builder) (obj)));
        }
        builder.address = ((com.google.android.calendar.api.event.location.Address) (obj));
        obj = eventlocation.geo;
        if (obj == null)
        {
            obj = obj1;
        } else
        {
            obj = new com.google.android.calendar.api.event.location.GeoCoordinates(((GeoCoordinates) (obj)).latitude.doubleValue(), ((GeoCoordinates) (obj)).longitude.doubleValue());
        }
        builder.geo = ((com.google.android.calendar.api.event.location.GeoCoordinates) (obj));
        eventlocation = eventlocation.serverGeocoded;
        if (eventlocation == null)
        {
            flag = false;
        } else
        {
            flag = eventlocation.booleanValue();
        }
        builder.serverGeocoded = flag;
        return new com.google.android.calendar.api.event.location.EventLocation(builder);
    }
}
