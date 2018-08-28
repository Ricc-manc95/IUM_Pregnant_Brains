// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, Organization

public final class Article extends GenericJson
{

    public String ampUrl;
    public AssignedId assignedId;
    public String description;
    public String imageUrl;
    public Organization publisher;
    public String title;
    public String url;

    public Article()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Article)clone();
    }

    public final volatile GenericData clone()
    {
        return (Article)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Article)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Article)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Article)super.set(s, obj);
    }
}
