// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            EventReminder, Event

public final class Events extends GenericJson
{

    public String accessRole;
    public List defaultAllDayReminders;
    public List defaultReminders;
    public String description;
    public String etag;
    public List items;
    public String kind;
    public String nextPageToken;
    public String nextSyncToken;
    public String summary;
    public String timeZone;
    public DateTime updated;

    public Events()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Events)clone();
    }

    public final volatile GenericData clone()
    {
        return (Events)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Events)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Events)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Events)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/calendar/model/EventReminder);
        Data.nullOf(com/google/api/services/calendar/model/EventReminder);
        Data.nullOf(com/google/api/services/calendar/model/Event);
    }
}
