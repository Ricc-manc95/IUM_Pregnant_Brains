// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthView, MiniMonthDrawable, MiniMonthGeometry

public final class MiniMonthView_Factory
    implements Factory
{

    private final Provider alternateCalendarHelperProvider;
    private final Provider contextProvider;
    private final Provider drawableProvider;
    private final Provider geometryProvider;
    private final Provider timeUtilsProvider;

    public MiniMonthView_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4)
    {
        contextProvider = provider;
        drawableProvider = provider1;
        geometryProvider = provider2;
        timeUtilsProvider = provider3;
        alternateCalendarHelperProvider = provider4;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = drawableProvider;
        Provider provider2 = geometryProvider;
        Provider provider3 = timeUtilsProvider;
        Provider provider4 = alternateCalendarHelperProvider;
        return new MiniMonthView((Context)provider.get(), (MiniMonthDrawable)provider1.get(), (MiniMonthGeometry)provider2.get(), (TimeUtils)provider3.get(), (AlternateCalendarHelper)provider4.get());
    }
}
