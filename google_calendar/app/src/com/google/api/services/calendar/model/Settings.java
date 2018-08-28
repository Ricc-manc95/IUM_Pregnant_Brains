// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            Setting

public final class Settings extends GenericJson
{

    public String etag;
    public List items;
    public String kind;
    public String nextPageToken;
    public String nextSyncToken;

    public Settings()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Settings)clone();
    }

    public final volatile GenericData clone()
    {
        return (Settings)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Settings)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Settings)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Settings)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/calendar/model/Setting);
    }
}
