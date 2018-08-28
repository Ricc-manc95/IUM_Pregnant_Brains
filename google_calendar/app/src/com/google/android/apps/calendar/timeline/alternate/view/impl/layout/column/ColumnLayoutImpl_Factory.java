// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnLayoutUpdater, AllDayManager, ColumnViewport, ColumnViewportController, 
//            ColumnLayoutImpl

public final class ColumnLayoutImpl_Factory
    implements Factory
{

    private final Provider allDayManagerProvider;
    private final Provider columnLayoutUpdaterProvider;
    private final Provider currentTimeProvider;
    private final Provider layoutDimensProvider;
    private final Provider screenTypeProvider;
    private final Provider selectedRangeObservableProvider;
    private final Provider shouldShowWeekNumbersProvider;
    private final Provider timeUtilsProvider;
    private final Provider unusedCreatePhantomUpdaterProvider;
    private final Provider viewProvider;
    private final Provider viewportControllerProvider;
    private final Provider viewportObservableProvider;
    private final Provider viewportProvider;

    public ColumnLayoutImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12)
    {
        columnLayoutUpdaterProvider = provider;
        viewProvider = provider1;
        allDayManagerProvider = provider2;
        viewportProvider = provider3;
        viewportControllerProvider = provider4;
        viewportObservableProvider = provider5;
        selectedRangeObservableProvider = provider6;
        layoutDimensProvider = provider7;
        screenTypeProvider = provider8;
        timeUtilsProvider = provider9;
        currentTimeProvider = provider10;
        shouldShowWeekNumbersProvider = provider11;
        unusedCreatePhantomUpdaterProvider = provider12;
    }

    public final Object get()
    {
        Object obj1 = columnLayoutUpdaterProvider;
        Object obj3 = viewProvider;
        Object obj5 = allDayManagerProvider;
        Object obj7 = viewportProvider;
        Object obj9 = viewportControllerProvider;
        Object obj11 = viewportObservableProvider;
        Object obj10 = selectedRangeObservableProvider;
        Object obj8 = layoutDimensProvider;
        Object obj6 = screenTypeProvider;
        Object obj4 = timeUtilsProvider;
        Object obj2 = currentTimeProvider;
        Object obj = shouldShowWeekNumbersProvider;
        Provider provider = unusedCreatePhantomUpdaterProvider;
        obj1 = (ColumnLayoutUpdater)((Provider) (obj1)).get();
        obj3 = (TimelineHostView)((Provider) (obj3)).get();
        obj5 = (AllDayManager)((Provider) (obj5)).get();
        obj7 = (ColumnViewport)((Provider) (obj7)).get();
        obj9 = (ColumnViewportController)((Provider) (obj9)).get();
        obj11 = (ObservableReference)((Provider) (obj11)).get();
        obj10 = (ObservableReference)((Provider) (obj10)).get();
        obj8 = (LayoutDimens)((Provider) (obj8)).get();
        obj6 = (ObservableReference)((Provider) (obj6)).get();
        obj4 = (TimeUtils)((Provider) (obj4)).get();
        obj2 = (ObservableReference)((Provider) (obj2)).get();
        obj = (ObservableReference)((Provider) (obj)).get();
        provider.get();
        return new ColumnLayoutImpl(((ColumnLayoutUpdater) (obj1)), ((TimelineHostView) (obj3)), ((AllDayManager) (obj5)), ((ColumnViewport) (obj7)), ((ColumnViewportController) (obj9)), ((ObservableReference) (obj11)), ((ObservableReference) (obj10)), ((LayoutDimens) (obj8)), ((ObservableReference) (obj6)), ((TimeUtils) (obj4)), ((ObservableReference) (obj2)), ((ObservableReference) (obj)));
    }
}
