// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import com.google.android.libraries.internal.growth.growthkit.internal.experiments.proto.GrowthKitProperties;
import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.inject:
//            GrowthKitInternalCommonModule

public final class GrowthKitInternalCommonModule_ProvideGrowthKitPropertiesFactory
    implements Factory
{

    public static final GrowthKitInternalCommonModule_ProvideGrowthKitPropertiesFactory INSTANCE = new GrowthKitInternalCommonModule_ProvideGrowthKitPropertiesFactory();

    public GrowthKitInternalCommonModule_ProvideGrowthKitPropertiesFactory()
    {
    }

    public static GrowthKitProperties proxyProvideGrowthKitProperties()
    {
        GrowthKitProperties growthkitproperties = GrowthKitInternalCommonModule.provideGrowthKitProperties();
        if (growthkitproperties == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (GrowthKitProperties)growthkitproperties;
        }
    }

    public final Object get()
    {
        GrowthKitProperties growthkitproperties = GrowthKitInternalCommonModule.provideGrowthKitProperties();
        if (growthkitproperties == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (GrowthKitProperties)growthkitproperties;
        }
    }

}
