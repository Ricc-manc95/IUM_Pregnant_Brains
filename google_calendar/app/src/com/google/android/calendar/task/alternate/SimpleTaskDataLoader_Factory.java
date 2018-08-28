// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import android.content.Context;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            SimpleTaskDataLoader

public final class SimpleTaskDataLoader_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider settingsCacheProvider;

    public SimpleTaskDataLoader_Factory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        settingsCacheProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = settingsCacheProvider;
        return new SimpleTaskDataLoader((Context)provider.get(), (ListenableFutureCache)provider1.get());
    }
}
