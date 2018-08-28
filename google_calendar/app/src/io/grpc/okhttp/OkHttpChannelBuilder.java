// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import com.squareup.okhttp.CipherSuite;
import com.squareup.okhttp.ConnectionSpec;
import com.squareup.okhttp.TlsVersion;
import io.grpc.Attributes;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.GrpcUtil;
import io.grpc.okhttp.internal.Platform;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public final class OkHttpChannelBuilder extends AbstractManagedChannelImplBuilder
{

    private static final io.grpc.okhttp.internal.ConnectionSpec INTERNAL_DEFAULT_CONNECTION_SPEC;
    public static final io.grpc.internal.SharedResourceHolder.Resource SHARED_EXECUTOR;
    private io.grpc.okhttp.internal.ConnectionSpec connectionSpec;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    public NegotiationType negotiationType;
    public SSLSocketFactory sslSocketFactory;

    public OkHttpChannelBuilder(String s)
    {
        super(s);
        connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        negotiationType = NegotiationType.TLS;
        keepAliveTimeNanos = 0x7fffffffffffffffL;
        keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
    }

    public OkHttpChannelBuilder(String s, int i)
    {
        this(GrpcUtil.authorityFromHostAndPort(s, i));
    }

    private final SSLSocketFactory createSocketFactory()
    {
        switch (negotiationType.ordinal())
        {
        default:
            String s = String.valueOf(negotiationType);
            throw new RuntimeException((new StringBuilder(String.valueOf(s).length() + 26)).append("Unknown negotiation type: ").append(s).toString());

        case 0: // '\0'
            try
            {
                if (sslSocketFactory == null)
                {
                    SSLContext sslcontext;
                    if (GrpcUtil.IS_RESTRICTED_APPENGINE)
                    {
                        sslcontext = SSLContext.getInstance("TLS", Platform.PLATFORM.sslProvider);
                        TrustManagerFactory trustmanagerfactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                        trustmanagerfactory.init(null);
                        sslcontext.init(null, trustmanagerfactory.getTrustManagers(), SecureRandom.getInstance("SHA1PRNG", Platform.PLATFORM.sslProvider));
                    } else
                    {
                        sslcontext = SSLContext.getInstance("Default", Platform.PLATFORM.sslProvider);
                    }
                    sslSocketFactory = sslcontext.getSocketFactory();
                }
                return sslSocketFactory;
            }
            catch (GeneralSecurityException generalsecurityexception)
            {
                throw new RuntimeException("TLS Provider failure", generalsecurityexception);
            }

        case 1: // '\001'
            return null;
        }
    }

    protected final ClientTransportFactory buildTransportFactory()
    {
        boolean flag;
        if (keepAliveTimeNanos != 0x7fffffffffffffffL)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return new OkHttpTransportFactory(null, null, createSocketFactory(), null, connectionSpec, super.maxInboundMessageSize, flag, keepAliveTimeNanos, keepAliveTimeoutNanos, false, transportTracerFactory);
    }

    protected final Attributes getNameResolverParams()
    {
        negotiationType.ordinal();
        JVM INSTR tableswitch 0 1: default 28
    //                   0 94
    //                   1 74;
           goto _L1 _L2 _L3
_L1:
        String s = String.valueOf(negotiationType);
        throw new AssertionError((new StringBuilder(String.valueOf(s).length() + 12)).append(s).append(" not handled").toString());
_L3:
        char c = 'P';
_L5:
        return Attributes.newBuilder().set(io.grpc.NameResolver.Factory.PARAMS_DEFAULT_PORT, Integer.valueOf(c)).build();
_L2:
        c = '\u01BB';
        if (true) goto _L5; else goto _L4
_L4:
    }

    static 
    {
        Object obj = (new com.squareup.okhttp.ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)).cipherSuites(new CipherSuite[] {
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384
        }).tlsVersions(new TlsVersion[] {
            TlsVersion.TLS_1_2
        });
        if (!((com.squareup.okhttp.ConnectionSpec.Builder) (obj)).tls)
        {
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }
        obj.supportsTlsExtensions = true;
        new ConnectionSpec(((com.squareup.okhttp.ConnectionSpec.Builder) (obj)));
        obj = (new io.grpc.okhttp.internal.ConnectionSpec.Builder(io.grpc.okhttp.internal.ConnectionSpec.MODERN_TLS)).cipherSuites(new io.grpc.okhttp.internal.CipherSuite[] {
            io.grpc.okhttp.internal.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, io.grpc.okhttp.internal.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, io.grpc.okhttp.internal.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, io.grpc.okhttp.internal.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, io.grpc.okhttp.internal.CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, io.grpc.okhttp.internal.CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, io.grpc.okhttp.internal.CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, io.grpc.okhttp.internal.CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384
        }).tlsVersions(new io.grpc.okhttp.internal.TlsVersion[] {
            io.grpc.okhttp.internal.TlsVersion.TLS_1_2
        });
        if (!((io.grpc.okhttp.internal.ConnectionSpec.Builder) (obj)).tls)
        {
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        } else
        {
            obj.supportsTlsExtensions = true;
            INTERNAL_DEFAULT_CONNECTION_SPEC = new io.grpc.okhttp.internal.ConnectionSpec(((io.grpc.okhttp.internal.ConnectionSpec.Builder) (obj)));
            TimeUnit.DAYS.toNanos(1000L);
            SHARED_EXECUTOR = new _cls1();
        }
    }

    private class NegotiationType extends Enum
    {

        private static final NegotiationType $VALUES[];
        public static final NegotiationType PLAINTEXT;
        public static final NegotiationType TLS;

        public static NegotiationType[] values()
        {
            return (NegotiationType[])$VALUES.clone();
        }

        static 
        {
            TLS = new NegotiationType("TLS", 0);
            PLAINTEXT = new NegotiationType("PLAINTEXT", 1);
            $VALUES = (new NegotiationType[] {
                TLS, PLAINTEXT
            });
        }

        private NegotiationType(String s, int i)
        {
            super(s, i);
        }
    }


    private class OkHttpTransportFactory
        implements ClientTransportFactory
    {

        private boolean closed;
        private final io.grpc.okhttp.internal.ConnectionSpec connectionSpec;
        private final boolean enableKeepAlive;
        private final Executor executor;
        private final HostnameVerifier hostnameVerifier;
        private final AtomicBackoff keepAliveTimeNanos;
        private final long keepAliveTimeoutNanos;
        private final boolean keepAliveWithoutCalls;
        private final int maxMessageSize;
        private final SSLSocketFactory socketFactory;
        private final ScheduledExecutorService timeoutService;
        private final io.grpc.internal.TransportTracer.Factory transportTracerFactory;
        private final boolean usingSharedExecutor;
        private final boolean usingSharedScheduler;

        public final void close()
        {
            if (!closed)
            {
                closed = true;
                if (usingSharedScheduler)
                {
                    io.grpc.internal.SharedResourceHolder.Resource resource = GrpcUtil.TIMER_SERVICE;
                    ScheduledExecutorService scheduledexecutorservice = timeoutService;
                    SharedResourceHolder.holder.releaseInternal(resource, scheduledexecutorservice);
                }
                if (usingSharedExecutor)
                {
                    io.grpc.internal.SharedResourceHolder.Resource resource1 = OkHttpChannelBuilder.SHARED_EXECUTOR;
                    ExecutorService executorservice = (ExecutorService)executor;
                    SharedResourceHolder.holder.releaseInternal(resource1, executorservice);
                    return;
                }
            }
        }

        public final ScheduledExecutorService getScheduledExecutorService()
        {
            return timeoutService;
        }

        public final ConnectionClientTransport newClientTransport(SocketAddress socketaddress, String s, String s1, ProxyParameters proxyparameters)
        {
            if (closed)
            {
                throw new IllegalStateException("The transport factory is closed.");
            }
            final io.grpc.internal.AtomicBackoff.State keepAliveTimeNanosState = keepAliveTimeNanos;
            keepAliveTimeNanosState = new io.grpc.internal.AtomicBackoff.State(keepAliveTimeNanosState, ((AtomicBackoff) (keepAliveTimeNanosState)).value.get());
            class _cls1
                implements Runnable
            {

                private final io.grpc.internal.AtomicBackoff.State val$keepAliveTimeNanosState;

                public final void run()
                {
                    io.grpc.internal.AtomicBackoff.State state = keepAliveTimeNanosState;
                    long l2 = Math.max(state.savedValue << 1, state.savedValue);
                    if (state.this$0.value.compareAndSet(state.savedValue, l2))
                    {
                        AtomicBackoff.log.logp(Level.WARNING, "io.grpc.internal.AtomicBackoff$State", "backoff", "Increased {0} to {1}", new Object[] {
                            state.this$0.name, Long.valueOf(l2)
                        });
                    }
                }

                _cls1()
                {
                    keepAliveTimeNanosState = state;
                    super();
                }
            }

            _cls1 _lcls1 = new _cls1();
            socketaddress = new OkHttpClientTransport((InetSocketAddress)socketaddress, s, s1, executor, socketFactory, hostnameVerifier, connectionSpec, maxMessageSize, proxyparameters, _lcls1, new TransportTracer(transportTracerFactory.timeProvider));
            if (enableKeepAlive)
            {
                long l = keepAliveTimeNanosState.savedValue;
                long l1 = keepAliveTimeoutNanos;
                boolean flag = keepAliveWithoutCalls;
                socketaddress.enableKeepAlive = true;
                socketaddress.keepAliveTimeNanos = l;
                socketaddress.keepAliveTimeoutNanos = l1;
                socketaddress.keepAliveWithoutCalls = flag;
            }
            return socketaddress;
        }

        OkHttpTransportFactory(Executor executor1, ScheduledExecutorService scheduledexecutorservice, SSLSocketFactory sslsocketfactory, HostnameVerifier hostnameverifier, io.grpc.okhttp.internal.ConnectionSpec connectionspec, int i, boolean flag, 
                long l, long l1, boolean flag1, io.grpc.internal.TransportTracer.Factory factory)
        {
            boolean flag2;
            if (scheduledexecutorservice == null)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            usingSharedScheduler = flag2;
            if (usingSharedScheduler)
            {
                scheduledexecutorservice = GrpcUtil.TIMER_SERVICE;
                scheduledexecutorservice = (ScheduledExecutorService)SharedResourceHolder.holder.getInternal(scheduledexecutorservice);
            }
            timeoutService = scheduledexecutorservice;
            socketFactory = sslsocketfactory;
            hostnameVerifier = hostnameverifier;
            connectionSpec = connectionspec;
            maxMessageSize = i;
            enableKeepAlive = flag;
            keepAliveTimeNanos = new AtomicBackoff("keepalive time nanos", l);
            keepAliveTimeoutNanos = l1;
            keepAliveWithoutCalls = flag1;
            if (executor1 == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            usingSharedExecutor = flag;
            if (factory == null)
            {
                throw new NullPointerException(String.valueOf("transportTracerFactory"));
            }
            transportTracerFactory = (io.grpc.internal.TransportTracer.Factory)factory;
            if (usingSharedExecutor)
            {
                executor1 = OkHttpChannelBuilder.SHARED_EXECUTOR;
                executor = (Executor)SharedResourceHolder.holder.getInternal(executor1);
                return;
            } else
            {
                executor = executor1;
                return;
            }
        }
    }


    private class _cls1
        implements io.grpc.internal.SharedResourceHolder.Resource
    {

        public final void close(Object obj)
        {
            ((ExecutorService)obj).shutdown();
        }

        public final Object create()
        {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-okhttp-%d", true));
        }

        _cls1()
        {
        }
    }

}
