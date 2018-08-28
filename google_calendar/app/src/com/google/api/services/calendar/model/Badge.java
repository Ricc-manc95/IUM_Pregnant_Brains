// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            Time

public final class Badge extends GenericJson
{

    public Time expirationTime;
    public Time startTime;
    public String status;
    public String text;

    public Badge()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Badge)clone();
    }

    public final volatile GenericData clone()
    {
        return (Badge)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Badge)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Badge)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Badge)super.set(s, obj);
    }
}
