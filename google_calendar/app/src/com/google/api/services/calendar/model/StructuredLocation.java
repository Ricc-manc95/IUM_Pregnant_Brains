// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            EventLocation

public final class StructuredLocation extends GenericJson
{

    public List locations;

    public StructuredLocation()
    {
    }

    public final volatile GenericJson clone()
    {
        return (StructuredLocation)clone();
    }

    public final volatile GenericData clone()
    {
        return (StructuredLocation)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (StructuredLocation)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (StructuredLocation)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (StructuredLocation)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/calendar/model/EventLocation);
    }
}
