// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendarSuggest.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendarSuggest.model:
//            Time

public final class Query extends GenericJson
{

    public Integer completionPoint;
    public String fullText;
    public String payload;
    public List terms;
    public Time time;

    public Query()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Query)clone();
    }

    public final volatile GenericData clone()
    {
        return (Query)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Query)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Query)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Query)super.set(s, obj);
    }
}
