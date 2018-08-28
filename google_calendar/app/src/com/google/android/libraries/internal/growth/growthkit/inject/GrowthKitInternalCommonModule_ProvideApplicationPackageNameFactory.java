// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.inject:
//            GrowthKitInternalCommonModule

public final class GrowthKitInternalCommonModule_ProvideApplicationPackageNameFactory
    implements Factory
{

    private final Provider contextProvider;

    public GrowthKitInternalCommonModule_ProvideApplicationPackageNameFactory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        String s = GrowthKitInternalCommonModule.provideApplicationPackageName((Context)contextProvider.get());
        if (s == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (String)s;
        }
    }
}
