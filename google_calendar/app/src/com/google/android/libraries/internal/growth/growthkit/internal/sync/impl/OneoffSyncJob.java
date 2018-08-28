// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Trigger;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesNotAvailableException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesRepairableException;
import com.google.android.libraries.gcoreclient.security.GcoreProviderInstaller;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.GrowthKitJob;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.PromotionSync;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import javax.inject.Provider;

public final class OneoffSyncJob
    implements GrowthKitJob
{

    private static final Logger logger = new Logger();
    private final Context appContext;
    private final ListeningExecutorService blockingExecutor;
    private final Clock clock;
    private final FirebaseJobDispatcher firebaseJobDispatcher;
    private final GcoreProviderInstaller gcoreProviderInstaller;
    private final Provider growthkitEnabled;
    private final PromotionSync promotionSync;
    private final ListenableFuture sharedPrefsFuture;
    private final Provider syncOnStartup;
    private final Provider syncOnStartupAtMostEveryMs;
    private final Provider syncRetryMaxDelayMs;
    private final Provider syncRetryMinDelayMs;
    private final Provider syncRetryPolicy;

    public OneoffSyncJob(FirebaseJobDispatcher firebasejobdispatcher, Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, 
            GcoreProviderInstaller gcoreproviderinstaller, Context context, PromotionSync promotionsync, ListeningExecutorService listeningexecutorservice, ListenableFuture listenablefuture, Clock clock1)
    {
        firebaseJobDispatcher = firebasejobdispatcher;
        growthkitEnabled = provider;
        syncOnStartup = provider1;
        syncOnStartupAtMostEveryMs = provider2;
        syncRetryMinDelayMs = provider3;
        syncRetryMaxDelayMs = provider4;
        syncRetryPolicy = provider5;
        gcoreProviderInstaller = gcoreproviderinstaller;
        appContext = context;
        promotionSync = promotionsync;
        blockingExecutor = listeningexecutorservice;
        sharedPrefsFuture = listenablefuture;
        clock = clock1;
    }

    public final boolean autoSchedule()
    {
        return ((Boolean)syncOnStartup.get()).booleanValue();
    }

    public final ListenableFuture executeJob$51666RRD5TJ6ISJ5C9GN6P9FD9NM4P39EDO62T33D1IN4BQADTH50OBIC5MMAT35E9PJMAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQCD5PN8PBEC5H6OPA6ELQ7ASJ57C______0()
    {
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final OneoffSyncJob arg$1;

            public final ListenableFuture apply(Object obj)
            {
                return arg$1.lambda$executeJob$0$OneoffSyncJob((SharedPreferences)obj);
            }

            .Lambda._cls0()
            {
                arg$1 = OneoffSyncJob.this;
            }
        }

        if (!((Boolean)growthkitEnabled.get()).booleanValue())
        {
            if (true)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
            }
        } else
        {
            return AbstractTransformFuture.create(sharedPrefsFuture, new .Lambda._cls0(), blockingExecutor);
        }
    }

    public final com.firebase.jobdispatcher.Job.Builder getJobBuilder()
    {
        com.firebase.jobdispatcher.Job.Builder builder = new com.firebase.jobdispatcher.Job.Builder(firebaseJobDispatcher.validator);
        builder.tag = "GrowthKit.OneoffSyncJob";
        builder.constraints = (new int[] {
            2
        });
        builder.lifetime = 2;
        builder.retryStrategy = firebaseJobDispatcher.newRetryStrategy(((Integer)syncRetryPolicy.get()).intValue(), ((Long)syncRetryMinDelayMs.get()).intValue(), ((Long)syncRetryMaxDelayMs.get()).intValue());
        builder.trigger = Trigger.NOW;
        builder.replaceCurrent = true;
        return builder;
    }

    final ListenableFuture lambda$executeJob$0$OneoffSyncJob(SharedPreferences sharedpreferences)
        throws Exception
    {
        long l = sharedpreferences.getLong("LAST_SYNC_TIME", 0L);
        long l1 = clock.currentTimeMillis();
        long l2 = ((Long)syncOnStartupAtMostEveryMs.get()).longValue();
        if (l != 0L && l1 - l < l2)
        {
            sharedpreferences = logger;
            if (true)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
            }
        }
        gcoreProviderInstaller.installIfNeeded(appContext);
        return promotionSync.syncAllAccounts();
        sharedpreferences;
_L2:
        logger.e(sharedpreferences, "Failed to install security provider, GrowthKit sync can't run.", new Object[0]);
        if (true)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
        sharedpreferences;
        if (true) goto _L2; else goto _L1
_L1:
    }

}
