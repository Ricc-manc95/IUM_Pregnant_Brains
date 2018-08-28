// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package io.grpc.internal:
//            ClientStream, NoopClientStream, ClientStreamListener, RetryPolicy

abstract class RetriableStream
    implements ClientStream
{

    public static final Status CANCELLED_BECAUSE_COMMITTED;
    private static final io.grpc.Metadata.Key GRPC_PREVIOUS_RPC_ATTEMPTS;
    public static final io.grpc.Metadata.Key GRPC_RETRY_PUSHBACK_MS;
    public static Random random = new Random();
    public final Executor callExecutor;
    public final long channelBufferLimit;
    public final ChannelBufferMeter channelBufferUsed;
    private final Metadata headers;
    public final Object lock = new Object();
    public ClientStreamListener masterListener;
    public final MethodDescriptor method;
    public long nextBackoffIntervalNanos;
    public boolean noMoreTransparentRetry;
    public final long perRpcBufferLimit;
    public long perRpcBufferUsed;
    public RetryPolicy retryPolicy;
    public final RetryPolicy.Provider retryPolicyProvider;
    public final ScheduledExecutorService scheduledExecutorService;
    public Future scheduledRetry;
    public volatile State state;
    public final Throttle throttle;

    RetriableStream(MethodDescriptor methoddescriptor, Metadata metadata, ChannelBufferMeter channelbuffermeter, long l, long l1, 
            Executor executor, ScheduledExecutorService scheduledexecutorservice, RetryPolicy.Provider provider, Throttle throttle1)
    {
        state = new State(new ArrayList(8), Collections.emptyList(), null, false, false);
        method = methoddescriptor;
        channelBufferUsed = channelbuffermeter;
        perRpcBufferLimit = l;
        channelBufferLimit = l1;
        callExecutor = executor;
        scheduledExecutorService = scheduledexecutorservice;
        headers = metadata;
        if (provider == null)
        {
            throw new NullPointerException(String.valueOf("retryPolicyProvider"));
        } else
        {
            retryPolicyProvider = (RetryPolicy.Provider)provider;
            throttle = throttle1;
            return;
        }
    }

    static boolean hasHedging()
    {
        return false;
    }

    public final void cancel(Status status)
    {
        Object obj = new Substream(0);
        obj.stream = new NoopClientStream();
        obj = commit(((Substream) (obj)));
        if (obj != null)
        {
            Future future = scheduledRetry;
            if (future != null)
            {
                future.cancel(false);
                scheduledRetry = null;
            }
            masterListener.closed(status, new Metadata());
            ((Runnable) (obj)).run();
            return;
        }
        state.winningSubstream.stream.cancel(status);
        synchronized (lock)
        {
            State state1 = state;
            state = new State(state1.buffer, state1.drainedSubstreams, state1.winningSubstream, true, state1.passThrough);
        }
        return;
        exception;
        status;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final Runnable commit(final Substream winningSubstream)
    {
        boolean flag1;
label0:
        {
            flag1 = true;
            synchronized (lock)
            {
                if (state.winningSubstream == null)
                {
                    break label0;
                }
            }
            return null;
        }
        final Collection savedDrainedSubstreams;
        State state1;
        savedDrainedSubstreams = state.drainedSubstreams;
        state1 = state;
        boolean flag;
        if (state1.winningSubstream == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        throw new IllegalStateException(String.valueOf("Already committed"));
        winningSubstream;
        obj2;
        JVM INSTR monitorexit ;
        throw winningSubstream;
        Object obj = state1.buffer;
        if (!state1.drainedSubstreams.contains(winningSubstream)) goto _L2; else goto _L1
_L1:
        Object obj1 = Collections.singleton(winningSubstream);
        obj = null;
_L4:
        state = new State(((List) (obj)), ((Collection) (obj1)), winningSubstream, state1.cancelled, flag1);
        obj = channelBufferUsed;
        long l = -perRpcBufferUsed;
        ((ChannelBufferMeter) (obj)).bufferUsed.addAndGet(l);
        winningSubstream = new _cls1CommitTask();
        obj2;
        JVM INSTR monitorexit ;
        return winningSubstream;
_L2:
        obj1 = Collections.emptyList();
        flag1 = false;
        if (true) goto _L4; else goto _L3
_L3:
    }

    final Substream createSubstream(int i)
    {
        Substream substream = new Substream(i);
        _cls1 _lcls1 = new _cls1();
        Metadata metadata = headers;
        Metadata metadata1 = new Metadata();
        metadata1.merge(metadata);
        if (i > 0)
        {
            metadata1.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(i));
        }
        substream.stream = newSubstream(_lcls1, metadata1);
        return substream;
    }

    final void delayOrExecute(BufferEntry bufferentry)
    {
        Collection collection;
        synchronized (lock)
        {
            if (!state.passThrough)
            {
                state.buffer.add(bufferentry);
            }
            collection = state.drainedSubstreams;
        }
        for (obj = collection.iterator(); ((Iterator) (obj)).hasNext(); bufferentry.runWith((Substream)((Iterator) (obj)).next())) { }
        break MISSING_BLOCK_LABEL_80;
        bufferentry;
        obj;
        JVM INSTR monitorexit ;
        throw bufferentry;
    }

    final void drain(Substream substream)
    {
        Object obj;
        int i;
        boolean flag;
        flag = true;
        obj = null;
        i = 0;
_L14:
        Object obj2 = lock;
        obj2;
        JVM INSTR monitorenter ;
        State state1 = state;
        if (state1.winningSubstream == null || state1.winningSubstream == substream) goto _L2; else goto _L1
_L1:
        substream.stream.cancel(CANCELLED_BECAUSE_COMMITTED);
_L11:
        return;
_L2:
        if (i != state1.buffer.size()) goto _L4; else goto _L3
_L3:
        if (!state1.passThrough)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        throw new IllegalStateException(String.valueOf("Already passThrough"));
        substream;
        obj2;
        JVM INSTR monitorexit ;
        throw substream;
        if (!substream.closed) goto _L6; else goto _L5
_L5:
        obj = state1.drainedSubstreams;
_L7:
        Object obj1;
        boolean flag1;
        if (state1.winningSubstream != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj1 = state1.buffer;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_230;
        }
        int j;
        int k;
        if (state1.winningSubstream == substream)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_490;
        }
        throw new IllegalStateException(String.valueOf("Another RPC attempt has already committed"));
_L6:
        if (!state1.drainedSubstreams.isEmpty())
        {
            break MISSING_BLOCK_LABEL_201;
        }
        obj = Collections.singletonList(substream);
          goto _L7
        obj = new ArrayList(state1.drainedSubstreams);
        ((Collection) (obj)).add(substream);
        obj = Collections.unmodifiableCollection(((Collection) (obj)));
          goto _L7
_L15:
        state = new State(((List) (obj1)), ((Collection) (obj)), state1.winningSubstream, state1.cancelled, flag1);
        obj2;
        JVM INSTR monitorexit ;
        return;
_L4:
        if (!substream.closed)
        {
            break MISSING_BLOCK_LABEL_270;
        }
        obj2;
        JVM INSTR monitorexit ;
        return;
        j = Math.min(i + 128, state1.buffer.size());
        if (obj != null) goto _L9; else goto _L8
_L8:
        obj = new ArrayList(state1.buffer.subList(i, j));
_L12:
        obj2;
        JVM INSTR monitorexit ;
        obj1 = (ArrayList)obj;
        k = ((ArrayList) (obj1)).size();
        i = 0;
_L13:
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_471;
        }
        obj2 = ((ArrayList) (obj1)).get(i);
        i++;
        obj2 = (BufferEntry)obj2;
        state1 = state;
        if (state1.winningSubstream != null && state1.winningSubstream != substream)
        {
            break MISSING_BLOCK_LABEL_471;
        }
        if (!state1.cancelled)
        {
            break MISSING_BLOCK_LABEL_460;
        }
        if (state1.winningSubstream == substream)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L11; else goto _L10
_L10:
        throw new IllegalStateException(String.valueOf("substream should be CANCELLED_BECAUSE_COMMITTED already"));
_L9:
        ((List) (obj)).clear();
        ((List) (obj)).addAll(state1.buffer.subList(i, j));
          goto _L12
        ((BufferEntry) (obj2)).runWith(substream);
          goto _L13
        i = j;
          goto _L14
        obj1 = null;
          goto _L15
    }

    public final void flush()
    {
        State state1 = state;
        if (state1.passThrough)
        {
            state1.winningSubstream.stream.flush();
            return;
        } else
        {
            delayOrExecute(new _cls1FlushEntry());
            return;
        }
    }

    public final void halfClose()
    {
        delayOrExecute(new _cls1HalfCloseEntry());
    }

    abstract ClientStream newSubstream(io.grpc.ClientStreamTracer.Factory factory, Metadata metadata);

    abstract void postCommit();

    abstract Status prestart();

    public final void request(final int numMessages)
    {
        State state1 = state;
        if (state1.passThrough)
        {
            state1.winningSubstream.stream.request(numMessages);
            return;
        } else
        {
            delayOrExecute(new _cls1RequestEntry());
            return;
        }
    }

    public final void setAuthority(final String authority)
    {
        delayOrExecute(new _cls1AuthorityEntry());
    }

    public final void setCompressor(final Compressor compressor)
    {
        delayOrExecute(new _cls1CompressorEntry());
    }

    public final void setDeadline(final Deadline deadline)
    {
        delayOrExecute(new _cls1DeadlineEntry());
    }

    public final void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry)
    {
        delayOrExecute(new _cls1DecompressorRegistryEntry());
    }

    public final void setFullStreamDecompression(final boolean fullStreamDecompression)
    {
        delayOrExecute(new _cls1FullStreamDecompressionEntry());
    }

    public final void setMaxInboundMessageSize(final int maxSize)
    {
        delayOrExecute(new _cls1MaxInboundMessageSizeEntry());
    }

    public final void setMaxOutboundMessageSize(final int maxSize)
    {
        delayOrExecute(new _cls1MaxOutboundMessageSizeEntry());
    }

    public final void start(ClientStreamListener clientstreamlistener)
    {
        masterListener = clientstreamlistener;
        clientstreamlistener = prestart();
        if (clientstreamlistener != null)
        {
            cancel(clientstreamlistener);
            return;
        }
        synchronized (lock)
        {
            state.buffer.add(new _cls1StartEntry());
        }
        drain(createSubstream(0));
        return;
        exception;
        clientstreamlistener;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void writeMessage(InputStream inputstream)
    {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    static 
    {
        GRPC_PREVIOUS_RPC_ATTEMPTS = io.grpc.Metadata.Key.of("grpc-previous-rpc-attempts", Metadata.ASCII_STRING_MARSHALLER);
        GRPC_RETRY_PUSHBACK_MS = io.grpc.Metadata.Key.of("grpc-retry-pushback-ms", Metadata.ASCII_STRING_MARSHALLER);
        CANCELLED_BECAUSE_COMMITTED = Status.CANCELLED.withDescription("Stream thrown away because RetriableStream committed");
    }

    private class State
    {
        private class Substream
        {

            public boolean bufferLimitExceeded;
            public boolean closed;
            public final int previousAttempts;
            public ClientStream stream;

            Substream(int i)
            {
                previousAttempts = i;
            }
        }


        public final List buffer;
        public final boolean cancelled;
        public final Collection drainedSubstreams;
        public final boolean passThrough;
        public final Substream winningSubstream;

        State(List list, Collection collection, Substream substream, boolean flag, boolean flag1)
        {
            boolean flag2;
label0:
            {
                boolean flag3 = false;
                super();
                buffer = list;
                if (collection == null)
                {
                    throw new NullPointerException(String.valueOf("drainedSubstreams"));
                }
                drainedSubstreams = (Collection)collection;
                winningSubstream = substream;
                cancelled = flag;
                passThrough = flag1;
                if (!flag1 || list == null)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (!flag2)
                {
                    throw new IllegalStateException(String.valueOf("passThrough should imply buffer is null"));
                }
                if (!flag1 || substream != null)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (!flag2)
                {
                    throw new IllegalStateException(String.valueOf("passThrough should imply winningSubstream != null"));
                }
                if (!flag1 || collection.size() == 1 && collection.contains(substream) || collection.size() == 0 && substream.closed)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (!flag2)
                {
                    throw new IllegalStateException(String.valueOf("passThrough should imply winningSubstream is drained"));
                }
                if (flag)
                {
                    flag2 = flag3;
                    if (substream == null)
                    {
                        break label0;
                    }
                }
                flag2 = true;
            }
            if (!flag2)
            {
                throw new IllegalStateException(String.valueOf("cancelled should imply committed"));
            } else
            {
                return;
            }
        }
    }


    private class ChannelBufferMeter
    {

        public final AtomicLong bufferUsed = new AtomicLong();

        ChannelBufferMeter()
        {
        }
    }


    private class _cls1CommitTask
        implements Runnable
    {

        private final RetriableStream this$0;
        private final Collection val$savedDrainedSubstreams;
        private final Substream val$winningSubstream;

        public final void run()
        {
            Iterator iterator = savedDrainedSubstreams.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Substream substream = (Substream)iterator.next();
                if (substream != winningSubstream)
                {
                    substream.stream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
                }
            } while (true);
            postCommit();
        }

        _cls1CommitTask()
        {
            this$0 = RetriableStream.this;
            savedDrainedSubstreams = collection;
            winningSubstream = substream;
            super();
        }
    }


    private class _cls1 extends io.grpc.ClientStreamTracer.Factory
    {

        private final ClientStreamTracer val$bufferSizeTracer;

        public final ClientStreamTracer newClientStreamTracer$5166IRPFCTP70OPF8DGMOR2FE1Q6IRREECTKOQBF5TJN4S335T6MAT31CHGN8O9R5566IRPFCTP70OPF8DM6IPBEEH9N8SJ5C5ML8SJ1CDIN4EO_0(Metadata metadata)
        {
            return bufferSizeTracer;
        }

        _cls1()
        {
            bufferSizeTracer = clientstreamtracer;
            super();
        }
    }


    private class BufferSizeTracer extends ClientStreamTracer
    {

        private long bufferNeeded;
        private final Substream substream;
        private final RetriableStream this$0;

        public final void outboundWireSize(long l)
        {
            if (state.winningSubstream == null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            obj = null;
            synchronized (lock)
            {
                if (state.winningSubstream == null && !substream.closed)
                {
                    break MISSING_BLOCK_LABEL_61;
                }
            }
            return;
            obj;
            obj1;
            JVM INSTR monitorexit ;
            throw obj;
            bufferNeeded = bufferNeeded + l;
            if (bufferNeeded > perRpcBufferUsed)
            {
                break MISSING_BLOCK_LABEL_90;
            }
            obj1;
            JVM INSTR monitorexit ;
            return;
            if (bufferNeeded <= perRpcBufferLimit)
            {
                break MISSING_BLOCK_LABEL_149;
            }
            substream.bufferLimitExceeded = true;
_L4:
            if (substream.bufferLimitExceeded)
            {
                obj = commit(substream);
            }
            obj1;
            JVM INSTR monitorexit ;
            if (obj == null) goto _L1; else goto _L3
_L3:
            ((Runnable) (obj)).run();
            return;
            ChannelBufferMeter channelbuffermeter = channelBufferUsed;
            l = bufferNeeded;
            long l1 = perRpcBufferUsed;
            l = channelbuffermeter.bufferUsed.addAndGet(l - l1);
            perRpcBufferUsed = bufferNeeded;
            if (l > channelBufferLimit)
            {
                substream.bufferLimitExceeded = true;
            }
              goto _L4
        }

        BufferSizeTracer(Substream substream1)
        {
            this$0 = RetriableStream.this;
            super();
            substream = substream1;
        }
    }


    private class BufferEntry
    {

        public abstract void runWith(Substream substream);
    }


    private class _cls1FlushEntry
        implements BufferEntry
    {

        public final void runWith(Substream substream)
        {
            substream.stream.flush();
        }

        _cls1FlushEntry()
        {
        }
    }


    private class _cls1HalfCloseEntry
        implements BufferEntry
    {

        public final void runWith(Substream substream)
        {
            substream.stream.halfClose();
        }

        _cls1HalfCloseEntry()
        {
        }
    }


    private class _cls1RequestEntry
        implements BufferEntry
    {

        private final int val$numMessages;

        public final void runWith(Substream substream)
        {
            substream.stream.request(numMessages);
        }

        _cls1RequestEntry()
        {
            numMessages = i;
            super();
        }
    }


    private class _cls1AuthorityEntry
        implements BufferEntry
    {

        private final String val$authority;

        public final void runWith(Substream substream)
        {
            substream.stream.setAuthority(authority);
        }

        _cls1AuthorityEntry()
        {
            authority = s;
            super();
        }
    }


    private class _cls1CompressorEntry
        implements BufferEntry
    {

        private final Compressor val$compressor;

        public final void runWith(Substream substream)
        {
            substream.stream.setCompressor(compressor);
        }

        _cls1CompressorEntry()
        {
            compressor = compressor1;
            super();
        }
    }


    private class _cls1DeadlineEntry
        implements BufferEntry
    {

        private final Deadline val$deadline;

        public final void runWith(Substream substream)
        {
            substream.stream.setDeadline(deadline);
        }

        _cls1DeadlineEntry()
        {
            deadline = deadline1;
            super();
        }
    }


    private class _cls1DecompressorRegistryEntry
        implements BufferEntry
    {

        private final DecompressorRegistry val$decompressorRegistry;

        public final void runWith(Substream substream)
        {
            substream.stream.setDecompressorRegistry(decompressorRegistry);
        }

        _cls1DecompressorRegistryEntry()
        {
            decompressorRegistry = decompressorregistry;
            super();
        }
    }


    private class _cls1FullStreamDecompressionEntry
        implements BufferEntry
    {

        private final boolean val$fullStreamDecompression;

        public final void runWith(Substream substream)
        {
            substream.stream.setFullStreamDecompression(fullStreamDecompression);
        }

        _cls1FullStreamDecompressionEntry()
        {
            fullStreamDecompression = flag;
            super();
        }
    }


    private class _cls1MaxInboundMessageSizeEntry
        implements BufferEntry
    {

        private final int val$maxSize;

        public final void runWith(Substream substream)
        {
            substream.stream.setMaxInboundMessageSize(maxSize);
        }

        _cls1MaxInboundMessageSizeEntry()
        {
            maxSize = i;
            super();
        }
    }


    private class _cls1MaxOutboundMessageSizeEntry
        implements BufferEntry
    {

        private final int val$maxSize;

        public final void runWith(Substream substream)
        {
            substream.stream.setMaxOutboundMessageSize(maxSize);
        }

        _cls1MaxOutboundMessageSizeEntry()
        {
            maxSize = i;
            super();
        }
    }


    private class _cls1StartEntry
        implements BufferEntry
    {

        private final RetriableStream this$0;

        public final void runWith(Substream substream)
        {
            substream.stream.start(new Sublistener(substream));
        }

        _cls1StartEntry()
        {
            this$0 = RetriableStream.this;
            super();
        }

        private class Sublistener
            implements ClientStreamListener
        {

            public final Substream substream;
            public final RetriableStream this$0;

            private final RetryPlan makeRetryDecision(RetryPolicy retrypolicy, Status status, Metadata metadata)
            {
                boolean flag = false;
                boolean flag1 = retrypolicy.retryableStatusCodes.contains(status.code);
                status = (String)metadata.get(RetriableStream.GRPC_RETRY_PUSHBACK_MS);
                int i;
                int j;
                long l;
                if (status != null)
                {
                    try
                    {
                        status = Integer.valueOf(status);
                    }
                    // Misplaced declaration of an exception variable
                    catch (Status status)
                    {
                        status = Integer.valueOf(-1);
                    }
                } else
                {
                    status = null;
                }
                if (throttle == null || !flag1 && (status == null || status.intValue() >= 0))
                {
                    break MISSING_BLOCK_LABEL_293;
                }
                metadata = throttle;
                i = ((Throttle) (metadata)).tokenCount.get();
                if (i == 0)
                {
                    i = 0;
                } else
                {
                    j = i - 1000;
                    if (!((Throttle) (metadata)).tokenCount.compareAndSet(i, Math.max(j, 0)))
                    {
                        break MISSING_BLOCK_LABEL_72;
                    }
                    if (j > ((Throttle) (metadata)).threshold)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                }
                if (i == 0)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
_L9:
                if (retrypolicy.maxAttempts <= substream.previousAttempts + 1 || i != 0) goto _L2; else goto _L1
_L1:
                if (status != null) goto _L4; else goto _L3
_L3:
                if (!flag1) goto _L2; else goto _L5
_L5:
                l = (long)((double)nextBackoffIntervalNanos * RetriableStream.random.nextDouble());
                nextBackoffIntervalNanos = Math.min((long)((double)nextBackoffIntervalNanos * retrypolicy.backoffMultiplier), retrypolicy.maxBackoffNanos);
                flag = true;
_L7:
                return new RetryPlan(flag, l);
_L4:
                if (status.intValue() >= 0)
                {
                    l = TimeUnit.MILLISECONDS.toNanos(status.intValue());
                    nextBackoffIntervalNanos = retrypolicy.initialBackoffNanos;
                    flag = true;
                    continue; /* Loop/switch isn't completed */
                }
_L2:
                l = 0L;
                if (true) goto _L7; else goto _L6
_L6:
                i = 0;
                if (true) goto _L9; else goto _L8
_L8:
            }

            public final void closed(Status status, Metadata metadata)
            {
                closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, metadata);
            }

            public final void closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(Status status, int i, Metadata metadata)
            {
                synchronized (lock)
                {
                    RetriableStream retriablestream = RetriableStream.this;
                    State state1 = state;
                    Object obj1 = substream;
                    obj1.closed = true;
                    if (state1.drainedSubstreams.contains(obj1))
                    {
                        ArrayList arraylist = new ArrayList(state1.drainedSubstreams);
                        arraylist.remove(obj1);
                        obj1 = Collections.unmodifiableCollection(arraylist);
                        state1 = new State(state1.buffer, ((Collection) (obj1)), state1.winningSubstream, state1.cancelled, state1.passThrough);
                    }
                    retriablestream.state = state1;
                }
                if (substream.bufferLimitExceeded)
                {
                    Runnable runnable = commit(substream);
                    if (runnable != null)
                    {
                        runnable.run();
                    }
                    if (state.winningSubstream == substream)
                    {
                        masterListener.closed(status, metadata);
                    }
                } else
                {
                    if (state.winningSubstream == null)
                    {
                        if (i == android.support.v4.content.ModernAsyncTask.Status.REFUSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0 && !noMoreTransparentRetry)
                        {
                            noMoreTransparentRetry = true;
                            class _cls1
                                implements Runnable
                            {

                                private final Sublistener this$1;

                                public final void run()
                                {
                                    Substream substream1 = createSubstream(substream.previousAttempts);
                                    drain(substream1);
                                }

                    _cls1()
                    {
                        this$1 = Sublistener.this;
                        super();
                    }
                            }

                            callExecutor.execute(new _cls1());
                            return;
                        }
                        if (i != android.support.v4.content.ModernAsyncTask.Status.DROPPED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0)
                        {
                            noMoreTransparentRetry = true;
                            if (retryPolicy == null)
                            {
                                retryPolicy = retryPolicyProvider.get();
                                nextBackoffIntervalNanos = retryPolicy.initialBackoffNanos;
                            }
                            RetryPlan retryplan = makeRetryDecision(retryPolicy, status, metadata);
                            if (retryplan.shouldRetry)
                            {
                                class _cls2
                                    implements Runnable
                                {

                                    public final Sublistener this$1;

                                    public final void run()
                                    {
                                        scheduledRetry = null;
                                        class _cls1
                                            implements Runnable
                                        {

                                            private final _cls2 this$2;

                                            public final void run()
                                            {
                                                Substream substream1 = createSubstream(substream.previousAttempts + 1);
                                                drain(substream1);
                                            }

                                                _cls1()
                                                {
                                                    this$2 = _cls2.this;
                                                    super();
                                                }
                                        }

                                        callExecutor.execute(new _cls1());
                                    }

                    _cls2()
                    {
                        this$1 = Sublistener.this;
                        super();
                    }
                                }

                                scheduledRetry = scheduledExecutorService.schedule(new _cls2(), retryplan.backoffNanos, TimeUnit.NANOSECONDS);
                                return;
                            }
                        }
                    }
                    RetriableStream.hasHedging();
                    Runnable runnable1 = commit(substream);
                    if (runnable1 != null)
                    {
                        runnable1.run();
                    }
                    if (state.winningSubstream == substream)
                    {
                        masterListener.closed(status, metadata);
                        return;
                    }
                }
                return;
                status;
                obj;
                JVM INSTR monitorexit ;
                throw status;
            }

            public final void headersRead(Metadata metadata)
            {
                Runnable runnable = commit(substream);
                if (runnable != null)
                {
                    runnable.run();
                }
                if (state.winningSubstream == substream)
                {
                    masterListener.headersRead(metadata);
                    if (throttle != null)
                    {
                        metadata = throttle;
                        int i;
                        int j;
                        do
                        {
                            i = ((Throttle) (metadata)).tokenCount.get();
                            if (i == ((Throttle) (metadata)).maxTokens)
                            {
                                break;
                            }
                            j = ((Throttle) (metadata)).tokenRatio;
                        } while (!((Throttle) (metadata)).tokenCount.compareAndSet(i, Math.min(j + i, ((Throttle) (metadata)).maxTokens)));
                    }
                }
            }

            public final void messagesAvailable(StreamListener.MessageProducer messageproducer)
            {
                State state1 = state;
                boolean flag;
                if (state1.winningSubstream != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalStateException(String.valueOf("Headers should be received prior to messages."));
                }
                if (state1.winningSubstream != substream)
                {
                    return;
                } else
                {
                    masterListener.messagesAvailable(messageproducer);
                    return;
                }
            }

            public final void onReady()
            {
                if (state.drainedSubstreams.contains(substream))
                {
                    masterListener.onReady();
                }
            }

            Sublistener(Substream substream1)
            {
                this$0 = RetriableStream.this;
                super();
                substream = substream1;
            }

            private class Throttle
            {

                public final int maxTokens;
                public final int threshold;
                public final AtomicInteger tokenCount = new AtomicInteger();
                public final int tokenRatio;

                public final boolean equals(Object obj)
                {
                    if (this != obj)
                    {
                        if (!(obj instanceof Throttle))
                        {
                            return false;
                        }
                        obj = (Throttle)obj;
                        if (maxTokens != ((Throttle) (obj)).maxTokens || tokenRatio != ((Throttle) (obj)).tokenRatio)
                        {
                            return false;
                        }
                    }
                    return true;
                }

                public final int hashCode()
                {
                    return Arrays.hashCode(new Object[] {
                        Integer.valueOf(maxTokens), Integer.valueOf(tokenRatio)
                    });
                }

                Throttle(float f, float f1)
                {
                    tokenRatio = (int)(f1 * 1000F);
                    maxTokens = (int)(f * 1000F);
                    threshold = maxTokens / 2;
                    tokenCount.set(maxTokens);
                }
            }


            private class RetryPlan
            {

                public final long backoffNanos;
                public final boolean shouldRetry;

                RetryPlan(boolean flag, long l)
                {
                    shouldRetry = flag;
                    backoffNanos = l;
                }
            }

        }

    }

}
