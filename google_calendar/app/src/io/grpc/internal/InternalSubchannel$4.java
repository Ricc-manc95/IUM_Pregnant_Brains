// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            InternalSubchannel, InUseStateAggregator, ConnectionClientTransport

final class val.inUse
    implements Runnable
{

    private final InternalSubchannel this$0;
    private final boolean val$inUse;
    private final ConnectionClientTransport val$transport;

    public final void run()
    {
        inUseStateAggregator.updateObjectInUse(val$transport, val$inUse);
    }

    port()
    {
        this$0 = final_internalsubchannel;
        val$transport = connectionclienttransport;
        val$inUse = Z.this;
        super();
    }
}
