// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.libraries.performance.primes.miniheapdump.MiniHeapDumpMemorySampler;
import java.util.ArrayList;
import java.util.Locale;
import logs.proto.wireless.performance.mobile.nano.PrimesHeapDumpCalibrationStatus;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            MiniHeapDumpMetricService, AppLifecycleMonitor

final class this._cls1
    implements Runnable
{

    private final rdStatus this$1;

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

    ySampler()
    {
        this$1 = this._cls1.this;
        super();
    }
}
