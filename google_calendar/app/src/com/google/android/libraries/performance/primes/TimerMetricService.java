// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import com.google.android.libraries.performance.primes.sampling.PrimesSampling;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import logs.proto.wireless.performance.mobile.nano.AccountableComponent;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;
import logs.proto.wireless.performance.mobile.nano.TimerMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, TimerEvent, PrimesLog, MetricRecorder, 
//            Supplier, PrimesPerEventConfigurationFlags

class TimerMetricService extends AbstractMetricService
{

    private static final Set reservedEventNames = new HashSet(Arrays.asList(new String[] {
        "Cold startup", "Cold startup interactive", "Cold startup interactive before onDraw", "Warm startup", "Warm startup interactive", "Warm startup interactive before onDraw", "Warm startup activity onStart"
    }));
    public final PrimesPerEventConfigurationFlags perEventConfigFlags;
    private final ConcurrentHashMap timerEvents = new ConcurrentHashMap();

    TimerMetricService(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, int i, PrimesPerEventConfigurationFlags primespereventconfigurationflags)
    {
        super(metrictransmitter, application, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.SAME_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0, i);
        perEventConfigFlags = primespereventconfigurationflags;
    }

    static SystemHealthMetric getMetric(TimerEvent timerevent, String s)
    {
        SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
        TimerMetric timermetric = new TimerMetric();
        timermetric.durationMs = Long.valueOf(timerevent.endMs - timerevent.startMs);
        timermetric.endStatus = timerevent.timerStatus$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0 - 1;
        systemhealthmetric.timerMetric = timermetric;
        if (s != null)
        {
            systemhealthmetric.accountableComponent = new AccountableComponent();
            systemhealthmetric.accountableComponent.customName = s;
        }
        return systemhealthmetric;
    }

    void recordTimer(final TimerEvent metric, final String eventName, final boolean isEventNameConstant, final MetricExtension extension)
    {
        boolean flag = true;
        if (metric == null || metric == TimerEvent.EMPTY_TIMER || eventName == null || eventName.isEmpty())
        {
            PrimesLog.log(3, "TimerMetricService", "Can't record an event that was never started or has been stopped already", new Object[0]);
        } else
        {
            if (reservedEventNames.contains(eventName))
            {
                PrimesLog.log(5, "TimerMetricService", "%s is reserved event. Dropping timer.", new Object[] {
                    eventName
                });
                return;
            }
            if (super.metricRecorder.instrumentationSampling.isSampleRateExceeded())
            {
                flag = false;
            }
            if (flag)
            {
                metric = getMetric(metric, null);
                ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new _cls1());
                return;
            }
        }
    }

    void shutdownService()
    {
        timerEvents.clear();
    }

    TimerEvent start()
    {
        boolean flag;
        if (!super.metricRecorder.instrumentationSampling.isSampleRateExceeded())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return new TimerEvent();
        } else
        {
            return TimerEvent.EMPTY_TIMER;
        }
    }


    private class _cls1
        implements Runnable
    {

        private final TimerMetricService this$0;
        private final String val$eventName;
        private final MetricExtension val$extension;
        private final boolean val$isEventNameConstant;
        private final SystemHealthMetric val$metric;

        public final void run()
        {
            perEventConfigFlags.isFlagEnabled$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9B8______0();
            recordSystemHealthMetric(eventName, isEventNameConstant, metric, extension);
        }

        _cls1()
        {
            this$0 = TimerMetricService.this;
            eventName = s;
            isEventNameConstant = flag;
            metric = systemhealthmetric;
            extension = metricextension;
            super();
        }
    }

}
