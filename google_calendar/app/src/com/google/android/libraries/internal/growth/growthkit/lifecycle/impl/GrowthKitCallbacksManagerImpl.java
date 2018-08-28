// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl;

import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacksManager;
import java.util.concurrent.atomic.AtomicReference;

public final class GrowthKitCallbacksManagerImpl
    implements GrowthKitCallbacksManager
{

    public final AtomicReference callback = new AtomicReference();

    public GrowthKitCallbacksManagerImpl()
    {
    }

    public final void registerGrowthKitCallbacks(GrowthKitCallbacks growthkitcallbacks)
    {
        callback.set(growthkitcallbacks);
    }

    public final void unregisterGrowthKitCallbacks()
    {
        callback.set(null);
    }
}
