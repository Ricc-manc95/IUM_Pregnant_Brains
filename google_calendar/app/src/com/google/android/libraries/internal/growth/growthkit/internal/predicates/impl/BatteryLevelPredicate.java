// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;

public final class BatteryLevelPredicate
    implements PartialTriggeringConditionsPredicate
{

    private static final Logger logger = new Logger();
    private final Context context;

    BatteryLevelPredicate(Context context1)
    {
        context = context1;
    }

    public final boolean apply(Object obj, Object obj1)
    {
        int i = ((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringConditions)obj).minBatteryPercentage_;
        if (i < 0 || i > 100)
        {
            logger.w("minBatterPercentage is not between 0 and 100", new Object[0]);
        } else
        {
            if (i == 0)
            {
                return true;
            }
            obj = new IntentFilter("android.intent.action.BATTERY_CHANGED");
            obj = context.registerReceiver(null, ((IntentFilter) (obj)));
            if (obj == null)
            {
                logger.w("Failed to get ACTION_BATTERY_CHANGED intent", new Object[0]);
                obj = Absent.INSTANCE;
            } else
            {
                int j = ((Intent) (obj)).getIntExtra("level", -1);
                int k = ((Intent) (obj)).getIntExtra("scale", -1);
                if (j < 0 || k < 0)
                {
                    logger.w("Failed to get level and scale from ACTION_BATTERY_CHANGED intent", new Object[0]);
                    obj = Absent.INSTANCE;
                } else
                {
                    obj = Integer.valueOf((int)(((double)j * 100D) / (double)k));
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    }
                    obj = new Present(obj);
                }
            }
            if (((Optional) (obj)).isPresent() && i <= ((Integer)((Optional) (obj)).get()).intValue())
            {
                return true;
            }
        }
        return false;
    }

    public final com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType getTriggeringConditionType()
    {
        return com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType.BATTERY;
    }

}
