// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            FormattedText, Image, Badge, Action

public final class SummaryLayout extends GenericJson
{

    public Badge badge;
    public List detailLines;
    public Action entireLayoutAction;
    public String iconOverlay;
    public List images;
    public FormattedText title;

    public SummaryLayout()
    {
    }

    public final volatile GenericJson clone()
    {
        return (SummaryLayout)clone();
    }

    public final volatile GenericData clone()
    {
        return (SummaryLayout)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (SummaryLayout)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (SummaryLayout)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (SummaryLayout)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/calendar/model/FormattedText);
        Data.nullOf(com/google/api/services/calendar/model/Image);
    }
}
