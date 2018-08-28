// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.primes;

import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.libraries.performance.primes.MemoryMetricExtensionProvider;
import logs.proto.wireless.performance.mobile.nano.CalendarExtension;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;

// Referenced classes of package com.google.android.apps.calendar.primes:
//            PerformanceMetricCollectorImpl

final class arg._cls1
    implements MemoryMetricExtensionProvider
{

    private final PerformanceMetricCollectorImpl arg$1;

    public final MetricExtension getMetricExtension(String s, int i)
    {
        Object obj = arg$1;
        s = new nsionBuilder();
        int ai[] = ((PerformanceMetricCollectorImpl) (obj)).activeExperiments;
        ((nsionBuilder) (s)).calendarExtension.activeExperiments = ai;
        ((nsionBuilder) (s)).calendarExtension.remoteFeatureConfig = RemoteFeatureConfig.getEnabledFeaturesArray();
        ai = new MetricExtension();
        ai.calendarExtension = ((nsionBuilder) (s)).calendarExtension;
        return ai;
    }

    nsionBuilder(PerformanceMetricCollectorImpl performancemetriccollectorimpl)
    {
        arg$1 = performancemetriccollectorimpl;
    }
}
