// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import android.arch.lifecycle.LifecycleOwner;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.calendar.task.TaskDataFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            TaskCacheInvalidator

public final class TaskCacheInvalidator_Factory
    implements Factory
{

    private final Provider lifecycleOwnerProvider;
    private final Provider settingsCacheProvider;
    private final Provider taskCacheProvider;
    private final Provider taskDataFactoryProvider;

    public TaskCacheInvalidator_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        settingsCacheProvider = provider;
        taskDataFactoryProvider = provider1;
        lifecycleOwnerProvider = provider2;
        taskCacheProvider = provider3;
    }

    public final Object get()
    {
        Provider provider = settingsCacheProvider;
        Provider provider1 = taskDataFactoryProvider;
        Provider provider2 = lifecycleOwnerProvider;
        Provider provider3 = taskCacheProvider;
        return new TaskCacheInvalidator((ListenableFutureCache)provider.get(), (TaskDataFactory)provider1.get(), (LifecycleOwner)provider2.get(), (ListenableFutureCache)provider3.get());
    }
}
