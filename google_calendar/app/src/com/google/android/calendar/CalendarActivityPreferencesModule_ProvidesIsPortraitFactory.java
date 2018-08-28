// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            CalendarActivityPropertiesManager

public final class CalendarActivityPreferencesModule_ProvidesIsPortraitFactory
    implements Factory
{

    private final Provider managerProvider;

    public CalendarActivityPreferencesModule_ProvidesIsPortraitFactory(Provider provider)
    {
        managerProvider = provider;
    }

    public final Object get()
    {
        ObservableReference observablereference = ((CalendarActivityPropertiesManager)managerProvider.get()).isPortrait;
        if (observablereference == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)observablereference;
        }
    }
}
