// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.AlternateTimelineChipViewModelFactory;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.timely.MonthBackgrounds;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            ScheduleProviderImpl, ChipHeights

public final class ScheduleProviderImpl_Factory
    implements Factory
{

    private final Provider chipHeightsProvider;
    private final Provider contextProvider;
    private final Provider dateTimeFormatHelperProvider;
    private final Provider monthBackgroundsProvider;
    private final Provider viewModelFactoryProvider;

    public ScheduleProviderImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4)
    {
        contextProvider = provider;
        monthBackgroundsProvider = provider1;
        dateTimeFormatHelperProvider = provider2;
        viewModelFactoryProvider = provider3;
        chipHeightsProvider = provider4;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = monthBackgroundsProvider;
        Provider provider2 = dateTimeFormatHelperProvider;
        Provider provider3 = viewModelFactoryProvider;
        Provider provider4 = chipHeightsProvider;
        return new ScheduleProviderImpl((Context)provider.get(), (MonthBackgrounds)provider1.get(), (DateTimeFormatHelper)provider2.get(), (AlternateTimelineChipViewModelFactory)provider3.get(), (ChipHeights)provider4.get());
    }
}
