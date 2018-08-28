// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.concurrent.ScheduledExecutorService;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            ShutdownListener, MetricRecorder, Supplier

abstract class AbstractMetricService
    implements ShutdownListener
{

    public final Application application;
    public final Supplier executorServiceSupplier;
    public final MetricRecorder metricRecorder;
    public volatile boolean shutdown;

    protected AbstractMetricService(MetricTransmitter metrictransmitter, Application application1, Supplier supplier, Supplier supplier1, int i)
    {
        this(metrictransmitter, application1, supplier, supplier1, i, 0x7fffffff);
    }

    protected AbstractMetricService(MetricTransmitter metrictransmitter, Application application1, Supplier supplier, Supplier supplier1, int i, int j)
    {
        if (metrictransmitter == null)
        {
            throw new NullPointerException();
        }
        if (application1 == null)
        {
            throw new NullPointerException();
        } else
        {
            application = application1;
            executorServiceSupplier = supplier1;
            metricRecorder = new MetricRecorder(metrictransmitter, supplier, supplier1, i, j);
            return;
        }
    }

    public final void onShutdown()
    {
        shutdown = true;
        shutdownService();
    }

    protected final void recordSystemHealthMetric(String s, boolean flag, SystemHealthMetric systemhealthmetric, MetricExtension metricextension)
    {
        MetricRecorder metricrecorder;
label0:
        {
            if (!shutdown)
            {
                metricrecorder = metricRecorder;
                if (metricrecorder.whereToRun$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0 != android.support.v4.content.ModernAsyncTask.Status.SAME_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0)
                {
                    break label0;
                }
                metricrecorder.recordInternal(s, flag, systemhealthmetric, metricextension);
            }
            return;
        }
        ((ScheduledExecutorService)metricrecorder.executorServiceSupplier.get()).submit(new MetricRecorder._cls1(metricrecorder, s, flag, systemhealthmetric, metricextension));
    }

    abstract void shutdownService();
}
