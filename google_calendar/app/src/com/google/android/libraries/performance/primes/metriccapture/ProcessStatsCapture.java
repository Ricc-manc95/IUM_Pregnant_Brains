// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import android.content.Context;
import android.os.Process;
import logs.proto.wireless.performance.mobile.nano.AndroidProcessStats;

// Referenced classes of package com.google.android.libraries.performance.primes.metriccapture:
//            ProcessStats

public final class ProcessStatsCapture
{

    public static AndroidProcessStats getAndroidProcessStats(String s, Context context)
    {
        AndroidProcessStats androidprocessstats = new AndroidProcessStats();
        androidprocessstats.processElapsedTimeMs = Long.valueOf(Process.getElapsedCpuTime());
        androidprocessstats.isInForeground = Boolean.valueOf(ProcessStats.isAppInForeground(context));
        androidprocessstats.threadCount = Integer.valueOf(Thread.activeCount());
        if (s != null)
        {
            androidprocessstats.processName = s;
        }
        return androidprocessstats;
    }
}
