// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StreamTracer;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package io.grpc.internal:
//            AbstractClientStream, StatsTraceContext, ClientStreamListener, TransportTracer, 
//            Deframer, StreamListener

public static abstract class statsTraceCtx extends statsTraceCtx
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
                if (super.ortTracer != null)
                {
                    metadata = super.ortTracer;
                    if (io.grpc.te.transportTracer == status.code)
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
            if (io.grpc.ortState.trailerStatus != status.code)
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
            transportReportStatus$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(status, android.support.v4.content.MUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, false, metadata);
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
        synchronized (super.yLock)
        {
            super.cated = true;
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

            private final AbstractClientStream.TransportState this$0;
            private final int val$rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0;
            private final Status val$status;
            private final Metadata val$trailers;

            public final void run()
            {
                closeListener$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, trailers);
            }

            _cls1()
            {
                this$0 = AbstractClientStream.TransportState.this;
                status = status1;
                rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0 = i;
                trailers = metadata;
                super();
            }
        }

        deframerClosedTask = new _cls1();
        if (flag)
        {
            super.er.close();
            return;
        } else
        {
            super.er.closeWhenComplete();
            return;
        }
    }

    protected _cls1(int i, StatsTraceContext statstracecontext, TransportTracer transporttracer)
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
