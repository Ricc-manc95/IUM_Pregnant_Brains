// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            ImageTitleDescription

public final class DetailedLayoutSection extends GenericJson
{

    public String icon;
    public ImageTitleDescription imageTitleDescription;
    public List sectionDatas;
    public String widgetType;

    public DetailedLayoutSection()
    {
    }

    public final volatile GenericJson clone()
    {
        return (DetailedLayoutSection)clone();
    }

    public final volatile GenericData clone()
    {
        return (DetailedLayoutSection)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (DetailedLayoutSection)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (DetailedLayoutSection)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (DetailedLayoutSection)super.set(s, obj);
    }
}
