// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteVisualElementEventsStoreFactory

public final class StorageCommonModule_ProvideVisualElementEventCounterFactory
    implements Factory
{

    private final Provider factoryProvider;

    public StorageCommonModule_ProvideVisualElementEventCounterFactory(Provider provider)
    {
        factoryProvider = provider;
    }

    public final Object get()
    {
        Object obj = (SqliteVisualElementEventsStoreFactory)factoryProvider.get();
        obj.getClass();
        obj = new PerAccountProvider(new StorageCommonModule..Lambda._cls6(((SqliteVisualElementEventsStoreFactory) (obj))));
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (PerAccountProvider)obj;
        }
    }
}
