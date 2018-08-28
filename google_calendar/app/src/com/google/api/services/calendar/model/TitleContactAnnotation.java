// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            UnicodeStringAnnotation, ContactReference

public final class TitleContactAnnotation extends GenericJson
{

    public UnicodeStringAnnotation annotation;
    public ContactReference contact;

    public TitleContactAnnotation()
    {
    }

    public final volatile GenericJson clone()
    {
        return (TitleContactAnnotation)clone();
    }

    public final volatile GenericData clone()
    {
        return (TitleContactAnnotation)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (TitleContactAnnotation)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (TitleContactAnnotation)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (TitleContactAnnotation)super.set(s, obj);
    }
}
