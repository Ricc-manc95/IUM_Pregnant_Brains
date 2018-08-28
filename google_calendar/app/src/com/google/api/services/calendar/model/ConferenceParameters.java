// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            ConferenceParametersAddOnParameters

public final class ConferenceParameters extends GenericJson
{

    public ConferenceParametersAddOnParameters addOnParameters;

    public ConferenceParameters()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ConferenceParameters)clone();
    }

    public final volatile GenericData clone()
    {
        return (ConferenceParameters)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ConferenceParameters)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ConferenceParameters)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ConferenceParameters)super.set(s, obj);
    }
}
