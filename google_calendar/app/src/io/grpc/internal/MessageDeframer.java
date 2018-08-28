// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Decompressor;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.StreamTracer;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;

// Referenced classes of package io.grpc.internal:
//            Deframer, CompositeReadableBuffer, StatsTraceContext, TransportTracer, 
//            AbstractReadableBuffer, LongCounter, TimeProvider, ReadableBuffers, 
//            GzipInflatingBuffer, ReadableBuffer

public final class MessageDeframer
    implements Deframer, Closeable
{

    public boolean closeWhenComplete;
    private boolean compressedFlag;
    private int currentMessageSeqNo;
    public Decompressor decompressor;
    public GzipInflatingBuffer fullStreamDecompressor;
    private boolean inDelivery;
    private int inboundBodyWireSize;
    private byte inflatedBuffer[];
    private int inflatedIndex;
    public Listener listener;
    public int maxInboundMessageSize;
    private CompositeReadableBuffer nextFrame;
    private long pendingDeliveries;
    private int requiredLength;
    private State state;
    private final StatsTraceContext statsTraceCtx;
    public volatile boolean stopDelivery;
    private final TransportTracer transportTracer;
    public CompositeReadableBuffer unprocessed;

    public MessageDeframer(Listener listener1, Decompressor decompressor1, int i, StatsTraceContext statstracecontext, TransportTracer transporttracer)
    {
        state = State.HEADER;
        requiredLength = 5;
        unprocessed = new CompositeReadableBuffer();
        inDelivery = false;
        currentMessageSeqNo = -1;
        closeWhenComplete = false;
        stopDelivery = false;
        if (listener1 == null)
        {
            throw new NullPointerException(String.valueOf("sink"));
        }
        listener = (Listener)listener1;
        if (decompressor1 == null)
        {
            throw new NullPointerException(String.valueOf("decompressor"));
        }
        decompressor = (Decompressor)decompressor1;
        maxInboundMessageSize = i;
        if (statstracecontext == null)
        {
            throw new NullPointerException(String.valueOf("statsTraceCtx"));
        }
        statsTraceCtx = (StatsTraceContext)statstracecontext;
        if (transporttracer == null)
        {
            throw new NullPointerException(String.valueOf("transportTracer"));
        } else
        {
            transportTracer = (TransportTracer)transporttracer;
            return;
        }
    }

    private final void deliver()
    {
        if (inDelivery)
        {
            return;
        }
        inDelivery = true;
_L10:
        if (stopDelivery || pendingDeliveries <= 0L || !readRequiredBytes()) goto _L2; else goto _L1
_L1:
        state.ordinal();
        JVM INSTR tableswitch 0 1: default 570
    //                   0 117
    //                   1 383;
           goto _L3 _L4 _L5
_L3:
        String s = String.valueOf(state);
        throw new AssertionError((new StringBuilder(String.valueOf(s).length() + 15)).append("Invalid state: ").append(s).toString());
        Exception exception;
        exception;
        inDelivery = false;
        throw exception;
_L4:
        int i;
        CompositeReadableBuffer compositereadablebuffer = nextFrame;
        CompositeReadableBuffer._cls1 _lcls1 = new CompositeReadableBuffer._cls1();
        compositereadablebuffer.execute(_lcls1, 1);
        i = ((CompositeReadableBuffer.ReadOperation) (_lcls1)).value;
        if ((i & 0xfe) == 0) goto _L7; else goto _L6
_L6:
        throw new StatusRuntimeException(Status.INTERNAL.withDescription("gRPC frame header malformed: reserved bits not zero"));
_L18:
        StreamTracer astreamtracer[];
        int j;
        boolean flag;
        compressedFlag = flag;
        Object obj = nextFrame;
        if (((AbstractReadableBuffer) (obj)).readableBytes() < 4)
        {
            throw new IndexOutOfBoundsException();
        }
        i = ((AbstractReadableBuffer) (obj)).readUnsignedByte();
        j = ((AbstractReadableBuffer) (obj)).readUnsignedByte();
        int k = ((AbstractReadableBuffer) (obj)).readUnsignedByte();
        requiredLength = ((AbstractReadableBuffer) (obj)).readUnsignedByte() | (i << 24 | j << 16 | k << 8);
        if (requiredLength < 0 || requiredLength > maxInboundMessageSize)
        {
            throw new StatusRuntimeException(Status.RESOURCE_EXHAUSTED.withDescription(String.format("gRPC message exceeds maximum size %d: %d", new Object[] {
                Integer.valueOf(maxInboundMessageSize), Integer.valueOf(requiredLength)
            })));
        }
        currentMessageSeqNo = currentMessageSeqNo + 1;
        obj = statsTraceCtx;
        i = currentMessageSeqNo;
        astreamtracer = ((StatsTraceContext) (obj)).tracers;
        j = astreamtracer.length;
        i = 0;
_L9:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        astreamtracer[i].inboundMessage$514IILG_0();
        i++;
        if (true) goto _L9; else goto _L8
_L8:
        TransportTracer transporttracer = transportTracer;
        transporttracer.messagesReceived.add(1L);
        transporttracer.lastMessageReceivedTimeNanos = transporttracer.timeProvider.currentTimeNanos();
        state = State.BODY;
          goto _L10
_L5:
        int l;
        long l1;
        StatsTraceContext statstracecontext = statsTraceCtx;
        j = currentMessageSeqNo;
        l1 = inboundBodyWireSize;
        statstracecontext = statstracecontext.tracers;
        l = statstracecontext.length;
        i = 0;
_L12:
        if (i >= l)
        {
            break; /* Loop/switch isn't completed */
        }
        statstracecontext[i].inboundMessageRead(j, l1, -1L);
        i++;
        if (true) goto _L12; else goto _L11
_L11:
        inboundBodyWireSize = 0;
        if (!compressedFlag) goto _L14; else goto _L13
_L13:
        InputStream inputstream = getCompressedBody();
_L15:
        nextFrame = null;
        listener.messagesAvailable(new SingleMessageProducer(inputstream));
        state = State.HEADER;
        requiredLength = 5;
        pendingDeliveries = pendingDeliveries - 1L;
          goto _L10
_L14:
        statsTraceCtx.inboundUncompressedSize(nextFrame.readableBytes);
        inputstream = ReadableBuffers.openStream(nextFrame, true);
          goto _L15
_L2:
        if (!stopDelivery) goto _L17; else goto _L16
_L16:
        close();
        inDelivery = false;
        return;
_L17:
        if (closeWhenComplete && isStalled())
        {
            close();
        }
        inDelivery = false;
        return;
_L7:
        if ((i & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L18
    }

    private final InputStream getCompressedBody()
    {
        if (decompressor == io.grpc.Codec.Identity.NONE)
        {
            throw new StatusRuntimeException(Status.INTERNAL.withDescription("Can't decode compressed gRPC message as compression not configured"));
        }
        SizeEnforcingInputStream sizeenforcinginputstream;
        try
        {
            sizeenforcinginputstream = new SizeEnforcingInputStream(decompressor.decompress(ReadableBuffers.openStream(nextFrame, true)), maxInboundMessageSize, statsTraceCtx);
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }
        return sizeenforcinginputstream;
    }

    private final boolean isStalled()
    {
        boolean flag1 = true;
        boolean flag = true;
        if (fullStreamDecompressor != null)
        {
            GzipInflatingBuffer gzipinflatingbuffer = fullStreamDecompressor;
            if (gzipinflatingbuffer.closed)
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("GzipInflatingBuffer is closed"));
            }
            flag1 = gzipinflatingbuffer.isStalled;
        } else
        if (unprocessed.readableBytes != 0)
        {
            return false;
        }
        return flag1;
    }

    private final boolean readRequiredBytes()
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        k1 = 0;
        i1 = 0;
        l1 = 0;
        j1 = 0;
        k = j1;
        i = i1;
        l = l1;
        j = k1;
        if (nextFrame != null)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        l = l1;
        j = k1;
        nextFrame = new CompositeReadableBuffer();
        i = i1;
        k = j1;
_L80:
        l = k;
        j = i;
        int i2 = requiredLength - nextFrame.readableBytes;
        if (i2 <= 0) goto _L2; else goto _L1
_L1:
        l = k;
        j = i;
        Object obj = fullStreamDecompressor;
        if (obj == null) goto _L4; else goto _L3
_L3:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (inflatedBuffer == null)
        {
            break MISSING_BLOCK_LABEL_173;
        }
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (inflatedIndex != inflatedBuffer.length)
        {
            break MISSING_BLOCK_LABEL_240;
        }
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        inflatedBuffer = new byte[Math.min(i2, 0x200000)];
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        inflatedIndex = 0;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        int i3 = Math.min(i2, inflatedBuffer.length - inflatedIndex);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj = fullStreamDecompressor;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        byte abyte0[] = inflatedBuffer;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        int j3 = inflatedIndex;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (!((GzipInflatingBuffer) (obj)).closed)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            break MISSING_BLOCK_LABEL_527;
        }
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        throw new IllegalStateException(String.valueOf("GzipInflatingBuffer is closed"));
        obj;
        l = i1;
        j = j1;
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        int j2;
        int l2;
        boolean flag;
        if (j > 0)
        {
            listener.bytesRead(j);
            GzipInflatingBuffer.GzipMetadataReader gzipmetadatareader;
            Object obj1;
            GzipInflatingBuffer gzipinflatingbuffer;
            byte abyte1[];
            int k2;
            int k3;
            if (state == State.BODY)
            {
                if (fullStreamDecompressor != null)
                {
                    statsTraceCtx.inboundWireSize(l);
                    inboundBodyWireSize = l + inboundBodyWireSize;
                } else
                {
                    statsTraceCtx.inboundWireSize(j);
                    inboundBodyWireSize = inboundBodyWireSize + j;
                }
            }
        }
        throw obj;
        i2 = 0;
        flag = true;
