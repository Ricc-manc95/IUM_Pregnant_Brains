// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timebox.task.TaskDataLoader;
import com.google.android.apps.calendar.timebox.task.TasksApi;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AllInOneActivityModule_ProvidesTasksApiFactory
    implements Factory
{

    private final Provider dataLoaderProvider;
    private final Provider timeZoneProvider;

    public AllInOneActivityModule_ProvidesTasksApiFactory(Provider provider, Provider provider1)
    {
        dataLoaderProvider = provider;
        timeZoneProvider = provider1;
    }

    public final Object get()
    {
        Object obj = dataLoaderProvider;
        Provider provider = timeZoneProvider;
        obj = new TasksApi((TaskDataLoader)((Provider) (obj)).get(), (ObservableReference)provider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (TasksApi)obj;
        }
    }
}
