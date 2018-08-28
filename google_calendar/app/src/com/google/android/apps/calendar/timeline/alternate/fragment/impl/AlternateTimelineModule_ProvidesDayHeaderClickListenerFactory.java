// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.ViewModeChangeListener;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            AlternateTimelineModule

public final class AlternateTimelineModule_ProvidesDayHeaderClickListenerFactory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider controllerProvider;
    private final Provider listenerProvider;
    private final Provider nextModeSupplierProvider;

    public AlternateTimelineModule_ProvidesDayHeaderClickListenerFactory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        contextProvider = provider;
        controllerProvider = provider1;
        listenerProvider = provider2;
        nextModeSupplierProvider = provider3;
    }

    public final Object get()
    {
        Object obj = contextProvider;
        Provider provider = controllerProvider;
        Provider provider1 = listenerProvider;
        Provider provider2 = nextModeSupplierProvider;
        obj = AlternateTimelineModule.providesDayHeaderClickListener((Context)((Provider) (obj)).get(), (TimelineApi)provider.get(), (ViewModeChangeListener)provider1.get(), (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier)provider2.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderClickListener)obj;
        }
    }
}
