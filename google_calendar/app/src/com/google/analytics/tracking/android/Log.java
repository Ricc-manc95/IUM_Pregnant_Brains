// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;


public final class Log
{

    public static int i(String s)
    {
        (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(s);
        return 0;
    }

    public static int w(String s)
    {
        return android.util.Log.w("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(s).toString());
    }
}
