// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import com.google.android.libraries.gcoreclient.security.GcoreProviderInstaller;
import com.google.android.libraries.internal.growth.growthkit.inject.BroadcastReceiverInjector;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKit;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.android.libraries.internal.growth.growthkit.internal.accounts.AccountManager;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Trace;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.MoreFutures;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.ClearcutEventsStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.VisualElementEventsStore;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.PromotionSync;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.ImageDownloadManager;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.CombinedFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.protobuf.GeneratedMessageLite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

public class TestingToolsBroadcastReceiver extends BroadcastReceiver
{
    public static interface TestingToolsBroadcastReceiverSubcomponent
        extends BroadcastReceiverInjector
    {
    }


    public AccountManager accountManager;
    public ListeningExecutorService blockingExecutor;
    public MessageStore cappedPromotionStore;
    public PerAccountProvider clearcutEventsStore;
    public Context context;
    public ListeningExecutorService executorService;
    public GcoreProviderInstaller gcoreProviderInstaller;
    public final Logger logger = new Logger();
    public PerAccountProvider presentedPromosStore;
    public PromotionSync promotionSync;
    public PerAccountProvider promotionsStore;
    public ListenableFuture sharedPrefsFuture;
    public Provider syncGaiaAccounts;
    public Provider syncZwiebackAccounts;
    public Provider testingEnabled;
    public Trace trace;
    public Map uiImageDownloadManager;
    public PerAccountProvider visualElementStore;

    public TestingToolsBroadcastReceiver()
    {
    }

