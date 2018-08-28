// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import android.content.Context;
import android.content.Intent;
import android.view.accessibility.AccessibilityManager;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.accounts.AccountManager;
import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.ClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Trace;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringConditionsPredicate;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.PromotionSync;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.PromoUiRenderer;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UserActionUtil;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks;
import com.google.android.libraries.streamz.CellFieldTuple;
import com.google.android.libraries.streamz.Counter1;
import com.google.android.libraries.streamz.Counter2;
import com.google.android.libraries.streamz.GcoreClearcutStreamzLogger;
import com.google.android.libraries.streamz.GenericMetric;
import com.google.android.libraries.streamz.StreamzMessageProducer;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.protobuf.GeneratedMessageLite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import javax.inject.Provider;

public final class TriggeringEventProcessor
{

    public static final Logger logger = new Logger();
    public final AccountManager accountManager;
    public final Provider callbackManager;
    public final MessageStore cappedPromotionsStore;
    public final PerAccountProvider clearcutCountersStore;
    public final ClearcutLogger clearcutLogger;
    public final Clock clock;
    private final Context context;
    private final Provider enablePromotionsWithAccessibilityProvider;
    public final ListeningExecutorService executor;
    public final String packageName;
    public final PerAccountProvider presentedPromosStore;
    public final PromoUiRenderer promoUiRenderer;
    private final Counter2 promotionShownCounter;
    public final PromotionSync promotionSync;
    public final PerAccountProvider promotionsStore;
    public final StreamzIncrements streamzIncrements;
    public final Provider syncAfterPromoShownProvider;
    public final Counter1 targetingAppliedCounter;
    public final TargetingRulePredicate targetingRulePredicate;
    public final Trace trace;
    public final Counter1 triggerAppliedCounter;
    public final TriggeringConditionsPredicate triggeringConditionsPredicate;
    public final Set triggeringRulePredicates;
    public final UserActionUtil userActionUtil;
    public final PerAccountProvider veCountersStore;

    public TriggeringEventProcessor(Context context1, Clock clock1, PerAccountProvider peraccountprovider, MessageStore messagestore, PerAccountProvider peraccountprovider1, PerAccountProvider peraccountprovider2, PerAccountProvider peraccountprovider3, 
            TriggeringConditionsPredicate triggeringconditionspredicate, Set set, TargetingRulePredicate targetingrulepredicate, AccountManager accountmanager, PromoUiRenderer promouirenderer, PromotionSync promotionsync, Provider provider, 
            ClearcutLogger clearcutlogger, ListeningExecutorService listeningexecutorservice, Provider provider1, Provider provider2, Counter1 counter1, Counter1 counter1_1, Counter2 counter2, 
            StreamzIncrements streamzincrements, String s, UserActionUtil useractionutil, Trace trace1)
    {
        context = context1;
        clock = clock1;
        promotionsStore = peraccountprovider;
        cappedPromotionsStore = messagestore;
        clearcutCountersStore = peraccountprovider1;
        veCountersStore = peraccountprovider2;
        presentedPromosStore = peraccountprovider3;
        triggeringConditionsPredicate = triggeringconditionspredicate;
        triggeringRulePredicates = set;
        targetingRulePredicate = targetingrulepredicate;
        accountManager = accountmanager;
        promoUiRenderer = promouirenderer;
        promotionSync = promotionsync;
        callbackManager = provider;
        clearcutLogger = clearcutlogger;
        executor = listeningexecutorservice;
        enablePromotionsWithAccessibilityProvider = provider1;
        syncAfterPromoShownProvider = provider2;
        triggerAppliedCounter = counter1;
        targetingAppliedCounter = counter1_1;
        promotionShownCounter = counter2;
        streamzIncrements = streamzincrements;
        packageName = s;
        userActionUtil = useractionutil;
        trace = trace1;
    }

    static final Boolean lambda$cleanupPersistedPromoIfNotRendered$1$TriggeringEventProcessor$5166KOBMC4NMOOBECSNLCRR9CGTIIJ3AC5R62BRCC5N6EBQ2DTNMOPB1DOTG____0()
    {
        return Boolean.valueOf(false);
    }

