// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.content.Context;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineRecyclerView

public final class TimelineRecyclerView_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider isA11yEnabledProvider;
    private final Provider weeksInMonthProvider;

    public TimelineRecyclerView_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        contextProvider = provider;
        weeksInMonthProvider = provider1;
        isA11yEnabledProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = weeksInMonthProvider;
        Provider provider2 = isA11yEnabledProvider;
        return new TimelineRecyclerView((Context)provider.get(), ((Integer)provider1.get()).intValue(), (ObservableReference)provider2.get());
    }
}