_L21:
        if (!flag) goto _L6; else goto _L5
_L5:
        j2 = i3 - i2;
        if (j2 <= 0) goto _L6; else goto _L7
_L7:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer) (obj)).state.ordinal();
        JVM INSTR tableswitch 0 9: default 5177
    //                   0 747
    //                   1 1644
    //                   2 1994
    //                   3 2683
    //                   4 2875
    //                   5 3067
    //                   6 3454
    //                   7 3735
    //                   8 3843
    //                   9 4290;
           goto _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18
_L8:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj = String.valueOf(((GzipInflatingBuffer) (obj)).state);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        throw new AssertionError((new StringBuilder(String.valueOf(obj).length() + 15)).append("Invalid state: ").append(((String) (obj))).toString());
        obj;
        l = k1;
        j = l1;
        throw new RuntimeException(((Throwable) (obj)));
_L9:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputEnd;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        k2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputStart;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.gzippedData.readableBytes + (j2 - k2) >= 10) goto _L20; else goto _L19
_L19:
        flag = false;
          goto _L21
_L20:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).readUnsignedByte();
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if ((((GzipInflatingBuffer.GzipMetadataReader) (obj1)).readUnsignedByte() << 8 | j2) == 35615) goto _L23; else goto _L22
_L22:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        throw new ZipException("Not in GZIP format");
_L23:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer) (obj)).gzipMetadataReader.readUnsignedByte() == 8) goto _L25; else goto _L24
_L24:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        throw new ZipException("Unsupported compression method");
_L25:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.gzipHeaderFlag = ((GzipInflatingBuffer) (obj)).gzipMetadataReader.readUnsignedByte();
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputEnd - ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputStart;
        if (j2 <= 0) goto _L27; else goto _L26
