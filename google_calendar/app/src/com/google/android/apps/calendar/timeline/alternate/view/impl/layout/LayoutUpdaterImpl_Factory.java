// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import android.support.v7.widget.RecyclerView;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutUpdaterImpl

public final class LayoutUpdaterImpl_Factory
    implements Factory
{

    private final Provider animatorSetFutureProvider;
    private final Provider fullyLoadedProvider;
    private final Provider isA11yEnabledProvider;
    private final Provider isVisibleSupplierProvider;
    private final Provider recyclerViewProvider;

    public LayoutUpdaterImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4)
    {
        recyclerViewProvider = provider;
        animatorSetFutureProvider = provider1;
        isA11yEnabledProvider = provider2;
        fullyLoadedProvider = provider3;
        isVisibleSupplierProvider = provider4;
    }

    public final Object get()
    {
        Provider provider = recyclerViewProvider;
        Provider provider1 = animatorSetFutureProvider;
        Provider provider2 = isA11yEnabledProvider;
        Provider provider3 = fullyLoadedProvider;
        Provider provider4 = isVisibleSupplierProvider;
        return new LayoutUpdaterImpl((RecyclerView)provider.get(), provider1, (ObservableReference)provider2.get(), (ObservableReference)provider3.get(), (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.IsVisibleSupplier)provider4.get());
    }
}
