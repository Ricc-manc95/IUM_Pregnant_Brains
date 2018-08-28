// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class RsvpActionUserResponse extends GenericJson
{

    public String comment;
    public Integer numberAdditionalGuests;
    public String selectedRsvpResponseType;
    public Long timestampMs;

    public RsvpActionUserResponse()
    {
    }

    public final volatile GenericJson clone()
    {
        return (RsvpActionUserResponse)clone();
    }

    public final volatile GenericData clone()
    {
        return (RsvpActionUserResponse)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (RsvpActionUserResponse)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (RsvpActionUserResponse)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (RsvpActionUserResponse)super.set(s, obj);
    }
}
