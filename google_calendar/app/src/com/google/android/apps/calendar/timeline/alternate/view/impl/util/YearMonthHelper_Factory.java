// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.util;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.util:
//            YearMonthHelper

public final class YearMonthHelper_Factory
    implements Factory
{

    private final Provider timeUtilsProvider;
    private final Provider weeksInMonthProvider;

    public YearMonthHelper_Factory(Provider provider, Provider provider1)
    {
        timeUtilsProvider = provider;
        weeksInMonthProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = timeUtilsProvider;
        Provider provider1 = weeksInMonthProvider;
        return new YearMonthHelper((TimeUtils)provider.get(), ((Integer)provider1.get()).intValue());
    }
}
