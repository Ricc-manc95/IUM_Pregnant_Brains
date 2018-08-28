// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            BatteryMetricExtensionProvider

public final class PrimesBatteryConfigurations
{

    public static final PrimesBatteryConfigurations DEFAULT;
    private static final BatteryMetricExtensionProvider DEFAULT_METRIC_EXTENSION_PROVIDER;
    public final boolean deferredLogging = false;
    public final boolean enabled = false;
    public final BatteryMetricExtensionProvider metricExtensionProvider;

    private PrimesBatteryConfigurations(boolean flag, boolean flag1, BatteryMetricExtensionProvider batterymetricextensionprovider)
    {
        metricExtensionProvider = batterymetricextensionprovider;
    }

    static 
    {
        DEFAULT_METRIC_EXTENSION_PROVIDER = new _cls1();
        DEFAULT = new PrimesBatteryConfigurations(false, false, DEFAULT_METRIC_EXTENSION_PROVIDER);
    }

    private class _cls1
        implements BatteryMetricExtensionProvider
    {

        public final MetricExtension getMetricExtension$5166KOBMC4NMOOBECSNL6T3ID5N6EEQ95566ORR7ECNN0SJFEHNIUTR9E9IMOPBJECNN0PBICPNN4RB1DPHMABRDDTH6IR355TN62RJF5T6MAT3ID5HKAU3KCLN76QBFDOTG____0()
        {
            return null;
        }

        _cls1()
        {
        }
    }

}
