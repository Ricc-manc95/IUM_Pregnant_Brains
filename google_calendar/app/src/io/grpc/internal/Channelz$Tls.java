// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

// Referenced classes of package io.grpc.internal:
//            Channelz

public static final class erHost
{

    public dException(SSLSession sslsession)
    {
        sslsession.getCipherSuite();
        sslsession.getLocalCertificates();
        try
        {
            sslsession.getPeerCertificates();
            return;
        }
        catch (SSLPeerUnverifiedException sslpeerunverifiedexception)
        {
            Channelz.log.logp(Level.FINE, "io.grpc.internal.Channelz$Tls", "<init>", String.format("Peer cert not available for peerHost=%s", new Object[] {
                sslsession.getPeerHost()
            }), sslpeerunverifiedexception);
        }
    }
}
