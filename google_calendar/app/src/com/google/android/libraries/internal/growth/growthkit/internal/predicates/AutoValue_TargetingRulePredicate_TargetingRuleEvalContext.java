// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.GeneratedMessageLite;

public final class AutoValue_TargetingRulePredicate_TargetingRuleEvalContext extends TargetingRulePredicate.TargetingRuleEvalContext
{

    private final String accountName;
    private final ImmutableMap appStates;
    private final ImmutableMap clearcutCounts;
    private final PromoContext clearcutLogContext;
    private final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoId;
    private final ImmutableMap veCounts;

    public AutoValue_TargetingRulePredicate_TargetingRuleEvalContext(String s, com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoidentification, PromoContext promocontext, ImmutableMap immutablemap, ImmutableMap immutablemap1, ImmutableMap immutablemap2)
    {
        accountName = s;
        if (promoidentification == null)
        {
            throw new NullPointerException("Null promoId");
        }
        promoId = promoidentification;
        if (promocontext == null)
        {
            throw new NullPointerException("Null clearcutLogContext");
        }
        clearcutLogContext = promocontext;
        if (immutablemap == null)
        {
            throw new NullPointerException("Null clearcutCounts");
        }
        clearcutCounts = immutablemap;
        if (immutablemap1 == null)
        {
            throw new NullPointerException("Null veCounts");
        }
        veCounts = immutablemap1;
        if (immutablemap2 == null)
        {
            throw new NullPointerException("Null appStates");
        } else
        {
            appStates = immutablemap2;
            return;
        }
    }

    public final String accountName()
    {
        return accountName;
    }

    public final ImmutableMap appStates()
    {
        return appStates;
    }

    public final ImmutableMap clearcutCounts()
    {
        return clearcutCounts;
    }

    public final PromoContext clearcutLogContext()
    {
        return clearcutLogContext;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TargetingRulePredicate.TargetingRuleEvalContext)
            {
                obj = (TargetingRulePredicate.TargetingRuleEvalContext)obj;
                if ((accountName != null ? !accountName.equals(((TargetingRulePredicate.TargetingRuleEvalContext) (obj)).accountName()) : ((TargetingRulePredicate.TargetingRuleEvalContext) (obj)).accountName() != null) || (!promoId.equals(((TargetingRulePredicate.TargetingRuleEvalContext) (obj)).promoId()) || !clearcutLogContext.equals(((TargetingRulePredicate.TargetingRuleEvalContext) (obj)).clearcutLogContext()) || !clearcutCounts.equals(((TargetingRulePredicate.TargetingRuleEvalContext) (obj)).clearcutCounts()) || !veCounts.equals(((TargetingRulePredicate.TargetingRuleEvalContext) (obj)).veCounts()) || !appStates.equals(((TargetingRulePredicate.TargetingRuleEvalContext) (obj)).appStates())))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int i;
        if (accountName == null)
        {
            i = 0;
        } else
        {
            i = accountName.hashCode();
        }
        return (((((i ^ 0xf4243) * 0xf4243 ^ promoId.hashCode()) * 0xf4243 ^ clearcutLogContext.hashCode()) * 0xf4243 ^ clearcutCounts.hashCode()) * 0xf4243 ^ veCounts.hashCode()) * 0xf4243 ^ appStates.hashCode();
    }

    public final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoId()
    {
        return promoId;
    }

    public final String toString()
    {
        String s = accountName;
        String s1 = String.valueOf(promoId);
        String s2 = String.valueOf(clearcutLogContext);
        String s3 = String.valueOf(clearcutCounts);
        String s4 = String.valueOf(veCounts);
        String s5 = String.valueOf(appStates);
        return (new StringBuilder(String.valueOf(s).length() + 109 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length())).append("TargetingRuleEvalContext{accountName=").append(s).append(", promoId=").append(s1).append(", clearcutLogContext=").append(s2).append(", clearcutCounts=").append(s3).append(", veCounts=").append(s4).append(", appStates=").append(s5).append("}").toString();
    }

    public final ImmutableMap veCounts()
    {
        return veCounts;
    }
}
