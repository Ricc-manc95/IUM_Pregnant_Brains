// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthControllerImpl, MiniMonthViewPager, MiniMonthViewPagerAdapter

public final class MiniMonthControllerImpl_Factory
    implements Factory
{

    private final Provider adapterProvider;
    private final Provider dimensConverterProvider;
    private final Provider isA11yEnabledProvider;
    private final Provider isPortraitProvider;
    private final Provider screenTypeProvider;
    private final Provider shouldShowWeekNumbersProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewPagerProvider;

    public MiniMonthControllerImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7)
    {
        viewPagerProvider = provider;
        adapterProvider = provider1;
        screenTypeProvider = provider2;
        isPortraitProvider = provider3;
        isA11yEnabledProvider = provider4;
        shouldShowWeekNumbersProvider = provider5;
        dimensConverterProvider = provider6;
        timeUtilsProvider = provider7;
    }

    public final Object get()
    {
        Provider provider = viewPagerProvider;
        Provider provider1 = adapterProvider;
        Provider provider2 = screenTypeProvider;
        Provider provider3 = isPortraitProvider;
        Provider provider4 = isA11yEnabledProvider;
        Provider provider5 = shouldShowWeekNumbersProvider;
        Provider provider6 = dimensConverterProvider;
        Provider provider7 = timeUtilsProvider;
        return new MiniMonthControllerImpl((MiniMonthViewPager)provider.get(), (MiniMonthViewPagerAdapter)provider1.get(), (ObservableReference)provider2.get(), (ObservableReference)provider3.get(), (ObservableReference)provider4.get(), (ObservableReference)provider5.get(), (DimensConverter)provider6.get(), (TimeUtils)provider7.get());
    }
}
