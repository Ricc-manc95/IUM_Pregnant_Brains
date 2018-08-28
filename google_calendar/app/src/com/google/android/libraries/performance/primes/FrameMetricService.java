// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.FrameMetrics;
import android.view.Window;
import com.google.android.libraries.performance.primes.jank.FrameTimeMeasurement;
import com.google.android.libraries.performance.primes.jank.FrameTimeMeasurementFactory;
import com.google.android.libraries.performance.primes.metriccapture.DisplayStats;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, PrimesStartupListener, AppLifecycleMonitor, JankMetricExtensionProvider, 
//            Supplier, PrimesLog, WithAccountableName, NoPiiString

final class FrameMetricService extends AbstractMetricService
    implements AppLifecycleListener.OnAppToBackground, PrimesStartupListener
{
    static final class ActivityTracker
        implements android.view.Window.OnFrameMetricsAvailableListener, AppLifecycleListener.OnActivityPaused, AppLifecycleListener.OnActivityResumed
    {

        private final FrameMetricCallback callback;
        public Activity currentActivity;
        public Handler handler;
        public HandlerThread handlerThread;
        public boolean measuring;
        private final boolean monitorActivities;

        private final void attachToCurrentActivity()
        {
            if (currentActivity != null)
            {
                Window window = currentActivity.getWindow();
                if (handler == null)
                {
                    handlerThread = new HandlerThread("Primes-Jank");
                    handlerThread.start();
                    handler = new Handler(handlerThread.getLooper());
                }
                window.addOnFrameMetricsAvailableListener(this, handler);
            }
        }

        final void detachFromCurrentActivity()
        {
            if (currentActivity == null)
            {
                break MISSING_BLOCK_LABEL_18;
            }
            currentActivity.getWindow().removeOnFrameMetricsAvailableListener(this);
            return;
            RuntimeException runtimeexception;
            runtimeexception;
            PrimesLog.log(3, "FrameMetricService", runtimeexception, "remove frame metrics listener failed", new Object[0]);
            return;
        }

        public final void onActivityPaused(Activity activity)
        {
            Object obj = null;
            this;
            JVM INSTR monitorenter ;
            if (measuring)
            {
                detachFromCurrentActivity();
            }
            currentActivity = null;
            this;
            JVM INSTR monitorexit ;
            if (monitorActivities)
            {
                FrameMetricCallback framemetriccallback = callback;
                if (activity instanceof WithAccountableName)
                {
                    activity = ((WithAccountableName)activity).getAccountableName();
                    if (activity == null)
                    {
                        activity = obj;
                    } else
                    {
                        activity = activity.toString();
                    }
                } else
                {
                    activity = activity.getClass().getName();
                }
                framemetriccallback.activityPaused(activity);
            }
            return;
            activity;
            this;
            JVM INSTR monitorexit ;
            throw activity;
        }

        public final void onActivityResumed(Activity activity)
        {
            if (monitorActivities)
            {
                FrameMetricCallback framemetriccallback = callback;
                Object obj;
                if (activity instanceof WithAccountableName)
                {
                    obj = ((WithAccountableName)activity).getAccountableName();
                    if (obj == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = ((NoPiiString) (obj)).toString();
                    }
                } else
                {
                    obj = activity.getClass().getName();
                }
                framemetriccallback.activityResumed(((String) (obj)));
            }
            this;
            JVM INSTR monitorenter ;
            currentActivity = activity;
            if (measuring)
            {
                attachToCurrentActivity();
            }
            this;
            JVM INSTR monitorexit ;
            return;
            activity;
            this;
            JVM INSTR monitorexit ;
            throw activity;
        }

        public final void onFrameMetricsAvailable(Window window, FrameMetrics framemetrics, int i)
        {
            i = (int)((double)framemetrics.getMetric(8) / 1000000D);
            callback.frameRendered(i);
        }

        final void stopCollecting()
        {
            this;
            JVM INSTR monitorenter ;
            measuring = false;
            detachFromCurrentActivity();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        ActivityTracker(FrameMetricCallback framemetriccallback, boolean flag)
        {
            callback = framemetriccallback;
            monitorActivities = flag;
            if (flag)
            {
                measuring = true;
            }
        }
    }

    static interface FrameMetricCallback
    {

        public abstract void activityPaused(String s);

        public abstract void activityResumed(String s);

        public abstract void frameRendered(int i);
    }


    public final ActivityTracker activityTracker;
    private final AppLifecycleMonitor appLifecycleMonitor;
    public final FrameTimeMeasurementFactory frameTimeMeasurementFactory;
    private final int maxAcceptedFrameTimeMs;
    public final Map measurements = new HashMap();
    public final JankMetricExtensionProvider metricExtensionProvider;
    public final boolean monitorActivities;

    FrameMetricService(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, boolean flag, int i, FrameTimeMeasurementFactory frametimemeasurementfactory, 
            final JankMetricExtensionProvider metricExtensionProvider)
    {
        super(metrictransmitter, application, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.BACKGROUND_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0, i);
        appLifecycleMonitor = AppLifecycleMonitor.getInstance(application);
        monitorActivities = flag;
        if (frametimemeasurementfactory == null)
        {
            throw new NullPointerException();
        } else
        {
            frameTimeMeasurementFactory = (FrameTimeMeasurementFactory)frametimemeasurementfactory;
            this.metricExtensionProvider = metricExtensionProvider;
            maxAcceptedFrameTimeMs = DisplayStats.maxAcceptedFrameRenderTimeMs(application);
            activityTracker = new ActivityTracker(new _cls1(), flag);
            appLifecycleMonitor.register(activityTracker);
            return;
        }
    }

    public final void onAppToBackground(Activity activity)
    {
        synchronized (measurements)
        {
            measurements.clear();
        }
        return;
        exception;
        activity;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void onFirstActivityCreated()
    {
    }

    public final void onPrimesInitialize()
    {
    }

    final void recordMeasurement(int i)
    {
        Map map = measurements;
        map;
        JVM INSTR monitorenter ;
        for (Iterator iterator = measurements.values().iterator(); iterator.hasNext(); ((FrameTimeMeasurement)iterator.next()).addFrame(i, maxAcceptedFrameTimeMs)) { }
        break MISSING_BLOCK_LABEL_58;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
        map;
        JVM INSTR monitorexit ;
    }

    final void shutdownService()
    {
        appLifecycleMonitor.unregister(activityTracker);
        synchronized (activityTracker)
        {
            ((ActivityTracker) (obj)).stopCollecting();
            if (((ActivityTracker) (obj)).handler != null)
            {
                ((ActivityTracker) (obj)).handlerThread.quitSafely();
                obj.handlerThread = null;
                obj.handler = null;
            }
        }
        synchronized (measurements)
        {
            measurements.clear();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    private class _cls1
        implements FrameMetricCallback
    {

        private final FrameMetricService this$0;
        private final JankMetricExtensionProvider val$metricExtensionProvider;

        public final void activityPaused(String s)
        {
            FrameMetricService framemetricservice;
            Object obj1;
            framemetricservice = FrameMetricService.this;
            obj1 = metricExtensionProvider.getMetricExtension();
            Object obj = framemetricservice.measurements;
            obj;
            JVM INSTR monitorenter ;
            FrameTimeMeasurement frametimemeasurement = (FrameTimeMeasurement)framemetricservice.measurements.remove(s);
            if (framemetricservice.measurements.isEmpty() && !framemetricservice.monitorActivities)
            {
                synchronized (framemetricservice.activityTracker)
                {
                    activitytracker.measuring = false;
                    activitytracker.detachFromCurrentActivity();
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
            activitytracker;
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
            if (((ActivityTracker) (obj)).currentActivity == null) goto _L15; else goto _L14
_L14:
            if (((ActivityTracker) (obj)).currentActivity != null)
            {
                s = ((ActivityTracker) (obj)).currentActivity.getWindow();
                if (((ActivityTracker) (obj)).handler == null)
                {
                    obj.handlerThread = new HandlerThread("Primes-Jank");
                    ((ActivityTracker) (obj)).handlerThread.start();
                    obj.handler = new Handler(((ActivityTracker) (obj)).handlerThread.getLooper());
                }
                s.addOnFrameMetricsAvailableListener(((android.view.Window.OnFrameMetricsAvailableListener) (obj)), ((ActivityTracker) (obj)).handler);
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

        _cls1()
        {
            this$0 = FrameMetricService.this;
            metricExtensionProvider = jankmetricextensionprovider;
            super();
        }
    }

}
