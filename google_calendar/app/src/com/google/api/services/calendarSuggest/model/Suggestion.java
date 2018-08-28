// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendarSuggest.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendarSuggest.model:
//            EventInfo, Query

public final class Suggestion extends GenericJson
{

    public String description;
    public EventInfo eventInfo;
    public Query query;
    public Double score;
    public String source;
    public String subDescription;

    public Suggestion()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Suggestion)clone();
    }

    public final volatile GenericData clone()
    {
        return (Suggestion)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Suggestion)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Suggestion)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Suggestion)super.set(s, obj);
    }
}
