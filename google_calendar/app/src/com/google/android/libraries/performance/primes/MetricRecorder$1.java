// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            MetricRecorder

final class val.metricExtension
    implements Runnable
{

    private final MetricRecorder this$0;
    private final String val$customEventName;
    private final boolean val$isEventNameConstant;
    private final SystemHealthMetric val$message;
    private final MetricExtension val$metricExtension;

    public final void run()
    {
        recordInternal(val$customEventName, val$isEventNameConstant, val$message, val$metricExtension);
    }

    ()
    {
        this$0 = final_metricrecorder;
        val$customEventName = s;
        val$isEventNameConstant = flag;
        val$message = systemhealthmetric;
        val$metricExtension = MetricExtension.this;
        super();
    }
}
