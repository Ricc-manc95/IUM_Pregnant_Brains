// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            JankMetricExtensionProvider

public final class PrimesJankConfigurations
{

    public static final PrimesJankConfigurations DEFAULT;
    public static final JankMetricExtensionProvider DEFAULT_METRIC_EXTENSION_PROVIDER = new _cls1();
    public final boolean enabled;
    public final JankMetricExtensionProvider metricExtensionProvider;
    public final boolean monitorActivities;
    public final int sampleRatePerSecond;
    public final boolean useAnimator;

    PrimesJankConfigurations(boolean flag, boolean flag1, boolean flag2, int i, JankMetricExtensionProvider jankmetricextensionprovider)
    {
        enabled = flag;
        monitorActivities = flag1;
        useAnimator = flag2;
        sampleRatePerSecond = i;
        metricExtensionProvider = jankmetricextensionprovider;
    }

    static 
    {
        Builder builder = new Builder();
        DEFAULT = new PrimesJankConfigurations(false, false, builder.useAnimator, builder.sampleRatePerSecond, builder.metricExtensionProvider);
    }

    private class _cls1
        implements JankMetricExtensionProvider
    {

        public final MetricExtension getMetricExtension()
        {
            return null;
        }

        _cls1()
        {
        }
    }


    private class Builder
    {

        private static final boolean DEFAULT_JANK_USE_ANIMATOR;
        private boolean enabled;
        public JankMetricExtensionProvider metricExtensionProvider;
        private boolean monitorActivities;
        public int sampleRatePerSecond;
        public boolean useAnimator;

        static 
        {
            boolean flag;
            if (android.os.Build.VERSION.SDK_INT < 26)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            DEFAULT_JANK_USE_ANIMATOR = flag;
        }

        public Builder()
        {
            enabled = false;
            monitorActivities = false;
            useAnimator = DEFAULT_JANK_USE_ANIMATOR;
            sampleRatePerSecond = 10;
            metricExtensionProvider = PrimesJankConfigurations.DEFAULT_METRIC_EXTENSION_PROVIDER;
        }
    }

}
