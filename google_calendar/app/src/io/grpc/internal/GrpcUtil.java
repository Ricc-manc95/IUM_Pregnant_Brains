// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.Status;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ProxyDetectorImpl, AbstractSubchannel, FailingClientTransport, ProxyDetector, 
//            ClientTransport

public final class GrpcUtil
{
    static final class AcceptEncodingMarshaller
        implements io.grpc.InternalMetadata.TrustedAsciiMarshaller
    {

        public final Object parseAsciiString(byte abyte0[])
        {
            return abyte0;
        }

        public final byte[] toAsciiString(Object obj)
        {
            return (byte[])obj;
        }

        AcceptEncodingMarshaller()
        {
        }
    }

    public static final class Http2Error extends Enum
    {

        private static final Http2Error $VALUES[];
        private static final Http2Error CANCEL;
        private static final Http2Error COMPRESSION_ERROR;
        private static final Http2Error CONNECT_ERROR;
        private static final Http2Error ENHANCE_YOUR_CALM;
        private static final Http2Error FLOW_CONTROL_ERROR;
        private static final Http2Error FRAME_SIZE_ERROR;
        private static final Http2Error HTTP_1_1_REQUIRED;
        private static final Http2Error INADEQUATE_SECURITY;
        private static final Http2Error INTERNAL_ERROR;
        private static final Http2Error NO_ERROR;
        private static final Http2Error PROTOCOL_ERROR;
        private static final Http2Error REFUSED_STREAM;
        private static final Http2Error SETTINGS_TIMEOUT;
        private static final Http2Error STREAM_CLOSED;
        private static final Http2Error codeMap[];
        private final int code;
        private final Status status;

        public static Status statusForCode(long l)
        {
            Http2Error http2error;
            if (l >= (long)codeMap.length || l < 0L)
            {
                http2error = null;
            } else
            {
                http2error = codeMap[(int)l];
            }
            if (http2error == null)
            {
                return Status.fromCodeValue(INTERNAL_ERROR.status.code.value).withDescription((new StringBuilder(52)).append("Unrecognized HTTP/2 error code: ").append(l).toString());
            } else
            {
                return http2error.status;
            }
        }

        public static Http2Error[] values()
        {
            return (Http2Error[])$VALUES.clone();
        }

        static 
        {
            int i = 0;
            NO_ERROR = new Http2Error("NO_ERROR", 0, 0, Status.UNAVAILABLE);
            PROTOCOL_ERROR = new Http2Error("PROTOCOL_ERROR", 1, 1, Status.INTERNAL);
            INTERNAL_ERROR = new Http2Error("INTERNAL_ERROR", 2, 2, Status.INTERNAL);
            FLOW_CONTROL_ERROR = new Http2Error("FLOW_CONTROL_ERROR", 3, 3, Status.INTERNAL);
            SETTINGS_TIMEOUT = new Http2Error("SETTINGS_TIMEOUT", 4, 4, Status.INTERNAL);
            STREAM_CLOSED = new Http2Error("STREAM_CLOSED", 5, 5, Status.INTERNAL);
            FRAME_SIZE_ERROR = new Http2Error("FRAME_SIZE_ERROR", 6, 6, Status.INTERNAL);
            REFUSED_STREAM = new Http2Error("REFUSED_STREAM", 7, 7, Status.UNAVAILABLE);
            CANCEL = new Http2Error("CANCEL", 8, 8, Status.CANCELLED);
            COMPRESSION_ERROR = new Http2Error("COMPRESSION_ERROR", 9, 9, Status.INTERNAL);
            CONNECT_ERROR = new Http2Error("CONNECT_ERROR", 10, 10, Status.INTERNAL);
            ENHANCE_YOUR_CALM = new Http2Error("ENHANCE_YOUR_CALM", 11, 11, Status.RESOURCE_EXHAUSTED.withDescription("Bandwidth exhausted"));
            INADEQUATE_SECURITY = new Http2Error("INADEQUATE_SECURITY", 12, 12, Status.PERMISSION_DENIED.withDescription("Permission denied as protocol is not secure enough to call"));
            HTTP_1_1_REQUIRED = new Http2Error("HTTP_1_1_REQUIRED", 13, 13, Status.UNKNOWN);
            $VALUES = (new Http2Error[] {
                NO_ERROR, PROTOCOL_ERROR, INTERNAL_ERROR, FLOW_CONTROL_ERROR, SETTINGS_TIMEOUT, STREAM_CLOSED, FRAME_SIZE_ERROR, REFUSED_STREAM, CANCEL, COMPRESSION_ERROR, 
                CONNECT_ERROR, ENHANCE_YOUR_CALM, INADEQUATE_SECURITY, HTTP_1_1_REQUIRED
            });
            Http2Error ahttp2error[] = values();
            Http2Error ahttp2error1[] = new Http2Error[ahttp2error[ahttp2error.length - 1].code + 1];
            for (int j = ahttp2error.length; i < j; i++)
            {
                Http2Error http2error = ahttp2error[i];
                ahttp2error1[http2error.code] = http2error;
            }

            codeMap = ahttp2error1;
        }

