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
    public Boolean colorRgbFormat;
    public Boolean supportsAllDayReminders;

    public final volatile AbstractGoogleClientRequest set(String s, Object obj)
    {
        return (Request)set(s, obj);
    }

    public final volatile AbstractGoogleJsonClientRequest set(String s, Object obj)
    {
        return (sonClientRequest)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (sonClientRequest)super.set(s, obj);
    }

    public final volatile CalendarRequest set(String s, Object obj)
    {
        return (sonClientRequest)set(s, obj);
    }

    public final CalendarRequest setFields(String s)
    {
        return (sonClientRequest)super.setFields(s);
    }

    public sonClientRequest(sonClientRequest sonclientrequest, String s, CalendarListEntry calendarlistentry)
    {
        super(sonclientrequest.sonClientRequest, "PUT", "users/me/calendarList/{calendarId}", calendarlistentry, com/google/api/services/calendar/model/CalendarListEntry);
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