_L26:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = Math.min(j2, 6);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.crc.update(((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInput, ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputStart, j2);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        gzipinflatingbuffer = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        gzipinflatingbuffer.inflaterInputStart = gzipinflatingbuffer.inflaterInputStart + j2;
        j2 = 6 - j2;
_L82:
        if (j2 <= 0) goto _L29; else goto _L28
_L28:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        abyte1 = new byte[512];
        k2 = 0;
_L30:
        if (k2 >= j2)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        l2 = Math.min(j2 - k2, 512);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.gzippedData.execute(new CompositeReadableBuffer._cls3(0, abyte1), l2);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.crc.update(abyte1, 0, l2);
        k2 += l2;
        if (true) goto _L30; else goto _L29
_L29:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1.bytesConsumed = ((GzipInflatingBuffer) (obj1)).bytesConsumed + 6;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.state = GzipInflatingBuffer.State.HEADER_EXTRA_LEN;
        flag = true;
          goto _L21
_L10:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if ((((GzipInflatingBuffer) (obj)).gzipHeaderFlag & 4) == 4) goto _L32; else goto _L31
_L31:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.state = GzipInflatingBuffer.State.HEADER_NAME;
        flag = true;
          goto _L21
_L32:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputEnd;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        k2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputStart;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.gzippedData.readableBytes + (j2 - k2) >= 2) goto _L34; else goto _L33
_L33:
        flag = false;
          goto _L21
_L34:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).readUnsignedByte();
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.headerExtraToRead = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).readUnsignedByte() << 8 | j2;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.state = GzipInflatingBuffer.State.HEADER_EXTRA;
        flag = true;
          goto _L21
