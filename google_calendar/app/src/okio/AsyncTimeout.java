// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

// Referenced classes of package okio:
//            Timeout

public class AsyncTimeout extends Timeout
{
    static final class Watchdog extends Thread
    {

        public final void run()
        {
_L5:
            okio/AsyncTimeout;
            JVM INSTR monitorenter ;
            Object obj = AsyncTimeout.head.next;
            if (obj != null) goto _L2; else goto _L1
_L1:
            long l;
            l = System.nanoTime();
            okio/AsyncTimeout.wait(AsyncTimeout.IDLE_TIMEOUT_MILLIS);
            if (AsyncTimeout.head.next != null || System.nanoTime() - l < AsyncTimeout.IDLE_TIMEOUT_NANOS) goto _L4; else goto _L3
_L3:
            obj = AsyncTimeout.head;
_L6:
            if (obj != null)
            {
                break MISSING_BLOCK_LABEL_139;
            }
            okio/AsyncTimeout;
            JVM INSTR monitorexit ;
              goto _L5
            obj;
            okio/AsyncTimeout;
            JVM INSTR monitorexit ;
            try
            {
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
              goto _L5
_L4:
            obj = null;
              goto _L6
_L2:
            l = System.nanoTime();
            l = ((AsyncTimeout) (obj)).timeoutAt - l;
            if (l <= 0L)
            {
                break MISSING_BLOCK_LABEL_121;
            }
            long l1 = l / 0xf4240L;
            okio/AsyncTimeout.wait(l1, (int)(l - l1 * 0xf4240L));
            obj = null;
              goto _L6
            AsyncTimeout.head.next = ((AsyncTimeout) (obj)).next;
            obj.next = null;
              goto _L6
            if (obj != AsyncTimeout.head)
            {
                break MISSING_BLOCK_LABEL_154;
            }
            AsyncTimeout.head = null;
            okio/AsyncTimeout;
            JVM INSTR monitorexit ;
            return;
            okio/AsyncTimeout;
            JVM INSTR monitorexit ;
            ((AsyncTimeout) (obj)).timedOut();
              goto _L5
        }

        Watchdog()
        {
            super("Okio Watchdog");
            setDaemon(true);
        }
    }


    public static final long IDLE_TIMEOUT_MILLIS;
    public static final long IDLE_TIMEOUT_NANOS;
    public static AsyncTimeout head;
    private boolean inQueue;
    public AsyncTimeout next;
    public long timeoutAt;

    public AsyncTimeout()
    {
    }

    private static boolean cancelScheduledTimeout(AsyncTimeout asynctimeout)
    {
        okio/AsyncTimeout;
        JVM INSTR monitorenter ;
        AsyncTimeout asynctimeout1 = head;
_L8:
        if (asynctimeout1 == null) goto _L2; else goto _L1
_L1:
        if (asynctimeout1.next != asynctimeout) goto _L4; else goto _L3
_L3:
        asynctimeout1.next = asynctimeout.next;
        asynctimeout.next = null;
        boolean flag = false;
_L6:
        okio/AsyncTimeout;
        JVM INSTR monitorexit ;
        return flag;
_L4:
        asynctimeout1 = asynctimeout1.next;
        continue; /* Loop/switch isn't completed */
_L2:
        flag = true;
        if (true) goto _L6; else goto _L5
_L5:
        asynctimeout;
        throw asynctimeout;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private static void scheduleTimeout(AsyncTimeout asynctimeout, long l, boolean flag)
    {
        okio/AsyncTimeout;
        JVM INSTR monitorenter ;
        long l1;
        if (head == null)
        {
            head = new AsyncTimeout();
            (new Watchdog()).start();
        }
        l1 = System.nanoTime();
        if (l == 0L || !flag)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        throw new IllegalStateException("No deadline");
        asynctimeout;
        okio/AsyncTimeout;
        JVM INSTR monitorexit ;
        throw asynctimeout;
        if (l == 0L)
        {
            break MISSING_BLOCK_LABEL_143;
        }
        asynctimeout.timeoutAt = l1 + l;
        l = asynctimeout.timeoutAt;
        for (AsyncTimeout asynctimeout1 = head; asynctimeout1.next != null && l - l1 >= asynctimeout1.next.timeoutAt - l1; asynctimeout1 = asynctimeout1.next)
        {
            break MISSING_BLOCK_LABEL_165;
        }

        asynctimeout.next = asynctimeout1.next;
        asynctimeout1.next = asynctimeout;
        if (asynctimeout1 == head)
        {
            okio/AsyncTimeout.notify();
        }
        okio/AsyncTimeout;
        JVM INSTR monitorexit ;
        return;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_157;
        }
        throw new IllegalStateException("No deadline");
        throw new AssertionError();
    }

    public final void enter()
    {
        if (inQueue)
        {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        if (0L == 0L)
        {
            return;
        } else
        {
            inQueue = true;
            scheduleTimeout(this, 0L, false);
            return;
        }
    }

    final IOException exit(IOException ioexception)
        throws IOException
    {
        boolean flag = false;
        if (inQueue)
        {
            inQueue = false;
            flag = cancelScheduledTimeout(this);
        }
        if (!flag)
        {
            return ioexception;
        } else
        {
            return newTimeoutException(ioexception);
        }
    }

    final void exit(boolean flag)
        throws IOException
    {
        boolean flag1 = false;
        if (inQueue)
        {
            inQueue = false;
            flag1 = cancelScheduledTimeout(this);
        }
        if (flag1 && flag)
        {
            throw newTimeoutException(null);
        } else
        {
            return;
        }
    }

    protected IOException newTimeoutException(IOException ioexception)
    {
        InterruptedIOException interruptedioexception = new InterruptedIOException("timeout");
        if (ioexception != null)
        {
            interruptedioexception.initCause(ioexception);
        }
        return interruptedioexception;
    }

    protected void timedOut()
    {
    }

    static 
    {
        IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60L);
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    }
}
