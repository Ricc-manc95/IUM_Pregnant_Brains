// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AppLifecycleMonitor, AppLifecycleTracker

final class activityResumedCallback
    implements sumed, Executor
{

    private volatile Activity activity;
    private final ityResumedCallback activityResumedCallback;
    private boolean done;
    private final AppLifecycleMonitor lifecycleMonitor;
    private boolean resumed;
    private Runnable task;

    private final void runTask(Runnable runnable)
    {
label0:
        {
            if (!done)
            {
                done = true;
                if (activityResumedCallback != null)
                {
                    break label0;
                }
                runnable.run();
            }
            return;
        }
        runnable = activityResumedCallback;
        Activity activity1 = activity;
        runnable._mth51662RJ4E9NMIP1FC5O70BQ1CDQ6ITJ9EHSJMJ3AC5R62BRCC5N6EBQIELN6SOB2DHIJMAAM0();
    }

    public final void execute(Runnable runnable)
    {
        this;
        JVM INSTR monitorenter ;
        if (!resumed && lifecycleMonitor.tracker.callbacks.umedCount.get() <= 0)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        runTask(runnable);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        task = runnable;
        if (true) goto _L2; else goto _L1
_L1:
        runnable;
        this;
        JVM INSTR monitorexit ;
        throw runnable;
    }

    public final void onActivityResumed(Activity activity1)
    {
        lifecycleMonitor.unregister(this);
        this;
        JVM INSTR monitorenter ;
        activity = activity1;
        if (task == null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        runTask(task);
        task = null;
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        resumed = true;
        if (true) goto _L2; else goto _L1
_L1:
        activity1;
        this;
        JVM INSTR monitorexit ;
        throw activity1;
    }

    ityResumedCallback(AppLifecycleMonitor applifecyclemonitor, ityResumedCallback ityresumedcallback)
    {
        lifecycleMonitor = applifecyclemonitor;
        activityResumedCallback = ityresumedcallback;
    }
}
