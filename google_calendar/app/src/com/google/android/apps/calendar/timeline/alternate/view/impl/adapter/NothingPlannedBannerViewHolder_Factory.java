// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            NothingPlannedBannerViewHolder

public final class NothingPlannedBannerViewHolder_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider onClickListenerProvider;
    private final Provider scheduleProvider;

    public NothingPlannedBannerViewHolder_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        contextProvider = provider;
        scheduleProvider = provider1;
        onClickListenerProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = scheduleProvider;
        Provider provider2 = onClickListenerProvider;
        return new NothingPlannedBannerViewHolder((Context)provider.get(), (ScheduleProvider)provider1.get(), (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.NothingPlannedBannerOnClickListener)provider2.get());
    }
}
