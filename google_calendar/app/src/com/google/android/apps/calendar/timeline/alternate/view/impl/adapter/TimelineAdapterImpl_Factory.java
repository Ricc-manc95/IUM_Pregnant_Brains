// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterImpl, AdapterConverter, MonthAdapter

public final class TimelineAdapterImpl_Factory
    implements Factory
{

    private final Provider allDayClickGuardHolderProvider;
    private final Provider allDayExpandViewHolderProvider;
    private final Provider allDayMoreViewHolderProvider;
    private final Provider converterProvider;
    private final Provider createEventPhantomProvider;
    private final Provider dayHeaderViewHolderProvider;
    private final Provider dragStateObservableProvider;
    private final Provider eventViewHolderProvider;
    private final Provider hoursProvider;
    private final Provider idleObservableProvider;
    private final Provider monthAdapterProvider;
    private final Provider monthBannerViewHolderProvider;
    private final Provider monthDayViewHolderProvider;
    private final Provider nothingPlannedBannerViewHolderProvider;
    private final Provider nowLineViewHolderProvider;
    private final Provider storeProvider;
    private final Provider timeUtilsProvider;
    private final Provider weekBannerViewHolderProvider;

    public TimelineAdapterImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, 
            Provider provider14, Provider provider15, Provider provider16, Provider provider17)
    {
        timeUtilsProvider = provider;
        dragStateObservableProvider = provider1;
        createEventPhantomProvider = provider2;
        storeProvider = provider3;
        idleObservableProvider = provider4;
        allDayExpandViewHolderProvider = provider5;
        allDayMoreViewHolderProvider = provider6;
        eventViewHolderProvider = provider7;
        dayHeaderViewHolderProvider = provider8;
        monthDayViewHolderProvider = provider9;
        monthBannerViewHolderProvider = provider10;
        weekBannerViewHolderProvider = provider11;
        nothingPlannedBannerViewHolderProvider = provider12;
        nowLineViewHolderProvider = provider13;
        allDayClickGuardHolderProvider = provider14;
        hoursProvider = provider15;
        converterProvider = provider16;
        monthAdapterProvider = provider17;
    }

    public final Object get()
    {
        Provider provider = timeUtilsProvider;
        Provider provider1 = dragStateObservableProvider;
        Provider provider2 = createEventPhantomProvider;
        Provider provider3 = storeProvider;
        Provider provider4 = idleObservableProvider;
        Provider provider5 = allDayExpandViewHolderProvider;
        Provider provider6 = allDayMoreViewHolderProvider;
        Provider provider7 = eventViewHolderProvider;
        Provider provider8 = dayHeaderViewHolderProvider;
        Provider provider9 = monthDayViewHolderProvider;
        Provider provider10 = monthBannerViewHolderProvider;
        Provider provider11 = weekBannerViewHolderProvider;
        Provider provider12 = nothingPlannedBannerViewHolderProvider;
        Provider provider13 = nowLineViewHolderProvider;
        Provider provider14 = allDayClickGuardHolderProvider;
        Provider provider15 = hoursProvider;
        Provider provider16 = converterProvider;
        Provider provider17 = monthAdapterProvider;
        return new TimelineAdapterImpl((TimeUtils)provider.get(), (ObservableReference)provider1.get(), (ObservableReference)provider2.get(), (CalendarContentStore)provider3.get(), (ObservableReference)provider4.get(), provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, (AdapterConverter)provider16.get(), (MonthAdapter)provider17.get());
    }
}
