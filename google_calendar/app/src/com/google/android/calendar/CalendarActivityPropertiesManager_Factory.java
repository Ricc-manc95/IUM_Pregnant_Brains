// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            CalendarActivityPropertiesManager

public final class CalendarActivityPropertiesManager_Factory
    implements Factory
{

    private final Provider activityProvider;

    public CalendarActivityPropertiesManager_Factory(Provider provider)
    {
        activityProvider = provider;
    }

    public final Object get()
    {
        return new CalendarActivityPropertiesManager((Activity)activityProvider.get());
    }
}
