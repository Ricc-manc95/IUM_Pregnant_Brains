// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Trace;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.SerializingExecutor;
import com.google.android.libraries.internal.growth.growthkit.internal.promotions.PromotionsManager;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.protobuf.GeneratedMessageLite;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            TriggeringEventProcessor

public final class PromotionsManagerImpl
    implements PromotionsManager
{

    public static final Logger logger = new Logger();
    public final Clock clock;
    public final TriggeringEventProcessor eventProcessor;
    private final Executor executor;
    private final Trace trace;

    public PromotionsManagerImpl(ListeningExecutorService listeningexecutorservice, TriggeringEventProcessor triggeringeventprocessor, Clock clock1, Trace trace1)
    {
        executor = new SerializingExecutor(listeningexecutorservice);
        eventProcessor = triggeringeventprocessor;
        clock = clock1;
        trace = trace1;
    }

    public final void processClearcutEventAsync(com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent, String s)
    {
        com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent.Builder builder = (com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        builder.copyOnWrite();
        com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent triggeringevent = (com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent)builder.instance;
        if (clearcutevent == null)
        {
            throw new NullPointerException();
        } else
        {
            triggeringevent.event_ = clearcutevent;
            triggeringevent.eventCase_ = 1;
            clearcutevent = (com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent)(GeneratedMessageLite)builder.build();
            class .Lambda._cls1
                implements Runnable
            {

                private final PromotionsManagerImpl arg$1;
                private final com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent arg$2;
                private final String arg$3;

                public final void run()
                {
                    Object obj1;
                    Object obj2;
                    Object obj3;
                    obj1 = arg$1;
                    obj2 = arg$2;
                    obj3 = arg$3;
                    Object obj;
                    Object obj4;
                    Object obj5;
                    ListenableFuture listenablefuture;
                    Object obj6;
                    obj = ((PromotionsManagerImpl) (obj1)).eventProcessor;
                    obj1 = new AutoValue_TriggeringEventProcessor_ProcessingContext(((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent) (obj2)), ((String) (obj3)), ((PromotionsManagerImpl) (obj1)).clock.currentTimeMillis());
                    obj4 = ((MessageStore)((TriggeringEventProcessor) (obj)).promotionsStore.forAccount(((TriggeringEventProcessor.ProcessingContext) (obj1)).accountName())).getAll();
                    obj2 = AbstractTransformFuture.create(((ListenableFuture) (obj4)), TriggeringEventProcessor..Lambda._cls13.$instance, ((TriggeringEventProcessor) (obj)).executor);
                    obj3 = AbstractTransformFuture.create(((TriggeringEventProcessor) (obj)).cappedPromotionsStore.getAll(), new TriggeringEventProcessor..Lambda._cls14(((TriggeringEventProcessor) (obj))), ((TriggeringEventProcessor) (obj)).executor);
                    listenablefuture = AbstractTransformFuture.create(((ListenableFuture) (obj4)), TriggeringEventProcessor..Lambda._cls8.$instance, ((TriggeringEventProcessor) (obj)).executor);
                    obj4 = AbstractTransformFuture.create(listenablefuture, new TriggeringEventProcessor..Lambda._cls9(((TriggeringEventProcessor) (obj)), ((TriggeringEventProcessor.ProcessingContext) (obj1))), ((TriggeringEventProcessor) (obj)).executor);
                    obj5 = AbstractTransformFuture.create(listenablefuture, new TriggeringEventProcessor..Lambda._cls10(((TriggeringEventProcessor) (obj)), ((TriggeringEventProcessor.ProcessingContext) (obj1))), ((TriggeringEventProcessor) (obj)).executor);
                    listenablefuture = AbstractTransformFuture.create(listenablefuture, ((TriggeringEventProcessor) (obj)).trace.propagateAsyncFunction(new TriggeringEventProcessor..Lambda._cls11(((TriggeringEventProcessor) (obj)), ((TriggeringEventProcessor.ProcessingContext) (obj1)))), ((TriggeringEventProcessor) (obj)).executor);
                    obj6 = new HashSet();
                    String s1;
                    for (Iterator iterator = ((TriggeringEventProcessor) (obj)).accountManager.getAccountsNames().iterator(); iterator.hasNext(); ((Set) (obj6)).add(((MessageStore)((TriggeringEventProcessor) (obj)).presentedPromosStore.forAccount(s1)).getAll()))
                    {
                        s1 = (String)iterator.next();
                    }

                      goto _L1
_L3:
                    PromotionsManagerImpl.logger.w(((Throwable) (obj)), "Failed to process event", new Object[0]);
                    return;
_L1:
                    try
                    {
                        ((Set) (obj6)).add(((MessageStore)((TriggeringEventProcessor) (obj)).presentedPromosStore.forAccount(null)).getAll());
                        Object obj7 = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(((Iterable) (obj6))));
                        obj6 = new TriggeringEventProcessor..Lambda._cls15(((Set) (obj6)));
                        ListeningExecutorService listeningexecutorservice = ((TriggeringEventProcessor) (obj)).executor;
                        obj7 = new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (obj7)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (obj7)).allMustSucceed, listeningexecutorservice, ((java.util.concurrent.Callable) (obj6)));
                        obj6 = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(new ListenableFuture[] {
                            obj2, obj4, obj5, listenablefuture, obj7
                        }));
                        obj2 = new TriggeringEventProcessor..Lambda._cls7(((TriggeringEventProcessor) (obj)), ((ListenableFuture) (obj2)), ((ListenableFuture) (obj3)), ((ListenableFuture) (obj4)), ((ListenableFuture) (obj5)), listenablefuture, ((ListenableFuture) (obj7)), ((TriggeringEventProcessor.ProcessingContext) (obj1)));
                        obj3 = ((TriggeringEventProcessor) (obj)).executor;
                        obj2 = new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (obj6)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (obj6)).allMustSucceed, ((Executor) (obj3)), ((java.util.concurrent.Callable) (obj2)));
                        obj3 = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(new ListenableFuture[] {
                            obj2
                        }));
                        obj4 = new TriggeringEventProcessor..Lambda._cls6(((TriggeringEventProcessor) (obj)), ((ListenableFuture) (obj2)), ((TriggeringEventProcessor.ProcessingContext) (obj1)));
                        obj5 = ((TriggeringEventProcessor) (obj)).executor;
                        obj3 = new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (obj3)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (obj3)).allMustSucceed, ((Executor) (obj5)), ((com.google.common.util.concurrent.AsyncCallable) (obj4)));
                        obj4 = AbstractTransformFuture.create(((ListenableFuture) (obj2)), ((TriggeringEventProcessor) (obj)).trace.propagateAsyncFunction(new TriggeringEventProcessor..Lambda._cls4(((TriggeringEventProcessor) (obj)), ((TriggeringEventProcessor.ProcessingContext) (obj1)))), ((TriggeringEventProcessor) (obj)).executor);
                        obj3 = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(new ListenableFuture[] {
                            obj2, obj3, obj4
                        }));
                        obj4 = new TriggeringEventProcessor..Lambda._cls5(((TriggeringEventProcessor) (obj)), ((ListenableFuture) (obj2)), ((TriggeringEventProcessor.ProcessingContext) (obj1)), ((ListenableFuture) (obj4)));
                        obj5 = ((TriggeringEventProcessor) (obj)).executor;
                        obj3 = new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (obj3)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (obj3)).allMustSucceed, ((Executor) (obj5)), ((com.google.common.util.concurrent.AsyncCallable) (obj4)));
                        obj4 = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(new ListenableFuture[] {
                            obj2, obj3
                        }));
                        obj1 = new TriggeringEventProcessor..Lambda._cls1(((TriggeringEventProcessor) (obj)), ((ListenableFuture) (obj3)), ((ListenableFuture) (obj2)), ((TriggeringEventProcessor.ProcessingContext) (obj1)));
                        obj2 = ((TriggeringEventProcessor) (obj)).executor;
                        obj2 = new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (obj4)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (obj4)).allMustSucceed, ((Executor) (obj2)), ((com.google.common.util.concurrent.AsyncCallable) (obj1)));
                        obj1 = new com.google.common.util.concurrent.Futures.FutureCombiner(false, ImmutableList.copyOf(new ListenableFuture[] {
                            obj2, AbstractTransformFuture.create(((ListenableFuture) (obj3)), new TriggeringEventProcessor..Lambda._cls2(((TriggeringEventProcessor) (obj))), ((TriggeringEventProcessor) (obj)).executor)
                        }));
                        obj2 = new TriggeringEventProcessor..Lambda._cls0(((ListenableFuture) (obj2)));
                        obj = ((TriggeringEventProcessor) (obj)).executor;
                        ((Boolean)(new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (obj1)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (obj1)).allMustSucceed, ((Executor) (obj)), ((java.util.concurrent.Callable) (obj2)))).get()).booleanValue();
                        obj = PromotionsManagerImpl.logger;
                        return;
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj) { }
                    // Misplaced declaration of an exception variable
                    catch (Object obj) { }
                    if (true) goto _L3; else goto _L2
_L2:
                }

            .Lambda._cls1(com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent triggeringevent, String s)
            {
                arg$1 = PromotionsManagerImpl.this;
                arg$2 = triggeringevent;
                arg$3 = s;
            }
            }

            executor.execute(trace.propagateRunnable(new .Lambda._cls1(clearcutevent, s)));
            return;
        }
    }

}
