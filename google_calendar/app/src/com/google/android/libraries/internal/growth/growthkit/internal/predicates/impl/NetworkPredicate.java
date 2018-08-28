// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate;
import com.google.identity.boq.growth.common.proto.ConnectivityState;

public final class NetworkPredicate
    implements PartialTriggeringConditionsPredicate
{

    private static final Logger logger = new Logger();
    private final ConnectivityManager connectivityManager;

    public NetworkPredicate(Context context)
    {
        connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
    }

    public final boolean apply(Object obj, Object obj1)
    {
        com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringConditions triggeringconditions;
        triggeringconditions = (com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringConditions)obj;
        Logger logger1;
        if (triggeringconditions.network_ == null)
        {
            obj = com.google.identity.growth.proto.Promotion.NetworkCondition.DEFAULT_INSTANCE;
        } else
        {
            obj = triggeringconditions.network_;
        }
        obj1 = ConnectivityState.forNumber(((com.google.identity.growth.proto.Promotion.NetworkCondition) (obj)).connectivity_);
        obj = obj1;
        if (obj1 == null)
        {
            obj = ConnectivityState.CONNECTIVITY_UNKNOWN;
        }
        ((ConnectivityState) (obj)).ordinal();
        JVM INSTR tableswitch 0 2: default 64
    //                   0 123
    //                   1 146
    //                   2 125;
           goto _L1 _L2 _L3 _L4
_L1:
        logger1 = logger;
        boolean flag;
        if (triggeringconditions.network_ == null)
        {
            obj = com.google.identity.growth.proto.Promotion.NetworkCondition.DEFAULT_INSTANCE;
        } else
        {
            obj = triggeringconditions.network_;
        }
        obj1 = ConnectivityState.forNumber(((com.google.identity.growth.proto.Promotion.NetworkCondition) (obj)).connectivity_);
        obj = obj1;
        if (obj1 == null)
        {
            obj = ConnectivityState.CONNECTIVITY_UNKNOWN;
        }
        logger1.w("Invalid Connectivity value: %s", new Object[] {
            obj
        });
_L6:
        return true;
_L2:
        return true;
_L4:
        obj = connectivityManager.getActiveNetworkInfo();
        if (obj == null || !((NetworkInfo) (obj)).isConnected())
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        obj = connectivityManager.getActiveNetworkInfo();
        if (obj != null && ((NetworkInfo) (obj)).isConnected())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return false;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType getTriggeringConditionType()
    {
        return com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType.NETWORK;
    }

}
