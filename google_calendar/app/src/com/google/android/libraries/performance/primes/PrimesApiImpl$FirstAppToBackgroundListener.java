// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            ShutdownListener, AppLifecycleMonitor, Supplier

final class executorServiceSupplier
    implements executorServiceSupplier, ShutdownListener
{

    private final AppLifecycleMonitor appLifecycleMonitor;
    public boolean appToBackground;
    public final Supplier executorServiceSupplier;
    public final ArrayList firstToBackgroundTasks = new ArrayList();

    public final void onAppToBackground(Activity activity)
    {
        activity = firstToBackgroundTasks;
        activity;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        int j;
        if (appToBackground)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        appToBackground = true;
        appLifecycleMonitor.unregister(this);
        arraylist = (ArrayList)firstToBackgroundTasks;
        j = arraylist.size();
        int i = 0;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        Runnable runnable = (Runnable)arraylist.get(i);
        ((ScheduledExecutorService)executorServiceSupplier.get()).submit(runnable);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        firstToBackgroundTasks.clear();
        activity;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        activity;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void onShutdown()
    {
        appLifecycleMonitor.unregister(this);
    }

    (AppLifecycleMonitor applifecyclemonitor, Supplier supplier)
    {
        appLifecycleMonitor = applifecyclemonitor;
        executorServiceSupplier = supplier;
        applifecyclemonitor.register(this);
    }
}
