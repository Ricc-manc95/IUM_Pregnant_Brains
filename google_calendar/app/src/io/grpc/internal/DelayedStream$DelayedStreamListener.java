// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package io.grpc.internal:
//            ClientStreamListener, DelayedStream

static final class realListener
    implements ClientStreamListener
{

    public volatile boolean passThrough;
    public List pendingCallbacks;
    public final ClientStreamListener realListener;

    private final void delayOrExecute(Runnable runnable)
    {
        this;
        JVM INSTR monitorenter ;
        if (passThrough)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        pendingCallbacks.add(runnable);
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        runnable.run();
        return;
        runnable;
        this;
        JVM INSTR monitorexit ;
        throw runnable;
    }

    public final void closed(final Status status, final Metadata trailers)
    {
        class _cls4
            implements Runnable
        {

            private final DelayedStream.DelayedStreamListener this$0;
            private final Status val$status;
            private final Metadata val$trailers;

            public final void run()
            {
                realListener.closed(status, trailers);
            }

            _cls4()
            {
                this$0 = DelayedStream.DelayedStreamListener.this;
                status = status1;
                trailers = metadata;
                super();
            }
        }

        delayOrExecute(new _cls4());
    }

    public final void closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(final Status status, final int rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, final Metadata trailers)
    {
        class _cls5
            implements Runnable
        {

            private final DelayedStream.DelayedStreamListener this$0;
            private final int val$rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0;
            private final Status val$status;
            private final Metadata val$trailers;

            public final void run()
            {
                realListener.closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, trailers);
            }

            _cls5()
            {
                this$0 = DelayedStream.DelayedStreamListener.this;
                status = status1;
                rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0 = i;
                trailers = metadata;
                super();
            }
        }

        delayOrExecute(new _cls5());
    }

    public final void headersRead(final Metadata headers)
    {
        class _cls3
            implements Runnable
        {

            private final DelayedStream.DelayedStreamListener this$0;
            private final Metadata val$headers;

            public final void run()
            {
                realListener.headersRead(headers);
            }

            _cls3()
            {
                this$0 = DelayedStream.DelayedStreamListener.this;
                headers = metadata;
                super();
            }
        }

        delayOrExecute(new _cls3());
    }

    public final void messagesAvailable(final _cls3 producer)
    {
        if (passThrough)
        {
            realListener.messagesAvailable(producer);
            return;
        } else
        {
            class _cls1
                implements Runnable
            {

                private final DelayedStream.DelayedStreamListener this$0;
                private final StreamListener.MessageProducer val$producer;

                public final void run()
                {
                    realListener.messagesAvailable(producer);
                }

            _cls1()
            {
                this$0 = DelayedStream.DelayedStreamListener.this;
                producer = messageproducer;
                super();
            }
            }

            delayOrExecute(new _cls1());
            return;
        }
    }

    public final void onReady()
    {
        if (passThrough)
        {
            realListener.onReady();
            return;
        } else
        {
            class _cls2
                implements Runnable
            {

                private final DelayedStream.DelayedStreamListener this$0;

                public final void run()
                {
                    realListener.onReady();
                }

            _cls2()
            {
                this$0 = DelayedStream.DelayedStreamListener.this;
                super();
            }
            }

            delayOrExecute(new _cls2());
            return;
        }
    }

    public _cls2(ClientStreamListener clientstreamlistener)
    {
        pendingCallbacks = new ArrayList();
        realListener = clientstreamlistener;
    }
}
