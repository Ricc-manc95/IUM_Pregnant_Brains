// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            AlternateTimelineModule

public final class AlternateTimelineModule_ProvidesDayHeaderNextModeSupplierFactory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider controllerProvider;
    private final Provider isPortraitProvider;
    private final Provider screenTypeProvider;

    public AlternateTimelineModule_ProvidesDayHeaderNextModeSupplierFactory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        contextProvider = provider;
        controllerProvider = provider1;
        isPortraitProvider = provider2;
        screenTypeProvider = provider3;
    }

    public final Object get()
    {
        Object obj = contextProvider;
        Provider provider = controllerProvider;
        Provider provider1 = isPortraitProvider;
        Provider provider2 = screenTypeProvider;
        obj = AlternateTimelineModule.providesDayHeaderNextModeSupplier((Context)((Provider) (obj)).get(), (TimelineApi)provider.get(), (ObservableReference)provider1.get(), (ObservableReference)provider2.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier)obj;
        }
    }
}
