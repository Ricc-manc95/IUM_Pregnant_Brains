// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package com.google.common.util.concurrent:
//            FluentFuture, AbstractFuture

abstract class ImmediateFuture extends FluentFuture
{
    public static final class ImmediateCancelledFuture extends AbstractFuture.TrustedFuture
    {

        public ImmediateCancelledFuture()
        {
            ((AbstractFuture)this).cancel(false);
        }
    }

    public static final class ImmediateFailedFuture extends AbstractFuture.TrustedFuture
    {

        public ImmediateFailedFuture(Throwable throwable)
        {
            setException(throwable);
        }
    }

    public static final class ImmediateSuccessfulFuture extends ImmediateFuture
    {

        public static final ImmediateSuccessfulFuture NULL = new ImmediateSuccessfulFuture(null);
        private final Object value;

        public final Object get()
        {
            return value;
        }

        public final String toString()
        {
            String s = super.toString();
            String s1 = String.valueOf(value);
            return (new StringBuilder(String.valueOf(s).length() + 27 + String.valueOf(s1).length())).append(s).append("[status=SUCCESS, result=[").append(s1).append("]]").toString();
        }


        public ImmediateSuccessfulFuture(Object obj)
        {
            value = obj;
        }
    }


    private static final Logger log = Logger.getLogger(com/google/common/util/concurrent/ImmediateFuture.getName());

    ImmediateFuture()
    {
    }

    public final void addListener(Runnable runnable, Executor executor)
    {
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("Runnable was null."));
        }
        if (executor == null)
        {
            throw new NullPointerException(String.valueOf("Executor was null."));
        }
        try
        {
            executor.execute(runnable);
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            Logger logger = log;
            Level level = Level.SEVERE;
            runnable = String.valueOf(runnable);
            executor = String.valueOf(executor);
            logger.logp(level, "com.google.common.util.concurrent.ImmediateFuture", "addListener", (new StringBuilder(String.valueOf(runnable).length() + 57 + String.valueOf(executor).length())).append("RuntimeException while executing runnable ").append(runnable).append(" with executor ").append(executor).toString(), runtimeexception);
            return;
        }
    }

    public boolean cancel(boolean flag)
    {
        return false;
    }

    public abstract Object get()
        throws ExecutionException;

    public Object get(long l, TimeUnit timeunit)
        throws ExecutionException
    {
        if (timeunit == null)
        {
            throw new NullPointerException();
        } else
        {
            return get();
        }
    }

    public boolean isCancelled()
    {
        return false;
    }

    public boolean isDone()
    {
        return true;
    }

}
