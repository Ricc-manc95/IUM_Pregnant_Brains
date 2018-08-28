// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, Organization, Person

public final class EmailMessage extends GenericJson
{

    public AssignedId assignedId;
    public Organization publisher;
    public Person sender;
    public String smartSnippet;

    public EmailMessage()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EmailMessage)clone();
    }

    public final volatile GenericData clone()
    {
        return (EmailMessage)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EmailMessage)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EmailMessage)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EmailMessage)super.set(s, obj);
    }
}
