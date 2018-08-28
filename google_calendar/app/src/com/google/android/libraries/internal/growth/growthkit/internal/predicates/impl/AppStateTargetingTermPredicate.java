// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import com.google.common.base.BinaryPredicate;
import com.google.common.collect.ImmutableMap;
import java.util.List;

public final class AppStateTargetingTermPredicate
    implements BinaryPredicate
{

    public AppStateTargetingTermPredicate()
    {
    }

    public static boolean apply(com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm targetingterm, com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext targetingruleevalcontext)
    {
        if (targetingterm != null && targetingruleevalcontext != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        com.google.identity.growth.proto.Promotion.AppStatePredicate appstatepredicate;
        if (targetingterm.predicateCase_ == 3)
        {
            appstatepredicate = (com.google.identity.growth.proto.Promotion.AppStatePredicate)targetingterm.predicate_;
        } else
        {
            appstatepredicate = com.google.identity.growth.proto.Promotion.AppStatePredicate.DEFAULT_INSTANCE;
        }
        if (targetingruleevalcontext.appStates().containsKey(appstatepredicate.stateId_))
        {
            com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.AppStateValue appstatevalue = (com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.AppStateValue)targetingruleevalcontext.appStates().get(appstatepredicate.stateId_);
            switch (appstatevalue.getKind().ordinal())
            {
            default:
                return false;

            case 1: // '\001'
                continue; /* Loop/switch isn't completed */

            case 0: // '\0'
                if (appstatepredicate.satisfiedConditionsCase_ == 3)
                {
                    targetingruleevalcontext = appstatevalue.stringList();
                    int i;
                    int j;
                    int k;
                    boolean flag;
                    if (appstatepredicate.satisfiedConditionsCase_ == 3)
                    {
                        targetingterm = (com.google.identity.growth.proto.Promotion.StringList)appstatepredicate.satisfiedConditions_;
                    } else
                    {
                        targetingterm = com.google.identity.growth.proto.Promotion.StringList.DEFAULT_INSTANCE;
                    }
                    if (targetingruleevalcontext.containsAll(((com.google.identity.growth.proto.Promotion.StringList) (targetingterm)).element_))
                    {
                        return true;
                    }
                }
                break;
            }
        }
        continue; /* Loop/switch isn't completed */
        if (appstatepredicate.satisfiedConditionsCase_ != 2) goto _L1; else goto _L3
_L3:
        if (appstatepredicate.satisfiedConditionsCase_ == 2)
        {
            targetingruleevalcontext = (com.google.identity.growth.proto.Promotion.IntRange)appstatepredicate.satisfiedConditions_;
        } else
        {
            targetingruleevalcontext = com.google.identity.growth.proto.Promotion.IntRange.DEFAULT_INSTANCE;
        }
        k = ((com.google.identity.growth.proto.Promotion.IntRange) (targetingruleevalcontext)).minValueInclusive_;
        if (appstatepredicate.satisfiedConditionsCase_ == 2)
        {
            targetingruleevalcontext = (com.google.identity.growth.proto.Promotion.IntRange)appstatepredicate.satisfiedConditions_;
        } else
        {
            targetingruleevalcontext = com.google.identity.growth.proto.Promotion.IntRange.DEFAULT_INSTANCE;
        }
        j = ((com.google.identity.growth.proto.Promotion.IntRange) (targetingruleevalcontext)).maxValueExclusive_;
        i = j;
        if (j == 0)
        {
            i = 0x7fffffff;
        }
        j = appstatevalue.integer();
        if (k <= j && j < i)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag != targetingterm.negate_)
        {
            return true;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public final volatile boolean apply(Object obj, Object obj1)
    {
        return apply((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm)obj, (com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext)obj1);
    }
}
