// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.clearcut.impl;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.os.ConfigurationCompat;
import android.support.v4.os.LocaleListCompat;
import android.support.v4.os.LocaleListInterface;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.ClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.BuildInfo;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.GcoreFutures;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.CellFieldTuple;
import com.google.android.libraries.streamz.Counter2;
import com.google.android.libraries.streamz.GcoreClearcutStreamzLogger;
import com.google.android.libraries.streamz.GenericMetric;
import com.google.android.libraries.streamz.StreamzMessageProducer;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.inject.Provider;

public final class ClearcutLoggerImpl
    implements ClearcutLogger
{

    public static final Logger logger = new Logger();
    private final String appPackageName;
    private final PerAccountProvider clearcutLogger;
    private final Context context;
    private final Counter2 impressionsCounter;
    private final StreamzIncrements streamzIncrements;

    public ClearcutLoggerImpl(PerAccountProvider peraccountprovider, String s, Context context1, StreamzIncrements streamzincrements, Counter2 counter2)
    {
        clearcutLogger = peraccountprovider;
        appPackageName = s;
        context = context1;
        streamzIncrements = streamzincrements;
        impressionsCounter = counter2;
    }

    private final void sendLogMessage(PromoContext promocontext, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType eventcodetype, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder builder)
    {
        ConfigurationCompat.getLocales(context.getResources().getConfiguration());
        Object obj = LocaleListCompat.IMPL.get(0);
        Object obj1 = appPackageName;
        builder.copyOnWrite();
        com.google.identity.growth.logging.proto.Client.GrowthMetricsLog growthmetricslog = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        growthmetricslog.bitField0_ = growthmetricslog.bitField0_ | 1;
        growthmetricslog.appName_ = ((String) (obj1));
        int i = BuildInfo.getChangelistNumber();
        builder.copyOnWrite();
        obj1 = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj1)).bitField0_ | 4;
        obj1.growthkitVersion_ = i;
        obj = ((Locale) (obj)).toLanguageTag();
        builder.copyOnWrite();
        obj1 = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj1)).bitField0_ | 8;
        obj1.language_ = ((String) (obj));
        obj = promocontext.serializedAdditionalData();
        builder.copyOnWrite();
        obj1 = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj1)).bitField0_ | 0x10;
        obj1.encodedAdditionalData_ = ((ByteString) (obj));
        obj = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)(GeneratedMessageLite)builder.build();
        builder = ((AbstractMessageLite) (obj)).toByteArray();
        builder = GcoreFutures.from(((GcoreClearcutLogger)clearcutLogger.forAccount(promocontext.accountName())).newEvent(builder).setEventCode(eventcodetype.value).logAsync()).transformAndClose();
        class .Lambda._cls0
            implements Receiver
        {

            private final com.google.identity.growth.logging.proto.Client.GrowthMetricsLog arg$1;
            private final com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType arg$2;
            private final PromoContext arg$3;

            public final void accept(Object obj2)
            {
                obj2 = arg$1;
                obj2 = arg$2;
                obj2 = arg$3;
                Logger logger1 = ClearcutLoggerImpl.logger;
                ((PromoContext) (obj2)).accountName();
            }

            .Lambda._cls0(com.google.identity.growth.logging.proto.Client.GrowthMetricsLog growthmetricslog, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType eventcodetype, PromoContext promocontext)
            {
                arg$1 = growthmetricslog;
                arg$2 = eventcodetype;
                arg$3 = promocontext;
            }
        }

        eventcodetype = new .Lambda._cls0(((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj)), eventcodetype, promocontext);
        class .Lambda._cls1
            implements Receiver
        {

            public static final Receiver $instance = new .Lambda._cls1();

            public final void accept(Object obj2)
            {
                Throwable throwable = (Throwable)obj2;
                obj2 = ClearcutLoggerImpl.logger;
                String s = "Failed to write a clearcut log message";
                Object aobj[] = new Object[0];
                String s1 = ((Logger) (obj2)).tag;
                obj2 = s;
                if (aobj != null)
                {
                    obj2 = s;
                    if (aobj.length > 0)
                    {
                        obj2 = String.format("Failed to write a clearcut log message", aobj);
                    }
                }
                Log.w(s1, ((String) (obj2)), throwable);
            }


            private .Lambda._cls1()
            {
            }
        }

        obj = .Lambda._cls1..instance;
        promocontext = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        eventcodetype = new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.MoreFutures._cls1(eventcodetype, ((Receiver) (obj)));
        if (eventcodetype == null)
        {
            throw new NullPointerException();
        } else
        {
            builder.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(builder, eventcodetype), promocontext);
            return;
        }
    }

    public final void logPromoConditionsEvaluated(PromoContext promocontext, List list)
    {
        com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder builder = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj = promocontext.promoId();
        com.google.identity.growth.logging.proto.Client.PromoEvent.Builder builder1 = (com.google.identity.growth.logging.proto.Client.PromoEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.PromoEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int i = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).impressionCappingId_;
        builder1.copyOnWrite();
        Object obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).bitField0_ | 1;
        obj1.impressionCappingId_ = i;
        obj1 = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).mendelId_;
        builder1.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_.isModifiable())
        {
            obj.mendelIds_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_);
        }
        obj = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_;
        Internal.checkNotNull(obj1);
        if (obj1 instanceof LazyStringList)
        {
            List list1 = ((LazyStringList)obj1).getUnderlyingElements();
            obj1 = (LazyStringList)obj;
            int k1 = ((List) (obj)).size();
            for (obj = list1.iterator(); ((Iterator) (obj)).hasNext();)
            {
                Object obj2 = ((Iterator) (obj)).next();
                if (obj2 == null)
                {
                    int j = ((LazyStringList) (obj1)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(j - k1).append(" is null.").toString();
                    for (int k = ((LazyStringList) (obj1)).size() - 1; k >= k1; k--)
                    {
                        ((LazyStringList) (obj1)).remove(k);
                    }

                    throw new NullPointerException(promocontext);
                }
                if (obj2 instanceof ByteString)
                {
                    ((LazyStringList) (obj1)).add((ByteString)obj2);
                } else
                {
                    ((LazyStringList) (obj1)).add((String)obj2);
                }
            }

        } else
        if (obj1 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj)).addAll((Collection)obj1);
        } else
        {
            if ((obj instanceof ArrayList) && (obj1 instanceof Collection))
            {
                ArrayList arraylist = (ArrayList)obj;
                int l = ((List) (obj)).size();
                arraylist.ensureCapacity(((Collection)obj1).size() + l);
            }
            int l1 = ((List) (obj)).size();
            obj1 = ((Iterable) (obj1)).iterator();
            while (((Iterator) (obj1)).hasNext()) 
            {
                Object obj3 = ((Iterator) (obj1)).next();
                if (obj3 == null)
                {
                    int i1 = ((List) (obj)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i1 - l1).append(" is null.").toString();
                    for (int j1 = ((List) (obj)).size() - 1; j1 >= l1; j1--)
                    {
                        ((List) (obj)).remove(j1);
                    }

                    throw new NullPointerException(promocontext);
                }
                ((List) (obj)).add(obj3);
            }
        }
        list = list.iterator();
        do
        {
            if (list.hasNext())
            {
                switch (((com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType)list.next()).ordinal())
                {
                default:
                    obj = com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason.CONDITION_UNKNOWN;
                    builder1.copyOnWrite();
                    obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    }
                    break;

                case 1: // '\001'
                    obj = com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason.CONDITION_BATTERY;
                    builder1.copyOnWrite();
                    obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    }
                    if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.isModifiable())
                    {
                        obj1.conditionsNotMet_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_);
                    }
                    ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.addInt(((com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason) (obj)).value);
                    continue;

                case 2: // '\002'
                    obj = com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason.CONDITION_INSTALLED_APP;
                    builder1.copyOnWrite();
                    obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    }
                    if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.isModifiable())
                    {
                        obj1.conditionsNotMet_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_);
                    }
                    ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.addInt(((com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason) (obj)).value);
                    continue;

                case 3: // '\003'
                    obj = com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason.CONDITION_NETWORK;
                    builder1.copyOnWrite();
                    obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    }
                    if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.isModifiable())
                    {
                        obj1.conditionsNotMet_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_);
                    }
                    ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.addInt(((com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason) (obj)).value);
                    continue;

                case 4: // '\004'
                    obj = com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason.CONDITION_LOCALE;
                    builder1.copyOnWrite();
                    obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    }
                    if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.isModifiable())
                    {
                        obj1.conditionsNotMet_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_);
                    }
                    ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.addInt(((com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason) (obj)).value);
                    continue;

                case 5: // '\005'
                    obj = com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason.CONDITION_TIME_CONSTRAINT;
                    builder1.copyOnWrite();
                    obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    }
                    if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.isModifiable())
                    {
                        obj1.conditionsNotMet_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_);
                    }
                    ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.addInt(((com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason) (obj)).value);
                    continue;
                }
                if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.isModifiable())
                {
                    obj1.conditionsNotMet_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_);
                }
                ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).conditionsNotMet_.addInt(((com.google.identity.growth.logging.proto.Client.PromoEvent.ConditionsReason) (obj)).value);
            } else
            {
                builder.copyOnWrite();
                list = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
                list.promoEvent_ = (com.google.identity.growth.logging.proto.Client.PromoEvent)(GeneratedMessageLite)builder1.build();
                list.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (list)).bitField0_ | 0x400000;
                sendLogMessage(promocontext, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType.PROMO_CONDITIONS_EVALUATED, builder);
                return;
            }
        } while (true);
    }

    public final void logPromoNotShownClientBlocked$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5THMURBDDTN2UK3IDTMMUGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMOQB6CLHNIORCCKNKESJFETQ6GIR9EH1M2R3CC9GM6QRJ4H26ARJPA9IM2SRFDOTIILG_0(PromoContext promocontext, int i)
    {
        com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder builder;
        com.google.identity.growth.logging.proto.Client.PromoEvent.Builder builder1;
        builder = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj = promocontext.promoId();
        builder1 = (com.google.identity.growth.logging.proto.Client.PromoEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.PromoEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int j = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).impressionCappingId_;
        builder1.copyOnWrite();
        Object obj2 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        obj2.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj2)).bitField0_ | 1;
        obj2.impressionCappingId_ = j;
        obj2 = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).mendelId_;
        builder1.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_.isModifiable())
        {
            obj.mendelIds_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_);
        }
        obj = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_;
        Internal.checkNotNull(obj2);
        if (obj2 instanceof LazyStringList)
        {
            List list = ((LazyStringList)obj2).getUnderlyingElements();
            obj2 = (LazyStringList)obj;
            int k = ((List) (obj)).size();
            for (obj = list.iterator(); ((Iterator) (obj)).hasNext();)
            {
                Object obj3 = ((Iterator) (obj)).next();
                if (obj3 == null)
                {
                    i = ((LazyStringList) (obj2)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i - k).append(" is null.").toString();
                    for (i = ((LazyStringList) (obj2)).size() - 1; i >= k; i--)
                    {
                        ((LazyStringList) (obj2)).remove(i);
                    }

                    throw new NullPointerException(promocontext);
                }
                if (obj3 instanceof ByteString)
                {
                    ((LazyStringList) (obj2)).add((ByteString)obj3);
                } else
                {
                    ((LazyStringList) (obj2)).add((String)obj3);
                }
            }

        } else
        if (obj2 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj)).addAll((Collection)obj2);
        } else
        {
            if ((obj instanceof ArrayList) && (obj2 instanceof Collection))
            {
                ArrayList arraylist = (ArrayList)obj;
                int l = ((List) (obj)).size();
                arraylist.ensureCapacity(((Collection)obj2).size() + l);
            }
            int i1 = ((List) (obj)).size();
            obj2 = ((Iterable) (obj2)).iterator();
            while (((Iterator) (obj2)).hasNext()) 
            {
                Object obj4 = ((Iterator) (obj2)).next();
                if (obj4 == null)
                {
                    i = ((List) (obj)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i - i1).append(" is null.").toString();
                    for (i = ((List) (obj)).size() - 1; i >= i1; i--)
                    {
                        ((List) (obj)).remove(i);
                    }

                    throw new NullPointerException(promocontext);
                }
                ((List) (obj)).add(obj4);
            }
        }
        obj = com.google.identity.growth.logging.proto.Client.PromoEvent.PromoNotShownReason.PROMO_NOT_SHOWN_CLIENT_BLOCK;
        builder1.copyOnWrite();
        obj2 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj2.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj2)).bitField0_ | 8;
        obj2.promoNotShownReason_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent.PromoNotShownReason) (obj)).value;
        i - 1;
        JVM INSTR tableswitch 1 4: default 628
    //                   1 661
    //                   2 770
    //                   3 829
    //                   4 888;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        Object obj1;
        com.google.identity.growth.logging.proto.Client.PromoEvent promoevent;
        obj1 = com.google.identity.growth.logging.proto.Client.PromoEvent.DisplayBlockReason.DISPLAY_BLOCK_UNKNOWN;
        builder1.copyOnWrite();
        promoevent = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        break; /* Loop/switch isn't completed */
