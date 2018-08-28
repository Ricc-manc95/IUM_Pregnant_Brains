// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.Map;

// Referenced classes of package com.google.api.services.calendar.model:
//            Event

public static final class _cls9 extends GenericJson
{

    public String display;
    public Integer height;
    public String iconLink;
    public String link;
    public Map preferences;
    public String title;
    public String type;
    public Integer width;

    public final volatile GenericJson clone()
    {
        return (_cls9)clone();
    }

    public final volatile GenericData clone()
    {
        return (_cls9)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (_cls9)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (_cls9)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (_cls9)super.set(s, obj);
    }

    public _cls9()
    {
    }
}
