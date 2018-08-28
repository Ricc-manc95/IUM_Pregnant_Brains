// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            AllDayManager, ColumnViewport, ColumnDimens

public final class AllDayManager_Factory
    implements Factory
{

    private final Provider adapterProvider;
    private final Provider columnDimensProvider;
    private final Provider hostViewProvider;
    private final Provider idleTrackerProvider;
    private final Provider isExpandedProvider;
    private final Provider isVisibleSupplierProvider;
    private final Provider layoutDimensProvider;
    private final Provider viewportProvider;

    public AllDayManager_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7)
    {
        hostViewProvider = provider;
        adapterProvider = provider1;
        viewportProvider = provider2;
        columnDimensProvider = provider3;
        layoutDimensProvider = provider4;
        isExpandedProvider = provider5;
        idleTrackerProvider = provider6;
        isVisibleSupplierProvider = provider7;
    }

    public final Object get()
    {
        Provider provider = hostViewProvider;
        Provider provider1 = adapterProvider;
        Provider provider2 = viewportProvider;
        Provider provider3 = columnDimensProvider;
        Provider provider4 = layoutDimensProvider;
        Provider provider5 = isExpandedProvider;
        Provider provider6 = idleTrackerProvider;
        Provider provider7 = isVisibleSupplierProvider;
        return new AllDayManager((TimelineHostView)provider.get(), (TimelineAdapter)provider1.get(), (ColumnViewport)provider2.get(), (ColumnDimens)provider3.get(), (LayoutDimens)provider4.get(), (ObservableReference)provider5.get(), (IdleTracker)provider6.get(), (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.IsVisibleSupplier)provider7.get());
    }
}
