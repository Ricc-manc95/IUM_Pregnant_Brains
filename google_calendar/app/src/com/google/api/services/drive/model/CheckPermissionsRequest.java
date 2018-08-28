// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.drive.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

public final class CheckPermissionsRequest extends GenericJson
{

    public List fileIds;
    public String kind;
    public List recipientEmailAddresses;
    public String role;

    public CheckPermissionsRequest()
    {
    }

    public final volatile GenericJson clone()
    {
        return (CheckPermissionsRequest)clone();
    }

    public final volatile GenericData clone()
    {
        return (CheckPermissionsRequest)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (CheckPermissionsRequest)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (CheckPermissionsRequest)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (CheckPermissionsRequest)super.set(s, obj);
    }
}
