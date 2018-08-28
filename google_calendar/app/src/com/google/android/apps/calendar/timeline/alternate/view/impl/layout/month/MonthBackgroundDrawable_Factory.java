// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.YearMonthHelper;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthBackgroundDrawable, MonthViewport, WeekdayNames

public final class MonthBackgroundDrawable_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider currentTimeProvider;
    private final Provider dimensConverterProvider;
    private final Provider dimensProvider;
    private final Provider isPortraitProvider;
    private final Provider isRtlProvider;
    private final Provider screenTypeProvider;
    private final Provider shouldShowWeekNumbersProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewportProvider;
    private final Provider weekdayNamesProvider;
    private final Provider weeksInMonthProvider;
    private final Provider yearMonthHelperProvider;

    public MonthBackgroundDrawable_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12)
    {
        contextProvider = provider;
        timeUtilsProvider = provider1;
        dimensProvider = provider2;
        viewportProvider = provider3;
        yearMonthHelperProvider = provider4;
        weekdayNamesProvider = provider5;
        currentTimeProvider = provider6;
        weeksInMonthProvider = provider7;
        isRtlProvider = provider8;
        screenTypeProvider = provider9;
        isPortraitProvider = provider10;
        shouldShowWeekNumbersProvider = provider11;
        dimensConverterProvider = provider12;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = timeUtilsProvider;
        Provider provider2 = dimensProvider;
        Provider provider3 = viewportProvider;
        Provider provider4 = yearMonthHelperProvider;
        Provider provider5 = weekdayNamesProvider;
        Provider provider6 = currentTimeProvider;
        Provider provider7 = weeksInMonthProvider;
        Provider provider8 = isRtlProvider;
        Provider provider9 = screenTypeProvider;
        Provider provider10 = isPortraitProvider;
        Provider provider11 = shouldShowWeekNumbersProvider;
        Provider provider12 = dimensConverterProvider;
        return new MonthBackgroundDrawable((Context)provider.get(), (TimeUtils)provider1.get(), (LayoutDimens)provider2.get(), (MonthViewport)provider3.get(), (YearMonthHelper)provider4.get(), (WeekdayNames)provider5.get(), (ObservableReference)provider6.get(), ((Integer)provider7.get()).intValue(), (ObservableReference)provider8.get(), (ObservableReference)provider9.get(), (ObservableReference)provider10.get(), (ObservableReference)provider11.get(), (DimensConverter)provider12.get());
    }
}
