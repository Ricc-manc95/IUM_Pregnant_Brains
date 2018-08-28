// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Stopwatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

final class Rescheduler
{

    public boolean enabled;
    public long runAtNanos;
    public final Runnable runnable;
    public final ScheduledExecutorService scheduler;
    public final Executor serializingExecutor;
    public final Stopwatch stopwatch;
    public ScheduledFuture wakeUp;

    Rescheduler(Runnable runnable1, Executor executor, ScheduledExecutorService scheduledexecutorservice, Stopwatch stopwatch1)
    {
        runnable = runnable1;
        serializingExecutor = executor;
        scheduler = scheduledexecutorservice;
        stopwatch = stopwatch1;
        stopwatch1.start();
    }
}
