// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            TimerMetricService, PrimesPerEventConfigurationFlags, AbstractMetricService

final class val.extension
    implements Runnable
{

    private final TimerMetricService this$0;
    private final String val$eventName;
    private final MetricExtension val$extension;
    private final boolean val$isEventNameConstant;
    private final SystemHealthMetric val$metric;

    public final void run()
    {
        perEventConfigFlags.isFlagEnabled$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9B8______0();
        recordSystemHealthMetric(val$eventName, val$isEventNameConstant, val$metric, val$extension);
    }

    ()
    {
        this$0 = final_timermetricservice;
        val$eventName = s;
        val$isEventNameConstant = flag;
        val$metric = systemhealthmetric;
        val$extension = MetricExtension.this;
        super();
    }
}
