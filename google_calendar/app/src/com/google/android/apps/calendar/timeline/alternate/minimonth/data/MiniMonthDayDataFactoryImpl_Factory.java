// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth.data;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth.data:
//            MiniMonthDayDataFactoryImpl

public final class MiniMonthDayDataFactoryImpl_Factory
    implements Factory
{

    private final Provider alternateCalendarHelperProvider;
    private final Provider contextProvider;
    private final Provider timeUtilsProvider;

    public MiniMonthDayDataFactoryImpl_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        contextProvider = provider;
        alternateCalendarHelperProvider = provider1;
        timeUtilsProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = alternateCalendarHelperProvider;
        Provider provider2 = timeUtilsProvider;
        return new MiniMonthDayDataFactoryImpl((Context)provider.get(), (AlternateCalendarHelper)provider1.get(), (TimeUtils)provider2.get());
    }
}
