// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class val.uncaughtExceptionHandler
    implements ThreadFactory
{

    private final ThreadFactory val$backingThreadFactory;
    private final AtomicLong val$count;
    private final Boolean val$daemon;
    private final String val$nameFormat;
    private final Integer val$priority;
    private final nHandler val$uncaughtExceptionHandler;

    public final Thread newThread(Runnable runnable)
    {
        runnable = val$backingThreadFactory.newThread(runnable);
        if (val$nameFormat != null)
        {
            String s = val$nameFormat;
            long l = val$count.getAndIncrement();
            runnable.setName(String.format(Locale.ROOT, s, new Object[] {
                Long.valueOf(l)
            }));
        }
        if (val$daemon != null)
        {
            runnable.setDaemon(val$daemon.booleanValue());
        }
        if (val$priority != null)
        {
            runnable.setPriority(val$priority.intValue());
        }
        if (val$uncaughtExceptionHandler != null)
        {
            runnable.setUncaughtExceptionHandler(val$uncaughtExceptionHandler);
        }
        return runnable;
    }

    public ()
    {
        val$backingThreadFactory = threadfactory;
        val$nameFormat = s;
        val$count = atomiclong;
        val$daemon = boolean1;
        val$priority = integer;
        val$uncaughtExceptionHandler = nhandler;
        super();
    }
}
