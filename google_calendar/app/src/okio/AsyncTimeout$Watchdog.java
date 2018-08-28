// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;


// Referenced classes of package okio:
//            AsyncTimeout

static final class setDaemon extends Thread
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

    ion()
    {
        super("Okio Watchdog");
        setDaemon(true);
    }
}
