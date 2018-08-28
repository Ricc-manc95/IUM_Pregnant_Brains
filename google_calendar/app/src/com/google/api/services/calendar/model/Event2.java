// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            SmartMailAddress, AssignedId, EventEventUpdate, Time, 
//            Image, Organization, CalendarGoTo

public final class Event2 extends GenericJson
{

    public SmartMailAddress address;
    public AssignedId assignedId;
    public List attendees;
    public EventEventUpdate changes;
    public String description;
    public Time endTime;
    public String eventStatus;
    public String googleCalendarId;
    public Image image;
    public String name;
    public List organizers;
    public Time originalEndTime;
    public Time originalStartTime;
    public List performers;
    public Organization publisher;
    public String recurrenceSummary;
    public Time startTime;
    public List updates;
    public String url;
    public CalendarGoTo videoCallLink;

    public Event2()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Event2)clone();
    }

    public final volatile GenericData clone()
    {
        return (Event2)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Event2)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Event2)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Event2)super.set(s, obj);
    }
}
