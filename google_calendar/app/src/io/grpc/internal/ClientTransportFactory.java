// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package io.grpc.internal:
//            ProxyParameters, ConnectionClientTransport

public interface ClientTransportFactory
    extends Closeable
{

    public abstract void close();

    public abstract ScheduledExecutorService getScheduledExecutorService();

    public abstract ConnectionClientTransport newClientTransport(SocketAddress socketaddress, String s, String s1, ProxyParameters proxyparameters);
}
