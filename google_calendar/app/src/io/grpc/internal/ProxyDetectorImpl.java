// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Supplier;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ProxyDetector, ProxyParameters, GrpcUtil

class ProxyDetectorImpl
    implements ProxyDetector
{
    static interface AuthenticationProvider
    {

        public abstract PasswordAuthentication requestPasswordAuthentication(String s, InetAddress inetaddress, int i, String s1, String s2, String s3);
    }


    private static final AuthenticationProvider DEFAULT_AUTHENTICATOR = new _cls1();
    private static final Supplier DEFAULT_PROXY_SELECTOR = new _cls2();
    public static final Logger log = Logger.getLogger(io/grpc/internal/ProxyDetectorImpl.getName());
    private final AuthenticationProvider authenticationProvider;
    private final ProxyParameters override;
    private final Supplier proxySelector;

    public ProxyDetectorImpl()
    {
        this(DEFAULT_PROXY_SELECTOR, DEFAULT_AUTHENTICATOR, System.getenv("GRPC_PROXY_EXP"));
    }

    private ProxyDetectorImpl(Supplier supplier, AuthenticationProvider authenticationprovider, String s)
    {
        if (supplier == null)
        {
            throw new NullPointerException();
        }
        proxySelector = (Supplier)supplier;
        if (authenticationprovider == null)
        {
            throw new NullPointerException();
        }
        authenticationProvider = (AuthenticationProvider)authenticationprovider;
        if (s != null)
        {
            if (s == null)
            {
                supplier = null;
            } else
            {
                supplier = s.split(":", 2);
                int i = 80;
                if (supplier.length > 1)
                {
                    i = Integer.parseInt(supplier[1]);
                }
                log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl", "overrideProxy", "Detected GRPC_PROXY_EXP and will honor it, but this feature will be removed in a future release. Use the JVM flags \"-Dhttps.proxyHost=HOST -Dhttps.proxyPort=PORT\" to set the https proxy for this JVM.");
                supplier = new InetSocketAddress(supplier[0], i);
            }
            override = new ProxyParameters(supplier, null, null);
            return;
        } else
        {
            override = null;
            return;
        }
    }

    private final ProxyParameters detectProxy(InetSocketAddress inetsocketaddress)
        throws IOException
    {
        Object obj;
        try
        {
            obj = GrpcUtil.getHost(inetsocketaddress);
        }
        // Misplaced declaration of an exception variable
        catch (InetSocketAddress inetsocketaddress)
        {
            log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl", "detectProxy", "Failed to get host for proxy lookup, proceeding without proxy", inetsocketaddress);
            return null;
        }
        try
        {
            inetsocketaddress = new URI("https", null, ((String) (obj)), inetsocketaddress.getPort(), null, null, null);
        }
        // Misplaced declaration of an exception variable
        catch (InetSocketAddress inetsocketaddress)
        {
            log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl", "detectProxy", "Failed to construct URI for proxy lookup, proceeding without proxy", inetsocketaddress);
            return null;
        }
        inetsocketaddress = ((ProxySelector)proxySelector.get()).select(inetsocketaddress);
        if (inetsocketaddress.size() > 1)
        {
            log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl", "detectProxy", "More than 1 proxy detected, gRPC will select the first one");
        }
        inetsocketaddress = (Proxy)inetsocketaddress.get(0);
        if (inetsocketaddress.type() == java.net.Proxy.Type.DIRECT)
        {
            return null;
        }
        obj = (InetSocketAddress)inetsocketaddress.address();
        PasswordAuthentication passwordauthentication = authenticationProvider.requestPasswordAuthentication(GrpcUtil.getHost(((InetSocketAddress) (obj))), ((InetSocketAddress) (obj)).getAddress(), ((InetSocketAddress) (obj)).getPort(), "https", "", null);
        inetsocketaddress = ((InetSocketAddress) (obj));
        if (((InetSocketAddress) (obj)).isUnresolved())
        {
            inetsocketaddress = new InetSocketAddress(InetAddress.getByName(((InetSocketAddress) (obj)).getHostName()), ((InetSocketAddress) (obj)).getPort());
        }
        if (passwordauthentication == null)
        {
            return new ProxyParameters(inetsocketaddress, null, null);
        } else
        {
            return new ProxyParameters(inetsocketaddress, passwordauthentication.getUserName(), new String(passwordauthentication.getPassword()));
        }
    }

    public final ProxyParameters proxyFor(SocketAddress socketaddress)
        throws IOException
    {
        if (override != null)
        {
            return override;
        }
        if (!(socketaddress instanceof InetSocketAddress))
        {
            return null;
        } else
        {
            return detectProxy((InetSocketAddress)socketaddress);
        }
    }


    private class _cls1
        implements AuthenticationProvider
    {

        public final PasswordAuthentication requestPasswordAuthentication(String s, InetAddress inetaddress, int i, String s1, String s2, String s3)
        {
            try
            {
                s3 = new URL(s1, s, i, "");
            }
            // Misplaced declaration of an exception variable
            catch (String s3)
            {
                ProxyDetectorImpl.log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl$1", "requestPasswordAuthentication", String.format("failed to create URL for Authenticator: %s %s", new Object[] {
                    s1, s
                }));
                s3 = null;
            }
            return Authenticator.requestPasswordAuthentication(s, inetaddress, i, s1, s2, null, s3, java.net.Authenticator.RequestorType.PROXY);
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements Supplier
    {

        public final Object get()
        {
            return ProxySelector.getDefault();
        }

        _cls2()
        {
        }
    }

}
