// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import com.google.android.calendar.api.event.location.EventLocation;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            EventResult

final class AutoValue_EventResult extends EventResult
{

    private final EventLocation getLocation;
    private final EventResult.Timespan getTimespan;
    private final String getTitle;
    private final boolean wasConnectorUsed;
    private final boolean wasSuggestionReceived;

    AutoValue_EventResult(String s, EventLocation eventlocation, EventResult.Timespan timespan, boolean flag, boolean flag1)
    {
        if (s == null)
        {
            throw new NullPointerException("Null getTitle");
        } else
        {
            getTitle = s;
            getLocation = eventlocation;
            getTimespan = timespan;
            wasConnectorUsed = flag;
            wasSuggestionReceived = flag1;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof EventResult)
            {
                if (!getTitle.equals(((EventResult) (obj = (EventResult)obj)).getTitle()) || (getLocation != null ? !getLocation.equals(((EventResult) (obj)).getLocation()) : ((EventResult) (obj)).getLocation() != null) || (getTimespan != null ? !getTimespan.equals(((EventResult) (obj)).getTimespan()) : ((EventResult) (obj)).getTimespan() != null) || (wasConnectorUsed != ((EventResult) (obj)).wasConnectorUsed() || wasSuggestionReceived != ((EventResult) (obj)).wasSuggestionReceived()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final EventLocation getLocation()
    {
        return getLocation;
    }

    public final EventResult.Timespan getTimespan()
    {
        return getTimespan;
    }

    public final String getTitle()
    {
        return getTitle;
    }

    public final int hashCode()
    {
        char c1 = '\u04CF';
        int j = 0;
        int k = getTitle.hashCode();
        int i;
        char c;
        if (getLocation == null)
        {
            i = 0;
        } else
        {
            i = getLocation.hashCode();
        }
        if (getTimespan != null)
        {
            j = getTimespan.hashCode();
        }
        if (wasConnectorUsed)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (!wasSuggestionReceived)
        {
            c1 = '\u04D5';
        }
        return (c ^ ((i ^ (k ^ 0xf4243) * 0xf4243) * 0xf4243 ^ j) * 0xf4243) * 0xf4243 ^ c1;
    }

    public final String toString()
    {
        String s = getTitle;
        String s1 = String.valueOf(getLocation);
        String s2 = String.valueOf(getTimespan);
        boolean flag = wasConnectorUsed;
        boolean flag1 = wasSuggestionReceived;
        return (new StringBuilder(String.valueOf(s).length() + 103 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("EventResult{getTitle=").append(s).append(", getLocation=").append(s1).append(", getTimespan=").append(s2).append(", wasConnectorUsed=").append(flag).append(", wasSuggestionReceived=").append(flag1).append("}").toString();
    }

    public final boolean wasConnectorUsed()
    {
        return wasConnectorUsed;
    }

    public final boolean wasSuggestionReceived()
    {
        return wasSuggestionReceived;
    }
}
