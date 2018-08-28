// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.SettableFuture;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.internal.http.StatusLine;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import io.grpc.internal.AbstractClientStream;
import io.grpc.internal.ApplicationThreadDeframer;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.Deframer;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.GzipInflatingBuffer;
import io.grpc.internal.Http2ClientStreamTransportState;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.LogId;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.ProxyParameters;
import io.grpc.internal.SerializingExecutor;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameReader;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Segment;
import okio.Source;
import okio.Util;

// Referenced classes of package io.grpc.okhttp:
//            OkHttpClientStream, AsyncFrameWriter, OutboundFlowController, Utils

class OkHttpClientTransport
    implements ConnectionClientTransport
{
    final class ClientFrameHandler
        implements io.grpc.okhttp.internal.framed.FrameReader.Handler, Runnable
    {

        private boolean firstSettings;
        private FrameReader frameReader;
        private final OkHttpClientTransport this$0;

        public final void ackSettings()
        {
        }

        public final void data(boolean flag, int i, BufferedSource bufferedsource, int j)
            throws IOException
        {
            Object obj = getStream(i);
            if (obj != null) goto _L2; else goto _L1
_L1:
            if (!mayHaveCreatedStream(i)) goto _L4; else goto _L3
_L3:
            obj = frameWriter;
            ErrorCode errorcode = ErrorCode.INVALID_STREAM;
            ((AsyncFrameWriter) (obj)).executor.execute(new AsyncFrameWriter._cls8(((AsyncFrameWriter) (obj)), i, errorcode));
            bufferedsource.skip(j);
_L6:
            bufferedsource = OkHttpClientTransport.this;
            bufferedsource.connectionUnacknowledgedBytesRead = ((OkHttpClientTransport) (bufferedsource)).connectionUnacknowledgedBytesRead + j;
            if (connectionUnacknowledgedBytesRead >= 32767)
            {
                bufferedsource = frameWriter;
                long l = connectionUnacknowledgedBytesRead;
                ((AsyncFrameWriter) (bufferedsource)).executor.execute(new AsyncFrameWriter._cls13(bufferedsource, 0, l));
                connectionUnacknowledgedBytesRead = 0;
            }
            return;
_L4:
            onError(ErrorCode.PROTOCOL_ERROR, (new StringBuilder(45)).append("Received data for unknown stream: ").append(i).toString());
            return;
_L2:
            bufferedsource.require(j);
            Buffer buffer = new Buffer();
            buffer.write(bufferedsource.buffer(), j);
            synchronized (lock)
            {
                ((OkHttpClientStream) (obj)).state.transportDataReceived(buffer, flag);
            }
            if (true) goto _L6; else goto _L5
_L5:
            exception;
            bufferedsource;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public final void goAway(int i, ErrorCode errorcode, ByteString bytestring)
        {
            if (errorcode == ErrorCode.ENHANCE_YOUR_CALM)
            {
                String s = bytestring.utf8();
                OkHttpClientTransport.log.logp(Level.WARNING, "io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler", "goAway", String.format("%s: Received GOAWAY with ENHANCE_YOUR_CALM. Debug data: %s", new Object[] {
                    this, s
                }));
                if ("too_many_pings".equals(s))
                {
                    tooManyPingsRunnable.run();
                }
            }
            Status status = io.grpc.internal.GrpcUtil.Http2Error.statusForCode(errorcode.httpCode).augmentDescription("Received Goaway");
            errorcode = status;
            if (bytestring.size() > 0)
            {
                errorcode = status.augmentDescription(bytestring.utf8());
            }
            startGoAway(i, null, errorcode);
        }

        public final void headers$51D5KIA99HL62TJ15TQN8QBC5T66ISRK7D66IRPFCTP70OPFDTLMGT3KE0NMIRJKCLP6SOBC5TJ74OBDCLI2UI35C5I6ASJJ9LNM8P9R55B0____0$51D5KIA99HL62TJ15TQN8QBC5T66ISRK7D4IILG_0(boolean flag, int i, List list)
        {
            Object obj = lock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = (OkHttpClientStream)streams.get(Integer.valueOf(i));
            if (obj1 != null) goto _L2; else goto _L1
_L1:
            if (!mayHaveCreatedStream(i)) goto _L4; else goto _L3
_L3:
            list = frameWriter;
            obj1 = ErrorCode.INVALID_STREAM;
            ((AsyncFrameWriter) (list)).executor.execute(new AsyncFrameWriter._cls8(list, i, ((ErrorCode) (obj1))));
            int j = 0;
_L5:
            obj;
            JVM INSTR monitorexit ;
            if (j != 0)
            {
                onError(ErrorCode.PROTOCOL_ERROR, (new StringBuilder(47)).append("Received header for unknown stream: ").append(i).toString());
            }
            return;
_L4:
            j = 1;
              goto _L5
_L2:
            obj1 = ((OkHttpClientStream) (obj1)).state;
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_530;
            }
            Object obj2 = Utils.convertTrailers(list);
            if (obj2 != null)
            {
                break MISSING_BLOCK_LABEL_170;
            }
            throw new NullPointerException(String.valueOf("trailers"));
            list;
            obj;
            JVM INSTR monitorexit ;
            throw list;
            if (((Http2ClientStreamTransportState) (obj1)).transportError == null && !((Http2ClientStreamTransportState) (obj1)).headersReceived)
            {
                obj1.transportError = Http2ClientStreamTransportState.validateInitialMetadata(((Metadata) (obj2)));
                if (((Http2ClientStreamTransportState) (obj1)).transportError != null)
                {
                    obj1.transportErrorMetadata = ((Metadata) (obj2));
                }
            }
            if (((Http2ClientStreamTransportState) (obj1)).transportError == null)
            {
                break MISSING_BLOCK_LABEL_295;
            }
            list = ((Http2ClientStreamTransportState) (obj1)).transportError;
            obj2 = String.valueOf(obj2);
            obj1.transportError = list.augmentDescription((new StringBuilder(String.valueOf(obj2).length() + 10)).append("trailers: ").append(((String) (obj2))).toString());
            ((Http2ClientStreamTransportState) (obj1)).http2ProcessingFailed(((Http2ClientStreamTransportState) (obj1)).transportError, false, ((Http2ClientStreamTransportState) (obj1)).transportErrorMetadata);
            j = 0;
              goto _L5
            list = (Status)((Metadata) (obj2)).get(InternalStatus.CODE_KEY);
            if (list == null) goto _L7; else goto _L6
_L6:
            list = list.withDescription((String)((Metadata) (obj2)).get(InternalStatus.MESSAGE_KEY));
_L10:
            ((Metadata) (obj2)).discardAll(Http2ClientStreamTransportState.HTTP2_STATUS);
            ((Metadata) (obj2)).discardAll(InternalStatus.CODE_KEY);
            ((Metadata) (obj2)).discardAll(InternalStatus.MESSAGE_KEY);
            if (list != null)
            {
                break MISSING_BLOCK_LABEL_438;
            }
            throw new NullPointerException(String.valueOf("status"));
_L7:
            if (!((Http2ClientStreamTransportState) (obj1)).headersReceived) goto _L9; else goto _L8
_L8:
            list = Status.UNKNOWN.withDescription("missing GRPC status in response");
              goto _L10
_L9:
            list = (Integer)((Metadata) (obj2)).get(Http2ClientStreamTransportState.HTTP2_STATUS);
            if (list == null)
            {
                break MISSING_BLOCK_LABEL_425;
            }
            list = GrpcUtil.httpStatusToGrpcStatus(list.intValue());
_L11:
            list = list.augmentDescription("missing GRPC status, inferred error from HTTP status code");
              goto _L10
            list = Status.INTERNAL.withDescription("missing HTTP status code");
              goto _L11
            if (obj2 != null)
            {
                break MISSING_BLOCK_LABEL_456;
            }
            throw new NullPointerException(String.valueOf("trailers"));
            if (!((io.grpc.internal.AbstractClientStream.TransportState) (obj1)).statusReported)
            {
                break MISSING_BLOCK_LABEL_501;
            }
            AbstractClientStream.log.logp(Level.INFO, "io.grpc.internal.AbstractClientStream$TransportState", "inboundTrailersReceived", "Received trailers on closed stream:\n {1}\n {2}", new Object[] {
                list, obj2
            });
            j = 0;
              goto _L5
            obj1.trailers = ((Metadata) (obj2));
            obj1.trailerStatus = list;
            ((io.grpc.internal.AbstractStream.TransportState) (obj1)).deframer.closeWhenComplete();
            j = 0;
              goto _L5
            obj2 = Utils.convertHeaders(list);
            if (obj2 != null)
            {
                break MISSING_BLOCK_LABEL_555;
            }
            throw new NullPointerException(String.valueOf("headers"));
            if (((Http2ClientStreamTransportState) (obj1)).transportError == null)
            {
                break MISSING_BLOCK_LABEL_623;
            }
            list = ((Http2ClientStreamTransportState) (obj1)).transportError;
            obj2 = String.valueOf(obj2);
            obj1.transportError = list.augmentDescription((new StringBuilder(String.valueOf(obj2).length() + 9)).append("headers: ").append(((String) (obj2))).toString());
            j = 0;
              goto _L5
            if (!((Http2ClientStreamTransportState) (obj1)).headersReceived)
            {
                break MISSING_BLOCK_LABEL_730;
            }
            obj1.transportError = Status.INTERNAL.withDescription("Received headers twice");
            if (((Http2ClientStreamTransportState) (obj1)).transportError != null)
            {
                list = ((Http2ClientStreamTransportState) (obj1)).transportError;
                String s = String.valueOf(obj2);
                obj1.transportError = list.augmentDescription((new StringBuilder(String.valueOf(s).length() + 9)).append("headers: ").append(s).toString());
                obj1.transportErrorMetadata = ((Metadata) (obj2));
                obj1.errorCharset = Http2ClientStreamTransportState.extractCharset(((Metadata) (obj2)));
            }
            j = 0;
              goto _L5
            list = (Integer)((Metadata) (obj2)).get(Http2ClientStreamTransportState.HTTP2_STATUS);
            if (list == null)
            {
                break MISSING_BLOCK_LABEL_854;
            }
            if (list.intValue() < 100)
            {
                break MISSING_BLOCK_LABEL_854;
            }
            j = list.intValue();
            if (j >= 200)
            {
                break MISSING_BLOCK_LABEL_854;
            }
            if (((Http2ClientStreamTransportState) (obj1)).transportError != null)
            {
                list = ((Http2ClientStreamTransportState) (obj1)).transportError;
                String s1 = String.valueOf(obj2);
                obj1.transportError = list.augmentDescription((new StringBuilder(String.valueOf(s1).length() + 9)).append("headers: ").append(s1).toString());
                obj1.transportErrorMetadata = ((Metadata) (obj2));
                obj1.errorCharset = Http2ClientStreamTransportState.extractCharset(((Metadata) (obj2)));
            }
            j = 0;
              goto _L5
            obj1.headersReceived = true;
            obj1.transportError = Http2ClientStreamTransportState.validateInitialMetadata(((Metadata) (obj2)));
            list = ((Http2ClientStreamTransportState) (obj1)).transportError;
            if (list == null)
            {
                break MISSING_BLOCK_LABEL_965;
            }
            if (((Http2ClientStreamTransportState) (obj1)).transportError != null)
            {
                list = ((Http2ClientStreamTransportState) (obj1)).transportError;
                String s2 = String.valueOf(obj2);
                obj1.transportError = list.augmentDescription((new StringBuilder(String.valueOf(s2).length() + 9)).append("headers: ").append(s2).toString());
                obj1.transportErrorMetadata = ((Metadata) (obj2));
                obj1.errorCharset = Http2ClientStreamTransportState.extractCharset(((Metadata) (obj2)));
            }
            j = 0;
              goto _L5
            ((Metadata) (obj2)).discardAll(Http2ClientStreamTransportState.HTTP2_STATUS);
            ((Metadata) (obj2)).discardAll(InternalStatus.CODE_KEY);
            ((Metadata) (obj2)).discardAll(InternalStatus.MESSAGE_KEY);
            Status status;
            String s5;
            if (!((io.grpc.internal.AbstractClientStream.TransportState) (obj1)).statusReported)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                break MISSING_BLOCK_LABEL_1109;
            }
            throw new IllegalStateException(String.valueOf("Received headers on closed stream"));
            list;
            if (((Http2ClientStreamTransportState) (obj1)).transportError != null)
            {
                status = ((Http2ClientStreamTransportState) (obj1)).transportError;
                s5 = String.valueOf(obj2);
                obj1.transportError = status.augmentDescription((new StringBuilder(String.valueOf(s5).length() + 9)).append("headers: ").append(s5).toString());
                obj1.transportErrorMetadata = ((Metadata) (obj2));
                obj1.errorCharset = Http2ClientStreamTransportState.extractCharset(((Metadata) (obj2)));
            }
            throw list;
            list = ((io.grpc.internal.AbstractClientStream.TransportState) (obj1)).statsTraceCtx;
            list = (String)((Metadata) (obj2)).get(GrpcUtil.CONTENT_ENCODING_KEY);
            if (!((io.grpc.internal.AbstractClientStream.TransportState) (obj1)).fullStreamDecompression || list == null) goto _L13; else goto _L12
_L12:
            if (!list.equalsIgnoreCase("gzip")) goto _L15; else goto _L14
_L14:
            list = new GzipInflatingBuffer();
            ((io.grpc.internal.AbstractStream.TransportState) (obj1)).deframer.setFullStreamDecompressor(list);
            obj1.deframer = new ApplicationThreadDeframer(((io.grpc.internal.MessageDeframer.Listener) (obj1)), ((io.grpc.internal.ApplicationThreadDeframer.TransportExecutor) (obj1)), (MessageDeframer)((io.grpc.internal.AbstractStream.TransportState) (obj1)).deframer);
            j = 1;
_L25:
            String s3 = (String)((Metadata) (obj2)).get(GrpcUtil.MESSAGE_ENCODING_KEY);
            if (s3 == null) goto _L17; else goto _L16
_L16:
            list = (io.grpc.DecompressorRegistry.DecompressorInfo)((io.grpc.internal.AbstractClientStream.TransportState) (obj1)).decompressorRegistry.decompressors.get(s3);
            if (list == null)
            {
                break MISSING_BLOCK_LABEL_1489;
            }
            list = ((io.grpc.DecompressorRegistry.DecompressorInfo) (list)).decompressor;
_L26:
            if (list != null) goto _L19; else goto _L18
_L18:
            ((io.grpc.internal.AbstractClientStream.TransportState) (obj1)).deframeFailed(new StatusRuntimeException(Status.INTERNAL.withDescription(String.format("Can't find decompressor for %s", new Object[] {
                s3
            }))));
_L23:
            if (((Http2ClientStreamTransportState) (obj1)).transportError == null) goto _L21; else goto _L20
_L20:
            list = ((Http2ClientStreamTransportState) (obj1)).transportError;
            String s4 = String.valueOf(obj2);
            obj1.transportError = list.augmentDescription((new StringBuilder(String.valueOf(s4).length() + 9)).append("headers: ").append(s4).toString());
            obj1.transportErrorMetadata = ((Metadata) (obj2));
            obj1.errorCharset = Http2ClientStreamTransportState.extractCharset(((Metadata) (obj2)));
            j = 0;
              goto _L5
_L15:
            if (list.equalsIgnoreCase("identity")) goto _L13; else goto _L22
_L22:
            ((io.grpc.internal.AbstractClientStream.TransportState) (obj1)).deframeFailed(new StatusRuntimeException(Status.INTERNAL.withDescription(String.format("Can't find full stream decompressor for %s", new Object[] {
                list
            }))));
              goto _L23
_L19:
            if (list == io.grpc.Codec.Identity.NONE) goto _L17; else goto _L24
_L24:
            if (j == 0)
            {
                break MISSING_BLOCK_LABEL_1451;
            }
            ((io.grpc.internal.AbstractClientStream.TransportState) (obj1)).deframeFailed(new StatusRuntimeException(Status.INTERNAL.withDescription(String.format("Full stream and gRPC message encoding cannot both be set", new Object[0]))));
              goto _L23
            ((io.grpc.internal.AbstractStream.TransportState) (obj1)).deframer.setDecompressor(list);
_L17:
            ((io.grpc.internal.AbstractClientStream.TransportState) (obj1)).listener.headersRead(((Metadata) (obj2)));
              goto _L23
_L21:
            j = 0;
              goto _L5
_L13:
            j = 0;
              goto _L25
            list = null;
              goto _L26
        }

        public final void ping(boolean flag, int i, int j)
        {
            if (flag) goto _L2; else goto _L1
_L1:
            AsyncFrameWriter asyncframewriter = frameWriter;
            asyncframewriter.executor.execute(new AsyncFrameWriter._cls11(asyncframewriter, true, i, j));
_L6:
            return;
_L2:
            Http2Ping http2ping;
            long l;
            http2ping = null;
            l = (long)i << 32 | (long)j & 0xffffffffL;
            Object obj = lock;
            obj;
            JVM INSTR monitorenter ;
            if (OkHttpClientTransport.this.ping == null)
            {
                break MISSING_BLOCK_LABEL_180;
            }
            if (OkHttpClientTransport.this.ping.data != l) goto _L4; else goto _L3
_L3:
            http2ping = OkHttpClientTransport.this.ping;
            OkHttpClientTransport.this.ping = null;
_L7:
            if (http2ping == null) goto _L6; else goto _L5
_L5:
            http2ping.complete();
            return;
_L4:
            OkHttpClientTransport.log.logp(Level.WARNING, "io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler", "ping", String.format("Received unexpected ping ack. Expecting %d, got %d", new Object[] {
                Long.valueOf(OkHttpClientTransport.this.ping.data), Long.valueOf(l)
            }));
              goto _L7
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            OkHttpClientTransport.log.logp(Level.WARNING, "io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler", "ping", "Received unexpected ping ack. No ping outstanding");
              goto _L7
        }

        public final void priority$514KIIAQ55B0____0()
        {
        }

        public final void pushPromise$514KIJ3AC5R62BRLEHKMOBQCD5PN8EP9AO______0(int i)
            throws IOException
        {
            AsyncFrameWriter asyncframewriter = frameWriter;
            ErrorCode errorcode = ErrorCode.PROTOCOL_ERROR;
            asyncframewriter.executor.execute(new AsyncFrameWriter._cls8(asyncframewriter, i, errorcode));
        }

        public final void rstStream(int i, ErrorCode errorcode)
        {
            Status status = OkHttpClientTransport.toGrpcStatus(errorcode).augmentDescription("Rst Stream");
            OkHttpClientTransport okhttpclienttransport;
            int j;
            boolean flag;
            if (status.code == io.grpc.Status.Code.CANCELLED || status.code == io.grpc.Status.Code.DEADLINE_EXCEEDED)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            okhttpclienttransport = OkHttpClientTransport.this;
            if (errorcode == ErrorCode.REFUSED_STREAM)
            {
                j = android.support.v4.content.ModernAsyncTask.Status.REFUSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0;
            } else
            {
                j = android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0;
            }
            okhttpclienttransport.finishStream$514KOQBF5TJN4S335T9N8OBKELPJMJ39DSNMESJGCCNMIRJKCLP6SOBC5T1MOQB5DPQ56T3ICLGMQJ39EDQ6ARJ5E8I54S33A1P6UPRICLPN6EQQ9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FCPP62RB5CGNKASJIDTP46RR4CKTKOQBF5TJN4S335T6MAT31CHGN8O9R55B0____0(i, status, j, flag, null, null);
        }

        public final void run()
        {
            String s;
            s = Thread.currentThread().getName();
            if (!GrpcUtil.IS_RESTRICTED_APPENGINE)
            {
                Thread.currentThread().setName("OkHttpClientTransport");
            }
            do
            {
                if (!frameReader.nextFrame(this))
                {
                    break;
                }
                if (keepAliveManager != null)
                {
                    keepAliveManager.onDataReceived();
                }
            } while (true);
              goto _L1
            Object obj;
            obj;
            startGoAway(0, ErrorCode.PROTOCOL_ERROR, Status.UNAVAILABLE.withDescription("error in frame handler").withCause(((Throwable) (obj))));
            try
            {
                frameReader.close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                OkHttpClientTransport.log.logp(Level.INFO, "io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler", "run", "Exception closing frame reader", ((Throwable) (obj)));
            }
            listener.transportTerminated();
            if (!GrpcUtil.IS_RESTRICTED_APPENGINE)
            {
                Thread.currentThread().setName(s);
            }
            return;
_L1:
            startGoAway(0, ErrorCode.INTERNAL_ERROR, Status.UNAVAILABLE.withDescription("End of stream or IOException"));
            try
            {
                frameReader.close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                OkHttpClientTransport.log.logp(Level.INFO, "io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler", "run", "Exception closing frame reader", ((Throwable) (obj)));
            }
            listener.transportTerminated();
            if (!GrpcUtil.IS_RESTRICTED_APPENGINE)
            {
                Thread.currentThread().setName(s);
                return;
            } else
            {
                break MISSING_BLOCK_LABEL_117;
            }
            obj;
            try
            {
                frameReader.close();
            }
            catch (IOException ioexception)
            {
                OkHttpClientTransport.log.logp(Level.INFO, "io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler", "run", "Exception closing frame reader", ioexception);
            }
            listener.transportTerminated();
            if (!GrpcUtil.IS_RESTRICTED_APPENGINE)
            {
                Thread.currentThread().setName(s);
            }
            throw obj;
        }

        public final void settings$51D4OQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNMCSJ1DLIM8BQJCLQ78QBECTPJMAAM0(Settings settings)
        {
            int j = 1;
            Object obj = lock;
            obj;
            JVM INSTR monitorenter ;
            OutboundFlowController outboundflowcontroller;
            int i;
            if ((settings.set & 0x10) != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                break MISSING_BLOCK_LABEL_48;
            }
            i = settings.values[4];
            maxConcurrentStreams = i;
            if ((settings.set & 0x80) != 0)
            {
                i = j;
            } else
            {
                i = 0;
            }
            if (i == 0) goto _L2; else goto _L1
_L1:
            i = settings.values[7];
            outboundflowcontroller = outboundFlow;
            if (i >= 0)
            {
                break MISSING_BLOCK_LABEL_138;
            }
            throw new IllegalArgumentException((new StringBuilder(40)).append("Invalid initial window size: ").append(i).toString());
            settings;
            obj;
            JVM INSTR monitorexit ;
            throw settings;
            OkHttpClientStream aokhttpclientstream[];
            int k;
            j = i - outboundflowcontroller.initialWindowSize;
            outboundflowcontroller.initialWindowSize = i;
            aokhttpclientstream = outboundflowcontroller.transport.getActiveStreams();
            k = aokhttpclientstream.length;
            i = 0;
_L10:
            if (i >= k) goto _L4; else goto _L3
_L3:
            OkHttpClientStream okhttpclientstream = aokhttpclientstream[i];
            OutboundFlowController.OutboundFlowState outboundflowstate = (OutboundFlowController.OutboundFlowState)okhttpclientstream.outboundFlowState;
            if (outboundflowstate != null) goto _L6; else goto _L5
_L5:
            okhttpclientstream.outboundFlowState = new OutboundFlowController.OutboundFlowState(outboundflowcontroller, okhttpclientstream);
              goto _L7
_L6:
            outboundflowstate.incrementStreamWindow(j);
              goto _L7
_L4:
            if (j <= 0) goto _L2; else goto _L8
_L8:
            outboundflowcontroller.writeStreams();
_L2:
            if (firstSettings)
            {
                listener.transportReady();
                firstSettings = false;
            }
            startPendingStreams();
            obj;
            JVM INSTR monitorexit ;
            AsyncFrameWriter asyncframewriter = frameWriter;
            asyncframewriter.executor.execute(new AsyncFrameWriter._cls2(asyncframewriter, settings));
            return;
_L7:
            i++;
            if (true) goto _L10; else goto _L9
_L9:
        }

        public final void windowUpdate(int i, long l)
        {
            boolean flag = false;
            if (l != 0L) goto _L2; else goto _L1
_L1:
            if (i != 0) goto _L4; else goto _L3
_L3:
            onError(ErrorCode.PROTOCOL_ERROR, "Received 0 flow control window increment.");
_L8:
            return;
_L4:
            finishStream$514KOQBF5TJN4S335T9N8OBKELPJMJ39DSNMESJGCCNMIRJKCLP6SOBC5T1MOQB5DPQ56T3ICLGMQJ39EDQ6ARJ5E8I54S33A1P6UPRICLPN6EQQ9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FCPP62RB5CGNKASJIDTP46RR4CKTKOQBF5TJN4S335T6MAT31CHGN8O9R55B0____0(i, Status.INTERNAL.withDescription("Received 0 flow control window increment."), android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, false, ErrorCode.PROTOCOL_ERROR, null);
            return;
_L2:
            Object obj = lock;
            obj;
            JVM INSTR monitorenter ;
            if (i != 0)
            {
                break MISSING_BLOCK_LABEL_95;
            }
            outboundFlow.windowUpdate(null, (int)l);
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            OkHttpClientStream okhttpclientstream = (OkHttpClientStream)streams.get(Integer.valueOf(i));
            if (okhttpclientstream == null) goto _L6; else goto _L5
_L5:
            outboundFlow.windowUpdate(okhttpclientstream, (int)l);
_L9:
            obj;
            JVM INSTR monitorexit ;
            if (!flag) goto _L8; else goto _L7
_L7:
            onError(ErrorCode.PROTOCOL_ERROR, (new StringBuilder(54)).append("Received window_update for unknown stream: ").append(i).toString());
            return;
_L6:
            boolean flag1 = mayHaveCreatedStream(i);
            if (!flag1)
            {
                flag = true;
            }
              goto _L9
        }

        ClientFrameHandler(FrameReader framereader)
        {
            this$0 = OkHttpClientTransport.this;
            super();
            firstSettings = true;
            frameReader = framereader;
        }
    }


    private static final OkHttpClientStream EMPTY_STREAM_ARRAY[] = new OkHttpClientStream[0];
    private static final Map ERROR_CODE_TO_STATUS;
    public static final Logger log = Logger.getLogger(io/grpc/okhttp/OkHttpClientTransport.getName());
    public final InetSocketAddress address;
    public Attributes attributes;
    public ClientFrameHandler clientFrameHandler;
    public SettableFuture connectedFuture;
    public Runnable connectingCallback;
    public final ConnectionSpec connectionSpec;
    public int connectionUnacknowledgedBytesRead;
    public final String defaultAuthority;
    public boolean enableKeepAlive;
    public final Executor executor;
    public AsyncFrameWriter frameWriter;
    private boolean goAwaySent;
    public Status goAwayStatus;
    public HostnameVerifier hostnameVerifier;
    private boolean inUse;
    public KeepAliveManager keepAliveManager;
    public long keepAliveTimeNanos;
    public long keepAliveTimeoutNanos;
    public boolean keepAliveWithoutCalls;
    public io.grpc.internal.ManagedClientTransport.Listener listener;
    public final Object lock;
    private final LogId logId;
    public int maxConcurrentStreams;
    private final int maxMessageSize;
    private int nextStreamId;
    public OutboundFlowController outboundFlow;
    public LinkedList pendingStreams;
    public Http2Ping ping;
    public final ProxyParameters proxy;
    private final Random random;
    private ScheduledExecutorService scheduler;
    private final SerializingExecutor serializingExecutor;
    public Socket socket;
    public SSLSocketFactory sslSocketFactory;
    private boolean stopped;
    private final Supplier stopwatchFactory;
    public final Map streams;
    public final Runnable tooManyPingsRunnable;
    private final TransportTracer transportTracer;
    private final String userAgent;

    OkHttpClientTransport(InetSocketAddress inetsocketaddress, String s, String s1, Executor executor1, SSLSocketFactory sslsocketfactory, HostnameVerifier hostnameverifier, ConnectionSpec connectionspec, 
            int i, ProxyParameters proxyparameters, Runnable runnable, TransportTracer transporttracer)
    {
        random = new Random();
        lock = new Object();
        logId = new LogId(getClass().getName(), LogId.idAlloc.incrementAndGet());
        streams = new HashMap();
        attributes = Attributes.EMPTY;
        maxConcurrentStreams = 0;
        pendingStreams = new LinkedList();
        if (inetsocketaddress == null)
        {
            throw new NullPointerException(String.valueOf("address"));
        }
        address = (InetSocketAddress)inetsocketaddress;
        defaultAuthority = s;
        maxMessageSize = i;
        if (executor1 == null)
        {
            throw new NullPointerException(String.valueOf("executor"));
        }
        executor = (Executor)executor1;
        serializingExecutor = new SerializingExecutor(executor1);
        nextStreamId = 3;
        sslSocketFactory = sslsocketfactory;
        hostnameVerifier = hostnameverifier;
        if (connectionspec == null)
        {
            throw new NullPointerException(String.valueOf("connectionSpec"));
        }
        connectionSpec = (ConnectionSpec)connectionspec;
        stopwatchFactory = GrpcUtil.STOPWATCH_SUPPLIER;
        userAgent = GrpcUtil.getGrpcUserAgent("okhttp", s1);
        proxy = proxyparameters;
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("tooManyPingsRunnable"));
        }
        tooManyPingsRunnable = (Runnable)runnable;
        if (transporttracer == null)
        {
            throw new NullPointerException();
        }
        transportTracer = (TransportTracer)transporttracer;
        inetsocketaddress = ((InetSocketAddress) (lock));
        inetsocketaddress;
        JVM INSTR monitorenter ;
        s = transportTracer;
        if (new _cls1() == null)
        {
            throw new NullPointerException();
        }
        break MISSING_BLOCK_LABEL_317;
        s;
        inetsocketaddress;
        JVM INSTR monitorexit ;
        throw s;
        inetsocketaddress;
        JVM INSTR monitorexit ;
    }

    private final Throwable getPingFailure()
    {
label0:
        {
            StatusException statusexception;
            synchronized (lock)
            {
                if (goAwayStatus == null)
                {
                    break label0;
                }
                statusexception = new StatusException(goAwayStatus);
            }
            return statusexception;
        }
        StatusException statusexception1 = new StatusException(Status.UNAVAILABLE.withDescription("Connection closed"));
        obj;
        JVM INSTR monitorexit ;
        return statusexception1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static String readUtf8LineStrictUnbuffered(Source source)
        throws IOException
    {
        Buffer buffer = new Buffer();
        do
        {
            if (source.read(buffer, 1L) == -1L)
            {
                source = String.valueOf((new ByteString(buffer.readByteArray())).hex());
                if (source.length() != 0)
                {
                    source = "\\n not found: ".concat(source);
                } else
                {
                    source = new String("\\n not found: ");
                }
                throw new EOFException(source);
            }
        } while (buffer.getByte(buffer.size - 1L) != 10);
        if (0x7fffffffffffffffL < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder("limit < 0: ")).append(0x7fffffffffffffffL).toString());
        }
        long l;
        if (0x7fffffffffffffffL == 0x7fffffffffffffffL)
        {
            l = 0x7fffffffffffffffL;
        } else
        {
            l = 0x8000000000000000L;
        }
        if (0L < 0L || l < 0L)
        {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", new Object[] {
                Long.valueOf(buffer.size), Long.valueOf(0L), Long.valueOf(l)
            }));
        }
        byte abyte0[];
        Object obj2;
        Buffer buffer1;
        Segment segment;
        int i;
        int j;
        long l1;
        long l4;
        long l5;
        if (l > buffer.size)
        {
            l5 = buffer.size;
        } else
        {
            l5 = l;
        }
        if (0L != l5)
        {
            source = buffer.head;
            if (source != null)
            {
                if (buffer.size < 0L)
                {
                    long l2 = buffer.size;
                    Object obj = source;
                    do
                    {
                        l1 = l2;
                        source = ((Source) (obj));
                        if (l2 <= 0L)
                        {
                            break;
                        }
                        obj = ((Segment) (obj)).prev;
                        l2 -= ((Segment) (obj)).limit - ((Segment) (obj)).pos;
                    } while (true);
                } else
                {
                    l1 = 0L;
                    Object obj1 = source;
                    do
                    {
                        long l3 = (long)(((Segment) (obj1)).limit - ((Segment) (obj1)).pos) + l1;
                        source = ((Source) (obj1));
                        if (l3 >= 0L)
                        {
                            break;
                        }
                        obj1 = ((Segment) (obj1)).next;
                        l1 = l3;
                    } while (true);
                }
                break MISSING_BLOCK_LABEL_816;
            }
        }
          goto _L1
