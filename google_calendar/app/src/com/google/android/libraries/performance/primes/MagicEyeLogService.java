// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, PrimesStartupListener, AppLifecycleMonitor, Supplier

final class MagicEyeLogService extends AbstractMetricService
    implements AppLifecycleListener.OnAppToBackground, AppLifecycleListener.OnAppToForeground, PrimesStartupListener
{

    private boolean monitoring;

    MagicEyeLogService(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1)
    {
        super(metrictransmitter, application, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.SAME_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0);
    }

    private final void startMonitoring()
    {
        this;
        JVM INSTR monitorenter ;
        if (!monitoring && !super.shutdown)
        {
            AppLifecycleMonitor.getInstance(super.application).register(this);
            monitoring = true;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private final void stopMonitoring()
    {
        this;
        JVM INSTR monitorenter ;
        if (monitoring)
        {
            AppLifecycleMonitor.getInstance(super.application).unregister(this);
            monitoring = false;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void onAppToBackground(Activity activity)
    {
        ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new _cls1());
        activity = "Logging APP_TO_BACKGROUND";
        Object aobj[] = new Object[0];
        if (Log.isLoggable("MagicEyeLogService", 3))
        {
            if (aobj.length != 0)
            {
                activity = String.format(Locale.US, "Logging APP_TO_BACKGROUND", aobj);
            }
            Log.println(3, "MagicEyeLogService", activity);
        }
    }

    public final void onAppToForeground(Activity activity)
    {
        ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new _cls1());
        activity = "Logging APP_TO_FOREGROUND";
        Object aobj[] = new Object[0];
        if (Log.isLoggable("MagicEyeLogService", 3))
        {
            if (aobj.length != 0)
            {
                activity = String.format(Locale.US, "Logging APP_TO_FOREGROUND", aobj);
            }
            Log.println(3, "MagicEyeLogService", activity);
        }
    }

    public final void onFirstActivityCreated()
    {
    }

    public final void onPrimesInitialize()
    {
        startMonitoring();
    }

    final void shutdownService()
    {
        stopMonitoring();
    }

    private class _cls1
        implements Runnable
    {

        private final MagicEyeLogService this$0;
        private final int val$appStateEvent;

        public final void run()
        {
            SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
            systemhealthmetric.magicEyeMetric = new MagicEyeMetric();
            systemhealthmetric.magicEyeMetric.appStateEvent = appStateEvent;
            recordSystemHealthMetric(null, true, systemhealthmetric, null);
        }

        _cls1()
        {
            this$0 = MagicEyeLogService.this;
            appStateEvent = i;
            super();
        }
    }

}
