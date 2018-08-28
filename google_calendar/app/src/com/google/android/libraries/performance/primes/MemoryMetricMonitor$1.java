// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            MemoryMetricMonitor, Supplier

final class this._cls0
    implements nAppToBackground
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

            private final MemoryMetricMonitor._cls1 this$1;
            private final String val$activityName;

            public final void run()
            {
                callback.onEvent(4, activityName);
            }

            _cls1()
            {
                this$1 = MemoryMetricMonitor._cls1.this;
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
}
