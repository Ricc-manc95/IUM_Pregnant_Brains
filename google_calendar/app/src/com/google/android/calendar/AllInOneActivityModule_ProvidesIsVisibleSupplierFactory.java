// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

public final class AllInOneActivityModule_ProvidesIsVisibleSupplierFactory
    implements Factory
{

    private final Provider activityProvider;

    public AllInOneActivityModule_ProvidesIsVisibleSupplierFactory(Provider provider)
    {
        activityProvider = provider;
    }

    public final Object get()
    {
        AllInOneActivityModule..Lambda._cls7 _lcls7 = new AllInOneActivityModule..Lambda._cls7((AllInOneCalendarActivity)activityProvider.get());
        if (_lcls7 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.IsVisibleSupplier)_lcls7;
        }
    }
}
