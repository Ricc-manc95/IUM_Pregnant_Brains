// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.libraries.internal.growth.growthkit.inject.BroadcastReceiverInjector;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKit;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.PhenotypeManager;
import java.util.Map;
import javax.inject.Provider;

public class PhenotypeBroadcastReceiver extends BroadcastReceiver
{
    public static interface PhenotypeBroadcastReceiverSubcomponent
        extends BroadcastReceiverInjector
    {
    }


    private final Logger logger = new Logger();
    public PhenotypeManager phenotypeManager;

    public PhenotypeBroadcastReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        try
        {
            ((BroadcastReceiverInjector)((Provider)GrowthKit.get(context).internalBroadcastReceiverInjectors().get(com/google/android/libraries/internal/growth/growthkit/internal/experiments/impl/PhenotypeBroadcastReceiver)).get()).inject(this);
            phenotypeManager.handlePhenotypeUpdateIntent(intent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            logger.w(context, "Failed to get phenotypeManager in PhenotypeBroadcastReceiver", new Object[0]);
        }
    }
}
