// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            ByteOutput, UnsafeUtil, ByteString, AbstractMessageLite, 
//            Schema, MessageLite, LazyFieldLite, Utf8, 
//            Internal, CodedOutputStreamWriter

public abstract class CodedOutputStream extends ByteOutput
{
    static abstract class AbstractBufferedEncoder extends CodedOutputStream
    {

        public final byte buffer[];
        public final int limit;
        public int position;
        public int totalBytesWritten;

        final void bufferFixed32NoTag(int i)
        {
            byte abyte0[] = buffer;
            int j = position;
            position = j + 1;
            abyte0[j] = (byte)i;
            abyte0 = buffer;
            j = position;
            position = j + 1;
            abyte0[j] = (byte)(i >> 8);
            abyte0 = buffer;
            j = position;
            position = j + 1;
            abyte0[j] = (byte)(i >> 16);
            abyte0 = buffer;
            j = position;
            position = j + 1;
            abyte0[j] = (byte)(i >>> 24);
            totalBytesWritten = totalBytesWritten + 4;
        }

        final void bufferFixed64NoTag(long l)
        {
            byte abyte0[] = buffer;
            int i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l & 255L);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 8 & 255L);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 16 & 255L);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 24 & 255L);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 32);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 40);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 48);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 56);
            totalBytesWritten = totalBytesWritten + 8;
        }

        final void bufferUInt32NoTag(int i)
        {
            int j = i;
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS)
            {
                long l = position;
                do
                {
                    if ((i & 0xffffff80) == 0)
                    {
                        byte abyte0[] = buffer;
                        j = position;
                        position = j + 1;
                        long l1 = j;
                        byte byte0 = (byte)i;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte0);
                        totalBytesWritten = (int)((long)position - l) + totalBytesWritten;
                        return;
                    }
                    byte abyte1[] = buffer;
                    j = position;
                    position = j + 1;
                    long l2 = j;
                    byte byte1 = (byte)(i & 0x7f | 0x80);
                    UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte1, l2 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte1);
                    i >>>= 7;
                } while (true);
            }
            for (; (j & 0xffffff80) != 0; j >>>= 7)
            {
                byte abyte2[] = buffer;
                i = position;
                position = i + 1;
                abyte2[i] = (byte)(j & 0x7f | 0x80);
                totalBytesWritten = totalBytesWritten + 1;
            }

            byte abyte3[] = buffer;
            i = position;
            position = i + 1;
            abyte3[i] = (byte)j;
            totalBytesWritten = totalBytesWritten + 1;
        }

        final void bufferUInt64NoTag(long l)
        {
            long l1 = l;
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS)
            {
                l1 = position;
                do
                {
                    if ((-128L & l) == 0L)
                    {
                        byte abyte0[] = buffer;
                        int i = position;
                        position = i + 1;
                        long l2 = i;
                        byte byte0 = (byte)(int)l;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l2 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte0);
                        totalBytesWritten = (int)((long)position - l1) + totalBytesWritten;
                        return;
                    }
                    byte abyte1[] = buffer;
                    int j = position;
                    position = j + 1;
                    long l3 = j;
                    byte byte1 = (byte)((int)l & 0x7f | 0x80);
                    UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte1, l3 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte1);
                    l >>>= 7;
                } while (true);
            }
            for (; (-128L & l1) != 0L; l1 >>>= 7)
            {
                byte abyte2[] = buffer;
                int k = position;
                position = k + 1;
                abyte2[k] = (byte)((int)l1 & 0x7f | 0x80);
                totalBytesWritten = totalBytesWritten + 1;
            }

            byte abyte3[] = buffer;
            int i1 = position;
            position = i1 + 1;
            abyte3[i1] = (byte)(int)l1;
            totalBytesWritten = totalBytesWritten + 1;
        }

        public final int spaceLeft()
        {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        AbstractBufferedEncoder(int i)
        {
            if (i < 0)
            {
                throw new IllegalArgumentException("bufferSize must be >= 0");
            } else
            {
                buffer = new byte[Math.max(i, 20)];
                limit = buffer.length;
                return;
            }
        }
    }

    static class ArrayEncoder extends CodedOutputStream
    {

        private final byte buffer[];
        private final int limit;
        public final int offset;
        public int position;

        public void flush()
        {
        }

        public final int spaceLeft()
        {
            return limit - position;
        }

        public final void write(byte byte0)
            throws IOException
        {
            byte abyte0[];
            int i;
            try
            {
                abyte0 = buffer;
                i = position;
                position = i + 1;
            }
            catch (IndexOutOfBoundsException indexoutofboundsexception)
            {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                    Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(1)
                }), indexoutofboundsexception);
            }
            abyte0[i] = byte0;
        }

        public final void write(byte abyte0[], int i, int j)
            throws IOException
        {
            try
            {
                System.arraycopy(abyte0, i, buffer, position, j);
                position = position + j;
                return;
            }
            // Misplaced declaration of an exception variable
            catch (byte abyte0[])
            {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                    Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(j)
                }), abyte0);
            }
        }

        public final void writeBool(int i, boolean flag)
            throws IOException
        {
            boolean flag1 = false;
            writeTag(i, 0);
            i = ((flag1) ? 1 : 0);
            if (flag)
            {
                i = 1;
            }
            write((byte)i);
        }

        public final void writeByteArrayNoTag(byte abyte0[], int i, int j)
            throws IOException
        {
            writeUInt32NoTag(j);
            write(abyte0, 0, j);
        }

        public final void writeBytes(int i, ByteString bytestring)
            throws IOException
        {
            writeTag(i, 2);
            writeBytesNoTag(bytestring);
        }

        public final void writeBytesNoTag(ByteString bytestring)
            throws IOException
        {
            writeUInt32NoTag(bytestring.size());
            bytestring.writeTo(this);
        }

        public final void writeFixed32(int i, int j)
            throws IOException
        {
            writeTag(i, 5);
            writeFixed32NoTag(j);
        }

        public final void writeFixed32NoTag(int i)
            throws IOException
        {
            byte abyte0[];
            int j;
            try
            {
                abyte0 = buffer;
                j = position;
                position = j + 1;
            }
            catch (IndexOutOfBoundsException indexoutofboundsexception)
            {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                    Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(1)
                }), indexoutofboundsexception);
            }
            abyte0[j] = (byte)i;
            abyte0 = buffer;
            j = position;
            position = j + 1;
            abyte0[j] = (byte)(i >> 8);
            abyte0 = buffer;
            j = position;
            position = j + 1;
            abyte0[j] = (byte)(i >> 16);
            abyte0 = buffer;
            j = position;
            position = j + 1;
            abyte0[j] = (byte)(i >>> 24);
            return;
        }

        public final void writeFixed64(int i, long l)
            throws IOException
        {
            writeTag(i, 1);
            writeFixed64NoTag(l);
        }

        public final void writeFixed64NoTag(long l)
            throws IOException
        {
            byte abyte0[];
            int i;
            try
            {
                abyte0 = buffer;
                i = position;
                position = i + 1;
            }
            catch (IndexOutOfBoundsException indexoutofboundsexception)
            {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                    Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(1)
                }), indexoutofboundsexception);
            }
            abyte0[i] = (byte)(int)l;
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 8);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 16);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 24);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 32);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 40);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 48);
            abyte0 = buffer;
            i = position;
            position = i + 1;
            abyte0[i] = (byte)(int)(l >> 56);
            return;
        }

        public final void writeInt32(int i, int j)
            throws IOException
        {
            writeTag(i, 0);
            writeInt32NoTag(j);
        }

        public final void writeInt32NoTag(int i)
            throws IOException
        {
            if (i >= 0)
            {
                writeUInt32NoTag(i);
                return;
            } else
            {
                writeUInt64NoTag(i);
                return;
            }
        }

        public final void writeLazy(byte abyte0[], int i, int j)
            throws IOException
        {
            write(abyte0, i, j);
        }

        public final void writeMessage(int i, MessageLite messagelite)
            throws IOException
        {
            writeTag(i, 2);
            writeMessageNoTag(messagelite);
        }

        final void writeMessage(int i, MessageLite messagelite, Schema schema)
            throws IOException
        {
            writeTag(i, 2);
            AbstractMessageLite abstractmessagelite = (AbstractMessageLite)messagelite;
            int j = abstractmessagelite.getMemoizedSerializedSize();
            i = j;
            if (j == -1)
            {
                i = schema.getSerializedSize(abstractmessagelite);
                abstractmessagelite.setMemoizedSerializedSize(i);
            }
            writeUInt32NoTag(i);
            schema.writeTo(messagelite, wrapper);
        }

        public final void writeMessageNoTag(MessageLite messagelite)
            throws IOException
        {
            writeUInt32NoTag(messagelite.getSerializedSize());
            messagelite.writeTo(this);
        }

        final void writeMessageNoTag(MessageLite messagelite, Schema schema)
            throws IOException
        {
            AbstractMessageLite abstractmessagelite = (AbstractMessageLite)messagelite;
            int j = abstractmessagelite.getMemoizedSerializedSize();
            int i = j;
            if (j == -1)
            {
                i = schema.getSerializedSize(abstractmessagelite);
                abstractmessagelite.setMemoizedSerializedSize(i);
            }
            writeUInt32NoTag(i);
            schema.writeTo(messagelite, wrapper);
        }

        public final void writeMessageSetExtension(int i, MessageLite messagelite)
            throws IOException
        {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeMessage(3, messagelite);
            writeTag(1, 4);
        }

        public final void writeRawMessageSetExtension(int i, ByteString bytestring)
            throws IOException
        {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeBytes(3, bytestring);
            writeTag(1, 4);
        }

        public final void writeString(int i, String s)
            throws IOException
        {
            writeTag(i, 2);
            writeStringNoTag(s);
        }

        public final void writeStringNoTag(String s)
            throws IOException
        {
            int i = position;
            byte abyte0[];
            int j;
            int k;
            int l;
            try
            {
                k = computeUInt32SizeNoTag(s.length() * 3);
                j = computeUInt32SizeNoTag(s.length());
            }
            catch (Utf8.UnpairedSurrogateException unpairedsurrogateexception)
            {
                position = i;
                inefficientWriteStringNoTag(s, unpairedsurrogateexception);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                throw new OutOfSpaceException(s);
            }
            if (j != k)
            {
                break MISSING_BLOCK_LABEL_94;
            }
            position = i + j;
            abyte0 = buffer;
            k = position;
            l = spaceLeft();
            k = Utf8.processor.encodeUtf8(s, abyte0, k, l);
            position = i;
            writeUInt32NoTag(k - i - j);
            position = k;
            return;
            writeUInt32NoTag(Utf8.encodedLength(s));
            abyte0 = buffer;
            j = position;
            k = spaceLeft();
            position = Utf8.processor.encodeUtf8(s, abyte0, j, k);
            return;
        }

        public final void writeTag(int i, int j)
            throws IOException
        {
            writeUInt32NoTag(i << 3 | j);
        }

        public final void writeUInt32(int i, int j)
            throws IOException
        {
            writeTag(i, 0);
            writeUInt32NoTag(j);
        }

        public final void writeUInt32NoTag(int i)
            throws IOException
        {
            int j = i;
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS)
            {
                j = i;
                if (spaceLeft() >= 10)
                {
                    do
                    {
                        if ((i & 0xffffff80) == 0)
                        {
                            byte abyte0[] = buffer;
                            j = position;
                            position = j + 1;
                            long l = j;
                            byte byte0 = (byte)i;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte0);
                            return;
                        }
                        byte abyte1[] = buffer;
                        j = position;
                        position = j + 1;
                        long l1 = j;
                        byte byte1 = (byte)(i & 0x7f | 0x80);
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte1, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte1);
                        i >>>= 7;
                    } while (true);
                }
            }
            while ((j & 0xffffff80) != 0) 
            {
                byte abyte2[];
                try
                {
                    abyte2 = buffer;
                    i = position;
                    position = i + 1;
                }
                catch (IndexOutOfBoundsException indexoutofboundsexception)
                {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                        Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(1)
                    }), indexoutofboundsexception);
                }
                abyte2[i] = (byte)(j & 0x7f | 0x80);
                j >>>= 7;
            }
            abyte2 = buffer;
            i = position;
            position = i + 1;
            abyte2[i] = (byte)j;
            return;
        }

        public final void writeUInt64(int i, long l)
            throws IOException
        {
            writeTag(i, 0);
            writeUInt64NoTag(l);
        }

        public final void writeUInt64NoTag(long l)
            throws IOException
        {
            long l1 = l;
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS)
            {
                l1 = l;
                if (spaceLeft() >= 10)
                {
                    do
                    {
                        if ((l & -128L) == 0L)
                        {
                            byte abyte0[] = buffer;
                            int i = position;
                            position = i + 1;
                            l1 = i;
                            byte byte0 = (byte)(int)l;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte0);
                            return;
                        }
                        byte abyte1[] = buffer;
                        int j = position;
                        position = j + 1;
                        l1 = j;
                        byte byte1 = (byte)((int)l & 0x7f | 0x80);
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte1, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte1);
                        l >>>= 7;
                    } while (true);
                }
            }
            while ((l1 & -128L) != 0L) 
            {
                byte abyte2[];
                int k;
                try
                {
                    abyte2 = buffer;
                    k = position;
                    position = k + 1;
                }
                catch (IndexOutOfBoundsException indexoutofboundsexception)
                {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                        Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(1)
                    }), indexoutofboundsexception);
                }
                abyte2[k] = (byte)((int)l1 & 0x7f | 0x80);
                l1 >>>= 7;
            }
            abyte2 = buffer;
            k = position;
            position = k + 1;
            abyte2[k] = (byte)(int)l1;
            return;
        }

        ArrayEncoder(byte abyte0[], int i, int j)
        {
            if (abyte0 == null)
            {
                throw new NullPointerException("buffer");
            }
            if ((i | j | abyte0.length - (i + j)) < 0)
            {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[] {
                    Integer.valueOf(abyte0.length), Integer.valueOf(i), Integer.valueOf(j)
                }));
            } else
            {
                buffer = abyte0;
                offset = i;
                position = i;
                limit = i + j;
                return;
            }
        }
    }

    static final class HeapNioEncoder extends ArrayEncoder
    {

        private final ByteBuffer byteBuffer;
        private int initialPosition;

        public final void flush()
        {
            byteBuffer.position(initialPosition + (super.position - super.offset));
        }

        HeapNioEncoder(ByteBuffer bytebuffer)
        {
            super(bytebuffer.array(), bytebuffer.arrayOffset() + bytebuffer.position(), bytebuffer.remaining());
            byteBuffer = bytebuffer;
            initialPosition = bytebuffer.position();
        }
    }

    public static final class OutOfSpaceException extends IOException
    {

        public static final long serialVersionUID = 0x9f95917c52ce6e25L;

        OutOfSpaceException()
        {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        OutOfSpaceException(String s)
        {
            String s1 = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = s1.concat(s);
            } else
            {
                s = new String(s1);
            }
            super(s);
        }

        OutOfSpaceException(String s, Throwable throwable)
        {
            String s1 = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = s1.concat(s);
            } else
            {
                s = new String(s1);
            }
            super(s, throwable);
        }

        OutOfSpaceException(Throwable throwable)
        {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", throwable);
        }
    }

    static final class OutputStreamEncoder extends AbstractBufferedEncoder
    {

        private final OutputStream out;

        public final void flush()
            throws IOException
        {
            if (position > 0)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
        }

        public final void write(byte byte0)
            throws IOException
        {
            if (position == limit)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            byte abyte0[] = super.buffer;
            int i = super.position;
            super.position = i + 1;
            abyte0[i] = byte0;
            super.totalBytesWritten = super.totalBytesWritten + 1;
        }

        public final void write(byte abyte0[], int i, int j)
            throws IOException
        {
            if (limit - position >= j)
            {
                System.arraycopy(abyte0, i, buffer, position, j);
                position = position + j;
            } else
            {
                int k = limit - position;
                System.arraycopy(abyte0, i, buffer, position, k);
                i += k;
                j -= k;
                position = limit;
                totalBytesWritten = k + totalBytesWritten;
                out.write(buffer, 0, position);
                position = 0;
                if (j <= limit)
                {
                    System.arraycopy(abyte0, i, buffer, 0, j);
                    position = j;
                } else
                {
                    out.write(abyte0, i, j);
                }
            }
            totalBytesWritten = totalBytesWritten + j;
        }

        public final void writeBool(int i, boolean flag)
            throws IOException
        {
            boolean flag1 = false;
            if (limit - position < 11)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            bufferUInt32NoTag(i << 3 | 0);
            i = ((flag1) ? 1 : 0);
            if (flag)
            {
                i = 1;
            }
            byte byte0 = (byte)i;
            byte abyte0[] = super.buffer;
            i = super.position;
            super.position = i + 1;
            abyte0[i] = byte0;
            super.totalBytesWritten = super.totalBytesWritten + 1;
        }

        public final void writeByteArrayNoTag(byte abyte0[], int i, int j)
            throws IOException
        {
            writeUInt32NoTag(j);
            write(abyte0, 0, j);
        }

        public final void writeBytes(int i, ByteString bytestring)
            throws IOException
        {
            writeTag(i, 2);
            writeBytesNoTag(bytestring);
        }

        public final void writeBytesNoTag(ByteString bytestring)
            throws IOException
        {
            writeUInt32NoTag(bytestring.size());
            bytestring.writeTo(this);
        }

        public final void writeFixed32(int i, int j)
            throws IOException
        {
            if (limit - position < 14)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            bufferUInt32NoTag(5 | i << 3);
            bufferFixed32NoTag(j);
        }

        public final void writeFixed32NoTag(int i)
            throws IOException
        {
            if (limit - position < 4)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            bufferFixed32NoTag(i);
        }

        public final void writeFixed64(int i, long l)
            throws IOException
        {
            if (limit - position < 18)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            bufferUInt32NoTag(1 | i << 3);
            bufferFixed64NoTag(l);
        }

        public final void writeFixed64NoTag(long l)
            throws IOException
        {
            if (limit - position < 8)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            bufferFixed64NoTag(l);
        }

        public final void writeInt32(int i, int j)
            throws IOException
        {
            if (limit - position < 20)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            bufferUInt32NoTag(i << 3 | 0);
            if (j >= 0)
            {
                bufferUInt32NoTag(j);
                return;
            } else
            {
                bufferUInt64NoTag(j);
                return;
            }
        }

        public final void writeInt32NoTag(int i)
            throws IOException
        {
            if (i >= 0)
            {
                writeUInt32NoTag(i);
                return;
            } else
            {
                writeUInt64NoTag(i);
                return;
            }
        }

        public final void writeLazy(byte abyte0[], int i, int j)
            throws IOException
        {
            write(abyte0, i, j);
        }

        public final void writeMessage(int i, MessageLite messagelite)
            throws IOException
        {
            writeTag(i, 2);
            writeMessageNoTag(messagelite);
        }

        final void writeMessage(int i, MessageLite messagelite, Schema schema)
            throws IOException
        {
            writeTag(i, 2);
            writeMessageNoTag(messagelite, schema);
        }

        public final void writeMessageNoTag(MessageLite messagelite)
            throws IOException
        {
            writeUInt32NoTag(messagelite.getSerializedSize());
            messagelite.writeTo(this);
        }

        final void writeMessageNoTag(MessageLite messagelite, Schema schema)
            throws IOException
        {
            AbstractMessageLite abstractmessagelite = (AbstractMessageLite)messagelite;
            int j = abstractmessagelite.getMemoizedSerializedSize();
            int i = j;
            if (j == -1)
            {
                i = schema.getSerializedSize(abstractmessagelite);
                abstractmessagelite.setMemoizedSerializedSize(i);
            }
            writeUInt32NoTag(i);
            schema.writeTo(messagelite, wrapper);
        }

        public final void writeMessageSetExtension(int i, MessageLite messagelite)
            throws IOException
        {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeMessage(3, messagelite);
            writeTag(1, 4);
        }

        public final void writeRawMessageSetExtension(int i, ByteString bytestring)
            throws IOException
        {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeBytes(3, bytestring);
            writeTag(1, 4);
        }

        public final void writeString(int i, String s)
            throws IOException
        {
            writeTag(i, 2);
            writeStringNoTag(s);
        }

        public final void writeStringNoTag(String s)
            throws IOException
        {
            int i;
            int k;
            i = s.length() * 3;
            k = computeUInt32SizeNoTag(i);
            if (k + i > limit)
            {
                byte abyte0[] = new byte[i];
                i = Utf8.processor.encodeUtf8(s, abyte0, 0, i);
                writeUInt32NoTag(i);
                writeLazy(abyte0, 0, i);
                return;
            }
            int j;
            if (i + k > limit - position)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            i = computeUInt32SizeNoTag(s.length());
            j = position;
            if (i != k)
            {
                break MISSING_BLOCK_LABEL_226;
            }
            position = j + i;
            byte abyte1[] = buffer;
            k = position;
            int i1 = limit;
            int j1 = position;
            k = Utf8.processor.encodeUtf8(s, abyte1, k, i1 - j1);
            position = j;
            i = k - j - i;
            bufferUInt32NoTag(i);
            position = k;
_L1:
            totalBytesWritten = i + totalBytesWritten;
            return;
            Object obj;
            obj;
            try
            {
                totalBytesWritten = totalBytesWritten - (position - j);
                position = j;
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                inefficientWriteStringNoTag(s, ((Utf8.UnpairedSurrogateException) (obj)));
            }
            return;
            i = Utf8.encodedLength(s);
            bufferUInt32NoTag(i);
            byte abyte2[] = buffer;
            int l = position;
            position = Utf8.processor.encodeUtf8(s, abyte2, l, i);
              goto _L1
            abyte2;
            throw new OutOfSpaceException(abyte2);
        }

        public final void writeTag(int i, int j)
            throws IOException
        {
            writeUInt32NoTag(i << 3 | j);
        }

        public final void writeUInt32(int i, int j)
            throws IOException
        {
            if (limit - position < 20)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            bufferUInt32NoTag(i << 3 | 0);
            bufferUInt32NoTag(j);
        }

        public final void writeUInt32NoTag(int i)
            throws IOException
        {
            if (limit - position < 10)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            bufferUInt32NoTag(i);
        }

        public final void writeUInt64(int i, long l)
            throws IOException
        {
            if (limit - position < 20)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            bufferUInt32NoTag(i << 3 | 0);
            bufferUInt64NoTag(l);
        }

        public final void writeUInt64NoTag(long l)
            throws IOException
        {
            if (limit - position < 10)
            {
                out.write(buffer, 0, position);
                position = 0;
            }
            bufferUInt64NoTag(l);
        }

        OutputStreamEncoder(OutputStream outputstream, int i)
        {
            super(i);
            if (outputstream == null)
            {
                throw new NullPointerException("out");
            } else
            {
                out = outputstream;
                return;
            }
        }
    }

    static final class SafeDirectNioEncoder extends CodedOutputStream
    {

        private final ByteBuffer buffer;
        private final ByteBuffer originalBuffer;

        public final void flush()
        {
            originalBuffer.position(buffer.position());
        }

        public final int spaceLeft()
        {
            return buffer.remaining();
        }

        public final void write(byte byte0)
            throws IOException
        {
            try
            {
                buffer.put(byte0);
                return;
            }
            catch (BufferOverflowException bufferoverflowexception)
            {
                throw new OutOfSpaceException(bufferoverflowexception);
            }
        }

        public final void write(byte abyte0[], int i, int j)
            throws IOException
        {
            try
            {
                buffer.put(abyte0, i, j);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (byte abyte0[])
            {
                throw new OutOfSpaceException(abyte0);
            }
            // Misplaced declaration of an exception variable
            catch (byte abyte0[])
            {
                throw new OutOfSpaceException(abyte0);
            }
        }

        public final void writeBool(int i, boolean flag)
            throws IOException
        {
            boolean flag1 = false;
            writeTag(i, 0);
            i = ((flag1) ? 1 : 0);
            if (flag)
            {
                i = 1;
            }
            write((byte)i);
        }

        public final void writeByteArrayNoTag(byte abyte0[], int i, int j)
            throws IOException
        {
            writeUInt32NoTag(j);
            write(abyte0, 0, j);
        }

        public final void writeBytes(int i, ByteString bytestring)
            throws IOException
        {
            writeTag(i, 2);
            writeBytesNoTag(bytestring);
        }

        public final void writeBytesNoTag(ByteString bytestring)
            throws IOException
        {
            writeUInt32NoTag(bytestring.size());
            bytestring.writeTo(this);
        }

        public final void writeFixed32(int i, int j)
            throws IOException
        {
            writeTag(i, 5);
            writeFixed32NoTag(j);
        }

        public final void writeFixed32NoTag(int i)
            throws IOException
        {
            try
            {
                buffer.putInt(i);
                return;
            }
            catch (BufferOverflowException bufferoverflowexception)
            {
                throw new OutOfSpaceException(bufferoverflowexception);
            }
        }

        public final void writeFixed64(int i, long l)
            throws IOException
        {
            writeTag(i, 1);
            writeFixed64NoTag(l);
        }

        public final void writeFixed64NoTag(long l)
            throws IOException
        {
            try
            {
                buffer.putLong(l);
                return;
            }
            catch (BufferOverflowException bufferoverflowexception)
            {
                throw new OutOfSpaceException(bufferoverflowexception);
            }
        }

        public final void writeInt32(int i, int j)
            throws IOException
        {
            writeTag(i, 0);
            writeInt32NoTag(j);
        }

        public final void writeInt32NoTag(int i)
            throws IOException
        {
            if (i >= 0)
            {
                writeUInt32NoTag(i);
                return;
            } else
            {
                writeUInt64NoTag(i);
                return;
            }
        }

        public final void writeLazy(byte abyte0[], int i, int j)
            throws IOException
        {
            write(abyte0, i, j);
        }

        public final void writeMessage(int i, MessageLite messagelite)
            throws IOException
        {
            writeTag(i, 2);
            writeMessageNoTag(messagelite);
        }

        final void writeMessage(int i, MessageLite messagelite, Schema schema)
            throws IOException
        {
            writeTag(i, 2);
            writeMessageNoTag(messagelite, schema);
        }

        public final void writeMessageNoTag(MessageLite messagelite)
            throws IOException
        {
            writeUInt32NoTag(messagelite.getSerializedSize());
            messagelite.writeTo(this);
        }

        final void writeMessageNoTag(MessageLite messagelite, Schema schema)
            throws IOException
        {
            AbstractMessageLite abstractmessagelite = (AbstractMessageLite)messagelite;
            int j = abstractmessagelite.getMemoizedSerializedSize();
            int i = j;
            if (j == -1)
            {
                i = schema.getSerializedSize(abstractmessagelite);
                abstractmessagelite.setMemoizedSerializedSize(i);
            }
            writeUInt32NoTag(i);
            schema.writeTo(messagelite, wrapper);
        }

        public final void writeMessageSetExtension(int i, MessageLite messagelite)
            throws IOException
        {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeMessage(3, messagelite);
            writeTag(1, 4);
        }

        public final void writeRawMessageSetExtension(int i, ByteString bytestring)
            throws IOException
        {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeBytes(3, bytestring);
            writeTag(1, 4);
        }

        public final void writeString(int i, String s)
            throws IOException
        {
            writeTag(i, 2);
            writeStringNoTag(s);
        }

        public final void writeStringNoTag(String s)
            throws IOException
        {
            int i = buffer.position();
            int j;
            int l;
            j = computeUInt32SizeNoTag(s.length() * 3);
            l = computeUInt32SizeNoTag(s.length());
            if (l != j)
            {
                break MISSING_BLOCK_LABEL_221;
            }
            j = buffer.position() + l;
            buffer.position(j);
            Object obj;
            byte abyte0[];
            obj = buffer;
            abyte0 = Utf8.processor;
            if (!((ByteBuffer) (obj)).hasArray()) goto _L2; else goto _L1
_L1:
            int i1 = ((ByteBuffer) (obj)).arrayOffset();
            abyte0 = ((ByteBuffer) (obj)).array();
            int l1 = ((ByteBuffer) (obj)).position();
            int j2 = ((ByteBuffer) (obj)).remaining();
            ((ByteBuffer) (obj)).position(Utf8.processor.encodeUtf8(s, abyte0, l1 + i1, j2) - i1);
_L3:
            int j1 = buffer.position();
            buffer.position(i);
            writeUInt32NoTag(j1 - j);
            buffer.position(j1);
            return;
_L2:
            try
            {
                if (!((ByteBuffer) (obj)).isDirect())
                {
                    break MISSING_BLOCK_LABEL_203;
                }
                abyte0.encodeUtf8Direct(s, ((ByteBuffer) (obj)));
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                try
                {
                    throw new OutOfSpaceException(((Throwable) (obj)));
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    buffer.position(i);
                }
                // Misplaced declaration of an exception variable
                catch (String s)
                {
                    throw new OutOfSpaceException(s);
                }
                inefficientWriteStringNoTag(s, ((Utf8.UnpairedSurrogateException) (obj)));
                return;
            }
              goto _L3
            Utf8.Processor.encodeUtf8Default(s, ((ByteBuffer) (obj)));
              goto _L3
            writeUInt32NoTag(Utf8.encodedLength(s));
            try
            {
                obj = buffer;
                abyte0 = Utf8.processor;
                if (((ByteBuffer) (obj)).hasArray())
                {
                    int k = ((ByteBuffer) (obj)).arrayOffset();
                    abyte0 = ((ByteBuffer) (obj)).array();
                    int k1 = ((ByteBuffer) (obj)).position();
                    int i2 = ((ByteBuffer) (obj)).remaining();
                    ((ByteBuffer) (obj)).position(Utf8.processor.encodeUtf8(s, abyte0, k1 + k, i2) - k);
                    return;
                }
                break MISSING_BLOCK_LABEL_302;
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
            throw new OutOfSpaceException(((Throwable) (obj)));
            if (((ByteBuffer) (obj)).isDirect())
            {
                abyte0.encodeUtf8Direct(s, ((ByteBuffer) (obj)));
                return;
            }
            Utf8.Processor.encodeUtf8Default(s, ((ByteBuffer) (obj)));
            return;
        }

        public final void writeTag(int i, int j)
            throws IOException
        {
            writeUInt32NoTag(i << 3 | j);
        }

        public final void writeUInt32(int i, int j)
            throws IOException
        {
            writeTag(i, 0);
            writeUInt32NoTag(j);
        }

        public final void writeUInt32NoTag(int i)
            throws IOException
        {
_L1:
            if ((i & 0xffffff80) == 0)
            {
                try
                {
                    buffer.put((byte)i);
                    return;
                }
                catch (BufferOverflowException bufferoverflowexception)
                {
                    throw new OutOfSpaceException(bufferoverflowexception);
                }
            }
            buffer.put((byte)(i & 0x7f | 0x80));
            i >>>= 7;
              goto _L1
        }

        public final void writeUInt64(int i, long l)
            throws IOException
        {
            writeTag(i, 0);
            writeUInt64NoTag(l);
        }

        public final void writeUInt64NoTag(long l)
            throws IOException
        {
_L1:
            if ((-128L & l) == 0L)
            {
                try
                {
                    buffer.put((byte)(int)l);
                    return;
                }
                catch (BufferOverflowException bufferoverflowexception)
                {
                    throw new OutOfSpaceException(bufferoverflowexception);
                }
            }
            buffer.put((byte)((int)l & 0x7f | 0x80));
            l >>>= 7;
              goto _L1
        }

        SafeDirectNioEncoder(ByteBuffer bytebuffer)
        {
            originalBuffer = bytebuffer;
            buffer = bytebuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            bytebuffer.position();
        }
    }

    static final class UnsafeDirectNioEncoder extends CodedOutputStream
    {

        private final long address;
        private final ByteBuffer buffer;
        private final long initialPosition;
        private final long limit;
        private final long oneVarintLimit;
        private final ByteBuffer originalBuffer;
        private long position;

        public final void flush()
        {
            originalBuffer.position((int)(position - address));
        }

        public final int spaceLeft()
        {
            return (int)(limit - position);
        }

        public final void write(byte byte0)
            throws IOException
        {
            if (position >= limit)
            {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                    Long.valueOf(position), Long.valueOf(limit), Integer.valueOf(1)
                }));
            } else
            {
                long l = position;
                position = 1L + l;
                UnsafeUtil.MEMORY_ACCESSOR.putByte(l, byte0);
                return;
            }
        }

        public final void write(byte abyte0[], int i, int j)
            throws IOException
        {
            if (abyte0 == null || i < 0 || j < 0 || abyte0.length - j < i || limit - (long)j < position)
            {
                if (abyte0 == null)
                {
                    throw new NullPointerException("value");
                } else
                {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                        Long.valueOf(position), Long.valueOf(limit), Integer.valueOf(j)
                    }));
                }
            } else
            {
                long l = i;
                long l1 = position;
                long l2 = j;
                UnsafeUtil.MEMORY_ACCESSOR.copyMemory(abyte0, l, l1, l2);
                position = position + (long)j;
                return;
            }
        }

        public final void writeBool(int i, boolean flag)
            throws IOException
        {
            boolean flag1 = false;
            writeTag(i, 0);
            i = ((flag1) ? 1 : 0);
            if (flag)
            {
                i = 1;
            }
            write((byte)i);
        }

        public final void writeByteArrayNoTag(byte abyte0[], int i, int j)
            throws IOException
        {
            writeUInt32NoTag(j);
            write(abyte0, 0, j);
        }

        public final void writeBytes(int i, ByteString bytestring)
            throws IOException
        {
            writeTag(i, 2);
            writeBytesNoTag(bytestring);
        }

        public final void writeBytesNoTag(ByteString bytestring)
            throws IOException
        {
            writeUInt32NoTag(bytestring.size());
            bytestring.writeTo(this);
        }

        public final void writeFixed32(int i, int j)
            throws IOException
        {
            writeTag(i, 5);
            writeFixed32NoTag(j);
        }

        public final void writeFixed32NoTag(int i)
            throws IOException
        {
            buffer.putInt((int)(position - address), i);
            position = position + 4L;
        }

        public final void writeFixed64(int i, long l)
            throws IOException
        {
            writeTag(i, 1);
            writeFixed64NoTag(l);
        }

        public final void writeFixed64NoTag(long l)
            throws IOException
        {
            buffer.putLong((int)(position - address), l);
            position = position + 8L;
        }

        public final void writeInt32(int i, int j)
            throws IOException
        {
            writeTag(i, 0);
            writeInt32NoTag(j);
        }

        public final void writeInt32NoTag(int i)
            throws IOException
        {
            if (i >= 0)
            {
                writeUInt32NoTag(i);
                return;
            } else
            {
                writeUInt64NoTag(i);
                return;
            }
        }

        public final void writeLazy(byte abyte0[], int i, int j)
            throws IOException
        {
            write(abyte0, i, j);
        }

        public final void writeMessage(int i, MessageLite messagelite)
            throws IOException
        {
            writeTag(i, 2);
            writeMessageNoTag(messagelite);
        }

        final void writeMessage(int i, MessageLite messagelite, Schema schema)
            throws IOException
        {
            writeTag(i, 2);
            writeMessageNoTag(messagelite, schema);
        }

        public final void writeMessageNoTag(MessageLite messagelite)
            throws IOException
        {
            writeUInt32NoTag(messagelite.getSerializedSize());
            messagelite.writeTo(this);
        }

        final void writeMessageNoTag(MessageLite messagelite, Schema schema)
            throws IOException
        {
            AbstractMessageLite abstractmessagelite = (AbstractMessageLite)messagelite;
            int j = abstractmessagelite.getMemoizedSerializedSize();
            int i = j;
            if (j == -1)
            {
                i = schema.getSerializedSize(abstractmessagelite);
                abstractmessagelite.setMemoizedSerializedSize(i);
            }
            writeUInt32NoTag(i);
            schema.writeTo(messagelite, wrapper);
        }

        public final void writeMessageSetExtension(int i, MessageLite messagelite)
            throws IOException
        {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeMessage(3, messagelite);
            writeTag(1, 4);
        }

        public final void writeRawMessageSetExtension(int i, ByteString bytestring)
            throws IOException
        {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeBytes(3, bytestring);
            writeTag(1, 4);
        }

        public final void writeString(int i, String s)
            throws IOException
        {
            writeTag(i, 2);
            writeStringNoTag(s);
        }

        public final void writeStringNoTag(String s)
            throws IOException
        {
            Object obj;
            byte abyte0[];
            long l1 = position;
            int i;
            int j;
            int k;
            int l;
            long l2;
            try
            {
                i = computeUInt32SizeNoTag(s.length() * 3);
                j = computeUInt32SizeNoTag(s.length());
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                position = l1;
                l1 = position;
                buffer.position((int)(l1 - address));
                inefficientWriteStringNoTag(s, ((Utf8.UnpairedSurrogateException) (obj)));
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                throw new OutOfSpaceException(s);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                throw new OutOfSpaceException(s);
            }
            if (j != i)
            {
                break MISSING_BLOCK_LABEL_225;
            }
            i = (int)(position - address) + j;
            buffer.position(i);
            obj = buffer;
            abyte0 = Utf8.processor;
            if (!((ByteBuffer) (obj)).hasArray()) goto _L2; else goto _L1
_L1:
            j = ((ByteBuffer) (obj)).arrayOffset();
            abyte0 = ((ByteBuffer) (obj)).array();
            k = ((ByteBuffer) (obj)).position();
            l = ((ByteBuffer) (obj)).remaining();
            ((ByteBuffer) (obj)).position(Utf8.processor.encodeUtf8(s, abyte0, k + j, l) - j);
_L3:
            i = buffer.position() - i;
            writeUInt32NoTag(i);
            l2 = position;
            position = (long)i + l2;
            return;
_L2:
label0:
            {
                if (!((ByteBuffer) (obj)).isDirect())
                {
                    break label0;
                }
                abyte0.encodeUtf8Direct(s, ((ByteBuffer) (obj)));
            }
              goto _L3
            Utf8.Processor.encodeUtf8Default(s, ((ByteBuffer) (obj)));
              goto _L3
            i = Utf8.encodedLength(s);
            writeUInt32NoTag(i);
            l2 = position;
            buffer.position((int)(l2 - address));
            obj = buffer;
            abyte0 = Utf8.processor;
            if (!((ByteBuffer) (obj)).hasArray()) goto _L5; else goto _L4
_L4:
            j = ((ByteBuffer) (obj)).arrayOffset();
            abyte0 = ((ByteBuffer) (obj)).array();
            k = ((ByteBuffer) (obj)).position();
            l = ((ByteBuffer) (obj)).remaining();
            ((ByteBuffer) (obj)).position(Utf8.processor.encodeUtf8(s, abyte0, k + j, l) - j);
_L6:
            l2 = position;
            position = (long)i + l2;
            return;
_L5:
label1:
            {
                if (!((ByteBuffer) (obj)).isDirect())
                {
                    break label1;
                }
                abyte0.encodeUtf8Direct(s, ((ByteBuffer) (obj)));
            }
              goto _L6
            Utf8.Processor.encodeUtf8Default(s, ((ByteBuffer) (obj)));
              goto _L6
        }

        public final void writeTag(int i, int j)
            throws IOException
        {
            writeUInt32NoTag(i << 3 | j);
        }

        public final void writeUInt32(int i, int j)
            throws IOException
        {
            writeTag(i, 0);
            writeUInt32NoTag(j);
        }

        public final void writeUInt32NoTag(int i)
            throws IOException
        {
            int j;
            j = i;
            if (position <= oneVarintLimit)
            {
                do
                {
                    if ((i & 0xffffff80) == 0)
                    {
                        long l = position;
                        position = l + 1L;
                        byte byte0 = (byte)i;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(l, byte0);
                        return;
                    }
                    long l1 = position;
                    position = l1 + 1L;
                    byte byte1 = (byte)(i & 0x7f | 0x80);
                    UnsafeUtil.MEMORY_ACCESSOR.putByte(l1, byte1);
                    i >>>= 7;
                } while (true);
            }
_L3:
            if (position < limit)
            {
                if ((j & 0xffffff80) == 0)
                {
                    long l3 = position;
                    position = l3 + 1L;
                    byte byte3 = (byte)j;
                    UnsafeUtil.MEMORY_ACCESSOR.putByte(l3, byte3);
                    return;
                }
            } else
            {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                    Long.valueOf(position), Long.valueOf(limit), Integer.valueOf(1)
                }));
            }
            if (true) goto _L2; else goto _L1
