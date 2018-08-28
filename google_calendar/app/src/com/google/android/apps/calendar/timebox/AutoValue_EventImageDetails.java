// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.smartmail.SmartMailAddress;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            EventImageDetails

final class AutoValue_EventImageDetails extends EventImageDetails
{

    private final EventLocation eventLocation;
    private final String googlePlusImageUrl;
    private final SmartMailAddress smartMailAddress;
    private final String smartMailImageUrl;
    private final EventImageDetails.SmartMailType type;

    AutoValue_EventImageDetails(String s, String s1, SmartMailAddress smartmailaddress, EventLocation eventlocation, EventImageDetails.SmartMailType smartmailtype)
    {
        googlePlusImageUrl = s;
        smartMailImageUrl = s1;
        smartMailAddress = smartmailaddress;
        eventLocation = eventlocation;
        type = smartmailtype;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof EventImageDetails))
            {
                break MISSING_BLOCK_LABEL_175;
            }
            obj = (EventImageDetails)obj;
            if ((googlePlusImageUrl != null ? googlePlusImageUrl.equals(((EventImageDetails) (obj)).getGooglePlusImageUrl()) : ((EventImageDetails) (obj)).getGooglePlusImageUrl() == null) && (smartMailImageUrl != null ? smartMailImageUrl.equals(((EventImageDetails) (obj)).getSmartMailImageUrl()) : ((EventImageDetails) (obj)).getSmartMailImageUrl() == null) && (smartMailAddress != null ? smartMailAddress.equals(((EventImageDetails) (obj)).getSmartMailAddress()) : ((EventImageDetails) (obj)).getSmartMailAddress() == null) && (eventLocation != null ? eventLocation.equals(((EventImageDetails) (obj)).getEventLocation()) : ((EventImageDetails) (obj)).getEventLocation() == null))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (type != null) goto _L4; else goto _L3
_L3:
        if (((EventImageDetails) (obj)).getType() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!type.equals(((EventImageDetails) (obj)).getType())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final EventLocation getEventLocation()
    {
        return eventLocation;
    }

    public final String getGooglePlusImageUrl()
    {
        return googlePlusImageUrl;
    }

    public final SmartMailAddress getSmartMailAddress()
    {
        return smartMailAddress;
    }

    public final String getSmartMailImageUrl()
    {
        return smartMailImageUrl;
    }

    public final EventImageDetails.SmartMailType getType()
    {
        return type;
    }

    public final int hashCode()
    {
        int i1 = 0;
        int i;
        int j;
        int k;
        int l;
        if (googlePlusImageUrl == null)
        {
            i = 0;
        } else
        {
            i = googlePlusImageUrl.hashCode();
        }
        if (smartMailImageUrl == null)
        {
            j = 0;
        } else
        {
            j = smartMailImageUrl.hashCode();
        }
        if (smartMailAddress == null)
        {
            k = 0;
        } else
        {
            k = smartMailAddress.hashCode();
        }
        if (eventLocation == null)
        {
            l = 0;
        } else
        {
            l = eventLocation.hashCode();
        }
        if (type != null)
        {
            i1 = type.hashCode();
        }
        return (l ^ (k ^ (j ^ (i ^ 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ i1;
    }

    public final String toString()
    {
        String s = googlePlusImageUrl;
        String s1 = smartMailImageUrl;
        String s2 = String.valueOf(smartMailAddress);
        String s3 = String.valueOf(eventLocation);
        String s4 = String.valueOf(type);
        return (new StringBuilder(String.valueOf(s).length() + 100 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length())).append("EventImageDetails{googlePlusImageUrl=").append(s).append(", smartMailImageUrl=").append(s1).append(", smartMailAddress=").append(s2).append(", eventLocation=").append(s3).append(", type=").append(s4).append("}").toString();
    }
}
