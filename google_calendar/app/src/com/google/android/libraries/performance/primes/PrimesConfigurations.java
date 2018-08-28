// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesBatteryConfigurations, PrimesCpuConfigurations, PrimesCrashConfigurations, PrimesGlobalConfigurations, 
//            PrimesJankConfigurations, PrimesMemoryConfigurations, PrimesMemoryLeakConfigurations, PrimesMiniHeapDumpConfigurations, 
//            PrimesPackageConfigurations, PrimesTraceConfigurations, PrimesTikTokTraceConfigurations, PrimesTimerConfigurations

public class PrimesConfigurations
{

    public PrimesConfigurations()
    {
    }

    public PrimesBatteryConfigurations batteryConfigurations()
    {
        return PrimesBatteryConfigurations.DEFAULT;
    }

    public PrimesCpuConfigurations cpuConfigurations()
    {
        return PrimesCpuConfigurations.DEFAULT;
    }

    public PrimesCrashConfigurations crashConfigurations()
    {
        return PrimesCrashConfigurations.DEFAULT;
    }

    public PrimesGlobalConfigurations globalConfigurations()
    {
        return PrimesGlobalConfigurations.DEFAULT;
    }

    public PrimesJankConfigurations jankConfigurations()
    {
        return PrimesJankConfigurations.DEFAULT;
    }

    public PrimesMemoryConfigurations memoryConfigurations()
    {
        return PrimesMemoryConfigurations.DEFAULT;
    }

    public PrimesMemoryLeakConfigurations memoryLeakConfigurations()
    {
        return PrimesMemoryLeakConfigurations.DEFAULT;
    }

    public MetricTransmitter metricTransmitter()
    {
        return MetricTransmitter.NOOP_TRANSMITTER;
    }

    public PrimesMiniHeapDumpConfigurations miniHeapDumpConfigurations()
    {
        return PrimesMiniHeapDumpConfigurations.DEFAULT;
    }

    public PrimesPackageConfigurations packageConfigurations()
    {
        return PrimesPackageConfigurations.DEFAULT;
    }

    public PrimesTraceConfigurations primesTraceConfigurations()
    {
        return PrimesTraceConfigurations.DEFAULT;
    }

    public PrimesTikTokTraceConfigurations tiktokTraceConfigurations()
    {
        return PrimesTikTokTraceConfigurations.DEFAULT;
    }

    public PrimesTimerConfigurations timerConfigurations()
    {
        return PrimesTimerConfigurations.DEFAULT;
    }
}
