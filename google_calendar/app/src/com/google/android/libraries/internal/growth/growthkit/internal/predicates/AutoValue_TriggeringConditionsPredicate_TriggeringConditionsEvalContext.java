// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.protobuf.GeneratedMessageLite;

public final class AutoValue_TriggeringConditionsPredicate_TriggeringConditionsEvalContext extends TriggeringConditionsPredicate.TriggeringConditionsEvalContext
{

    private final String accountName;
    private final PromoContext clearcutLogContext;
    private final boolean hasPresentedPromos;
    private final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoId;

    public AutoValue_TriggeringConditionsPredicate_TriggeringConditionsEvalContext(String s, com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoidentification, PromoContext promocontext, boolean flag)
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
        } else
        {
            clearcutLogContext = promocontext;
            hasPresentedPromos = flag;
            return;
        }
    }

    public final String accountName()
    {
        return accountName;
    }

    public final PromoContext clearcutLogContext()
    {
        return clearcutLogContext;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TriggeringConditionsPredicate.TriggeringConditionsEvalContext)
            {
                obj = (TriggeringConditionsPredicate.TriggeringConditionsEvalContext)obj;
                if ((accountName != null ? !accountName.equals(((TriggeringConditionsPredicate.TriggeringConditionsEvalContext) (obj)).accountName()) : ((TriggeringConditionsPredicate.TriggeringConditionsEvalContext) (obj)).accountName() != null) || (!promoId.equals(((TriggeringConditionsPredicate.TriggeringConditionsEvalContext) (obj)).promoId()) || !clearcutLogContext.equals(((TriggeringConditionsPredicate.TriggeringConditionsEvalContext) (obj)).clearcutLogContext()) || hasPresentedPromos != ((TriggeringConditionsPredicate.TriggeringConditionsEvalContext) (obj)).hasPresentedPromos()))
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

    public final boolean hasPresentedPromos()
    {
        return hasPresentedPromos;
    }

    public final int hashCode()
    {
        int i;
        char c;
        int j;
        int k;
        if (accountName == null)
        {
            i = 0;
        } else
        {
            i = accountName.hashCode();
        }
        j = promoId.hashCode();
        k = clearcutLogContext.hashCode();
        if (hasPresentedPromos)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ (((i ^ 0xf4243) * 0xf4243 ^ j) * 0xf4243 ^ k) * 0xf4243;
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
        boolean flag = hasPresentedPromos;
        return (new StringBuilder(String.valueOf(s).length() + 102 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("TriggeringConditionsEvalContext{accountName=").append(s).append(", promoId=").append(s1).append(", clearcutLogContext=").append(s2).append(", hasPresentedPromos=").append(flag).append("}").toString();
    }
}
