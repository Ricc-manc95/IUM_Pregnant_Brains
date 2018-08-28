// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Process;
import android.util.Log;
import com.google.android.libraries.performance.primes.metriccapture.MemoryUsageCapture;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.PrimesScenario;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            MemoryMetricService, MemoryMetricExtensionProvider, AbstractMetricService

final class val.primesScenario
    implements Runnable
{

    private final MemoryMetricService this$0;
    private final String val$activityName;
    private final String val$customEventName;
    private final int val$eventCode;
    private final boolean val$isEventNameConstant;
    private final MetricExtension val$metricExtension;
    private final PrimesScenario val$primesScenario;

    public final void run()
    {
        Object obj1;
        if (forceGcBeforeRecordMemory)
        {
            System.gc();
            System.runFinalization();
            System.gc();
        }
        obj1 = val$metricExtension;
        if (val$metricExtension != null || metricExtensionProvider == null)
        {
            break MISSING_BLOCK_LABEL_276;
        }
        Object obj = metricExtensionProvider.getMetricExtension(val$customEventName, val$eventCode);
_L1:
        Object aobj[];
        if (recordMemoryPerProcess)
        {
            obj1 = MemoryMetricService.this;
            String s = val$customEventName;
            boolean flag = val$isEventNameConstant;
            int i = val$eventCode;
            String s2 = val$activityName;
            PrimesScenario primesscenario = val$primesScenario;
            Object obj3 = ProcessStats.getActivityManager(((AbstractMetricService) (obj1)).application).getRunningAppProcesses();
            if (obj3 != null)
            {
                String s4 = ((AbstractMetricService) (obj1)).application.getPackageName();
                obj3 = ((List) (obj3)).iterator();
                do
                {
                    if (!((Iterator) (obj3)).hasNext())
                    {
                        break;
                    }
                    android.app.gAppProcessInfo gappprocessinfo = (android.app.gAppProcessInfo)((Iterator) (obj3)).next();
                    if (android.os.ngAppProcessInfo > 22 || gappprocessinfo.processName.contains(s4))
                    {
                        SystemHealthMetric systemhealthmetric1 = new SystemHealthMetric();
                        systemhealthmetric1.primesScenario = primesscenario;
                        systemhealthmetric1.memoryUsageMetric = MemoryUsageCapture.getMemoryUsageMetric(i, gappprocessinfo.pid, gappprocessinfo.processName, ((AbstractMetricService) (obj1)).application, s2, ((MemoryMetricService) (obj1)).memorySummaryDisabled);
                        ((AbstractMetricService) (obj1)).recordSystemHealthMetric(s, flag, systemhealthmetric1, ((MetricExtension) (obj)));
                    }
                } while (true);
            }
        } else
        {
            MemoryMetricService memorymetricservice = MemoryMetricService.this;
            String s1 = val$customEventName;
            boolean flag1 = val$isEventNameConstant;
            int j = val$eventCode;
            String s3 = val$activityName;
            Object obj2 = val$primesScenario;
            SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
            systemhealthmetric.primesScenario = ((PrimesScenario) (obj2));
            obj2 = ((AbstractMetricService) (memorymetricservice)).application;
            boolean flag2 = memorymetricservice.memorySummaryDisabled;
            systemhealthmetric.memoryUsageMetric = MemoryUsageCapture.getMemoryUsageMetric(j, Process.myPid(), null, ((android.content.Context) (obj2)), s3, flag2);
            memorymetricservice.recordSystemHealthMetric(s1, flag1, systemhealthmetric, ((MetricExtension) (obj)));
        }
        break MISSING_BLOCK_LABEL_386;
        obj;
        obj = "Metric metric extension provider failed.";
        aobj = new Object[0];
        if (Log.isLoggable("MemoryMetricService", 6))
        {
            if (aobj.length != 0)
            {
                obj = String.format(Locale.US, "Metric metric extension provider failed.", aobj);
            }
            Log.println(6, "MemoryMetricService", ((String) (obj)));
        }
        obj = obj1;
          goto _L1
    }

    ageCapture()
    {
        this$0 = final_memorymetricservice;
        val$metricExtension = metricextension;
        val$customEventName = s;
        val$eventCode = i;
        val$isEventNameConstant = flag;
        val$activityName = s1;
        val$primesScenario = PrimesScenario.this;
        super();
    }
}
