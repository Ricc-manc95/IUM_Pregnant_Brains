// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthDrawable

public final class MiniMonthDrawable_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider dimensConverterProvider;
    private final Provider isRtlProvider;
    private final Provider screenTypeProvider;
    private final Provider shouldShowMonthIllustrationsProvider;
    private final Provider shouldShowWeekNumbersProvider;
    private final Provider timeUtilsProvider;

    public MiniMonthDrawable_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6)
    {
        contextProvider = provider;
        dimensConverterProvider = provider1;
        timeUtilsProvider = provider2;
        screenTypeProvider = provider3;
        shouldShowMonthIllustrationsProvider = provider4;
        isRtlProvider = provider5;
        shouldShowWeekNumbersProvider = provider6;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = dimensConverterProvider;
        Provider provider2 = timeUtilsProvider;
        Provider provider3 = screenTypeProvider;
        Provider provider4 = shouldShowMonthIllustrationsProvider;
        Provider provider5 = isRtlProvider;
        Provider provider6 = shouldShowWeekNumbersProvider;
        return new MiniMonthDrawable((Context)provider.get(), (DimensConverter)provider1.get(), (TimeUtils)provider2.get(), (ObservableReference)provider3.get(), (ObservableReference)provider4.get(), (ObservableReference)provider5.get(), (ObservableReference)provider6.get());
    }
}