_L11:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputEnd;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        k2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputStart;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.gzippedData.readableBytes + (j2 - k2) >= ((GzipInflatingBuffer) (obj)).headerExtraToRead) goto _L36; else goto _L35
_L35:
        flag = false;
          goto _L21
_L36:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        l2 = ((GzipInflatingBuffer) (obj)).headerExtraToRead;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputEnd - ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputStart;
        if (j2 <= 0) goto _L38; else goto _L37
_L37:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = Math.min(j2, l2);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.crc.update(((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInput, ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputStart, j2);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        abyte1 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        abyte1.inflaterInputStart = ((GzipInflatingBuffer) (abyte1)).inflaterInputStart + j2;
        j2 = l2 - j2;
_L81:
        if (j2 <= 0) goto _L40; else goto _L39
_L39:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        abyte1 = new byte[512];
        k2 = 0;
_L41:
        if (k2 >= j2)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        k3 = Math.min(j2 - k2, 512);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.gzippedData.execute(new CompositeReadableBuffer._cls3(0, abyte1), k3);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.crc.update(abyte1, 0, k3);
        k2 += k3;
        if (true) goto _L41; else goto _L40
_L40:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1.bytesConsumed = ((GzipInflatingBuffer) (obj1)).bytesConsumed + l2;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.state = GzipInflatingBuffer.State.HEADER_NAME;
        flag = true;
          goto _L21
_L12:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if ((((GzipInflatingBuffer) (obj)).gzipHeaderFlag & 8) != 8) goto _L43; else goto _L42
_L42:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
_L47:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if ((((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputEnd - ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputStart) + ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.gzippedData.readableBytes <= 0) goto _L45; else goto _L44
_L44:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer.GzipMetadataReader) (obj1)).readUnsignedByte() != 0) goto _L47; else goto _L46
_L46:
        j = 1;
          goto _L48
_L43:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.state = GzipInflatingBuffer.State.HEADER_COMMENT;
        flag = true;
          goto _L21
_L13:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if ((((GzipInflatingBuffer) (obj)).gzipHeaderFlag & 0x10) != 16) goto _L50; else goto _L49
_L49:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
_L54:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if ((((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputEnd - ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputStart) + ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.gzippedData.readableBytes <= 0) goto _L52; else goto _L51
_L51:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer.GzipMetadataReader) (obj1)).readUnsignedByte() != 0) goto _L54; else goto _L53
_L53:
        j = 1;
          goto _L55
