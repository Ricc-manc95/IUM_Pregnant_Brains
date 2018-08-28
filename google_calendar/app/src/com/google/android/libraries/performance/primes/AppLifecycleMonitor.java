// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import java.util.List;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AppLifecycleTracker, AppLifecycleListener

public class AppLifecycleMonitor
{

    public static volatile AppLifecycleMonitor instance;
    public final AppLifecycleTracker tracker = new AppLifecycleTracker();

    private AppLifecycleMonitor()
    {
    }

    public static AppLifecycleMonitor getInstance(Application application)
    {
        if (instance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/AppLifecycleMonitor;
        JVM INSTR monitorenter ;
        if (instance == null)
        {
            AppLifecycleMonitor applifecyclemonitor = new AppLifecycleMonitor();
            AppLifecycleTracker applifecycletracker = applifecyclemonitor.tracker;
            application.registerActivityLifecycleCallbacks(applifecycletracker.callbacks);
            application.registerComponentCallbacks(applifecycletracker.callbacks);
            instance = applifecyclemonitor;
        }
        com/google/android/libraries/performance/primes/AppLifecycleMonitor;
        JVM INSTR monitorexit ;
_L2:
        return instance;
        application;
        com/google/android/libraries/performance/primes/AppLifecycleMonitor;
        JVM INSTR monitorexit ;
        throw application;
    }

    public final void register(AppLifecycleListener applifecyclelistener)
    {
        AppLifecycleTracker applifecycletracker = tracker;
        if (applifecyclelistener == null)
        {
            throw new NullPointerException();
        } else
        {
            applifecycletracker.callbacks.lifecycleListeners.add(applifecyclelistener);
            return;
        }
    }

    public final void unregister(AppLifecycleListener applifecyclelistener)
    {
        AppLifecycleTracker applifecycletracker = tracker;
        if (applifecyclelistener == null)
        {
            throw new NullPointerException();
        } else
        {
            applifecycletracker.callbacks.lifecycleListeners.remove(applifecyclelistener);
            return;
        }
    }
}
