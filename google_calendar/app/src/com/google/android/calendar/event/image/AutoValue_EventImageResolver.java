// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.EventKey;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImageResolver

public final class AutoValue_EventImageResolver extends EventImageResolver
{

    private final CalendarKey calendarId;
    private final EventKey id;
    private final String location;
    private final String title;

    public AutoValue_EventImageResolver(String s, EventKey eventkey, CalendarKey calendarkey, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException("Null title");
        }
        title = s;
        if (eventkey == null)
        {
            throw new NullPointerException("Null id");
        }
        id = eventkey;
        if (calendarkey == null)
        {
            throw new NullPointerException("Null calendarId");
        }
        calendarId = calendarkey;
        if (s1 == null)
        {
            throw new NullPointerException("Null location");
        } else
        {
            location = s1;
            return;
        }
    }

    final CalendarKey calendarId()
    {
        return calendarId;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof EventImageResolver)
            {
                if (!title.equals(((EventImageResolver) (obj = (EventImageResolver)obj)).title()) || !id.equals(((EventImageResolver) (obj)).id()) || !calendarId.equals(((EventImageResolver) (obj)).calendarId()) || !location.equals(((EventImageResolver) (obj)).location()))
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

    public final int hashCode()
    {
        return (((title.hashCode() ^ 0xf4243) * 0xf4243 ^ id.hashCode()) * 0xf4243 ^ calendarId.hashCode()) * 0xf4243 ^ location.hashCode();
    }

    final EventKey id()
    {
        return id;
    }

    final String location()
    {
        return location;
    }

    final String title()
    {
        return title;
    }

    public final String toString()
    {
        String s = title;
        String s1 = String.valueOf(id);
        String s2 = String.valueOf(calendarId);
        String s3 = location;
        return (new StringBuilder(String.valueOf(s).length() + 55 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length())).append("EventImageResolver{title=").append(s).append(", id=").append(s1).append(", calendarId=").append(s2).append(", location=").append(s3).append("}").toString();
    }
}
