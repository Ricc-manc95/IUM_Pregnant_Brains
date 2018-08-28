// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

public final class Image extends GenericJson
{

    public List attributionMetadatas;
    public String imageMetadataUrl;
    public String imageUrl;
    public String imageUrlSource;
    public String originalImageUrl;
    public Integer sampleColor;
    public Integer secondaryColor;
    public String sizeOption;
    public String stockPhoto;

    public Image()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Image)clone();
    }

    public final volatile GenericData clone()
    {
        return (Image)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Image)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Image)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Image)super.set(s, obj);
    }
}
