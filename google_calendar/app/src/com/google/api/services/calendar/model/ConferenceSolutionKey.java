// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            ConferenceSolutionAddOnId

public final class ConferenceSolutionKey extends GenericJson
{

    public ConferenceSolutionAddOnId addOnId;
    public String type;

    public ConferenceSolutionKey()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ConferenceSolutionKey)clone();
    }

    public final volatile GenericData clone()
    {
        return (ConferenceSolutionKey)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ConferenceSolutionKey)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ConferenceSolutionKey)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ConferenceSolutionKey)super.set(s, obj);
    }
}
