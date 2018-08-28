// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import okio.Buffer;

// Referenced classes of package io.grpc.okhttp:
//            OkHttpClientTransport, OkHttpClientStream

class OutboundFlowController
{
    final class OutboundFlowState
    {

        public int allocatedBytes;
        public final Queue pendingWriteQueue;
        public int queuedBytes;
        public OkHttpClientStream stream;
        public final int streamId;
        public final OutboundFlowController this$0;
        public int window;

        final int incrementStreamWindow(int i)
        {
            if (i > 0 && 0x7fffffff - i < window)
            {
                i = streamId;
                throw new IllegalArgumentException((new StringBuilder(44)).append("Window size overflow for stream: ").append(i).toString());
            } else
            {
                window = window + i;
                return window;
            }
        }

        final int writeBytes(int i, WriteStatus writestatus)
        {
            int k;
            int j = Math.min(i, Math.min(window, connectionState.window));
            k = 0;
            do
            {
label0:
                {
                    class Frame
                    {

                        public final Buffer data;
                        private final boolean endStream;
                        public boolean enqueued;
                        public final OutboundFlowState this$1;

                        final Frame split(int i1)
                        {
                            i1 = Math.min(i1, (int)data.size);
                            Object obj = new Buffer();
                            ((Buffer) (obj)).write(data, i1);
                            obj = new Frame(((Buffer) (obj)), false);
                            if (enqueued)
                            {
                                OutboundFlowState outboundflowstate = OutboundFlowState.this;
                                outboundflowstate.queuedBytes = outboundflowstate.queuedBytes - i1;
                            }
                            return ((Frame) (obj));
                        }

                        final void write()
                        {
                            boolean flag1 = true;
_L6:
                            int i1;
                            int j1;
                            j1 = (int)data.size;
                            i1 = Math.min(j1, frameWriter.maxDataLength());
                            if (i1 != j1) goto _L2; else goto _L1
_L1:
                            Object obj;
                            Object obj1;
                            connectionState.incrementStreamWindow(-j1);
                            incrementStreamWindow(-j1);
                            try
                            {
                                frameWriter.data(endStream, streamId, data, j1);
                            }
                            // Misplaced declaration of an exception variable
                            catch (Object obj)
                            {
                                throw new RuntimeException(((Throwable) (obj)));
                            }
                            obj1 = stream.state;
                            obj = ((io.grpc.internal.AbstractStream.TransportState) (obj1)).onReadyLock;
                            obj;
                            JVM INSTR monitorenter ;
                            if (!((io.grpc.internal.AbstractStream.TransportState) (obj1)).allocated)
                            {
                                throw new IllegalStateException(String.valueOf("onStreamAllocated was not called, but it seems the stream is active"));
                            }
                            break MISSING_BLOCK_LABEL_153;
                            obj1;
                            obj;
                            JVM INSTR monitorexit ;
                            throw obj1;
                            boolean flag;
                            if (((io.grpc.internal.AbstractStream.TransportState) (obj1)).numSentBytesQueued < 32768)
                            {
                                i1 = 1;
                            } else
                            {
                                i1 = 0;
                            }
                            obj1.numSentBytesQueued = ((io.grpc.internal.AbstractStream.TransportState) (obj1)).numSentBytesQueued - j1;
                            if (((io.grpc.internal.AbstractStream.TransportState) (obj1)).numSentBytesQueued < 32768)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                              goto _L3
_L4:
                            obj;
                            JVM INSTR monitorexit ;
                            if (i1 != 0)
                            {
                                ((io.grpc.internal.AbstractStream.TransportState) (obj1)).notifyIfReady();
                            }
                            if (enqueued)
                            {
                                OutboundFlowState outboundflowstate = OutboundFlowState.this;
                                outboundflowstate.queuedBytes = outboundflowstate.queuedBytes - j1;
                                pendingWriteQueue.remove(this);
                            }
                            return;
_L8:
                            i1 = 0;
                              goto _L4
_L2:
                            split(i1).write();
                            if ((int)data.size > 0) goto _L6; else goto _L5
_L5:
                            return;
_L3:
                            if (i1 != 0 || !flag) goto _L8; else goto _L7
_L7:
                            i1 = ((flag1) ? 1 : 0);
                              goto _L4
                        }

                Frame(Buffer buffer, boolean flag)
                {
                    this$1 = OutboundFlowState.this;
                    super();
                    data = buffer;
                    endStream = flag;
                }
                    }

                    Frame frame;
                    int l;
                    if (!pendingWriteQueue.isEmpty())
                    {
                        l = 1;
                    } else
                    {
                        l = 0;
                    }
                    if (l == 0)
                    {
                        break label0;
                    }
                    frame = (Frame)pendingWriteQueue.peek();
                    if (j >= (int)frame.data.size)
                    {
                        writestatus.numWrites = writestatus.numWrites + 1;
                        j = (int)frame.data.size + k;
                        frame.write();
                    } else
                    {
                        if (j <= 0)
                        {
                            break label0;
                        }
                        frame = frame.split(j);
                        writestatus.numWrites = writestatus.numWrites + 1;
                        j = (int)frame.data.size + k;
                        frame.write();
                    }
                    l = Math.min(i - j, Math.min(window, connectionState.window));
                    k = j;
                    j = l;
                }
            } while (true);
            return k;
        }

