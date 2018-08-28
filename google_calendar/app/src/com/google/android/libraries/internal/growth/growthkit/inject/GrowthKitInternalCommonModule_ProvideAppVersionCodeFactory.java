// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import android.content.Context;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.inject:
//            GrowthKitInternalCommonModule

public final class GrowthKitInternalCommonModule_ProvideAppVersionCodeFactory
    implements Factory
{

    private final Provider contextProvider;

    public GrowthKitInternalCommonModule_ProvideAppVersionCodeFactory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        Optional optional = GrowthKitInternalCommonModule.provideAppVersionCode((Context)contextProvider.get());
        if (optional == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Optional)optional;
        }
    }
}
