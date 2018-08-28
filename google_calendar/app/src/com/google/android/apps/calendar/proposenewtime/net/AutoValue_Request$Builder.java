// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.net;

import com.google.common.collect.ImmutableList;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.net:
//            AutoValue_Request, Request

public final class  extends 
{

    private ImmutableList attendees;
    private String calendarId;
    private Long endTimeMillis;
    private String eventId;
    private Long startTimeMillis;
    private TimeZone timeZone;

    public final Request build()
    {
        String s1 = "";
        if (attendees == null)
        {
            s1 = String.valueOf("").concat(" attendees");
        }
        String s = s1;
        if (startTimeMillis == null)
        {
            s = String.valueOf(s1).concat(" startTimeMillis");
        }
        s1 = s;
        if (endTimeMillis == null)
        {
            s1 = String.valueOf(s).concat(" endTimeMillis");
        }
        s = s1;
        if (timeZone == null)
        {
            s = String.valueOf(s1).concat(" timeZone");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_Request(attendees, startTimeMillis.longValue(), endTimeMillis.longValue(), timeZone, eventId, calendarId);
        }
    }

    public final calendarId setAttendees(ImmutableList immutablelist)
    {
        if (immutablelist == null)
        {
            throw new NullPointerException("Null attendees");
        } else
        {
            attendees = immutablelist;
            return this;
        }
    }

    public final attendees setCalendarId(String s)
    {
        calendarId = s;
        return this;
    }

    public final calendarId setEndTimeMillis(long l)
    {
        endTimeMillis = Long.valueOf(l);
        return this;
    }

    public final endTimeMillis setEventId(String s)
    {
        eventId = s;
        return this;
    }

    public final eventId setStartTimeMillis(long l)
    {
        startTimeMillis = Long.valueOf(l);
        return this;
    }

    public final startTimeMillis setTimeZone(TimeZone timezone)
    {
        if (timezone == null)
        {
            throw new NullPointerException("Null timeZone");
        } else
        {
            timeZone = timezone;
            return this;
        }
    }

    public ()
    {
    }
}
