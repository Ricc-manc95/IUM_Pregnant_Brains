// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.accounts.AccountManager;
import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.ClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Trace;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.TargetingRulePredicate;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.TriggeringConditionsPredicate;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.PromotionSync;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.PromoUiRenderer;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UserActionUtil;
import com.google.android.libraries.streamz.Counter1;
import com.google.android.libraries.streamz.Counter2;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import java.util.Set;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            TriggeringEventProcessor

public final class TriggeringEventProcessor_Factory
    implements Factory
{

    private final Provider accountManagerProvider;
    private final Provider callbackManagerProvider;
    private final Provider cappedPromotionsStoreProvider;
    private final Provider clearcutCountersStoreProvider;
    private final Provider clearcutLoggerProvider;
    private final Provider clockProvider;
    private final Provider contextProvider;
    private final Provider enablePromotionsWithAccessibilityProvider;
    private final Provider executorProvider;
    private final Provider packageNameProvider;
    private final Provider presentedPromosStoreProvider;
    private final Provider promoUiRendererProvider;
    private final Provider promotionShownCounterProvider;
    private final Provider promotionSyncProvider;
    private final Provider promotionsStoreProvider;
    private final Provider streamzIncrementsProvider;
    private final Provider syncAfterPromoShownProvider;
    private final Provider targetingAppliedCounterProvider;
    private final Provider targetingRulePredicateProvider;
    private final Provider traceProvider;
    private final Provider triggerAppliedCounterProvider;
    private final Provider triggeringConditionsPredicateProvider;
    private final Provider triggeringRulePredicatesProvider;
    private final Provider userActionUtilProvider;
    private final Provider veCountersStoreProvider;

    public TriggeringEventProcessor_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, 
            Provider provider14, Provider provider15, Provider provider16, Provider provider17, Provider provider18, Provider provider19, Provider provider20, 
            Provider provider21, Provider provider22, Provider provider23, Provider provider24)
    {
        contextProvider = provider;
        clockProvider = provider1;
        promotionsStoreProvider = provider2;
        cappedPromotionsStoreProvider = provider3;
        clearcutCountersStoreProvider = provider4;
        veCountersStoreProvider = provider5;
        presentedPromosStoreProvider = provider6;
        triggeringConditionsPredicateProvider = provider7;
        triggeringRulePredicatesProvider = provider8;
        targetingRulePredicateProvider = provider9;
        accountManagerProvider = provider10;
        promoUiRendererProvider = provider11;
        promotionSyncProvider = provider12;
        callbackManagerProvider = provider13;
        clearcutLoggerProvider = provider14;
        executorProvider = provider15;
        enablePromotionsWithAccessibilityProvider = provider16;
        syncAfterPromoShownProvider = provider17;
        triggerAppliedCounterProvider = provider18;
        targetingAppliedCounterProvider = provider19;
        promotionShownCounterProvider = provider20;
        streamzIncrementsProvider = provider21;
        packageNameProvider = provider22;
        userActionUtilProvider = provider23;
        traceProvider = provider24;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = clockProvider;
        Provider provider2 = promotionsStoreProvider;
        Provider provider3 = cappedPromotionsStoreProvider;
        Provider provider4 = clearcutCountersStoreProvider;
        Provider provider5 = veCountersStoreProvider;
        Provider provider6 = presentedPromosStoreProvider;
        Provider provider7 = triggeringConditionsPredicateProvider;
        Provider provider8 = triggeringRulePredicatesProvider;
        Provider provider9 = targetingRulePredicateProvider;
        Provider provider10 = accountManagerProvider;
        Provider provider11 = promoUiRendererProvider;
        Provider provider12 = promotionSyncProvider;
        Provider provider13 = callbackManagerProvider;
        Provider provider14 = clearcutLoggerProvider;
        Provider provider15 = executorProvider;
        Provider provider16 = enablePromotionsWithAccessibilityProvider;
        Provider provider17 = syncAfterPromoShownProvider;
        Provider provider18 = triggerAppliedCounterProvider;
        Provider provider19 = targetingAppliedCounterProvider;
        Provider provider20 = promotionShownCounterProvider;
        Provider provider21 = streamzIncrementsProvider;
        Provider provider22 = packageNameProvider;
        Provider provider23 = userActionUtilProvider;
        Provider provider24 = traceProvider;
        return new TriggeringEventProcessor((Context)provider.get(), (Clock)provider1.get(), (PerAccountProvider)provider2.get(), (MessageStore)provider3.get(), (PerAccountProvider)provider4.get(), (PerAccountProvider)provider5.get(), (PerAccountProvider)provider6.get(), (TriggeringConditionsPredicate)provider7.get(), (Set)provider8.get(), (TargetingRulePredicate)provider9.get(), (AccountManager)provider10.get(), (PromoUiRenderer)provider11.get(), (PromotionSync)provider12.get(), provider13, (ClearcutLogger)provider14.get(), (ListeningExecutorService)provider15.get(), provider16, provider17, (Counter1)provider18.get(), (Counter1)provider19.get(), (Counter2)provider20.get(), (StreamzIncrements)provider21.get(), (String)provider22.get(), (UserActionUtil)provider23.get(), (Trace)provider24.get());
    }
}
