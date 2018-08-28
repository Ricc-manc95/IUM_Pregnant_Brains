// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Application;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            Utils

public final class CalendarApplicationPropertiesModule_ProvidesCurrentJulianDayFactory
    implements Factory
{

    private final Provider applicationProvider;
    private final Provider currentTimeProvider;

    public CalendarApplicationPropertiesModule_ProvidesCurrentJulianDayFactory(Provider provider, Provider provider1)
    {
        applicationProvider = provider;
        currentTimeProvider = provider1;
    }

    public final Object get()
    {
        Object obj = applicationProvider;
        Provider provider = currentTimeProvider;
        obj = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Integer.valueOf(Utils.getJulianDay((Application)((Provider) (obj)).get(), ((Long)((ObservableReference)provider.get()).get()).longValue())));
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)obj;
        }
    }
}
