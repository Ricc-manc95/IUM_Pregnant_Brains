// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import com.google.android.libraries.internal.growth.growthkit.events.GrowthKitEventManager;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacksManager;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitStartup;
import java.util.Map;

public interface GrowthKitComponent
{

    public abstract GrowthKitCallbacksManager getGrowthKitCallbacksManager();

    public abstract GrowthKitEventManager getGrowthKitEventManager();

    public abstract GrowthKitStartup getGrowthKitStartup();

    public abstract Map internalBroadcastReceiverInjectors();

    public abstract Map internalFragmentInjectors();

    public abstract Map internalServiceInjectors();
}
