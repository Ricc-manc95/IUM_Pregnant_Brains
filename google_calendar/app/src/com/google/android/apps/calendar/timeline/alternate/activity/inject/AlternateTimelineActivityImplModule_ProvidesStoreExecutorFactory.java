// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.activity.inject;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.activity.inject:
//            AlternateTimelineActivityImplModule

public final class AlternateTimelineActivityImplModule_ProvidesStoreExecutorFactory
    implements Factory
{

    private final Provider alternateTimelineEnabledProvider;

    public AlternateTimelineActivityImplModule_ProvidesStoreExecutorFactory(Provider provider)
    {
        alternateTimelineEnabledProvider = provider;
    }

    public final Object get()
    {
        com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialexecutor = AlternateTimelineActivityImplModule.providesStoreExecutor(((Boolean)alternateTimelineEnabledProvider.get()).booleanValue());
        if (serialexecutor == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor)serialexecutor;
        }
    }
}
