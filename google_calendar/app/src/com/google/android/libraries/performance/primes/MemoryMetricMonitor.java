// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AppLifecycleMonitor, Supplier

final class MemoryMetricMonitor
{

    public final AppLifecycleMonitor appLifecycleMonitor;
    public final Callback callback;
    public final Supplier executorServiceSupplier;
    public ScheduledFuture futureMemoryBackgroundTask;
    public ScheduledFuture futureMemoryForegroundTask;
    public final AtomicBoolean hasMemoryMonitorStarted;
    public final AppLifecycleListener.OnAppToBackground onAppToBackground;
    public final AppLifecycleListener.OnAppToForeground onAppToForeground;

    MemoryMetricMonitor(Callback callback1, Application application, Supplier supplier)
    {
        this(callback1, supplier, AppLifecycleMonitor.getInstance(application));
    }

    private MemoryMetricMonitor(Callback callback1, Supplier supplier, AppLifecycleMonitor applifecyclemonitor)
    {
        hasMemoryMonitorStarted = new AtomicBoolean(false);
        onAppToBackground = new _cls1();
        onAppToForeground = new _cls2();
        callback = callback1;
        executorServiceSupplier = supplier;
        appLifecycleMonitor = applifecyclemonitor;
    }

    private class _cls1
        implements AppLifecycleListener.OnAppToBackground
    {

        public final MemoryMetricMonitor this$0;

        public final void onAppToBackground(final Activity activityName)
        {
            activityName = activityName.getClass().getSimpleName();
            callback.onEvent(2, activityName);
            MemoryMetricMonitor memorymetricmonitor = MemoryMetricMonitor.this;
            if (memorymetricmonitor.futureMemoryForegroundTask != null)
            {
                memorymetricmonitor.futureMemoryForegroundTask.cancel(true);
                memorymetricmonitor.futureMemoryForegroundTask = null;
            }
            if (memorymetricmonitor.futureMemoryBackgroundTask != null)
            {
                memorymetricmonitor.futureMemoryBackgroundTask.cancel(true);
                memorymetricmonitor.futureMemoryBackgroundTask = null;
            }
            class _cls1
                implements Runnable
            {

                private final _cls1 this$1;
                private final String val$activityName;

                public final void run()
                {
                    callback.onEvent(4, activityName);
                }

                _cls1()
                {
                    this$1 = _cls1.this;
                    activityName = s;
                    super();
                }
            }

            futureMemoryBackgroundTask = ((ScheduledExecutorService)executorServiceSupplier.get()).schedule(new _cls1(), 10L, TimeUnit.SECONDS);
        }

        _cls1()
        {
            this$0 = MemoryMetricMonitor.this;
            super();
        }

        private class Callback
        {

            public abstract void onEvent(int i, String s);
        }

    }


    private class _cls2
        implements AppLifecycleListener.OnAppToForeground
    {

        public final MemoryMetricMonitor this$0;

        public final void onAppToForeground(final Activity activityName)
        {
            activityName = activityName.getClass().getSimpleName();
            callback.onEvent(3, activityName);
            MemoryMetricMonitor memorymetricmonitor = MemoryMetricMonitor.this;
            if (memorymetricmonitor.futureMemoryForegroundTask != null)
            {
                memorymetricmonitor.futureMemoryForegroundTask.cancel(true);
                memorymetricmonitor.futureMemoryForegroundTask = null;
            }
            if (memorymetricmonitor.futureMemoryBackgroundTask != null)
            {
                memorymetricmonitor.futureMemoryBackgroundTask.cancel(true);
                memorymetricmonitor.futureMemoryBackgroundTask = null;
            }
            class _cls1
                implements Runnable
            {

                private final _cls2 this$1;
                private final String val$activityName;

                public final void run()
                {
                    callback.onEvent(5, activityName);
                }

                _cls1()
                {
                    this$1 = _cls2.this;
                    activityName = s;
                    super();
                }
            }

            futureMemoryForegroundTask = ((ScheduledExecutorService)executorServiceSupplier.get()).schedule(new _cls1(), 10L, TimeUnit.SECONDS);
        }

        _cls2()
        {
            this$0 = MemoryMetricMonitor.this;
            super();
        }
    }

}
