// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import android.content.Context;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Trigger;
import com.google.android.libraries.gcoreclient.security.GcoreProviderInstaller;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.GrowthKitJob;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.PromotionSync;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

public final class PeriodicSyncJob
    implements GrowthKitJob
{

    public static final Logger logger = new Logger();
    public final Context appContext;
    private final ListeningExecutorService blockingExecutor;
    private final FirebaseJobDispatcher firebaseJobDispatcher;
    public final GcoreProviderInstaller gcoreProviderInstaller;
    private final Provider growthkitEnabled;
    private final Provider period;
    public final PromotionSync promotionSync;
    private final Provider syncRetryMaxDelayMs;
    private final Provider syncRetryMinDelayMs;
    private final Provider syncRetryPolicy;

    public PeriodicSyncJob(FirebaseJobDispatcher firebasejobdispatcher, Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, GcoreProviderInstaller gcoreproviderinstaller, 
            Context context, PromotionSync promotionsync, ListeningExecutorService listeningexecutorservice)
    {
        firebaseJobDispatcher = firebasejobdispatcher;
        growthkitEnabled = provider;
        period = provider1;
        syncRetryMinDelayMs = provider2;
        syncRetryMaxDelayMs = provider3;
        syncRetryPolicy = provider4;
        gcoreProviderInstaller = gcoreproviderinstaller;
        appContext = context;
        promotionSync = promotionsync;
        blockingExecutor = listeningexecutorservice;
    }

    public final boolean autoSchedule()
    {
        return true;
    }

    public final ListenableFuture executeJob$51666RRD5TJ6ISJ5C9GN6P9FD9NM4P39EDO62T33D1IN4BQADTH50OBIC5MMAT35E9PJMAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQCD5PN8PBEC5H6OPA6ELQ7ASJ57C______0()
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final PeriodicSyncJob arg$1;

            public final void run()
            {
                PeriodicSyncJob periodicsyncjob = arg$1;
                periodicsyncjob.gcoreProviderInstaller.installIfNeeded(periodicsyncjob.appContext);
                return;
                Object obj;
                obj;
_L2:
                PeriodicSyncJob.logger.e(((Throwable) (obj)), "Failed to install security provider, GrowthKit sync can't run.", new Object[0]);
                return;
                obj;
                if (true) goto _L2; else goto _L1
_L1:
            }

            .Lambda._cls0()
            {
                arg$1 = PeriodicSyncJob.this;
            }
        }

        class .Lambda._cls1
            implements AsyncFunction
        {

            private final PeriodicSyncJob arg$1;

            public final ListenableFuture apply(Object obj)
            {
                return arg$1.promotionSync.syncAllAccounts();
            }

            .Lambda._cls1()
            {
                arg$1 = PeriodicSyncJob.this;
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
            return AbstractTransformFuture.create(blockingExecutor.submit(new .Lambda._cls0()), new .Lambda._cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
    }

    public final com.firebase.jobdispatcher.Job.Builder getJobBuilder()
    {
        int i = (int)TimeUnit.MILLISECONDS.toSeconds(((Long)period.get()).longValue());
        int j = (int)TimeUnit.MILLISECONDS.toSeconds((((Long)period.get()).longValue() * 5L) / 100L);
        com.firebase.jobdispatcher.Job.Builder builder = new com.firebase.jobdispatcher.Job.Builder(firebaseJobDispatcher.validator);
        builder.tag = "GrowthKit.PeriodicSyncJob";
        builder.constraints = (new int[] {
            2
        });
        builder.lifetime = 2;
        builder.retryStrategy = firebaseJobDispatcher.newRetryStrategy(((Integer)syncRetryPolicy.get()).intValue(), ((Long)syncRetryMinDelayMs.get()).intValue(), ((Long)syncRetryMaxDelayMs.get()).intValue());
        builder.trigger = Trigger.executionWindow(i - j, i + j);
        builder.recurring = true;
        builder.replaceCurrent = true;
        return builder;
    }

}