    static final Map lambda$getActionIntentMapFuture$4$TriggeringEventProcessor(Map map)
        throws Exception
    {
        HashMap hashmap;
        Iterator iterator;
        hashmap = new HashMap(map.size());
        iterator = map.entrySet().iterator();
_L2:
        java.util.Map.Entry entry;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        entry = (java.util.Map.Entry)iterator.next();
        Intent intent = (Intent)((ListenableFuture)entry.getValue()).get();
        if (intent == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType actiontype = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.forNumber(((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action)entry.getKey()).actionType_);
        map = actiontype;
        if (actiontype != null)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        map = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.ACTION_UNKNOWN;
        hashmap.put(map, intent);
        continue; /* Loop/switch isn't completed */
        map;
_L3:
        logger.w(map, "Failed to add intent for action: %s", new Object[] {
            ((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action)entry.getKey()).toString()
        });
        if (true) goto _L2; else goto _L1
_L1:
        return hashmap;
        map;
          goto _L3
    }

    static final TargetingData lambda$getTargetingData$11$TriggeringEventProcessor(Map map)
    {
        class TargetingData.Builder
        {

            abstract com.google.common.collect.ImmutableSet.Builder appStateIdsBuilder();

            abstract TargetingData build();

            abstract com.google.common.collect.ImmutableSet.Builder clearcutEventsBuilder();

            abstract com.google.common.collect.ImmutableSet.Builder veEventsBuilder();

            TargetingData.Builder()
            {
            }
        }

        AutoValue_TriggeringEventProcessor_TargetingData.Builder builder;
        Iterator iterator;
        builder = new AutoValue_TriggeringEventProcessor_TargetingData.Builder();
        if (map == null)
        {
            return builder.build();
        }
        iterator = map.values().iterator();
_L2:
        if (iterator.hasNext())
        {
            map = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion)iterator.next();
            Iterator iterator1;
            if (((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion) (map)).targeting_ == null)
            {
                map = com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.DEFAULT_INSTANCE;
            } else
            {
                map = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion) (map)).targeting_;
            }
            iterator1 = ((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule) (map)).clause_.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    continue;
                }
                Iterator iterator2 = ((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingClause)iterator1.next()).term_.iterator();
