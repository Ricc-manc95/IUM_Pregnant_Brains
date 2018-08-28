// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.YearMonthHelper;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthViewport

public final class MonthViewport_Factory
    implements Factory
{

    private final Provider currentTimeProvider;
    private final Provider dimensConverterProvider;
    private final Provider dimensProvider;
    private final Provider isRtlProvider;
    private final Provider screenTypeProvider;
    private final Provider shouldShowWeekNumbersProvider;
    private final Provider yearMonthHelperProvider;

    public MonthViewport_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6)
    {
        yearMonthHelperProvider = provider;
        isRtlProvider = provider1;
        screenTypeProvider = provider2;
        shouldShowWeekNumbersProvider = provider3;
        currentTimeProvider = provider4;
        dimensProvider = provider5;
        dimensConverterProvider = provider6;
    }

    public final Object get()
    {
        Provider provider = yearMonthHelperProvider;
        Provider provider1 = isRtlProvider;
        Provider provider2 = screenTypeProvider;
        Provider provider3 = shouldShowWeekNumbersProvider;
        Provider provider4 = currentTimeProvider;
        Provider provider5 = dimensProvider;
        Provider provider6 = dimensConverterProvider;
        return new MonthViewport((YearMonthHelper)provider.get(), (ObservableReference)provider1.get(), (ObservableReference)provider2.get(), (ObservableReference)provider3.get(), (ObservableReference)provider4.get(), (LayoutDimens)provider5.get(), (DimensConverter)provider6.get());
    }
}
