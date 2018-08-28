// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            Stream, ClientStreamListener

public interface ClientStream
    extends Stream
{

    public abstract void cancel(Status status);

    public abstract void halfClose();

    public abstract void setAuthority(String s);

    public abstract void setDeadline(Deadline deadline);

    public abstract void setDecompressorRegistry(DecompressorRegistry decompressorregistry);

    public abstract void setFullStreamDecompression(boolean flag);

    public abstract void setMaxInboundMessageSize(int i);

    public abstract void setMaxOutboundMessageSize(int i);

    public abstract void start(ClientStreamListener clientstreamlistener);
}
