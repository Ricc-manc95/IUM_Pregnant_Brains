// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            SmartMailAddress, GooglePlusData, Time

public final class Organization extends GenericJson
{

    public SmartMailAddress address;
    public GooglePlusData googlePlusData;
    public Boolean isOpen;
    public String name;
    public Time nextChange;
    public List phoneNumbers;
    public String url;

    public Organization()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Organization)clone();
    }

    public final volatile GenericData clone()
    {
        return (Organization)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Organization)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Organization)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Organization)super.set(s, obj);
    }
}
