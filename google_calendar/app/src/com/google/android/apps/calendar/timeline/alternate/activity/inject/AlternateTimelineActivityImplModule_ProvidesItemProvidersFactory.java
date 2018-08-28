// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.activity.inject;

import com.google.android.apps.calendar.timeline.alternate.view.api.ItemProvider;
import com.google.android.apps.calendar.timeline.alternate.view.timebox.TaskItemProvider;
import com.google.android.apps.calendar.timeline.alternate.view.timebox.TimeBoxItemProvider;
import com.google.android.calendar.latency.LatencyLogger;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.activity.inject:
//            AlternateTimelineActivityImplModule

public final class AlternateTimelineActivityImplModule_ProvidesItemProvidersFactory
    implements Factory
{

    private final Provider alternateTimelineEnabledProvider;
    private final Provider latencyLoggerProvider;
    private final Provider taskProvider;
    private final Provider timeBoxProvider;

    public AlternateTimelineActivityImplModule_ProvidesItemProvidersFactory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        alternateTimelineEnabledProvider = provider;
        timeBoxProvider = provider1;
        taskProvider = provider2;
        latencyLoggerProvider = provider3;
    }

    public final Object get()
    {
        Object obj = alternateTimelineEnabledProvider;
        Provider provider = timeBoxProvider;
        Provider provider1 = taskProvider;
        Provider provider2 = latencyLoggerProvider;
        obj = AlternateTimelineActivityImplModule.providesItemProviders(((Boolean)((Provider) (obj)).get()).booleanValue(), (TimeBoxItemProvider)provider.get(), (TaskItemProvider)provider1.get(), (LatencyLogger)provider2.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ItemProvider)obj;
        }
    }
}