_L50:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.state = GzipInflatingBuffer.State.HEADER_CRC;
        flag = true;
          goto _L21
_L14:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if ((((GzipInflatingBuffer) (obj)).gzipHeaderFlag & 2) != 2) goto _L57; else goto _L56
_L56:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputEnd;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        k2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.inflaterInputStart;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer.GzipMetadataReader) (obj1)).this$0.gzippedData.readableBytes + (j2 - k2) >= 2) goto _L59; else goto _L58
_L58:
        flag = false;
          goto _L21
_L59:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = (int)((GzipInflatingBuffer) (obj)).crc.getValue();
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        k2 = ((GzipInflatingBuffer.GzipMetadataReader) (obj1)).readUnsignedByte();
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if ((j2 & 0xffff) == (((GzipInflatingBuffer.GzipMetadataReader) (obj1)).readUnsignedByte() << 8 | k2)) goto _L57; else goto _L60
_L60:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        throw new ZipException("Corrupt GZIP header");
_L57:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.state = GzipInflatingBuffer.State.INITIALIZE_INFLATER;
        flag = true;
          goto _L21
_L15:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer) (obj)).inflater != null) goto _L62; else goto _L61
_L61:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.inflater = new Inflater(true);
_L65:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer) (obj)).crc.reset();
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer) (obj)).inflaterInputEnd - ((GzipInflatingBuffer) (obj)).inflaterInputStart;
        if (j2 <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer) (obj)).inflater.setInput(((GzipInflatingBuffer) (obj)).inflaterInput, ((GzipInflatingBuffer) (obj)).inflaterInputStart, j2);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.state = GzipInflatingBuffer.State.INFLATING;
          goto _L63
_L62:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer) (obj)).inflater.reset();
        if (true) goto _L65; else goto _L64
_L64:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.state = GzipInflatingBuffer.State.INFLATER_NEEDS_INPUT;
          goto _L63
_L16:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        i2 += ((GzipInflatingBuffer) (obj)).inflate(abyte0, j3 + i2, j2);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer) (obj)).state != GzipInflatingBuffer.State.TRAILER) goto _L67; else goto _L66
_L66:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        flag = ((GzipInflatingBuffer) (obj)).processTrailer();
          goto _L21
_L17:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer) (obj)).inflater != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L69; else goto _L68
_L68:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        throw new IllegalStateException(String.valueOf("inflater is null"));
_L69:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer) (obj)).inflaterInputStart == ((GzipInflatingBuffer) (obj)).inflaterInputEnd)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L71; else goto _L70
_L70:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        throw new IllegalStateException(String.valueOf("inflaterInput has unconsumed bytes"));
_L71:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = Math.min(((GzipInflatingBuffer) (obj)).gzippedData.readableBytes, 512);
        if (j2 != 0) goto _L73; else goto _L72
_L72:
        flag = false;
          goto _L21
_L73:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.inflaterInputStart = 0;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.inflaterInputEnd = j2;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj1 = ((GzipInflatingBuffer) (obj)).gzippedData;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        abyte1 = ((GzipInflatingBuffer) (obj)).inflaterInput;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((CompositeReadableBuffer) (obj1)).execute(new CompositeReadableBuffer._cls3(((GzipInflatingBuffer) (obj)).inflaterInputStart, abyte1), j2);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        ((GzipInflatingBuffer) (obj)).inflater.setInput(((GzipInflatingBuffer) (obj)).inflaterInput, ((GzipInflatingBuffer) (obj)).inflaterInputStart, j2);
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.state = GzipInflatingBuffer.State.INFLATING;
        flag = true;
          goto _L21
_L18:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        flag = ((GzipInflatingBuffer) (obj)).processTrailer();
          goto _L21
_L6:
        if (!flag) goto _L75; else goto _L74
