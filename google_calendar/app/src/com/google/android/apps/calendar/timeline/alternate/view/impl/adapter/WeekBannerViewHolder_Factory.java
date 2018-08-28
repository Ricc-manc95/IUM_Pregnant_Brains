// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            WeekBannerViewHolder

public final class WeekBannerViewHolder_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider scheduleProvider;
    private final Provider shouldShowWeekNumbersProvider;
    private final Provider timeUtilsProvider;
    private final Provider weekBannerClickCallbackProvider;

    public WeekBannerViewHolder_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4)
    {
        contextProvider = provider;
        timeUtilsProvider = provider1;
        scheduleProvider = provider2;
        shouldShowWeekNumbersProvider = provider3;
        weekBannerClickCallbackProvider = provider4;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = timeUtilsProvider;
        Provider provider2 = scheduleProvider;
        Provider provider3 = shouldShowWeekNumbersProvider;
        Provider provider4 = weekBannerClickCallbackProvider;
        return new WeekBannerViewHolder((Context)provider.get(), (TimeUtils)provider1.get(), (ScheduleProvider)provider2.get(), (ObservableReference)provider3.get(), (Runnable)provider4.get());
    }
}
