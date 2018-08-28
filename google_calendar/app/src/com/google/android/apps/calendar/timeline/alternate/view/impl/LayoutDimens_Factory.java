// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            LayoutDimens

public final class LayoutDimens_Factory
    implements Factory
{

    private final Provider converterProvider;
    private final Provider eventsPerMonthViewDayProvider;
    private final Provider scheduleProvider;
    private final Provider screenTypeProvider;

    public LayoutDimens_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        converterProvider = provider;
        screenTypeProvider = provider1;
        eventsPerMonthViewDayProvider = provider2;
        scheduleProvider = provider3;
    }

    public final Object get()
    {
        Provider provider = converterProvider;
        Provider provider1 = screenTypeProvider;
        Provider provider2 = eventsPerMonthViewDayProvider;
        Provider provider3 = scheduleProvider;
        return new LayoutDimens((DimensConverter)provider.get(), (ObservableReference)provider1.get(), (ObservableReference)provider2.get(), (ScheduleProvider)provider3.get());
    }
}
