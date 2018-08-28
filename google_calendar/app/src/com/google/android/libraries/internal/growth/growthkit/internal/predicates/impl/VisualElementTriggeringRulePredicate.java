// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import com.google.common.base.BinaryPredicate;
import java.util.Iterator;
import java.util.List;

public final class VisualElementTriggeringRulePredicate
    implements BinaryPredicate
{

    public VisualElementTriggeringRulePredicate()
    {
    }

    public final boolean apply(Object obj, Object obj1)
    {
        com.google.identity.growth.proto.Promotion.TriggeringRule triggeringrule;
        triggeringrule = (com.google.identity.growth.proto.Promotion.TriggeringRule)obj;
        obj = (com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringRulePredicate.TriggeringRuleEvalContext)obj1;
        break MISSING_BLOCK_LABEL_10;
        if (triggeringrule != null && obj != null && ((com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringRulePredicate.TriggeringRuleEvalContext) (obj)).triggeringEvent().eventCase_ == 4)
        {
            obj = ((com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringRulePredicate.TriggeringRuleEvalContext) (obj)).triggeringEvent();
            Iterator iterator;
            if (((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj)).eventCase_ == 4)
            {
                obj = (com.google.identity.growth.proto.Promotion.VisualElementEvent)((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj)).event_;
            } else
            {
                obj = com.google.identity.growth.proto.Promotion.VisualElementEvent.DEFAULT_INSTANCE;
            }
            iterator = triggeringrule.event_.iterator();
            while (iterator.hasNext()) 
            {
                obj1 = (com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent)iterator.next();
                int i;
                if (((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj1)).eventCase_ == 4)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    com.google.identity.growth.proto.Promotion.VisualElementEvent.Action action;
                    com.google.identity.growth.proto.Promotion.VisualElementEvent.Action action1;
                    com.google.identity.growth.proto.Promotion.VisualElementEvent.Action action2;
                    if (((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj1)).eventCase_ == 4)
                    {
                        obj1 = (com.google.identity.growth.proto.Promotion.VisualElementEvent)((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj1)).event_;
                    } else
                    {
                        obj1 = com.google.identity.growth.proto.Promotion.VisualElementEvent.DEFAULT_INSTANCE;
                    }
                    action1 = com.google.identity.growth.proto.Promotion.VisualElementEvent.Action.forNumber(((com.google.identity.growth.proto.Promotion.VisualElementEvent) (obj1)).action_);
                    action = action1;
                    if (action1 == null)
                    {
                        action = com.google.identity.growth.proto.Promotion.VisualElementEvent.Action.UNKNOWN;
                    }
                    action2 = com.google.identity.growth.proto.Promotion.VisualElementEvent.Action.forNumber(((com.google.identity.growth.proto.Promotion.VisualElementEvent) (obj)).action_);
                    action1 = action2;
                    if (action2 == null)
                    {
                        action1 = com.google.identity.growth.proto.Promotion.VisualElementEvent.Action.UNKNOWN;
                    }
                    if (action == action1)
                    {
                        i = 0;
                        int j;
                        int k;
                        for (j = 0; i < ((com.google.identity.growth.proto.Promotion.VisualElementEvent) (obj)).nodeIdPath_.size() && j < ((com.google.identity.growth.proto.Promotion.VisualElementEvent) (obj1)).nodeIdPath_.size(); j = k)
                        {
                            k = j;
                            if (((com.google.identity.growth.proto.Promotion.VisualElementEvent) (obj)).nodeIdPath_.getInt(i) == ((com.google.identity.growth.proto.Promotion.VisualElementEvent) (obj1)).nodeIdPath_.getInt(j))
                            {
                                k = j + 1;
                            }
                            i++;
                        }

                        if (j == ((com.google.identity.growth.proto.Promotion.VisualElementEvent) (obj1)).nodeIdPath_.size())
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
