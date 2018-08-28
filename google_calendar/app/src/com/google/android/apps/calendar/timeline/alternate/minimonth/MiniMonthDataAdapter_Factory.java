// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import com.google.android.apps.calendar.timeline.alternate.minimonth.data.MiniMonthDayDataFactory;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthDataAdapter

public final class MiniMonthDataAdapter_Factory
    implements Factory
{

    private final Provider dayDataFactoryProvider;
    private final Provider storeProvider;
    private final Provider timeUtilsProvider;

    public MiniMonthDataAdapter_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        storeProvider = provider;
        dayDataFactoryProvider = provider1;
        timeUtilsProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = storeProvider;
        Provider provider1 = dayDataFactoryProvider;
        Provider provider2 = timeUtilsProvider;
        return new MiniMonthDataAdapter((CalendarContentStore)provider.get(), (MiniMonthDayDataFactory)provider1.get(), (TimeUtils)provider2.get());
    }
}
