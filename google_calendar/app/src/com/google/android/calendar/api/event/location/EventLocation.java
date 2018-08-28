// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.api.event.location:
//            Address, GeoCoordinates

public final class EventLocation
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final Address address;
    public final GeoCoordinates geo;
    public final String mapsClusterId;
    public final String name;
    public final String placeId;
    public final boolean serverGeocoded;
    public final String url;

    EventLocation(Parcel parcel)
    {
        Builder builder = new Builder();
        String s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        }
        builder.mapsClusterId = (String)s;
        s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        }
        builder.placeId = (String)s;
        s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        }
        builder.name = (String)s;
        builder.address = (Address)parcel.readParcelable(com/google/android/calendar/api/event/location/Address.getClassLoader());
        builder.geo = (GeoCoordinates)parcel.readParcelable(com/google/android/calendar/api/event/location/GeoCoordinates.getClassLoader());
        s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            builder.url = (String)s;
            builder.serverGeocoded = ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue();
            this(builder);
            return;
        }
    }

    public EventLocation(Builder builder)
    {
        mapsClusterId = builder.mapsClusterId;
        placeId = builder.placeId;
        name = builder.name;
        address = builder.address;
        geo = builder.geo;
        url = builder.url;
        serverGeocoded = builder.serverGeocoded;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof EventLocation))
        {
            return false;
        }
        obj = (EventLocation)obj;
        if (!mapsClusterId.equals(((EventLocation) (obj)).mapsClusterId) || !placeId.equals(((EventLocation) (obj)).placeId) || !name.equals(((EventLocation) (obj)).name))
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = address;
        Object obj2 = ((EventLocation) (obj)).address;
        boolean flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = geo;
        obj2 = ((EventLocation) (obj)).geo;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && url.equals(((EventLocation) (obj)).url) && serverGeocoded == ((EventLocation) (obj)).serverGeocoded) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            mapsClusterId, placeId, name, address, geo, url, Boolean.valueOf(serverGeocoded)
        });
    }

    public final String toString()
    {
        return String.format("EventLocation{maps_cluster_id=%s, place_id=%s, name=%s, address=%s,geo_coordinates=%s, url=%s, server_geocoded=%s}", new Object[] {
            mapsClusterId, placeId, name, address, geo, url, Boolean.valueOf(serverGeocoded)
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mapsClusterId);
        parcel.writeString(placeId);
        parcel.writeString(name);
        parcel.writeParcelable(address, i);
        parcel.writeParcelable(geo, i);
        parcel.writeString(url);
        parcel.writeValue(Boolean.valueOf(serverGeocoded));
    }


    private class Builder
    {

        public Address address;
        public GeoCoordinates geo;
        public String mapsClusterId;
        public String name;
        public String placeId;
        public boolean serverGeocoded;
        public String url;

        public Builder()
        {
            mapsClusterId = "";
            placeId = "";
            name = "";
            url = "";
            serverGeocoded = false;
        }

        public Builder(EventLocation eventlocation)
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


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EventLocation(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new EventLocation[i];
        }

        _cls1()
        {
        }
    }

}
