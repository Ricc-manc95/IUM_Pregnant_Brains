// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;

abstract class InterruptibleTask extends AtomicReference
    implements Runnable
{

    private static final Runnable DONE = new DoNothingRunnable();
    private static final Runnable INTERRUPTING = new DoNothingRunnable();

    InterruptibleTask()
    {
    }

    abstract void afterRanInterruptibly(Object obj, Throwable throwable);

    final void interruptTask()
    {
        Runnable runnable = (Runnable)get();
        if ((runnable instanceof Thread) && compareAndSet(runnable, INTERRUPTING))
        {
            ((Thread)runnable).interrupt();
            set(DONE);
        }
    }

    abstract boolean isDone();

    public final void run()
    {
        Thread thread = Thread.currentThread();
        if (compareAndSet(null, thread)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj;
        boolean flag;
        if (!isDone())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_154;
        }
        obj = runInterruptibly();
_L4:
        if (!compareAndSet(thread, DONE))
        {
            for (; get() == INTERRUPTING; Thread.yield()) { }
        }
        if (flag)
        {
            afterRanInterruptibly(obj, null);
            return;
        }
          goto _L1
        obj;
        if (!compareAndSet(thread, DONE))
        {
            for (; get() == INTERRUPTING; Thread.yield()) { }
        }
        if (!flag) goto _L1; else goto _L3
_L3:
        afterRanInterruptibly(null, ((Throwable) (obj)));
        return;
        obj;
        if (!compareAndSet(thread, DONE))
        {
            for (; get() == INTERRUPTING; Thread.yield()) { }
        }
        if (flag)
        {
            afterRanInterruptibly(null, null);
        }
        throw obj;
        obj = null;
          goto _L4
    }

    abstract Object runInterruptibly()
        throws Exception;

    abstract String toPendingString();

    public final String toString()
    {
        Object obj = (Runnable)get();
        String s;
        if (obj == DONE)
        {
            obj = "running=[DONE]";
        } else
        if (obj == INTERRUPTING)
        {
            obj = "running=[INTERRUPTED]";
        } else
        if (obj instanceof Thread)
        {
            obj = ((Thread)obj).getName();
            obj = (new StringBuilder(String.valueOf(obj).length() + 21)).append("running=[RUNNING ON ").append(((String) (obj))).append("]").toString();
        } else
        {
            obj = "running=[NOT STARTED YET]";
        }
        s = toPendingString();
        return (new StringBuilder(String.valueOf(obj).length() + 2 + String.valueOf(s).length())).append(((String) (obj))).append(", ").append(s).toString();
    }


    private class DoNothingRunnable
        implements Runnable
    {

        public final void run()
        {
        }

        DoNothingRunnable()
        {
        }
    }

}
