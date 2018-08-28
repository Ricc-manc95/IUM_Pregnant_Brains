// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import com.google.common.base.BinaryPredicate;
import com.google.common.collect.ImmutableMap;
import com.google.identity.growth.common.BaseAppUtil;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Protobuf;
import com.google.protobuf.Schema;
import java.util.Iterator;
import java.util.List;

public final class EventCountTargetingTermPredicate
    implements BinaryPredicate
{

    public EventCountTargetingTermPredicate()
    {
    }

    public static boolean apply(com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm targetingterm, com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext targetingruleevalcontext)
    {
        if (targetingterm != null && targetingruleevalcontext != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        Object obj;
        Object obj2;
        int i;
        int j;
        int l;
        Iterator iterator;
        if (targetingterm.predicateCase_ == 2)
        {
            obj = (com.google.identity.growth.proto.Promotion.EventCountPredicate)targetingterm.predicate_;
        } else
        {
            obj = com.google.identity.growth.proto.Promotion.EventCountPredicate.DEFAULT_INSTANCE;
        }
        l = ((com.google.identity.growth.proto.Promotion.EventCountPredicate) (obj)).minValueInclusive_;
        i = ((com.google.identity.growth.proto.Promotion.EventCountPredicate) (obj)).maxValueExclusive_;
        if (i == 0)
        {
            i = 0x7fffffff;
        }
        iterator = ((com.google.identity.growth.proto.Promotion.EventCountPredicate) (obj)).clientEvent_.iterator();
        j = 0;
_L6:
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_374;
        }
        obj2 = (com.google.identity.growth.proto.Promotion.ClientTargetingEvent)iterator.next();
        com.google.identity.growth.proto.Promotion.ClientTargetingEvent.EventCase.forNumber(((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (obj2)).eventCase_).ordinal();
        JVM INSTR tableswitch 0 1: default 112
    //                   0 141
    //                   1 324;
           goto _L3 _L4 _L5
_L3:
        obj = null;
_L7:
        Object obj1;
        GeneratedMessageLite generatedmessagelite;
        int k;
        if (obj != null)
        {
            k = ((Integer) (obj)).intValue();
        } else
        {
            k = 0;
        }
        j = k + j;
        if (true) goto _L6; else goto _L4
_L4:
        if (((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (obj2)).eventCase_ == 1)
        {
            obj = (com.google.identity.growth.proto.Promotion.ClearcutEvent)((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (obj2)).event_;
        } else
        {
            obj = com.google.identity.growth.proto.Promotion.ClearcutEvent.DEFAULT_INSTANCE;
        }
        obj1 = BaseAppUtil.normalizeAndroidPackageName(((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj)).bundleIdentifier_);
        if (((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (obj2)).eventCase_ == 1)
        {
            obj = (com.google.identity.growth.proto.Promotion.ClearcutEvent)((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (obj2)).event_;
        } else
        {
            obj = com.google.identity.growth.proto.Promotion.ClearcutEvent.DEFAULT_INSTANCE;
        }
        obj2 = (com.google.protobuf.GeneratedMessageLite.Builder)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        generatedmessagelite = ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).instance;
        Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).mergeFrom(generatedmessagelite, obj);
        obj = (com.google.identity.growth.proto.Promotion.ClearcutEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)obj2;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        obj2 = (com.google.identity.growth.proto.Promotion.ClearcutEvent)((com.google.identity.growth.proto.Promotion.ClearcutEvent.Builder) (obj)).instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        obj2.bitField0_ = ((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj2)).bitField0_ | 4;
        obj2.bundleIdentifier_ = ((String) (obj1));
        obj = (com.google.identity.growth.proto.Promotion.ClearcutEvent)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
        obj = (Integer)targetingruleevalcontext.clearcutCounts().get(obj);
          goto _L7
_L5:
        obj1 = targetingruleevalcontext.veCounts();
        if (((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (obj2)).eventCase_ == 2)
        {
            obj = (com.google.identity.growth.proto.Promotion.VisualElementEvent)((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (obj2)).event_;
        } else
        {
            obj = com.google.identity.growth.proto.Promotion.VisualElementEvent.DEFAULT_INSTANCE;
        }
        obj = (Integer)((ImmutableMap) (obj1)).get(obj);
          goto _L7
        boolean flag;
        if (l <= j && j < i)
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
        if (true) goto _L1; else goto _L8
_L8:
    }

    public final volatile boolean apply(Object obj, Object obj1)
    {
        return apply((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm)obj, (com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate.TargetingRuleEvalContext)obj1);
    }
}
