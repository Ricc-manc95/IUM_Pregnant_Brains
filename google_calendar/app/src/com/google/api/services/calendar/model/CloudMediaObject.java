// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, Image

public final class CloudMediaObject extends GenericJson
{

    public AssignedId assignedId;
    public String cloudObjectId;
    public Image image;
    public Boolean isFromQuotedRegion;
    public String mediaObjectUrl;
    public String mimeType;
    public String title;

    public CloudMediaObject()
    {
    }

    public final volatile GenericJson clone()
    {
        return (CloudMediaObject)clone();
    }

    public final volatile GenericData clone()
    {
        return (CloudMediaObject)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (CloudMediaObject)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (CloudMediaObject)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (CloudMediaObject)super.set(s, obj);
    }
}
