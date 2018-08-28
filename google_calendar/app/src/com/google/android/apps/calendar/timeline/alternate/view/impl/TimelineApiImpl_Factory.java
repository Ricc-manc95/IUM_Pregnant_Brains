// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.CreationMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutManager;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineApiImpl, RecyclerViewAccessibilityDelegateHelper

public final class TimelineApiImpl_Factory
    implements Factory
{

    private final Provider accessibilityDelegateProvider;
    private final Provider adapterProvider;
    private final Provider columnBackgroundProvider;
    private final Provider columnLayoutProvider;
    private final Provider columnOnDragListenerProvider;
    private final Provider columnOnTapListenerProvider;
    private final Provider createEventPhantomProvider;
    private final Provider creationModeProvider;
    private final Provider currentTimeProvider;
    private final Provider dragOffsetProvider;
    private final Provider idleObservableProvider;
    private final Provider isA11yEnabledProvider;
    private final Provider layoutManagerProvider;
    private final Provider monthBackgroundProvider;
    private final Provider monthLayoutProvider;
    private final Provider recyclerViewLayoutManagerProvider;
    private final Provider recyclerViewProvider;
    private final Provider scheduleLayoutProvider;
    private final Provider storeProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewportRangeProvider;

    public TimelineApiImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, 
            Provider provider14, Provider provider15, Provider provider16, Provider provider17, Provider provider18, Provider provider19, Provider provider20)
    {
        recyclerViewProvider = provider;
        recyclerViewLayoutManagerProvider = provider1;
        layoutManagerProvider = provider2;
        timeUtilsProvider = provider3;
        currentTimeProvider = provider4;
        idleObservableProvider = provider5;
        columnLayoutProvider = provider6;
        columnBackgroundProvider = provider7;
        columnOnDragListenerProvider = provider8;
        columnOnTapListenerProvider = provider9;
        createEventPhantomProvider = provider10;
        creationModeProvider = provider11;
        viewportRangeProvider = provider12;
        storeProvider = provider13;
        scheduleLayoutProvider = provider14;
        monthLayoutProvider = provider15;
        monthBackgroundProvider = provider16;
        adapterProvider = provider17;
        dragOffsetProvider = provider18;
        accessibilityDelegateProvider = provider19;
        isA11yEnabledProvider = provider20;
    }

    public final Object get()
    {
        Provider provider = recyclerViewProvider;
        Provider provider1 = recyclerViewLayoutManagerProvider;
        Provider provider2 = layoutManagerProvider;
        Provider provider3 = timeUtilsProvider;
        Provider provider4 = currentTimeProvider;
        Provider provider5 = idleObservableProvider;
        Provider provider6 = columnLayoutProvider;
        Provider provider7 = columnBackgroundProvider;
        Provider provider8 = columnOnDragListenerProvider;
        Provider provider9 = columnOnTapListenerProvider;
        Provider provider10 = createEventPhantomProvider;
        Provider provider11 = creationModeProvider;
        Provider provider12 = viewportRangeProvider;
        Provider provider13 = storeProvider;
        Provider provider14 = scheduleLayoutProvider;
        Provider provider15 = monthLayoutProvider;
        Provider provider16 = monthBackgroundProvider;
        Provider provider17 = adapterProvider;
        Provider provider18 = dragOffsetProvider;
        Provider provider19 = accessibilityDelegateProvider;
        Provider provider20 = isA11yEnabledProvider;
        return new TimelineApiImpl((RecyclerView)provider.get(), (android.support.v7.widget.RecyclerView.LayoutManager)provider1.get(), (LayoutManager)provider2.get(), (TimeUtils)provider3.get(), (ObservableReference)provider4.get(), (ObservableReference)provider5.get(), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), (ObservableReference)provider10.get(), (CreationMode)provider11.get(), (ObservableReference)provider12.get(), (CalendarContentStore)provider13.get(), DoubleCheck.lazy(provider14), DoubleCheck.lazy(provider15), DoubleCheck.lazy(provider16), (android.support.v7.widget.RecyclerView.Adapter)provider17.get(), (Point)provider18.get(), (RecyclerViewAccessibilityDelegateHelper)provider19.get(), (ObservableReference)provider20.get());
    }
}
