// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.os.Looper;
import android.os.SystemClock;
import com.google.android.libraries.performance.primes.PrimesStartupMeasure;
import com.google.android.libraries.stitch.util.ThreadUtil;

final class arg._cls1
    implements Runnable
{

    private final PrimesStartupMeasure arg$1;

    public final void run()
    {
        PrimesStartupMeasure primesstartupmeasure = arg$1;
        if (ThreadUtil.sMainThread == null)
        {
            ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
        }
        boolean flag;
        if (Thread.currentThread() == ThreadUtil.sMainThread)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && primesstartupmeasure.appClassLoadedAt > 0L && primesstartupmeasure.firstOnActivityInitAt == 0L && primesstartupmeasure.firstOnActivityCreatedAt == 0L)
        {
            primesstartupmeasure.firstOnActivityInitAt = SystemClock.elapsedRealtime();
            primesstartupmeasure.runOnActivityInitListeners();
        }
    }

    upMeasure(PrimesStartupMeasure primesstartupmeasure)
    {
        arg$1 = primesstartupmeasure;
    }
}