    final Boolean lambda$sync$1$TestingToolsBroadcastReceiver$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T16URRCCLGMSEO_0()
    {
        try
        {
            promotionSync.syncAllAccounts().get();
        }
        catch (Exception exception)
        {
            logger.e(exception, "Failed to sync", new Object[0]);
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public void onReceive(Context context1, Intent intent)
    {
        try
        {
            ((BroadcastReceiverInjector)((Provider)GrowthKit.get(context1).internalBroadcastReceiverInjectors().get(com/google/android/libraries/internal/growth/growthkit/internal/storage/impl/TestingToolsBroadcastReceiver)).get()).inject(this);
        }
        // Misplaced declaration of an exception variable
        catch (Context context1)
        {
            logger.w(context1, "Failed to initialize TestingToolsBroadcastReceiver", new Object[0]);
            return;
        }
        trace.begin();
        if (((Boolean)testingEnabled.get()).booleanValue())
        {
            break MISSING_BLOCK_LABEL_105;
        }
        logger.w("Testing Feature is not enabled. Did you forget to override the phenotype flag?", new Object[0]);
        setResultCode(-2);
        trace.end();
        return;
        context1 = intent.getAction();
        byte byte0 = -1;
        int i = context1.hashCode();
        i;
        JVM INSTR lookupswitch 4: default 164
    //                   -984653766: 251
    //                   729328716: 236
    //                   1466296994: 221
    //                   1943132320: 206;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        byte0;
        JVM INSTR tableswitch 0 3: default 196
    //                   0 270
    //                   1 767
    //                   2 941
    //                   3 1029;
           goto _L6 _L7 _L8 _L9 _L10
_L6:
        trace.end();
        return;
_L5:
        if (!context1.equals("com.google.android.libraries.internal.growth.growthkit.ADD_PROMO")) goto _L1; else goto _L11
_L11:
        byte0 = 0;
          goto _L1
_L4:
        if (!context1.equals("com.google.android.libraries.internal.growth.growthkit.LOG_DEBUG_DATA")) goto _L1; else goto _L12
_L12:
        byte0 = 1;
          goto _L1
_L3:
        if (!context1.equals("com.google.android.libraries.internal.growth.growthkit.SYNC")) goto _L1; else goto _L13
_L13:
        byte0 = 2;
          goto _L1
_L2:
        boolean flag = context1.equals("com.google.android.libraries.internal.growth.growthkit.CLEAR_COUNTERS");
        if (flag)
        {
            byte0 = 3;
        }
          goto _L1
_L7:
        Object obj = intent.getStringExtra("account");
        if (intent.getStringExtra("proto") == null) goto _L6; else goto _L14
_L14:
        com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion;
        context1 = Base64.decode(intent.getStringExtra("proto"), 0);
        promotion = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion)GeneratedMessageLite.parseFrom(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion.DEFAULT_INSTANCE, context1);
        context1 = logger;
        context1 = ((Context) (new Object[0]));
        if (context1 == null) goto _L16; else goto _L15
_L15:
        if (context1.length > 0)
        {
            String.format("Saving custom promotion received from broadcast.", context1);
        }
_L16:
        ArrayList arraylist;
        arraylist = new ArrayList();
        if (((Boolean)syncGaiaAccounts.get()).booleanValue())
        {
            for (context1 = accountManager.getAccountsNames().iterator(); context1.hasNext(); arraylist.add(((MessageStore)presentedPromosStore.forAccount(intent)).clearAndPutAll(Collections.emptyMap())))
            {
                intent = (String)context1.next();
                arraylist.add(((MessageStore)promotionsStore.forAccount(intent)).clearAndPutAll(Collections.emptyMap()));
            }

        }
          goto _L17
        context1;
        logger.e(context1, "Failed to parse custom promotion received in BroadcastReceiver", new Object[0]);
          goto _L6
        context1;
        trace.end();
        throw context1;
_L17:
        Map map;
        if (((Boolean)syncZwiebackAccounts.get()).booleanValue())
        {
            arraylist.add(((MessageStore)promotionsStore.forAccount(null)).clearAndPutAll(Collections.emptyMap()));
            arraylist.add(((MessageStore)presentedPromosStore.forAccount(null)).clearAndPutAll(Collections.emptyMap()));
        }
        class .Lambda._cls4
            implements Function
        {

            private final TestingToolsBroadcastReceiver arg$1;

            public final Object apply(Object obj2)
            {
                Object obj3 = arg$1;
                obj2 = (SharedPreferences)obj2;
                obj3 = ((TestingToolsBroadcastReceiver) (obj3)).context.getResources().getConfiguration().locale;
                ((SharedPreferences) (obj2)).edit().putString("SYNC_LANGUAGE", ((Locale) (obj3)).toLanguageTag()).apply();
                return null;
            }

            .Lambda._cls4()
            {
                arg$1 = TestingToolsBroadcastReceiver.this;
            }
        }

        arraylist.add(AbstractTransformFuture.create(sharedPrefsFuture, new .Lambda._cls4(), executorService));
        map = uiImageDownloadManager;
        if (promotion.ui_ != null) goto _L19; else goto _L18
_L18:
        context1 = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
_L26:
        intent = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(((com.google.identity.growth.proto.Promotion.PromoUi) (context1)).uiType_);
        context1 = intent;
        if (intent != null) goto _L21; else goto _L20
_L20:
        context1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
_L21:
        context1 = (Provider)map.get(context1);
        if (context1 == null) goto _L23; else goto _L22
_L22:
        intent = (ImageDownloadManager)context1.get();
        if (promotion.ui_ != null) goto _L25; else goto _L24
_L24:
        context1 = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
_L27:
        context1 = intent.downloadImageIfNeeded(context1);
        if (context1.isPresent())
        {
            arraylist.add((ListenableFuture)context1.get());
        }
_L23:
        context1 = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(arraylist));
        class .Lambda._cls5
            implements AsyncCallable
        {

            private final TestingToolsBroadcastReceiver arg$1;
            private final String arg$2;
            private final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion arg$3;

            public final ListenableFuture call()
            {
                Object obj2 = arg$1;
                Object obj3 = arg$2;
                com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion1 = arg$3;
                obj3 = (MessageStore)((TestingToolsBroadcastReceiver) (obj2)).promotionsStore.forAccount(((String) (obj3)));
                if (promotion1.promoId_ == null)
                {
                    obj2 = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification.DEFAULT_INSTANCE;
                } else
                {
                    obj2 = promotion1.promoId_;
                }
                return ((MessageStore) (obj3)).put(PromotionKeysHelper.of(((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj2))), promotion1);
            }

            .Lambda._cls5(String s, com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion)
            {
                arg$1 = TestingToolsBroadcastReceiver.this;
                arg$2 = s;
                arg$3 = promotion;
            }
        }

        intent = new .Lambda._cls5(((String) (obj)), promotion);
        obj = executorService;
        class .Lambda._cls6
            implements Receiver
        {

            private final TestingToolsBroadcastReceiver arg$1;

            public final void accept(Object obj2)
            {
                TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver = arg$1;
                obj2 = (Throwable)obj2;
                testingtoolsbroadcastreceiver.logger.e(((Throwable) (obj2)), "Failed to save custom promotion received from broadcast.", new Object[0]);
            }

            .Lambda._cls6()
            {
                arg$1 = TestingToolsBroadcastReceiver.this;
            }
        }

        MoreFutures.addCallback(new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (context1)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (context1)).allMustSucceed, ((java.util.concurrent.Executor) (obj)), intent), null, new .Lambda._cls6());
          goto _L6
