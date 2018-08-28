// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthViewBinder, MiniMonthDataAdapter

public final class MiniMonthViewBinder_Factory
    implements Factory
{

    private final Provider currentTimeProvider;
    private final Provider dataAdapterProvider;
    private final Provider miniMonthViewProvider;
    private final Provider timeUtilsProvider;

    public MiniMonthViewBinder_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        miniMonthViewProvider = provider;
        currentTimeProvider = provider1;
        timeUtilsProvider = provider2;
        dataAdapterProvider = provider3;
    }

    public final Object get()
    {
        Provider provider = miniMonthViewProvider;
        Provider provider1 = currentTimeProvider;
        Provider provider2 = timeUtilsProvider;
        Provider provider3 = dataAdapterProvider;
        return new MiniMonthViewBinder(provider, (ObservableReference)provider1.get(), (TimeUtils)provider2.get(), (MiniMonthDataAdapter)provider3.get());
    }
}
