// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl:
//            GrowthKitCallbacksManagerImpl

public final class GrowthKitCallbacksManagerImpl_Factory
    implements Factory
{

    public static final GrowthKitCallbacksManagerImpl_Factory INSTANCE = new GrowthKitCallbacksManagerImpl_Factory();

    public GrowthKitCallbacksManagerImpl_Factory()
    {
    }

    public final Object get()
    {
        return new GrowthKitCallbacksManagerImpl();
    }

}
