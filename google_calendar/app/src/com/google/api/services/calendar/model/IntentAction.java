// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

public final class IntentAction extends GenericJson
{

    public String fallbackUrl;
    public List intentDetails;
    public String intentType;
    public String text;

    public IntentAction()
    {
    }

    public final volatile GenericJson clone()
    {
        return (IntentAction)clone();
    }

    public final volatile GenericData clone()
    {
        return (IntentAction)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (IntentAction)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (IntentAction)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (IntentAction)super.set(s, obj);
    }
}
