// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleCache, ScheduleDayFactory

public final class ScheduleCache_Factory
    implements Factory
{

    private final Provider adapterProvider;
    private final Provider currentTimeProvider;
    private final Provider factoryProvider;
    private final Provider timeUtilsProvider;

    public ScheduleCache_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        timeUtilsProvider = provider;
        currentTimeProvider = provider1;
        adapterProvider = provider2;
        factoryProvider = provider3;
    }

    public final Object get()
    {
        Provider provider = timeUtilsProvider;
        Provider provider1 = currentTimeProvider;
        Provider provider2 = adapterProvider;
        Provider provider3 = factoryProvider;
        return new ScheduleCache((TimeUtils)provider.get(), (ObservableReference)provider1.get(), (TimelineAdapter)provider2.get(), (ScheduleDayFactory)provider3.get());
    }
}
