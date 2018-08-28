// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            MonthBannerViewHolder, MonthBannerView

public final class MonthBannerViewHolder_Factory
    implements Factory
{

    private final Provider scheduleProvider;
    private final Provider screenTypeProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewProvider;

    public MonthBannerViewHolder_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        viewProvider = provider;
        timeUtilsProvider = provider1;
        scheduleProvider = provider2;
        screenTypeProvider = provider3;
    }

    public final Object get()
    {
        Provider provider = viewProvider;
        Provider provider1 = timeUtilsProvider;
        Provider provider2 = scheduleProvider;
        Provider provider3 = screenTypeProvider;
        return new MonthBannerViewHolder((MonthBannerView)provider.get(), (TimeUtils)provider1.get(), (ScheduleProvider)provider2.get(), (ObservableReference)provider3.get());
    }
}
