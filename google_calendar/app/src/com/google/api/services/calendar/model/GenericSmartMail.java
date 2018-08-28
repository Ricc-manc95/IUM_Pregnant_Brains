// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, DetailedLayout, Time, SummaryLayout

public final class GenericSmartMail extends GenericJson
{

    public AssignedId assignedId;
    public DetailedLayout detailedLayout;
    public Long itemFingerprint;
    public String smartMailType;
    public Time sortTime;
    public SummaryLayout summaryLayout;

    public GenericSmartMail()
    {
    }

    public final volatile GenericJson clone()
    {
        return (GenericSmartMail)clone();
    }

    public final volatile GenericData clone()
    {
        return (GenericSmartMail)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (GenericSmartMail)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (GenericSmartMail)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (GenericSmartMail)super.set(s, obj);
    }
}
