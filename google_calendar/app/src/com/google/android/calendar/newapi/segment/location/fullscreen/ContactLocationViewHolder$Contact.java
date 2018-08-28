// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.net.Uri;

public final class isDuplicate
{

    public final String address;
    public final Uri contactPhoto;
    public final boolean isDuplicate;
    public final String name;

    public (String s, String s1, Uri uri, boolean flag)
    {
        Object obj = null;
        super();
        if (flag)
        {
            s = null;
        }
        name = s;
        address = s1;
        if (flag)
        {
            s = obj;
        } else
        {
            s = uri;
        }
        contactPhoto = s;
        isDuplicate = flag;
    }
}
