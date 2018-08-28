// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar;

import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.GenericData;
import com.google.api.services.calendar.model.Setting;

// Referenced classes of package com.google.api.services.calendar:
//            CalendarRequest

public final class setting extends CalendarRequest
{

    public String setting;
    public String smartMailDeliveryChangeBehavior;

    public final volatile AbstractGoogleClientRequest set(String s, Object obj)
    {
        return (ientRequest)set(s, obj);
    }

    public final volatile AbstractGoogleJsonClientRequest set(String s, Object obj)
    {
        return (gleJsonClientRequest)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (gleJsonClientRequest)super.set(s, obj);
    }

    public final volatile CalendarRequest set(String s, Object obj)
    {
        return (gleJsonClientRequest)set(s, obj);
    }

    public final CalendarRequest setFields(String s)
    {
        return (gleJsonClientRequest)super.setFields(s);
    }

    public gleJsonClientRequest(gleJsonClientRequest glejsonclientrequest, String s, Setting setting1)
    {
        super(glejsonclientrequest.gleJsonClientRequest, "PUT", "users/me/settings/{setting}", setting1, com/google/api/services/calendar/model/Setting);
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("Required parameter setting must be specified."));
        } else
        {
            setting = (String)s;
            return;
        }
    }
}
