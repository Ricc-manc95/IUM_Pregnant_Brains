// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import java.util.ArrayDeque;
import java.util.Queue;
import okio.Buffer;

// Referenced classes of package io.grpc.okhttp:
//            OutboundFlowController, OkHttpClientStream

final class stream
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

    final int writeBytes(int i, window window1)
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
                    public final OutboundFlowController.OutboundFlowState this$1;

                    final Frame split(int i1)
                    {
                        i1 = Math.min(i1, (int)data.size);
                        Object obj = new Buffer();
                        ((Buffer) (obj)).write(data, i1);
                        obj = new Frame(((Buffer) (obj)), false);
                        if (enqueued)
                        {
                            OutboundFlowController.OutboundFlowState outboundflowstate = OutboundFlowController.OutboundFlowState.this;
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
                            OutboundFlowController.OutboundFlowState outboundflowstate = OutboundFlowController.OutboundFlowState.this;
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
                this$1 = OutboundFlowController.OutboundFlowState.this;
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
                    window1.tes = window1.tes + 1;
                    j = (int)frame.data.size + k;
                    frame.write();
                } else
                {
                    if (j <= 0)
                    {
                        break label0;
                    }
                    frame = frame.split(j);
                    window1.tes = window1.tes + 1;
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

    Frame(int i)
    {
        this$0 = OutboundFlowController.this;
        super();
        window = initialWindowSize;
        streamId = i;
        pendingWriteQueue = new ArrayDeque(2);
    }

    pendingWriteQueue(OkHttpClientStream okhttpclientstream)
    {
        this(okhttpclientstream.id);
        stream = okhttpclientstream;
    }
}
