// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.os.SystemClock;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesApi, TimerEvent, ConfiguredPrimesApi

final class PreInitPrimesApi
    implements PrimesApi
{

    public volatile ConfiguredPrimesApi initializedPrimesApi;
    public final Queue scheduledApiCalls = new ConcurrentLinkedQueue();

    PreInitPrimesApi()
    {
    }

    private final void schedule(ScheduledApiCall scheduledapicall)
    {
        Queue queue = scheduledApiCalls;
        queue;
        JVM INSTR monitorenter ;
        if (initializedPrimesApi != null)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        scheduledApiCalls.add(scheduledapicall);
_L2:
        return;
        scheduledapicall.callApi(initializedPrimesApi);
        if (true) goto _L2; else goto _L1
_L1:
        scheduledapicall;
        queue;
        JVM INSTR monitorexit ;
        throw scheduledapicall;
    }

    final void flushQueue(ConfiguredPrimesApi configuredprimesapi)
    {
        for (ScheduledApiCall scheduledapicall = (ScheduledApiCall)scheduledApiCalls.poll(); scheduledapicall != null; scheduledapicall = (ScheduledApiCall)scheduledApiCalls.poll())
        {
            scheduledapicall.callApi(configuredprimesapi);
        }

    }

    public final void recordMemory(final String customEventName, final boolean isEventNameConstant)
    {
        schedule(new _cls3());
    }

    public final void shutdown()
    {
        scheduledApiCalls.clear();
    }

    public final void startMemoryMonitor()
    {
        schedule(new _cls2());
    }

    public final TimerEvent startTimer()
    {
        return TimerEvent.EMPTY_TIMER;
    }

    public final void stopTimer$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK7D66KOBMC4NMOOBECSNL6T3ID5N6EEQQ9HM6UPRJ5TO74RRKDSNNEQBICLM6ASRJ5TO6ASJ6DTP6QOBECDIIURBFC9KMOP9FDPGMSRPF9LIN8SJ9CD2NGT35DPPMIRRE7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK4HA6IRB5E99N8OBKELPJMAAM0(final TimerEvent event, final String customEventName, final boolean isEventNameConstant, final MetricExtension metricExtension, final int timerStatus$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0)
    {
        if (event == null || event == TimerEvent.EMPTY_TIMER)
        {
            return;
        } else
        {
            event.endMs = SystemClock.elapsedRealtime();
            schedule(new _cls9());
            return;
        }
    }

    public final Thread.UncaughtExceptionHandler wrapCrashReportingIntoUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
    {
        uncaughtexceptionhandler = new EarlyUncaughtExceptionHandler(uncaughtexceptionhandler);
        schedule(uncaughtexceptionhandler);
        return uncaughtexceptionhandler;
    }

    private class ScheduledApiCall
    {

        public abstract void callApi(ConfiguredPrimesApi configuredprimesapi);
    }


    private class _cls3
        implements ScheduledApiCall
    {

        private final String val$customEventName;
        private final boolean val$isEventNameConstant;
        private final MetricExtension val$metricExtension;
        private final PrimesScenario val$primesScenario;

        public final void callApi(ConfiguredPrimesApi configuredprimesapi)
        {
            configuredprimesapi.recordMemory(customEventName, isEventNameConstant, metricExtension, primesScenario);
        }

        _cls3()
        {
            customEventName = s;
            isEventNameConstant = flag;
            metricExtension = metricextension;
            primesScenario = primesscenario;
            super();
        }
    }


    private class _cls2
        implements ScheduledApiCall
    {

        public final void callApi(ConfiguredPrimesApi configuredprimesapi)
        {
            if (configuredprimesapi.lazyServices.configs.memoryConfigurations().enabled)
            {
                configuredprimesapi.lazyServices.memoryMetricService().startMonitoring();
            }
        }

        _cls2()
        {
        }
    }


    private class _cls9
        implements ScheduledApiCall
    {

        private final String val$customEventName;
        private final TimerEvent val$event;
        private final boolean val$isEventNameConstant;
        private final MetricExtension val$metricExtension;
        private final int val$timerStatus$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0;

        public final void callApi(ConfiguredPrimesApi configuredprimesapi)
        {
            configuredprimesapi.stopTimer$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK7D66KOBMC4NMOOBECSNL6T3ID5N6EEQQ9HM6UPRJ5TO74RRKDSNNEQBICLM6ASRJ5TO6ASJ6DTP6QOBECDIIURBFC9KMOP9FDPGMSRPF9LIN8SJ9CD2NGT35DPPMIRRE7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK4HA6IRB5E99N8OBKELPJMAAM0(event, customEventName, isEventNameConstant, metricExtension, timerStatus$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0);
        }

        _cls9()
        {
            event = timerevent;
            customEventName = s;
            isEventNameConstant = flag;
            metricExtension = metricextension;
            timerStatus$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0 = i;
            super();
        }
    }


    private class EarlyUncaughtExceptionHandler
        implements ScheduledApiCall, Thread.UncaughtExceptionHandler
    {

        private volatile PrimesApi initializedPrimesApi;
        private final Thread.UncaughtExceptionHandler prevHandler;

        public final void callApi(ConfiguredPrimesApi configuredprimesapi)
        {
            initializedPrimesApi = configuredprimesapi;
        }

        public final void uncaughtException(Thread thread, Throwable throwable)
        {
            if (initializedPrimesApi == null)
            {
                try
                {
                    Thread.sleep(100L);
                }
                catch (InterruptedException interruptedexception)
                {
                    String s = "Wait for initialization is interrupted";
                    Object aobj[] = new Object[0];
                    if (Log.isLoggable("Primes", 5))
                    {
                        if (aobj.length != 0)
                        {
                            s = String.format(Locale.US, "Wait for initialization is interrupted", aobj);
                        }
                        Log.println(5, "Primes", s);
                    }
                    Thread.currentThread().interrupt();
                }
            }
            if (initializedPrimesApi != null)
            {
                initializedPrimesApi.wrapCrashReportingIntoUncaughtExceptionHandler(prevHandler).uncaughtException(thread, throwable);
            } else
            if (prevHandler != null)
            {
                prevHandler.uncaughtException(thread, throwable);
                return;
            }
        }

        EarlyUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
        {
            prevHandler = uncaughtexceptionhandler;
        }
    }

}
