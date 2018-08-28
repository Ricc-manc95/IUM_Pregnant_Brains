// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesConfigurations, PrimesBatteryConfigurations, PrimesCpuConfigurations, PrimesCrashConfigurations, 
//            PrimesGlobalConfigurations, PrimesJankConfigurations, PrimesMemoryConfigurations, PrimesMemoryLeakConfigurations, 
//            PrimesMiniHeapDumpConfigurations, PrimesPackageConfigurations, PrimesTraceConfigurations, PrimesTikTokTraceConfigurations, 
//            PrimesTimerConfigurations

public final class configs extends PrimesConfigurations
{

    private volatile PrimesBatteryConfigurations batteryConfigurations;
    private final PrimesConfigurations configs;
    private volatile PrimesCpuConfigurations cpuConfigurations;
    private volatile PrimesCrashConfigurations crashConfigurations;
    private volatile PrimesGlobalConfigurations globalConfigurations;
    private volatile PrimesJankConfigurations jankConfigurations;
    private volatile PrimesMemoryConfigurations memoryConfigurations;
    private volatile PrimesMemoryLeakConfigurations memoryLeakConfigurations;
    private volatile MetricTransmitter metricTransmitter;
    private volatile PrimesMiniHeapDumpConfigurations miniHeapDumpConfigurations;
    private final Object mutex = new Object();
    private volatile PrimesPackageConfigurations packageConfigurations;
    private volatile PrimesTraceConfigurations primesTraceConfigurations;
    private volatile PrimesTikTokTraceConfigurations tiktokTraceConfigurations;
    private volatile PrimesTimerConfigurations timerConfigurations;

