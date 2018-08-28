// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class DonateEmailAction extends GenericJson
{

    public String classification;
    public String state;
    public String type;
    public String userDescription;

    public DonateEmailAction()
    {
    }

    public final volatile GenericJson clone()
    {
        return (DonateEmailAction)clone();
    }

    public final volatile GenericData clone()
    {
        return (DonateEmailAction)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (DonateEmailAction)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (DonateEmailAction)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (DonateEmailAction)super.set(s, obj);
    }
}
