// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutManager;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.common.base.Supplier;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineAccessibilityDelegate

public final class TimelineAccessibilityDelegate_Factory
    implements Factory
{

    private final Provider idleTrackerProvider;
    private final Provider layoutManagerProvider;
    private final Provider virtualViewsProvider;

    public TimelineAccessibilityDelegate_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        layoutManagerProvider = provider;
        virtualViewsProvider = provider1;
        idleTrackerProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = layoutManagerProvider;
        Provider provider1 = virtualViewsProvider;
        Provider provider2 = idleTrackerProvider;
        return new TimelineAccessibilityDelegate((LayoutManager)provider.get(), (Supplier)provider1.get(), (IdleTracker)provider2.get());
    }
}
