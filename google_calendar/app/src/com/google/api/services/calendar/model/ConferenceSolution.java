// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            ConferenceSolutionKey

public final class ConferenceSolution extends GenericJson
{

    public String iconUri;
    public ConferenceSolutionKey key;
    public String name;

    public ConferenceSolution()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ConferenceSolution)clone();
    }

    public final volatile GenericData clone()
    {
        return (ConferenceSolution)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ConferenceSolution)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ConferenceSolution)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ConferenceSolution)super.set(s, obj);
    }
}
