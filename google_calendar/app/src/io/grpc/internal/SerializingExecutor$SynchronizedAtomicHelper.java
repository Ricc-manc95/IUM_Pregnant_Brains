// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            SerializingExecutor

static final class  extends 
{

    public final boolean runStateCompareAndSet(SerializingExecutor serializingexecutor, int i, int j)
    {
        serializingexecutor;
        JVM INSTR monitorenter ;
        if (serializingexecutor.runState != 0)
        {
            break MISSING_BLOCK_LABEL_18;
        }
        serializingexecutor.runState = -1;
        return true;
        serializingexecutor;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        serializingexecutor;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void runStateSet(SerializingExecutor serializingexecutor, int i)
    {
        serializingexecutor;
        JVM INSTR monitorenter ;
        serializingexecutor.runState = 0;
        serializingexecutor;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        serializingexecutor;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ()
    {
    }
}