        private Http2Error(String s, int i, int j, Status status1)
        {
            super(s, i);
            code = j;
            s = String.valueOf(name());
            if (s.length() != 0)
            {
                s = "HTTP/2 error code: ".concat(s);
            } else
            {
                s = new String("HTTP/2 error code: ");
            }
            status = status1.augmentDescription(s);
        }
    }

    static final class TimeoutMarshaller
        implements io.grpc.Metadata.AsciiMarshaller
    {

        public final Object parseAsciiString(String s)
        {
            boolean flag;
            if (s.length() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("empty timeout"));
            }
            if (s.length() <= 9)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("bad timeout format"));
            }
            long l = Long.parseLong(s.substring(0, s.length() - 1));
            char c = s.charAt(s.length() - 1);
            switch (c)
            {
            default:
                throw new IllegalArgumentException(String.format("Invalid timeout unit: %s", new Object[] {
                    Character.valueOf(c)
                }));

            case 110: // 'n'
                return Long.valueOf(l);

            case 117: // 'u'
                return Long.valueOf(TimeUnit.MICROSECONDS.toNanos(l));

            case 109: // 'm'
                return Long.valueOf(TimeUnit.MILLISECONDS.toNanos(l));

            case 83: // 'S'
                return Long.valueOf(TimeUnit.SECONDS.toNanos(l));

            case 77: // 'M'
                return Long.valueOf(TimeUnit.MINUTES.toNanos(l));

            case 72: // 'H'
                return Long.valueOf(TimeUnit.HOURS.toNanos(l));
            }
        }

        public final String toAsciiString(Object obj)
        {
            obj = (Long)obj;
            TimeUnit timeunit = TimeUnit.NANOSECONDS;
            if (((Long) (obj)).longValue() < 0L)
            {
                throw new IllegalArgumentException("Timeout too small");
            }
            if (((Long) (obj)).longValue() < 0x5f5e100L)
            {
                obj = String.valueOf(obj);
                return (new StringBuilder(String.valueOf(obj).length() + 1)).append(((String) (obj))).append("n").toString();
            }
            if (((Long) (obj)).longValue() < 0x174876e800L)
            {
                long l = timeunit.toMicros(((Long) (obj)).longValue());
                return (new StringBuilder(21)).append(l).append("u").toString();
            }
            if (((Long) (obj)).longValue() < 0x5af3107a4000L)
            {
                long l1 = timeunit.toMillis(((Long) (obj)).longValue());
                return (new StringBuilder(21)).append(l1).append("m").toString();
            }
            if (((Long) (obj)).longValue() < 0x16345785d8a0000L)
            {
                long l2 = timeunit.toSeconds(((Long) (obj)).longValue());
                return (new StringBuilder(21)).append(l2).append("S").toString();
            }
            if (((Long) (obj)).longValue() < 0x53444835ec580000L)
            {
                long l3 = timeunit.toMinutes(((Long) (obj)).longValue());
                return (new StringBuilder(21)).append(l3).append("M").toString();
            } else
            {
                long l4 = timeunit.toHours(((Long) (obj)).longValue());
                return (new StringBuilder(21)).append(l4).append("H").toString();
            }
        }

        TimeoutMarshaller()
        {
        }
    }


    public static final io.grpc.Metadata.Key CONTENT_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf("accept-encoding", new AcceptEncodingMarshaller());
    public static final io.grpc.Metadata.Key CONTENT_ENCODING_KEY;
    public static final io.grpc.Metadata.Key CONTENT_TYPE_KEY;
    public static final long DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
    public static final ProxyDetector DEFAULT_PROXY_DETECTOR;
    public static final boolean IS_RESTRICTED_APPENGINE;
    public static final io.grpc.Metadata.Key MESSAGE_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf("grpc-accept-encoding", new AcceptEncodingMarshaller());
    public static final io.grpc.Metadata.Key MESSAGE_ENCODING_KEY;
    public static final ProxyDetector NOOP_PROXY_DETECTOR;
    public static final SharedResourceHolder.Resource SHARED_CHANNEL_EXECUTOR;
    public static final Supplier STOPWATCH_SUPPLIER;
    public static final io.grpc.Metadata.Key TE_HEADER;
    public static final io.grpc.Metadata.Key TIMEOUT_KEY = io.grpc.Metadata.Key.of("grpc-timeout", new TimeoutMarshaller());
    public static final SharedResourceHolder.Resource TIMER_SERVICE;
    public static final io.grpc.Metadata.Key USER_AGENT_KEY;
    private static final Logger log = Logger.getLogger(io/grpc/internal/GrpcUtil.getName());

    private GrpcUtil()
    {
    }

    public static String authorityFromHostAndPort(String s, int i)
    {
        String s1;
        try
        {
            s1 = (new URI(null, null, s, i, null, null, null)).getAuthority();
        }
        catch (URISyntaxException urisyntaxexception)
        {
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 34)).append("Invalid host or port: ").append(s).append(" ").append(i).toString(), urisyntaxexception);
        }
        return s1;
    }

    public static URI authorityToUri(String s)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("authority"));
        }
        URI uri;
        try
        {
            uri = new URI(null, s, null, null, null);
        }
        catch (URISyntaxException urisyntaxexception)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Invalid authority: ".concat(s);
            } else
            {
                s = new String("Invalid authority: ");
            }
            throw new IllegalArgumentException(s, urisyntaxexception);
        }
        return uri;
    }

    static void closeQuietly(StreamListener.MessageProducer messageproducer)
    {
        do
        {
            InputStream inputstream = messageproducer.next();
            if (inputstream != null)
            {
                closeQuietly(inputstream);
            } else
            {
                return;
            }
        } while (true);
    }

    public static void closeQuietly(InputStream inputstream)
    {
        if (inputstream == null)
        {
            return;
        }
        try
        {
            inputstream.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            log.logp(Level.WARNING, "io.grpc.internal.GrpcUtil", "closeQuietly", "exception caught in closeQuietly", inputstream);
        }
    }

    public static String getGrpcUserAgent(String s, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (s1 != null)
        {
            stringbuilder.append(s1);
            stringbuilder.append(' ');
        }
        stringbuilder.append("grpc-java-");
        stringbuilder.append(s);
        stringbuilder.append('/');
        stringbuilder.append("1.14.0-SNAPSHOT");
        return stringbuilder.toString();
    }

    public static String getHost(InetSocketAddress inetsocketaddress)
    {
        String s = (String)java/net/InetSocketAddress.getMethod("getHostString", new Class[0]).invoke(inetsocketaddress, new Object[0]);
        return s;
        Object obj;
        obj;
_L2:
        return inetsocketaddress.getHostName();
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static ThreadFactory getThreadFactory(String s, boolean flag)
    {
        if (IS_RESTRICTED_APPENGINE)
        {
            return MoreExecutors.platformThreadFactory();
        } else
        {
            ThreadFactoryBuilder threadfactorybuilder = new ThreadFactoryBuilder();
            threadfactorybuilder.daemon = Boolean.valueOf(true);
            String.format(Locale.ROOT, s, new Object[] {
                Integer.valueOf(0)
            });
            threadfactorybuilder.nameFormat = s;
            return threadfactorybuilder.build();
        }
    }

    static ClientTransport getTransportFromPickResult(final io.grpc.LoadBalancer.PickResult streamTracerFactory, boolean flag)
    {
        final ClientTransport transport = streamTracerFactory.subchannel;
        if (transport != null)
        {
            transport = ((AbstractSubchannel)transport).obtainActiveTransport();
        } else
        {
            transport = null;
        }
        if (transport != null)
        {
            streamTracerFactory = streamTracerFactory.streamTracerFactory;
            if (streamTracerFactory == null)
            {
                return transport;
            } else
            {
                return new _cls5();
            }
        }
        transport = streamTracerFactory.status;
        boolean flag1;
        if (io.grpc.Status.Code.OK == ((Status) (transport)).code)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            if (streamTracerFactory.drop)
            {
                return new FailingClientTransport(streamTracerFactory.status, android.support.v4.content.ModernAsyncTask.Status.DROPPED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0);
            }
            if (!flag)
            {
                return new FailingClientTransport(streamTracerFactory.status, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0);
            }
        }
        return null;
    }

    public static Status httpStatusToGrpcStatus(int i)
    {
        if (i < 100 || i >= 200) goto _L2; else goto _L1
_L1:
        io.grpc.Status.Code code = io.grpc.Status.Code.INTERNAL;
_L4:
        return ((Status)Status.STATUS_LIST.get(code.value)).withDescription((new StringBuilder(28)).append("HTTP status code ").append(i).toString());
_L2:
        switch (i)
        {
        default:
            code = io.grpc.Status.Code.UNKNOWN;
            break;

        case 400: 
        case 431: 
            code = io.grpc.Status.Code.INTERNAL;
            break;

        case 401: 
            code = io.grpc.Status.Code.UNAUTHENTICATED;
            break;

        case 403: 
            code = io.grpc.Status.Code.PERMISSION_DENIED;
            break;

        case 404: 
            code = io.grpc.Status.Code.UNIMPLEMENTED;
            break;

        case 429: 
        case 502: 
        case 503: 
        case 504: 
            code = io.grpc.Status.Code.UNAVAILABLE;
            break;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isGrpcContentType(String s)
    {
        if (s != null && 16 <= s.length())
        {
            s = s.toLowerCase();
            if (s.startsWith("application/grpc"))
            {
                if (s.length() == 16)
                {
                    return true;
                }
                char c = s.charAt(16);
                if (c == '+' || c == ';')
                {
                    return true;
                }
            }
        }
        return false;
    }

    static 
    {
        Charset.forName("US-ASCII");
        Splitter splitter;
        CharMatcher charmatcher;
        boolean flag;
        if (System.getProperty("com.google.appengine.runtime.environment") != null && "1.7".equals(System.getProperty("java.specification.version")))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        IS_RESTRICTED_APPENGINE = flag;
        MESSAGE_ENCODING_KEY = io.grpc.Metadata.Key.of("grpc-encoding", Metadata.ASCII_STRING_MARSHALLER);
        CONTENT_ENCODING_KEY = io.grpc.Metadata.Key.of("content-encoding", Metadata.ASCII_STRING_MARSHALLER);
        CONTENT_TYPE_KEY = io.grpc.Metadata.Key.of("content-type", Metadata.ASCII_STRING_MARSHALLER);
        TE_HEADER = io.grpc.Metadata.Key.of("te", Metadata.ASCII_STRING_MARSHALLER);
        USER_AGENT_KEY = io.grpc.Metadata.Key.of("user-agent", Metadata.ASCII_STRING_MARSHALLER);
        splitter = Splitter.on(',');
        charmatcher = CharMatcher.whitespace();
        if (charmatcher == null)
        {
            throw new NullPointerException();
        } else
        {
            new Splitter(splitter.strategy, splitter.omitEmptyStrings, charmatcher, splitter.limit);
            TimeUnit.MINUTES.toNanos(1L);
            DEFAULT_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20L);
            TimeUnit.HOURS.toNanos(2L);
            TimeUnit.SECONDS.toNanos(20L);
            DEFAULT_PROXY_DETECTOR = new ProxyDetectorImpl();
            NOOP_PROXY_DETECTOR = new _cls1();
            SHARED_CHANNEL_EXECUTOR = new _cls2();
            TIMER_SERVICE = new _cls3();
            STOPWATCH_SUPPLIER = new _cls4();
        }
    }

    private class _cls5
        implements ClientTransport
    {

        private final io.grpc.ClientStreamTracer.Factory val$streamTracerFactory;
        private final ClientTransport val$transport;

        public final LogId getLogId()
        {
            return transport.getLogId();
        }

        public final ClientStream newStream(MethodDescriptor methoddescriptor, Metadata metadata, CallOptions calloptions)
        {
            return transport.newStream(methoddescriptor, metadata, calloptions.withStreamTracerFactory(streamTracerFactory));
        }

        public final void ping(ClientTransport.PingCallback pingcallback, Executor executor)
        {
            transport.ping(pingcallback, executor);
        }

        _cls5()
        {
            transport = clienttransport;
            streamTracerFactory = factory;
            super();
        }
    }


    private class _cls1
        implements ProxyDetector
    {

        public final ProxyParameters proxyFor(SocketAddress socketaddress)
        {
            return null;
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements SharedResourceHolder.Resource
    {

        public final void close(Object obj)
        {
            ((ExecutorService)obj).shutdown();
        }

        public final Object create()
        {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-default-executor-%d", true));
        }

        public final String toString()
        {
            return "grpc-default-executor";
        }

        _cls2()
        {
        }
    }


    private class _cls3
        implements SharedResourceHolder.Resource
    {

        private static ScheduledExecutorService create()
        {
            Object obj = Executors.newScheduledThreadPool(1, GrpcUtil.getThreadFactory("grpc-timer-%d", true));
            try
            {
                obj.getClass().getMethod("setRemoveOnCancelPolicy", new Class[] {
                    Boolean.TYPE
                }).invoke(obj, new Object[] {
                    Boolean.valueOf(true)
                });
            }
            catch (NoSuchMethodException nosuchmethodexception)
            {
                return ((ScheduledExecutorService) (obj));
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                throw new RuntimeException(((Throwable) (obj)));
            }
            return ((ScheduledExecutorService) (obj));
        }

        public final void close(Object obj)
        {
            ((ScheduledExecutorService)obj).shutdown();
        }

        public final volatile Object create()
        {
            return create();
        }

        _cls3()
        {
        }
    }


    private class _cls4
        implements Supplier
    {

        public final Object get()
        {
            return new Stopwatch();
        }

        _cls4()
        {
        }
    }

}
