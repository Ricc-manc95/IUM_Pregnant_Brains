// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            MiniHeapDumpMetricService, Supplier

final class this._cls0
    implements Background
{

    public final MiniHeapDumpMetricService this$0;

    public final void onAppToBackground(Activity activity)
    {
        class _cls1
            implements Runnable
        {

            private final MiniHeapDumpMetricService._cls2 this$1;

            public final void run()
            {
                appLifecycleMonitor.unregister(logTotalPssSampleCount);
                if (preferences.getBoolean("primes.miniheapdump.isCalibrated", false))
                {
                    return;
                }
                Object obj = "Logging calibration status";
                Object aobj[] = new Object[0];
                if (Log.isLoggable("MiniHeapDumpMetric", 3))
                {
                    if (aobj.length != 0)
                    {
                        obj = String.format(Locale.US, "Logging calibration status", aobj);
                    }
                    Log.println(3, "MiniHeapDumpMetric", ((String) (obj)));
                }
                obj = new PrimesHeapDumpCalibrationStatus();
                obj.currentSampleCount = Integer.valueOf(miniHeapDumpSampler.samples.size());
                recordStatus(((PrimesHeapDumpCalibrationStatus) (obj)));
            }

            _cls1()
            {
                this$1 = MiniHeapDumpMetricService._cls2.this;
                super();
            }
        }

        ((ScheduledExecutorService)executorServiceSupplier.get()).submit(new _cls1());
    }

    _cls1()
    {
        this$0 = MiniHeapDumpMetricService.this;
        super();
    }
}
