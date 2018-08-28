// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Decompressor;
import java.util.ArrayDeque;
import java.util.Queue;

// Referenced classes of package io.grpc.internal:
//            Deframer, MessageDeframer, ReadableBuffer, GzipInflatingBuffer

public final class ApplicationThreadDeframer
    implements Deframer, MessageDeframer.Listener
{

    public final MessageDeframer deframer;
    public final Queue messageReadQueue = new ArrayDeque();
    public final MessageDeframer.Listener storedListener;
    public final TransportExecutor transportExecutor;

    public ApplicationThreadDeframer(MessageDeframer.Listener listener, TransportExecutor transportexecutor, MessageDeframer messagedeframer)
    {
        if (listener == null)
        {
            throw new NullPointerException(String.valueOf("listener"));
        }
        storedListener = (MessageDeframer.Listener)listener;
        if (transportexecutor == null)
        {
            throw new NullPointerException(String.valueOf("transportExecutor"));
        } else
        {
            transportExecutor = (TransportExecutor)transportexecutor;
            messagedeframer.listener = this;
            deframer = messagedeframer;
            return;
        }
    }

    public final void bytesRead(final int numBytes)
    {
        transportExecutor.runOnTransportThread(new _cls5());
    }

    public final void close()
    {
        deframer.stopDelivery = true;
        storedListener.messagesAvailable(new InitializingMessageProducer(new _cls4()));
    }

    public final void closeWhenComplete()
    {
        storedListener.messagesAvailable(new InitializingMessageProducer(new _cls3()));
    }

    public final void deframe(final ReadableBuffer data)
    {
        storedListener.messagesAvailable(new InitializingMessageProducer(new _cls2()));
    }

    public final void deframeFailed(final Throwable cause)
    {
        transportExecutor.runOnTransportThread(new _cls7());
    }

    public final void deframerClosed(final boolean hasPartialMessage)
    {
        transportExecutor.runOnTransportThread(new _cls6());
    }

    public final void messagesAvailable(StreamListener.MessageProducer messageproducer)
    {
        do
        {
            InputStream inputstream = messageproducer.next();
            if (inputstream != null)
            {
                messageReadQueue.add(inputstream);
            } else
            {
                return;
            }
        } while (true);
    }

    public final void request(final int numMessages)
    {
        storedListener.messagesAvailable(new InitializingMessageProducer(new _cls1()));
    }

    public final void setDecompressor(Decompressor decompressor)
    {
        MessageDeframer messagedeframer = deframer;
        boolean flag;
        if (messagedeframer.fullStreamDecompressor == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Already set full stream decompressor"));
        }
        if (decompressor == null)
        {
            throw new NullPointerException(String.valueOf("Can't pass an empty decompressor"));
        } else
        {
            messagedeframer.decompressor = (Decompressor)decompressor;
            return;
        }
    }

    public final void setFullStreamDecompressor(GzipInflatingBuffer gzipinflatingbuffer)
    {
        deframer.setFullStreamDecompressor(gzipinflatingbuffer);
    }

    public final void setMaxInboundMessageSize(int i)
    {
        deframer.maxInboundMessageSize = i;
    }

    private class TransportExecutor
    {

        public abstract void runOnTransportThread(Runnable runnable);
    }


    private class _cls5
        implements Runnable
    {

        private final ApplicationThreadDeframer this$0;
        private final int val$numBytes;

        public final void run()
        {
            storedListener.bytesRead(numBytes);
        }

        _cls5()
        {
            this$0 = ApplicationThreadDeframer.this;
            numBytes = i;
            super();
        }
    }


    private class InitializingMessageProducer
        implements StreamListener.MessageProducer
    {

        private boolean initialized;
        private final Runnable runnable;
        private final ApplicationThreadDeframer this$0;

        public final InputStream next()
        {
            if (!initialized)
            {
                runnable.run();
                initialized = true;
            }
            return (InputStream)messageReadQueue.poll();
        }

        InitializingMessageProducer(Runnable runnable1)
        {
            this$0 = ApplicationThreadDeframer.this;
            super();
            initialized = false;
            runnable = runnable1;
        }
    }


    private class _cls4
        implements Runnable
    {

        private final ApplicationThreadDeframer this$0;

        public final void run()
        {
            deframer.close();
        }

        _cls4()
        {
            this$0 = ApplicationThreadDeframer.this;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        private final ApplicationThreadDeframer this$0;

        public final void run()
        {
            MessageDeframer messagedeframer;
            boolean flag2;
            flag2 = false;
            boolean flag1 = false;
            messagedeframer = deframer;
            boolean flag;
            if (messagedeframer.unprocessed == null && messagedeframer.fullStreamDecompressor == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag) goto _L2; else goto _L1
_L1:
            if (messagedeframer.fullStreamDecompressor == null) goto _L4; else goto _L3
_L3:
            GzipInflatingBuffer gzipinflatingbuffer = messagedeframer.fullStreamDecompressor;
            flag = flag1;
            if (!gzipinflatingbuffer.closed)
            {
                flag = true;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("GzipInflatingBuffer is closed"));
            }
            flag2 = gzipinflatingbuffer.isStalled;
_L6:
            if (!flag2)
            {
                break; /* Loop/switch isn't completed */
            }
            messagedeframer.close();
_L2:
            return;
_L4:
            if (messagedeframer.unprocessed.readableBytes == 0)
            {
                flag2 = true;
            }
            if (true) goto _L6; else goto _L5
_L5:
            messagedeframer.closeWhenComplete = true;
            return;
        }

        _cls3()
        {
            this$0 = ApplicationThreadDeframer.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final ApplicationThreadDeframer this$0;
        private final ReadableBuffer val$data;

        public final void run()
        {
            try
            {
                deframer.deframe(data);
                return;
            }
            catch (final Throwable cause)
            {
                ApplicationThreadDeframer applicationthreaddeframer = ApplicationThreadDeframer.this;
                applicationthreaddeframer.transportExecutor.runOnTransportThread(applicationthreaddeframer. new _cls7());
                deframer.close();
                return;
            }
        }

        _cls2()
        {
            this$0 = ApplicationThreadDeframer.this;
            data = readablebuffer;
            super();
        }
    }


    private class _cls7
        implements Runnable
    {

        private final ApplicationThreadDeframer this$0;
        private final Throwable val$cause;

        public final void run()
        {
            storedListener.deframeFailed(cause);
        }

        _cls7()
        {
            this$0 = ApplicationThreadDeframer.this;
            cause = throwable;
            super();
        }
    }


    private class _cls6
        implements Runnable
    {

        private final ApplicationThreadDeframer this$0;
        private final boolean val$hasPartialMessage;

        public final void run()
        {
            storedListener.deframerClosed(hasPartialMessage);
        }

        _cls6()
        {
            this$0 = ApplicationThreadDeframer.this;
            hasPartialMessage = flag;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final ApplicationThreadDeframer this$0;
        private final int val$numMessages;

        public final void run()
        {
            MessageDeframer messagedeframer = deframer;
            boolean flag;
            if (messagedeframer.unprocessed == null && messagedeframer.fullStreamDecompressor == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return;
            }
            try
            {
                deframer.request(numMessages);
                return;
            }
            catch (Throwable throwable)
            {
                storedListener.deframeFailed(throwable);
            }
            deframer.close();
        }

        _cls1()
        {
            this$0 = ApplicationThreadDeframer.this;
            numMessages = i;
            super();
        }
    }

}
