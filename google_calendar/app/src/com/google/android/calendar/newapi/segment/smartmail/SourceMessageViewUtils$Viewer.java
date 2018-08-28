// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.smartmail;

import android.content.Context;
import android.content.Intent;

abstract class minVersion
{

    public final String mPackageName;
    public final int minVersion;

    abstract Intent getIntent(Context context, String s, String s1, String s2);

    protected (String s, int i)
    {
        mPackageName = s;
        minVersion = i;
    }
}
