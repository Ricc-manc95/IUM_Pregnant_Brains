// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar;

import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.GenericData;
import com.google.api.services.calendar.model.CalendarList;

// Referenced classes of package com.google.api.services.calendar:
//            CalendarRequest

public final class _cls0 extends CalendarRequest
{

    public Integer maxResults;
    public String minAccessRole;
    public String pageToken;
    public Boolean showDeleted;
    public Boolean showHidden;
    public Boolean supportsAllDayReminders;
    public String syncToken;

    public final volatile AbstractGoogleClientRequest set(String s, Object obj)
    {
        return (ntRequest)set(s, obj);
    }

    public final volatile AbstractGoogleJsonClientRequest set(String s, Object obj)
    {
        return (eJsonClientRequest)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (eJsonClientRequest)super.set(s, obj);
    }

    public final volatile CalendarRequest set(String s, Object obj)
    {
        return (eJsonClientRequest)set(s, obj);
    }

    public final CalendarRequest setFields(String s)
    {
        return (eJsonClientRequest)super.setFields(s);
    }

    public eJsonClientRequest(eJsonClientRequest ejsonclientrequest)
    {
        super(ejsonclientrequest._fld0, "GET", "users/me/calendarList", null, com/google/api/services/calendar/model/CalendarList);
    }
}
