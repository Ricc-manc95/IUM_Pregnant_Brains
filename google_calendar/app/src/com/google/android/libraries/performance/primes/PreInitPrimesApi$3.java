// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.PrimesScenario;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            ConfiguredPrimesApi

final class val.primesScenario
    implements heduledApiCall
{

    private final String val$customEventName;
    private final boolean val$isEventNameConstant;
    private final MetricExtension val$metricExtension;
    private final PrimesScenario val$primesScenario;

    public final void callApi(ConfiguredPrimesApi configuredprimesapi)
    {
        configuredprimesapi.recordMemory(val$customEventName, val$isEventNameConstant, val$metricExtension, val$primesScenario);
    }

    ()
    {
        val$customEventName = s;
        val$isEventNameConstant = flag;
        val$metricExtension = metricextension;
        val$primesScenario = primesscenario;
        super();
    }
}
