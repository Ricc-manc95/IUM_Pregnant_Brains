// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import android.app.Application;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public final class DisplayStats
{

    private static volatile int maxFrameRenderTimeMs;
    private static volatile int refreshRate;

    public DisplayStats()
    {
    }

    public static int getRefreshRate(Application application)
    {
        if (refreshRate != 0) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/metriccapture/DisplayStats;
        JVM INSTR monitorenter ;
        if (refreshRate == 0)
        {
            refreshRate = Math.round(((WindowManager)application.getApplicationContext().getSystemService("window")).getDefaultDisplay().getRefreshRate());
        }
        com/google/android/libraries/performance/primes/metriccapture/DisplayStats;
        JVM INSTR monitorexit ;
_L2:
        return refreshRate;
        application;
        com/google/android/libraries/performance/primes/metriccapture/DisplayStats;
        JVM INSTR monitorexit ;
        throw application;
    }

    public static int maxAcceptedFrameRenderTimeMs(Application application)
    {
        if (maxFrameRenderTimeMs != 0) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/metriccapture/DisplayStats;
        JVM INSTR monitorenter ;
        if (maxFrameRenderTimeMs != 0) goto _L4; else goto _L3
_L3:
        int j = getRefreshRate(application);
        if (j < 10) goto _L6; else goto _L5
_L5:
        int i = j;
        if (j <= 60) goto _L7; else goto _L6
_L7:
        maxFrameRenderTimeMs = (int)Math.ceil(1000D / (double)i);
_L4:
        com/google/android/libraries/performance/primes/metriccapture/DisplayStats;
        JVM INSTR monitorexit ;
_L2:
        return maxFrameRenderTimeMs;
        application;
        com/google/android/libraries/performance/primes/metriccapture/DisplayStats;
        JVM INSTR monitorexit ;
        throw application;
_L6:
        i = 60;
        if (true) goto _L7; else goto _L8
_L8:
    }
}
