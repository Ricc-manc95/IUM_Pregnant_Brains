// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.month.DayView;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            MonthDayViewHolder, MonthAdapter

public final class MonthDayViewHolder_Factory
    implements Factory
{

    private final Provider alternateCalendarHelperProvider;
    private final Provider clickListenerProvider;
    private final Provider contextProvider;
    private final Provider currentJulianDayProvider;
    private final Provider dataSetChangedObservableProvider;
    private final Provider dayViewProvider;
    private final Provider isA11yEnabledProvider;
    private final Provider itemAdapterProvider;
    private final Provider maxEventsPerDayProvider;
    private final Provider monthAdapterProvider;
    private final Provider timeUtilsProvider;

    public MonthDayViewHolder_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10)
    {
        contextProvider = provider;
        timeUtilsProvider = provider1;
        itemAdapterProvider = provider2;
        monthAdapterProvider = provider3;
        currentJulianDayProvider = provider4;
        clickListenerProvider = provider5;
        maxEventsPerDayProvider = provider6;
        dataSetChangedObservableProvider = provider7;
        isA11yEnabledProvider = provider8;
        alternateCalendarHelperProvider = provider9;
        dayViewProvider = provider10;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = timeUtilsProvider;
        Provider provider2 = itemAdapterProvider;
        Provider provider3 = monthAdapterProvider;
        Provider provider4 = currentJulianDayProvider;
        Provider provider5 = clickListenerProvider;
        Provider provider6 = maxEventsPerDayProvider;
        Provider provider7 = dataSetChangedObservableProvider;
        Provider provider8 = isA11yEnabledProvider;
        Provider provider9 = alternateCalendarHelperProvider;
        Provider provider10 = dayViewProvider;
        return new MonthDayViewHolder((Context)provider.get(), (TimeUtils)provider1.get(), (ItemAdapter)provider2.get(), (MonthAdapter)provider3.get(), (ObservableReference)provider4.get(), (Consumer)provider5.get(), (ObservableReference)provider6.get(), (ObservableReference)provider7.get(), (ObservableReference)provider8.get(), (AlternateCalendarHelper)provider9.get(), (DayView)provider10.get());
    }
}
