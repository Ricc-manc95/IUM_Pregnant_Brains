// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar;

import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.GenericData;
import com.google.api.services.calendar.model.Event;

// Referenced classes of package com.google.api.services.calendar:
//            CalendarRequest

public final class quest.checkRequiredParameter extends CalendarRequest
{

    public String calendarId;
    public Integer conferenceDataVersion;
    public Boolean expandGroupAttendees;
    public Integer maxImageDimension;
    public Integer proposeTimeChangeVersion;
    public Boolean showRanges;
    public Boolean supportsAllDayReminders;
    public Boolean supportsAttachments;
    public Boolean supportsConferenceData;

    public final volatile AbstractGoogleClientRequest set(String s, Object obj)
    {
        return (quest)set(s, obj);
    }

    public final volatile AbstractGoogleJsonClientRequest set(String s, Object obj)
    {
        return (nClientRequest)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (nClientRequest)super.set(s, obj);
    }

    public final volatile CalendarRequest set(String s, Object obj)
    {
        return (nClientRequest)set(s, obj);
    }

    public final CalendarRequest setFields(String s)
    {
        return (nClientRequest)super.setFields(s);
    }

    public nClientRequest(nClientRequest nclientrequest, String s, Event event)
    {
        super(nclientrequest.nClientRequest, "POST", "calendars/{calendarId}/events/import", event, com/google/api/services/calendar/model/Event);
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("Required parameter calendarId must be specified."));
        } else
        {
            calendarId = (String)s;
            checkRequiredParameter(event, "content");
            checkRequiredParameter(event.iCalUID, "Event.getICalUID()");
            return;
        }
    }
}
