// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.events.impl;

import com.google.android.libraries.internal.growth.growthkit.events.GrowthKitEventManager;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Trace;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.Counter3;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.identity.growth.common.BaseAppUtil;
import com.google.protobuf.GeneratedMessageLite;
import dagger.Lazy;
import javax.inject.Provider;

public final class GrowthKitEventManagerImpl
    implements GrowthKitEventManager
{

    public static final Logger logger = new Logger();
    public final String appPackageName;
    public final PerAccountProvider clearcutEventCounter;
    private final Provider enableFlag;
    public final ListeningExecutorService executor;
    public final Counter3 loggingCounter;
    public final PerAccountProvider monitoredEventClearcutStoreProvider;
    public final Lazy promotionsManagerLazy;
    public final Provider saveOnlyMonitoredEvents;
    public final StreamzIncrements streamzIncrements;
    private final Trace trace;

    public GrowthKitEventManagerImpl(PerAccountProvider peraccountprovider, Lazy lazy, String s, Provider provider, Provider provider1, PerAccountProvider peraccountprovider1, PerAccountProvider peraccountprovider2, 
            PerAccountProvider peraccountprovider3, ListeningExecutorService listeningexecutorservice, Counter3 counter3, StreamzIncrements streamzincrements, Trace trace1)
    {
        clearcutEventCounter = peraccountprovider;
        promotionsManagerLazy = lazy;
        appPackageName = s;
        enableFlag = provider;
        saveOnlyMonitoredEvents = provider1;
        monitoredEventClearcutStoreProvider = peraccountprovider1;
        executor = listeningexecutorservice;
        loggingCounter = counter3;
        streamzIncrements = streamzincrements;
        trace = trace1;
    }

    public final ListenableFuture reportClearCutEventAsync(int i, int j, String s)
    {
        Object obj = (com.google.identity.growth.proto.Promotion.ClearcutEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.proto.Promotion.ClearcutEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        Object obj1 = (com.google.identity.growth.proto.Promotion.ClearcutEvent)((com.google.identity.growth.proto.Promotion.ClearcutEvent.Builder) (obj)).instance;
        obj1.bitField0_ = ((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj1)).bitField0_ | 1;
        obj1.logSource_ = 111;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        obj1 = (com.google.identity.growth.proto.Promotion.ClearcutEvent)((com.google.identity.growth.proto.Promotion.ClearcutEvent.Builder) (obj)).instance;
        obj1.bitField0_ = ((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj1)).bitField0_ | 2;
        obj1.eventCode_ = j;
        obj1 = BaseAppUtil.normalizeAndroidPackageName(appPackageName);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        Object obj2 = (com.google.identity.growth.proto.Promotion.ClearcutEvent)((com.google.identity.growth.proto.Promotion.ClearcutEvent.Builder) (obj)).instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            obj2.bitField0_ = ((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj2)).bitField0_ | 4;
            obj2.bundleIdentifier_ = ((String) (obj1));
            obj = (com.google.identity.growth.proto.Promotion.ClearcutEvent)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
            obj1 = executor;
            obj2 = trace;
            Provider provider = enableFlag;
            provider.getClass();
            class .Lambda._cls5
                implements Callable
            {

                private final Provider arg$1;

                public final Object call()
                {
                    return arg$1.get();
                }

            .Lambda._cls5(Provider provider)
            {
                arg$1 = provider;
            }
            }

            obj1 = ((ListeningExecutorService) (obj1)).submit(((Trace) (obj2)).propagateCallable(new .Lambda._cls5(provider)));
            class .Lambda._cls0
                implements AsyncFunction
            {

                private final GrowthKitEventManagerImpl arg$1;
                private final com.google.identity.growth.proto.Promotion.ClearcutEvent arg$2;
                private final String arg$3;

                public final ListenableFuture apply(Object obj3)
                {
                    Object obj4 = arg$1;
                    Object obj5 = arg$2;
                    Object obj6 = arg$3;
                    if (!((Boolean)obj3).booleanValue())
                    {
                        obj3 = GrowthKitEventManagerImpl.logger;
                        obj3 = ((Object) (new Object[0]));
                        if (obj3 != null && obj3.length > 0)
                        {
                            String.format("GrowthKit is disabled by Phenotype flag.", ((Object []) (obj3)));
                        }
                        if (true)
                        {
                            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                        } else
                        {
                            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
                        }
                    }
                    class .Lambda._cls6
                        implements AsyncFunction
                    {

                        private final GrowthKitEventManagerImpl arg$1;
                        private final String arg$2;
                        private final com.google.identity.growth.proto.Promotion.ClearcutEvent arg$3;

                        public final ListenableFuture apply(Object obj7)
                        {
                            Object obj8 = arg$1;
                            Object obj9 = arg$2;
                            Object obj10 = arg$3;
                            if (!((Boolean)obj7).booleanValue())
                            {
                                if (true)
                                {
                                    return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                                } else
                                {
                                    return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
                                }
                            }
                            obj7 = ((ClearcutEventsStore)((GrowthKitEventManagerImpl) (obj8)).clearcutEventCounter.forAccount(((String) (obj9)))).addEvent(((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj10)));
                            class .Lambda._cls14
                                implements Receiver
                            {

                                private final GrowthKitEventManagerImpl arg$1;

                                public final void accept(Object obj11)
                                {
                                    GrowthKitEventManagerImpl growthkiteventmanagerimpl = arg$1;
                                    obj11 = growthkiteventmanagerimpl.streamzIncrements;
                                    growthkiteventmanagerimpl.loggingCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
                                        growthkiteventmanagerimpl.appPackageName, "Clearcut", "OK"
                                    }));
                                    StreamzIncrements.incrementCounts++;
                                    if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj11)).incrementsToFlush.get()).longValue())
                                    {
                                        GcoreClearcutStreamzLogger gcoreclearcutstreamzlogger = ((StreamzIncrements) (obj11)).streamzLogger;
                                        obj11 = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj11)).metricFactory);
                                        boolean flag;
                                        if (((StreamzMessageProducer) (obj11)).incrementRequest.batch_.size() == 0)
                                        {
                                            flag = true;
                                        } else
                                        {
                                            flag = false;
                                        }
                                        if (!flag)
                                        {
                                            gcoreclearcutstreamzlogger.gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj11))).setLogSourceName(gcoreclearcutstreamzlogger.logSourceName).logAsync();
                                        }
                                        StreamzIncrements.incrementCounts = 0;
                                    }
                                }

                                    .Lambda._cls14()
                                    {
                                        arg$1 = GrowthKitEventManagerImpl.this;
                                    }
                            }

                            obj9 = ((.Lambda._cls14) (obj8)). new .Lambda._cls14();
                            class .Lambda._cls15
                                implements Receiver
                            {

                                private final GrowthKitEventManagerImpl arg$1;

                                public final void accept(Object obj11)
                                {
                                    GrowthKitEventManagerImpl growthkiteventmanagerimpl = arg$1;
                                    Throwable throwable = (Throwable)obj11;
                                    obj11 = GrowthKitEventManagerImpl.logger;
                                    String s1 = "Failed to log clearcut event.";
                                    Object aobj[] = new Object[0];
                                    String s2 = ((Logger) (obj11)).tag;
                                    obj11 = s1;
                                    if (aobj != null)
                                    {
                                        obj11 = s1;
                                        if (aobj.length > 0)
                                        {
                                            obj11 = String.format("Failed to log clearcut event.", aobj);
                                        }
                                    }
                                    Log.w(s2, ((String) (obj11)), throwable);
                                    obj11 = growthkiteventmanagerimpl.streamzIncrements;
                                    growthkiteventmanagerimpl.loggingCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
                                        growthkiteventmanagerimpl.appPackageName, "Clearcut", "ERROR"
                                    }));
                                    StreamzIncrements.incrementCounts++;
                                    if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj11)).incrementsToFlush.get()).longValue())
                                    {
                                        GcoreClearcutStreamzLogger gcoreclearcutstreamzlogger = ((StreamzIncrements) (obj11)).streamzLogger;
                                        obj11 = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj11)).metricFactory);
                                        boolean flag;
                                        if (((StreamzMessageProducer) (obj11)).incrementRequest.batch_.size() == 0)
                                        {
                                            flag = true;
                                        } else
                                        {
                                            flag = false;
                                        }
                                        if (!flag)
                                        {
                                            gcoreclearcutstreamzlogger.gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj11))).setLogSourceName(gcoreclearcutstreamzlogger.logSourceName).logAsync();
                                        }
                                        StreamzIncrements.incrementCounts = 0;
                                    }
                                }

                                    .Lambda._cls15()
                                    {
                                        arg$1 = GrowthKitEventManagerImpl.this;
                                    }
                            }

                            obj10 = ((.Lambda._cls15) (obj8)). new .Lambda._cls15();
                            obj8 = ((GrowthKitEventManagerImpl) (obj8)).executor;
                            obj9 = new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.MoreFutures._cls1(((Receiver) (obj9)), ((Receiver) (obj10)));
                            if (obj9 == null)
                            {
                                throw new NullPointerException();
                            } else
                            {
                                ((ListenableFuture) (obj7)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((Future) (obj7)), ((com.google.common.util.concurrent.FutureCallback) (obj9))), ((java.util.concurrent.Executor) (obj8)));
                                return ((ListenableFuture) (obj7));
                            }
                        }

                            .Lambda._cls6(String s, com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent)
                            {
                                arg$1 = GrowthKitEventManagerImpl.this;
                                arg$2 = s;
                                arg$3 = clearcutevent;
                            }
                    }

                    if (((Boolean)((GrowthKitEventManagerImpl) (obj4)).saveOnlyMonitoredEvents.get()).booleanValue())
                    {
                        return AbstractTransformFuture.create(((MessageStore)((GrowthKitEventManagerImpl) (obj4)).monitoredEventClearcutStoreProvider.forAccount(((String) (obj6)))).contains(PromotionKeysHelper.of(((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj5)))), ((.Lambda._cls6) (obj4)). new .Lambda._cls6(((String) (obj6)), ((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj5))), ((GrowthKitEventManagerImpl) (obj4)).executor);
                    }
                    obj3 = ((ClearcutEventsStore)((GrowthKitEventManagerImpl) (obj4)).clearcutEventCounter.forAccount(((String) (obj6)))).addEvent(((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj5)));
                    class .Lambda._cls7
                        implements Receiver
                    {

                        private final GrowthKitEventManagerImpl arg$1;

                        public final void accept(Object obj7)
                        {
                            GrowthKitEventManagerImpl growthkiteventmanagerimpl = arg$1;
                            obj7 = growthkiteventmanagerimpl.streamzIncrements;
                            growthkiteventmanagerimpl.loggingCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
                                growthkiteventmanagerimpl.appPackageName, "Clearcut", "OK"
                            }));
                            StreamzIncrements.incrementCounts++;
                            if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj7)).incrementsToFlush.get()).longValue())
                            {
                                GcoreClearcutStreamzLogger gcoreclearcutstreamzlogger = ((StreamzIncrements) (obj7)).streamzLogger;
                                obj7 = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj7)).metricFactory);
                                boolean flag;
                                if (((StreamzMessageProducer) (obj7)).incrementRequest.batch_.size() == 0)
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (!flag)
                                {
                                    gcoreclearcutstreamzlogger.gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj7))).setLogSourceName(gcoreclearcutstreamzlogger.logSourceName).logAsync();
                                }
                                StreamzIncrements.incrementCounts = 0;
                            }
                        }

                            .Lambda._cls7()
                            {
                                arg$1 = GrowthKitEventManagerImpl.this;
                            }
                    }

                    obj5 = ((.Lambda._cls7) (obj4)). new .Lambda._cls7();
                    class .Lambda._cls8
                        implements Receiver
                    {

                        private final GrowthKitEventManagerImpl arg$1;

                        public final void accept(Object obj7)
                        {
                            GrowthKitEventManagerImpl growthkiteventmanagerimpl = arg$1;
                            Throwable throwable = (Throwable)obj7;
                            obj7 = GrowthKitEventManagerImpl.logger;
                            String s1 = "Failed to log clearcut event.";
                            Object aobj[] = new Object[0];
                            String s2 = ((Logger) (obj7)).tag;
                            obj7 = s1;
                            if (aobj != null)
                            {
                                obj7 = s1;
                                if (aobj.length > 0)
                                {
                                    obj7 = String.format("Failed to log clearcut event.", aobj);
                                }
                            }
                            Log.w(s2, ((String) (obj7)), throwable);
                            obj7 = growthkiteventmanagerimpl.streamzIncrements;
                            growthkiteventmanagerimpl.loggingCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
                                growthkiteventmanagerimpl.appPackageName, "Clearcut", "ERROR"
                            }));
                            StreamzIncrements.incrementCounts++;
                            if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj7)).incrementsToFlush.get()).longValue())
                            {
                                GcoreClearcutStreamzLogger gcoreclearcutstreamzlogger = ((StreamzIncrements) (obj7)).streamzLogger;
                                obj7 = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj7)).metricFactory);
                                boolean flag;
                                if (((StreamzMessageProducer) (obj7)).incrementRequest.batch_.size() == 0)
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (!flag)
                                {
                                    gcoreclearcutstreamzlogger.gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj7))).setLogSourceName(gcoreclearcutstreamzlogger.logSourceName).logAsync();
                                }
                                StreamzIncrements.incrementCounts = 0;
                            }
                        }

                            .Lambda._cls8()
                            {
                                arg$1 = GrowthKitEventManagerImpl.this;
                            }
                    }

                    obj6 = ((.Lambda._cls8) (obj4)). new .Lambda._cls8();
                    obj4 = ((GrowthKitEventManagerImpl) (obj4)).executor;
                    obj5 = new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.MoreFutures._cls1(((Receiver) (obj5)), ((Receiver) (obj6)));
                    if (obj5 == null)
                    {
                        throw new NullPointerException();
                    } else
                    {
                        ((ListenableFuture) (obj3)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((Future) (obj3)), ((com.google.common.util.concurrent.FutureCallback) (obj5))), ((java.util.concurrent.Executor) (obj4)));
                        return ((ListenableFuture) (obj3));
                    }
                }

            .Lambda._cls0(com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent, String s)
            {
                arg$1 = GrowthKitEventManagerImpl.this;
                arg$2 = clearcutevent;
                arg$3 = s;
            }
            }

            class .Lambda._cls1
                implements AsyncFunction
            {

                private final GrowthKitEventManagerImpl arg$1;
                private final ListenableFuture arg$2;
                private final com.google.identity.growth.proto.Promotion.ClearcutEvent arg$3;
                private final String arg$4;

                public final ListenableFuture apply(Object obj3)
                {
                    obj3 = arg$1;
                    ListenableFuture listenablefuture = arg$2;
                    com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent = arg$3;
                    String s1 = arg$4;
                    if (!listenablefuture.isDone())
                    {
                        throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                            listenablefuture
                        }));
                    }
                    if (!((Boolean)Uninterruptibles.getUninterruptibly(listenablefuture)).booleanValue())
                    {
                        if (true)
                        {
                            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                        } else
                        {
                            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
                        }
                    }
                    ((PromotionsManager)((GrowthKitEventManagerImpl) (obj3)).promotionsManagerLazy.get()).processClearcutEventAsync(clearcutevent, s1);
                    if (true)
                    {
                        return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                    } else
                    {
                        return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
                    }
                }

            .Lambda._cls1(ListenableFuture listenablefuture, com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent, String s)
            {
                arg$1 = GrowthKitEventManagerImpl.this;
                arg$2 = listenablefuture;
                arg$3 = clearcutevent;
                arg$4 = s;
            }
            }

            return AbstractTransformFuture.create(AbstractTransformFuture.create(((ListenableFuture) (obj1)), new .Lambda._cls0(((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj)), s), executor), trace.propagateAsyncFunction(new .Lambda._cls1(((ListenableFuture) (obj1)), ((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj)), s)), executor);
        }
    }

}
