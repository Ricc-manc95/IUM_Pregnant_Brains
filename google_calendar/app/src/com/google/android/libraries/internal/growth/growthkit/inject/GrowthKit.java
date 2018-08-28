// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import android.content.Context;
import com.google.android.apps.common.inject.HasComponent;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.apps.tiktok.inject.SingletonEntryPoints;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.inject:
//            GrowthKitComponent

public final class GrowthKit
{

    private static volatile GrowthKitComponent growthKitComponent = null;
    private static final Logger logger = new Logger();

    public static GrowthKitComponent get(Context context)
    {
        Context context1 = context.getApplicationContext();
        if (context1 instanceof HasComponent)
        {
            return (GrowthKitComponent)((HasComponent)context1).component();
        }
        if (growthKitComponent != null)
        {
            return growthKitComponent;
        }
        try
        {
            context = (GrowthKitComponent)SingletonEntryPoints.getEntryPoint(context, com/google/android/libraries/internal/growth/growthkit/inject/GrowthKitComponent);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            context = logger;
            throw new NullPointerException("Unable to get GrowthKit Component from host app.");
        }
        return context;
    }

    public static void set(GrowthKitComponent growthkitcomponent)
    {
        growthKitComponent = growthkitcomponent;
    }

}
