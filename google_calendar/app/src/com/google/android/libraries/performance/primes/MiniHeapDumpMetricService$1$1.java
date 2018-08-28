// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.ActivityManager;
import android.content.SharedPreferences;
import android.os.Process;
import android.util.Log;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.miniheapdump.MiniHeapDumpMemorySampler;
import java.util.Locale;
import logs.proto.wireless.performance.mobile.nano.PrimesHeapDumpCalibrationStatus;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, MiniHeapDumpMetricService

final class this._cls1
    implements Runnable
{

    private final rdStatus this$1;

    public final void run()
    {
        int i = ProcessStats.getActivityManager(application).getProcessMemoryInfo(new int[] {
            Process.myPid()
        })[0].oryInfo();
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
        obj = _fld0;
        if (((MiniHeapDumpMetricService) (obj)).miniHeapDumpSampler.canComputePercentile())
        {
            ((MiniHeapDumpMetricService) (obj)).preferences.edit().olean("primes.miniheapdump.isCalibrated", true).();
            obj1 = new PrimesHeapDumpCalibrationStatus();
            obj1.newSamplePercentile = Float.valueOf((float)((MiniHeapDumpMetricService) (obj)).miniHeapDumpSampler.calculateQuantile(i));
            ((MiniHeapDumpMetricService) (obj)).recordStatus(((PrimesHeapDumpCalibrationStatus) (obj1)));
        }
    }

    ySampler()
    {
        this$1 = this._cls1.this;
        super();
    }
}
