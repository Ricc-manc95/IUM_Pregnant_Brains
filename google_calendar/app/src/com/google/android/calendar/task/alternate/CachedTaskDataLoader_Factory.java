// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            CachedTaskDataLoader, SimpleTaskDataLoader

public final class CachedTaskDataLoader_Factory
    implements Factory
{

    private final Provider simpleLoaderProvider;
    private final Provider timeZoneProvider;

    public CachedTaskDataLoader_Factory(Provider provider, Provider provider1)
    {
        timeZoneProvider = provider;
        simpleLoaderProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = timeZoneProvider;
        Provider provider1 = simpleLoaderProvider;
        return new CachedTaskDataLoader((ObservableReference)provider.get(), (SimpleTaskDataLoader)provider1.get());
    }
}
