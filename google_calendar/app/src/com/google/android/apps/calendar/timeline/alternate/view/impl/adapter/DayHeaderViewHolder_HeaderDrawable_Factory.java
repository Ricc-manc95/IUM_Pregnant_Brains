// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DayHeaderViewHolder_HeaderDrawable_Factory
    implements Factory
{

    private final Provider alternateCalendarHelperProvider;
    private final Provider contextProvider;
    private final Provider dimensConverterProvider;
    private final Provider isRtlProvider;
    private final Provider screenTypeProvider;

    public DayHeaderViewHolder_HeaderDrawable_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4)
    {
        contextProvider = provider;
        dimensConverterProvider = provider1;
        isRtlProvider = provider2;
        screenTypeProvider = provider3;
        alternateCalendarHelperProvider = provider4;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = dimensConverterProvider;
        Provider provider2 = isRtlProvider;
        Provider provider3 = screenTypeProvider;
        Provider provider4 = alternateCalendarHelperProvider;
        return new DayHeaderViewHolder.HeaderDrawable((Context)provider.get(), (DimensConverter)provider1.get(), (ObservableReference)provider2.get(), (ObservableReference)provider3.get(), (AlternateCalendarHelper)provider4.get());
    }
}
