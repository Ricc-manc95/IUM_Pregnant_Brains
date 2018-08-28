// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            SmartMailAddress

public final class ViewMapAction extends GenericJson
{

    public SmartMailAddress address;
    public Boolean includeNameInFormattedString;

    public ViewMapAction()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ViewMapAction)clone();
    }

    public final volatile GenericData clone()
    {
        return (ViewMapAction)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ViewMapAction)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ViewMapAction)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ViewMapAction)super.set(s, obj);
    }
}
