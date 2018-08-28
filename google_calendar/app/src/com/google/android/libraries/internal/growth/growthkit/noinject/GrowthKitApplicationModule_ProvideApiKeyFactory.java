// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.noinject:
//            GrowthKitApplicationModule

public final class GrowthKitApplicationModule_ProvideApiKeyFactory
    implements Factory
{

    private final GrowthKitApplicationModule module;

    public GrowthKitApplicationModule_ProvideApiKeyFactory(GrowthKitApplicationModule growthkitapplicationmodule)
    {
        module = growthkitapplicationmodule;
    }

    public final Object get()
    {
        String s = module.params.getApiKey();
        if (s == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (String)s;
        }
    }
}
