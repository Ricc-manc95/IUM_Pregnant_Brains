// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common.net;

import android.content.Context;

public final class LocationResolver
{

    public final Context context;
    public final String language;

    public LocationResolver(Context context1, String s)
    {
        context = context1.getApplicationContext();
        language = s;
    }
}
