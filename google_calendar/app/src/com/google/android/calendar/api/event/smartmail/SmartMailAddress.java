// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            SmartMailActionTarget

public class SmartMailAddress
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String latitude;
    public final String locality;
    public final String longitude;
    public final SmartMailActionTarget mapLink;
    public final String name;
    public final String postalCode;
    public final String region;
    public final String streetAddress;

    SmartMailAddress(Parcel parcel)
    {
        this(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), (SmartMailActionTarget)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailActionTarget.getClassLoader()), parcel.readString(), parcel.readString());
    }

    public SmartMailAddress(String s, String s1, String s2, String s3, String s4, SmartMailActionTarget smartmailactiontarget, String s5, 
            String s6)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        name = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        streetAddress = (String)s1;
        if (s2 == null)
        {
            throw new NullPointerException();
        }
        locality = (String)s2;
        if (s3 == null)
        {
            throw new NullPointerException();
        }
        region = (String)s3;
        if (s4 == null)
        {
            throw new NullPointerException();
        }
        postalCode = (String)s4;
        mapLink = smartmailactiontarget;
        if (s5 == null)
        {
            throw new NullPointerException();
        }
        latitude = (String)s5;
        if (s6 == null)
        {
            throw new NullPointerException();
        } else
        {
            longitude = (String)s6;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = false;
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag1;
        if (obj == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (getClass() != obj.getClass())
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (SmartMailAddress)obj;
        flag = flag1;
        if (!name.equals(((SmartMailAddress) (obj)).name))
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!streetAddress.equals(((SmartMailAddress) (obj)).streetAddress))
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!locality.equals(((SmartMailAddress) (obj)).locality))
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!region.equals(((SmartMailAddress) (obj)).region))
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!postalCode.equals(((SmartMailAddress) (obj)).postalCode))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mapLink == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!mapLink.equals(((SmartMailAddress) (obj)).mapLink))
        {
            continue; /* Loop/switch isn't completed */
        }
_L5:
        flag = flag1;
        if (latitude.equals(((SmartMailAddress) (obj)).latitude))
        {
            return longitude.equals(((SmartMailAddress) (obj)).longitude);
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (((SmartMailAddress) (obj)).mapLink != null)
        {
            return false;
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    public int hashCode()
    {
        int j = name.hashCode();
        int k = streetAddress.hashCode();
        int l = locality.hashCode();
        int i1 = region.hashCode();
        int j1 = postalCode.hashCode();
        int i;
        if (mapLink != null)
        {
            i = mapLink.hashCode();
        } else
        {
            i = 0;
        }
        return ((i + ((((j * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31) * 31 + latitude.hashCode()) * 31 + longitude.hashCode();
    }

    public String toString()
    {
        return String.format("SmartMailAddress{name='%s', streetAddress='%s', locality='%s', region='%s', postalCode='%s', mapLink=%s, latitude='%s', longitude='%s'}", new Object[] {
            name, streetAddress, locality, region, postalCode, mapLink, latitude, longitude
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeString(streetAddress);
        parcel.writeString(locality);
        parcel.writeString(region);
        parcel.writeString(postalCode);
        parcel.writeParcelable(mapLink, i);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SmartMailAddress(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SmartMailAddress[i];
        }

        _cls1()
        {
        }
    }

}
