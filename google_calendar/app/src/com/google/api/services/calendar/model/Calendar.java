// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            ConferenceProperties

public final class Calendar extends GenericJson
{

    public List categories;
    public ConferenceProperties conferenceProperties;
    public String description;
    public String etag;
    public String googleMeetOptInStatus;
    public String id;
    public String kind;
    public String location;
    public String summary;
    public String timeZone;

    public Calendar()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Calendar)clone();
    }

    public final volatile GenericData clone()
    {
        return (Calendar)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Calendar)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Calendar)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Calendar)super.set(s, obj);
    }
}