_L2:
            long l2 = position;
            position = l2 + 1L;
            byte byte2 = (byte)(j & 0x7f | 0x80);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(l2, byte2);
            j >>>= 7;
            if (true) goto _L3; else goto _L1
_L1:
        }

        public final void writeUInt64(int i, long l)
            throws IOException
        {
            writeTag(i, 0);
            writeUInt64NoTag(l);
        }

        public final void writeUInt64NoTag(long l)
            throws IOException
        {
            long l1;
            l1 = l;
            if (position <= oneVarintLimit)
            {
                do
                {
                    if ((l & -128L) == 0L)
                    {
                        l1 = position;
                        position = l1 + 1L;
                        byte byte0 = (byte)(int)l;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(l1, byte0);
                        return;
                    }
                    l1 = position;
                    position = l1 + 1L;
                    byte byte1 = (byte)((int)l & 0x7f | 0x80);
                    UnsafeUtil.MEMORY_ACCESSOR.putByte(l1, byte1);
                    l >>>= 7;
                } while (true);
            }
_L3:
            if (position < limit)
            {
                if ((l1 & -128L) == 0L)
                {
                    l = position;
                    position = l + 1L;
                    byte byte3 = (byte)(int)l1;
                    UnsafeUtil.MEMORY_ACCESSOR.putByte(l, byte3);
                    return;
                }
            } else
            {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                    Long.valueOf(position), Long.valueOf(limit), Integer.valueOf(1)
                }));
            }
            if (true) goto _L2; else goto _L1
