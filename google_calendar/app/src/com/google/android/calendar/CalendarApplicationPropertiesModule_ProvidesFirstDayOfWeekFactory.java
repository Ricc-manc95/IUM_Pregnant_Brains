// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplicationPropertiesManager

public final class CalendarApplicationPropertiesModule_ProvidesFirstDayOfWeekFactory
    implements Factory
{

    private final Provider managerProvider;

    public CalendarApplicationPropertiesModule_ProvidesFirstDayOfWeekFactory(Provider provider)
    {
        managerProvider = provider;
    }

    public static ObservableReference proxyProvidesFirstDayOfWeek(Object obj)
    {
        obj = ((CalendarApplicationPropertiesManager)obj).firstDayOfWeek;
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)obj;
        }
    }

    public final Object get()
    {
        ObservableReference observablereference = ((CalendarApplicationPropertiesManager)managerProvider.get()).firstDayOfWeek;
        if (observablereference == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)observablereference;
        }
    }
}