        OutboundFlowState(int i)
        {
            this$0 = OutboundFlowController.this;
            super();
            window = initialWindowSize;
            streamId = i;
            pendingWriteQueue = new ArrayDeque(2);
        }

        OutboundFlowState(OkHttpClientStream okhttpclientstream)
        {
            this(okhttpclientstream.id);
            stream = okhttpclientstream;
        }
    }

    static final class WriteStatus
    {

        public int numWrites;

        WriteStatus()
        {
        }
    }


    public final OutboundFlowState connectionState = new OutboundFlowState(0);
    public final FrameWriter frameWriter;
    public int initialWindowSize;
    public final OkHttpClientTransport transport;

    OutboundFlowController(OkHttpClientTransport okhttpclienttransport, FrameWriter framewriter)
    {
        initialWindowSize = 65535;
        if (okhttpclienttransport == null)
        {
            throw new NullPointerException(String.valueOf("transport"));
        }
        transport = (OkHttpClientTransport)okhttpclienttransport;
        if (framewriter == null)
        {
            throw new NullPointerException(String.valueOf("frameWriter"));
        } else
        {
            frameWriter = (FrameWriter)framewriter;
            return;
        }
    }

    private final OutboundFlowState state(OkHttpClientStream okhttpclientstream)
    {
        OutboundFlowState outboundflowstate1 = (OutboundFlowState)okhttpclientstream.outboundFlowState;
        OutboundFlowState outboundflowstate = outboundflowstate1;
        if (outboundflowstate1 == null)
        {
            outboundflowstate = new OutboundFlowState(okhttpclientstream);
            okhttpclientstream.outboundFlowState = outboundflowstate;
        }
        return outboundflowstate;
    }

