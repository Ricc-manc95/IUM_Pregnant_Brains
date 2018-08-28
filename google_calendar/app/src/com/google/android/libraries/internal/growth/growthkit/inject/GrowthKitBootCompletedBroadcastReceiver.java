// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import java.util.Map;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.inject:
//            GrowthKit, GrowthKitComponent, BroadcastReceiverInjector

public class GrowthKitBootCompletedBroadcastReceiver extends BroadcastReceiver
{

    private final Logger logger = new Logger();

    public GrowthKitBootCompletedBroadcastReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        try
        {
            ((BroadcastReceiverInjector)((Provider)GrowthKit.get(context).internalBroadcastReceiverInjectors().get(com/google/android/libraries/internal/growth/growthkit/inject/GrowthKitBootCompletedBroadcastReceiver)).get()).inject(this);
            throw new NullPointerException();
        }
        catch (Exception exception)
        {
            context = logger;
            intent = "Failed to get GrowthKitJobScheduler in GrowthKitBootCompletedBroadcastReceiver So GrowthKit failed to schedule jobs after package replaced / boot completed.";
            Object aobj[] = new Object[0];
            String s = ((Logger) (context)).tag;
            context = intent;
            if (aobj != null)
            {
                context = intent;
                if (aobj.length > 0)
                {
                    context = String.format("Failed to get GrowthKitJobScheduler in GrowthKitBootCompletedBroadcastReceiver So GrowthKit failed to schedule jobs after package replaced / boot completed.", aobj);
                }
            }
            Log.w(s, context, exception);
            return;
        }
    }
}
