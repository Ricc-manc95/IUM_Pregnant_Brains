// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutManagerImpl, LayoutUpdaterImpl

public final class LayoutManagerImpl_Factory
    implements Factory
{

    private final Provider idleTrackerProvider;
    private final Provider layoutUpdaterProvider;

    public LayoutManagerImpl_Factory(Provider provider, Provider provider1)
    {
        layoutUpdaterProvider = provider;
        idleTrackerProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = layoutUpdaterProvider;
        Provider provider1 = idleTrackerProvider;
        return new LayoutManagerImpl((LayoutUpdaterImpl)provider.get(), (IdleTracker)provider1.get());
    }
}
