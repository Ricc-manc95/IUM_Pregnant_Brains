// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.month;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DayView_DayDrawable_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider dimensConverterProvider;
    private final Provider dimensProvider;
    private final Provider eventsPerDayProvider;
    private final Provider isPortraitProvider;
    private final Provider isRtlProvider;
    private final Provider screenTypeProvider;

    public DayView_DayDrawable_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6)
    {
        contextProvider = provider;
        eventsPerDayProvider = provider1;
        isRtlProvider = provider2;
        screenTypeProvider = provider3;
        isPortraitProvider = provider4;
        dimensProvider = provider5;
        dimensConverterProvider = provider6;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = eventsPerDayProvider;
        Provider provider2 = isRtlProvider;
        Provider provider3 = screenTypeProvider;
        Provider provider4 = isPortraitProvider;
        Provider provider5 = dimensProvider;
        Provider provider6 = dimensConverterProvider;
        return new DayView.DayDrawable((Context)provider.get(), (ObservableReference)provider1.get(), (ObservableReference)provider2.get(), (ObservableReference)provider3.get(), (ObservableReference)provider4.get(), (LayoutDimens)provider5.get(), (DimensConverter)provider6.get());
    }
}
