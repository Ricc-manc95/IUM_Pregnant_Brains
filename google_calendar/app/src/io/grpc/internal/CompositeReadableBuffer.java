// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

// Referenced classes of package io.grpc.internal:
//            AbstractReadableBuffer, ReadableBuffer

public final class CompositeReadableBuffer extends AbstractReadableBuffer
{

    private final Queue buffers = new ArrayDeque();
    public int readableBytes;

    public CompositeReadableBuffer()
    {
    }

    private final void advanceBufferIfNecessary()
    {
        if (((ReadableBuffer)buffers.peek()).readableBytes() == 0)
        {
            ((ReadableBuffer)buffers.remove()).close();
        }
    }

    public final void addBuffer(ReadableBuffer readablebuffer)
    {
        if (!(readablebuffer instanceof CompositeReadableBuffer))
        {
            buffers.add(readablebuffer);
            readableBytes = readableBytes + readablebuffer.readableBytes();
            return;
        }
        ReadableBuffer readablebuffer1;
        for (readablebuffer = (CompositeReadableBuffer)readablebuffer; !((CompositeReadableBuffer) (readablebuffer)).buffers.isEmpty(); buffers.add(readablebuffer1))
        {
            readablebuffer1 = (ReadableBuffer)((CompositeReadableBuffer) (readablebuffer)).buffers.remove();
        }

        readableBytes = readableBytes + ((CompositeReadableBuffer) (readablebuffer)).readableBytes;
        readablebuffer.readableBytes = 0;
        readablebuffer.close();
    }

    public final void close()
    {
        for (; !buffers.isEmpty(); ((ReadableBuffer)buffers.remove()).close()) { }
    }

    final void execute(ReadOperation readoperation, int i)
    {
        int j;
        if (readableBytes() < i)
        {
            throw new IndexOutOfBoundsException();
        }
        j = i;
        if (!buffers.isEmpty())
        {
            advanceBufferIfNecessary();
            j = i;
        }
_L7:
        if (j <= 0 || buffers.isEmpty()) goto _L2; else goto _L1
_L1:
        int k;
        ReadableBuffer readablebuffer = (ReadableBuffer)buffers.peek();
        k = Math.min(j, readablebuffer.readableBytes());
        try
        {
            readoperation.value = readoperation.readInternal(readablebuffer, k);
        }
        catch (IOException ioexception)
        {
            readoperation.ex = ioexception;
        }
        if (readoperation.ex != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L4; else goto _L3
_L3:
        return;
_L4:
        j -= k;
        readableBytes = readableBytes - k;
        advanceBufferIfNecessary();
        continue; /* Loop/switch isn't completed */
_L2:
        if (j <= 0) goto _L3; else goto _L5
_L5:
        throw new AssertionError("Failed executing read operation");
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final ReadableBuffer readBytes(int i)
    {
        if (readableBytes() < i)
        {
            throw new IndexOutOfBoundsException();
        }
        readableBytes = readableBytes - i;
        CompositeReadableBuffer compositereadablebuffer = new CompositeReadableBuffer();
        while (i > 0) 
        {
            ReadableBuffer readablebuffer = (ReadableBuffer)buffers.peek();
            if (readablebuffer.readableBytes() > i)
            {
                compositereadablebuffer.addBuffer(readablebuffer.readBytes(i));
                i = 0;
            } else
            {
                compositereadablebuffer.addBuffer((ReadableBuffer)buffers.poll());
                i -= readablebuffer.readableBytes();
            }
        }
        return compositereadablebuffer;
    }

    public final void readBytes(final byte dest[], final int destOffset, int i)
    {
        execute(new _cls3(), i);
    }

    public final int readUnsignedByte()
    {
        _cls1 _lcls1 = new _cls1();
        execute(_lcls1, 1);
        return ((ReadOperation) (_lcls1)).value;
    }

    public final int readableBytes()
    {
        return readableBytes;
    }

    private class ReadOperation
    {

        public IOException ex;
        public int value;

        abstract int readInternal(ReadableBuffer readablebuffer, int i)
            throws IOException;

        private ReadOperation()
        {
        }

        ReadOperation(byte byte0)
        {
            this();
        }
    }


    private class _cls3 extends ReadOperation
    {

        private int currentOffset;
        private final byte val$dest[];
        private final int val$destOffset;

        public final int readInternal(ReadableBuffer readablebuffer, int i)
        {
            readablebuffer.readBytes(dest, currentOffset, i);
            currentOffset = currentOffset + i;
            return 0;
        }

        _cls3()
        {
            destOffset = i;
            dest = abyte0;
            super((byte)0);
            currentOffset = destOffset;
        }
    }


    private class _cls1 extends ReadOperation
    {

        final int readInternal(ReadableBuffer readablebuffer, int i)
        {
            return readablebuffer.readUnsignedByte();
        }

        _cls1()
        {
            super((byte)0);
        }
    }

}
