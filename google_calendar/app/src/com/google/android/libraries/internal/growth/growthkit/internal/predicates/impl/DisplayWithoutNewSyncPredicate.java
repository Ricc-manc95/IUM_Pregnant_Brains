// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate;

public final class DisplayWithoutNewSyncPredicate
    implements PartialTriggeringConditionsPredicate
{

    DisplayWithoutNewSyncPredicate()
    {
    }

    public final boolean apply(Object obj, Object obj1)
    {
        obj = (com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringConditions)obj;
        boolean flag1 = ((com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringConditionsPredicate.TriggeringConditionsEvalContext)obj1).hasPresentedPromos();
        boolean flag = false;
        if ((((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringConditions) (obj)).bitField0_ & 8) == 8)
        {
            flag = ((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringConditions) (obj)).displayWithoutNewSync_;
        }
        if (!flag1)
        {
            flag = true;
        }
        return flag;
    }

    public final com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType getTriggeringConditionType()
    {
        return com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType.DISPLAY_WITHOUT_NEW_SYNC;
    }
}
