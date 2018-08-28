// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.ClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate;
import java.util.List;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            TargetingClausePredicate

public final class TargetingRulePredicateImpl
    implements TargetingRulePredicate
{

    private final ClearcutLogger clearcutLogger;
    private final TargetingClausePredicate targetingClausePredicate;

    public TargetingRulePredicateImpl(TargetingClausePredicate targetingclausepredicate, ClearcutLogger clearcutlogger)
    {
        targetingClausePredicate = targetingclausepredicate;
        clearcutLogger = clearcutlogger;
    }

    public final boolean apply(Object obj, Object obj1)
    {
        obj = (com.google.identity.growth.proto.Promotion.ClientSideTargetingRule)obj;
        obj1 = (com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext)obj1;
        if (obj == null || obj1 == null)
        {
            return false;
        }
        obj = ((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule) (obj)).clause_;
        if (((List) (obj)).isEmpty())
        {
            clearcutLogger.logPromoTargetingEvaluated(((com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext) (obj1)).clearcutLogContext(), false);
            return true;
        }
        for (int i = 0; i < ((List) (obj)).size(); i++)
        {
            if (targetingClausePredicate.apply((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingClause)((List) (obj)).get(i), ((com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext) (obj1))))
            {
                clearcutLogger.logPromoTargetingEvaluated(((com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext) (obj1)).clearcutLogContext(), false);
                return true;
            }
        }

        clearcutLogger.logPromoTargetingEvaluated(((com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext) (obj1)).clearcutLogContext(), true);
        return false;
    }
}
