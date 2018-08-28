// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package io.grpc.internal:
//            StatsTraceContext

final class statsTraceCtx extends FilterInputStream
{

    private long count;
    private long mark;
    private long maxCount;
    private final int maxMessageSize;
    private final StatsTraceContext statsTraceCtx;

    private final void reportCount()
    {
        if (count > maxCount)
        {
            statsTraceCtx.inboundUncompressedSize(count - maxCount);
            maxCount = count;
        }
    }

    private final void verifySize()
    {
        if (count > (long)maxMessageSize)
        {
            throw new StatusRuntimeException(Status.RESOURCE_EXHAUSTED.withDescription(String.format("Compressed gRPC message exceeds maximum size %d: %d bytes read", new Object[] {
                Integer.valueOf(maxMessageSize), Long.valueOf(count)
            })));
        } else
        {
            return;
        }
    }

    public final void mark(int i)
    {
        this;
        JVM INSTR monitorenter ;
        in.mark(i);
        mark = count;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final int read()
        throws IOException
    {
        int i = in.read();
        if (i != -1)
        {
            count = count + 1L;
        }
        verifySize();
        reportCount();
        return i;
    }

    public final int read(byte abyte0[], int i, int j)
        throws IOException
    {
        i = in.read(abyte0, i, j);
        if (i != -1)
        {
            count = count + (long)i;
        }
        verifySize();
        reportCount();
        return i;
    }

    public final void reset()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (!in.markSupported())
        {
            throw new IOException("Mark not supported");
        }
        break MISSING_BLOCK_LABEL_27;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if (mark == -1L)
        {
            throw new IOException("Mark not set");
        }
        in.reset();
        count = mark;
        this;
        JVM INSTR monitorexit ;
    }

    public final long skip(long l)
        throws IOException
    {
        l = in.skip(l);
        count = count + l;
        verifySize();
        reportCount();
        return l;
    }

    (InputStream inputstream, int i, StatsTraceContext statstracecontext)
    {
        super(inputstream);
        mark = -1L;
        maxMessageSize = i;
        statsTraceCtx = statstracecontext;
    }
}