    public final PrimesBatteryConfigurations batteryConfigurations()
    {
        if (batteryConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (batteryConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesBatteryConfigurations primesbatteryconfigurations = configs.batteryConfigurations();
        if (primesbatteryconfigurations == null) goto _L6; else goto _L5
_L5:
        batteryConfigurations = primesbatteryconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return batteryConfigurations;
_L6:
        primesbatteryconfigurations = PrimesBatteryConfigurations.DEFAULT;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesCpuConfigurations cpuConfigurations()
    {
        if (cpuConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (cpuConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesCpuConfigurations primescpuconfigurations = configs.cpuConfigurations();
        if (primescpuconfigurations == null)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        if (primescpuconfigurations.initialDelay <= 0 || primescpuconfigurations.numSamples <= 0 || primescpuconfigurations.timeBetweenSamples < 100)
        {
            break MISSING_BLOCK_LABEL_68;
        }
_L5:
        cpuConfigurations = primescpuconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return cpuConfigurations;
        primescpuconfigurations = PrimesCpuConfigurations.DEFAULT;
          goto _L5
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesCrashConfigurations crashConfigurations()
    {
        if (crashConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (crashConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesCrashConfigurations primescrashconfigurations = configs.crashConfigurations();
        if (primescrashconfigurations == null) goto _L6; else goto _L5
_L5:
        crashConfigurations = primescrashconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return crashConfigurations;
_L6:
        primescrashconfigurations = PrimesCrashConfigurations.DEFAULT;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesGlobalConfigurations globalConfigurations()
    {
        if (globalConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (globalConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesGlobalConfigurations primesglobalconfigurations = configs.globalConfigurations();
        if (primesglobalconfigurations == null) goto _L6; else goto _L5
_L5:
        globalConfigurations = primesglobalconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return globalConfigurations;
_L6:
        primesglobalconfigurations = PrimesGlobalConfigurations.DEFAULT;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesJankConfigurations jankConfigurations()
    {
        if (jankConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (jankConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesJankConfigurations primesjankconfigurations = configs.jankConfigurations();
        if (primesjankconfigurations == null) goto _L6; else goto _L5
_L5:
        jankConfigurations = primesjankconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return jankConfigurations;
_L6:
        primesjankconfigurations = PrimesJankConfigurations.DEFAULT;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesMemoryConfigurations memoryConfigurations()
    {
        if (memoryConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (memoryConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesMemoryConfigurations primesmemoryconfigurations = configs.memoryConfigurations();
        if (primesmemoryconfigurations == null) goto _L6; else goto _L5
_L5:
        memoryConfigurations = primesmemoryconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return memoryConfigurations;
_L6:
        primesmemoryconfigurations = PrimesMemoryConfigurations.DEFAULT;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesMemoryLeakConfigurations memoryLeakConfigurations()
    {
        if (memoryLeakConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (memoryLeakConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesMemoryLeakConfigurations primesmemoryleakconfigurations = configs.memoryLeakConfigurations();
        if (primesmemoryleakconfigurations == null) goto _L6; else goto _L5
_L5:
        memoryLeakConfigurations = primesmemoryleakconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return memoryLeakConfigurations;
_L6:
        primesmemoryleakconfigurations = PrimesMemoryLeakConfigurations.DEFAULT;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final MetricTransmitter metricTransmitter()
    {
        if (metricTransmitter != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (metricTransmitter != null) goto _L4; else goto _L3
_L3:
        MetricTransmitter metrictransmitter = configs.metricTransmitter();
        if (metrictransmitter == null) goto _L6; else goto _L5
_L5:
        metricTransmitter = metrictransmitter;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return metricTransmitter;
_L6:
        metrictransmitter = MetricTransmitter.NOOP_TRANSMITTER;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesMiniHeapDumpConfigurations miniHeapDumpConfigurations()
    {
        if (miniHeapDumpConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (miniHeapDumpConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesMiniHeapDumpConfigurations primesminiheapdumpconfigurations = configs.miniHeapDumpConfigurations();
        if (primesminiheapdumpconfigurations == null) goto _L6; else goto _L5
_L5:
        miniHeapDumpConfigurations = primesminiheapdumpconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return miniHeapDumpConfigurations;
_L6:
        primesminiheapdumpconfigurations = PrimesMiniHeapDumpConfigurations.DEFAULT;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesPackageConfigurations packageConfigurations()
    {
        if (packageConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (packageConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesPackageConfigurations primespackageconfigurations = configs.packageConfigurations();
        if (primespackageconfigurations == null) goto _L6; else goto _L5
_L5:
        packageConfigurations = primespackageconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return packageConfigurations;
_L6:
        primespackageconfigurations = PrimesPackageConfigurations.DEFAULT;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesTraceConfigurations primesTraceConfigurations()
    {
        if (primesTraceConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (primesTraceConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesTraceConfigurations primestraceconfigurations = configs.primesTraceConfigurations();
        if (primestraceconfigurations == null) goto _L6; else goto _L5
_L5:
        primesTraceConfigurations = primestraceconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return primesTraceConfigurations;
_L6:
        primestraceconfigurations = PrimesTraceConfigurations.DEFAULT;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesTikTokTraceConfigurations tiktokTraceConfigurations()
    {
        if (tiktokTraceConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (tiktokTraceConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesTikTokTraceConfigurations primestiktoktraceconfigurations = configs.tiktokTraceConfigurations();
        if (primestiktoktraceconfigurations == null) goto _L6; else goto _L5
_L5:
        tiktokTraceConfigurations = primestiktoktraceconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return tiktokTraceConfigurations;
_L6:
        primestiktoktraceconfigurations = PrimesTikTokTraceConfigurations.DEFAULT;
        if (true) goto _L5; else goto _L7
_L7:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final PrimesTimerConfigurations timerConfigurations()
    {
        if (timerConfigurations != null) goto _L2; else goto _L1
_L1:
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if (timerConfigurations != null) goto _L4; else goto _L3
_L3:
        PrimesTimerConfigurations primestimerconfigurations = configs.timerConfigurations();
        if (primestimerconfigurations == null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        if (primestimerconfigurations.sampleRatePerSecond <= 0)
        {
            break MISSING_BLOCK_LABEL_52;
        }
_L5:
        timerConfigurations = primestimerconfigurations;
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return timerConfigurations;
        primestimerconfigurations = PrimesTimerConfigurations.DEFAULT;
          goto _L5
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public (PrimesConfigurations primesconfigurations)
    {
        configs = primesconfigurations;
    }
}
