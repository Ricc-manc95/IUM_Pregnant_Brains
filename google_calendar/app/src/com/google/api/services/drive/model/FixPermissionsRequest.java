// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.drive.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

public final class FixPermissionsRequest extends GenericJson
{

    public List fileIds;
    public String fixOptionType;
    public String kind;
    public List recipientEmailAddresses;
    public String role;

    public FixPermissionsRequest()
    {
    }

    public final volatile GenericJson clone()
    {
        return (FixPermissionsRequest)clone();
    }

    public final volatile GenericData clone()
    {
        return (FixPermissionsRequest)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (FixPermissionsRequest)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (FixPermissionsRequest)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (FixPermissionsRequest)super.set(s, obj);
    }
}
