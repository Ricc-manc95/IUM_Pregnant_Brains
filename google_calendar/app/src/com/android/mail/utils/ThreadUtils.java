// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.mail.utils;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Looper;
import com.google.apps.xplat.tracing.XTracer;

public final class ThreadUtils
{

    private static final int CPU_COUNT;

    public static void throwExceptionIfUiThread()
    {
        boolean flag1 = true;
        boolean flag;
        if (ActivityManager.isRunningInTestHarness() || "robolectric".equals(Build.FINGERPRINT) || ActivityManager.isUserAMonkey())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            if (Looper.getMainLooper() == Looper.myLooper())
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                throw new IllegalStateException("Called on the main UI thread");
            }
        }
    }

    static 
    {
        new XTracer("ThreadUtils");
        CPU_COUNT = Runtime.getRuntime().availableProcessors();
        Math.max(2, Math.min(CPU_COUNT - 1, 4));
    }
}
