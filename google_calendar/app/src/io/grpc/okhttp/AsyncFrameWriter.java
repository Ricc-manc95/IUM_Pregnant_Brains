// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.internal.SerializingExecutor;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import java.net.Socket;
import java.util.List;
import java.util.logging.Logger;
import okio.Buffer;

// Referenced classes of package io.grpc.okhttp:
//            OkHttpClientTransport

final class AsyncFrameWriter
    implements FrameWriter
{

    public static final Logger log = Logger.getLogger(io/grpc/okhttp/OkHttpClientTransport.getName());
    public final SerializingExecutor executor;
    public FrameWriter frameWriter;
    public Socket socket;
    public final OkHttpClientTransport transport;

    public AsyncFrameWriter(OkHttpClientTransport okhttpclienttransport, SerializingExecutor serializingexecutor)
    {
        transport = okhttpclienttransport;
        executor = serializingexecutor;
    }

    public final void ackSettings(final Settings peerSettings)
    {
        executor.execute(new _cls2());
    }

    final void becomeConnected(FrameWriter framewriter, Socket socket1)
    {
        boolean flag;
        if (frameWriter == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("AsyncFrameWriter's setFrameWriter() should only be called once."));
        }
        if (framewriter == null)
        {
            throw new NullPointerException(String.valueOf("frameWriter"));
        }
        frameWriter = (FrameWriter)framewriter;
        if (socket1 == null)
        {
            throw new NullPointerException(String.valueOf("socket"));
        } else
        {
            socket = (Socket)socket1;
            return;
        }
    }

    public final void close()
    {
        executor.execute(new _cls14());
    }

    public final void connectionPreface()
    {
        executor.execute(new _cls1());
    }

    public final void data(final boolean outFinished, final int streamId, final Buffer source, final int byteCount)
    {
        executor.execute(new _cls9());
    }

    public final void flush()
    {
        executor.execute(new _cls4());
    }

    public final void goAway(final int lastGoodStreamId, final ErrorCode errorCode, final byte debugData[])
    {
        executor.execute(new _cls12());
    }

    public final int maxDataLength()
    {
        if (frameWriter == null)
        {
            return 16384;
        } else
        {
            return frameWriter.maxDataLength();
        }
    }

    public final void ping(final boolean ack, final int payload1, final int payload2)
    {
        executor.execute(new _cls11());
    }

    public final void rstStream(final int streamId, final ErrorCode errorCode)
    {
        executor.execute(new _cls8());
    }

    public final void settings(final Settings okHttpSettings)
    {
        executor.execute(new _cls10());
    }

    public final void synStream(final boolean outFinished, final boolean inFinished, final int streamId, final int associatedStreamId, final List headerBlock)
    {
        executor.execute(new _cls5());
    }

    public final void windowUpdate(final int streamId, final long windowSizeIncrement)
    {
        executor.execute(new _cls13());
    }


    private class _cls2 extends WriteRunnable
    {
        private class WriteRunnable
            implements Runnable
        {

            private final AsyncFrameWriter this$0;

            public abstract void doRun()
                throws IOException;

            public final void run()
            {
                try
                {
                    if (frameWriter == null)
                    {
                        throw new IOException("Unable to perform write due to unavailable frameWriter.");
                    }
                }
                catch (RuntimeException runtimeexception)
                {
                    OkHttpClientTransport okhttpclienttransport = transport;
                    if (runtimeexception == null)
                    {
                        throw new NullPointerException(String.valueOf("failureCause"));
                    } else
                    {
                        Status status = Status.UNAVAILABLE.withCause(runtimeexception);
                        okhttpclienttransport.startGoAway(0, ErrorCode.INTERNAL_ERROR, status);
                        return;
                    }
                }
                catch (Exception exception)
                {
                    OkHttpClientTransport okhttpclienttransport1 = transport;
                    if (exception == null)
                    {
                        throw new NullPointerException(String.valueOf("failureCause"));
                    } else
                    {
                        Status status1 = Status.UNAVAILABLE.withCause(exception);
                        okhttpclienttransport1.startGoAway(0, ErrorCode.INTERNAL_ERROR, status1);
                        return;
                    }
                }
                doRun();
                return;
            }

            private WriteRunnable()
            {
                this$0 = AsyncFrameWriter.this;
                super();
            }

            WriteRunnable(byte byte0)
            {
                this();
            }
        }


        private final AsyncFrameWriter this$0;
        private final Settings val$peerSettings;

        public final void doRun()
            throws IOException
        {
            frameWriter.ackSettings(peerSettings);
        }

        _cls2()
        {
            this$0 = AsyncFrameWriter.this;
            peerSettings = settings1;
            super((byte)0);
        }
    }


    private class _cls14
        implements Runnable
    {

        private final AsyncFrameWriter this$0;

        public final void run()
        {
            if (frameWriter == null)
            {
                break MISSING_BLOCK_LABEL_32;
            }
            frameWriter.close();
            socket.close();
            return;
            IOException ioexception;
            ioexception;
            AsyncFrameWriter.log.logp(Level.WARNING, "io.grpc.okhttp.AsyncFrameWriter$14", "run", "Failed closing connection", ioexception);
            return;
        }

        _cls14()
        {
            this$0 = AsyncFrameWriter.this;
            super();
        }
    }


    private class _cls1 extends WriteRunnable
    {

        private final AsyncFrameWriter this$0;

        public final void doRun()
            throws IOException
        {
            frameWriter.connectionPreface();
        }

        _cls1()
        {
            this$0 = AsyncFrameWriter.this;
            super((byte)0);
        }
    }


    private class _cls9 extends WriteRunnable
    {

        private final AsyncFrameWriter this$0;
        private final int val$byteCount;
        private final boolean val$outFinished;
        private final Buffer val$source;
        private final int val$streamId;

        public final void doRun()
            throws IOException
        {
            frameWriter.data(outFinished, streamId, source, byteCount);
        }

        _cls9()
        {
            this$0 = AsyncFrameWriter.this;
            outFinished = flag;
            streamId = i;
            source = buffer;
            byteCount = j;
            super((byte)0);
        }
    }


    private class _cls4 extends WriteRunnable
    {

        private final AsyncFrameWriter this$0;

        public final void doRun()
            throws IOException
        {
            frameWriter.flush();
        }

        _cls4()
        {
            this$0 = AsyncFrameWriter.this;
            super((byte)0);
        }
    }


    private class _cls12 extends WriteRunnable
    {

        private final AsyncFrameWriter this$0;
        private final byte val$debugData[];
        private final ErrorCode val$errorCode;
        private final int val$lastGoodStreamId;

        public final void doRun()
            throws IOException
        {
            frameWriter.goAway(lastGoodStreamId, errorCode, debugData);
            frameWriter.flush();
        }

        _cls12()
        {
            this$0 = AsyncFrameWriter.this;
            lastGoodStreamId = i;
            errorCode = errorcode;
            debugData = abyte0;
            super((byte)0);
        }
    }


    private class _cls11 extends WriteRunnable
    {

        private final AsyncFrameWriter this$0;
        private final boolean val$ack;
        private final int val$payload1;
        private final int val$payload2;

        public final void doRun()
            throws IOException
        {
            frameWriter.ping(ack, payload1, payload2);
        }

        _cls11()
        {
            this$0 = AsyncFrameWriter.this;
            ack = flag;
            payload1 = i;
            payload2 = j;
            super((byte)0);
        }
    }


    private class _cls8 extends WriteRunnable
    {

        private final AsyncFrameWriter this$0;
        private final ErrorCode val$errorCode;
        private final int val$streamId;

        public final void doRun()
            throws IOException
        {
            frameWriter.rstStream(streamId, errorCode);
        }

        _cls8()
        {
            this$0 = AsyncFrameWriter.this;
            streamId = i;
            errorCode = errorcode;
            super((byte)0);
        }
    }


    private class _cls10 extends WriteRunnable
    {

        private final AsyncFrameWriter this$0;
        private final Settings val$okHttpSettings;

        public final void doRun()
            throws IOException
        {
            frameWriter.settings(okHttpSettings);
        }

        _cls10()
        {
            this$0 = AsyncFrameWriter.this;
            okHttpSettings = settings1;
            super((byte)0);
        }
    }


    private class _cls5 extends WriteRunnable
    {

        private final AsyncFrameWriter this$0;
        private final int val$associatedStreamId;
        private final List val$headerBlock;
        private final boolean val$inFinished;
        private final boolean val$outFinished;
        private final int val$streamId;

        public final void doRun()
            throws IOException
        {
            frameWriter.synStream(outFinished, inFinished, streamId, associatedStreamId, headerBlock);
        }

        _cls5()
        {
            this$0 = AsyncFrameWriter.this;
            outFinished = flag;
            inFinished = flag1;
            streamId = i;
            associatedStreamId = j;
            headerBlock = list;
            super((byte)0);
        }
    }


    private class _cls13 extends WriteRunnable
    {

        private final AsyncFrameWriter this$0;
        private final int val$streamId;
        private final long val$windowSizeIncrement;

        public final void doRun()
            throws IOException
        {
            frameWriter.windowUpdate(streamId, windowSizeIncrement);
        }

        _cls13()
        {
            this$0 = AsyncFrameWriter.this;
            streamId = i;
            windowSizeIncrement = l;
            super((byte)0);
        }
    }

}