label0:
                do
                {
                    if (!iterator2.hasNext())
                    {
                        break;
                    }
                    map = (com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm)iterator2.next();
                    switch (com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm.PredicateCase.forNumber(((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm) (map)).predicateCase_).ordinal())
                    {
                    default:
                        break;

                    case 0: // '\0'
                        Iterator iterator3;
                        if (((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm) (map)).predicateCase_ == 2)
                        {
                            map = (com.google.identity.growth.proto.Promotion.EventCountPredicate)((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm) (map)).predicate_;
                        } else
                        {
                            map = com.google.identity.growth.proto.Promotion.EventCountPredicate.DEFAULT_INSTANCE;
                        }
                        iterator3 = ((com.google.identity.growth.proto.Promotion.EventCountPredicate) (map)).clientEvent_.iterator();
                        do
                        {
                            if (!iterator3.hasNext())
                            {
                                continue label0;
                            }
                            map = (com.google.identity.growth.proto.Promotion.ClientTargetingEvent)iterator3.next();
                            switch (com.google.identity.growth.proto.Promotion.ClientTargetingEvent.EventCase.forNumber(((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (map)).eventCase_).ordinal())
                            {
                            default:
                                map = logger;
                                break;

                            case 0: // '\0'
                                if (((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (map)).eventCase_ == 1)
                                {
                                    map = (com.google.identity.growth.proto.Promotion.ClearcutEvent)((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (map)).event_;
                                } else
                                {
                                    map = com.google.identity.growth.proto.Promotion.ClearcutEvent.DEFAULT_INSTANCE;
                                }
                                map = (com.google.common.collect.ImmutableSet.Builder)builder.clearcutEventsBuilder().add(map);
                                break;

                            case 1: // '\001'
                                if (((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (map)).eventCase_ == 2)
                                {
                                    map = (com.google.identity.growth.proto.Promotion.VisualElementEvent)((com.google.identity.growth.proto.Promotion.ClientTargetingEvent) (map)).event_;
                                } else
                                {
                                    map = com.google.identity.growth.proto.Promotion.VisualElementEvent.DEFAULT_INSTANCE;
                                }
                                map = (com.google.common.collect.ImmutableSet.Builder)builder.veEventsBuilder().add(map);
                                break;
                            }
                        } while (true);

                    case 1: // '\001'
                        if (((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm) (map)).predicateCase_ == 3)
                        {
                            map = (com.google.identity.growth.proto.Promotion.AppStatePredicate)((com.google.identity.growth.proto.Promotion.ClientSideTargetingRule.TargetingTerm) (map)).predicate_;
                        } else
                        {
                            map = com.google.identity.growth.proto.Promotion.AppStatePredicate.DEFAULT_INSTANCE;
                        }
                        map = ((com.google.identity.growth.proto.Promotion.AppStatePredicate) (map)).stateId_;
                        map = (com.google.common.collect.ImmutableSet.Builder)builder.appStateIdsBuilder().add(map);
                        break;
                    }
                } while (true);
            } while (true);
        }
          goto _L1
        if (true) goto _L2; else goto _L1
_L1:
        return builder.build();
    }

    static final Boolean lambda$hasPresentedPromos$19$TriggeringEventProcessor(Set set)
        throws Exception
    {
        for (set = set.iterator(); set.hasNext();)
        {
            if (!((Map)((ListenableFuture)set.next()).get()).isEmpty())
            {
                return Boolean.valueOf(true);
            }
        }

        return Boolean.valueOf(false);
    }

    static final Boolean lambda$processEvent$0$TriggeringEventProcessor(ListenableFuture listenablefuture)
        throws Exception
    {
        return (Boolean)listenablefuture.get();
    }

    static final Boolean lambda$renderPromotion$6$TriggeringEventProcessor$5166KOBMC4NMOOBECSNLCRR9CGTIIJ3AC5R62BRCC5N6EBQ2DTNMOPB1DOTG____0()
    {
        return Boolean.valueOf(true);
    }

    static final Map lambda$resolveAppStateResponses$15$TriggeringEventProcessor(List list)
    {
        HashMap hashmap = new HashMap();
        list = list.iterator();
        do
        {
            if (!list.hasNext())
            {
                break;
            }
            com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.AppStateResponse appstateresponse = (com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.AppStateResponse)list.next();
            if (!appstateresponse.value.getKind().equals(com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.AppStateValue.Kind.INVALID))
            {
                hashmap.put(appstateresponse.id, appstateresponse.value);
            }
        } while (true);
        return hashmap;
    }

    static final int lambda$sortPromos$16$TriggeringEventProcessor(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion, com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion1)
    {
        return promotion.priority_ - promotion1.priority_;
    }

    static final List lambda$sortPromos$17$TriggeringEventProcessor(Map map)
    {
        if (map == null)
        {
            return new ArrayList();
        } else
        {
            map = new ArrayList(map.values());
            class .Lambda._cls16
                implements Comparator
            {

                public static final Comparator $instance = new .Lambda._cls16();

                public final int compare(Object obj, Object obj1)
                {
                    return TriggeringEventProcessor.lambda$sortPromos$16$TriggeringEventProcessor((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion)obj, (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion)obj1);
                }


            private .Lambda._cls16()
            {
            }
            }

            Collections.sort(map, .Lambda._cls16..instance);
            return map;
        }
    }

    final Boolean lambda$renderPromotion$7$TriggeringEventProcessor(PromoContext promocontext, com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion)
        throws Exception
    {
        Object obj1 = (GrowthKitCallbacks)callbackManager.get();
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        promotion = logger;
        clearcutLogger.logPromoNotShownClientBlocked$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5THMURBDDTN2UK3IDTMMUGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMOQB6CLHNIORCCKNKESJFETQ6GIR9EH1M2R3CC9GM6QRJ4H26ARJPA9IM2SRFDOTIILG_0(promocontext, android.support.v4.content.ModernAsyncTask.Status.CLIENT_REJECT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP48HIMSUAICLGN6RRE7C______0);
_L18:
        return Boolean.valueOf(false);
_L2:
        if (((Boolean)enablePromotionsWithAccessibilityProvider.get()).booleanValue()) goto _L4; else goto _L3
_L3:
        if (context == null)
        {
            logger.e("Null context passed to isAccessibilityEnabled", new Object[0]);
            break MISSING_BLOCK_LABEL_549;
        }
          goto _L5
_L7:
        int i;
        if (i == 0) goto _L4; else goto _L6
_L6:
        promotion = logger;
        clearcutLogger.logPromoNotShownClientBlocked$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5THMURBDDTN2UK3IDTMMUGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMOQB6CLHNIORCCKNKESJFETQ6GIR9EH1M2R3CC9GM6QRJ4H26ARJPA9IM2SRFDOTIILG_0(promocontext, android.support.v4.content.ModernAsyncTask.Status.CLIENT_REJECT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP48HIMSUAICLGN6RRE7C______0);
        return Boolean.valueOf(false);
_L5:
        Object obj = (AccessibilityManager)context.getSystemService("accessibility");
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_549;
        }
        if (!((AccessibilityManager) (obj)).isEnabled())
        {
            break MISSING_BLOCK_LABEL_549;
        }
        Object obj2;
        String s;
        if (!((AccessibilityManager) (obj)).getEnabledAccessibilityServiceList(1).isEmpty())
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L7
_L4:
        obj2 = promoUiRenderer;
        if (promotion.ui_ != null) goto _L9; else goto _L8
_L8:
        obj = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
_L14:
        i = ((PromoUiRenderer) (obj2)).convertPromoType$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH4A1P6URBFALKJMAACCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BR9DPQ6ASJEC5M2UPRIDTRN8Q1FCTP6UTRKD1LMIT1FDHKMCPB3F5HMOP9F8TP6UTRKD15MIT23C5M6OOJ1CDLN692GE9NMQRQKF5O6AEO_0(((com.google.identity.growth.proto.Promotion.PromoUi) (obj)));
        obj2 = promoUiRenderer;
        if (promotion.ui_ != null) goto _L11; else goto _L10
_L10:
        obj = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
_L15:
        obj = ((GrowthKitCallbacks) (obj1)).onPromoReady$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMOQB6CLHNIORCCKNKESJFETQ6GIR9EH1M2R3CC9GM6QRJ4H874RRDDTA7IS357D66KOBMC4NMOOBECSNL6T3ID5N6EEP99HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP4A1P6URBFA9IN6S3FDPPMAEO_0(i, ((PromoUiRenderer) (obj2)).convertElementId(((com.google.identity.growth.proto.Promotion.PromoUi) (obj))));
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_532;
        }
        if (!((com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse) (obj)).approved || ((com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse) (obj)).activity == null)
        {
            break MISSING_BLOCK_LABEL_532;
        }
        obj1 = promoUiRenderer;
        obj2 = ((com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse) (obj)).activity;
        if (promotion.ui_ != null) goto _L13; else goto _L12
