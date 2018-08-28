// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.phenotype.client;

import android.net.Uri;

public final class preferGservices
{

    public final Uri contentProviderUri;
    public final String gservicesPrefix;
    public final String phenotypePrefix;
    public final boolean preferGservices;
    public final String sharedPrefsName;
    public final boolean skipGservices;

    public final preferGservices withGservicePrefix(String s)
    {
        if (skipGservices)
        {
            throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
        } else
        {
            return new <init>(sharedPrefsName, contentProviderUri, s, phenotypePrefix, skipGservices, preferGservices);
        }
    }

    public I(String s)
    {
        this(s, null, "", "", false, false);
    }

    public <init>(String s, Uri uri, String s1, String s2, boolean flag, boolean flag1)
    {
        sharedPrefsName = s;
        contentProviderUri = uri;
        gservicesPrefix = s1;
        phenotypePrefix = s2;
        skipGservices = flag;
        preferGservices = flag1;
    }
}
