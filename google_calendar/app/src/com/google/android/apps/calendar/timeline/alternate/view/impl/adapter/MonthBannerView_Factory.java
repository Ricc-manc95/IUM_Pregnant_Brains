// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            MonthBannerView

public final class MonthBannerView_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider dimensConverterProvider;
    private final Provider isRtlProvider;
    private final Provider scheduleProvider;
    private final Provider screenTypeProvider;

    public MonthBannerView_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4)
    {
        contextProvider = provider;
        isRtlProvider = provider1;
        scheduleProvider = provider2;
        screenTypeProvider = provider3;
        dimensConverterProvider = provider4;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = isRtlProvider;
        Provider provider2 = scheduleProvider;
        Provider provider3 = screenTypeProvider;
        Provider provider4 = dimensConverterProvider;
        return new MonthBannerView((Context)provider.get(), (ObservableReference)provider1.get(), (ScheduleProvider)provider2.get(), (ObservableReference)provider3.get(), (DimensConverter)provider4.get());
    }
}
