// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Window;
import com.google.android.libraries.performance.primes.jank.FrameTimeMeasurement;
import com.google.android.libraries.performance.primes.jank.FrameTimeMeasurementFactory;
import com.google.android.libraries.performance.primes.metriccapture.DisplayStats;
import java.util.Locale;
import java.util.Map;
import logs.proto.wireless.performance.mobile.nano.JankMetric;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            JankMetricExtensionProvider, FrameMetricService, AbstractMetricService, PrimesLog

final class ovider
    implements ameMetricCallback
{

    private final FrameMetricService this$0;
    private final JankMetricExtensionProvider val$metricExtensionProvider;

    public final void activityPaused(String s)
    {
        FrameMetricService framemetricservice;
        Object obj1;
        framemetricservice = FrameMetricService.this;
        obj1 = val$metricExtensionProvider.getMetricExtension();
        Object obj = framemetricservice.measurements;
        obj;
        JVM INSTR monitorenter ;
        FrameTimeMeasurement frametimemeasurement = (FrameTimeMeasurement)framemetricservice.measurements.remove(s);
        if (framemetricservice.measurements.isEmpty() && !framemetricservice.monitorActivities)
        {
            synchronized (framemetricservice.activityTracker)
            {
                tivitytracker.measuring = false;
                tivitytracker.detachFromCurrentActivity();
            }
        }
        obj;
        JVM INSTR monitorexit ;
        if (frametimemeasurement != null)
        {
            if (frametimemeasurement.isMetricReadyToBeSent())
            {
                obj = new SystemHealthMetric();
                obj.jankMetric = frametimemeasurement.getMetric();
                ((SystemHealthMetric) (obj)).jankMetric.deviceRefreshRate = Integer.valueOf(DisplayStats.getRefreshRate(((AbstractMetricService) (framemetricservice)).application));
                if (obj1 == null && framemetricservice.metricExtensionProvider != null)
                {
                    try
                    {
                        obj.metricExtension = framemetricservice.metricExtensionProvider.getMetricExtension();
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj1)
                    {
                        PrimesLog.log(5, "FrameMetricService", ((Throwable) (obj1)), "Exception while getting jank metric extension!", new Object[0]);
                    }
                } else
                {
                    obj.metricExtension = ((logs.proto.wireless.performance.mobile.nano.MetricExtension) (obj1));
                }
                framemetricservice.recordSystemHealthMetric(s, true, ((SystemHealthMetric) (obj)), null);
            }
        } else
        {
            String s1 = "Measurement not found: %s";
            Object aobj[] = new Object[1];
            aobj[0] = s;
            if (Log.isLoggable("FrameMetricService", 5))
            {
                if (aobj.length == 0)
                {
                    s = s1;
                } else
                {
                    s = String.format(Locale.US, "Measurement not found: %s", aobj);
                }
                Log.println(5, "FrameMetricService", s);
                return;
            }
        }
        return;
        s;
        tivitytracker;
        JVM INSTR monitorexit ;
        throw s;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
    }

    public final void activityResumed(String s)
    {
        Object obj = FrameMetricService.this;
        Map map = ((FrameMetricService) (obj)).measurements;
        map;
        JVM INSTR monitorenter ;
        if (!((FrameMetricService) (obj)).measurements.containsKey(s))
        {
            break MISSING_BLOCK_LABEL_81;
        }
        obj = "measurement already started: %s";
        Object aobj[] = new Object[1];
        aobj[0] = s;
        if (!Log.isLoggable("FrameMetricService", 5)) goto _L2; else goto _L1
_L1:
        if (aobj.length != 0)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        s = ((String) (obj));
_L4:
        Log.println(5, "FrameMetricService", s);
_L2:
        map;
        JVM INSTR monitorexit ;
        return;
        s = String.format(Locale.US, "measurement already started: %s", aobj);
        if (true) goto _L4; else goto _L3
_L3:
        if (((FrameMetricService) (obj)).measurements.size() < 25)
        {
            break MISSING_BLOCK_LABEL_156;
        }
        obj = "Too many concurrent measurements, ignoring %s";
        aobj = new Object[1];
        aobj[0] = s;
        if (!Log.isLoggable("FrameMetricService", 5)) goto _L6; else goto _L5
_L5:
        if (aobj.length != 0)
        {
            break MISSING_BLOCK_LABEL_142;
        }
        s = ((String) (obj));
_L7:
        Log.println(5, "FrameMetricService", s);
_L6:
        map;
        JVM INSTR monitorexit ;
        return;
        s;
        map;
        JVM INSTR monitorexit ;
        throw s;
        s = String.format(Locale.US, "Too many concurrent measurements, ignoring %s", aobj);
          goto _L7
        ((FrameMetricService) (obj)).measurements.put(s, ((FrameMetricService) (obj)).frameTimeMeasurementFactory.newMeasurement$5166KOBMC4NMOOBECSNL6T3ID5N6EEP99HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRAC5N6MBQ6E9GMQPAKD5MMAJB5C5PNASJ5DLIMST1R0());
        if (((FrameMetricService) (obj)).measurements.size() != 1 || ((FrameMetricService) (obj)).monitorActivities) goto _L9; else goto _L8
_L8:
        s = "measuring start";
        aobj = new Object[0];
        if (!Log.isLoggable("FrameMetricService", 3)) goto _L11; else goto _L10
_L10:
        if (aobj.length != 0) goto _L13; else goto _L12
_L12:
        Log.println(3, "FrameMetricService", s);
_L11:
        obj = ((FrameMetricService) (obj)).activityTracker;
        obj;
        JVM INSTR monitorenter ;
        obj.measuring = true;
        if (((tivityTracker) (obj)).currentActivity == null) goto _L15; else goto _L14
_L14:
        if (((tivityTracker) (obj)).currentActivity != null)
        {
            s = ((tivityTracker) (obj)).currentActivity.getWindow();
            if (((tivityTracker) (obj)).handler == null)
            {
                obj.handlerThread = new HandlerThread("Primes-Jank");
                ((tivityTracker) (obj)).handlerThread.start();
                obj.handler = new Handler(((tivityTracker) (obj)).handlerThread.getLooper());
            }
            s.addOnFrameMetricsAvailableListener(((android.view.AvailableListener) (obj)), ((tivityTracker) (obj)).handler);
        }
_L9:
        map;
        JVM INSTR monitorexit ;
        return;
_L13:
        s = String.format(Locale.US, "measuring start", aobj);
          goto _L12
_L15:
        s = "No activity";
        aobj = new Object[0];
        if (!Log.isLoggable("FrameMetricService", 3)) goto _L9; else goto _L16
_L16:
        if (aobj.length != 0)
        {
            break MISSING_BLOCK_LABEL_375;
        }
_L17:
        Log.println(3, "FrameMetricService", s);
          goto _L9
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
        s = String.format(Locale.US, "No activity", aobj);
          goto _L17
    }

    public final void frameRendered(int i)
    {
        recordMeasurement(i);
    }

    ovider()
    {
        this$0 = final_framemetricservice;
        val$metricExtensionProvider = JankMetricExtensionProvider.this;
        super();
    }
}
