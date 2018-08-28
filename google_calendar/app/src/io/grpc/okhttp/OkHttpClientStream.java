// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.internal.AbstractClientStream;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import okio.Buffer;

// Referenced classes of package io.grpc.okhttp:
//            OkHttpWritableBufferAllocator, OkHttpClientTransport, AsyncFrameWriter, OutboundFlowController

final class OkHttpClientStream extends AbstractClientStream
{

    public static final Buffer EMPTY_BUFFER = new Buffer();
    public String authority;
    public volatile int id;
    public final MethodDescriptor method;
    public Object outboundFlowState;
    private final Sink sink = new Sink();
    public final TransportState state;
    public final StatsTraceContext statsTraceCtx;
    public boolean useGet;
    public final String userAgent;

    OkHttpClientStream(MethodDescriptor methoddescriptor, Metadata metadata, AsyncFrameWriter asyncframewriter, OkHttpClientTransport okhttpclienttransport, OutboundFlowController outboundflowcontroller, Object obj, int i, 
            String s, String s1, StatsTraceContext statstracecontext, TransportTracer transporttracer)
    {
        super(new OkHttpWritableBufferAllocator(), statstracecontext, transporttracer, metadata, methoddescriptor.safe);
        id = -1;
        useGet = false;
        if (statstracecontext == null)
        {
            throw new NullPointerException(String.valueOf("statsTraceCtx"));
        } else
        {
            statsTraceCtx = (StatsTraceContext)statstracecontext;
            method = methoddescriptor;
            authority = s;
            userAgent = s1;
            methoddescriptor = okhttpclienttransport.attributes;
            state = new TransportState(i, statstracecontext, obj, asyncframewriter, outboundflowcontroller, okhttpclienttransport);
            return;
        }
    }

    protected final io.grpc.internal.AbstractClientStream.Sink abstractClientStreamSink()
    {
        return sink;
    }