_L2:
        obj1 = com.google.identity.growth.logging.proto.Client.PromoEvent.DisplayBlockReason.DISPLAY_BLOCK_CLIENT_REJECT;
        builder1.copyOnWrite();
        promoevent = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        promoevent.bitField0_ = promoevent.bitField0_ | 0x10;
        promoevent.displayBlockReason_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent.DisplayBlockReason) (obj1)).value;
_L7:
        builder.copyOnWrite();
        obj1 = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
        obj1.promoEvent_ = (com.google.identity.growth.logging.proto.Client.PromoEvent)(GeneratedMessageLite)builder1.build();
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj1)).bitField0_ | 0x400000;
        sendLogMessage(promocontext, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType.PROMO_NOT_SHOWN, builder);
        return;
_L3:
        obj1 = com.google.identity.growth.logging.proto.Client.PromoEvent.DisplayBlockReason.DISPLAY_BLOCK_CLIENT_ERROR;
        builder1.copyOnWrite();
        promoevent = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        promoevent.bitField0_ = promoevent.bitField0_ | 0x10;
        promoevent.displayBlockReason_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent.DisplayBlockReason) (obj1)).value;
        continue; /* Loop/switch isn't completed */
_L4:
        obj1 = com.google.identity.growth.logging.proto.Client.PromoEvent.DisplayBlockReason.DISPLAY_BLOCK_TRY_AGAIN_LATER;
        builder1.copyOnWrite();
        promoevent = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        promoevent.bitField0_ = promoevent.bitField0_ | 0x10;
        promoevent.displayBlockReason_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent.DisplayBlockReason) (obj1)).value;
        continue; /* Loop/switch isn't completed */
