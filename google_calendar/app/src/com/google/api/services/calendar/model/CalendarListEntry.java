// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            ConferenceProperties

public final class CalendarListEntry extends GenericJson
{
    public static final class NotificationSettings extends GenericJson
    {

        public List notifications;

        public final volatile GenericJson clone()
        {
            return (NotificationSettings)clone();
        }

        public final volatile GenericData clone()
        {
            return (NotificationSettings)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (NotificationSettings)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (NotificationSettings)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (NotificationSettings)super.set(s, obj);
        }

        public NotificationSettings()
        {
        }
    }


    public String accessRole;
    public String backgroundColor;
    public List categories;
    public String colorId;
    public ConferenceProperties conferenceProperties;
    public List defaultAllDayReminders;
    public List defaultReminders;
    public Boolean deleted;
    public String description;
    public String etag;
    public String foregroundColor;
    public Boolean hidden;
    public String id;
    public String kind;
    public String location;
    public NotificationSettings notificationSettings;
    public Boolean primary;
    public Boolean selected;
    public String summary;
    public String summaryOverride;
    public String timeZone;

    public CalendarListEntry()
    {
    }

    public final volatile GenericJson clone()
    {
        return (CalendarListEntry)clone();
    }

    public final volatile GenericData clone()
    {
        return (CalendarListEntry)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (CalendarListEntry)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (CalendarListEntry)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (CalendarListEntry)super.set(s, obj);
    }
}
