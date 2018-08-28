// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.graphics.Point;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.CreationMode;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnLayoutUpdater, AllDayManager, ColumnViewport, ColumnViewportController, 
//            ColumnDimens

public final class ColumnLayoutUpdater_Factory
    implements Factory
{

    private final Provider adapterProvider;
    private final Provider allDayManagerProvider;
    private final Provider createEventPhantomProvider;
    private final Provider creationModeProvider;
    private final Provider currentTimeProvider;
    private final Provider dimensConverterProvider;
    private final Provider dimensProvider;
    private final Provider dragStateObservableProvider;
    private final Provider gridOffsetProvider;
    private final Provider isA11yEnabledProvider;
    private final Provider isRtlProvider;
    private final Provider itemAdapterProvider;
    private final Provider layoutDimensProvider;
    private final Provider scheduleProvider;
    private final Provider screenTypeProvider;
    private final Provider shouldShowWeekNumbersProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewProvider;
    private final Provider viewportControllerProvider;
    private final Provider viewportProvider;

    public ColumnLayoutUpdater_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, 
            Provider provider14, Provider provider15, Provider provider16, Provider provider17, Provider provider18, Provider provider19)
    {
        viewProvider = provider;
        isRtlProvider = provider1;
        isA11yEnabledProvider = provider2;
        dimensConverterProvider = provider3;
        timeUtilsProvider = provider4;
        currentTimeProvider = provider5;
        createEventPhantomProvider = provider6;
        dragStateObservableProvider = provider7;
        adapterProvider = provider8;
        allDayManagerProvider = provider9;
        viewportProvider = provider10;
        viewportControllerProvider = provider11;
        gridOffsetProvider = provider12;
        itemAdapterProvider = provider13;
        dimensProvider = provider14;
        layoutDimensProvider = provider15;
        screenTypeProvider = provider16;
        shouldShowWeekNumbersProvider = provider17;
        scheduleProvider = provider18;
        creationModeProvider = provider19;
    }

    public final Object get()
    {
        Provider provider = viewProvider;
        Provider provider1 = isRtlProvider;
        Provider provider2 = isA11yEnabledProvider;
        Provider provider3 = dimensConverterProvider;
        Provider provider4 = timeUtilsProvider;
        Provider provider5 = currentTimeProvider;
        Provider provider6 = createEventPhantomProvider;
        Provider provider7 = dragStateObservableProvider;
        Provider provider8 = adapterProvider;
        Provider provider9 = allDayManagerProvider;
        Provider provider10 = viewportProvider;
        Provider provider11 = viewportControllerProvider;
        Provider provider12 = gridOffsetProvider;
        Provider provider13 = itemAdapterProvider;
        Provider provider14 = dimensProvider;
        Provider provider15 = layoutDimensProvider;
        Provider provider16 = screenTypeProvider;
        Provider provider17 = shouldShowWeekNumbersProvider;
        Provider provider18 = scheduleProvider;
        Provider provider19 = creationModeProvider;
        return new ColumnLayoutUpdater((TimelineHostView)provider.get(), (ObservableReference)provider1.get(), (ObservableReference)provider2.get(), (DimensConverter)provider3.get(), (TimeUtils)provider4.get(), (ObservableReference)provider5.get(), (ObservableReference)provider6.get(), (ObservableReference)provider7.get(), (TimelineAdapter)provider8.get(), (AllDayManager)provider9.get(), (ColumnViewport)provider10.get(), (ColumnViewportController)provider11.get(), (Point)provider12.get(), (ItemAdapter)provider13.get(), (ColumnDimens)provider14.get(), (LayoutDimens)provider15.get(), (ObservableReference)provider16.get(), (ObservableReference)provider17.get(), (ScheduleProvider)provider18.get(), (CreationMode)provider19.get());
    }
}
