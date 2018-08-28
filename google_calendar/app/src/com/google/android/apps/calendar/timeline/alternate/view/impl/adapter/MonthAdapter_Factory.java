// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.YearMonthHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            MonthAdapter

public final class MonthAdapter_Factory
    implements Factory
{

    private final Provider timeUtilsProvider;
    private final Provider weeksInMonthProvider;
    private final Provider yearMonthHelperProvider;

    public MonthAdapter_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        timeUtilsProvider = provider;
        yearMonthHelperProvider = provider1;
        weeksInMonthProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = timeUtilsProvider;
        Provider provider1 = yearMonthHelperProvider;
        Provider provider2 = weeksInMonthProvider;
        return new MonthAdapter((TimeUtils)provider.get(), (YearMonthHelper)provider1.get(), ((Integer)provider2.get()).intValue());
    }
}
