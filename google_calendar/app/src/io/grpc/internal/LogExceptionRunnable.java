// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class LogExceptionRunnable
    implements Runnable
{

    private static final Logger log = Logger.getLogger(io/grpc/internal/LogExceptionRunnable.getName());
    private final Runnable task;

    public LogExceptionRunnable(Runnable runnable)
    {
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("task"));
        } else
        {
            task = (Runnable)runnable;
            return;
        }
    }

    public final void run()
    {
        try
        {
            task.run();
            return;
        }
        catch (Throwable throwable)
        {
            Logger logger = log;
            Level level = Level.SEVERE;
            String s = String.valueOf(task);
            logger.logp(level, "io.grpc.internal.LogExceptionRunnable", "run", (new StringBuilder(String.valueOf(s).length() + 35)).append("Exception while executing runnable ").append(s).toString(), throwable);
            if (throwable == null)
            {
                throw new NullPointerException();
            }
            if (throwable instanceof RuntimeException)
            {
                throw (RuntimeException)throwable;
            }
            if (throwable instanceof Error)
            {
                throw (Error)throwable;
            } else
            {
                throw new AssertionError(throwable);
            }
        }
    }

    public final String toString()
    {
        String s = String.valueOf(task);
        return (new StringBuilder(String.valueOf(s).length() + 22)).append("LogExceptionRunnable(").append(s).append(")").toString();
    }

}
