// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            Offer, AssignedId

public final class Product extends GenericJson
{

    public AssignedId assignedId;
    public String imageUrl;
    public Boolean isDigital;
    public String name;
    public List offers;

    public Product()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Product)clone();
    }

    public final volatile GenericData clone()
    {
        return (Product)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Product)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Product)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Product)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/calendar/model/Offer);
    }
}
