// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthViewPagerAdapter, MiniMonthViewBinder, MiniMonthDataAdapter

public final class MiniMonthViewPagerAdapter_Factory
    implements Factory
{

    private final Provider dataAdapterProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewBinderProvider;

    public MiniMonthViewPagerAdapter_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        timeUtilsProvider = provider;
        viewBinderProvider = provider1;
        dataAdapterProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = timeUtilsProvider;
        Provider provider1 = viewBinderProvider;
        Provider provider2 = dataAdapterProvider;
        return new MiniMonthViewPagerAdapter((TimeUtils)provider.get(), (MiniMonthViewBinder)provider1.get(), (MiniMonthDataAdapter)provider2.get());
    }
}
