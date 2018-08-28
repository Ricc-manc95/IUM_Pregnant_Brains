// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            MemoryMetricExtensionProvider

public final class PrimesMemoryConfigurations
{

    public static final PrimesMemoryConfigurations DEFAULT = new PrimesMemoryConfigurations(false);
    private static final MemoryMetricExtensionProvider DEFAULT_METRIC_EXTENSION_PROVIDER = new _cls1();
    public final boolean enabled;
    public final boolean forceGcBeforeRecordMemory;
    public final MemoryMetricExtensionProvider metricExtensionProvider;
    public final boolean recordMetricPerProcess;
    public final int sampleRatePerSecond;

    public PrimesMemoryConfigurations()
    {
        this(false);
    }

    private PrimesMemoryConfigurations(boolean flag)
    {
        this(false, 3);
    }

    private PrimesMemoryConfigurations(boolean flag, int i)
    {
        this(flag, 3, false);
    }

    private PrimesMemoryConfigurations(boolean flag, int i, boolean flag1)
    {
        this(flag, i, false, DEFAULT_METRIC_EXTENSION_PROVIDER);
    }

    public PrimesMemoryConfigurations(boolean flag, int i, boolean flag1, MemoryMetricExtensionProvider memorymetricextensionprovider)
    {
        this(flag, i, flag1, memorymetricextensionprovider, false);
    }

    private PrimesMemoryConfigurations(boolean flag, int i, boolean flag1, MemoryMetricExtensionProvider memorymetricextensionprovider, boolean flag2)
    {
        enabled = flag;
        sampleRatePerSecond = i;
        recordMetricPerProcess = flag1;
        metricExtensionProvider = memorymetricextensionprovider;
        forceGcBeforeRecordMemory = false;
    }


    private class _cls1
        implements MemoryMetricExtensionProvider
    {

        public final MetricExtension getMetricExtension(String s, int i)
        {
            return null;
        }

        _cls1()
        {
        }
    }

}
