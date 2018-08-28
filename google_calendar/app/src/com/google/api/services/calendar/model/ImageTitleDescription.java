// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            Action, FormattedText, Image

public final class ImageTitleDescription extends GenericJson
{

    public Action action;
    public FormattedText description;
    public Image image;
    public FormattedText title;

    public ImageTitleDescription()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ImageTitleDescription)clone();
    }

    public final volatile GenericData clone()
    {
        return (ImageTitleDescription)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ImageTitleDescription)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ImageTitleDescription)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ImageTitleDescription)super.set(s, obj);
    }
}
