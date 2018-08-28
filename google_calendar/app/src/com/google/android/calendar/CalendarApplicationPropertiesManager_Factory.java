// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Application;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplicationPropertiesManager

public final class CalendarApplicationPropertiesManager_Factory
    implements Factory
{

    private final Provider applicationProvider;

    public CalendarApplicationPropertiesManager_Factory(Provider provider)
    {
        applicationProvider = provider;
    }

    public final Object get()
    {
        return new CalendarApplicationPropertiesManager((Application)applicationProvider.get());
    }
}
