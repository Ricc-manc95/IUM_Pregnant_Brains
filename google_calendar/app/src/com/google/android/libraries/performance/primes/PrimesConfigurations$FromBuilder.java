// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesConfigurations, PrimesBatteryConfigurations, PrimesCpuConfigurations, PrimesCrashConfigurations, 
//            PrimesGlobalConfigurations, PrimesJankConfigurations, PrimesMemoryConfigurations, PrimesMemoryLeakConfigurations, 
//            PrimesMiniHeapDumpConfigurations, PrimesPackageConfigurations, PrimesTraceConfigurations, PrimesTikTokTraceConfigurations, 
//            PrimesTimerConfigurations, PrimesNetworkConfigurations, PrimesExperimentalConfigurations

public final class miniHeapDumpConfigurations extends PrimesConfigurations
{

    private final PrimesBatteryConfigurations batteryConfigurations;
    private final PrimesCpuConfigurations cpuConfigurations;
    private final PrimesCrashConfigurations crashConfigurations;
    private final PrimesGlobalConfigurations globalConfigurations;
    private final PrimesJankConfigurations jankConfigurations;
    private final PrimesMemoryConfigurations memoryConfigurations;
    private final PrimesMemoryLeakConfigurations memoryLeakConfigurations;
    private final MetricTransmitter metricTransmitter;
    private final PrimesMiniHeapDumpConfigurations miniHeapDumpConfigurations;
    private final PrimesPackageConfigurations packageConfigurations;
    private final PrimesTraceConfigurations primesTraceConfigurations;
    private final PrimesTikTokTraceConfigurations tiktokTraceConfigurations;
    private final PrimesTimerConfigurations timerConfigurations;

    public final PrimesBatteryConfigurations batteryConfigurations()
    {
        return batteryConfigurations;
    }

    public final PrimesCpuConfigurations cpuConfigurations()
    {
        return cpuConfigurations;
    }

    public final PrimesCrashConfigurations crashConfigurations()
    {
        return crashConfigurations;
    }

    public final PrimesGlobalConfigurations globalConfigurations()
    {
        return globalConfigurations;
    }

    public final PrimesJankConfigurations jankConfigurations()
    {
        return jankConfigurations;
    }

    public final PrimesMemoryConfigurations memoryConfigurations()
    {
        return memoryConfigurations;
    }

    public final PrimesMemoryLeakConfigurations memoryLeakConfigurations()
    {
        return memoryLeakConfigurations;
    }

    public final MetricTransmitter metricTransmitter()
    {
        return metricTransmitter;
    }

    public final PrimesMiniHeapDumpConfigurations miniHeapDumpConfigurations()
    {
        return miniHeapDumpConfigurations;
    }

    public final PrimesPackageConfigurations packageConfigurations()
    {
        return packageConfigurations;
    }

    public final PrimesTraceConfigurations primesTraceConfigurations()
    {
        return primesTraceConfigurations;
    }

    public final PrimesTikTokTraceConfigurations tiktokTraceConfigurations()
    {
        return tiktokTraceConfigurations;
    }

    public final PrimesTimerConfigurations timerConfigurations()
    {
        return timerConfigurations;
    }

    public (MetricTransmitter metrictransmitter, PrimesGlobalConfigurations primesglobalconfigurations, PrimesMemoryConfigurations primesmemoryconfigurations, PrimesTimerConfigurations primestimerconfigurations, PrimesCrashConfigurations primescrashconfigurations, PrimesNetworkConfigurations primesnetworkconfigurations, PrimesPackageConfigurations primespackageconfigurations, 
            PrimesJankConfigurations primesjankconfigurations, PrimesTikTokTraceConfigurations primestiktoktraceconfigurations, PrimesTraceConfigurations primestraceconfigurations, PrimesBatteryConfigurations primesbatteryconfigurations, PrimesMemoryLeakConfigurations primesmemoryleakconfigurations, PrimesExperimentalConfigurations primesexperimentalconfigurations, PrimesCpuConfigurations primescpuconfigurations, 
            PrimesMiniHeapDumpConfigurations primesminiheapdumpconfigurations)
    {
        metricTransmitter = metrictransmitter;
        globalConfigurations = primesglobalconfigurations;
        memoryConfigurations = primesmemoryconfigurations;
        timerConfigurations = primestimerconfigurations;
        crashConfigurations = primescrashconfigurations;
        packageConfigurations = primespackageconfigurations;
        jankConfigurations = primesjankconfigurations;
        tiktokTraceConfigurations = primestiktoktraceconfigurations;
        primesTraceConfigurations = primestraceconfigurations;
        batteryConfigurations = primesbatteryconfigurations;
        memoryLeakConfigurations = primesmemoryleakconfigurations;
        cpuConfigurations = primescpuconfigurations;
        miniHeapDumpConfigurations = primesminiheapdumpconfigurations;
    }
}
