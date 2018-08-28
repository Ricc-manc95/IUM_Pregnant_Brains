// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import logs.proto.wireless.performance.mobile.nano.MetricExtension;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesApi, TimerEvent

final class NoopPrimesApi
    implements PrimesApi
{

    NoopPrimesApi()
    {
    }

    public final void recordMemory(String s, boolean flag)
    {
    }

    public final void shutdown()
    {
    }

    public final void startMemoryMonitor()
    {
    }

    public final TimerEvent startTimer()
    {
        return TimerEvent.EMPTY_TIMER;
    }

    public final void stopTimer$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK7D66KOBMC4NMOOBECSNL6T3ID5N6EEQQ9HM6UPRJ5TO74RRKDSNNEQBICLM6ASRJ5TO6ASJ6DTP6QOBECDIIURBFC9KMOP9FDPGMSRPF9LIN8SJ9CD2NGT35DPPMIRRE7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK4HA6IRB5E99N8OBKELPJMAAM0(TimerEvent timerevent, String s, boolean flag, MetricExtension metricextension, int i)
    {
    }

    public final Thread.UncaughtExceptionHandler wrapCrashReportingIntoUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
    {
        return uncaughtexceptionhandler;
    }

    static 
    {
        new _cls1();
    }

    private class _cls1
        implements MetricTransmitter
    {

        public final void send(SystemHealthMetric systemhealthmetric)
        {
        }

        _cls1()
        {
        }
    }

}
