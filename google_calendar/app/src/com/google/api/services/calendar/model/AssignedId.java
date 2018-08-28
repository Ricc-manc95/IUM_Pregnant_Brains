// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class AssignedId extends GenericJson
{

    public Integer index;
    public String messageId;
    public AssignedId nestedId;
    public String uniqueIdentifier;

    public AssignedId()
    {
    }

    public final volatile GenericJson clone()
    {
        return (AssignedId)clone();
    }

    public final volatile GenericData clone()
    {
        return (AssignedId)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (AssignedId)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (AssignedId)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (AssignedId)super.set(s, obj);
    }
}
