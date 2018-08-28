// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.Status;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package io.grpc.internal:
//            ClientStream, NoopClientStream, ClientStreamListener

class DelayedStream
    implements ClientStream
{
    static final class DelayedStreamListener
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

                private final DelayedStreamListener this$0;
                private final Status val$status;
                private final Metadata val$trailers;

                public final void run()
                {
                    realListener.closed(status, trailers);
                }

                _cls4()
                {
                    this$0 = DelayedStreamListener.this;
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

                private final DelayedStreamListener this$0;
                private final int val$rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0;
                private final Status val$status;
                private final Metadata val$trailers;

                public final void run()
                {
                    realListener.closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, trailers);
                }

                _cls5()
                {
                    this$0 = DelayedStreamListener.this;
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

                private final DelayedStreamListener this$0;
                private final Metadata val$headers;

                public final void run()
                {
                    realListener.headersRead(headers);
                }

                _cls3()
                {
                    this$0 = DelayedStreamListener.this;
                    headers = metadata;
                    super();
                }
            }

            delayOrExecute(new _cls3());
        }

        public final void messagesAvailable(final StreamListener.MessageProducer producer)
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

                    private final DelayedStreamListener this$0;
                    private final StreamListener.MessageProducer val$producer;

                    public final void run()
                    {
                        realListener.messagesAvailable(producer);
                    }

                _cls1()
                {
                    this$0 = DelayedStreamListener.this;
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

                    private final DelayedStreamListener this$0;

                    public final void run()
                    {
                        realListener.onReady();
                    }

                _cls2()
                {
                    this$0 = DelayedStreamListener.this;
                    super();
                }
                }

                delayOrExecute(new _cls2());
                return;
            }
        }

        public DelayedStreamListener(ClientStreamListener clientstreamlistener)
        {
            pendingCallbacks = new ArrayList();
            realListener = clientstreamlistener;
        }
    }


    private DelayedStreamListener delayedListener;
    private Status error;
    private ClientStreamListener listener;
    private volatile boolean passThrough;
    private List pendingCalls;
    public ClientStream realStream;

    DelayedStream()
    {
        pendingCalls = new ArrayList();
    }

    private final void delayOrExecute(Runnable runnable)
    {
        this;
        JVM INSTR monitorenter ;
        if (passThrough)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        pendingCalls.add(runnable);
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

    private final void drainPendingCalls()
    {
        Object obj = new ArrayList();
_L7:
        this;
        JVM INSTR monitorenter ;
        if (!pendingCalls.isEmpty()) goto _L2; else goto _L1
_L1:
        DelayedStreamListener delayedstreamlistener;
        pendingCalls = null;
        passThrough = true;
        delayedstreamlistener = delayedListener;
        this;
        JVM INSTR monitorexit ;
        if (delayedstreamlistener == null) goto _L4; else goto _L3
_L3:
        obj = new ArrayList();
_L8:
        delayedstreamlistener;
        JVM INSTR monitorenter ;
        if (!delayedstreamlistener.pendingCallbacks.isEmpty()) goto _L6; else goto _L5
_L5:
        delayedstreamlistener.pendingCallbacks = null;
        delayedstreamlistener.passThrough = true;
        delayedstreamlistener;
        JVM INSTR monitorexit ;
_L4:
        return;
_L2:
        List list;
        list = pendingCalls;
        pendingCalls = ((List) (obj));
        this;
        JVM INSTR monitorexit ;
        for (obj = list.iterator(); ((Iterator) (obj)).hasNext(); ((Runnable)((Iterator) (obj)).next()).run()) { }
        break MISSING_BLOCK_LABEL_128;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        list.clear();
        obj = list;
          goto _L7
_L6:
        list = delayedstreamlistener.pendingCallbacks;
        delayedstreamlistener.pendingCallbacks = ((List) (obj));
        delayedstreamlistener;
        JVM INSTR monitorexit ;
        for (obj = list.iterator(); ((Iterator) (obj)).hasNext(); ((Runnable)((Iterator) (obj)).next()).run()) { }
        break MISSING_BLOCK_LABEL_189;
        obj;
        delayedstreamlistener;
        JVM INSTR monitorexit ;
        throw obj;
        list.clear();
        obj = list;
          goto _L8
    }

    public void cancel(final Status reason)
    {
        boolean flag;
        if (reason == null)
        {
            throw new NullPointerException(String.valueOf("reason"));
        }
        flag = true;
        ClientStreamListener clientstreamlistener = null;
        this;
        JVM INSTR monitorenter ;
        if (realStream != null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        realStream = NoopClientStream.INSTANCE;
        flag = false;
        clientstreamlistener = listener;
        error = reason;
        this;
        JVM INSTR monitorexit ;
        if (flag)
        {
            delayOrExecute(new _cls8());
            return;
        }
        break MISSING_BLOCK_LABEL_74;
        reason;
        this;
        JVM INSTR monitorexit ;
        throw reason;
        if (clientstreamlistener != null)
        {
            clientstreamlistener.closed(reason, new Metadata());
        }
        drainPendingCalls();
        return;
    }

    public final void flush()
    {
        if (passThrough)
        {
            realStream.flush();
            return;
        } else
        {
            delayOrExecute(new _cls7());
            return;
        }
    }

    public final void halfClose()
    {
        delayOrExecute(new _cls9());
    }

    public final void request(final int numMessages)
    {
        if (passThrough)
        {
            realStream.request(numMessages);
            return;
        } else
        {
            delayOrExecute(new _cls10());
            return;
        }
    }

    public final void setAuthority(final String authority)
    {
        boolean flag;
        if (listener == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("May only be called before start"));
        }
        if (authority == null)
        {
            throw new NullPointerException(String.valueOf("authority"));
        } else
        {
            delayOrExecute(new _cls4());
            return;
        }
    }

    public final void setCompressor(final Compressor compressor)
    {
        if (compressor == null)
        {
            throw new NullPointerException(String.valueOf("compressor"));
        } else
        {
            delayOrExecute(new _cls11());
            return;
        }
    }

    public final void setDeadline(final Deadline deadline)
    {
        delayOrExecute(new _cls3());
    }

    public final void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry)
    {
        if (decompressorRegistry == null)
        {
            throw new NullPointerException(String.valueOf("decompressorRegistry"));
        } else
        {
            delayOrExecute(new _cls13());
            return;
        }
    }

    public final void setFullStreamDecompression(final boolean fullStreamDecompression)
    {
        delayOrExecute(new _cls12());
    }

    public final void setMaxInboundMessageSize(final int maxSize)
    {
        if (passThrough)
        {
            realStream.setMaxInboundMessageSize(maxSize);
            return;
        } else
        {
            delayOrExecute(new _cls1());
            return;
        }
    }

    public final void setMaxOutboundMessageSize(final int maxSize)
    {
        if (passThrough)
        {
            realStream.setMaxOutboundMessageSize(maxSize);
            return;
        } else
        {
            delayOrExecute(new _cls2());
            return;
        }
    }

    final void setStream(ClientStream clientstream)
    {
        this;
        JVM INSTR monitorenter ;
        if (realStream == null)
        {
            break MISSING_BLOCK_LABEL_12;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        if (clientstream != null)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        throw new NullPointerException(String.valueOf("stream"));
        clientstream;
        this;
        JVM INSTR monitorexit ;
        throw clientstream;
        realStream = (ClientStream)clientstream;
        this;
        JVM INSTR monitorexit ;
        drainPendingCalls();
        return;
    }

    public final void start(ClientStreamListener clientstreamlistener)
    {
        boolean flag;
        if (listener == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("already started"));
        }
        this;
        JVM INSTR monitorenter ;
        if (clientstreamlistener != null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        throw new NullPointerException(String.valueOf("listener"));
        clientstreamlistener;
        this;
        JVM INSTR monitorexit ;
        throw clientstreamlistener;
        Status status;
        boolean flag1;
        listener = (ClientStreamListener)clientstreamlistener;
        status = error;
        flag1 = passThrough;
        final Object finalListener;
        finalListener = clientstreamlistener;
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        finalListener = new DelayedStreamListener(clientstreamlistener);
        delayedListener = ((DelayedStreamListener) (finalListener));
        this;
        JVM INSTR monitorexit ;
        if (status != null)
        {
            ((ClientStreamListener) (finalListener)).closed(status, new Metadata());
            return;
        }
        if (flag1)
        {
            realStream.start(((ClientStreamListener) (finalListener)));
            return;
        } else
        {
            delayOrExecute(new _cls5());
            return;
        }
    }

    public final void writeMessage(final InputStream message)
    {
        if (message == null)
        {
            throw new NullPointerException(String.valueOf("message"));
        }
        if (passThrough)
        {
            realStream.writeMessage(message);
            return;
        } else
        {
            delayOrExecute(new _cls6());
            return;
        }
    }

    private class _cls8
        implements Runnable
    {

        private final DelayedStream this$0;
        private final Status val$reason;

        public final void run()
        {
            realStream.cancel(reason);
        }

        _cls8()
        {
            this$0 = DelayedStream.this;
            reason = status;
            super();
        }
    }


    private class _cls7
        implements Runnable
    {

        private final DelayedStream this$0;

        public final void run()
        {
            realStream.flush();
        }

        _cls7()
        {
            this$0 = DelayedStream.this;
            super();
        }
    }


    private class _cls9
        implements Runnable
    {

        private final DelayedStream this$0;

        public final void run()
        {
            realStream.halfClose();
        }

        _cls9()
        {
            this$0 = DelayedStream.this;
            super();
        }
    }


    private class _cls10
        implements Runnable
    {

        private final DelayedStream this$0;
        private final int val$numMessages;

        public final void run()
        {
            realStream.request(numMessages);
        }

        _cls10()
        {
            this$0 = DelayedStream.this;
            numMessages = i;
            super();
        }
    }


    private class _cls4
        implements Runnable
    {

        private final DelayedStream this$0;
        private final String val$authority;

        public final void run()
        {
            realStream.setAuthority(authority);
        }

        _cls4()
        {
            this$0 = DelayedStream.this;
            authority = s;
            super();
        }
    }


    private class _cls11
        implements Runnable
    {

        private final DelayedStream this$0;
        private final Compressor val$compressor;

        public final void run()
        {
            realStream.setCompressor(compressor);
        }

        _cls11()
        {
            this$0 = DelayedStream.this;
            compressor = compressor1;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        private final DelayedStream this$0;
        private final Deadline val$deadline;

        public final void run()
        {
            realStream.setDeadline(deadline);
        }

        _cls3()
        {
            this$0 = DelayedStream.this;
            deadline = deadline1;
            super();
        }
    }


    private class _cls13
        implements Runnable
    {

        private final DelayedStream this$0;
        private final DecompressorRegistry val$decompressorRegistry;

        public final void run()
        {
            realStream.setDecompressorRegistry(decompressorRegistry);
        }

        _cls13()
        {
            this$0 = DelayedStream.this;
            decompressorRegistry = decompressorregistry;
            super();
        }
    }


    private class _cls12
        implements Runnable
    {

        private final DelayedStream this$0;
        private final boolean val$fullStreamDecompression;

        public final void run()
        {
            realStream.setFullStreamDecompression(fullStreamDecompression);
        }

        _cls12()
        {
            this$0 = DelayedStream.this;
            fullStreamDecompression = flag;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final DelayedStream this$0;
        private final int val$maxSize;

        public final void run()
        {
            realStream.setMaxInboundMessageSize(maxSize);
        }

        _cls1()
        {
            this$0 = DelayedStream.this;
            maxSize = i;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final DelayedStream this$0;
        private final int val$maxSize;

        public final void run()
        {
            realStream.setMaxOutboundMessageSize(maxSize);
        }

        _cls2()
        {
            this$0 = DelayedStream.this;
            maxSize = i;
            super();
        }
    }


    private class _cls5
        implements Runnable
    {

        private final DelayedStream this$0;
        private final ClientStreamListener val$finalListener;

        public final void run()
        {
            realStream.start(finalListener);
        }

        _cls5()
        {
            this$0 = DelayedStream.this;
            finalListener = clientstreamlistener;
            super();
        }
    }


    private class _cls6
        implements Runnable
    {

        private final DelayedStream this$0;
        private final InputStream val$message;

        public final void run()
        {
            realStream.writeMessage(message);
        }

        _cls6()
        {
            this$0 = DelayedStream.this;
            message = inputstream;
            super();
        }
    }

}
