// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.Closeable;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;

// Referenced classes of package io.grpc.internal:
//            CompositeReadableBuffer, AbstractReadableBuffer

public final class GzipInflatingBuffer
    implements Closeable
{

    public int bytesConsumed;
    public boolean closed;
    public final CRC32 crc = new CRC32();
    public int deflatedBytesConsumed;
    private long expectedGzipTrailerIsize;
    public int gzipHeaderFlag;
    public final GzipMetadataReader gzipMetadataReader = new GzipMetadataReader();
    public final CompositeReadableBuffer gzippedData = new CompositeReadableBuffer();
    public int headerExtraToRead;
    public Inflater inflater;
    public final byte inflaterInput[] = new byte[512];
    public int inflaterInputEnd;
    public int inflaterInputStart;
    public boolean isStalled;
    public State state;

    public GzipInflatingBuffer()
    {
        state = State.HEADER;
        closed = false;
        bytesConsumed = 0;
        deflatedBytesConsumed = 0;
        isStalled = true;
    }

    public final void close()
    {
        if (!closed)
        {
            closed = true;
            gzippedData.close();
            if (inflater != null)
            {
                inflater.end();
                inflater = null;
            }
        }
    }

    final int inflate(byte abyte0[], int i, int j)
        throws DataFormatException, ZipException
    {
        boolean flag;
        if (inflater != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("inflater is null"));
        }
        int k = inflater.getTotalIn();
        j = inflater.inflate(abyte0, i, j);
        k = inflater.getTotalIn() - k;
        bytesConsumed = bytesConsumed + k;
        deflatedBytesConsumed = deflatedBytesConsumed + k;
        inflaterInputStart = k + inflaterInputStart;
        crc.update(abyte0, i, j);
        if (!inflater.finished())
        {
            break MISSING_BLOCK_LABEL_143;
        }
        expectedGzipTrailerIsize = inflater.getBytesWritten() & 0xffffffffL;
        state = State.TRAILER;
        return j;
label0:
        {
            try
            {
                if (!inflater.needsInput())
                {
                    break label0;
                }
                state = State.INFLATER_NEEDS_INPUT;
            }
            // Misplaced declaration of an exception variable
            catch (byte abyte0[])
            {
                abyte0 = String.valueOf(abyte0.getMessage());
                if (abyte0.length() != 0)
                {
                    abyte0 = "Inflater data format exception: ".concat(abyte0);
                } else
                {
                    abyte0 = new String("Inflater data format exception: ");
                }
                throw new DataFormatException(abyte0);
            }
            return j;
        }
        return j;
    }

    final boolean processTrailer()
        throws ZipException
    {
label0:
        {
            if (inflater != null)
            {
                GzipMetadataReader gzipmetadatareader = gzipMetadataReader;
                int i = gzipmetadatareader._fld0.inflaterInputEnd;
                int l = gzipmetadatareader._fld0.inflaterInputStart;
                if (gzipmetadatareader._fld0.gzippedData.readableBytes + (i - l) <= 18)
                {
                    inflater.end();
                    inflater = null;
                }
            }
            GzipMetadataReader gzipmetadatareader1 = gzipMetadataReader;
            int j = gzipmetadatareader1._fld0.inflaterInputEnd;
            int i1 = gzipmetadatareader1._fld0.inflaterInputStart;
            if (gzipmetadatareader1._fld0.gzippedData.readableBytes + (j - i1) < 8)
            {
                return false;
            }
            long l1 = crc.getValue();
            gzipmetadatareader1 = gzipMetadataReader;
            long l3 = gzipmetadatareader1.readUnsignedByte() | gzipmetadatareader1.readUnsignedByte() << 8;
            j = gzipmetadatareader1.readUnsignedByte();
            if (l1 == ((long)(gzipmetadatareader1.readUnsignedByte() << 8 | j) << 16 | l3))
            {
                long l2 = expectedGzipTrailerIsize;
                GzipMetadataReader gzipmetadatareader2 = gzipMetadataReader;
                long l4 = gzipmetadatareader2.readUnsignedByte() | gzipmetadatareader2.readUnsignedByte() << 8;
                int k = gzipmetadatareader2.readUnsignedByte();
                if (l2 == ((long)(gzipmetadatareader2.readUnsignedByte() << 8 | k) << 16 | l4))
                {
                    break label0;
                }
            }
            throw new ZipException("Corrupt GZIP trailer");
        }
        crc.reset();
        state = State.HEADER;
        return true;
    }

    private class GzipMetadataReader
    {

        public final GzipInflatingBuffer this$0;

        final int readUnsignedByte()
        {
            GzipInflatingBuffer gzipinflatingbuffer1;
            int i;
            if (inflaterInputEnd - inflaterInputStart > 0)
            {
                i = inflaterInput[inflaterInputStart] & 0xff;
                GzipInflatingBuffer gzipinflatingbuffer = GzipInflatingBuffer.this;
                gzipinflatingbuffer.inflaterInputStart = gzipinflatingbuffer.inflaterInputStart + 1;
            } else
            {
                CompositeReadableBuffer compositereadablebuffer = gzippedData;
                CompositeReadableBuffer._cls1 _lcls1 = new CompositeReadableBuffer._cls1();
                compositereadablebuffer.execute(_lcls1, 1);
                i = ((CompositeReadableBuffer.ReadOperation) (_lcls1)).value;
            }
            crc.update(i);
            gzipinflatingbuffer1 = GzipInflatingBuffer.this;
            gzipinflatingbuffer1.bytesConsumed = gzipinflatingbuffer1.bytesConsumed + 1;
            return i;
        }

        GzipMetadataReader()
        {
            this$0 = GzipInflatingBuffer.this;
            super();
        }
    }


    private class State extends Enum
    {

        private static final State $VALUES[];
        public static final State HEADER;
        public static final State HEADER_COMMENT;
        public static final State HEADER_CRC;
        public static final State HEADER_EXTRA;
        public static final State HEADER_EXTRA_LEN;
        public static final State HEADER_NAME;
        public static final State INFLATER_NEEDS_INPUT;
        public static final State INFLATING;
        public static final State INITIALIZE_INFLATER;
        public static final State TRAILER;

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        static 
        {
            HEADER = new State("HEADER", 0);
            HEADER_EXTRA_LEN = new State("HEADER_EXTRA_LEN", 1);
            HEADER_EXTRA = new State("HEADER_EXTRA", 2);
            HEADER_NAME = new State("HEADER_NAME", 3);
            HEADER_COMMENT = new State("HEADER_COMMENT", 4);
            HEADER_CRC = new State("HEADER_CRC", 5);
            INITIALIZE_INFLATER = new State("INITIALIZE_INFLATER", 6);
            INFLATING = new State("INFLATING", 7);
            INFLATER_NEEDS_INPUT = new State("INFLATER_NEEDS_INPUT", 8);
            TRAILER = new State("TRAILER", 9);
            $VALUES = (new State[] {
                HEADER, HEADER_EXTRA_LEN, HEADER_EXTRA, HEADER_NAME, HEADER_COMMENT, HEADER_CRC, INITIALIZE_INFLATER, INFLATING, INFLATER_NEEDS_INPUT, TRAILER
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }

}
