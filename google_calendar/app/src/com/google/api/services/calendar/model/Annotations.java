// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

public final class Annotations extends GenericJson
{

    public List titleContactAnnotations;

    public Annotations()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Annotations)clone();
    }

    public final volatile GenericData clone()
    {
        return (Annotations)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Annotations)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Annotations)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Annotations)super.set(s, obj);
    }
}
