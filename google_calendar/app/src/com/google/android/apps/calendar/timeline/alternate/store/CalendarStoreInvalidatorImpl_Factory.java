// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import android.arch.lifecycle.LifecycleOwner;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarStoreInvalidatorImpl, CalendarContentStore

public final class CalendarStoreInvalidatorImpl_Factory
    implements Factory
{

    private final Provider alternateTimelineEnabledProvider;
    private final Provider calendarsCacheProvider;
    private final Provider lifecycleOwnerProvider;
    private final Provider settingsCacheProvider;
    private final Provider storeProvider;
    private final Provider taskCacheProvider;
    private final Provider timeZoneObservableProvider;

    public CalendarStoreInvalidatorImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6)
    {
        alternateTimelineEnabledProvider = provider;
        storeProvider = provider1;
        calendarsCacheProvider = provider2;
        settingsCacheProvider = provider3;
        taskCacheProvider = provider4;
        timeZoneObservableProvider = provider5;
        lifecycleOwnerProvider = provider6;
    }

    public final Object get()
    {
        Provider provider = alternateTimelineEnabledProvider;
        Provider provider1 = storeProvider;
        Provider provider2 = calendarsCacheProvider;
        Provider provider3 = settingsCacheProvider;
        Provider provider4 = taskCacheProvider;
        Provider provider5 = timeZoneObservableProvider;
        Provider provider6 = lifecycleOwnerProvider;
        return new CalendarStoreInvalidatorImpl(((Boolean)provider.get()).booleanValue(), (CalendarContentStore)provider1.get(), (ListenableFutureCache)provider2.get(), (ListenableFutureCache)provider3.get(), (ListenableFutureCache)provider4.get(), (ObservableReference)provider5.get(), (LifecycleOwner)provider6.get());
    }
}
