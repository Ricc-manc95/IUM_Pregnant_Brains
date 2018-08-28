// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            DayHeaderViewHolder

public final class DayHeaderViewHolder_Factory
    implements Factory
{

    private final Provider alternateCalendarHelperProvider;
    private final Provider clickListenerProvider;
    private final Provider contextProvider;
    private final Provider currentJulianDayProvider;
    private final Provider drawableProvider;
    private final Provider isA11yEnabledProvider;
    private final Provider nextModeSupplierProvider;
    private final Provider timeUtilsProvider;
    private final Provider timeZoneObservableProvider;

    public DayHeaderViewHolder_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8)
    {
        contextProvider = provider;
        isA11yEnabledProvider = provider1;
        drawableProvider = provider2;
        timeUtilsProvider = provider3;
        timeZoneObservableProvider = provider4;
        clickListenerProvider = provider5;
        nextModeSupplierProvider = provider6;
        currentJulianDayProvider = provider7;
        alternateCalendarHelperProvider = provider8;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = isA11yEnabledProvider;
        Provider provider2 = drawableProvider;
        Provider provider3 = timeUtilsProvider;
        Provider provider4 = timeZoneObservableProvider;
        Provider provider5 = clickListenerProvider;
        Provider provider6 = nextModeSupplierProvider;
        Provider provider7 = currentJulianDayProvider;
        Provider provider8 = alternateCalendarHelperProvider;
        return new DayHeaderViewHolder((Context)provider.get(), (ObservableReference)provider1.get(), (DayHeaderViewHolder.HeaderDrawable)provider2.get(), (TimeUtils)provider3.get(), (ObservableReference)provider4.get(), (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderClickListener)provider5.get(), (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier)provider6.get(), (ObservableReference)provider7.get(), (AlternateCalendarHelper)provider8.get());
    }
}