_L3:
        l1 = (long)(((Segment) (source)).limit - ((Segment) (source)).pos) + l4;
        source = ((Segment) (source)).next;
        l4 = l1;
_L5:
        if (l4 >= l5) goto _L1; else goto _L2
_L2:
        abyte0 = ((Segment) (source)).data;
        j = (int)Math.min(((Segment) (source)).limit, ((long)((Segment) (source)).pos + l5) - l4);
        i = (int)((l1 + (long)((Segment) (source)).pos) - l4);
_L4:
        while (i < j) 
        {
            if (abyte0[i] == 10)
            {
                l1 = (long)(i - ((Segment) (source)).pos) + l4;
                break MISSING_BLOCK_LABEL_436;
            }
            i++;
        }
          goto _L3
_L1:
        l1 = -1L;
        if (l1 != -1L)
        {
            return buffer.readUtf8Line(l1);
        }
        if (l < buffer.size && buffer.getByte(l - 1L) == 13 && buffer.getByte(l) == 10)
        {
            return buffer.readUtf8Line(l);
        }
        buffer1 = new Buffer();
        l5 = Math.min(32L, buffer.size);
        Util.checkOffsetAndCount(buffer.size, 0L, l5);
        if (l5 != 0L)
        {
            buffer1.size = buffer1.size + l5;
            source = buffer.head;
            l = 0L;
            do
            {
                obj2 = source;
                l1 = l;
                l4 = l5;
                if (l < (long)(((Segment) (source)).limit - ((Segment) (source)).pos))
                {
                    break;
                }
                l -= ((Segment) (source)).limit - ((Segment) (source)).pos;
                source = ((Segment) (source)).next;
            } while (true);
            while (l4 > 0L) 
            {
                source = new Segment(((Segment) (obj2)));
                source.pos = (int)(l1 + (long)((Segment) (source)).pos);
                source.limit = Math.min(((Segment) (source)).pos + (int)l4, ((Segment) (source)).limit);
                if (buffer1.head == null)
                {
                    source.prev = source;
                    source.next = source;
                    buffer1.head = source;
                } else
                {
                    segment = buffer1.head.prev;
                    source.prev = segment;
                    source.next = segment.next;
                    segment.next.prev = source;
                    segment.next = source;
                }
                l4 -= ((Segment) (source)).limit - ((Segment) (source)).pos;
                l1 = 0L;
                obj2 = ((Segment) (obj2)).next;
            }
        }
        throw new EOFException((new StringBuilder("\\n not found: limit=")).append(Math.min(buffer.size, 0x7fffffffffffffffL)).append(" content=").append((new ByteString(buffer1.readByteArray())).hex()).append('\u2026').toString());
          goto _L4
          goto _L3
        l4 = l1;
        l1 = 0L;
          goto _L5
    }

    private final void stopIfNecessary()
    {
        while (goAwayStatus == null || !streams.isEmpty() || !pendingStreams.isEmpty() || stopped) 
        {
            return;
        }
        stopped = true;
        if (keepAliveManager != null)
        {
            keepAliveManager.onTransportTermination();
            io.grpc.internal.SharedResourceHolder.Resource resource = GrpcUtil.TIMER_SERVICE;
            ScheduledExecutorService scheduledexecutorservice = scheduler;
            scheduler = (ScheduledExecutorService)SharedResourceHolder.holder.releaseInternal(resource, scheduledexecutorservice);
        }
        if (ping == null) goto _L2; else goto _L1
_L1:
        Object obj1 = ping;
        Object obj = getPingFailure();
        obj1;
        JVM INSTR monitorenter ;
        if (!((Http2Ping) (obj1)).completed) goto _L4; else goto _L3
_L3:
        obj1;
        JVM INSTR monitorexit ;
_L6:
        ping = null;
_L2:
        if (!goAwaySent)
        {
            goAwaySent = true;
            obj = frameWriter;
            obj1 = ErrorCode.NO_ERROR;
            ((AsyncFrameWriter) (obj)).executor.execute(new AsyncFrameWriter._cls12(((AsyncFrameWriter) (obj)), 0, ((ErrorCode) (obj1)), new byte[0]));
        }
        frameWriter.close();
        return;
_L4:
        Map map;
        obj1.completed = true;
        obj1.failureCause = ((Throwable) (obj));
        map = ((Http2Ping) (obj1)).callbacks;
        obj1.callbacks = null;
        obj1;
        JVM INSTR monitorexit ;
        obj1 = map.entrySet().iterator();
        while (((Iterator) (obj1)).hasNext()) 
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj1)).next();
            io.grpc.internal.ClientTransport.PingCallback pingcallback = (io.grpc.internal.ClientTransport.PingCallback)entry.getKey();
            Http2Ping.doExecute((Executor)entry.getValue(), new io.grpc.internal.Http2Ping._cls2(pingcallback, ((Throwable) (obj))));
        }
        if (true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static Status toGrpcStatus(ErrorCode errorcode)
    {
        Status status = (Status)ERROR_CODE_TO_STATUS.get(errorcode);
        if (status != null)
        {
            return status;
        } else
        {
            Status status1 = Status.UNKNOWN;
            int i = errorcode.httpCode;
            return status1.withDescription((new StringBuilder(37)).append("Unknown http2 error code: ").append(i).toString());
        }
    }

    final Socket createHttpProxySocket(InetSocketAddress inetsocketaddress, InetSocketAddress inetsocketaddress1, String s, String s1)
        throws IOException, StatusException
    {
        if (inetsocketaddress1.getAddress() == null) goto _L2; else goto _L1
_L1:
        Socket socket1 = new Socket(inetsocketaddress1.getAddress(), inetsocketaddress1.getPort());
_L5:
        Source source;
        BufferedSink bufferedsink;
        com.squareup.okhttp.HttpUrl.Builder builder;
        socket1.setTcpNoDelay(true);
        source = Okio.source(socket1);
        bufferedsink = Okio.buffer(Okio.sink(socket1));
        builder = new com.squareup.okhttp.HttpUrl.Builder();
        if (!"https".equalsIgnoreCase("http")) goto _L4; else goto _L3
_L3:
        builder.scheme = "http";
_L6:
        String s3 = inetsocketaddress.getHostName();
        if (s3 == null)
        {
            try
            {
                throw new IllegalArgumentException("host == null");
            }
            // Misplaced declaration of an exception variable
            catch (InetSocketAddress inetsocketaddress)
            {
                throw new StatusException(Status.UNAVAILABLE.withDescription("Failed trying to connect with proxy").withCause(inetsocketaddress));
            }
        }
        break MISSING_BLOCK_LABEL_190;
_L2:
        socket1 = new Socket(inetsocketaddress1.getHostName(), inetsocketaddress1.getPort());
          goto _L5
_L4:
label0:
        {
            if (!"https".equalsIgnoreCase("https"))
            {
                break label0;
            }
            builder.scheme = "https";
        }
          goto _L6
        throw new IllegalArgumentException((new StringBuilder("unexpected scheme: ")).append("https").toString());
          goto _L5
        inetsocketaddress1 = HttpUrl.percentDecode(s3, 0, s3.length(), false);
        if (!inetsocketaddress1.startsWith("[") || !inetsocketaddress1.endsWith("]")) goto _L8; else goto _L7
_L7:
        inetsocketaddress1 = com.squareup.okhttp.HttpUrl.Builder.decodeIpv6(inetsocketaddress1, 1, inetsocketaddress1.length() - 1);
        if (inetsocketaddress1 != null) goto _L10; else goto _L9
_L9:
        inetsocketaddress1 = null;
_L26:
        if (inetsocketaddress1 != null) goto _L12; else goto _L11
_L11:
        throw new IllegalArgumentException((new StringBuilder("unexpected host: ")).append(s3).toString());
_L10:
        inetsocketaddress1 = inetsocketaddress1.getAddress();
        if (inetsocketaddress1.length != 16) goto _L14; else goto _L13
_L13:
        int i;
        int j;
        int k;
        j = -1;
        k = 0;
        i = 0;
_L44:
        int l;
        if (i >= inetsocketaddress1.length) goto _L16; else goto _L15
_L16:
        buffer = new Buffer();
        i = 0;
_L20:
        if (i >= inetsocketaddress1.length) goto _L18; else goto _L17
_L17:
        if (i != j)
        {
            break MISSING_BLOCK_LABEL_465;
        }
        Segment segment = buffer.writableSegment(1);
        abyte0 = segment.data;
        l = segment.limit;
        segment.limit = l + 1;
        abyte0[l] = (byte)58;
        buffer.size = buffer.size + 1L;
        Buffer buffer1 = (Buffer)buffer;
        i += k;
        if (i != 16) goto _L20; else goto _L19
_L19:
        Segment segment1 = buffer.writableSegment(1);
        abyte0 = segment1.data;
        l = segment1.limit;
        segment1.limit = l + 1;
        abyte0[l] = (byte)58;
        buffer.size = buffer.size + 1L;
        Buffer buffer2 = (Buffer)buffer;
          goto _L20
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_528;
        }
        Segment segment2 = buffer.writableSegment(1);
        abyte0 = segment2.data;
        l = segment2.limit;
        segment2.limit = l + 1;
        abyte0[l] = (byte)58;
        buffer.size = buffer.size + 1L;
        Buffer buffer3 = (Buffer)buffer;
        l1 = (inetsocketaddress1[i] & 0xff) << 8 | inetsocketaddress1[i + 1] & 0xff;
        if (l1 != 0L) goto _L22; else goto _L21
_L21:
        Segment segment3 = buffer.writableSegment(1);
        abyte0 = segment3.data;
        l = segment3.limit;
        segment3.limit = l + 1;
        abyte0[l] = (byte)48;
        buffer.size = buffer.size + 1L;
        Buffer buffer4 = (Buffer)buffer;
          goto _L23
_L22:
        i1 = Long.numberOfTrailingZeros(Long.highestOneBit(l1)) / 4 + 1;
        segment4 = buffer.writableSegment(i1);
        abyte0 = segment4.data;
        l = (segment4.limit + i1) - 1;
        k1 = segment4.limit;
_L25:
        if (l < k1)
        {
            break; /* Loop/switch isn't completed */
        }
        abyte0[l] = Buffer.DIGITS[(int)(15L & l1)];
        l1 >>>= 4;
        l--;
        if (true) goto _L25; else goto _L24
_L24:
        segment4.limit = segment4.limit + i1;
        buffer.size = buffer.size + (long)i1;
          goto _L23
_L18:
        inetsocketaddress1 = buffer.readUtf8();
          goto _L26
_L14:
        throw new AssertionError();
_L8:
        inetsocketaddress1 = com.squareup.okhttp.HttpUrl.Builder.domainToAscii(inetsocketaddress1);
          goto _L26
_L12:
        builder.host = inetsocketaddress1;
        i = inetsocketaddress.getPort();
        if (i > 0 && i <= 65535)
        {
            break MISSING_BLOCK_LABEL_814;
        }
        throw new IllegalArgumentException((new StringBuilder("unexpected port: ")).append(i).toString());
        builder.port = i;
        if (builder.scheme == null)
        {
            throw new IllegalStateException("scheme == null");
        }
        if (builder.host == null)
        {
            throw new IllegalStateException("host == null");
        }
        inetsocketaddress = new HttpUrl(builder);
        inetsocketaddress1 = new com.squareup.okhttp.Request.Builder();
        if (inetsocketaddress != null)
        {
            break MISSING_BLOCK_LABEL_892;
        }
        throw new IllegalArgumentException("url == null");
        inetsocketaddress1.url = inetsocketaddress;
        String s2 = ((HttpUrl) (inetsocketaddress)).host;
        i = ((HttpUrl) (inetsocketaddress)).port;
        inetsocketaddress = inetsocketaddress1.header("Host", (new StringBuilder(String.valueOf(s2).length() + 12)).append(s2).append(":").append(i).toString()).header("User-Agent", userAgent);
        if (s == null || s1 == null)
        {
            break MISSING_BLOCK_LABEL_987;
        }
        inetsocketaddress.header("Proxy-Authorization", Credentials.basic(s, s1));
        if (((com.squareup.okhttp.Request.Builder) (inetsocketaddress)).url == null)
        {
            throw new IllegalStateException("url == null");
        }
        inetsocketaddress1 = new Request(inetsocketaddress);
        inetsocketaddress = ((Request) (inetsocketaddress1)).url;
        bufferedsink.writeUtf8(String.format("CONNECT %s:%d HTTP/1.1", new Object[] {
            ((HttpUrl) (inetsocketaddress)).host, Integer.valueOf(((HttpUrl) (inetsocketaddress)).port)
        })).writeUtf8("\r\n");
        i = 0;
        j = ((Request) (inetsocketaddress1)).headers.namesAndValues.length / 2;
_L33:
        if (i >= j) goto _L28; else goto _L27
_L27:
        inetsocketaddress = ((Request) (inetsocketaddress1)).headers;
        k = i << 1;
        if (k < 0) goto _L30; else goto _L29
_L29:
        if (k < ((Headers) (inetsocketaddress)).namesAndValues.length) goto _L31; else goto _L30
_L34:
        s = bufferedsink.writeUtf8(inetsocketaddress).writeUtf8(": ");
        inetsocketaddress = ((Request) (inetsocketaddress1)).headers;
        k = (i << 1) + 1;
        if (k < 0)
        {
            break MISSING_BLOCK_LABEL_1506;
        }
        if (k >= ((Headers) (inetsocketaddress)).namesAndValues.length)
        {
            break MISSING_BLOCK_LABEL_1506;
        }
          goto _L32
_L35:
        s.writeUtf8(inetsocketaddress).writeUtf8("\r\n");
        i++;
          goto _L33
_L31:
        inetsocketaddress = ((Headers) (inetsocketaddress)).namesAndValues[k];
          goto _L34
_L32:
        inetsocketaddress = ((Headers) (inetsocketaddress)).namesAndValues[k];
          goto _L35
_L28:
        bufferedsink.writeUtf8("\r\n");
        bufferedsink.flush();
        inetsocketaddress1 = StatusLine.parse(readUtf8LineStrictUnbuffered(source));
        while (!readUtf8LineStrictUnbuffered(source).equals("")) ;
        if (((StatusLine) (inetsocketaddress1)).code >= 200 && ((StatusLine) (inetsocketaddress1)).code < 300) goto _L37; else goto _L36
_L36:
        s = new Buffer();
        socket1.shutdownOutput();
        source.read(s, 1024L);
_L42:
        try
        {
            socket1.close();
        }
        // Misplaced declaration of an exception variable
        catch (InetSocketAddress inetsocketaddress) { }
        inetsocketaddress = String.format("Response returned from proxy was not successful (expected 2xx, got %d %s). Response body:\n%s", new Object[] {
            Integer.valueOf(((StatusLine) (inetsocketaddress1)).code), ((StatusLine) (inetsocketaddress1)).message, s.readUtf8()
        });
        throw new StatusException(Status.UNAVAILABLE.withDescription(inetsocketaddress));
        inetsocketaddress;
        inetsocketaddress = String.valueOf(inetsocketaddress.toString());
        if (inetsocketaddress.length() == 0) goto _L39; else goto _L38
_L38:
        inetsocketaddress = "Unable to read body: ".concat(inetsocketaddress);
_L40:
        inetsocketaddress = (Buffer)s.writeUtf8(inetsocketaddress, 0, inetsocketaddress.length());
        continue; /* Loop/switch isn't completed */
_L39:
        inetsocketaddress = new String("Unable to read body: ");
        if (true) goto _L40; else goto _L37
_L37:
        return socket1;
        if (true) goto _L42; else goto _L41
_L41:
        i = k;
        break; /* Loop/switch isn't completed */
_L15:
        Buffer buffer;
        Segment segment4;
        byte abyte0[];
        int i1;
        int k1;
        long l1;
        for (l = i; l < 16 && inetsocketaddress1[l] == 0 && inetsocketaddress1[l + 1] == 0; l += 2) { }
        int j1 = l - i;
        if (j1 <= k)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = i;
        i = j1;
          goto _L43
        if (true) goto _L41; else goto _L43
_L43:
        l += 2;
        k = i;
        i = l;
          goto _L44
_L23:
        i += 2;
          goto _L20
_L30:
        inetsocketaddress = null;
          goto _L34
        inetsocketaddress = null;
          goto _L35
    }

    final void finishStream$514KOQBF5TJN4S335T9N8OBKELPJMJ39DSNMESJGCCNMIRJKCLP6SOBC5T1MOQB5DPQ56T3ICLGMQJ39EDQ6ARJ5E8I54S33A1P6UPRICLPN6EQQ9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FCPP62RB5CGNKASJIDTP46RR4CKTKOQBF5TJN4S335T6MAT31CHGN8O9R55B0____0(int i, Status status, int j, boolean flag, ErrorCode errorcode, Metadata metadata)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        OkHttpClientStream okhttpclientstream = (OkHttpClientStream)streams.remove(Integer.valueOf(i));
        if (okhttpclientstream == null) goto _L2; else goto _L1
