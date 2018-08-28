// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StreamTracer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            AbstractStream, ClientStream, TransportTracer, MessageFramer, 
//            Framer, GrpcUtil, Deframer, ClientStreamListener, 
//            WritableBufferAllocator, StatsTraceContext, WritableBuffer, IoUtils, 
//            StreamListener

public abstract class AbstractClientStream extends AbstractStream
    implements ClientStream, MessageFramer.Sink
{
    final class GetFramer
        implements Framer
    {

        private boolean closed;
        private Metadata headers;
        private byte payload[];
        private final StatsTraceContext statsTraceCtx;
        private final AbstractClientStream this$0;

        public final void close()
        {
            boolean flag = true;
            closed = true;
            if (payload == null)
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("Lack of request message. GET request is only supported for unary requests"));
            } else
            {
                abstractClientStreamSink().writeHeaders(headers, payload);
                payload = null;
                headers = null;
                return;
            }
        }

        public final void flush()
        {
        }

        public final boolean isClosed()
        {
            return closed;
        }

        public final Framer setCompressor(Compressor compressor)
        {
            return this;
        }

        public final void setMaxOutboundMessageSize(int i)
        {
        }

        public final void writePayload(InputStream inputstream)
        {
            boolean flag;
            if (payload == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("writePayload should not be called multiple times"));
            }
            try
            {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                IoUtils.copy(inputstream, bytearrayoutputstream);
                payload = bytearrayoutputstream.toByteArray();
            }
            // Misplaced declaration of an exception variable
            catch (InputStream inputstream)
            {
                throw new RuntimeException(inputstream);
            }
            statsTraceCtx.outboundMessage(0);
            statsTraceCtx.outboundMessageSent(0, payload.length, payload.length);
            statsTraceCtx.outboundUncompressedSize(payload.length);
            statsTraceCtx.outboundWireSize(payload.length);
        }

        public GetFramer(Metadata metadata, StatsTraceContext statstracecontext)
        {
            this$0 = AbstractClientStream.this;
            super();
            if (metadata == null)
            {
                throw new NullPointerException(String.valueOf("headers"));
            }
            headers = (Metadata)metadata;
            if (statstracecontext == null)
            {
                throw new NullPointerException(String.valueOf("statsTraceCtx"));
            } else
            {
                statsTraceCtx = (StatsTraceContext)statstracecontext;
                return;
            }
        }
    }

    public static interface Sink
    {

        public abstract void cancel(Status status);

        public abstract void request(int i);

        public abstract void writeFrame(WritableBuffer writablebuffer, boolean flag, boolean flag1, int i);

        public abstract void writeHeaders(Metadata metadata, byte abyte0[]);
    }

    public static abstract class TransportState extends AbstractStream.TransportState
    {

        public DecompressorRegistry decompressorRegistry;
        private boolean deframerClosed;
        private Runnable deframerClosedTask;
        public boolean fullStreamDecompression;
        public ClientStreamListener listener;
        private boolean listenerClosed;
        public volatile boolean outboundClosed;
        public final StatsTraceContext statsTraceCtx;
        public boolean statusReported;
        public Status trailerStatus;
        public Metadata trailers;

        final void closeListener$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(Status status, int i, Metadata metadata)
        {
label0:
            {
                boolean flag = true;
                if (!listenerClosed)
                {
                    listenerClosed = true;
                    StatsTraceContext statstracecontext = statsTraceCtx;
                    if (statstracecontext.closed.compareAndSet(false, true))
                    {
                        StreamTracer astreamtracer[] = statstracecontext.tracers;
                        int k = astreamtracer.length;
                        for (int j = 0; j < k; j++)
                        {
                            astreamtracer[j].streamClosed$5166IRPFCTP70OPFADQ62T3LECTIILG_0();
                        }

                    }
                    listener.closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, i, metadata);
                    if (super.transportTracer != null)
                    {
                        metadata = super.transportTracer;
                        if (io.grpc.Status.Code.OK == status.code)
                        {
                            i = ((flag) ? 1 : 0);
                        } else
                        {
                            i = 0;
                        }
                        if (i == 0)
                        {
                            break label0;
                        }
                        metadata.streamsSucceeded = ((TransportTracer) (metadata)).streamsSucceeded + 1L;
                    }
                }
                return;
            }
            metadata.streamsFailed = ((TransportTracer) (metadata)).streamsFailed + 1L;
        }

        public void deframerClosed(boolean flag)
        {
            boolean flag1 = true;
            deframerClosed = true;
            if (trailerStatus != null)
            {
                Status status = trailerStatus;
                Metadata metadata;
                if (io.grpc.Status.Code.OK != status.code)
                {
                    flag1 = false;
                }
                if (flag1 && flag)
                {
                    trailerStatus = Status.INTERNAL.withDescription("Encountered end-of-stream mid-frame");
                    trailers = new Metadata();
                }
                status = trailerStatus;
                metadata = trailers;
                transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(status, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, false, metadata);
            } else
            if (!statusReported)
            {
                throw new IllegalStateException(String.valueOf("status should have been reported on deframer closed"));
            }
            if (deframerClosedTask != null)
            {
                deframerClosedTask.run();
                deframerClosedTask = null;
            }
        }

        protected final StreamListener listener()
        {
            return listener;
        }

        public final void transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(final Status status, final int rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, boolean flag, final Metadata trailers)
        {
            if (status == null)
            {
                throw new NullPointerException(String.valueOf("status"));
            }
            if (trailers == null)
            {
                throw new NullPointerException(String.valueOf("trailers"));
            }
            if (statusReported && !flag)
            {
                return;
            }
            statusReported = true;
            synchronized (super.onReadyLock)
            {
                super.deallocated = true;
            }
            if (deframerClosed)
            {
                deframerClosedTask = null;
                closeListener$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, trailers);
                return;
            }
            break MISSING_BLOCK_LABEL_96;
            status;
            obj;
            JVM INSTR monitorexit ;
            throw status;
            class _cls1
                implements Runnable
            {

                private final TransportState this$0;
                private final int val$rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0;
                private final Status val$status;
                private final Metadata val$trailers;

                public final void run()
                {
                    _mth5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, trailers);
                }

                _cls1()
                {
                    this$0 = TransportState.this;
                    status = status1;
                    rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0 = i;
                    trailers = metadata;
                    super();
                }
            }

            deframerClosedTask = new _cls1();
            if (flag)
            {
                super.deframer.close();
                return;
            } else
            {
                super.deframer.closeWhenComplete();
                return;
            }
        }

        protected TransportState(int i, StatsTraceContext statstracecontext, TransportTracer transporttracer)
        {
            super(i, statstracecontext, transporttracer);
            decompressorRegistry = DecompressorRegistry.DEFAULT_INSTANCE;
            deframerClosed = false;
            if (statstracecontext == null)
            {
                throw new NullPointerException(String.valueOf("statsTraceCtx"));
            } else
            {
                statsTraceCtx = (StatsTraceContext)statstracecontext;
                return;
            }
        }
    }


    public static final Logger log = Logger.getLogger(io/grpc/internal/AbstractClientStream.getName());
    private volatile boolean cancelled;
    private final Framer framer;
    private Metadata headers;
    public final TransportTracer transportTracer;
    private boolean useGet;

    public AbstractClientStream(WritableBufferAllocator writablebufferallocator, StatsTraceContext statstracecontext, TransportTracer transporttracer, Metadata metadata, boolean flag)
    {
        if (metadata == null)
        {
            throw new NullPointerException(String.valueOf("headers"));
        }
        if (transporttracer == null)
        {
            throw new NullPointerException(String.valueOf("transportTracer"));
        }
        transportTracer = (TransportTracer)transporttracer;
        useGet = flag;
        if (!flag)
        {
            framer = new MessageFramer(this, writablebufferallocator, statstracecontext);
            headers = metadata;
            return;
        } else
        {
            framer = new GetFramer(metadata, statstracecontext);
            return;
        }
    }

    public abstract Sink abstractClientStreamSink();

    public final void cancel(Status status)
    {
        boolean flag1 = false;
        boolean flag;
        if (io.grpc.Status.Code.OK == status.code)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag1 = true;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf("Should not cancel with OK status"));
        } else
        {
            cancelled = true;
            abstractClientStreamSink().cancel(status);
            return;
        }
    }

    public final void deliverFrame(WritableBuffer writablebuffer, boolean flag, boolean flag1, int i)
    {
        boolean flag2;
        if (writablebuffer != null || flag)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2)
        {
            throw new IllegalArgumentException(String.valueOf("null frame before EOS"));
        } else
        {
            abstractClientStreamSink().writeFrame(writablebuffer, flag, flag1, i);
            return;
        }
    }

    protected final Framer framer()
    {
        return framer;
    }

    public final void halfClose()
    {
        if (!((TransportState)transportState()).outboundClosed)
        {
            ((TransportState)transportState()).outboundClosed = true;
            framer().close();
        }
    }

    public final void request(int i)
    {
        abstractClientStreamSink().request(i);
    }

    public final void setDeadline(Deadline deadline)
    {
        headers.discardAll(GrpcUtil.TIMEOUT_KEY);
        long l = Math.max(0L, deadline.timeRemaining(TimeUnit.NANOSECONDS));
        headers.put(GrpcUtil.TIMEOUT_KEY, Long.valueOf(l));
    }

    public final void setDecompressorRegistry(DecompressorRegistry decompressorregistry)
    {
        TransportState transportstate = (TransportState)transportState();
        boolean flag;
        if (transportstate.listener == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Already called start"));
        }
        if (decompressorregistry == null)
        {
            throw new NullPointerException(String.valueOf("decompressorRegistry"));
        } else
        {
            transportstate.decompressorRegistry = (DecompressorRegistry)decompressorregistry;
            return;
        }
    }

    public final void setFullStreamDecompression(boolean flag)
    {
        ((TransportState)transportState()).fullStreamDecompression = flag;
    }

    public final void setMaxInboundMessageSize(int i)
    {
        ((AbstractStream.TransportState) ((TransportState)transportState())).deframer.setMaxInboundMessageSize(i);
    }

    public final void setMaxOutboundMessageSize(int i)
    {
        framer.setMaxOutboundMessageSize(i);
    }

    public final void start(ClientStreamListener clientstreamlistener)
    {
        TransportState transportstate = (TransportState)transportState();
        boolean flag;
        if (transportstate.listener == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Already called setListener"));
        }
        if (clientstreamlistener == null)
        {
            throw new NullPointerException(String.valueOf("listener"));
        }
        transportstate.listener = (ClientStreamListener)clientstreamlistener;
        if (!useGet)
        {
            abstractClientStreamSink().writeHeaders(headers, null);
            headers = null;
        }
    }

    public abstract TransportState transportState();

    public volatile AbstractStream.TransportState transportState()
    {
        return transportState();
    }

}
