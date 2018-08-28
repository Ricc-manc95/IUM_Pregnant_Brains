// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import android.util.Log;

final class FutureApis
{

    public static int version()
    {
        int i;
        try
        {
            i = Integer.parseInt(android.os.Build.VERSION.SDK);
        }
        catch (NumberFormatException numberformatexception)
        {
            String s = (new StringBuilder("Invalid version number: ")).append(android.os.Build.VERSION.SDK).toString();
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(s).toString());
            return 0;
        }
        return i;
    }
}