_L1:
        if (errorcode == null)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        errorcode = frameWriter;
        ErrorCode errorcode1 = ErrorCode.CANCEL;
        ((AsyncFrameWriter) (errorcode)).executor.execute(new AsyncFrameWriter._cls8(errorcode, i, errorcode1));
        if (status == null) goto _L4; else goto _L3
_L3:
        errorcode = okhttpclientstream.state;
        if (metadata == null) goto _L6; else goto _L5
_L5:
        errorcode.transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(status, j, flag, metadata);
_L4:
        if (!startPendingStreams())
        {
            stopIfNecessary();
            maybeClearInUse();
        }
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
_L6:
        metadata = new Metadata();
        if (true) goto _L5; else goto _L7
_L7:
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
    }

    final OkHttpClientStream[] getActiveStreams()
    {
        OkHttpClientStream aokhttpclientstream[];
        synchronized (lock)
        {
            aokhttpclientstream = (OkHttpClientStream[])streams.values().toArray(EMPTY_STREAM_ARRAY);
        }
        return aokhttpclientstream;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final Attributes getAttributes()
    {
        return attributes;
    }

    public final LogId getLogId()
    {
        return logId;
    }

    final OkHttpClientStream getStream(int i)
    {
        OkHttpClientStream okhttpclientstream;
        synchronized (lock)
        {
            okhttpclientstream = (OkHttpClientStream)streams.get(Integer.valueOf(i));
        }
        return okhttpclientstream;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final boolean mayHaveCreatedStream(int i)
    {
        boolean flag = true;
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        if (i >= nextStreamId || (i & 1) != 1)
        {
            flag = false;
        }
        obj;
        JVM INSTR monitorexit ;
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final void maybeClearInUse()
    {
        if (inUse && pendingStreams.isEmpty() && streams.isEmpty())
        {
            inUse = false;
            listener.transportInUse(false);
            if (keepAliveManager != null)
            {
                keepAliveManager.onTransportIdle();
            }
        }
    }

    public final ClientStream newStream(MethodDescriptor methoddescriptor, Metadata metadata, CallOptions calloptions)
    {
        if (methoddescriptor == null)
        {
            throw new NullPointerException(String.valueOf("method"));
        }
        if (metadata == null)
        {
            throw new NullPointerException(String.valueOf("headers"));
        } else
        {
            calloptions = StatsTraceContext.newClientContext(calloptions, metadata);
            return new OkHttpClientStream(methoddescriptor, metadata, frameWriter, this, outboundFlow, lock, maxMessageSize, defaultAuthority, userAgent, calloptions, transportTracer);
        }
    }

    final void onError(ErrorCode errorcode, String s)
    {
        startGoAway(0, errorcode, toGrpcStatus(errorcode).augmentDescription(s));
    }

    final void onException(Throwable throwable)
    {
        if (throwable == null)
        {
            throw new NullPointerException(String.valueOf("failureCause"));
        } else
        {
            throwable = Status.UNAVAILABLE.withCause(throwable);
            startGoAway(0, ErrorCode.INTERNAL_ERROR, throwable);
            return;
        }
    }

    public final void ping(io.grpc.internal.ClientTransport.PingCallback pingcallback, Executor executor1)
    {
        long l;
label0:
        {
            boolean flag;
            if (frameWriter != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            }
            l = 0L;
            synchronized (lock)
            {
                if (!stopped)
                {
                    break label0;
                }
                Http2Ping.notifyFailed(pingcallback, executor1, getPingFailure());
            }
            return;
        }
        if (ping == null) goto _L2; else goto _L1
_L1:
        Object obj = ping;
        int i = 0;
_L3:
        obj1;
        JVM INSTR monitorexit ;
        if (i != 0)
        {
            obj1 = frameWriter;
            i = (int)(l >>> 32);
            int j = (int)l;
            ((AsyncFrameWriter) (obj1)).executor.execute(new AsyncFrameWriter._cls11(((AsyncFrameWriter) (obj1)), false, i, j));
        }
        obj;
        JVM INSTR monitorenter ;
        if (((Http2Ping) (obj)).completed)
        {
            break MISSING_BLOCK_LABEL_227;
        }
        ((Http2Ping) (obj)).callbacks.put(pingcallback, executor1);
        obj;
        JVM INSTR monitorexit ;
        return;
        pingcallback;
        obj;
        JVM INSTR monitorexit ;
        throw pingcallback;
_L2:
        l = random.nextLong();
        obj = (Stopwatch)stopwatchFactory.get();
        ((Stopwatch) (obj)).start();
        obj = new Http2Ping(l, ((Stopwatch) (obj)));
        ping = ((Http2Ping) (obj));
        TransportTracer transporttracer = transportTracer;
        transporttracer.keepAlivesSent = transporttracer.keepAlivesSent + 1L;
        i = 1;
          goto _L3
        pingcallback;
        obj1;
        JVM INSTR monitorexit ;
        throw pingcallback;
        if (((Http2Ping) (obj)).failureCause == null)
        {
            break MISSING_BLOCK_LABEL_278;
        }
        pingcallback = new io.grpc.internal.Http2Ping._cls2(pingcallback, ((Http2Ping) (obj)).failureCause);
_L4:
        obj;
        JVM INSTR monitorexit ;
        try
        {
            executor1.execute(pingcallback);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (io.grpc.internal.ClientTransport.PingCallback pingcallback)
        {
            Http2Ping.log.logp(Level.SEVERE, "io.grpc.internal.Http2Ping", "doExecute", "Failed to execute PingCallback", pingcallback);
        }
        return;
        pingcallback = new io.grpc.internal.Http2Ping._cls1(pingcallback, ((Http2Ping) (obj)).roundTripTimeNanos);
          goto _L4
    }

    final void setInUse()
    {
        if (!inUse)
        {
            inUse = true;
            listener.transportInUse(true);
            if (keepAliveManager != null)
            {
                keepAliveManager.onTransportActive();
            }
        }
    }

    public final void shutdown(Status status)
    {
label0:
        {
            synchronized (lock)
            {
                if (goAwayStatus == null)
                {
                    break label0;
                }
            }
            return;
        }
        goAwayStatus = status;
        listener.transportShutdown(goAwayStatus);
        stopIfNecessary();
        obj;
        JVM INSTR monitorexit ;
        return;
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
    }

    public final void shutdownNow(Status status)
    {
        shutdown(status);
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        Metadata metadata;
        for (Iterator iterator = streams.entrySet().iterator(); iterator.hasNext(); ((io.grpc.internal.AbstractClientStream.TransportState) (obj1)).transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(status, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, false, metadata))
        {
            obj1 = (java.util.Map.Entry)iterator.next();
            iterator.remove();
            obj1 = ((OkHttpClientStream)((java.util.Map.Entry) (obj1)).getValue()).state;
            metadata = new Metadata();
        }

        break MISSING_BLOCK_LABEL_97;
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
        OkHttpClientStream.TransportState transportstate;
        Metadata metadata1;
        for (Iterator iterator1 = pendingStreams.iterator(); iterator1.hasNext(); transportstate.transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(status, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, true, metadata1))
        {
            transportstate = ((OkHttpClientStream)iterator1.next()).state;
            metadata1 = new Metadata();
        }

        pendingStreams.clear();
        maybeClearInUse();
        stopIfNecessary();
        obj;
        JVM INSTR monitorexit ;
    }

    public final Runnable start(io.grpc.internal.ManagedClientTransport.Listener listener1)
    {
        if (listener1 == null)
        {
            throw new NullPointerException(String.valueOf("listener"));
        }
        listener = (io.grpc.internal.ManagedClientTransport.Listener)listener1;
        if (enableKeepAlive)
        {
            listener1 = GrpcUtil.TIMER_SERVICE;
            scheduler = (ScheduledExecutorService)SharedResourceHolder.holder.getInternal(listener1);
            keepAliveManager = new KeepAliveManager(new io.grpc.internal.KeepAliveManager.ClientKeepAlivePinger(this), scheduler, keepAliveTimeNanos, keepAliveTimeoutNanos, keepAliveWithoutCalls);
            keepAliveManager.onTransportStarted();
        }
        frameWriter = new AsyncFrameWriter(this, serializingExecutor);
        outboundFlow = new OutboundFlowController(this, frameWriter);
        serializingExecutor.execute(new _cls2());
        return null;
    }

    final void startGoAway(int i, ErrorCode errorcode, Status status)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (goAwayStatus == null)
        {
            goAwayStatus = status;
            listener.transportShutdown(status);
        }
        if (errorcode == null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        if (!goAwaySent)
        {
            goAwaySent = true;
            AsyncFrameWriter asyncframewriter = frameWriter;
            asyncframewriter.executor.execute(new AsyncFrameWriter._cls12(asyncframewriter, 0, errorcode, new byte[0]));
        }
        errorcode = streams.entrySet().iterator();
        do
        {
            if (!errorcode.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)errorcode.next();
            if (((Integer)entry.getKey()).intValue() > i)
            {
                errorcode.remove();
                ((OkHttpClientStream)entry.getValue()).state.transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(status, android.support.v4.content.ModernAsyncTask.Status.REFUSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, false, new Metadata());
            }
        } while (true);
        break MISSING_BLOCK_LABEL_170;
        errorcode;
        obj;
        JVM INSTR monitorexit ;
        throw errorcode;
        for (errorcode = pendingStreams.iterator(); errorcode.hasNext(); ((OkHttpClientStream)errorcode.next()).state.transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(status, android.support.v4.content.ModernAsyncTask.Status.REFUSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, true, new Metadata())) { }
        pendingStreams.clear();
        maybeClearInUse();
        stopIfNecessary();
        obj;
        JVM INSTR monitorexit ;
    }

    final boolean startPendingStreams()
    {
        boolean flag;
        for (flag = false; !pendingStreams.isEmpty() && streams.size() < maxConcurrentStreams; flag = true)
        {
            startStream((OkHttpClientStream)pendingStreams.poll());
        }

        return flag;
    }

    final void startStream(OkHttpClientStream okhttpclientstream)
    {
        boolean flag;
        if (okhttpclientstream.id == -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("StreamId already assigned"));
        }
        streams.put(Integer.valueOf(nextStreamId), okhttpclientstream);
        setInUse();
        OkHttpClientStream.TransportState transportstate = okhttpclientstream.state;
        int j = nextStreamId;
        if (transportstate.this$0.id == -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(Strings.lenientFormat("the stream has been started with id %s", new Object[] {
                Integer.valueOf(j)
            }));
        }
        transportstate.this$0.id = j;
        transportstate.this$0.state.onStreamAllocated();
        if (transportstate.pendingData != null)
        {
            Object obj = transportstate.frameWriter;
            boolean flag2 = transportstate.this$0.useGet;
            int i = transportstate.this$0.id;
            List list = transportstate.requestHeaders;
            ((AsyncFrameWriter) (obj)).executor.execute(new AsyncFrameWriter._cls5(((AsyncFrameWriter) (obj)), flag2, false, i, 0, list));
            obj = transportstate.this$0.statsTraceCtx;
            transportstate.requestHeaders = null;
            boolean flag1 = false;
            do
            {
                if (transportstate.pendingData.isEmpty())
                {
                    break;
                }
                OkHttpClientStream.PendingData pendingdata = (OkHttpClientStream.PendingData)transportstate.pendingData.poll();
                transportstate.outboundFlow.data(pendingdata.endOfStream, transportstate.this$0.id, pendingdata.buffer, false);
                if (pendingdata.flush)
                {
                    flag1 = true;
                }
            } while (true);
            if (flag1)
            {
                OutboundFlowController outboundflowcontroller = transportstate.outboundFlow;
                try
                {
                    outboundflowcontroller.frameWriter.flush();
                }
                // Misplaced declaration of an exception variable
                catch (OkHttpClientStream okhttpclientstream)
                {
                    throw new RuntimeException(okhttpclientstream);
                }
            }
            transportstate.pendingData = null;
        }
        if (okhttpclientstream.method.type != io.grpc.MethodDescriptor.MethodType.UNARY && okhttpclientstream.method.type != io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING || okhttpclientstream.useGet)
        {
            okhttpclientstream = frameWriter;
            ((AsyncFrameWriter) (okhttpclientstream)).executor.execute(new AsyncFrameWriter._cls4(okhttpclientstream));
        }
        if (nextStreamId >= 0x7ffffffd)
        {
            nextStreamId = 0x7fffffff;
            startGoAway(0x7fffffff, ErrorCode.NO_ERROR, Status.UNAVAILABLE.withDescription("Stream ids exhausted"));
            return;
        } else
        {
            nextStreamId = nextStreamId + 2;
            return;
        }
    }

    public String toString()
    {
        return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("logId", logId.id).add("address", address).toString();
    }

    static 
    {
        EnumMap enummap = new EnumMap(io/grpc/okhttp/internal/framed/ErrorCode);
        enummap.put(ErrorCode.NO_ERROR, Status.INTERNAL.withDescription("No error: A GRPC status of OK should have been sent"));
        enummap.put(ErrorCode.PROTOCOL_ERROR, Status.INTERNAL.withDescription("Protocol error"));
        enummap.put(ErrorCode.INTERNAL_ERROR, Status.INTERNAL.withDescription("Internal error"));
        enummap.put(ErrorCode.FLOW_CONTROL_ERROR, Status.INTERNAL.withDescription("Flow control error"));
        enummap.put(ErrorCode.STREAM_CLOSED, Status.INTERNAL.withDescription("Stream closed"));
        enummap.put(ErrorCode.FRAME_TOO_LARGE, Status.INTERNAL.withDescription("Frame too large"));
        enummap.put(ErrorCode.REFUSED_STREAM, Status.UNAVAILABLE.withDescription("Refused stream"));
        enummap.put(ErrorCode.CANCEL, Status.CANCELLED.withDescription("Cancelled"));
        enummap.put(ErrorCode.COMPRESSION_ERROR, Status.INTERNAL.withDescription("Compression error"));
        enummap.put(ErrorCode.CONNECT_ERROR, Status.INTERNAL.withDescription("Connect error"));
        enummap.put(ErrorCode.ENHANCE_YOUR_CALM, Status.RESOURCE_EXHAUSTED.withDescription("Enhance your calm"));
        enummap.put(ErrorCode.INADEQUATE_SECURITY, Status.PERMISSION_DENIED.withDescription("Inadequate security"));
        ERROR_CODE_TO_STATUS = Collections.unmodifiableMap(enummap);
    }

    private class _cls1
    {

        _cls1()
        {
        }
    }


    private class _cls2
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
                obj.clientFrameHandler = okhttpclienttransport. new ClientFrameHandler(null);
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
            obj6 = OkHttpTlsUpgrader.upgrade(((SSLSocketFactory) (obj7)), ((HostnameVerifier) (obj8)), ((Socket) (obj5)), ((String) (obj6)), i, connectionSpec);
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
            obj10 = Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, ((Socket) (obj6)).getRemoteSocketAddress()).set(Grpc.TRANSPORT_ATTR_SSL_SESSION, obj5);
            obj3 = obj2;
            io.grpc.Attributes.Key key = CallCredentials.ATTR_SECURITY_LEVEL;
            if (obj5 != null) goto _L8; else goto _L7
_L7:
            obj3 = obj2;
            obj7 = SecurityLevel.NONE;
_L14:
            obj3 = obj2;
            obj9.attributes = ((io.grpc.Attributes.Builder) (obj10)).set(key, obj7).build();
            clientFrameHandler = new ClientFrameHandler(http2.newReader(((BufferedSource) (obj2)), true));
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
            clientFrameHandler = new ClientFrameHandler(http2.newReader(((BufferedSource) (obj2)), true));
            executor.execute(clientFrameHandler);
            return;
            obj5;
_L18:
            obj3 = obj2;
            onException(((Throwable) (obj5)));
            clientFrameHandler = new ClientFrameHandler(http2.newReader(((BufferedSource) (obj2)), true));
            executor.execute(clientFrameHandler);
            return;
            obj2;
_L16:
            clientFrameHandler = new ClientFrameHandler(http2.newReader(((BufferedSource) (obj3)), true));
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
            new io.grpc.internal.Channelz.Security(new io.grpc.internal.Channelz.Tls(((javax.net.ssl.SSLSession) (obj5))));
            obj2;
            JVM INSTR monitorexit ;
            obj2 = http2.newWriter(((BufferedSink) (obj8)), true);
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

        _cls2()
        {
            this$0 = OkHttpClientTransport.this;
            super();
        }
    }

}
