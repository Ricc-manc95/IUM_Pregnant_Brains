// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            MiniHeapDumpMetricService, Supplier

final class this._cls0
    implements Background
{

    public final MiniHeapDumpMetricService this$0;

    public final void onAppToBackground(Activity activity)
    {
        activity = MiniHeapDumpMetricService.this;
        if (((MiniHeapDumpMetricService) (activity)).futureMemoryCollectionTask != null)
        {
            ((MiniHeapDumpMetricService) (activity)).futureMemoryCollectionTask.cancel(true);
            activity.futureMemoryCollectionTask = null;
        }
        class _cls1
            implements Runnable
        {

            private final MiniHeapDumpMetricService._cls1 this$1;

            public final void run()
            {
                int i = ProcessStats.getActivityManager(application).getProcessMemoryInfo(new int[] {
                    Process.myPid()
                })[0].getTotalPss();
                Object obj = (new StringBuilder(36)).append("Background total pss kb: ").append(i).toString();
                Object obj1 = ((Object) (new Object[0]));
                if (Log.isLoggable("MiniHeapDumpMetric", 3))
                {
                    if (obj1.length != 0)
                    {
                        obj = String.format(Locale.US, ((String) (obj)), ((Object []) (obj1)));
                    }
                    Log.println(3, "MiniHeapDumpMetric", ((String) (obj)));
                }
                addMemorySample(i);
                obj = this$0;
                if (((MiniHeapDumpMetricService) (obj)).miniHeapDumpSampler.canComputePercentile())
                {
                    ((MiniHeapDumpMetricService) (obj)).preferences.edit().putBoolean("primes.miniheapdump.isCalibrated", true).apply();
                    obj1 = new PrimesHeapDumpCalibrationStatus();
                    obj1.newSamplePercentile = Float.valueOf((float)((MiniHeapDumpMetricService) (obj)).miniHeapDumpSampler.calculateQuantile(i));
                    ((MiniHeapDumpMetricService) (obj)).recordStatus(((PrimesHeapDumpCalibrationStatus) (obj1)));
                }
            }

            _cls1()
            {
                this$1 = MiniHeapDumpMetricService._cls1.this;
                super();
            }
        }

        futureMemoryCollectionTask = ((ScheduledExecutorService)executorServiceSupplier.get()).schedule(new _cls1(), 10L, TimeUnit.SECONDS);
    }

    _cls1()
    {
        this$0 = MiniHeapDumpMetricService.this;
        super();
    }
}
