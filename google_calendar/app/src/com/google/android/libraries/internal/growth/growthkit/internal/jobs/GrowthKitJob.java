// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.jobs;

import com.google.common.util.concurrent.ListenableFuture;

public interface GrowthKitJob
{

    public abstract boolean autoSchedule();

    public abstract ListenableFuture executeJob$51666RRD5TJ6ISJ5C9GN6P9FD9NM4P39EDO62T33D1IN4BQADTH50OBIC5MMAT35E9PJMAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQCD5PN8PBEC5H6OPA6ELQ7ASJ57C______0();

    public abstract com.firebase.jobdispatcher.Job.Builder getJobBuilder();
}
