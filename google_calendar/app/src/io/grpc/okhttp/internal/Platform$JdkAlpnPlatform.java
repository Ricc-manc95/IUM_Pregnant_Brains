// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;

// Referenced classes of package io.grpc.okhttp.internal:
//            Platform, Protocol

static final class getApplicationProtocol extends Platform
{

    private final Method getApplicationProtocol;
    private final Method setApplicationProtocols;

    public final void configureTlsExtensions(SSLSocket sslsocket, String s, List list)
    {
        s = sslsocket.getSSLParameters();
        ArrayList arraylist = new ArrayList(list.size());
        list = list.iterator();
        do
        {
            if (!list.hasNext())
            {
                break;
            }
            Protocol protocol = (Protocol)list.next();
            if (protocol != Protocol.HTTP_1_0)
            {
                arraylist.add(protocol.toString());
            }
        } while (true);
        try
        {
            setApplicationProtocols.invoke(s, new Object[] {
                arraylist.toArray(new String[arraylist.size()])
            });
        }
        // Misplaced declaration of an exception variable
        catch (SSLSocket sslsocket)
        {
            throw new RuntimeException(sslsocket);
        }
        // Misplaced declaration of an exception variable
        catch (SSLSocket sslsocket)
        {
            throw new RuntimeException(sslsocket);
        }
        sslsocket.setSSLParameters(s);
    }

    public final String getSelectedProtocol(SSLSocket sslsocket)
    {
        try
        {
            sslsocket = (String)getApplicationProtocol.invoke(sslsocket, new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (SSLSocket sslsocket)
        {
            throw new RuntimeException(sslsocket);
        }
        // Misplaced declaration of an exception variable
        catch (SSLSocket sslsocket)
        {
            throw new RuntimeException(sslsocket);
        }
        return sslsocket;
    }

    public final int getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0()
    {
        return android.support.v4.content.PN_AND_NPN._fld9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
    }

    (Provider provider, Method method, Method method1)
    {
        super(provider);
        setApplicationProtocols = method;
        getApplicationProtocol = method1;
    }
}
