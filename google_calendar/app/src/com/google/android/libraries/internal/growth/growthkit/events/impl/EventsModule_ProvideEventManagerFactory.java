// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.events.impl;

import com.google.android.libraries.internal.growth.growthkit.events.GrowthKitEventManager;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.events.impl:
//            GrowthKitEventManagerImpl

public final class EventsModule_ProvideEventManagerFactory
    implements Factory
{

    private final Provider implProvider;

    public EventsModule_ProvideEventManagerFactory(Provider provider)
    {
        implProvider = provider;
    }

    public final Object get()
    {
        GrowthKitEventManagerImpl growthkiteventmanagerimpl = (GrowthKitEventManagerImpl)implProvider.get();
        if (growthkiteventmanagerimpl == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (GrowthKitEventManager)growthkiteventmanagerimpl;
        }
    }
}
