// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.conference;

import java.util.Locale;

// Referenced classes of package com.google.android.calendar.event.conference:
//            PhoneNumberDetails, Availability, LocalPhoneSource

abstract class $AutoValue_PhoneNumberDetails extends PhoneNumberDetails
{

    private final Availability availability;
    private final String passCode;
    private final String phoneNumber;
    private final Locale region;
    private final LocalPhoneSource source;

    $AutoValue_PhoneNumberDetails(String s, String s1, Locale locale, Availability availability1, LocalPhoneSource localphonesource)
    {
        if (s == null)
        {
            throw new NullPointerException("Null phoneNumber");
        }
        phoneNumber = s;
        passCode = s1;
        if (locale == null)
        {
            throw new NullPointerException("Null region");
        }
        region = locale;
        if (availability1 == null)
        {
            throw new NullPointerException("Null availability");
        } else
        {
            availability = availability1;
            source = localphonesource;
            return;
        }
    }

    public final Availability availability()
    {
        return availability;
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof PhoneNumberDetails))
            {
                break MISSING_BLOCK_LABEL_124;
            }
            obj = (PhoneNumberDetails)obj;
            if (phoneNumber.equals(((PhoneNumberDetails) (obj)).phoneNumber()) && (passCode != null ? passCode.equals(((PhoneNumberDetails) (obj)).passCode()) : ((PhoneNumberDetails) (obj)).passCode() == null) && (region.equals(((PhoneNumberDetails) (obj)).region()) && availability.equals(((PhoneNumberDetails) (obj)).availability())))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (source != null) goto _L4; else goto _L3
_L3:
        if (((PhoneNumberDetails) (obj)).source() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!source.equals(((PhoneNumberDetails) (obj)).source())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public int hashCode()
    {
        int j = 0;
        int k = phoneNumber.hashCode();
        int i;
        int l;
        int i1;
        if (passCode == null)
        {
            i = 0;
        } else
        {
            i = passCode.hashCode();
        }
        l = region.hashCode();
        i1 = availability.hashCode();
        if (source != null)
        {
            j = source.hashCode();
        }
        return (((i ^ (k ^ 0xf4243) * 0xf4243) * 0xf4243 ^ l) * 0xf4243 ^ i1) * 0xf4243 ^ j;
    }

    public final String passCode()
    {
        return passCode;
    }

    public final String phoneNumber()
    {
        return phoneNumber;
    }

    public final Locale region()
    {
        return region;
    }

    public final LocalPhoneSource source()
    {
        return source;
    }

    public String toString()
    {
        String s = phoneNumber;
        String s1 = passCode;
        String s2 = String.valueOf(region);
        String s3 = String.valueOf(availability);
        String s4 = String.valueOf(source);
        return (new StringBuilder(String.valueOf(s).length() + 76 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length())).append("PhoneNumberDetails{phoneNumber=").append(s).append(", passCode=").append(s1).append(", region=").append(s2).append(", availability=").append(s3).append(", source=").append(s4).append("}").toString();
    }
}
