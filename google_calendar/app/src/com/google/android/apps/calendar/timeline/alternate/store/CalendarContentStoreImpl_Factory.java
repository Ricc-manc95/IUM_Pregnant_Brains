// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import android.arch.lifecycle.Lifecycle;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemProvider;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarContentStoreImpl, CalendarWeekCache, ItemTransformer

public final class CalendarContentStoreImpl_Factory
    implements Factory
{

    private final Provider firstDayOfWeekProvider;
    private final Provider itemTransformerProvider;
    private final Provider lifecycleProvider;
    private final Provider providerProvider;
    private final Provider serialExecutorProvider;
    private final Provider timeUtilsProvider;
    private final Provider weekCacheProvider;

    public CalendarContentStoreImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6)
    {
        lifecycleProvider = provider;
        weekCacheProvider = provider1;
        itemTransformerProvider = provider2;
        providerProvider = provider3;
        serialExecutorProvider = provider4;
        timeUtilsProvider = provider5;
        firstDayOfWeekProvider = provider6;
    }

    public final Object get()
    {
        Provider provider = lifecycleProvider;
        Provider provider1 = weekCacheProvider;
        Provider provider2 = itemTransformerProvider;
        Provider provider3 = providerProvider;
        Provider provider4 = serialExecutorProvider;
        Provider provider5 = timeUtilsProvider;
        Provider provider6 = firstDayOfWeekProvider;
        return new CalendarContentStoreImpl((Lifecycle)provider.get(), (CalendarWeekCache)provider1.get(), (ItemTransformer)provider2.get(), (ItemProvider)provider3.get(), (com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor)provider4.get(), (TimeUtils)provider5.get(), (ObservableReference)provider6.get());
    }
}
