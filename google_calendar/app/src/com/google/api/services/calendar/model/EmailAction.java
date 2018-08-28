// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

public final class EmailAction extends GenericJson
{

    public List emailAddress;

    public EmailAction()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EmailAction)clone();
    }

    public final volatile GenericData clone()
    {
        return (EmailAction)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EmailAction)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EmailAction)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EmailAction)super.set(s, obj);
    }
}
