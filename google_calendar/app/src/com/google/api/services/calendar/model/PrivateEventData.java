// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            Annotations, SmartMailInfo

public final class PrivateEventData extends GenericJson
{

    public Annotations annotations;
    public SmartMailInfo smartMailInfo;

    public PrivateEventData()
    {
    }

    public final volatile GenericJson clone()
    {
        return (PrivateEventData)clone();
    }

    public final volatile GenericData clone()
    {
        return (PrivateEventData)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (PrivateEventData)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (PrivateEventData)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (PrivateEventData)super.set(s, obj);
    }
}
