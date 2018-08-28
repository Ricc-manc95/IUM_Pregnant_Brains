// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.transmitter.StackTraceTransmitter;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            CrashMetricExtensionProvider

public final class PrimesCrashConfigurations
{

    public static final PrimesCrashConfigurations DEFAULT = new PrimesCrashConfigurations(false);
    public final boolean enabled;
    public final CrashMetricExtensionProvider metricExtensionProvider;
    public final boolean sendStackTraces;
    public final StackTraceTransmitter stackTraceTransmitter;
    public final float startupSamplePercentage;

    public PrimesCrashConfigurations()
    {
        this(false);
    }

    public PrimesCrashConfigurations(boolean flag)
    {
        this(flag, null);
    }

    private PrimesCrashConfigurations(boolean flag, float f, CrashMetricExtensionProvider crashmetricextensionprovider, StackTraceTransmitter stacktracetransmitter, boolean flag1)
    {
        enabled = flag;
        startupSamplePercentage = 100F;
        sendStackTraces = false;
        stackTraceTransmitter = StackTraceTransmitter.NOOP_TRANSMITTER;
        metricExtensionProvider = crashmetricextensionprovider;
    }

    private PrimesCrashConfigurations(boolean flag, CrashMetricExtensionProvider crashmetricextensionprovider)
    {
        this(flag, 100F, null, StackTraceTransmitter.NOOP_TRANSMITTER, false);
    }

}
