// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.IOException;
import java.net.SocketAddress;

// Referenced classes of package io.grpc.internal:
//            ProxyParameters

public interface ProxyDetector
{

    public static final io.grpc.Attributes.Key PROXY_PARAMS_KEY = new io.grpc.Attributes.Key("proxy-params-key");

    public abstract ProxyParameters proxyFor(SocketAddress socketaddress)
        throws IOException;

}
