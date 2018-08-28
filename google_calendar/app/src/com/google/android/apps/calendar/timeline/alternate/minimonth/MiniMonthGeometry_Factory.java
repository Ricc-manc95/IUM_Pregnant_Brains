// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthGeometry

public final class MiniMonthGeometry_Factory
    implements Factory
{

    private final Provider alternateCalendarHelperProvider;
    private final Provider contextProvider;
    private final Provider dimensConverterProvider;
    private final Provider isPortraitProvider;
    private final Provider isRtlProvider;
    private final Provider screenTypeProvider;
    private final Provider shouldShowWeekNumbersProvider;
    private final Provider timeUtilsProvider;

    public MiniMonthGeometry_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7)
    {
        contextProvider = provider;
        dimensConverterProvider = provider1;
        timeUtilsProvider = provider2;
        isRtlProvider = provider3;
        screenTypeProvider = provider4;
        isPortraitProvider = provider5;
        shouldShowWeekNumbersProvider = provider6;
        alternateCalendarHelperProvider = provider7;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = dimensConverterProvider;
        Provider provider2 = timeUtilsProvider;
        Provider provider3 = isRtlProvider;
        Provider provider4 = screenTypeProvider;
        Provider provider5 = isPortraitProvider;
        Provider provider6 = shouldShowWeekNumbersProvider;
        Provider provider7 = alternateCalendarHelperProvider;
        return new MiniMonthGeometry((Context)provider.get(), (DimensConverter)provider1.get(), (TimeUtils)provider2.get(), (ObservableReference)provider3.get(), (ObservableReference)provider4.get(), (ObservableReference)provider5.get(), (ObservableReference)provider6.get(), (AlternateCalendarHelper)provider7.get());
    }
}
