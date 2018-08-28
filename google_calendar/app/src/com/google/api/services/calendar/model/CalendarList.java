// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

public final class CalendarList extends GenericJson
{

    public String etag;
    public List items;
    public String kind;
    public String nextPageToken;
    public String nextSyncToken;

    public CalendarList()
    {
    }

    public final volatile GenericJson clone()
    {
        return (CalendarList)clone();
    }

    public final volatile GenericData clone()
    {
        return (CalendarList)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (CalendarList)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (CalendarList)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (CalendarList)super.set(s, obj);
    }
}
