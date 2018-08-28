// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.ClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringConditionsPredicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public final class CompositeTriggeringConditionsPredicate
    implements TriggeringConditionsPredicate
{

    private final ClearcutLogger clearcutLogger;
    private final Set predicates;

    public CompositeTriggeringConditionsPredicate(Set set, ClearcutLogger clearcutlogger)
    {
        predicates = set;
        clearcutLogger = clearcutlogger;
    }

    public final boolean apply(Object obj, Object obj1)
    {
        obj = (com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringConditions)obj;
        obj1 = (com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringConditionsPredicate.TriggeringConditionsEvalContext)obj1;
        ArrayList arraylist = new ArrayList();
        if (obj != null)
        {
            Iterator iterator = predicates.iterator();
            boolean flag = false;
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                PartialTriggeringConditionsPredicate partialtriggeringconditionspredicate = (PartialTriggeringConditionsPredicate)iterator.next();
                if (!partialtriggeringconditionspredicate.apply(obj, obj1))
                {
                    arraylist.add(partialtriggeringconditionspredicate.getTriggeringConditionType());
                    flag = true;
                }
            } while (true);
            clearcutLogger.logPromoConditionsEvaluated(((com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringConditionsPredicate.TriggeringConditionsEvalContext) (obj1)).clearcutLogContext(), arraylist);
            if (!flag)
            {
                return true;
            }
        }
        return false;
    }
}
