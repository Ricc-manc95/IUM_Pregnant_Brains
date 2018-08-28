// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            ConnectionClientTransport

public final class transport
    implements transport
{

    public final ConnectionClientTransport transport;

    public final void onPingTimeout()
    {
        transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
    }

    public final void ping()
    {
        class _cls1
            implements ClientTransport.PingCallback
        {

            private final KeepAliveManager.ClientKeepAlivePinger this$0;

            public final void onFailure$5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTIILG_0()
            {
                transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
            }

            public final void onSuccess$5152ILG_0()
            {
            }

            _cls1()
            {
                this$0 = KeepAliveManager.ClientKeepAlivePinger.this;
                super();
            }
        }

        transport.ping(new _cls1(), com.google.common.util.concurrent._BB__11__08__11_);
    }

    public ecutor(ConnectionClientTransport connectionclienttransport)
    {
        transport = connectionclienttransport;
    }
}
