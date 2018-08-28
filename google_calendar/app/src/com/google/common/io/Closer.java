// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import com.google.common.base.Throwables;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public final class Closer
    implements Closeable
{

    public static final Suppressor SUPPRESSOR;
    public final Deque stack = new ArrayDeque(4);
    private final Suppressor suppressor;
    public Throwable thrown;

    Closer(Suppressor suppressor1)
    {
        if (suppressor1 == null)
        {
            throw new NullPointerException();
        } else
        {
            suppressor = (Suppressor)suppressor1;
            return;
        }
    }

    public final void close()
        throws IOException
    {
        Throwable throwable = thrown;
        while (!stack.isEmpty()) 
        {
            Closeable closeable = (Closeable)stack.removeFirst();
            try
            {
                closeable.close();
            }
            catch (Throwable throwable1)
            {
                if (throwable == null)
                {
                    throwable = throwable1;
                } else
                {
                    suppressor.suppress(closeable, throwable, throwable1);
                }
            }
        }
        if (thrown == null && throwable != null)
        {
            Throwables.propagateIfPossible(throwable, java/io/IOException);
            throw new AssertionError(throwable);
        } else
        {
            return;
        }
    }

    public final RuntimeException rethrow(Throwable throwable)
        throws IOException
    {
        if (throwable == null)
        {
            throw new NullPointerException();
        } else
        {
            thrown = throwable;
            Throwables.propagateIfPossible(throwable, java/io/IOException);
            throw new RuntimeException(throwable);
        }
    }

    static 
    {
        Object obj;
        boolean flag;
        if (SuppressingSuppressor.addSuppressed != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = SuppressingSuppressor.INSTANCE;
        } else
        {
            obj = LoggingSuppressor.INSTANCE;
        }
        SUPPRESSOR = ((Suppressor) (obj));
    }

    private class Suppressor
    {

        public abstract void suppress(Closeable closeable, Throwable throwable, Throwable throwable1);
    }


    private class SuppressingSuppressor
        implements Suppressor
    {

        public static final SuppressingSuppressor INSTANCE = new SuppressingSuppressor();
        public static final Method addSuppressed = getAddSuppressed();

        private static Method getAddSuppressed()
        {
            Method method;
            try
            {
                method = java/lang/Throwable.getMethod("addSuppressed", new Class[] {
                    java/lang/Throwable
                });
            }
            catch (Throwable throwable)
            {
                return null;
            }
            return method;
        }

        public final void suppress(Closeable closeable, Throwable throwable, Throwable throwable1)
        {
            if (throwable == throwable1)
            {
                return;
            }
            try
            {
                addSuppressed.invoke(throwable, new Object[] {
                    throwable1
                });
                return;
            }
            catch (Throwable throwable2)
            {
                LoggingSuppressor.INSTANCE.suppress(closeable, throwable, throwable1);
            }
        }


        SuppressingSuppressor()
        {
        }
    }


    private class LoggingSuppressor
        implements Suppressor
    {

        public static final LoggingSuppressor INSTANCE = new LoggingSuppressor();

        public final void suppress(Closeable closeable, Throwable throwable, Throwable throwable1)
        {
            throwable = Closeables.logger;
            Level level = Level.WARNING;
            closeable = String.valueOf(closeable);
            throwable.logp(level, "com.google.common.io.Closer$LoggingSuppressor", "suppress", (new StringBuilder(String.valueOf(closeable).length() + 42)).append("Suppressing exception thrown when closing ").append(closeable).toString(), throwable1);
        }


        LoggingSuppressor()
        {
        }
    }

}
