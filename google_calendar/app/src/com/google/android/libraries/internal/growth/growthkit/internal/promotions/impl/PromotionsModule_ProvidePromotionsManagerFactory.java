// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.promotions.PromotionsManager;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            PromotionsManagerImpl, PromotionsModule

public final class PromotionsModule_ProvidePromotionsManagerFactory
    implements Factory
{

    private final Provider implProvider;
    private final PromotionsModule module;

    public PromotionsModule_ProvidePromotionsManagerFactory(PromotionsModule promotionsmodule, Provider provider)
    {
        module = promotionsmodule;
        implProvider = provider;
    }

    public final Object get()
    {
        Object obj = module;
        obj = (PromotionsManagerImpl)implProvider.get();
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (PromotionsManager)obj;
        }
    }
}
