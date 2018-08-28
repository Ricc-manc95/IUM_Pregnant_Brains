// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Strings;
import io.grpc.Compressor;
import io.grpc.Drainable;
import io.grpc.KnownLength;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package io.grpc.internal:
//            Framer, WritableBufferAllocator, StatsTraceContext, WritableBuffer, 
//            IoUtils

public final class MessageFramer
    implements Framer
{

    private WritableBuffer buffer;
    public final WritableBufferAllocator bufferAllocator;
    private boolean closed;
    private Compressor compressor;
    private int currentMessageSeqNo;
    private long currentMessageWireSize;
    private final byte headerScratch[] = new byte[5];
    private int maxOutboundMessageSize;
    private boolean messageCompression;
    private int messagesBuffered;
    private final OutputStreamAdapter outputStreamAdapter = new OutputStreamAdapter();
    private final Sink sink;
    private final StatsTraceContext statsTraceCtx;

    public MessageFramer(Sink sink1, WritableBufferAllocator writablebufferallocator, StatsTraceContext statstracecontext)
    {
        maxOutboundMessageSize = -1;
        compressor = io.grpc.Codec.Identity.NONE;
        messageCompression = true;
        currentMessageSeqNo = -1;
        if (sink1 == null)
        {
            throw new NullPointerException(String.valueOf("sink"));
        }
        sink = (Sink)sink1;
        if (writablebufferallocator == null)
        {
            throw new NullPointerException(String.valueOf("bufferAllocator"));
        }
        bufferAllocator = (WritableBufferAllocator)writablebufferallocator;
        if (statstracecontext == null)
        {
            throw new NullPointerException(String.valueOf("statsTraceCtx"));
        } else
        {
            statsTraceCtx = (StatsTraceContext)statstracecontext;
            return;
        }
    }

    private final void writeBufferChain(BufferChainOutputStream bufferchainoutputstream, boolean flag)
    {
        ByteBuffer bytebuffer = ByteBuffer.wrap(headerScratch);
        byte byte0;
        Object obj;
        int i;
        if (flag)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        bytebuffer.put(byte0);
        obj = bufferchainoutputstream.bufferList.iterator();
        for (i = 0; ((Iterator) (obj)).hasNext(); i = ((WritableBuffer)((Iterator) (obj)).next()).readableBytes() + i) { }
        bytebuffer.putInt(i);
        obj = bufferAllocator.allocate(5);
        ((WritableBuffer) (obj)).write(headerScratch, 0, bytebuffer.position());
        if (i == 0)
        {
            buffer = ((WritableBuffer) (obj));
            return;
        }
        sink.deliverFrame(((WritableBuffer) (obj)), false, false, messagesBuffered - 1);
        messagesBuffered = 1;
        bufferchainoutputstream = bufferchainoutputstream.bufferList;
        for (int j = 0; j < bufferchainoutputstream.size() - 1; j++)
        {
            sink.deliverFrame((WritableBuffer)bufferchainoutputstream.get(j), false, false, 0);
        }

        buffer = (WritableBuffer)bufferchainoutputstream.get(bufferchainoutputstream.size() - 1);
        currentMessageWireSize = i;
    }

    private final int writeCompressed$5166KOBMC4NMIRPF95N70TBKADQ74PB1DKTKIAA90(InputStream inputstream)
        throws IOException
    {
        OutputStream outputstream;
        BufferChainOutputStream bufferchainoutputstream;
        bufferchainoutputstream = new BufferChainOutputStream();
        outputstream = compressor.compress(bufferchainoutputstream);
        int i = writeToOutputStream(inputstream, outputstream);
        outputstream.close();
        if (maxOutboundMessageSize >= 0 && i > maxOutboundMessageSize)
        {
            throw new StatusRuntimeException(Status.RESOURCE_EXHAUSTED.withDescription(String.format("message too large %d > %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(maxOutboundMessageSize)
            })));
        } else
        {
            writeBufferChain(bufferchainoutputstream, true);
            return i;
        }
        inputstream;
        outputstream.close();
        throw inputstream;
    }

    private static int writeToOutputStream(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        if (inputstream instanceof Drainable)
        {
            return ((Drainable)inputstream).drainTo(outputstream);
        }
        long l = IoUtils.copy(inputstream, outputstream);
        boolean flag;
        if (l <= 0x7fffffffL)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("Message size overflow: %s", new Object[] {
                Long.valueOf(l)
            }));
        } else
        {
            return (int)l;
        }
    }

    public final void close()
    {
        if (!closed)
        {
            closed = true;
            if (buffer != null && buffer.readableBytes() == 0 && buffer != null)
            {
                buffer.release();
                buffer = null;
            }
            WritableBuffer writablebuffer = buffer;
            buffer = null;
            sink.deliverFrame(writablebuffer, true, true, messagesBuffered);
            messagesBuffered = 0;
        }
    }

    public final void flush()
    {
        if (buffer != null && buffer.readableBytes() > 0)
        {
            WritableBuffer writablebuffer = buffer;
            buffer = null;
            sink.deliverFrame(writablebuffer, false, true, messagesBuffered);
            messagesBuffered = 0;
        }
    }

    public final boolean isClosed()
    {
        return closed;
    }

    public final Framer setCompressor(Compressor compressor1)
    {
        if (compressor1 == null)
        {
            throw new NullPointerException(String.valueOf("Can't pass an empty compressor"));
        } else
        {
            compressor = (Compressor)compressor1;
            return this;
        }
    }

    public final void setMaxOutboundMessageSize(int i)
    {
        boolean flag;
        if (maxOutboundMessageSize == -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("max size already set"));
        } else
        {
            maxOutboundMessageSize = i;
            return;
        }
    }

    public final void writePayload(InputStream inputstream)
    {
        int i;
        int j;
        if (closed)
        {
            throw new IllegalStateException("Framer already closed");
        }
        messagesBuffered = messagesBuffered + 1;
        currentMessageSeqNo = currentMessageSeqNo + 1;
        currentMessageWireSize = 0L;
        statsTraceCtx.outboundMessage(currentMessageSeqNo);
        if (messageCompression && compressor != io.grpc.Codec.Identity.NONE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!(inputstream instanceof KnownLength) && !(inputstream instanceof ByteArrayInputStream)) goto _L2; else goto _L1
_L1:
        j = inputstream.available();
_L5:
        if (j == 0 || i == 0) goto _L4; else goto _L3
_L3:
        BufferChainOutputStream bufferchainoutputstream;
        long l;
        try
        {
            i = writeCompressed$5166KOBMC4NMIRPF95N70TBKADQ74PB1DKTKIAA90(inputstream);
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            throw new StatusRuntimeException(Status.INTERNAL.withDescription("Failed to frame message").withCause(inputstream));
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            throw new StatusRuntimeException(Status.INTERNAL.withDescription("Failed to frame message").withCause(inputstream));
        }
_L6:
        if (j != -1 && i != j)
        {
            inputstream = String.format("Message length inaccurate %s != %s", new Object[] {
                Integer.valueOf(i), Integer.valueOf(j)
            });
            throw new StatusRuntimeException(Status.INTERNAL.withDescription(inputstream));
        } else
        {
            statsTraceCtx.outboundUncompressedSize(i);
            statsTraceCtx.outboundWireSize(currentMessageWireSize);
            statsTraceCtx.outboundMessageSent(currentMessageSeqNo, currentMessageWireSize, i);
            return;
        }
_L2:
        j = -1;
          goto _L5
_L4:
        if (j == -1)
        {
            break MISSING_BLOCK_LABEL_341;
        }
        l = j;
        currentMessageWireSize = l;
        if (maxOutboundMessageSize >= 0 && j > maxOutboundMessageSize)
        {
            throw new StatusRuntimeException(Status.RESOURCE_EXHAUSTED.withDescription(String.format("message too large %d > %d", new Object[] {
                Integer.valueOf(j), Integer.valueOf(maxOutboundMessageSize)
            })));
        }
        ByteBuffer bytebuffer = ByteBuffer.wrap(headerScratch);
        bytebuffer.put((byte)0);
        bytebuffer.putInt(j);
        if (buffer == null)
        {
            buffer = bufferAllocator.allocate(bytebuffer.position() + j);
        }
        writeRaw(headerScratch, 0, bytebuffer.position());
        i = writeToOutputStream(inputstream, outputStreamAdapter);
        break MISSING_BLOCK_LABEL_108;
        bufferchainoutputstream = new BufferChainOutputStream();
        i = writeToOutputStream(inputstream, bufferchainoutputstream);
        if (maxOutboundMessageSize >= 0 && i > maxOutboundMessageSize)
        {
            throw new StatusRuntimeException(Status.RESOURCE_EXHAUSTED.withDescription(String.format("message too large %d > %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(maxOutboundMessageSize)
            })));
        }
        writeBufferChain(bufferchainoutputstream, false);
          goto _L6
    }

    final void writeRaw(byte abyte0[], int i, int j)
    {
        int k;
        for (; j > 0; j -= k)
        {
            if (buffer != null && buffer.writableBytes() == 0)
            {
                WritableBuffer writablebuffer = buffer;
                buffer = null;
                sink.deliverFrame(writablebuffer, false, false, messagesBuffered);
                messagesBuffered = 0;
            }
            if (buffer == null)
            {
                buffer = bufferAllocator.allocate(j);
            }
            k = Math.min(j, buffer.writableBytes());
            buffer.write(abyte0, i, k);
            i += k;
        }

    }

    private class OutputStreamAdapter extends OutputStream
    {

        private final MessageFramer this$0;

        public final void write(int i)
        {
            write(new byte[] {
                (byte)i
            }, 0, 1);
        }

        public final void write(byte abyte0[], int i, int j)
        {
            writeRaw(abyte0, i, j);
        }

        OutputStreamAdapter()
        {
            this$0 = MessageFramer.this;
            super();
        }
    }


    private class Sink
    {

        public abstract void deliverFrame(WritableBuffer writablebuffer, boolean flag, boolean flag1, int i);
    }


    private class BufferChainOutputStream extends OutputStream
    {

        public final List bufferList = new ArrayList();
        private WritableBuffer current;
        private final MessageFramer this$0;

        public final void write(int i)
            throws IOException
        {
            if (current != null && current.writableBytes() > 0)
            {
                current.write((byte)i);
                return;
            } else
            {
                write(new byte[] {
                    (byte)i
                }, 0, 1);
                return;
            }
        }

        public final void write(byte abyte0[], int i, int j)
        {
            int k = i;
            int l = j;
            if (current == null)
            {
                current = bufferAllocator.allocate(j);
                bufferList.add(current);
                l = j;
                k = i;
            }
            while (l > 0) 
            {
                i = Math.min(l, current.writableBytes());
                if (i == 0)
                {
                    i = Math.max(l, current.readableBytes() << 1);
                    current = bufferAllocator.allocate(i);
                    bufferList.add(current);
                } else
                {
                    current.write(abyte0, k, i);
                    k += i;
                    l -= i;
                }
            }
        }

        BufferChainOutputStream()
        {
            this$0 = MessageFramer.this;
            super();
        }
    }

}
