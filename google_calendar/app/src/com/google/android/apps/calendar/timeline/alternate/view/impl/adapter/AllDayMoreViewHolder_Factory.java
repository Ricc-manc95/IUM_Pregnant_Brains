// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AllDayMoreViewHolder, TimelineAdapter

public final class AllDayMoreViewHolder_Factory
    implements Factory
{

    private final Provider adapterProvider;
    private final Provider allDayExpandedObservableProvider;
    private final Provider contextProvider;
    private final Provider dataSetChangedObservableProvider;
    private final Provider dimensConverterProvider;

    public AllDayMoreViewHolder_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4)
    {
        contextProvider = provider;
        adapterProvider = provider1;
        dimensConverterProvider = provider2;
        dataSetChangedObservableProvider = provider3;
        allDayExpandedObservableProvider = provider4;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = adapterProvider;
        Provider provider2 = dimensConverterProvider;
        Provider provider3 = dataSetChangedObservableProvider;
        Provider provider4 = allDayExpandedObservableProvider;
        return new AllDayMoreViewHolder((Context)provider.get(), (TimelineAdapter)provider1.get(), (DimensConverter)provider2.get(), (ObservableReference)provider3.get(), (ObservableReference)provider4.get());
    }
}
