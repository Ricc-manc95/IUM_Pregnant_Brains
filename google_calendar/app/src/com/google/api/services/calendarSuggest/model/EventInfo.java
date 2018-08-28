// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendarSuggest.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendarSuggest.model:
//            Annotations

public final class EventInfo extends GenericJson
{

    public Annotations annotations;
    public String title;

    public EventInfo()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EventInfo)clone();
    }

    public final volatile GenericData clone()
    {
        return (EventInfo)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EventInfo)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EventInfo)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EventInfo)super.set(s, obj);
    }
}
