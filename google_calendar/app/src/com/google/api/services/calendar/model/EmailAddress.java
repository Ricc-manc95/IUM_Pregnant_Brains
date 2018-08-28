// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            ContactTag

public final class EmailAddress extends GenericJson
{

    public ContactTag contactTag;
    public String emailAddress;

    public EmailAddress()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EmailAddress)clone();
    }

    public final volatile GenericData clone()
    {
        return (EmailAddress)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EmailAddress)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EmailAddress)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EmailAddress)super.set(s, obj);
    }
}