    final void data(boolean flag, int i, Buffer buffer, boolean flag1)
    {
        OkHttpClientStream okhttpclientstream;
        if (buffer == null)
        {
            throw new NullPointerException(String.valueOf("source"));
        }
        okhttpclientstream = transport.getStream(i);
        if (okhttpclientstream != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j;
        OutboundFlowState outboundflowstate2 = (OutboundFlowState)okhttpclientstream.outboundFlowState;
        OutboundFlowState outboundflowstate = outboundflowstate2;
        if (outboundflowstate2 == null)
        {
            outboundflowstate = new OutboundFlowState(okhttpclientstream);
            okhttpclientstream.outboundFlowState = outboundflowstate;
        }
        j = Math.min(outboundflowstate.window, outboundflowstate._fld0.connectionState.window);
        if (!outboundflowstate.pendingWriteQueue.isEmpty())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        buffer = outboundflowstate. new OutboundFlowState.Frame(buffer, flag);
        if (i != 0 || j < (int)((OutboundFlowState.Frame) (buffer)).data.size)
        {
            break; /* Loop/switch isn't completed */
        }
        buffer.write();
        if (flag1)
        {
            try
            {
                frameWriter.flush();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Buffer buffer)
            {
                throw new RuntimeException(buffer);
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (!((OutboundFlowState.Frame) (buffer)).enqueued)
        {
            buffer.enqueued = true;
            ((OutboundFlowState.Frame) (buffer))._fld1.pendingWriteQueue.offer(buffer);
            OutboundFlowState outboundflowstate1 = ((OutboundFlowState.Frame) (buffer))._fld1;
            outboundflowstate1.queuedBytes = outboundflowstate1.queuedBytes + (int)((OutboundFlowState.Frame) (buffer)).data.size;
        }
        if (i == 0 && j > 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag1)
        {
            try
            {
                frameWriter.flush();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Buffer buffer)
            {
                throw new RuntimeException(buffer);
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
        buffer.split(j).write();
        if (flag1)
        {
            try
            {
                frameWriter.flush();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Buffer buffer)
            {
                throw new RuntimeException(buffer);
            }
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    final int windowUpdate(OkHttpClientStream okhttpclientstream, int i)
    {
        if (okhttpclientstream == null)
        {
            i = connectionState.incrementStreamWindow(i);
            writeStreams();
            return i;
        }
        OutboundFlowState outboundflowstate1 = (OutboundFlowState)okhttpclientstream.outboundFlowState;
        OutboundFlowState outboundflowstate = outboundflowstate1;
        if (outboundflowstate1 == null)
        {
            outboundflowstate = new OutboundFlowState(okhttpclientstream);
            okhttpclientstream.outboundFlowState = outboundflowstate;
        }
        int j = outboundflowstate.incrementStreamWindow(i);
        okhttpclientstream = new WriteStatus();
        outboundflowstate.writeBytes(Math.min(outboundflowstate.window, outboundflowstate._fld0.connectionState.window), okhttpclientstream);
        if (((WriteStatus) (okhttpclientstream)).numWrites > 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            try
            {
                frameWriter.flush();
            }
            // Misplaced declaration of an exception variable
            catch (OkHttpClientStream okhttpclientstream)
            {
                throw new RuntimeException(okhttpclientstream);
            }
            return j;
        } else
        {
            return j;
        }
    }

    final void writeStreams()
    {
        OkHttpClientStream aokhttpclientstream[] = transport.getActiveStreams();
        int k1 = connectionState.window;
        int i;
        for (int k = aokhttpclientstream.length; k > 0 && k1 > 0; k = i)
        {
            int l1 = (int)Math.ceil((float)k1 / (float)k);
            int i1 = 0;
            i = 0;
            int j1;
            for (; i1 < k && k1 > 0; k1 = j1)
            {
                OkHttpClientStream okhttpclientstream = aokhttpclientstream[i1];
                OutboundFlowState outboundflowstate = state(okhttpclientstream);
                int i2 = Math.min(k1, Math.min(Math.max(0, Math.min(outboundflowstate.window, outboundflowstate.queuedBytes)) - outboundflowstate.allocatedBytes, l1));
                j1 = k1;
                if (i2 > 0)
                {
                    outboundflowstate.allocatedBytes = outboundflowstate.allocatedBytes + i2;
                    j1 = k1 - i2;
                }
                if (Math.max(0, Math.min(outboundflowstate.window, outboundflowstate.queuedBytes)) - outboundflowstate.allocatedBytes > 0)
                {
                    k1 = i + 1;
                    aokhttpclientstream[i] = okhttpclientstream;
                    i = k1;
                }
                i1++;
            }

        }

        WriteStatus writestatus = new WriteStatus();
        OkHttpClientStream aokhttpclientstream1[] = transport.getActiveStreams();
        int l = aokhttpclientstream1.length;
        for (int j = 0; j < l; j++)
        {
            OutboundFlowState outboundflowstate1 = state(aokhttpclientstream1[j]);
            outboundflowstate1.writeBytes(outboundflowstate1.allocatedBytes, writestatus);
            outboundflowstate1.allocatedBytes = 0;
        }

        boolean flag;
        if (writestatus.numWrites > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_278;
        }
        frameWriter.flush();
        return;
        IOException ioexception;
        ioexception;
        throw new RuntimeException(ioexception);
    }
}
