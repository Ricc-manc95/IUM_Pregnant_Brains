// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Status;
import java.io.InputStream;

// Referenced classes of package io.grpc.internal:
//            ClientStream, ClientStreamListener

public class NoopClientStream
    implements ClientStream
{

    public static final NoopClientStream INSTANCE = new NoopClientStream();

    public NoopClientStream()
    {
    }

    public final void cancel(Status status)
    {
    }

    public final void flush()
    {
    }

    public final void halfClose()
    {
    }

    public final void request(int i)
    {
    }

    public final void setAuthority(String s)
    {
    }

    public final void setCompressor(Compressor compressor)
    {
    }

    public final void setDeadline(Deadline deadline)
    {
    }

    public final void setDecompressorRegistry(DecompressorRegistry decompressorregistry)
    {
    }

    public final void setFullStreamDecompression(boolean flag)
    {
    }

    public final void setMaxInboundMessageSize(int i)
    {
    }

    public final void setMaxOutboundMessageSize(int i)
    {
    }

    public void start(ClientStreamListener clientstreamlistener)
    {
    }

    public final void writeMessage(InputStream inputstream)
    {
    }

}
