// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.util;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.util:
//            TimeUtils

public final class TimeUtils_Factory
    implements Factory
{

    private final Provider firstDayOfWeekProvider;
    private final Provider timeZoneProvider;

    public TimeUtils_Factory(Provider provider, Provider provider1)
    {
        timeZoneProvider = provider;
        firstDayOfWeekProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = timeZoneProvider;
        Provider provider1 = firstDayOfWeekProvider;
        return new TimeUtils((ObservableReference)provider.get(), (ObservableReference)provider1.get());
    }
}