_L74:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (((GzipInflatingBuffer) (obj)).state != GzipInflatingBuffer.State.HEADER) goto _L77; else goto _L76
_L76:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        gzipmetadatareader = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = gzipmetadatareader.this$0.inflaterInputEnd;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        k2 = gzipmetadatareader.this$0.inflaterInputStart;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        if (gzipmetadatareader.this$0.gzippedData.readableBytes + (j2 - k2) >= 10) goto _L77; else goto _L75
_L79:
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.isStalled = flag;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj = fullStreamDecompressor;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer) (obj)).bytesConsumed;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.bytesConsumed = 0;
        i += j2;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj = fullStreamDecompressor;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        j2 = ((GzipInflatingBuffer) (obj)).deflatedBytesConsumed;
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        obj.deflatedBytesConsumed = 0;
          goto _L78
_L77:
        flag = false;
          goto _L79
_L78:
        k += j2;
        if (i2 == 0)
        {
            if (i > 0)
            {
                listener.bytesRead(i);
                if (state == State.BODY)
                {
                    if (fullStreamDecompressor != null)
                    {
                        statsTraceCtx.inboundWireSize(k);
                        inboundBodyWireSize = inboundBodyWireSize + k;
                    } else
                    {
                        statsTraceCtx.inboundWireSize(i);
                        inboundBodyWireSize = inboundBodyWireSize + i;
                    }
                }
            }
            return false;
        }
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        nextFrame.addBuffer(ReadableBuffers.wrap(inflatedBuffer, inflatedIndex, i2));
        i1 = k;
        j1 = i;
        l = k;
        j = i;
        k1 = k;
        l1 = i;
        inflatedIndex = inflatedIndex + i2;
          goto _L80
_L4:
        l = k;
        j = i;
        i1 = unprocessed.readableBytes;
        if (i1 == 0)
        {
            if (i > 0)
            {
                listener.bytesRead(i);
                if (state == State.BODY)
                {
                    if (fullStreamDecompressor != null)
                    {
                        statsTraceCtx.inboundWireSize(k);
                        inboundBodyWireSize = inboundBodyWireSize + k;
                    } else
                    {
                        statsTraceCtx.inboundWireSize(i);
                        inboundBodyWireSize = inboundBodyWireSize + i;
                    }
                }
            }
            return false;
        }
        l = k;
        j = i;
        i1 = Math.min(i2, unprocessed.readableBytes);
        i += i1;
        l = k;
        j = i;
        nextFrame.addBuffer((CompositeReadableBuffer)unprocessed.readBytes(i1));
          goto _L80
_L2:
        if (i > 0)
        {
            listener.bytesRead(i);
            if (state == State.BODY)
            {
                if (fullStreamDecompressor != null)
                {
                    statsTraceCtx.inboundWireSize(k);
                    inboundBodyWireSize = inboundBodyWireSize + k;
                } else
                {
                    statsTraceCtx.inboundWireSize(i);
                    inboundBodyWireSize = inboundBodyWireSize + i;
                }
            }
        }
        return true;
_L38:
        j2 = l2;
          goto _L81
_L27:
        j2 = 6;
          goto _L82
_L48:
        if (j != 0) goto _L43; else goto _L83
_L83:
        flag = false;
          goto _L21
_L45:
        j = 0;
          goto _L48
_L55:
        if (j != 0) goto _L50; else goto _L84
_L84:
        flag = false;
          goto _L21
_L52:
        j = 0;
          goto _L55
_L63:
        flag = true;
          goto _L21
_L67:
        flag = true;
          goto _L21