_L19:
        context1 = promotion.ui_;
          goto _L26
_L25:
        context1 = promotion.ui_;
          goto _L27
_L8:
        context1 = intent.getStringExtra("account");
        intent = ((MessageStore)promotionsStore.forAccount(context1)).getAll();
        Object obj1 = cappedPromotionStore.getAll();
        ListenableFuture listenablefuture = ((ClearcutEventsStore)clearcutEventsStore.forAccount(context1)).getAllEventsCounts();
        ListenableFuture listenablefuture1 = ((VisualElementEventsStore)visualElementStore.forAccount(context1)).getAllEventsCounts();
        context1 = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(new ListenableFuture[] {
            intent, obj1, listenablefuture, listenablefuture1
        }));
        class .Lambda._cls7
            implements Callable
        {

            private final TestingToolsBroadcastReceiver arg$1;
            private final ListenableFuture arg$2;
            private final ListenableFuture arg$3;
            private final ListenableFuture arg$4;
            private final ListenableFuture arg$5;

            public final Object call()
            {
                TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver = arg$1;
                Object obj5 = arg$2;
                ListenableFuture listenablefuture2 = arg$3;
                Object obj6 = arg$4;
                ListenableFuture listenablefuture3 = arg$5;
                obj5 = ((Map)((ListenableFuture) (obj5)).get()).entrySet().iterator();
                do
                {
                    if (!((Iterator) (obj5)).hasNext())
                    {
                        break;
                    }
                    java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj5)).next();
                    com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent = (com.google.identity.growth.proto.Promotion.ClearcutEvent)entry.getKey();
                    Logger logger1 = testingtoolsbroadcastreceiver.logger;
                    Object aobj2[] = new Object[4];
                    aobj2[0] = clearcutevent.bundleIdentifier_;
                    aobj2[1] = Integer.valueOf(clearcutevent.logSource_);
                    aobj2[2] = Integer.valueOf(clearcutevent.eventCode_);
                    aobj2[3] = entry.getValue();
                    if (aobj2 != null && aobj2.length > 0)
                    {
                        String.format("ClearcutEvent[package: %s, log_source: %s, event_code:%s] Count: %d", aobj2);
                    }
                } while (true);
                Iterator iterator = ((Map)listenablefuture2.get()).entrySet().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator.next();
                    com.google.identity.growth.proto.Promotion.VisualElementEvent visualelementevent = (com.google.identity.growth.proto.Promotion.VisualElementEvent)entry1.getKey();
                    Object obj2 = testingtoolsbroadcastreceiver.logger;
                    Object aobj3[] = new Object[3];
                    obj5 = com.google.identity.growth.proto.Promotion.VisualElementEvent.Action.forNumber(visualelementevent.action_);
                    obj2 = obj5;
                    if (obj5 == null)
                    {
                        obj2 = com.google.identity.growth.proto.Promotion.VisualElementEvent.Action.UNKNOWN;
                    }
                    aobj3[0] = obj2;
                    aobj3[1] = TextUtils.join(", ", visualelementevent.nodeIdPath_);
                    aobj3[2] = entry1.getValue();
                    if (aobj3 != null && aobj3.length > 0)
                    {
                        String.format("VisualElementEvent[action: %s, path: %s] Count: %d", aobj3);
                    }
                } while (true);
                obj6 = ((Map)((ListenableFuture) (obj6)).get()).values().iterator();
                do
                {
                    if (!((Iterator) (obj6)).hasNext())
                    {
                        break;
                    }
                    com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion1 = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion)((Iterator) (obj6)).next();
                    Object obj3 = testingtoolsbroadcastreceiver.logger;
                    Object aobj1[] = new Object[4];
                    if (promotion1.promoId_ == null)
                    {
                        obj3 = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification.DEFAULT_INSTANCE;
                    } else
                    {
                        obj3 = promotion1.promoId_;
                    }
                    aobj1[0] = Integer.valueOf(((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj3)).impressionCappingId_);
                    if (promotion1.promoId_ == null)
                    {
                        obj3 = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification.DEFAULT_INSTANCE;
                    } else
                    {
                        obj3 = promotion1.promoId_;
                    }
                    aobj1[1] = Integer.valueOf(((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj3)).mendelId_.getInt(0));
                    if (promotion1.ui_ == null)
                    {
                        obj3 = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
                    } else
                    {
                        obj3 = promotion1.ui_;
                    }
                    obj5 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(((com.google.identity.growth.proto.Promotion.PromoUi) (obj3)).uiType_);
                    obj3 = obj5;
                    if (obj5 == null)
                    {
                        obj3 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
                    }
                    aobj1[2] = ((com.google.identity.growth.proto.Promotion.PromoUi.UiType) (obj3)).toString();
                    if (promotion1.ui_ == null)
                    {
                        obj3 = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
                    } else
                    {
                        obj3 = promotion1.ui_;
                    }
                    aobj1[3] = DebugUtil.getPromoTitle(((com.google.identity.growth.proto.Promotion.PromoUi) (obj3)));
                    if (aobj1 != null && aobj1.length > 0)
                    {
                        String.format("Promotion[impressionCappingId: %d, mendelId: %d] uiType: %s%s", aobj1);
                    }
                } while (true);
                obj5 = ((Map)listenablefuture3.get()).values().iterator();
                do
                {
                    if (!((Iterator) (obj5)).hasNext())
                    {
                        break;
                    }
                    com.google.identity.boq.growth.promoprovider.proto.PromoProvider.CappedPromotion cappedpromotion = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.CappedPromotion)((Iterator) (obj5)).next();
                    Object obj7 = TimeUnit.SECONDS;
                    Object obj4;
                    Object aobj[];
                    long l;
                    if (cappedpromotion.cappedUntil_ == null)
                    {
                        obj4 = Timestamp.DEFAULT_INSTANCE;
                    } else
                    {
                        obj4 = cappedpromotion.cappedUntil_;
                    }
                    l = ((TimeUnit) (obj7)).toMillis(((Timestamp) (obj4)).seconds_);
                    obj7 = TimeUnit.NANOSECONDS;
                    if (cappedpromotion.cappedUntil_ == null)
                    {
                        obj4 = Timestamp.DEFAULT_INSTANCE;
                    } else
                    {
                        obj4 = cappedpromotion.cappedUntil_;
                    }
                    obj4 = new Date(l + ((TimeUnit) (obj7)).toMillis(((Timestamp) (obj4)).nanos_));
                    obj7 = testingtoolsbroadcastreceiver.logger;
                    aobj = new Object[2];
                    aobj[0] = Integer.valueOf(cappedpromotion.impressionCappingId_);
                    aobj[1] = SimpleDateFormat.getDateTimeInstance().format(((Date) (obj4)));
                    if (aobj != null && aobj.length > 0)
                    {
                        String.format("CappedPromotion[impressionCappingId: %d] expiration: %s", aobj);
                    }
                } while (true);
                return null;
            }

            .Lambda._cls7(ListenableFuture listenablefuture, ListenableFuture listenablefuture1, ListenableFuture listenablefuture2, ListenableFuture listenablefuture3)
            {
                arg$1 = TestingToolsBroadcastReceiver.this;
                arg$2 = listenablefuture;
                arg$3 = listenablefuture1;
                arg$4 = listenablefuture2;
                arg$5 = listenablefuture3;
            }
        }

        intent = new .Lambda._cls7(listenablefuture, listenablefuture1, intent, ((ListenableFuture) (obj1)));
        obj1 = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        class .Lambda._cls8
            implements Receiver
        {

            private final TestingToolsBroadcastReceiver arg$1;

            public final void accept(Object obj2)
            {
                TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver = arg$1;
                obj2 = (Throwable)obj2;
                testingtoolsbroadcastreceiver.logger.e(((Throwable) (obj2)), "Failed to get event counts in BroadcastReceiver", new Object[0]);
            }

            .Lambda._cls8()
            {
                arg$1 = TestingToolsBroadcastReceiver.this;
            }
        }

        MoreFutures.addCallback(new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (context1)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (context1)).allMustSucceed, ((java.util.concurrent.Executor) (obj1)), intent), null, new .Lambda._cls8());
          goto _L6
        context1;
        logger.e(context1, "Failed to dump event counts in BroadcastReceiver", new Object[0]);
          goto _L6
