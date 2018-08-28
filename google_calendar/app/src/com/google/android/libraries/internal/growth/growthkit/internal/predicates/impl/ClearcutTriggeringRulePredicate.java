// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import com.google.common.base.BinaryPredicate;
import com.google.identity.growth.common.BaseAppUtil;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Protobuf;
import com.google.protobuf.Schema;
import java.util.Iterator;
import java.util.List;

public final class ClearcutTriggeringRulePredicate
    implements BinaryPredicate
{

    public ClearcutTriggeringRulePredicate()
    {
    }

    public final boolean apply(Object obj, Object obj1)
    {
label0:
        {
            Object obj2 = (com.google.identity.growth.proto.Promotion.TriggeringRule)obj;
            obj = (com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringRulePredicate.TriggeringRuleEvalContext)obj1;
            if (obj2 == null || obj == null)
            {
                return false;
            }
            if (((com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringRulePredicate.TriggeringRuleEvalContext) (obj)).triggeringEvent().eventCase_ != 1)
            {
                break label0;
            }
            obj = ((com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringRulePredicate.TriggeringRuleEvalContext) (obj)).triggeringEvent();
            Object obj3;
            if (((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj)).eventCase_ == 1)
            {
                obj = (com.google.identity.growth.proto.Promotion.ClearcutEvent)((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj)).event_;
            } else
            {
                obj = com.google.identity.growth.proto.Promotion.ClearcutEvent.DEFAULT_INSTANCE;
            }
            obj1 = ((com.google.identity.growth.proto.Promotion.TriggeringRule) (obj2)).event_.iterator();
            do
            {
                if (!((Iterator) (obj1)).hasNext())
                {
                    break label0;
                }
                obj2 = (com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent)((Iterator) (obj1)).next();
                GeneratedMessageLite generatedmessagelite;
                boolean flag;
                if (((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj2)).eventCase_ == 1)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            } while (!flag);
            if (((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj2)).eventCase_ == 1)
            {
                obj1 = (com.google.identity.growth.proto.Promotion.ClearcutEvent)((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj2)).event_;
            } else
            {
                obj1 = com.google.identity.growth.proto.Promotion.ClearcutEvent.DEFAULT_INSTANCE;
            }
            obj2 = BaseAppUtil.normalizeAndroidPackageName(((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj1)).bundleIdentifier_);
            obj3 = (com.google.protobuf.GeneratedMessageLite.Builder)((GeneratedMessageLite) (obj1)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
            generatedmessagelite = ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).instance;
            Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).mergeFrom(generatedmessagelite, obj1);
            obj1 = (com.google.identity.growth.proto.Promotion.ClearcutEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)obj3;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
            obj3 = (com.google.identity.growth.proto.Promotion.ClearcutEvent)((com.google.identity.growth.proto.Promotion.ClearcutEvent.Builder) (obj1)).instance;
            if (obj2 == null)
            {
                throw new NullPointerException();
            } else
            {
                obj3.bitField0_ = ((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj3)).bitField0_ | 4;
                obj3.bundleIdentifier_ = ((String) (obj2));
                return ((com.google.identity.growth.proto.Promotion.ClearcutEvent)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build()).equals(obj);
            }
        }
        return false;
    }
}