_L75:
        flag = true;
          goto _L79
    }

    public final void close()
    {
        Object obj;
        boolean flag4;
        boolean flag;
        if (unprocessed == null && fullStreamDecompressor == null)
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
        boolean flag2;
        if (nextFrame != null && nextFrame.readableBytes > 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        flag4 = flag2;
        if (fullStreamDecompressor == null) goto _L2; else goto _L1
_L1:
        if (flag2) goto _L4; else goto _L3
_L3:
        obj = fullStreamDecompressor;
        boolean flag1;
        if (!((GzipInflatingBuffer) (obj)).closed)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_123;
        }
        throw new IllegalStateException(String.valueOf("GzipInflatingBuffer is closed"));
        obj;
        fullStreamDecompressor = null;
        unprocessed = null;
        nextFrame = null;
        throw obj;
        GzipInflatingBuffer.GzipMetadataReader gzipmetadatareader;
        int i;
        int j;
        gzipmetadatareader = ((GzipInflatingBuffer) (obj)).gzipMetadataReader;
        i = gzipmetadatareader.this$0.inflaterInputEnd;
        j = gzipmetadatareader.this$0.inflaterInputStart;
        break MISSING_BLOCK_LABEL_145;
_L6:
        fullStreamDecompressor.close();
        boolean flag3;
        flag4 = flag3;
_L2:
        if (unprocessed != null)
        {
            unprocessed.close();
        }
        if (nextFrame != null)
        {
            nextFrame.close();
        }
        fullStreamDecompressor = null;
        unprocessed = null;
        nextFrame = null;
        listener.deframerClosed(flag4);
        return;
        if (gzipmetadatareader.this$0.gzippedData.readableBytes + (i - j) == 0 && ((GzipInflatingBuffer) (obj)).state == GzipInflatingBuffer.State.HEADER)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        while (!i) 
        {
            flag3 = false;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        flag3 = true;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void closeWhenComplete()
    {
        boolean flag3 = false;
        boolean flag2 = false;
        boolean flag;
        if (unprocessed == null && fullStreamDecompressor == null)
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
        if (fullStreamDecompressor != null)
        {
            GzipInflatingBuffer gzipinflatingbuffer = fullStreamDecompressor;
            boolean flag1 = flag2;
            if (!gzipinflatingbuffer.closed)
            {
                flag1 = true;
            }
            if (!flag1)
            {
                throw new IllegalStateException(String.valueOf("GzipInflatingBuffer is closed"));
            }
            flag3 = gzipinflatingbuffer.isStalled;
        } else
        if (unprocessed.readableBytes == 0)
        {
            flag3 = true;
        }
        if (flag3)
        {
            close();
            return;
        } else
        {
            closeWhenComplete = true;
            return;
        }
    }

    public final void deframe(ReadableBuffer readablebuffer)
    {
        boolean flag1;
        boolean flag2;
        flag1 = true;
        flag2 = false;
        if (readablebuffer == null)
        {
            throw new NullPointerException(String.valueOf("data"));
        }
        Object obj;
        boolean flag;
        if (unprocessed == null && fullStreamDecompressor == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        if (!closeWhenComplete)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
          goto _L2
_L11:
        if (flag)
        {
            break MISSING_BLOCK_LABEL_176;
        }
        if (fullStreamDecompressor == null) goto _L4; else goto _L3
_L3:
        obj = fullStreamDecompressor;
        if (!((GzipInflatingBuffer) (obj)).closed)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L6; else goto _L5
_L5:
        throw new IllegalStateException(String.valueOf("GzipInflatingBuffer is closed"));
        obj;
        flag = flag1;
_L8:
        if (flag)
        {
            readablebuffer.close();
        }
        throw obj;
_L6:
        ((GzipInflatingBuffer) (obj)).gzippedData.addBuffer(readablebuffer);
        obj.isStalled = false;
_L7:
        deliver();
        flag = flag2;
_L9:
        if (flag)
        {
            readablebuffer.close();
        }
        return;
_L4:
        unprocessed.addBuffer(readablebuffer);
          goto _L7
        obj;
        flag = false;
          goto _L8
        flag = true;
          goto _L9
_L2:
        flag = true;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public final void request(int i)
    {
        boolean flag1 = true;
        boolean flag;
        if (i > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("numMessages must be > 0"));
        }
        if (unprocessed == null && fullStreamDecompressor == null)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return;
        } else
        {
            pendingDeliveries = pendingDeliveries + (long)i;
            deliver();
            return;
        }
    }

    public final void setDecompressor(Decompressor decompressor1)
    {
        boolean flag;
        if (fullStreamDecompressor == null)
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
        if (decompressor1 == null)
        {
            throw new NullPointerException(String.valueOf("Can't pass an empty decompressor"));
        } else
        {
            decompressor = (Decompressor)decompressor1;
            return;
        }
    }

    public final void setFullStreamDecompressor(GzipInflatingBuffer gzipinflatingbuffer)
    {
        boolean flag1 = true;
        boolean flag;
        if (decompressor == io.grpc.Codec.Identity.NONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("per-message decompressor already set"));
        }
        if (fullStreamDecompressor == null)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("full stream decompressor already set"));
        }
        if (gzipinflatingbuffer == null)
        {
            throw new NullPointerException(String.valueOf("Can't pass a null full stream decompressor"));
        } else
        {
            fullStreamDecompressor = (GzipInflatingBuffer)gzipinflatingbuffer;
            unprocessed = null;
            return;
        }
    }

    public final void setMaxInboundMessageSize(int i)
    {
        maxInboundMessageSize = i;
    }

    private class State extends Enum
    {

        private static final State $VALUES[];
        public static final State BODY;
        public static final State HEADER;

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        static 
        {
            HEADER = new State("HEADER", 0);
            BODY = new State("BODY", 1);
            $VALUES = (new State[] {
                HEADER, BODY
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    private class Listener
    {

        public abstract void bytesRead(int i);

        public abstract void deframeFailed(Throwable throwable);

        public abstract void deframerClosed(boolean flag);

        public abstract void messagesAvailable(StreamListener.MessageProducer messageproducer);
    }


    private class SingleMessageProducer
        implements StreamListener.MessageProducer
    {

        private InputStream message;

        public final InputStream next()
        {
            InputStream inputstream = message;
            message = null;
            return inputstream;
        }

        SingleMessageProducer(InputStream inputstream)
        {
            message = inputstream;
        }
    }


    private class SizeEnforcingInputStream extends FilterInputStream
    {

        private long count;
        private long mark;
        private long maxCount;
        private final int maxMessageSize;
        private final StatsTraceContext statsTraceCtx;

        private final void reportCount()
        {
            if (count > maxCount)
            {
                statsTraceCtx.inboundUncompressedSize(count - maxCount);
                maxCount = count;
            }
        }

        private final void verifySize()
        {
            if (count > (long)maxMessageSize)
            {
                throw new StatusRuntimeException(Status.RESOURCE_EXHAUSTED.withDescription(String.format("Compressed gRPC message exceeds maximum size %d: %d bytes read", new Object[] {
                    Integer.valueOf(maxMessageSize), Long.valueOf(count)
                })));
            } else
            {
                return;
            }
        }

        public final void mark(int i)
        {
            this;
            JVM INSTR monitorenter ;
            in.mark(i);
            mark = count;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public final int read()
            throws IOException
        {
            int i = in.read();
            if (i != -1)
            {
                count = count + 1L;
            }
            verifySize();
            reportCount();
            return i;
        }

        public final int read(byte abyte0[], int i, int j)
            throws IOException
        {
            i = in.read(abyte0, i, j);
            if (i != -1)
            {
                count = count + (long)i;
            }
            verifySize();
            reportCount();
            return i;
        }

        public final void reset()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if (!in.markSupported())
            {
                throw new IOException("Mark not supported");
            }
            break MISSING_BLOCK_LABEL_27;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            if (mark == -1L)
            {
                throw new IOException("Mark not set");
            }
            in.reset();
            count = mark;
            this;
            JVM INSTR monitorexit ;
        }

        public final long skip(long l)
            throws IOException
        {
            l = in.skip(l);
            count = count + l;
            verifySize();
            reportCount();
            return l;
        }

        SizeEnforcingInputStream(InputStream inputstream, int i, StatsTraceContext statstracecontext)
        {
            super(inputstream);
            mark = -1L;
            maxMessageSize = i;
            statsTraceCtx = statstracecontext;
        }
    }

}