_L9:
        context1 = logger;
        context1 = ((Context) (new Object[0]));
        if (context1 == null) goto _L29; else goto _L28
_L28:
        if (context1.length > 0)
        {
            String.format("Syncing all accounts with the server.", context1);
        }
_L29:
        class .Lambda._cls0
            implements Runnable
        {

            private final TestingToolsBroadcastReceiver arg$1;

            public final void run()
            {
                TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver = arg$1;
                testingtoolsbroadcastreceiver.gcoreProviderInstaller.installIfNeeded(testingtoolsbroadcastreceiver.context);
                return;
                Object obj2;
                obj2;
_L2:
                testingtoolsbroadcastreceiver.logger.e(((Throwable) (obj2)), "Failed to install security provider, GrowthKit sync can't run.", new Object[0]);
                return;
                obj2;
                if (true) goto _L2; else goto _L1
_L1:
            }

            .Lambda._cls0()
            {
                arg$1 = TestingToolsBroadcastReceiver.this;
            }
        }

        class .Lambda._cls1
            implements Function
        {

            private final TestingToolsBroadcastReceiver arg$1;

            public final Object apply(Object obj2)
            {
                return arg$1.lambda$sync$1$TestingToolsBroadcastReceiver$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T16URRCCLGMSEO_0();
            }

            .Lambda._cls1()
            {
                arg$1 = TestingToolsBroadcastReceiver.this;
            }
        }

        context1 = AbstractTransformFuture.create(blockingExecutor.submit(new .Lambda._cls0()), new .Lambda._cls1(), executorService);
        intent = goAsync();
        class .Lambda._cls2
            implements Receiver
        {

            private final android.content.BroadcastReceiver.PendingResult arg$1;

            public final void accept(Object obj2)
            {
                android.content.BroadcastReceiver.PendingResult pendingresult = arg$1;
                int j;
                if (((Boolean)obj2).booleanValue())
                {
                    j = 0;
                } else
                {
                    j = -1;
                }
                pendingresult.setResultCode(j);
                pendingresult.finish();
            }

            .Lambda._cls2(android.content.BroadcastReceiver.PendingResult pendingresult)
            {
                arg$1 = pendingresult;
            }
        }

        class .Lambda._cls3
            implements Receiver
        {

            private final android.content.BroadcastReceiver.PendingResult arg$1;

            public final void accept(Object obj2)
            {
                obj2 = arg$1;
                ((android.content.BroadcastReceiver.PendingResult) (obj2)).setResultCode(-1);
                ((android.content.BroadcastReceiver.PendingResult) (obj2)).finish();
            }

            .Lambda._cls3(android.content.BroadcastReceiver.PendingResult pendingresult)
            {
                arg$1 = pendingresult;
            }
        }

        MoreFutures.addCallback(context1, new .Lambda._cls2(intent), new .Lambda._cls3(intent));
          goto _L6
