// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import logs.proto.wireless.performance.mobile.nano.MagicEyeMetric;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, MagicEyeLogService

final class val.appStateEvent
    implements Runnable
{

    private final MagicEyeLogService this$0;
    private final int val$appStateEvent;

    public final void run()
    {
        SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
        systemhealthmetric.magicEyeMetric = new MagicEyeMetric();
        systemhealthmetric.magicEyeMetric.appStateEvent = val$appStateEvent;
        recordSystemHealthMetric(null, true, systemhealthmetric, null);
    }

    ()
    {
        this$0 = final_magiceyelogservice;
        val$appStateEvent = I.this;
        super();
    }
}
