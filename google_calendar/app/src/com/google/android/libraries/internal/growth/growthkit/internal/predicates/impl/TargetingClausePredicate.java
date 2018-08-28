// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import com.google.common.base.BinaryPredicate;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            EventCountTargetingTermPredicate, AppStateTargetingTermPredicate

public final class TargetingClausePredicate
    implements BinaryPredicate
{

    private final AppStateTargetingTermPredicate appStateTargetingTermPredicate;
    private final EventCountTargetingTermPredicate eventCountTargetingTermPredicate;

    public TargetingClausePredicate(EventCountTargetingTermPredicate eventcounttargetingtermpredicate, AppStateTargetingTermPredicate appstatetargetingtermpredicate)
    {
        eventCountTargetingTermPredicate = eventcounttargetingtermpredicate;
        appStateTargetingTermPredicate = appstatetargetingtermpredicate;
    }

    public final boolean apply(com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingClause targetingclause, com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext targetingruleevalcontext)
    {
        if (targetingclause == null || targetingruleevalcontext == null)
        {
            return false;
        }
        targetingclause = targetingclause.term_.iterator();
_L7:
        if (!targetingclause.hasNext()) goto _L2; else goto _L1
_L1:
        com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm targetingterm = (com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm)targetingclause.next();
        com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm.PredicateCase.forNumber(targetingterm.predicateCase_).ordinal();
        JVM INSTR tableswitch 0 1: default 72
    //                   0 74
    //                   1 84;
           goto _L3 _L4 _L5
_L5:
        continue; /* Loop/switch isn't completed */
_L3:
        return false;
_L4:
        if (EventCountTargetingTermPredicate.apply(targetingterm, targetingruleevalcontext)) goto _L7; else goto _L6
_L6:
        return false;
        if (AppStateTargetingTermPredicate.apply(targetingterm, targetingruleevalcontext)) goto _L7; else goto _L8
_L8:
        return false;
_L2:
        return true;
    }

    public final volatile boolean apply(Object obj, Object obj1)
    {
        return apply((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingClause)obj, (com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext)obj1);
    }
}