    public final void setAuthority(String s)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("authority"));
        } else
        {
            authority = (String)s;
            return;
        }
    }

    protected final io.grpc.internal.AbstractClientStream.TransportState transportState()
    {
        return state;
    }

    protected final io.grpc.internal.AbstractStream.TransportState transportState()
    {
        return state;
    }


    private class Sink
        implements io.grpc.internal.AbstractClientStream.Sink
    {

        private final OkHttpClientStream this$0;

        public final void cancel(Status status)
        {
            synchronized (state.lock)
            {
                state.cancel(status, true, null);
            }
            return;
            status;
            obj;
            JVM INSTR monitorexit ;
            throw status;
        }

        public final void request(int i)
        {
            Object obj = state.lock;
            obj;
            JVM INSTR monitorenter ;
            TransportState transportstate = state;
            ((io.grpc.internal.AbstractStream.TransportState) (transportstate)).deframer.request(i);
_L1:
            return;
            Throwable throwable;
            throwable;
            transportstate.deframeFailed(throwable);
              goto _L1
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public final void writeFrame(WritableBuffer writablebuffer, boolean flag, boolean flag1, int i)
        {
            Object obj;
            TransportState transportstate;
            if (writablebuffer == null)
            {
                writablebuffer = OkHttpClientStream.EMPTY_BUFFER;
            } else
            {
                obj = ((OkHttpWritableBuffer)writablebuffer).buffer;
                int j = (int)((Buffer) (obj)).size;
                writablebuffer = ((WritableBuffer) (obj));
                if (j > 0)
                {
                    transportState().onSendingBytes(j);
                    writablebuffer = ((WritableBuffer) (obj));
                }
            }
            obj = state.lock;
            obj;
            JVM INSTR monitorenter ;
            transportstate = state;
            if (transportstate.cancelSent) goto _L2; else goto _L1
_L1:
            if (transportstate.pendingData == null) goto _L4; else goto _L3
_L3:
            transportstate.pendingData.add(new PendingData(writablebuffer, flag, flag1));
_L2:
            writablebuffer = transportTracer;
            if (i == 0)
            {
                break MISSING_BLOCK_LABEL_104;
            }
            writablebuffer.messagesSent = ((TransportTracer) (writablebuffer)).messagesSent + (long)i;
            ((TransportTracer) (writablebuffer)).timeProvider.currentTimeNanos();
            obj;
            JVM INSTR monitorexit ;
            return;
_L4:
            boolean flag2;
            if (transportstate._fld0.id != -1)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag2)
            {
                break MISSING_BLOCK_LABEL_196;
            }
            throw new IllegalStateException(String.valueOf("streamId should be set"));
            writablebuffer;
            obj;
            JVM INSTR monitorexit ;
            throw writablebuffer;
            transportstate.outboundFlow.data(flag, transportstate._fld0.id, writablebuffer, flag1);
              goto _L2
        }

        public final void writeHeaders(Metadata metadata, byte abyte0[])
        {
            Object obj;
            obj = String.valueOf(method.fullMethodName);
            String s;
            if (((String) (obj)).length() != 0)
            {
                obj = "/".concat(((String) (obj)));
            } else
            {
                obj = new String("/");
            }
            s = ((String) (obj));
            if (abyte0 != null)
            {
                useGet = true;
                obj = String.valueOf(obj);
                abyte0 = BaseEncoding.BASE64.encode(abyte0, 0, abyte0.length);
                s = (new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(abyte0).length())).append(((String) (obj))).append("?").append(abyte0).toString();
            }
            abyte0 = ((byte []) (state.lock));
            abyte0;
            JVM INSTR monitorenter ;
            obj = state;
            obj.requestHeaders = Headers.createRequestHeaders(metadata, s, ((TransportState) (obj))._fld0.authority, ((TransportState) (obj))._fld0.userAgent, ((TransportState) (obj))._fld0.useGet);
            metadata = ((TransportState) (obj)).transport;
            obj = ((TransportState) (obj))._fld0;
            if (((OkHttpClientTransport) (metadata)).goAwayStatus == null) goto _L2; else goto _L1
_L1:
            ((OkHttpClientStream) (obj)).state.transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(((OkHttpClientTransport) (metadata)).goAwayStatus, android.support.v4.content.ModernAsyncTask.Status.REFUSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, true, new Metadata());
_L3:
            return;
_L2:
            if (((OkHttpClientTransport) (metadata)).streams.size() < ((OkHttpClientTransport) (metadata)).maxConcurrentStreams)
            {
                break MISSING_BLOCK_LABEL_245;
            }
            ((OkHttpClientTransport) (metadata)).pendingStreams.add(obj);
            metadata.setInUse();
              goto _L3
            metadata;
            abyte0;
            JVM INSTR monitorexit ;
            throw metadata;
            metadata.startStream(((OkHttpClientStream) (obj)));
              goto _L3
        }

        Sink()
        {
            this$0 = OkHttpClientStream.this;
            super();
        }

        private class PendingData
        {

            public Buffer buffer;
            public boolean endOfStream;
            public boolean flush;

            PendingData(Buffer buffer1, boolean flag, boolean flag1)
            {
                buffer = buffer1;
                endOfStream = flag;
                flush = flag1;
            }
        }

    }


    private class TransportState extends Http2ClientStreamTransportState
    {

        public boolean cancelSent;
        public final AsyncFrameWriter frameWriter;
        public final Object lock;
        public final OutboundFlowController outboundFlow;
        public Queue pendingData;
        private int processedWindow;
        public List requestHeaders;
        public final OkHttpClientStream this$0;
        public final OkHttpClientTransport transport;
        private int window;

        public final void bytesRead(int i)
        {
            processedWindow = processedWindow - i;
            if (processedWindow <= 32767)
            {
                i = 65535 - processedWindow;
                window = window + i;
                processedWindow = processedWindow + i;
                AsyncFrameWriter asyncframewriter = frameWriter;
                int j = id;
                long l = i;
                asyncframewriter.executor.execute(new AsyncFrameWriter._cls13(asyncframewriter, j, l));
            }
        }

        final void cancel(Status status, boolean flag, Metadata metadata)
        {
            if (cancelSent)
            {
                return;
            }
            cancelSent = true;
            if (pendingData != null)
            {
                OkHttpClientTransport okhttpclienttransport = transport;
                OkHttpClientStream okhttpclientstream = OkHttpClientStream.this;
                okhttpclienttransport.pendingStreams.remove(okhttpclientstream);
                okhttpclienttransport.maybeClearInUse();
                requestHeaders = null;
                for (Iterator iterator = pendingData.iterator(); iterator.hasNext();)
                {
                    Buffer buffer = ((PendingData)iterator.next()).buffer;
                    try
                    {
                        buffer.skip(buffer.size);
                    }
                    // Misplaced declaration of an exception variable
                    catch (Status status)
                    {
                        throw new AssertionError(status);
                    }
                }

                pendingData = null;
                if (metadata == null)
                {
                    metadata = new Metadata();
                }
                transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(status, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, true, metadata);
                return;
            } else
            {
                transport.finishStream$514KOQBF5TJN4S335T9N8OBKELPJMJ39DSNMESJGCCNMIRJKCLP6SOBC5T1MOQB5DPQ56T3ICLGMQJ39EDQ6ARJ5E8I54S33A1P6UPRICLPN6EQQ9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FCPP62RB5CGNKASJIDTP46RR4CKTKOQBF5TJN4S335T6MAT31CHGN8O9R55B0____0(id, status, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, flag, ErrorCode.CANCEL, metadata);
                return;
            }
        }

        public final void deframeFailed(Throwable throwable)
        {
            http2ProcessingFailed(Status.fromThrowable(throwable), true, new Metadata());
        }

        public final void deframerClosed(boolean flag)
        {
            if (!super.outboundClosed)
            {
                transport.finishStream$514KOQBF5TJN4S335T9N8OBKELPJMJ39DSNMESJGCCNMIRJKCLP6SOBC5T1MOQB5DPQ56T3ICLGMQJ39EDQ6ARJ5E8I54S33A1P6UPRICLPN6EQQ9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FCPP62RB5CGNKASJIDTP46RR4CKTKOQBF5TJN4S335T6MAT31CHGN8O9R55B0____0(id, null, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, false, ErrorCode.CANCEL, null);
            } else
            {
                transport.finishStream$514KOQBF5TJN4S335T9N8OBKELPJMJ39DSNMESJGCCNMIRJKCLP6SOBC5T1MOQB5DPQ56T3ICLGMQJ39EDQ6ARJ5E8I54S33A1P6UPRICLPN6EQQ9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FCPP62RB5CGNKASJIDTP46RR4CKTKOQBF5TJN4S335T6MAT31CHGN8O9R55B0____0(id, null, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, false, null, null);
            }
            super.deframerClosed(flag);
        }

        protected final void http2ProcessingFailed(Status status, boolean flag, Metadata metadata)
        {
            cancel(status, flag, metadata);
        }

        protected final void onStreamAllocated()
        {
            super.onStreamAllocated();
            TransportTracer transporttracer = super.transportTracer;
            transporttracer.streamsStarted = transporttracer.streamsStarted + 1L;
            transporttracer.timeProvider.currentTimeNanos();
        }

        public final void runOnTransportThread(Runnable runnable)
        {
            synchronized (lock)
            {
                runnable.run();
            }
            return;
            runnable;
            obj;
            JVM INSTR monitorexit ;
            throw runnable;
        }

        public final void transportDataReceived(Buffer buffer, boolean flag)
        {
            int i;
            i = 0;
            int j = (int)buffer.size;
            window = window - j;
            if (window >= 0) goto _L2; else goto _L1
_L1:
            buffer = frameWriter;
            i = id;
            ErrorCode errorcode = ErrorCode.FLOW_CONTROL_ERROR;
            ((AsyncFrameWriter) (buffer)).executor.execute(new AsyncFrameWriter._cls8(buffer, i, errorcode));
            transport.finishStream$514KOQBF5TJN4S335T9N8OBKELPJMJ39DSNMESJGCCNMIRJKCLP6SOBC5T1MOQB5DPQ56T3ICLGMQJ39EDQ6ARJ5E8I54S33A1P6UPRICLPN6EQQ9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FCPP62RB5CGNKASJIDTP46RR4CKTKOQBF5TJN4S335T6MAT31CHGN8O9R55B0____0(id, Status.INTERNAL.withDescription("Received data size exceeded our receiving window size"), android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, false, null, null);
_L4:
            return;
_L2:
            Object obj;
            obj = new OkHttpReadableBuffer(buffer);
            if (super.transportError == null)
            {
                break; /* Loop/switch isn't completed */
            }
            Status status = super.transportError;
            buffer = String.valueOf(ReadableBuffers.readAsString(((ReadableBuffer) (obj)), super.errorCharset));
            if (buffer.length() != 0)
            {
                buffer = "DATA-----------------------------\n".concat(buffer);
            } else
            {
                buffer = new String("DATA-----------------------------\n");
            }
            super.transportError = status.augmentDescription(buffer);
            ((ReadableBuffer) (obj)).close();
            if (super.transportError.description.length() > 1000 || flag)
            {
                http2ProcessingFailed(super.transportError, false, super.transportErrorMetadata);
                return;
            }
            if (true) goto _L4; else goto _L3
_L3:
            if (!super.headersReceived)
            {
                http2ProcessingFailed(Status.INTERNAL.withDescription("headers not received before payload"), false, new Metadata());
                return;
            }
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("frame"));
            }
            if (!super.statusReported) goto _L6; else goto _L5
