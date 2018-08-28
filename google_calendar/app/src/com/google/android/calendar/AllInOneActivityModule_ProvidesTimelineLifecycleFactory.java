// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.arch.lifecycle.Lifecycle;
import android.support.v4.app.SupportActivity;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

public final class AllInOneActivityModule_ProvidesTimelineLifecycleFactory
    implements Factory
{

    private final Provider activityProvider;

    public AllInOneActivityModule_ProvidesTimelineLifecycleFactory(Provider provider)
    {
        activityProvider = provider;
    }

    public final Object get()
    {
        Lifecycle lifecycle = ((AllInOneCalendarActivity)activityProvider.get()).getLifecycle();
        if (lifecycle == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Lifecycle)lifecycle;
        }
    }
}
