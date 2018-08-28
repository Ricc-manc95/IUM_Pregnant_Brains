// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class ContactReference extends GenericJson
{

    public Long focusId;

    public ContactReference()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ContactReference)clone();
    }

    public final volatile GenericData clone()
    {
        return (ContactReference)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ContactReference)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ContactReference)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ContactReference)super.set(s, obj);
    }
}
