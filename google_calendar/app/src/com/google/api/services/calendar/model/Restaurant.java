// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, Image, Organization

public final class Restaurant extends GenericJson
{

    public AssignedId assignedId;
    public Image image;
    public Organization organization;
    public String restaurantId;

    public Restaurant()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Restaurant)clone();
    }

    public final volatile GenericData clone()
    {
        return (Restaurant)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Restaurant)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Restaurant)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Restaurant)super.set(s, obj);
    }
}
