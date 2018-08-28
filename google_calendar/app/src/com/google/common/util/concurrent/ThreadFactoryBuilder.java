// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class ThreadFactoryBuilder
{

    private ThreadFactory backingThreadFactory;
    public Boolean daemon;
    public String nameFormat;
    private Integer priority;
    public Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public ThreadFactoryBuilder()
    {
        nameFormat = null;
        daemon = null;
        priority = null;
        uncaughtExceptionHandler = null;
        backingThreadFactory = null;
    }

    public final ThreadFactory build()
    {
        final String nameFormat = this.nameFormat;
        final Boolean daemon = this.daemon;
        Thread.UncaughtExceptionHandler uncaughtexceptionhandler = uncaughtExceptionHandler;
        final ThreadFactory backingThreadFactory = Executors.defaultThreadFactory();
        final AtomicLong count;
        if (nameFormat != null)
        {
            count = new AtomicLong(0L);
        } else
        {
            count = null;
        }
        return new _cls1();
    }

    private class _cls1
        implements ThreadFactory
    {

        private final ThreadFactory val$backingThreadFactory;
        private final AtomicLong val$count;
        private final Boolean val$daemon;
        private final String val$nameFormat;
        private final Integer val$priority;
        private final Thread.UncaughtExceptionHandler val$uncaughtExceptionHandler;

        public final Thread newThread(Runnable runnable)
        {
            runnable = backingThreadFactory.newThread(runnable);
            if (nameFormat != null)
            {
                String s = nameFormat;
                long l = count.getAndIncrement();
                runnable.setName(String.format(Locale.ROOT, s, new Object[] {
                    Long.valueOf(l)
                }));
            }
            if (daemon != null)
            {
                runnable.setDaemon(daemon.booleanValue());
            }
            if (priority != null)
            {
                runnable.setPriority(priority.intValue());
            }
            if (uncaughtExceptionHandler != null)
            {
                runnable.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            }
            return runnable;
        }

        public _cls1()
        {
            backingThreadFactory = threadfactory;
            nameFormat = s;
            count = atomiclong;
            daemon = boolean1;
            priority = integer;
            uncaughtExceptionHandler = uncaughtexceptionhandler;
            super();
        }
    }

}
