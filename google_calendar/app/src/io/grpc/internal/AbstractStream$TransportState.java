// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            TransportTracer, MessageDeframer, StreamListener, Deframer, 
//            StatsTraceContext

public abstract class deframer
    implements sportExecutor, sportExecutor
{

    public boolean allocated;
    public boolean deallocated;
    public Deframer deframer;
    public int numSentBytesQueued;
    public final Object onReadyLock = new Object();
    public final TransportTracer transportTracer;

    private final boolean isReady()
    {
        Object obj = onReadyLock;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (allocated && numSentBytesQueued < 32768 && !deallocated)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj;
        JVM INSTR monitorexit ;
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected abstract StreamListener listener();

    public final void messagesAvailable( )
    {
        listener().messagesAvailable();
    }

    public final void notifyIfReady()
    {
        boolean flag;
        synchronized (onReadyLock)
        {
            flag = isReady();
        }
        if (flag)
        {
            listener().onReady();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void onSendingBytes(int i)
    {
        synchronized (onReadyLock)
        {
            numSentBytesQueued = numSentBytesQueued + i;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onStreamAllocated()
    {
        boolean flag2;
        flag2 = true;
        boolean flag;
        if (listener() != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        Object obj = onReadyLock;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag1;
        if (!allocated)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        throw new IllegalStateException(String.valueOf("Already allocated"));
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        allocated = true;
        obj;
        JVM INSTR monitorexit ;
        notifyIfReady();
        return;
    }

    protected (int i, StatsTraceContext statstracecontext, TransportTracer transporttracer)
    {
        if (statstracecontext == null)
        {
            throw new NullPointerException(String.valueOf("statsTraceCtx"));
        }
        if (transporttracer == null)
        {
            throw new NullPointerException(String.valueOf("transportTracer"));
        } else
        {
            transportTracer = (TransportTracer)transporttracer;
            deframer = new MessageDeframer(this, io.grpc.ner, i, statstracecontext, transporttracer);
            return;
        }
    }
}
