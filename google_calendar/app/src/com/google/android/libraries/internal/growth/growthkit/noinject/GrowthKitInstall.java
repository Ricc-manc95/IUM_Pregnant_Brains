// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import com.google.android.libraries.gcoreclient.auth.impl.GcoreAuthDaggerModule;
import com.google.android.libraries.gcoreclient.common.api.impl.GcoreCommonApiDaggerModule;
import com.google.android.libraries.gcoreclient.pseudonymous.impl.GcorePseudonymousDaggerModule;
import com.google.android.libraries.gcoreclient.security.impl.GcoreSecurityDaggerModule;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKit;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl.PromotionsModule;
import com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl.RpcModule;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitStartup;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.noinject:
//            DaggerGrowthKitApplicationComponent, GrowthKitApplicationModule

public final class GrowthKitInstall
{

    private static GrowthKitComponent component;

    public static void initialize(Params params)
    {
        com/google/android/libraries/internal/growth/growthkit/noinject/GrowthKitInstall;
        JVM INSTR monitorenter ;
        boolean flag;
        if (component == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        throw new IllegalStateException(String.valueOf("GrowthKitInstall must be initialized only once."));
        params;
        com/google/android/libraries/internal/growth/growthkit/noinject/GrowthKitInstall;
        JVM INSTR monitorexit ;
        throw params;
        DaggerGrowthKitApplicationComponent.Builder builder;
        builder = DaggerGrowthKitApplicationComponent.builder();
        params = new GrowthKitApplicationModule(params);
        if (params != null)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        throw new NullPointerException();
        builder.growthKitApplicationModule = (GrowthKitApplicationModule)params;
        if (builder.growthKitApplicationModule == null)
        {
            throw new IllegalStateException(String.valueOf(com/google/android/libraries/internal/growth/growthkit/noinject/GrowthKitApplicationModule.getCanonicalName()).concat(" must be set"));
        }
        if (builder.gcoreAuthDaggerModule == null)
        {
            builder.gcoreAuthDaggerModule = new GcoreAuthDaggerModule();
        }
        if (builder.rpcModule == null)
        {
            builder.rpcModule = new RpcModule();
        }
        if (builder.gcorePseudonymousDaggerModule == null)
        {
            builder.gcorePseudonymousDaggerModule = new GcorePseudonymousDaggerModule();
        }
        if (builder.gcoreCommonApiDaggerModule == null)
        {
            builder.gcoreCommonApiDaggerModule = new GcoreCommonApiDaggerModule();
        }
        if (builder.promotionsModule == null)
        {
            builder.promotionsModule = new PromotionsModule();
        }
        if (builder.gcoreSecurityDaggerModule == null)
        {
            builder.gcoreSecurityDaggerModule = new GcoreSecurityDaggerModule();
        }
        params = new DaggerGrowthKitApplicationComponent(builder);
        component = params;
        GrowthKit.set(params);
        component.getGrowthKitStartup().start();
        com/google/android/libraries/internal/growth/growthkit/noinject/GrowthKitInstall;
        JVM INSTR monitorexit ;
    }
}
