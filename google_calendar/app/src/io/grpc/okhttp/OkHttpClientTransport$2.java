// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import com.google.common.util.concurrent.AbstractFuture;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.Grpc;
import io.grpc.SecurityLevel;
import io.grpc.StatusException;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ProxyParameters;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Http2;
import io.grpc.okhttp.internal.framed.Settings;
import io.grpc.okhttp.internal.framed.Variant;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.util.concurrent.Executor;
import javax.net.ssl.SSLSocket;
import okio.Okio;

// Referenced classes of package io.grpc.okhttp:
//            OkHttpClientTransport, AsyncFrameWriter, OkHttpTlsUpgrader

final class this._cls0
    implements Runnable
{

    private final OkHttpClientTransport this$0;

    public final void run()
    {
        boolean flag = false;
        if (address == null)
        {
            flag = true;
        }
        if (flag)
        {
            Object obj = connectingCallback;
            obj = OkHttpClientTransport.this;
            OkHttpClientTransport okhttpclienttransport = OkHttpClientTransport.this;
            OkHttpClientTransport okhttpclienttransport2 = OkHttpClientTransport.this;
            obj.clientFrameHandler = new ientFrameHandler(okhttpclienttransport, null);
            executor.execute(clientFrameHandler);
            synchronized (lock)
            {
                maxConcurrentStreams = 0x7fffffff;
                startPendingStreams();
            }
            obj1 = frameWriter;
            okhttpclienttransport = OkHttpClientTransport.this;
            ((AsyncFrameWriter) (obj1)).becomeConnected(null, socket);
            connectedFuture.set(null);
            return;
        }
        break MISSING_BLOCK_LABEL_148;
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
        Object obj2;
        Object obj3;
        Http2 http2;
        class _cls1
            implements Source
        {

            public final void close()
            {
            }

            public final long read(Buffer buffer, long l)
            {
                return -1L;
            }

            _cls1()
            {
            }
        }

        obj2 = Okio.buffer(new _cls1());
        http2 = new Http2();
        obj3 = obj2;
        if (proxy != null) goto _L2; else goto _L1
_L1:
        obj3 = obj2;
        Object obj5 = new Socket(address.getAddress(), address.getPort());
_L11:
        obj3 = obj2;
        if (sslSocketFactory == null)
        {
            break MISSING_BLOCK_LABEL_926;
        }
        obj3 = obj2;
        Object obj7 = sslSocketFactory;
        obj3 = obj2;
        Object obj8 = hostnameVerifier;
        obj3 = obj2;
        Object obj6 = OkHttpClientTransport.this;
        obj3 = obj2;
        Object obj9 = GrpcUtil.authorityToUri(((OkHttpClientTransport) (obj6)).defaultAuthority);
        obj3 = obj2;
        if (((URI) (obj9)).getHost() == null) goto _L4; else goto _L3
_L3:
        obj3 = obj2;
        obj6 = ((URI) (obj9)).getHost();
_L12:
        obj3 = obj2;
        obj9 = OkHttpClientTransport.this;
        obj3 = obj2;
        Object obj10 = GrpcUtil.authorityToUri(((OkHttpClientTransport) (obj9)).defaultAuthority);
        obj3 = obj2;
        if (((URI) (obj10)).getPort() == -1) goto _L6; else goto _L5
_L5:
        obj3 = obj2;
        int i = ((URI) (obj10)).getPort();
_L13:
        obj3 = obj2;
        obj6 = OkHttpTlsUpgrader.upgrade(((javax.net.ssl.SSLSocketFactory) (obj7)), ((javax.net.ssl.HostnameVerifier) (obj8)), ((Socket) (obj5)), ((String) (obj6)), i, connectionSpec);
        obj3 = obj2;
        obj5 = ((SSLSocket) (obj6)).getSession();
_L21:
        obj3 = obj2;
        ((Socket) (obj6)).setTcpNoDelay(true);
        obj3 = obj2;
        obj7 = Okio.buffer(Okio.source(((Socket) (obj6))));
        obj2 = obj7;
        obj3 = obj2;
        obj8 = Okio.buffer(Okio.sink(((Socket) (obj6))));
        obj3 = obj2;
        obj9 = OkHttpClientTransport.this;
        obj3 = obj2;
        obj10 = Attributes.newBuilder().port(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, ((Socket) (obj6)).getRemoteSocketAddress()).ddress(Grpc.TRANSPORT_ATTR_SSL_SESSION, obj5);
        obj3 = obj2;
        io.grpc.onSpec onspec = CallCredentials.ATTR_SECURITY_LEVEL;
        if (obj5 != null) goto _L8; else goto _L7
_L7:
        obj3 = obj2;
        obj7 = SecurityLevel.NONE;
_L14:
        obj3 = obj2;
        obj9.attributes = ((io.grpc.port.attributes) (obj10)).attributes(onspec, obj7).();
        clientFrameHandler = new ientFrameHandler(OkHttpClientTransport.this, http2.newReader(((okio.BufferedSource) (obj2)), true));
        executor.execute(clientFrameHandler);
        obj2 = lock;
        obj2;
        JVM INSTR monitorenter ;
        obj3 = OkHttpClientTransport.this;
        if (obj6 != null) goto _L10; else goto _L9
_L9:
        throw new NullPointerException(String.valueOf("socket"));
        obj3;
        obj2;
        JVM INSTR monitorexit ;
        throw obj3;
_L2:
        obj3 = obj2;
        obj5 = createHttpProxySocket(address, proxy.proxyAddress, proxy.username, proxy.password);
          goto _L11
_L4:
        obj3 = obj2;
        obj6 = ((OkHttpClientTransport) (obj6)).defaultAuthority;
          goto _L12
_L6:
        obj3 = obj2;
        i = ((OkHttpClientTransport) (obj9)).address.getPort();
          goto _L13
_L8:
        obj3 = obj2;
        obj7 = SecurityLevel.PRIVACY_AND_INTEGRITY;
          goto _L14
        obj5;
_L20:
        obj3 = obj2;
        startGoAway(0, ErrorCode.INTERNAL_ERROR, ((StatusException) (obj5)).status);
        clientFrameHandler = new ientFrameHandler(OkHttpClientTransport.this, http2.newReader(((okio.BufferedSource) (obj2)), true));
        executor.execute(clientFrameHandler);
        return;
        obj5;
_L18:
        obj3 = obj2;
        onException(((Throwable) (obj5)));
        clientFrameHandler = new ientFrameHandler(OkHttpClientTransport.this, http2.newReader(((okio.BufferedSource) (obj2)), true));
        executor.execute(clientFrameHandler);
        return;
        obj2;
_L16:
        clientFrameHandler = new ientFrameHandler(OkHttpClientTransport.this, http2.newReader(((okio.BufferedSource) (obj3)), true));
        executor.execute(clientFrameHandler);
        throw obj2;
_L10:
        obj3.socket = (Socket)obj6;
        maxConcurrentStreams = 0x7fffffff;
        startPendingStreams();
        if (obj5 == null)
        {
            break MISSING_BLOCK_LABEL_853;
        }
        OkHttpClientTransport okhttpclienttransport1 = OkHttpClientTransport.this;
        new io.grpc.internal.(new io.grpc.internal.(((javax.net.ssl.SSLSession) (obj5))));
        obj2;
        JVM INSTR monitorexit ;
        obj2 = http2.newWriter(((okio.BufferedSink) (obj8)), true);
        frameWriter.becomeConnected(((FrameWriter) (obj2)), socket);
        try
        {
            ((FrameWriter) (obj2)).connectionPreface();
            ((FrameWriter) (obj2)).settings(new Settings());
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj2)
        {
            onException(((Throwable) (obj2)));
        }
        return;
        obj2;
        if (true) goto _L16; else goto _L15
_L15:
        obj5;
        if (true) goto _L18; else goto _L17
_L17:
        obj5;
        if (true) goto _L20; else goto _L19
_L19:
        Object obj4 = null;
        obj6 = obj5;
        obj5 = obj4;
          goto _L21
    }

    ()
    {
        this$0 = OkHttpClientTransport.this;
        super();
    }
}
