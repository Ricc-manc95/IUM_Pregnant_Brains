// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

public final class FormattedText extends GenericJson
{

    public List parts;
    public String translationId;
    public List translationParameters;

    public FormattedText()
    {
    }

    public final volatile GenericJson clone()
    {
        return (FormattedText)clone();
    }

    public final volatile GenericData clone()
    {
        return (FormattedText)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (FormattedText)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (FormattedText)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (FormattedText)super.set(s, obj);
    }
}
