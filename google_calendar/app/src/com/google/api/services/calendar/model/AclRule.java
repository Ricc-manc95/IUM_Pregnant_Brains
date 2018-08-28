// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;

public final class AclRule extends GenericJson
{

    public String etag;
    public String id;
    public String kind;
    public String role;
    public Scope scope;
    public DateTime updated;

    public AclRule()
    {
    }

    public final volatile GenericJson clone()
    {
        return (AclRule)clone();
    }

    public final volatile GenericData clone()
    {
        return (AclRule)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (AclRule)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (AclRule)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (AclRule)super.set(s, obj);
    }
}
