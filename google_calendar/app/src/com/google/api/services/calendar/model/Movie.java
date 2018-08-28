// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId

public final class Movie extends GenericJson
{

    public AssignedId assignedId;
    public String imageUrl;
    public String name;

    public Movie()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Movie)clone();
    }

    public final volatile GenericData clone()
    {
        return (Movie)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Movie)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Movie)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Movie)super.set(s, obj);
    }
}
