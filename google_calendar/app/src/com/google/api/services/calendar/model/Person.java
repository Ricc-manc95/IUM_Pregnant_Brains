// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            EmailAddress

public final class Person extends GenericJson
{

    public List emailAddress;
    public String firstName;
    public String gaiaId;
    public String lastName;
    public String name;
    public List phoneNumbers;
    public String photoUrl;
    public List profileUrls;

    public Person()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Person)clone();
    }

    public final volatile GenericData clone()
    {
        return (Person)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Person)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Person)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Person)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/calendar/model/EmailAddress);
    }
}