_L5:
        obj1 = com.google.identity.growth.logging.proto.Client.PromoEvent.DisplayBlockReason.DISPLAY_BLOCK_LEGACY_USER;
        builder1.copyOnWrite();
        promoevent = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        promoevent.bitField0_ = promoevent.bitField0_ | 0x10;
        promoevent.displayBlockReason_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent.DisplayBlockReason) (obj1)).value;
        continue; /* Loop/switch isn't completed */
        promoevent.bitField0_ = promoevent.bitField0_ | 0x10;
        promoevent.displayBlockReason_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent.DisplayBlockReason) (obj1)).value;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void logPromoNotShownControlGroup(PromoContext promocontext)
    {
        Object obj = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj1 = promocontext.promoId();
        com.google.identity.growth.logging.proto.Client.PromoEvent.Builder builder = (com.google.identity.growth.logging.proto.Client.PromoEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.PromoEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int i = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj1)).impressionCappingId_;
        builder.copyOnWrite();
        Object obj2 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder.instance;
        obj2.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj2)).bitField0_ | 1;
        obj2.impressionCappingId_ = i;
        obj2 = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj1)).mendelId_;
        builder.copyOnWrite();
        obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder.instance;
        if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).mendelIds_.isModifiable())
        {
            obj1.mendelIds_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).mendelIds_);
        }
        obj1 = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).mendelIds_;
        Internal.checkNotNull(obj2);
        if (obj2 instanceof LazyStringList)
        {
            List list = ((LazyStringList)obj2).getUnderlyingElements();
            obj2 = (LazyStringList)obj1;
            int k1 = ((List) (obj1)).size();
            for (obj1 = list.iterator(); ((Iterator) (obj1)).hasNext();)
            {
                Object obj3 = ((Iterator) (obj1)).next();
                if (obj3 == null)
                {
                    int j = ((LazyStringList) (obj2)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(j - k1).append(" is null.").toString();
                    for (int k = ((LazyStringList) (obj2)).size() - 1; k >= k1; k--)
                    {
                        ((LazyStringList) (obj2)).remove(k);
                    }

                    throw new NullPointerException(promocontext);
                }
                if (obj3 instanceof ByteString)
                {
                    ((LazyStringList) (obj2)).add((ByteString)obj3);
                } else
                {
                    ((LazyStringList) (obj2)).add((String)obj3);
                }
            }

        } else
        if (obj2 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj1)).addAll((Collection)obj2);
        } else
        {
            if ((obj1 instanceof ArrayList) && (obj2 instanceof Collection))
            {
                ArrayList arraylist = (ArrayList)obj1;
                int l = ((List) (obj1)).size();
                arraylist.ensureCapacity(((Collection)obj2).size() + l);
            }
            int l1 = ((List) (obj1)).size();
            obj2 = ((Iterable) (obj2)).iterator();
            while (((Iterator) (obj2)).hasNext()) 
            {
                Object obj4 = ((Iterator) (obj2)).next();
                if (obj4 == null)
                {
                    int i1 = ((List) (obj1)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i1 - l1).append(" is null.").toString();
                    for (int j1 = ((List) (obj1)).size() - 1; j1 >= l1; j1--)
                    {
                        ((List) (obj1)).remove(j1);
                    }

                    throw new NullPointerException(promocontext);
                }
                ((List) (obj1)).add(obj4);
            }
        }
        obj1 = com.google.identity.growth.logging.proto.Client.PromoEvent.PromoNotShownReason.PROMO_NOT_SHOWN_CONTROL_GROUP;
        builder.copyOnWrite();
        obj2 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        obj2.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj2)).bitField0_ | 8;
        obj2.promoNotShownReason_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent.PromoNotShownReason) (obj1)).value;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        obj1 = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder) (obj)).instance;
        obj1.promoEvent_ = (com.google.identity.growth.logging.proto.Client.PromoEvent)(GeneratedMessageLite)builder.build();
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj1)).bitField0_ | 0x400000;
        sendLogMessage(promocontext, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType.PROMO_NOT_SHOWN, ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder) (obj)));
        obj = streamzIncrements;
        impressionsCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            appPackageName, "CONTROL_NOT_SEEN"
        }));
        StreamzIncrements.incrementCounts++;
        if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj)).incrementsToFlush.get()).longValue())
        {
            promocontext = ((StreamzIncrements) (obj)).streamzLogger;
            obj = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj)).metricFactory);
            boolean flag;
            if (((StreamzMessageProducer) (obj)).incrementRequest.batch_.size() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                ((GcoreClearcutStreamzLogger) (promocontext)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj))).setLogSourceName(((GcoreClearcutStreamzLogger) (promocontext)).logSourceName).logAsync();
            }
            StreamzIncrements.incrementCounts = 0;
        }
    }

    public final void logPromoNotShownDeviceCapped(PromoContext promocontext)
    {
        com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder builder = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj = promocontext.promoId();
        com.google.identity.growth.logging.proto.Client.PromoEvent.Builder builder1 = (com.google.identity.growth.logging.proto.Client.PromoEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.PromoEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int i = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).impressionCappingId_;
        builder1.copyOnWrite();
        Object obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).bitField0_ | 1;
        obj1.impressionCappingId_ = i;
        obj1 = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).mendelId_;
        builder1.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_.isModifiable())
        {
            obj.mendelIds_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_);
        }
        obj = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_;
        Internal.checkNotNull(obj1);
        if (obj1 instanceof LazyStringList)
        {
            List list = ((LazyStringList)obj1).getUnderlyingElements();
            obj1 = (LazyStringList)obj;
            int k1 = ((List) (obj)).size();
            for (obj = list.iterator(); ((Iterator) (obj)).hasNext();)
            {
                Object obj2 = ((Iterator) (obj)).next();
                if (obj2 == null)
                {
                    int j = ((LazyStringList) (obj1)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(j - k1).append(" is null.").toString();
                    for (int k = ((LazyStringList) (obj1)).size() - 1; k >= k1; k--)
                    {
                        ((LazyStringList) (obj1)).remove(k);
                    }

                    throw new NullPointerException(promocontext);
                }
                if (obj2 instanceof ByteString)
                {
                    ((LazyStringList) (obj1)).add((ByteString)obj2);
                } else
                {
                    ((LazyStringList) (obj1)).add((String)obj2);
                }
            }

        } else
        if (obj1 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj)).addAll((Collection)obj1);
        } else
        {
            if ((obj instanceof ArrayList) && (obj1 instanceof Collection))
            {
                ArrayList arraylist = (ArrayList)obj;
                int l = ((List) (obj)).size();
                arraylist.ensureCapacity(((Collection)obj1).size() + l);
            }
            int l1 = ((List) (obj)).size();
            obj1 = ((Iterable) (obj1)).iterator();
            while (((Iterator) (obj1)).hasNext()) 
            {
                Object obj3 = ((Iterator) (obj1)).next();
                if (obj3 == null)
                {
                    int i1 = ((List) (obj)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i1 - l1).append(" is null.").toString();
                    for (int j1 = ((List) (obj)).size() - 1; j1 >= l1; j1--)
                    {
                        ((List) (obj)).remove(j1);
                    }

                    throw new NullPointerException(promocontext);
                }
                ((List) (obj)).add(obj3);
            }
        }
        builder.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
        obj.promoEvent_ = (com.google.identity.growth.logging.proto.Client.PromoEvent)(GeneratedMessageLite)builder1.build();
        obj.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj)).bitField0_ | 0x400000;
        sendLogMessage(promocontext, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType.PROMO_NOT_SHOWN_DEVICE_CAPPED, builder);
    }

    public final void logPromoShown(PromoContext promocontext)
    {
        com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder builder = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj = promocontext.promoId();
        com.google.identity.growth.logging.proto.Client.PromoEvent.Builder builder1 = (com.google.identity.growth.logging.proto.Client.PromoEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.PromoEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int i = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).impressionCappingId_;
        builder1.copyOnWrite();
        Object obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).bitField0_ | 1;
        obj1.impressionCappingId_ = i;
        obj1 = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).mendelId_;
        builder1.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_.isModifiable())
        {
            obj.mendelIds_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_);
        }
        obj = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_;
        Internal.checkNotNull(obj1);
        if (obj1 instanceof LazyStringList)
        {
            List list = ((LazyStringList)obj1).getUnderlyingElements();
            obj1 = (LazyStringList)obj;
            int k1 = ((List) (obj)).size();
            for (obj = list.iterator(); ((Iterator) (obj)).hasNext();)
            {
                Object obj2 = ((Iterator) (obj)).next();
                if (obj2 == null)
                {
                    int j = ((LazyStringList) (obj1)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(j - k1).append(" is null.").toString();
                    for (int k = ((LazyStringList) (obj1)).size() - 1; k >= k1; k--)
                    {
                        ((LazyStringList) (obj1)).remove(k);
                    }

                    throw new NullPointerException(promocontext);
                }
                if (obj2 instanceof ByteString)
                {
                    ((LazyStringList) (obj1)).add((ByteString)obj2);
                } else
                {
                    ((LazyStringList) (obj1)).add((String)obj2);
                }
            }

        } else
        if (obj1 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj)).addAll((Collection)obj1);
        } else
        {
            if ((obj instanceof ArrayList) && (obj1 instanceof Collection))
            {
                ArrayList arraylist = (ArrayList)obj;
                int l = ((List) (obj)).size();
                arraylist.ensureCapacity(((Collection)obj1).size() + l);
            }
            int l1 = ((List) (obj)).size();
            obj1 = ((Iterable) (obj1)).iterator();
            while (((Iterator) (obj1)).hasNext()) 
            {
                Object obj3 = ((Iterator) (obj1)).next();
                if (obj3 == null)
                {
                    int i1 = ((List) (obj)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i1 - l1).append(" is null.").toString();
                    for (int j1 = ((List) (obj)).size() - 1; j1 >= l1; j1--)
                    {
                        ((List) (obj)).remove(j1);
                    }

                    throw new NullPointerException(promocontext);
                }
                ((List) (obj)).add(obj3);
            }
        }
        builder.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
        obj.promoEvent_ = (com.google.identity.growth.logging.proto.Client.PromoEvent)(GeneratedMessageLite)builder1.build();
        obj.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj)).bitField0_ | 0x400000;
        sendLogMessage(promocontext, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType.PROMO_SHOWN, builder);
    }

    public final void logPromoTargetingEvaluated(PromoContext promocontext, boolean flag)
    {
        com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder builder = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj = promocontext.promoId();
        com.google.identity.growth.logging.proto.Client.PromoEvent.Builder builder1 = (com.google.identity.growth.logging.proto.Client.PromoEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.PromoEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int i = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).impressionCappingId_;
        builder1.copyOnWrite();
        Object obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).bitField0_ | 1;
        obj1.impressionCappingId_ = i;
        obj1 = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).mendelId_;
        builder1.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_.isModifiable())
        {
            obj.mendelIds_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_);
        }
        obj = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_;
        Internal.checkNotNull(obj1);
        if (obj1 instanceof LazyStringList)
        {
            List list = ((LazyStringList)obj1).getUnderlyingElements();
            obj1 = (LazyStringList)obj;
            int k1 = ((List) (obj)).size();
            for (obj = list.iterator(); ((Iterator) (obj)).hasNext();)
            {
                Object obj2 = ((Iterator) (obj)).next();
                if (obj2 == null)
                {
                    int j = ((LazyStringList) (obj1)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(j - k1).append(" is null.").toString();
                    for (int k = ((LazyStringList) (obj1)).size() - 1; k >= k1; k--)
                    {
                        ((LazyStringList) (obj1)).remove(k);
                    }

                    throw new NullPointerException(promocontext);
                }
                if (obj2 instanceof ByteString)
                {
                    ((LazyStringList) (obj1)).add((ByteString)obj2);
                } else
                {
                    ((LazyStringList) (obj1)).add((String)obj2);
                }
            }

        } else
        if (obj1 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj)).addAll((Collection)obj1);
        } else
        {
            if ((obj instanceof ArrayList) && (obj1 instanceof Collection))
            {
                ArrayList arraylist = (ArrayList)obj;
                int l = ((List) (obj)).size();
                arraylist.ensureCapacity(((Collection)obj1).size() + l);
            }
            int l1 = ((List) (obj)).size();
            obj1 = ((Iterable) (obj1)).iterator();
            while (((Iterator) (obj1)).hasNext()) 
            {
                Object obj3 = ((Iterator) (obj1)).next();
                if (obj3 == null)
                {
                    int i1 = ((List) (obj)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i1 - l1).append(" is null.").toString();
                    for (int j1 = ((List) (obj)).size() - 1; j1 >= l1; j1--)
                    {
                        ((List) (obj)).remove(j1);
                    }

                    throw new NullPointerException(promocontext);
                }
                ((List) (obj)).add(obj3);
            }
        }
        builder1.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        obj.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).bitField0_ | 4;
        obj.targetingRuleNotSatisfied_ = flag;
        builder.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
        obj.promoEvent_ = (com.google.identity.growth.logging.proto.Client.PromoEvent)(GeneratedMessageLite)builder1.build();
        obj.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj)).bitField0_ | 0x400000;
        sendLogMessage(promocontext, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType.PROMO_TARGETING_EVALUATED, builder);
    }

    public final void logPromoTriggered(PromoContext promocontext, int i)
    {
        com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder builder = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj = promocontext.promoId();
        com.google.identity.growth.logging.proto.Client.PromoEvent.Builder builder1 = (com.google.identity.growth.logging.proto.Client.PromoEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.PromoEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int j = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).impressionCappingId_;
        builder1.copyOnWrite();
        Object obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).bitField0_ | 1;
        obj1.impressionCappingId_ = j;
        obj1 = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).mendelId_;
        builder1.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_.isModifiable())
        {
            obj.mendelIds_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_);
        }
        obj = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_;
        Internal.checkNotNull(obj1);
        if (obj1 instanceof LazyStringList)
        {
            List list = ((LazyStringList)obj1).getUnderlyingElements();
            obj1 = (LazyStringList)obj;
            int k = ((List) (obj)).size();
            for (obj = list.iterator(); ((Iterator) (obj)).hasNext();)
            {
                Object obj2 = ((Iterator) (obj)).next();
                if (obj2 == null)
                {
                    i = ((LazyStringList) (obj1)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i - k).append(" is null.").toString();
                    for (i = ((LazyStringList) (obj1)).size() - 1; i >= k; i--)
                    {
                        ((LazyStringList) (obj1)).remove(i);
                    }

                    throw new NullPointerException(promocontext);
                }
                if (obj2 instanceof ByteString)
                {
                    ((LazyStringList) (obj1)).add((ByteString)obj2);
                } else
                {
                    ((LazyStringList) (obj1)).add((String)obj2);
                }
            }

        } else
        if (obj1 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj)).addAll((Collection)obj1);
        } else
        {
            if ((obj instanceof ArrayList) && (obj1 instanceof Collection))
            {
                ArrayList arraylist = (ArrayList)obj;
                int l = ((List) (obj)).size();
                arraylist.ensureCapacity(((Collection)obj1).size() + l);
            }
            int i1 = ((List) (obj)).size();
            obj1 = ((Iterable) (obj1)).iterator();
            while (((Iterator) (obj1)).hasNext()) 
            {
                Object obj3 = ((Iterator) (obj1)).next();
                if (obj3 == null)
                {
                    i = ((List) (obj)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i - i1).append(" is null.").toString();
                    for (i = ((List) (obj)).size() - 1; i >= i1; i--)
                    {
                        ((List) (obj)).remove(i);
                    }

                    throw new NullPointerException(promocontext);
                }
                ((List) (obj)).add(obj3);
            }
        }
        builder1.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        obj.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).bitField0_ | 2;
        obj.triggeringEventIndex_ = i;
        builder.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
        obj.promoEvent_ = (com.google.identity.growth.logging.proto.Client.PromoEvent)(GeneratedMessageLite)builder1.build();
        obj.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj)).bitField0_ | 0x400000;
        sendLogMessage(promocontext, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType.PROMO_TRIGGERED, builder);
    }

    public final void logPromoUserAction(PromoContext promocontext, com.google.identity.growth.logging.proto.Client.PromoEvent.UserAction useraction)
    {
        com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder builder = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj = promocontext.promoId();
        com.google.identity.growth.logging.proto.Client.PromoEvent.Builder builder1 = (com.google.identity.growth.logging.proto.Client.PromoEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.PromoEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int i = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).impressionCappingId_;
        builder1.copyOnWrite();
        Object obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).bitField0_ | 1;
        obj1.impressionCappingId_ = i;
        obj1 = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj)).mendelId_;
        builder1.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_.isModifiable())
        {
            obj.mendelIds_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_);
        }
        obj = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).mendelIds_;
        Internal.checkNotNull(obj1);
        if (obj1 instanceof LazyStringList)
        {
            List list = ((LazyStringList)obj1).getUnderlyingElements();
            obj1 = (LazyStringList)obj;
            int k1 = ((List) (obj)).size();
            for (obj = list.iterator(); ((Iterator) (obj)).hasNext();)
            {
                Object obj2 = ((Iterator) (obj)).next();
                if (obj2 == null)
                {
                    int j = ((LazyStringList) (obj1)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(j - k1).append(" is null.").toString();
                    for (int k = ((LazyStringList) (obj1)).size() - 1; k >= k1; k--)
                    {
                        ((LazyStringList) (obj1)).remove(k);
                    }

                    throw new NullPointerException(promocontext);
                }
                if (obj2 instanceof ByteString)
                {
                    ((LazyStringList) (obj1)).add((ByteString)obj2);
                } else
                {
                    ((LazyStringList) (obj1)).add((String)obj2);
                }
            }

        } else
        if (obj1 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj)).addAll((Collection)obj1);
        } else
        {
            if ((obj instanceof ArrayList) && (obj1 instanceof Collection))
            {
                ArrayList arraylist = (ArrayList)obj;
                int l = ((List) (obj)).size();
                arraylist.ensureCapacity(((Collection)obj1).size() + l);
            }
            int l1 = ((List) (obj)).size();
            obj1 = ((Iterable) (obj1)).iterator();
            while (((Iterator) (obj1)).hasNext()) 
            {
                Object obj3 = ((Iterator) (obj1)).next();
                if (obj3 == null)
                {
                    int i1 = ((List) (obj)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i1 - l1).append(" is null.").toString();
                    for (int j1 = ((List) (obj)).size() - 1; j1 >= l1; j1--)
                    {
                        ((List) (obj)).remove(j1);
                    }

                    throw new NullPointerException(promocontext);
                }
                ((List) (obj)).add(obj3);
            }
        }
        builder1.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder1.instance;
        if (useraction == null)
        {
            throw new NullPointerException();
        }
        obj.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj)).bitField0_ | 0x20;
        obj.userAction_ = useraction.value;
        builder.copyOnWrite();
        obj = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)builder.instance;
        obj.promoEvent_ = (com.google.identity.growth.logging.proto.Client.PromoEvent)(GeneratedMessageLite)builder1.build();
        obj.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj)).bitField0_ | 0x400000;
        sendLogMessage(promocontext, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType.PROMO_USER_ACTION, builder);
        promocontext = streamzIncrements;
        impressionsCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            appPackageName, useraction.name()
        }));
        StreamzIncrements.incrementCounts++;
        if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (promocontext)).incrementsToFlush.get()).longValue())
        {
            useraction = ((StreamzIncrements) (promocontext)).streamzLogger;
            promocontext = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (promocontext)).metricFactory);
            boolean flag;
            if (((StreamzMessageProducer) (promocontext)).incrementRequest.batch_.size() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                ((GcoreClearcutStreamzLogger) (useraction)).gcoreClearcutLogger.newEvent(promocontext).setLogSourceName(((GcoreClearcutStreamzLogger) (useraction)).logSourceName).logAsync();
            }
            StreamzIncrements.incrementCounts = 0;
        }
    }

    public final void logPromoUserDismissed(PromoContext promocontext)
    {
        Object obj = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj1 = promocontext.promoId();
        com.google.identity.growth.logging.proto.Client.PromoEvent.Builder builder = (com.google.identity.growth.logging.proto.Client.PromoEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.logging.proto.Client.PromoEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int i = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj1)).impressionCappingId_;
        builder.copyOnWrite();
        Object obj2 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder.instance;
        obj2.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj2)).bitField0_ | 1;
        obj2.impressionCappingId_ = i;
        obj2 = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj1)).mendelId_;
        builder.copyOnWrite();
        obj1 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder.instance;
        if (!((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).mendelIds_.isModifiable())
        {
            obj1.mendelIds_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).mendelIds_);
        }
        obj1 = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj1)).mendelIds_;
        Internal.checkNotNull(obj2);
        if (obj2 instanceof LazyStringList)
        {
            List list = ((LazyStringList)obj2).getUnderlyingElements();
            obj2 = (LazyStringList)obj1;
            int k1 = ((List) (obj1)).size();
            for (obj1 = list.iterator(); ((Iterator) (obj1)).hasNext();)
            {
                Object obj3 = ((Iterator) (obj1)).next();
                if (obj3 == null)
                {
                    int j = ((LazyStringList) (obj2)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(j - k1).append(" is null.").toString();
                    for (int k = ((LazyStringList) (obj2)).size() - 1; k >= k1; k--)
                    {
                        ((LazyStringList) (obj2)).remove(k);
                    }

                    throw new NullPointerException(promocontext);
                }
                if (obj3 instanceof ByteString)
                {
                    ((LazyStringList) (obj2)).add((ByteString)obj3);
                } else
                {
                    ((LazyStringList) (obj2)).add((String)obj3);
                }
            }

        } else
        if (obj2 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj1)).addAll((Collection)obj2);
        } else
        {
            if ((obj1 instanceof ArrayList) && (obj2 instanceof Collection))
            {
                ArrayList arraylist = (ArrayList)obj1;
                int l = ((List) (obj1)).size();
                arraylist.ensureCapacity(((Collection)obj2).size() + l);
            }
            int l1 = ((List) (obj1)).size();
            obj2 = ((Iterable) (obj2)).iterator();
            while (((Iterator) (obj2)).hasNext()) 
            {
                Object obj4 = ((Iterator) (obj2)).next();
                if (obj4 == null)
                {
                    int i1 = ((List) (obj1)).size();
                    promocontext = (new StringBuilder(37)).append("Element at index ").append(i1 - l1).append(" is null.").toString();
                    for (int j1 = ((List) (obj1)).size() - 1; j1 >= l1; j1--)
                    {
                        ((List) (obj1)).remove(j1);
                    }

                    throw new NullPointerException(promocontext);
                }
                ((List) (obj1)).add(obj4);
            }
        }
        obj1 = com.google.identity.growth.logging.proto.Client.PromoEvent.UserAction.ACTION_DISMISS;
        builder.copyOnWrite();
        obj2 = (com.google.identity.growth.logging.proto.Client.PromoEvent)builder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        obj2.bitField0_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent) (obj2)).bitField0_ | 0x20;
        obj2.userAction_ = ((com.google.identity.growth.logging.proto.Client.PromoEvent.UserAction) (obj1)).value;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        obj1 = (com.google.identity.growth.logging.proto.Client.GrowthMetricsLog)((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder) (obj)).instance;
        obj1.promoEvent_ = (com.google.identity.growth.logging.proto.Client.PromoEvent)(GeneratedMessageLite)builder.build();
        obj1.bitField0_ = ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog) (obj1)).bitField0_ | 0x400000;
        sendLogMessage(promocontext, com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.EventCodeType.PROMO_USER_DISMISSED, ((com.google.identity.growth.logging.proto.Client.GrowthMetricsLog.Builder) (obj)));
        obj = streamzIncrements;
        impressionsCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            appPackageName, "DISMISSED"
        }));
        StreamzIncrements.incrementCounts++;
        if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj)).incrementsToFlush.get()).longValue())
        {
            promocontext = ((StreamzIncrements) (obj)).streamzLogger;
            obj = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj)).metricFactory);
            boolean flag;
            if (((StreamzMessageProducer) (obj)).incrementRequest.batch_.size() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                ((GcoreClearcutStreamzLogger) (promocontext)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj))).setLogSourceName(((GcoreClearcutStreamzLogger) (promocontext)).logSourceName).logAsync();
            }
            StreamzIncrements.incrementCounts = 0;
        }
    }

}
