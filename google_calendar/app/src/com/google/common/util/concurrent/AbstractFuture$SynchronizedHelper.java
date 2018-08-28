// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture

static final class I extends I
{

    final boolean casListeners(AbstractFuture abstractfuture, I i, I j)
    {
        abstractfuture;
        JVM INSTR monitorenter ;
        if (abstractfuture.listeners != i)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        abstractfuture.listeners = j;
        return true;
        abstractfuture;
        JVM INSTR monitorexit ;
        return false;
        i;
        abstractfuture;
        JVM INSTR monitorexit ;
        throw i;
    }

    final boolean casValue(AbstractFuture abstractfuture, Object obj, Object obj1)
    {
        abstractfuture;
        JVM INSTR monitorenter ;
        if (abstractfuture.value != obj)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        abstractfuture.value = obj1;
        return true;
        abstractfuture;
        JVM INSTR monitorexit ;
        return false;
        obj;
        abstractfuture;
        JVM INSTR monitorexit ;
        throw obj;
    }

    final boolean casWaiters(AbstractFuture abstractfuture, I i, I j)
    {
        abstractfuture;
        JVM INSTR monitorenter ;
        if (abstractfuture.waiters != i)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        abstractfuture.waiters = j;
        return true;
        abstractfuture;
        JVM INSTR monitorexit ;
        return false;
        i;
        abstractfuture;
        JVM INSTR monitorexit ;
        throw i;
    }

    final void putNext(I i, I j)
    {
        i.I = j;
    }

    final void putThread(I i, Thread thread)
    {
        i.I = thread;
    }

    I()
    {
    }
}
