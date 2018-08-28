// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarWeekCache

public final class CalendarWeekCache_Factory
    implements Factory
{

    private final Provider adapterProvider;
    private final Provider serialExecutorProvider;
    private final Provider timeUtilsProvider;

    public CalendarWeekCache_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        serialExecutorProvider = provider;
        timeUtilsProvider = provider1;
        adapterProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = serialExecutorProvider;
        Provider provider1 = timeUtilsProvider;
        Provider provider2 = adapterProvider;
        return new CalendarWeekCache((com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor)provider.get(), (TimeUtils)provider1.get(), (ItemAdapter)provider2.get());
    }
}
