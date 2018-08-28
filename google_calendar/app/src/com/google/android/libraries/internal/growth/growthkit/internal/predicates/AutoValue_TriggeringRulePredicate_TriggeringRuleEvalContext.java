// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates;

import com.google.protobuf.GeneratedMessageLite;

public final class AutoValue_TriggeringRulePredicate_TriggeringRuleEvalContext extends TriggeringRulePredicate.TriggeringRuleEvalContext
{

    private final String accountName;
    private final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoId;
    private final com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent triggeringEvent;

    public AutoValue_TriggeringRulePredicate_TriggeringRuleEvalContext(String s, com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoidentification, com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent triggeringevent)
    {
        accountName = s;
        if (promoidentification == null)
        {
            throw new NullPointerException("Null promoId");
        }
        promoId = promoidentification;
        if (triggeringevent == null)
        {
            throw new NullPointerException("Null triggeringEvent");
        } else
        {
            triggeringEvent = triggeringevent;
            return;
        }
    }

    public final String accountName()
    {
        return accountName;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TriggeringRulePredicate.TriggeringRuleEvalContext)
            {
                obj = (TriggeringRulePredicate.TriggeringRuleEvalContext)obj;
                if ((accountName != null ? !accountName.equals(((TriggeringRulePredicate.TriggeringRuleEvalContext) (obj)).accountName()) : ((TriggeringRulePredicate.TriggeringRuleEvalContext) (obj)).accountName() != null) || (!promoId.equals(((TriggeringRulePredicate.TriggeringRuleEvalContext) (obj)).promoId()) || !triggeringEvent.equals(((TriggeringRulePredicate.TriggeringRuleEvalContext) (obj)).triggeringEvent())))
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
        return ((i ^ 0xf4243) * 0xf4243 ^ promoId.hashCode()) * 0xf4243 ^ triggeringEvent.hashCode();
    }

    public final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoId()
    {
        return promoId;
    }

    public final String toString()
    {
        String s = accountName;
        String s1 = String.valueOf(promoId);
        String s2 = String.valueOf(triggeringEvent);
        return (new StringBuilder(String.valueOf(s).length() + 67 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("TriggeringRuleEvalContext{accountName=").append(s).append(", promoId=").append(s1).append(", triggeringEvent=").append(s2).append("}").toString();
    }

    public final com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent triggeringEvent()
    {
        return triggeringEvent;
    }
}
