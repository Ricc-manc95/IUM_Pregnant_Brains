// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;

import android.util.Log;

public final class Logger
{

    public final String tag = "GrowthKit";

    public Logger()
    {
    }

    public final transient void e(String s, Object aobj[])
    {
        String s2 = tag;
        String s1 = s;
        if (aobj != null)
        {
            s1 = s;
            if (aobj.length > 0)
            {
                s1 = String.format(s, aobj);
            }
        }
        Log.e(s2, s1);
    }

    public final transient void e(Throwable throwable, String s, Object aobj[])
    {
        String s2 = tag;
        String s1 = s;
        if (aobj != null)
        {
            s1 = s;
            if (aobj.length > 0)
            {
                s1 = String.format(s, aobj);
            }
        }
        Log.e(s2, s1, throwable);
    }

    public final transient void w(String s, Object aobj[])
    {
        String s2 = tag;
        String s1 = s;
        if (aobj != null)
        {
            s1 = s;
            if (aobj.length > 0)
            {
                s1 = String.format(s, aobj);
            }
        }
        Log.w(s2, s1);
    }

    public final transient void w(Throwable throwable, String s, Object aobj[])
    {
        String s2 = tag;
        String s1 = s;
        if (aobj != null)
        {
            s1 = s;
            if (aobj.length > 0)
            {
                s1 = String.format(s, aobj);
            }
        }
        Log.w(s2, s1, throwable);
    }
}
