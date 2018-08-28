// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.text.TextUtils;

public final class mapsClusterId
{

    public final String address;
    public final String mapsClusterId;
    public final String title;

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (mapsClusterId)obj;
            if (title == null ? ((title) (obj)).title != null : !title.equals(((title) (obj)).title))
            {
                return false;
            }
            if (address == null ? ((address) (obj)).address != null : !address.equals(((address) (obj)).address))
            {
                return false;
            }
            if (mapsClusterId != null)
            {
                return mapsClusterId.equals(((mapsClusterId) (obj)).mapsClusterId);
            }
            if (((mapsClusterId) (obj)).mapsClusterId != null)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int k = 0;
        int i;
        int j;
        if (title != null)
        {
            i = title.hashCode();
        } else
        {
            i = 0;
        }
        if (address != null)
        {
            j = address.hashCode();
        } else
        {
            j = 0;
        }
        if (mapsClusterId != null)
        {
            k = mapsClusterId.hashCode();
        }
        return (j + i * 31) * 31 + k;
    }

    public (String s)
    {
        this(null, s, null);
    }

    public <init>(String s, String s1, String s2)
    {
        if (TextUtils.isEmpty(s2))
        {
            s = null;
        }
        title = s;
        address = s1;
        mapsClusterId = s2;
    }
}
