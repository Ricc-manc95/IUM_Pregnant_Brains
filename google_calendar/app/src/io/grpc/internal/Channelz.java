// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public final class Channelz
{
    public static final class Security
    {

        public Security(Tls tls)
        {
            if (tls == null)
            {
                throw new NullPointerException();
            } else
            {
                return;
            }
        }
    }

    public static final class Tls
    {

        public Tls(SSLSession sslsession)
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


    public static final Channelz INSTANCE = new Channelz();
    public static final Logger log = Logger.getLogger(io/grpc/internal/Channelz.getName());
    public final ConcurrentMap otherSockets = new ConcurrentHashMap();
    public final ConcurrentNavigableMap rootChannels = new ConcurrentSkipListMap();
    public final ConcurrentMap subchannels = new ConcurrentHashMap();

    public Channelz()
    {
        new ConcurrentSkipListMap();
        new ConcurrentHashMap();
    }

}
