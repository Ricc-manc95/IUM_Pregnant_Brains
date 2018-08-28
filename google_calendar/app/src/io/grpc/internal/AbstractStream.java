// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Compressor;
import java.io.InputStream;

// Referenced classes of package io.grpc.internal:
//            Stream, Framer, GrpcUtil

public abstract class AbstractStream
    implements Stream
{

    public AbstractStream()
    {
    }

    public final void flush()
    {
        if (!framer().isClosed())
        {
            framer().flush();
        }
    }

    protected abstract Framer framer();

    public final void setCompressor(Compressor compressor)
    {
        Framer framer1 = framer();
        if (compressor == null)
        {
            throw new NullPointerException(String.valueOf("compressor"));
        } else
        {
            framer1.setCompressor((Compressor)compressor);
            return;
        }
    }

    public abstract TransportState transportState();

    public final void writeMessage(InputStream inputstream)
    {
        if (inputstream == null)
        {
            throw new NullPointerException(String.valueOf("message"));
        }
        if (!framer().isClosed())
        {
            framer().writePayload(inputstream);
        }
        GrpcUtil.closeQuietly(inputstream);
        return;
        Exception exception;
        exception;
        GrpcUtil.closeQuietly(inputstream);
        throw exception;
    }
}
