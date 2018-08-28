// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;


public class Feature
{

    public Feature()
    {
    }

    public Object getValue()
    {
        throw new IllegalStateException("Cannot call getValue() for Feature where isSupported()==false");
    }

    public boolean isSupported()
    {
        return false;
    }
}
