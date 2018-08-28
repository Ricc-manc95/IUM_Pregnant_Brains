// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.OptionalMethod;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.Util;
import java.io.IOException;
import java.util.List;
import javax.net.ssl.SSLSocket;

// Referenced classes of package io.grpc.okhttp:
//            OkHttpProtocolNegotiator

static final class  extends OkHttpProtocolNegotiator
{

    private static final OptionalMethod GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod([B, "getAlpnSelectedProtocol", new Class[0]);
    private static final OptionalMethod GET_NPN_SELECTED_PROTOCOL = new OptionalMethod([B, "getNpnSelectedProtocol", new Class[0]);
    private static final OptionalMethod SET_ALPN_PROTOCOLS = new OptionalMethod(null, "setAlpnProtocols", new Class[] {
        [B
    });
    private static final OptionalMethod SET_HOSTNAME = new OptionalMethod(null, "setHostname", new Class[] {
        java/lang/String
    });
    private static final OptionalMethod SET_NPN_PROTOCOLS = new OptionalMethod(null, "setNpnProtocols", new Class[] {
        [B
    });
    private static final OptionalMethod SET_USE_SESSION_TICKETS;

    protected final void configureTlsExtensions(SSLSocket sslsocket, String s, List list)
    {
        if (s != null)
        {
            SET_USE_SESSION_TICKETS.invokeOptionalWithoutCheckedException(sslsocket, new Object[] {
                Boolean.valueOf(true)
            });
            SET_HOSTNAME.invokeOptionalWithoutCheckedException(sslsocket, new Object[] {
                s
            });
        }
        s = ((String) (new Object[1]));
        s[0] = Platform.concatLengthPrefixed(list);
        if (platform.getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0() == android.support.v4.content._fld7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0)
        {
            SET_ALPN_PROTOCOLS.invokeWithoutCheckedException(sslsocket, s);
        }
        if (platform.getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0() != android.support.v4.content.FDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0)
        {
            SET_NPN_PROTOCOLS.invokeWithoutCheckedException(sslsocket, s);
            return;
        } else
        {
            throw new RuntimeException("We can not do TLS handshake on this Android version, please install the Google Play Services Dynamic Security Provider to use TLS");
        }
    }

    public final String getSelectedProtocol(SSLSocket sslsocket)
    {
        if (platform.getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0() != android.support.v4.content._fld7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        byte abyte0[] = (byte[])GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sslsocket, new Object[0]);
        if (abyte0 == null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        String s = new String(abyte0, Util.UTF_8);
        return s;
        Exception exception;
        exception;
        if (platform.getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0() == android.support.v4.content.FDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        sslsocket = (byte[])GET_NPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sslsocket, new Object[0]);
        if (sslsocket == null)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        sslsocket = new String(sslsocket, Util.UTF_8);
        return sslsocket;
        sslsocket;
        return null;
    }

    public final String negotiate(SSLSocket sslsocket, String s, List list)
        throws IOException
    {
        String s2 = getSelectedProtocol(sslsocket);
        String s1 = s2;
        if (s2 == null)
        {
            s1 = super.negotiate(sslsocket, s, list);
        }
        return s1;
    }

    static 
    {
        SET_USE_SESSION_TICKETS = new OptionalMethod(null, "setUseSessionTickets", new Class[] {
            Boolean.TYPE
        });
    }

    (Platform platform)
    {
        super(platform);
    }
}
