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

abstract class ForwardingClientStream
    implements ClientStream
{

    ForwardingClientStream()
    {
    }

    public final void cancel(Status status)
    {
        _mthdelegate().cancel(status);
    }

    protected abstract ClientStream _mthdelegate();

    public final void flush()
    {
        _mthdelegate().flush();
    }

    public final void halfClose()
    {
        _mthdelegate().halfClose();
    }

    public final void request(int i)
    {
        _mthdelegate().request(i);
    }

    public final void setAuthority(String s)
    {
        _mthdelegate().setAuthority(s);
    }

    public final void setCompressor(Compressor compressor)
    {
        _mthdelegate().setCompressor(compressor);
    }

    public final void setDeadline(Deadline deadline)
    {
        _mthdelegate().setDeadline(deadline);
    }

    public final void setDecompressorRegistry(DecompressorRegistry decompressorregistry)
    {
        _mthdelegate().setDecompressorRegistry(decompressorregistry);
    }

    public final void setFullStreamDecompression(boolean flag)
    {
        _mthdelegate().setFullStreamDecompression(flag);
    }

    public final void setMaxInboundMessageSize(int i)
    {
        _mthdelegate().setMaxInboundMessageSize(i);
    }

    public final void setMaxOutboundMessageSize(int i)
    {
        _mthdelegate().setMaxOutboundMessageSize(i);
    }

    public void start(ClientStreamListener clientstreamlistener)
    {
        _mthdelegate().start(clientstreamlistener);
    }

    public String toString()
    {
        return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("delegate", _mthdelegate()).toString();
    }

    public final void writeMessage(InputStream inputstream)
    {
        _mthdelegate().writeMessage(inputstream);
    }
}
