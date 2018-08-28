// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class EventAttachment extends GenericJson
{

    public String fileId;
    public String fileUrl;
    public String iconLink;
    public String mimeType;
    public String title;

    public EventAttachment()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EventAttachment)clone();
    }

    public final volatile GenericData clone()
    {
        return (EventAttachment)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EventAttachment)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EventAttachment)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EventAttachment)super.set(s, obj);
    }
}