_L5:
            AbstractClientStream.log.logp(Level.INFO, "io.grpc.internal.AbstractClientStream$TransportState", "inboundDataReceived", "Received data on closed stream");
            ((ReadableBuffer) (obj)).close();
_L8:
            if (flag)
            {
                super.transportError = Status.INTERNAL.withDescription("Received unexpected EOS on DATA frame from server.");
                super.transportErrorMetadata = new Metadata();
                buffer = super.transportError;
                obj = super.transportErrorMetadata;
                transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(buffer, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, false, ((Metadata) (obj)));
                return;
            }
            break; /* Loop/switch isn't completed */
_L6:
            try
            {
                super.deframer.deframe(((ReadableBuffer) (obj)));
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Buffer buffer) { }
            finally { }
            deframeFailed(buffer);
            if (true) goto _L8; else goto _L7
_L7:
            if (true) goto _L4; else goto _L9
_L9:
_L11:
            if (i != 0)
            {
                ((ReadableBuffer) (obj)).close();
            }
            throw buffer;
            buffer;
            i = 1;
            if (true) goto _L11; else goto _L10
_L10:
        }

        public TransportState(int i, StatsTraceContext statstracecontext, Object obj, AsyncFrameWriter asyncframewriter, OutboundFlowController outboundflowcontroller, OkHttpClientTransport okhttpclienttransport)
        {
            this$0 = OkHttpClientStream.this;
            super(i, statstracecontext, transportTracer);
            pendingData = new ArrayDeque();
            cancelSent = false;
            window = 65535;
            processedWindow = 65535;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("lock"));
            } else
            {
                lock = obj;
                frameWriter = asyncframewriter;
                outboundFlow = outboundflowcontroller;
                transport = okhttpclienttransport;
                return;
            }
        }
    }

}
