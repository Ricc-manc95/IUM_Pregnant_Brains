// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.ArrayList;
import logs.proto.wireless.performance.mobile.nano.PrimesForPrimes;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesLog

final class PrimesForPrimesLogger
{

    static PrimesForPrimes primesInitMetrics()
    {
        logs.proto.wireless.performance.mobile.nano.PrimesForPrimes.InternalTimer ainternaltimer[];
        PrimesForPrimes primesforprimes;
        try
        {
            PrimesForPrimesMeasurements.PrimesInitializationMeasurement primesinitializationmeasurement = PrimesForPrimesMeasurements.InitializationMeasurementHolder.initializationMeasurement;
            ainternaltimer = (logs.proto.wireless.performance.mobile.nano.PrimesForPrimes.InternalTimer[])(new TimerBuilder()).addTimer(1, primesinitializationmeasurement.primesInitStart, primesinitializationmeasurement.primesInitEnd).addTimer(2, primesinitializationmeasurement.primesInitStart, primesinitializationmeasurement.primesShutdownInitialized).addTimer(3, primesinitializationmeasurement.primesShutdownInitialized, primesinitializationmeasurement.primesConfigsCreated).addTimer(4, primesinitializationmeasurement.primesConfigsCreated, primesinitializationmeasurement.primesFlagsCreated).timers.toArray(new logs.proto.wireless.performance.mobile.nano.PrimesForPrimes.InternalTimer[0]);
        }
        catch (RuntimeException runtimeexception)
        {
            PrimesLog.log(5, "PrimesForPrimes", runtimeexception, "Exception getting Primes Init timers", new Object[0]);
            return null;
        }
        if (ainternaltimer == null)
        {
            break MISSING_BLOCK_LABEL_116;
        }
        if (ainternaltimer.length == 0)
        {
            break MISSING_BLOCK_LABEL_116;
        }
        primesforprimes = new PrimesForPrimes();
        primesforprimes.internalTimers = ainternaltimer;
        return primesforprimes;
        return null;
    }

    private class TimerBuilder
    {

        public final ArrayList timers = new ArrayList();

        final TimerBuilder addTimer(int i, CpuWallTime cpuwalltime, CpuWallTime cpuwalltime1)
        {
            if (cpuwalltime != null && cpuwalltime1 != null)
            {
                cpuwalltime = new CpuWallTime(cpuwalltime1.wallNanos - cpuwalltime.wallNanos, cpuwalltime1.cpuNanos - cpuwalltime.cpuNanos);
                cpuwalltime1 = new logs.proto.wireless.performance.mobile.nano.PrimesForPrimes.InternalTimer();
                cpuwalltime1.duration = new MicroCpuTime();
                ((logs.proto.wireless.performance.mobile.nano.PrimesForPrimes.InternalTimer) (cpuwalltime1)).duration.cpuMicros = Long.valueOf(cpuwalltime.cpuNanos / 1000L);
                ((logs.proto.wireless.performance.mobile.nano.PrimesForPrimes.InternalTimer) (cpuwalltime1)).duration.wallMicros = Long.valueOf(cpuwalltime.wallNanos / 1000L);
                cpuwalltime1.primesForPrimesEvent = i;
                timers.add(cpuwalltime1);
            }
            return this;
        }

        TimerBuilder()
        {
        }
    }

}
