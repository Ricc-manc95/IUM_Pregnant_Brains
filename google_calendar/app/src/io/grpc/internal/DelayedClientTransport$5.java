// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ClientTransport

final class val.transport
    implements Runnable
{

    private final ndingStream val$stream;
    private final ClientTransport val$transport;

    public final void run()
    {
        val$stream.createRealStream(val$transport);
    }

    ndingStream()
    {
        val$stream = ndingstream;
        val$transport = clienttransport;
        super();
    }
}
