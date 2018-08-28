// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class ActionResponse extends GenericJson
{

    public String debugErrorMessage;
    public Integer httpStatusCode;
    public String status;

    public ActionResponse()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ActionResponse)clone();
    }

    public final volatile GenericData clone()
    {
        return (ActionResponse)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ActionResponse)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ActionResponse)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ActionResponse)super.set(s, obj);
    }
}