_L12:
        obj = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
_L16:
        if (!((PromoUiRenderer) (obj1)).render(((android.support.v4.app.FragmentActivity) (obj2)), ((com.google.identity.growth.proto.Promotion.PromoUi) (obj)), promocontext))
        {
            break MISSING_BLOCK_LABEL_499;
        }
        clearcutLogger.logPromoShown(promocontext);
        obj1 = streamzIncrements;
        obj2 = promotionShownCounter;
        s = packageName;
        if (promotion.ui_ != null)
        {
            break MISSING_BLOCK_LABEL_491;
        }
        promotion = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
_L17:
        obj = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(((com.google.identity.growth.proto.Promotion.PromoUi) (promotion)).uiType_);
        promotion = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion) (obj));
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_327;
        }
        promotion = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
        ((GenericMetric) (obj2)).doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            s, promotion.name()
        }));
        StreamzIncrements.incrementCounts++;
        if ((long)StreamzIncrements.incrementCounts < ((Long)((StreamzIncrements) (obj1)).incrementsToFlush.get()).longValue())
        {
            break MISSING_BLOCK_LABEL_462;
        }
        promotion = ((StreamzIncrements) (obj1)).streamzLogger;
        obj = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj1)).metricFactory);
        if (((StreamzMessageProducer) (obj)).incrementRequest.batch_.size() == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_458;
        }
        ((GcoreClearcutStreamzLogger) (promotion)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj))).setLogSourceName(((GcoreClearcutStreamzLogger) (promotion)).logSourceName).logAsync();
        StreamzIncrements.incrementCounts = 0;
        return Boolean.valueOf(true);
_L9:
        obj = promotion.ui_;
          goto _L14
_L11:
        obj = promotion.ui_;
          goto _L15
_L13:
        obj = promotion.ui_;
          goto _L16
        promotion = promotion.ui_;
          goto _L17
        try
        {
            clearcutLogger.logPromoNotShownClientBlocked$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5THMURBDDTN2UK3IDTMMUGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMOQB6CLHNIORCCKNKESJFETQ6GIR9EH1M2R3CC9GM6QRJ4H26ARJPA9IM2SRFDOTIILG_0(promocontext, android.support.v4.content.ModernAsyncTask.Status.CLIENT_ERROR$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP48HIMSUAICLGN6RRE7C______0);
        }
        // Misplaced declaration of an exception variable
        catch (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion)
        {
            clearcutLogger.logPromoNotShownClientBlocked$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5THMURBDDTN2UK3IDTMMUGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMOQB6CLHNIORCCKNKESJFETQ6GIR9EH1M2R3CC9GM6QRJ4H26ARJPA9IM2SRFDOTIILG_0(promocontext, android.support.v4.content.ModernAsyncTask.Status.CLIENT_ERROR$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP48HIMSUAICLGN6RRE7C______0);
        }
          goto _L18
        clearcutLogger.logPromoNotShownClientBlocked$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5THMURBDDTN2UK3IDTMMUGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMOQB6CLHNIORCCKNKESJFETQ6GIR9EH1M2R3CC9GM6QRJ4H26ARJPA9IM2SRFDOTIILG_0(promocontext, ((com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse) (obj)).denyReason$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP48HIMSUAICLGN6RRE7C______0);
          goto _L18
        i = 0;
          goto _L7
    }

}
