// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            WeekdayNames

public final class WeekdayNames_Factory
    implements Factory
{

    private final Provider screenTypeProvider;
    private final Provider timeUtilsProvider;

    public WeekdayNames_Factory(Provider provider, Provider provider1)
    {
        screenTypeProvider = provider;
        timeUtilsProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = screenTypeProvider;
        Provider provider1 = timeUtilsProvider;
        return new WeekdayNames((ObservableReference)provider.get(), (TimeUtils)provider1.get());
    }
}
