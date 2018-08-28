// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.YearMonthHelper;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthLayoutImpl, MonthViewport, MonthViewportController

public final class MonthLayoutImpl_Factory
    implements Factory
{

    private final Provider adapterProvider;
    private final Provider currentTimeProvider;
    private final Provider dimensAndLayoutDimensProvider;
    private final Provider dimensConverterProvider;
    private final Provider eventsPerDayProvider;
    private final Provider hostViewProvider;
    private final Provider isRtlProvider;
    private final Provider itemAdapterProvider;
    private final Provider screenTypeProvider;
    private final Provider selectedRangeObservableProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewportControllerProvider;
    private final Provider viewportObservableProvider;
    private final Provider viewportProvider;
    private final Provider yearMonthHelperProvider;

    public MonthLayoutImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, 
            Provider provider14)
    {
        hostViewProvider = provider;
        adapterProvider = provider1;
        viewportProvider = provider2;
        viewportControllerProvider = provider3;
        viewportObservableProvider = provider4;
        selectedRangeObservableProvider = provider5;
        eventsPerDayProvider = provider6;
        itemAdapterProvider = provider7;
        yearMonthHelperProvider = provider8;
        timeUtilsProvider = provider9;
        dimensAndLayoutDimensProvider = provider10;
        dimensConverterProvider = provider11;
        isRtlProvider = provider12;
        screenTypeProvider = provider13;
        currentTimeProvider = provider14;
    }

    public final Object get()
    {
        Provider provider = hostViewProvider;
        Provider provider1 = adapterProvider;
        Provider provider2 = viewportProvider;
        Provider provider3 = viewportControllerProvider;
        Provider provider4 = viewportObservableProvider;
        Provider provider5 = selectedRangeObservableProvider;
        Provider provider6 = eventsPerDayProvider;
        Provider provider7 = itemAdapterProvider;
        Provider provider8 = yearMonthHelperProvider;
        Provider provider9 = timeUtilsProvider;
        Provider provider10 = dimensAndLayoutDimensProvider;
        Provider provider11 = dimensConverterProvider;
        Provider provider12 = isRtlProvider;
        Provider provider13 = screenTypeProvider;
        Provider provider14 = currentTimeProvider;
        return new MonthLayoutImpl((TimelineHostView)provider.get(), (TimelineAdapter)provider1.get(), (MonthViewport)provider2.get(), (MonthViewportController)provider3.get(), (ObservableReference)provider4.get(), (ObservableReference)provider5.get(), (ObservableReference)provider6.get(), (ItemAdapter)provider7.get(), (YearMonthHelper)provider8.get(), (TimeUtils)provider9.get(), (LayoutDimens)provider10.get(), (DimensConverter)provider11.get(), (ObservableReference)provider12.get(), (ObservableReference)provider13.get(), (LayoutDimens)provider10.get(), (ObservableReference)provider14.get());
    }
}
