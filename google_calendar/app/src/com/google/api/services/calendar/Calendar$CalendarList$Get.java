// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar;

import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.GenericData;
import com.google.api.services.calendar.model.CalendarListEntry;

// Referenced classes of package com.google.api.services.calendar:
//            CalendarRequest

public final class calendarId extends CalendarRequest
{

    public String calendarId;
    public Boolean supportsAllDayReminders;

    public final volatile AbstractGoogleClientRequest set(String s, Object obj)
    {
        return (entRequest)set(s, obj);
    }

    public final volatile AbstractGoogleJsonClientRequest set(String s, Object obj)
    {
        return (leJsonClientRequest)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (leJsonClientRequest)super.set(s, obj);
    }

    public final volatile CalendarRequest set(String s, Object obj)
    {
        return (leJsonClientRequest)set(s, obj);
    }

    public final CalendarRequest setFields(String s)
    {
        return (leJsonClientRequest)super.setFields(s);
    }

    public leJsonClientRequest(leJsonClientRequest lejsonclientrequest, String s)
    {
        super(lejsonclientrequest._fld0, "GET", "users/me/calendarList/{calendarId}", null, com/google/api/services/calendar/model/CalendarListEntry);
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("Required parameter calendarId must be specified."));
        } else
        {
            calendarId = (String)s;
            return;
        }
    }
}
