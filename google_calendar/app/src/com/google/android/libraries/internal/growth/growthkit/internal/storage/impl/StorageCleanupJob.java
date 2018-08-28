// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.GrowthKitJob;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.StorageUtilities;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

public final class StorageCleanupJob
    implements GrowthKitJob
{

    private final FirebaseJobDispatcher firebaseJobDispatcher;
    private final Provider period;
    private final StorageUtilities storageUtilities;

    public StorageCleanupJob(StorageUtilities storageutilities, Provider provider, FirebaseJobDispatcher firebasejobdispatcher)
    {
        storageUtilities = storageutilities;
        period = provider;
        firebaseJobDispatcher = firebasejobdispatcher;
    }

    public final boolean autoSchedule()
    {
        return true;
    }

    public final ListenableFuture executeJob$51666RRD5TJ6ISJ5C9GN6P9FD9NM4P39EDO62T33D1IN4BQADTH50OBIC5MMAT35E9PJMAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQCD5PN8PBEC5H6OPA6ELQ7ASJ57C______0()
    {
        storageUtilities.cleanup();
        if (true)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
    }

    public final com.firebase.jobdispatcher.Job.Builder getJobBuilder()
    {
        int i = (int)TimeUnit.MILLISECONDS.toSeconds(((Long)period.get()).longValue());
        int j = (int)TimeUnit.MILLISECONDS.toSeconds((((Long)period.get()).longValue() * 5L) / 100L);
        com.firebase.jobdispatcher.Job.Builder builder = new com.firebase.jobdispatcher.Job.Builder(firebaseJobDispatcher.validator);
        builder.tag = "GrowthKit.StorageCleanupJob";
        builder.lifetime = 2;
        builder.retryStrategy = RetryStrategy.DEFAULT_LINEAR;
        builder.trigger = Trigger.executionWindow(i - j, j + i);
        builder.recurring = true;
        builder.replaceCurrent = true;
        return builder;
    }
}
