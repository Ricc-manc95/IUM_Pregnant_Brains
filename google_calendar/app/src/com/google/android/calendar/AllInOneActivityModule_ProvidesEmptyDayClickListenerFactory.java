// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.timeline.alternate.DefaultBundleFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

public final class AllInOneActivityModule_ProvidesEmptyDayClickListenerFactory
    implements Factory
{

    private final Provider activityProvider;
    private final Provider defaultBundleFactoryProvider;

    public AllInOneActivityModule_ProvidesEmptyDayClickListenerFactory(Provider provider, Provider provider1)
    {
        activityProvider = provider;
        defaultBundleFactoryProvider = provider1;
    }

    public final Object get()
    {
        Object obj = activityProvider;
        Provider provider = defaultBundleFactoryProvider;
        obj = new AllInOneActivityModule..Lambda._cls2((AllInOneCalendarActivity)((Provider) (obj)).get(), (DefaultBundleFactory)provider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.NothingPlannedBannerOnClickListener)obj;
        }
    }
}