_L2:
            l = position;
            position = l + 1L;
            byte byte2 = (byte)((int)l1 & 0x7f | 0x80);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(l, byte2);
            l1 >>>= 7;
            if (true) goto _L3; else goto _L1
_L1:
        }

        UnsafeDirectNioEncoder(ByteBuffer bytebuffer)
        {
            originalBuffer = bytebuffer;
            buffer = bytebuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            UnsafeUtil.MemoryAccessor memoryaccessor = UnsafeUtil.MEMORY_ACCESSOR;
            long l = UnsafeUtil.BUFFER_ADDRESS_OFFSET;
            address = memoryaccessor.unsafe.getLong(bytebuffer, l);
            initialPosition = address + (long)bytebuffer.position();
            limit = address + (long)bytebuffer.limit();
            oneVarintLimit = limit - 10L;
            position = initialPosition;
        }
    }


    public static final boolean HAS_UNSAFE_ARRAY_OPERATIONS;
    private static final Logger logger = Logger.getLogger(com/google/protobuf/CodedOutputStream.getName());
    public CodedOutputStreamWriter wrapper;

    CodedOutputStream()
    {
    }

    public static int computeBoolSize(int i, boolean flag)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + 1;
    }

    public static int computeBoolSizeNoTag$51D2II8_0()
    {
        return 1;
    }

    public static int computeByteArraySizeNoTag(byte abyte0[])
    {
        int i = abyte0.length;
        return i + computeUInt32SizeNoTag(i);
    }

    public static int computeBytesSize(int i, ByteString bytestring)
    {
        i = computeUInt32SizeNoTag(0 | i << 3);
        int j = bytestring.size();
        return i + (j + computeUInt32SizeNoTag(j));
    }

    public static int computeBytesSizeNoTag(ByteString bytestring)
    {
        int i = bytestring.size();
        return i + computeUInt32SizeNoTag(i);
    }

    public static int computeDoubleSize(int i, double d)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + 8;
    }

    public static int computeDoubleSizeNoTag$5122II8_0()
    {
        return 8;
    }

    public static int computeEnumSize(int i, int j)
    {
        int k = computeUInt32SizeNoTag(0 | i << 3);
        if (j >= 0)
        {
            i = computeUInt32SizeNoTag(j);
        } else
        {
            i = 10;
        }
        return i + k;
    }

    public static int computeEnumSizeNoTag(int i)
    {
        if (i >= 0)
        {
            return computeUInt32SizeNoTag(i);
        } else
        {
            return 10;
        }
    }

    public static int computeFixed32Size(int i, int j)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + 4;
    }

    public static int computeFixed32SizeNoTag$514III8_0()
    {
        return 4;
    }

    public static int computeFixed64Size(int i, long l)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + 8;
    }

    public static int computeFixed64SizeNoTag$5152II8_0()
    {
        return 8;
    }

    public static int computeFloatSize(int i, float f)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + 4;
    }

    public static int computeFloatSizeNoTag$5132II8_0()
    {
        return 4;
    }

    static int computeGroupSize(int i, MessageLite messagelite, Schema schema)
    {
        int k = computeUInt32SizeNoTag(0 | i << 3);
        messagelite = (AbstractMessageLite)messagelite;
        int j = messagelite.getMemoizedSerializedSize();
        i = j;
        if (j == -1)
        {
            i = schema.getSerializedSize(messagelite);
            messagelite.setMemoizedSerializedSize(i);
        }
        return i + (k << 1);
    }

    public static int computeGroupSizeNoTag(MessageLite messagelite)
    {
        return messagelite.getSerializedSize();
    }

    public static int computeInt32Size(int i, int j)
    {
        int k = computeUInt32SizeNoTag(0 | i << 3);
        if (j >= 0)
        {
            i = computeUInt32SizeNoTag(j);
        } else
        {
            i = 10;
        }
        return i + k;
    }

    public static int computeInt32SizeNoTag(int i)
    {
        if (i >= 0)
        {
            return computeUInt32SizeNoTag(i);
        } else
        {
            return 10;
        }
    }

    public static int computeInt64Size(int i, long l)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + computeUInt64SizeNoTag(l);
    }

    public static int computeInt64SizeNoTag(long l)
    {
        return computeUInt64SizeNoTag(l);
    }

    public static int computeLazyFieldMessageSetExtensionSize(int i, LazyFieldLite lazyfieldlite)
    {
        return (computeUInt32SizeNoTag(8) << 1) + (computeUInt32SizeNoTag(16) + computeUInt32SizeNoTag(i)) + computeLazyFieldSize(3, lazyfieldlite);
    }

    public static int computeLazyFieldSize(int i, LazyFieldLite lazyfieldlite)
    {
        boolean flag;
        int j;
        flag = false;
        j = computeUInt32SizeNoTag(i << 3 | 0);
        if (lazyfieldlite.memoizedBytes == null) goto _L2; else goto _L1
_L1:
        i = lazyfieldlite.memoizedBytes.size();
_L4:
        return i + computeUInt32SizeNoTag(i) + j;
_L2:
        i = ((flag) ? 1 : 0);
        if (lazyfieldlite.value != null)
        {
            i = lazyfieldlite.value.getSerializedSize();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static int computeLazyFieldSizeNoTag(LazyFieldLite lazyfieldlite)
    {
        int i;
        if (lazyfieldlite.memoizedBytes != null)
        {
            i = lazyfieldlite.memoizedBytes.size();
        } else
        if (lazyfieldlite.value != null)
        {
            i = lazyfieldlite.value.getSerializedSize();
        } else
        {
            i = 0;
        }
        return i + computeUInt32SizeNoTag(i);
    }

    static int computeLengthDelimitedFieldSize(int i)
    {
        return computeUInt32SizeNoTag(i) + i;
    }

    public static int computeMessageSetExtensionSize(int i, MessageLite messagelite)
    {
        return (computeUInt32SizeNoTag(8) << 1) + (computeUInt32SizeNoTag(16) + computeUInt32SizeNoTag(i)) + computeMessageSize(3, messagelite);
    }

    public static int computeMessageSize(int i, MessageLite messagelite)
    {
        i = computeUInt32SizeNoTag(0 | i << 3);
        int j = messagelite.getSerializedSize();
        return i + (j + computeUInt32SizeNoTag(j));
    }

    static int computeMessageSize(int i, MessageLite messagelite, Schema schema)
    {
        int k = computeUInt32SizeNoTag(0 | i << 3);
        messagelite = (AbstractMessageLite)messagelite;
        int j = messagelite.getMemoizedSerializedSize();
        i = j;
        if (j == -1)
        {
            i = schema.getSerializedSize(messagelite);
            messagelite.setMemoizedSerializedSize(i);
        }
        return i + computeUInt32SizeNoTag(i) + k;
    }

    public static int computeMessageSizeNoTag(MessageLite messagelite)
    {
        int i = messagelite.getSerializedSize();
        return i + computeUInt32SizeNoTag(i);
    }

    static int computeMessageSizeNoTag(MessageLite messagelite, Schema schema)
    {
        messagelite = (AbstractMessageLite)messagelite;
        int j = messagelite.getMemoizedSerializedSize();
        int i = j;
        if (j == -1)
        {
            i = schema.getSerializedSize(messagelite);
            messagelite.setMemoizedSerializedSize(i);
        }
        return i + computeUInt32SizeNoTag(i);
    }

    static int computePreferredBufferSize(int i)
    {
        int j = i;
        if (i > 4096)
        {
            j = 4096;
        }
        return j;
    }

    public static int computeRawMessageSetExtensionSize(int i, ByteString bytestring)
    {
        int j = computeUInt32SizeNoTag(8);
        int k = computeUInt32SizeNoTag(16);
        i = computeUInt32SizeNoTag(i);
        int l = computeUInt32SizeNoTag(24);
        int i1 = bytestring.size();
        return (j << 1) + (k + i) + (l + (i1 + computeUInt32SizeNoTag(i1)));
    }

    public static int computeRawVarint32Size(int i)
    {
        return computeUInt32SizeNoTag(i);
    }

    public static int computeSFixed32Size(int i, int j)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + 4;
    }

    public static int computeSFixed32SizeNoTag$514III8_0()
    {
        return 4;
    }

    public static int computeSFixed64Size(int i, long l)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + 8;
    }

    public static int computeSFixed64SizeNoTag$5152II8_0()
    {
        return 8;
    }

    public static int computeSInt32Size(int i, int j)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + computeUInt32SizeNoTag(j << 1 ^ j >> 31);
    }

    public static int computeSInt32SizeNoTag(int i)
    {
        return computeUInt32SizeNoTag(i << 1 ^ i >> 31);
    }

    public static int computeSInt64Size(int i, long l)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + computeUInt64SizeNoTag(l << 1 ^ l >> 63);
    }

    public static int computeSInt64SizeNoTag(long l)
    {
        return computeUInt64SizeNoTag(l << 1 ^ l >> 63);
    }

    public static int computeStringSize(int i, String s)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + computeStringSizeNoTag(s);
    }

    public static int computeStringSizeNoTag(String s)
    {
        int i;
        try
        {
            i = Utf8.encodedLength(s);
        }
        catch (Utf8.UnpairedSurrogateException unpairedsurrogateexception)
        {
            i = s.getBytes(Internal.UTF_8).length;
        }
        return i + computeUInt32SizeNoTag(i);
    }

    public static int computeTagSize(int i)
    {
        return computeUInt32SizeNoTag(0 | i << 3);
    }

    public static int computeUInt32Size(int i, int j)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + computeUInt32SizeNoTag(j);
    }

    public static int computeUInt32SizeNoTag(int i)
    {
        if ((i & 0xffffff80) == 0)
        {
            return 1;
        }
        if ((i & 0xffffc000) == 0)
        {
            return 2;
        }
        if ((0xffe00000 & i) == 0)
        {
            return 3;
        }
        return (0xf0000000 & i) != 0 ? 5 : 4;
    }

    public static int computeUInt64Size(int i, long l)
    {
        return computeUInt32SizeNoTag(0 | i << 3) + computeUInt64SizeNoTag(l);
    }

    public static int computeUInt64SizeNoTag(long l)
    {
        int j;
        if ((-128L & l) == 0L)
        {
            j = 1;
        } else
        {
            if (l < 0L)
            {
                return 10;
            }
            j = 2;
            if ((0xfffffff800000000L & l) != 0L)
            {
                j = 6;
                l >>>= 28;
            }
            int i = j;
            long l1 = l;
            if ((0xffffffffffe00000L & l) != 0L)
            {
                i = j + 2;
                l1 = l >>> 14;
            }
            j = i;
            if ((l1 & -16384L) != 0L)
            {
                return i + 1;
            }
        }
        return j;
    }

    public static CodedOutputStream newInstance(OutputStream outputstream, int i)
    {
        return new OutputStreamEncoder(outputstream, i);
    }

    public static CodedOutputStream newInstance(ByteBuffer bytebuffer)
    {
        if (bytebuffer.hasArray())
        {
            return new HeapNioEncoder(bytebuffer);
        }
        if (bytebuffer.isDirect() && !bytebuffer.isReadOnly())
        {
            if (UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS)
            {
                return new UnsafeDirectNioEncoder(bytebuffer);
            } else
            {
                return new SafeDirectNioEncoder(bytebuffer);
            }
        } else
        {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        }
    }

    public static CodedOutputStream newInstance(byte abyte0[])
    {
        return new ArrayEncoder(abyte0, 0, abyte0.length);
    }

    public static CodedOutputStream newInstance(byte abyte0[], int i, int j)
    {
        return new ArrayEncoder(abyte0, i, j);
    }

    public abstract void flush()
        throws IOException;

    final void inefficientWriteStringNoTag(String s, Utf8.UnpairedSurrogateException unpairedsurrogateexception)
        throws IOException
    {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", unpairedsurrogateexception);
        s = s.getBytes(Internal.UTF_8);
        try
        {
            writeUInt32NoTag(s.length);
            writeLazy(s, 0, s.length);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new OutOfSpaceException(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw s;
        }
    }

    public abstract int spaceLeft();

    public abstract void write(byte byte0)
        throws IOException;

    public abstract void write(byte abyte0[], int i, int j)
        throws IOException;

    public abstract void writeBool(int i, boolean flag)
        throws IOException;

    abstract void writeByteArrayNoTag(byte abyte0[], int i, int j)
        throws IOException;

    public abstract void writeBytes(int i, ByteString bytestring)
        throws IOException;

    public abstract void writeBytesNoTag(ByteString bytestring)
        throws IOException;

    public abstract void writeFixed32(int i, int j)
        throws IOException;

    public abstract void writeFixed32NoTag(int i)
        throws IOException;

    public abstract void writeFixed64(int i, long l)
        throws IOException;

    public abstract void writeFixed64NoTag(long l)
        throws IOException;

    public abstract void writeInt32(int i, int j)
        throws IOException;

    public abstract void writeInt32NoTag(int i)
        throws IOException;

    public abstract void writeMessage(int i, MessageLite messagelite)
        throws IOException;

    abstract void writeMessage(int i, MessageLite messagelite, Schema schema)
        throws IOException;

    public abstract void writeMessageNoTag(MessageLite messagelite)
        throws IOException;

    abstract void writeMessageNoTag(MessageLite messagelite, Schema schema)
        throws IOException;

    public abstract void writeMessageSetExtension(int i, MessageLite messagelite)
        throws IOException;

    public abstract void writeRawMessageSetExtension(int i, ByteString bytestring)
        throws IOException;

    public abstract void writeString(int i, String s)
        throws IOException;

    public abstract void writeStringNoTag(String s)
        throws IOException;

    public abstract void writeTag(int i, int j)
        throws IOException;

    public abstract void writeUInt32(int i, int j)
        throws IOException;

    public abstract void writeUInt32NoTag(int i)
        throws IOException;

    public abstract void writeUInt64(int i, long l)
        throws IOException;

    public abstract void writeUInt64NoTag(long l)
        throws IOException;

    static 
    {
        HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS;
    }
}
