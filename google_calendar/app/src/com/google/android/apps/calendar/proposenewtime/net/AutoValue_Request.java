// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.net;

import com.google.common.collect.ImmutableList;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.net:
//            Request

final class AutoValue_Request extends Request
{

    private final ImmutableList attendees;
    private final String calendarId;
    private final long endTimeMillis;
    private final String eventId;
    private final long startTimeMillis;
    private final TimeZone timeZone;

    AutoValue_Request(ImmutableList immutablelist, long l, long l1, TimeZone timezone, String s, 
            String s1)
    {
        attendees = immutablelist;
        startTimeMillis = l;
        endTimeMillis = l1;
        timeZone = timezone;
        eventId = s;
        calendarId = s1;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof Request))
            {
                break MISSING_BLOCK_LABEL_134;
            }
            obj = (Request)obj;
            if (attendees.equals(((Request) (obj)).getAttendees()) && startTimeMillis == ((Request) (obj)).getStartTimeMillis() && endTimeMillis == ((Request) (obj)).getEndTimeMillis() && timeZone.equals(((Request) (obj)).getTimeZone()) && (eventId != null ? eventId.equals(((Request) (obj)).getEventId()) : ((Request) (obj)).getEventId() == null))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (calendarId != null) goto _L4; else goto _L3
_L3:
        if (((Request) (obj)).getCalendarId() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!calendarId.equals(((Request) (obj)).getCalendarId())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final ImmutableList getAttendees()
    {
        return attendees;
    }

    public final String getCalendarId()
    {
        return calendarId;
    }

    public final long getEndTimeMillis()
    {
        return endTimeMillis;
    }

    public final String getEventId()
    {
        return eventId;
    }

    public final long getStartTimeMillis()
    {
        return startTimeMillis;
    }

    public final TimeZone getTimeZone()
    {
        return timeZone;
    }

    public final int hashCode()
    {
        int j = 0;
        int k = attendees.hashCode();
        int l = (int)(startTimeMillis >>> 32 ^ startTimeMillis);
        int i1 = (int)(endTimeMillis >>> 32 ^ endTimeMillis);
        int j1 = timeZone.hashCode();
        int i;
        if (eventId == null)
        {
            i = 0;
        } else
        {
            i = eventId.hashCode();
        }
        if (calendarId != null)
        {
            j = calendarId.hashCode();
        }
        return (i ^ ((((k ^ 0xf4243) * 0xf4243 ^ l) * 0xf4243 ^ i1) * 0xf4243 ^ j1) * 0xf4243) * 0xf4243 ^ j;
    }

    public final String toString()
    {
        String s = String.valueOf(attendees);
        long l = startTimeMillis;
        long l1 = endTimeMillis;
        String s1 = String.valueOf(timeZone);
        String s2 = eventId;
        String s3 = calendarId;
        return (new StringBuilder(String.valueOf(s).length() + 127 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length())).append("Request{attendees=").append(s).append(", startTimeMillis=").append(l).append(", endTimeMillis=").append(l1).append(", timeZone=").append(s1).append(", eventId=").append(s2).append(", calendarId=").append(s3).append("}").toString();
    }
}
