// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import android.content.Context;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.inject:
//            GrowthKitInternalCommonModule

final class arg._cls1
    implements Callable
{

    private final Context arg$1;

    public final Object call()
    {
        return GrowthKitInternalCommonModule.lambda$provideGrowthKitSharedPrefs$0$GrowthKitInternalCommonModule(arg$1);
    }

    (Context context)
    {
        arg$1 = context;
    }
}