_L10:
        context1 = new ArrayList();
        if (((Boolean)syncGaiaAccounts.get()).booleanValue())
        {
            String s;
            for (intent = accountManager.getAccountsNames().iterator(); intent.hasNext(); context1.add(((VisualElementEventsStore)visualElementStore.forAccount(s)).clearAll()))
            {
                s = (String)intent.next();
                context1.add(((ClearcutEventsStore)clearcutEventsStore.forAccount(s)).clearAll());
            }

        }
          goto _L30
        context1;
        logger.e(context1, "Failed to clear event counts in BroadcastReceiver", new Object[0]);
          goto _L6
_L30:
        context1.add(((ClearcutEventsStore)clearcutEventsStore.forAccount(null)).clearAll());
        context1.add(((VisualElementEventsStore)visualElementStore.forAccount(null)).clearAll());
        context1 = new com.google.common.util.concurrent.Futures.FutureCombiner(false, ImmutableList.copyOf(context1));
        class .Lambda._cls9
            implements Callable
        {

            private final TestingToolsBroadcastReceiver arg$1;

            public final Object call()
            {
                Logger logger1 = arg$1.logger;
                Object aobj[] = new Object[0];
                if (aobj != null && aobj.length > 0)
                {
                    String.format("Cleared all counters", aobj);
                }
                return null;
            }

            .Lambda._cls9()
            {
                arg$1 = TestingToolsBroadcastReceiver.this;
            }
        }

        intent = new .Lambda._cls9();
        com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        class .Lambda._cls10
            implements Receiver
        {

            private final TestingToolsBroadcastReceiver arg$1;

            public final void accept(Object obj2)
            {
                TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver = arg$1;
                obj2 = (Throwable)obj2;
                testingtoolsbroadcastreceiver.logger.e(((Throwable) (obj2)), "Failed to clear event counts in BroadcastReceiver", new Object[0]);
            }

            .Lambda._cls10()
            {
                arg$1 = TestingToolsBroadcastReceiver.this;
            }
        }

        MoreFutures.addCallback(new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (context1)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (context1)).allMustSucceed, directexecutor, intent), null, new .Lambda._cls10());
          goto _L6
    }
}
