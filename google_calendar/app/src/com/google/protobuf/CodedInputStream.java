// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.google.protobuf:
//            Internal, InvalidProtocolBufferException, CodedInputStreamReader, ByteString, 
//            Parser, ExtensionRegistryLite, MessageLite

public abstract class CodedInputStream
{

    private static volatile boolean proto3DiscardUnknownFieldsDefault = false;
    public int recursionDepth;
    public int recursionLimit;
    public int sizeLimit;
    public CodedInputStreamReader wrapper;

    CodedInputStream()
    {
        recursionLimit = 100;
        sizeLimit = 0x7fffffff;
    }

    public static CodedInputStream newInstance(InputStream inputstream)
    {
        if (inputstream == null)
        {
            inputstream = Internal.EMPTY_BYTE_ARRAY;
            return newInstance(((byte []) (inputstream)), 0, inputstream.length, false);
        } else
        {
            return new StreamDecoder(inputstream, 4096);
        }
    }

    public static CodedInputStream newInstance(byte abyte0[], int i, int j, boolean flag)
    {
        abyte0 = new ArrayDecoder(abyte0, i, j, flag);
        try
        {
            abyte0.pushLimit(j);
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new IllegalArgumentException(abyte0);
        }
        return abyte0;
    }

    public abstract void checkLastTagWas(int i)
        throws InvalidProtocolBufferException;

    public abstract int getLastTag();

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd()
        throws IOException;

    public abstract void popLimit(int i);

    public abstract int pushLimit(int i)
        throws InvalidProtocolBufferException;

    public abstract boolean readBool()
        throws IOException;

    public abstract ByteString readBytes()
        throws IOException;

    public abstract double readDouble()
        throws IOException;

    public abstract int readEnum()
        throws IOException;

    public abstract int readFixed32()
        throws IOException;

    public abstract long readFixed64()
        throws IOException;

    public abstract float readFloat()
        throws IOException;

    public abstract int readInt32()
        throws IOException;

    public abstract long readInt64()
        throws IOException;

    public abstract MessageLite readMessage(Parser parser, ExtensionRegistryLite extensionregistrylite)
        throws IOException;

    abstract long readRawVarint64SlowPath()
        throws IOException;

    public abstract int readSFixed32()
        throws IOException;

    public abstract long readSFixed64()
        throws IOException;

    public abstract int readSInt32()
        throws IOException;

    public abstract long readSInt64()
        throws IOException;

    public abstract String readString()
        throws IOException;

    public abstract String readStringRequireUtf8()
        throws IOException;

    public abstract int readTag()
        throws IOException;

    public abstract int readUInt32()
        throws IOException;

    public abstract long readUInt64()
        throws IOException;

    public abstract boolean skipField(int i)
        throws IOException;

    public abstract void skipRawBytes(int i)
        throws IOException;


    private class StreamDecoder extends CodedInputStream
    {

        private final byte buffer[];
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final InputStream input;
        private int lastTag;
        private int pos;
        private RefillCallback refillCallback;
        private int totalBytesRetired;

        private final byte readRawByte()
            throws IOException
        {
            if (pos == bufferSize)
            {
                refillBuffer(1);
            }
            byte abyte0[] = buffer;
            int i = pos;
            pos = i + 1;
            return abyte0[i];
        }

        private final byte[] readRawBytesSlowPath(int i)
            throws IOException
        {
            byte abyte0[] = readRawBytesSlowPathOneChunk(i);
            if (abyte0 != null)
            {
                return abyte0;
            }
            int k = pos;
            int j = bufferSize - pos;
            totalBytesRetired = totalBytesRetired + bufferSize;
            pos = 0;
            bufferSize = 0;
            Object obj = readRawBytesSlowPathRemainingChunks(i - j);
            abyte0 = new byte[i];
            System.arraycopy(buffer, k, abyte0, 0, j);
            obj = ((List) (obj)).iterator();
            byte abyte1[];
            for (i = j; ((Iterator) (obj)).hasNext(); i = abyte1.length + i)
            {
                abyte1 = (byte[])((Iterator) (obj)).next();
                System.arraycopy(abyte1, 0, abyte0, i, abyte1.length);
            }

            return abyte0;
        }

        private final byte[] readRawBytesSlowPathOneChunk(int i)
            throws IOException
        {
            if (i == 0)
            {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if (i < 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            int j = totalBytesRetired + pos + i;
            if (j - sizeLimit > 0)
            {
                throw new InvalidProtocolBufferException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
            }
            if (j > currentLimit)
            {
                skipRawBytes(currentLimit - totalBytesRetired - pos);
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            j = bufferSize - pos;
            int k = i - j;
            if (k < 4096 || k <= input.available())
            {
                byte abyte0[] = new byte[i];
                System.arraycopy(buffer, pos, abyte0, 0, j);
                totalBytesRetired = totalBytesRetired + bufferSize;
                pos = 0;
                bufferSize = 0;
                int l;
                for (; j < abyte0.length; j += l)
                {
                    l = input.read(abyte0, j, i - j);
                    if (l == -1)
                    {
                        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                    }
                    totalBytesRetired = totalBytesRetired + l;
                }

                return abyte0;
            } else
            {
                return null;
            }
        }

        private final List readRawBytesSlowPathRemainingChunks(int i)
            throws IOException
        {
            ArrayList arraylist = new ArrayList();
            while (i > 0) 
            {
                byte abyte0[] = new byte[Math.min(i, 4096)];
                int k;
                for (int j = 0; j < abyte0.length; j += k)
                {
                    k = input.read(abyte0, j, abyte0.length - j);
                    if (k == -1)
                    {
                        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                    }
                    totalBytesRetired = totalBytesRetired + k;
                }

                i -= abyte0.length;
                arraylist.add(abyte0);
            }
            return arraylist;
        }

        private final int readRawLittleEndian32()
            throws IOException
        {
            int j = pos;
            int i = j;
            if (bufferSize - j < 4)
            {
                refillBuffer(4);
                i = pos;
            }
            byte abyte0[] = buffer;
            pos = i + 4;
            j = abyte0[i];
            byte byte0 = abyte0[i + 1];
            byte byte1 = abyte0[i + 2];
            return (abyte0[i + 3] & 0xff) << 24 | (j & 0xff | (byte0 & 0xff) << 8 | (byte1 & 0xff) << 16);
        }

        private final long readRawLittleEndian64()
            throws IOException
        {
            int j = pos;
            int i = j;
            if (bufferSize - j < 8)
            {
                refillBuffer(8);
                i = pos;
            }
            byte abyte0[] = buffer;
            pos = i + 8;
            long l = abyte0[i];
            long l1 = abyte0[i + 1];
            long l2 = abyte0[i + 2];
            long l3 = abyte0[i + 3];
            long l4 = abyte0[i + 4];
            long l5 = abyte0[i + 5];
            long l6 = abyte0[i + 6];
            return ((long)abyte0[i + 7] & 255L) << 56 | (l & 255L | (l1 & 255L) << 8 | (l2 & 255L) << 16 | (l3 & 255L) << 24 | (l4 & 255L) << 32 | (l5 & 255L) << 40 | (l6 & 255L) << 48);
        }

        private final int readRawVarint32()
            throws IOException
        {
            int i = pos;
            if (bufferSize == i) goto _L2; else goto _L1
_L1:
            byte abyte0[];
            int j;
            int k;
            abyte0 = buffer;
            j = i + 1;
            k = abyte0[i];
            if (k >= 0)
            {
                pos = j;
                return k;
            }
            if (bufferSize - j < 9) goto _L2; else goto _L3
_L3:
            i = j + 1;
            k ^= abyte0[j] << 7;
            if (k >= 0) goto _L5; else goto _L4
_L4:
            j = k ^ 0xffffff80;
_L11:
            pos = i;
            return j;
_L5:
            int l;
            j = i + 1;
            k ^= abyte0[i] << 14;
            if (k >= 0)
            {
                k ^= 0x3f80;
                i = j;
                j = k;
                continue; /* Loop/switch isn't completed */
            }
            i = j + 1;
            k ^= abyte0[j] << 21;
            if (k < 0)
            {
                j = k ^ 0xffe03f80;
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            j = abyte0[i];
            k = k ^ j << 28 ^ 0xfe03f80;
            i = l;
            if (j >= 0) goto _L7; else goto _L6
_L6:
            int i1;
            i1 = l + 1;
            j = k;
            i = i1;
            if (abyte0[l] >= 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i1 + 1;
            i = l;
            if (abyte0[i1] >= 0) goto _L7; else goto _L8
_L8:
            i1 = l + 1;
            j = k;
            i = i1;
            if (abyte0[l] >= 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i1 + 1;
            i = l;
            if (abyte0[i1] >= 0) goto _L7; else goto _L9
_L9:
            i = l + 1;
            j = k;
            if (abyte0[l] >= 0)
            {
                continue; /* Loop/switch isn't completed */
            }
_L2:
            return (int)readRawVarint64SlowPath();
_L7:
            j = k;
            if (true) goto _L11; else goto _L10
_L10:
        }

        private final long readRawVarint64()
            throws IOException
        {
            int i = pos;
            if (bufferSize == i) goto _L2; else goto _L1
_L1:
            byte abyte0[];
            int j;
            int k;
            abyte0 = buffer;
            j = i + 1;
            k = abyte0[i];
            if (k >= 0)
            {
                pos = j;
                return (long)k;
            }
            if (bufferSize - j < 9) goto _L2; else goto _L3
_L3:
            i = j + 1;
            k ^= abyte0[j] << 7;
            if (k >= 0) goto _L5; else goto _L4
_L4:
            long l = k ^ 0xffffff80;
_L9:
            pos = i;
            return l;
_L5:
            j = i + 1;
            k ^= abyte0[i] << 14;
            if (k >= 0)
            {
                l = k ^ 0x3f80;
                i = j;
                continue; /* Loop/switch isn't completed */
            }
            i = j + 1;
            j = k ^ abyte0[j] << 21;
            if (j < 0)
            {
                l = j ^ 0xffe03f80;
                continue; /* Loop/switch isn't completed */
            }
            l = j;
            j = i + 1;
            l ^= (long)abyte0[i] << 28;
            if (l >= 0L)
            {
                l ^= 0xfe03f80L;
                i = j;
                continue; /* Loop/switch isn't completed */
            }
            i = j + 1;
            l ^= (long)abyte0[j] << 35;
            if (l < 0L)
            {
                l ^= 0xfffffff80fe03f80L;
                continue; /* Loop/switch isn't completed */
            }
            j = i + 1;
            l ^= (long)abyte0[i] << 42;
            if (l >= 0L)
            {
                l ^= 0x3f80fe03f80L;
                i = j;
                continue; /* Loop/switch isn't completed */
            }
            i = j + 1;
            l ^= (long)abyte0[j] << 49;
            if (l < 0L)
            {
                l ^= 0xfffe03f80fe03f80L;
                continue; /* Loop/switch isn't completed */
            }
            j = i + 1;
            l = l ^ (long)abyte0[i] << 56 ^ 0xfe03f80fe03f80L;
            if (l >= 0L) goto _L7; else goto _L6
_L6:
            i = j + 1;
            if ((long)abyte0[j] >= 0L)
            {
                continue; /* Loop/switch isn't completed */
            }
_L2:
            return readRawVarint64SlowPath();
_L7:
            i = j;
            if (true) goto _L9; else goto _L8
_L8:
        }

        private final void recomputeBufferSizeAfterLimit()
        {
            bufferSize = bufferSize + bufferSizeAfterLimit;
            int i = totalBytesRetired + bufferSize;
            if (i > currentLimit)
            {
                bufferSizeAfterLimit = i - currentLimit;
                bufferSize = bufferSize - bufferSizeAfterLimit;
                return;
            } else
            {
                bufferSizeAfterLimit = 0;
                return;
            }
        }

        private final void refillBuffer(int i)
            throws IOException
        {
            if (!tryRefillBuffer(i))
            {
                if (i > sizeLimit - totalBytesRetired - pos)
                {
                    throw new InvalidProtocolBufferException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
                } else
                {
                    throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
            } else
            {
                return;
            }
        }

        private final boolean tryRefillBuffer(int i)
            throws IOException
        {
_L2:
            if (pos + i <= bufferSize)
            {
                throw new IllegalStateException((new StringBuilder(77)).append("refillBuffer() called when ").append(i).append(" bytes were already available in buffer").toString());
            }
            break MISSING_BLOCK_LABEL_47;
            int j;
            while (j <= 0) 
            {
                do
                {
                    return false;
                } while (i > sizeLimit - totalBytesRetired - pos || totalBytesRetired + pos + i > currentLimit);
                j = pos;
                if (j > 0)
                {
                    if (bufferSize > j)
                    {
                        System.arraycopy(buffer, j, buffer, 0, bufferSize - j);
                    }
                    totalBytesRetired = totalBytesRetired + j;
                    bufferSize = bufferSize - j;
                    pos = 0;
                }
                j = input.read(buffer, bufferSize, Math.min(buffer.length - bufferSize, sizeLimit - totalBytesRetired - bufferSize));
                if (j == 0 || j < -1 || j > buffer.length)
                {
                    throw new IllegalStateException((new StringBuilder(102)).append("InputStream#read(byte[]) returned invalid result: ").append(j).append("\nThe InputStream implementation is buggy.").toString());
                }
            }
            bufferSize = j + bufferSize;
            recomputeBufferSizeAfterLimit();
            if (bufferSize >= i)
            {
                return true;
            }
            if (true) goto _L2; else goto _L1
_L1:
        }

        public final void checkLastTagWas(int i)
            throws InvalidProtocolBufferException
        {
            if (lastTag != i)
            {
                throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
            } else
            {
                return;
            }
        }

        public final int getLastTag()
        {
            return lastTag;
        }

        public final int getTotalBytesRead()
        {
            return totalBytesRetired + pos;
        }

        public final boolean isAtEnd()
            throws IOException
        {
            return pos == bufferSize && !tryRefillBuffer(1);
        }

        public final void popLimit(int i)
        {
            currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public final int pushLimit(int i)
            throws InvalidProtocolBufferException
        {
            if (i < 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            i = totalBytesRetired + pos + i;
            int j = currentLimit;
            if (i > j)
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            } else
            {
                currentLimit = i;
                recomputeBufferSizeAfterLimit();
                return j;
            }
        }

        public final boolean readBool()
            throws IOException
        {
            return readRawVarint64() != 0L;
        }

        public final ByteString readBytes()
            throws IOException
        {
            int i = readRawVarint32();
            if (i <= bufferSize - pos && i > 0)
            {
                ByteString bytestring = ByteString.copyFrom(buffer, pos, i);
                pos = i + pos;
                return bytestring;
            }
            if (i == 0)
            {
                return ByteString.EMPTY;
            }
            byte abyte0[] = readRawBytesSlowPathOneChunk(i);
            if (abyte0 != null)
            {
                return ByteString.wrap(abyte0);
            }
            int j = pos;
            int k = bufferSize - pos;
            totalBytesRetired = totalBytesRetired + bufferSize;
            pos = 0;
            bufferSize = 0;
            Object obj = readRawBytesSlowPathRemainingChunks(i - k);
            ArrayList arraylist = new ArrayList(((List) (obj)).size() + 1);
            arraylist.add(ByteString.copyFrom(buffer, j, k));
            for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); arraylist.add(ByteString.wrap((byte[])((Iterator) (obj)).next()))) { }
            return ByteString.copyFrom(arraylist);
        }

        public final double readDouble()
            throws IOException
        {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public final int readEnum()
            throws IOException
        {
            return readRawVarint32();
        }

        public final int readFixed32()
            throws IOException
        {
            return readRawLittleEndian32();
        }

        public final long readFixed64()
            throws IOException
        {
            return readRawLittleEndian64();
        }

        public final float readFloat()
            throws IOException
        {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public final int readInt32()
            throws IOException
        {
            return readRawVarint32();
        }

        public final long readInt64()
            throws IOException
        {
            return readRawVarint64();
        }

        public final MessageLite readMessage(Parser parser, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            int i = readRawVarint32();
            if (recursionDepth >= recursionLimit)
            {
                throw new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
            } else
            {
                i = pushLimit(i);
                recursionDepth = recursionDepth + 1;
                parser = (MessageLite)parser.parsePartialFrom(this, extensionregistrylite);
                checkLastTagWas(0);
                recursionDepth = recursionDepth - 1;
                popLimit(i);
                return parser;
            }
        }

        final long readRawVarint64SlowPath()
            throws IOException
        {
            long l = 0L;
            for (int i = 0; i < 64; i += 7)
            {
                byte byte0 = readRawByte();
                l |= (long)(byte0 & 0x7f) << i;
                if ((byte0 & 0x80) == 0)
                {
                    return l;
                }
            }

            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
        }

        public final int readSFixed32()
            throws IOException
        {
            return readRawLittleEndian32();
        }

        public final long readSFixed64()
            throws IOException
        {
            return readRawLittleEndian64();
        }

        public final int readSInt32()
            throws IOException
        {
            int i = readRawVarint32();
            return -(i & 1) ^ i >>> 1;
        }

        public final long readSInt64()
            throws IOException
        {
            long l = readRawVarint64();
            return -(l & 1L) ^ l >>> 1;
        }

        public final String readString()
            throws IOException
        {
            int i = readRawVarint32();
            if (i > 0 && i <= bufferSize - pos)
            {
                String s = new String(buffer, pos, i, Internal.UTF_8);
                pos = i + pos;
                return s;
            }
            if (i == 0)
            {
                return "";
            }
            if (i <= bufferSize)
            {
                refillBuffer(i);
                String s1 = new String(buffer, pos, i, Internal.UTF_8);
                pos = i + pos;
                return s1;
            } else
            {
                return new String(readRawBytesSlowPath(i), Internal.UTF_8);
            }
        }

        public final String readStringRequireUtf8()
            throws IOException
        {
            boolean flag = false;
            int j = readRawVarint32();
            int i = pos;
            byte abyte0[];
            if (j <= bufferSize - i && j > 0)
            {
                abyte0 = buffer;
                pos = i + j;
            } else
            {
                if (j == 0)
                {
                    return "";
                }
                if (j <= bufferSize)
                {
                    refillBuffer(j);
                    abyte0 = buffer;
                    pos = j + 0;
                    i = 0;
                } else
                {
                    abyte0 = readRawBytesSlowPath(j);
                    i = 0;
                }
            }
            if (Utf8.processor.partialIsValidUtf8(0, abyte0, i, i + j) == 0)
            {
                flag = true;
            }
            if (!flag)
            {
                throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
            } else
            {
                return new String(abyte0, i, j, Internal.UTF_8);
            }
        }

        public final int readTag()
            throws IOException
        {
            if (isAtEnd())
            {
                lastTag = 0;
                return 0;
            }
            lastTag = readRawVarint32();
            if (lastTag >>> 3 == 0)
            {
                throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
            } else
            {
                return lastTag;
            }
        }

        public final int readUInt32()
            throws IOException
        {
            return readRawVarint32();
        }

        public final long readUInt64()
            throws IOException
        {
            return readRawVarint64();
        }

        public final boolean skipField(int i)
            throws IOException
        {
            boolean flag;
            boolean flag1;
            flag1 = false;
            flag = false;
            i & 7;
            JVM INSTR tableswitch 0 5: default 48
        //                       0 59
        //                       1 156
        //                       2 164
        //                       3 174
        //                       4 204
        //                       5 206;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L2:
            i = ((flag1) ? 1 : 0);
            if (bufferSize - pos >= 10)
            {
                for (i = ((flag) ? 1 : 0); i < 10; i++)
                {
                    byte abyte0[] = buffer;
                    int j = pos;
                    pos = j + 1;
                    if (abyte0[j] >= 0)
                    {
                        break MISSING_BLOCK_LABEL_162;
                    }
                }

                throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
            }
            for (; i < 10; i++)
            {
                if (readRawByte() >= 0)
                {
                    break MISSING_BLOCK_LABEL_162;
                }
            }

            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
_L3:
            skipRawBytes(8);
            return true;
_L4:
            skipRawBytes(readRawVarint32());
            return true;
_L5:
            int k;
            do
            {
                k = readTag();
            } while (k != 0 && skipField(k));
            checkLastTagWas((i >>> 3) << 3 | 4);
            return true;
_L6:
            return false;
_L7:
            skipRawBytes(4);
            return true;
        }

        public final void skipRawBytes(int i)
            throws IOException
        {
            if (i <= bufferSize - pos && i >= 0)
            {
                pos = pos + i;
                return;
            }
            if (i < 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            if (totalBytesRetired + pos + i > currentLimit)
            {
                skipRawBytes(currentLimit - totalBytesRetired - pos);
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            int j = bufferSize - pos;
            pos = bufferSize;
            refillBuffer(1);
            while (i - j > bufferSize) 
            {
                j += bufferSize;
                pos = bufferSize;
                refillBuffer(1);
            }
            pos = i - j;
        }

        public StreamDecoder(InputStream inputstream, int i)
        {
            currentLimit = 0x7fffffff;
            refillCallback = null;
            Internal.checkNotNull(inputstream, "input");
            input = inputstream;
            buffer = new byte[i];
            bufferSize = 0;
            pos = 0;
            totalBytesRetired = 0;
        }
    }


    private class ArrayDecoder extends CodedInputStream
    {

        private final byte buffer[];
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final boolean immutable;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        private final int readRawLittleEndian32()
            throws IOException
        {
            int i = pos;
            if (limit - i < 4)
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            } else
            {
                byte abyte0[] = buffer;
                pos = i + 4;
                byte byte0 = abyte0[i];
                byte byte1 = abyte0[i + 1];
                byte byte2 = abyte0[i + 2];
                return (abyte0[i + 3] & 0xff) << 24 | (byte0 & 0xff | (byte1 & 0xff) << 8 | (byte2 & 0xff) << 16);
            }
        }

        private final long readRawLittleEndian64()
            throws IOException
        {
            int i = pos;
            if (limit - i < 8)
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            } else
            {
                byte abyte0[] = buffer;
                pos = i + 8;
                long l = abyte0[i];
                long l1 = abyte0[i + 1];
                long l2 = abyte0[i + 2];
                long l3 = abyte0[i + 3];
                long l4 = abyte0[i + 4];
                long l5 = abyte0[i + 5];
                long l6 = abyte0[i + 6];
                return ((long)abyte0[i + 7] & 255L) << 56 | (l & 255L | (l1 & 255L) << 8 | (l2 & 255L) << 16 | (l3 & 255L) << 24 | (l4 & 255L) << 32 | (l5 & 255L) << 40 | (l6 & 255L) << 48);
            }
        }

        private final int readRawVarint32()
            throws IOException
        {
            int i = pos;
            if (limit == i) goto _L2; else goto _L1
_L1:
            byte abyte0[];
            int j;
            int k;
            abyte0 = buffer;
            j = i + 1;
            k = abyte0[i];
            if (k >= 0)
            {
                pos = j;
                return k;
            }
            if (limit - j < 9) goto _L2; else goto _L3
_L3:
            i = j + 1;
            k ^= abyte0[j] << 7;
            if (k >= 0) goto _L5; else goto _L4
_L4:
            j = k ^ 0xffffff80;
_L11:
            pos = i;
            return j;
_L5:
            int l;
            j = i + 1;
            k ^= abyte0[i] << 14;
            if (k >= 0)
            {
                k ^= 0x3f80;
                i = j;
                j = k;
                continue; /* Loop/switch isn't completed */
            }
            i = j + 1;
            k ^= abyte0[j] << 21;
            if (k < 0)
            {
                j = k ^ 0xffe03f80;
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            j = abyte0[i];
            k = k ^ j << 28 ^ 0xfe03f80;
            i = l;
            if (j >= 0) goto _L7; else goto _L6
_L6:
            int i1;
            i1 = l + 1;
            j = k;
            i = i1;
            if (abyte0[l] >= 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i1 + 1;
            i = l;
            if (abyte0[i1] >= 0) goto _L7; else goto _L8
_L8:
            i1 = l + 1;
            j = k;
            i = i1;
            if (abyte0[l] >= 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i1 + 1;
            i = l;
            if (abyte0[i1] >= 0) goto _L7; else goto _L9
_L9:
            i = l + 1;
            j = k;
            if (abyte0[l] >= 0)
            {
                continue; /* Loop/switch isn't completed */
            }
_L2:
            return (int)readRawVarint64SlowPath();
_L7:
            j = k;
            if (true) goto _L11; else goto _L10
_L10:
        }

        private final long readRawVarint64()
            throws IOException
        {
            int i = pos;
            if (limit == i) goto _L2; else goto _L1
_L1:
            byte abyte0[];
            int j;
            int k;
            abyte0 = buffer;
            j = i + 1;
            k = abyte0[i];
            if (k >= 0)
            {
                pos = j;
                return (long)k;
            }
            if (limit - j < 9) goto _L2; else goto _L3
_L3:
            i = j + 1;
            k ^= abyte0[j] << 7;
            if (k >= 0) goto _L5; else goto _L4
_L4:
            long l = k ^ 0xffffff80;
_L9:
            pos = i;
            return l;
_L5:
            j = i + 1;
            k ^= abyte0[i] << 14;
            if (k >= 0)
            {
                l = k ^ 0x3f80;
                i = j;
                continue; /* Loop/switch isn't completed */
            }
            i = j + 1;
            j = k ^ abyte0[j] << 21;
            if (j < 0)
            {
                l = j ^ 0xffe03f80;
                continue; /* Loop/switch isn't completed */
            }
            l = j;
            j = i + 1;
            l ^= (long)abyte0[i] << 28;
            if (l >= 0L)
            {
                l ^= 0xfe03f80L;
                i = j;
                continue; /* Loop/switch isn't completed */
            }
            i = j + 1;
            l ^= (long)abyte0[j] << 35;
            if (l < 0L)
            {
                l ^= 0xfffffff80fe03f80L;
                continue; /* Loop/switch isn't completed */
            }
            j = i + 1;
            l ^= (long)abyte0[i] << 42;
            if (l >= 0L)
            {
                l ^= 0x3f80fe03f80L;
                i = j;
                continue; /* Loop/switch isn't completed */
            }
            i = j + 1;
            l ^= (long)abyte0[j] << 49;
            if (l < 0L)
            {
                l ^= 0xfffe03f80fe03f80L;
                continue; /* Loop/switch isn't completed */
            }
            j = i + 1;
            l = l ^ (long)abyte0[i] << 56 ^ 0xfe03f80fe03f80L;
            if (l >= 0L) goto _L7; else goto _L6
_L6:
            i = j + 1;
            if ((long)abyte0[j] >= 0L)
            {
                continue; /* Loop/switch isn't completed */
            }
_L2:
            return readRawVarint64SlowPath();
_L7:
            i = j;
            if (true) goto _L9; else goto _L8
_L8:
        }

        private final void recomputeBufferSizeAfterLimit()
        {
            limit = limit + bufferSizeAfterLimit;
            int i = limit - startPos;
            if (i > currentLimit)
            {
                bufferSizeAfterLimit = i - currentLimit;
                limit = limit - bufferSizeAfterLimit;
                return;
            } else
            {
                bufferSizeAfterLimit = 0;
                return;
            }
        }

        public final void checkLastTagWas(int i)
            throws InvalidProtocolBufferException
        {
            if (lastTag != i)
            {
                throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
            } else
            {
                return;
            }
        }

        public final int getLastTag()
        {
            return lastTag;
        }

        public final int getTotalBytesRead()
        {
            return pos - startPos;
        }

        public final boolean isAtEnd()
            throws IOException
        {
            return pos == limit;
        }

        public final void popLimit(int i)
        {
            currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public final int pushLimit(int i)
            throws InvalidProtocolBufferException
        {
            if (i < 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            i = getTotalBytesRead() + i;
            int j = currentLimit;
            if (i > j)
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            } else
            {
                currentLimit = i;
                recomputeBufferSizeAfterLimit();
                return j;
            }
        }

        public final boolean readBool()
            throws IOException
        {
            return readRawVarint64() != 0L;
        }

        public final ByteString readBytes()
            throws IOException
        {
            int i = readRawVarint32();
            if (i > 0 && i <= limit - pos)
            {
                ByteString bytestring = ByteString.copyFrom(buffer, pos, i);
                pos = i + pos;
                return bytestring;
            }
            if (i == 0)
            {
                return ByteString.EMPTY;
            }
            byte abyte0[];
            if (i > 0 && i <= limit - pos)
            {
                int j = pos;
                pos = i + pos;
                abyte0 = Arrays.copyOfRange(buffer, j, pos);
            } else
            if (i <= 0)
            {
                if (i == 0)
                {
                    abyte0 = Internal.EMPTY_BYTE_ARRAY;
                } else
                {
                    throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                }
            } else
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            return ByteString.wrap(abyte0);
        }

        public final double readDouble()
            throws IOException
        {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public final int readEnum()
            throws IOException
        {
            return readRawVarint32();
        }

        public final int readFixed32()
            throws IOException
        {
            return readRawLittleEndian32();
        }

        public final long readFixed64()
            throws IOException
        {
            return readRawLittleEndian64();
        }

        public final float readFloat()
            throws IOException
        {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public final int readInt32()
            throws IOException
        {
            return readRawVarint32();
        }

        public final long readInt64()
            throws IOException
        {
            return readRawVarint64();
        }

        public final MessageLite readMessage(Parser parser, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            int i = readRawVarint32();
            if (recursionDepth >= recursionLimit)
            {
                throw new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
            } else
            {
                i = pushLimit(i);
                recursionDepth = recursionDepth + 1;
                parser = (MessageLite)parser.parsePartialFrom(this, extensionregistrylite);
                checkLastTagWas(0);
                recursionDepth = recursionDepth - 1;
                popLimit(i);
                return parser;
            }
        }

        final long readRawVarint64SlowPath()
            throws IOException
        {
            long l = 0L;
            for (int i = 0; i < 64; i += 7)
            {
                if (pos == limit)
                {
                    throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
                byte abyte0[] = buffer;
                int j = pos;
                pos = j + 1;
                j = abyte0[j];
                l |= (long)(j & 0x7f) << i;
                if ((j & 0x80) == 0)
                {
                    return l;
                }
            }

            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
        }

        public final int readSFixed32()
            throws IOException
        {
            return readRawLittleEndian32();
        }

        public final long readSFixed64()
            throws IOException
        {
            return readRawLittleEndian64();
        }

        public final int readSInt32()
            throws IOException
        {
            int i = readRawVarint32();
            return -(i & 1) ^ i >>> 1;
        }

        public final long readSInt64()
            throws IOException
        {
            long l = readRawVarint64();
            return -(l & 1L) ^ l >>> 1;
        }

        public final String readString()
            throws IOException
        {
            int i = readRawVarint32();
            if (i > 0 && i <= limit - pos)
            {
                String s = new String(buffer, pos, i, Internal.UTF_8);
                pos = i + pos;
                return s;
            }
            if (i == 0)
            {
                return "";
            }
            if (i < 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            } else
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
        }

        public final String readStringRequireUtf8()
            throws IOException
        {
            boolean flag = false;
            int j = readRawVarint32();
            if (j > 0 && j <= limit - pos)
            {
                byte abyte0[] = buffer;
                int k = pos;
                int l = pos;
                if (Utf8.processor.partialIsValidUtf8(0, abyte0, k, l + j) == 0)
                {
                    flag = true;
                }
                if (!flag)
                {
                    throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
                } else
                {
                    int i = pos;
                    pos = pos + j;
                    return new String(buffer, i, j, Internal.UTF_8);
                }
            }
            if (j == 0)
            {
                return "";
            }
            if (j <= 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            } else
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
        }

        public final int readTag()
            throws IOException
        {
            if (isAtEnd())
            {
                lastTag = 0;
                return 0;
            }
            lastTag = readRawVarint32();
            if (lastTag >>> 3 == 0)
            {
                throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
            } else
            {
                return lastTag;
            }
        }

        public final int readUInt32()
            throws IOException
        {
            return readRawVarint32();
        }

        public final long readUInt64()
            throws IOException
        {
            return readRawVarint64();
        }

        public final boolean skipField(int i)
            throws IOException
        {
            int j;
            boolean flag;
            flag = false;
            j = 0;
            i & 7;
            JVM INSTR tableswitch 0 5: default 48
        //                       0 58
        //                       1 187
        //                       2 195
        //                       3 205
        //                       4 235
        //                       5 237;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L2:
            i = ((flag) ? 1 : 0);
            if (limit - pos < 10) goto _L9; else goto _L8
_L8:
            i = j;
_L14:
            if (i >= 10) goto _L11; else goto _L10
_L10:
            byte abyte0[];
            abyte0 = buffer;
            j = pos;
            pos = j + 1;
            if (abyte0[j] >= 0) goto _L13; else goto _L12
_L12:
            i++;
              goto _L14
_L11:
            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
_L15:
            byte abyte1[] = buffer;
            int k = pos;
            pos = k + 1;
            if (abyte1[k] >= 0)
            {
                break; /* Loop/switch isn't completed */
            }
            i++;
_L9:
            if (i < 10)
            {
                if (pos == limit)
                {
                    throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
            } else
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
            }
            if (true) goto _L15; else goto _L13
_L3:
            skipRawBytes(8);
_L13:
            return true;
_L4:
            skipRawBytes(readRawVarint32());
            return true;
_L5:
            int l;
            do
            {
                l = readTag();
            } while (l != 0 && skipField(l));
            checkLastTagWas((i >>> 3) << 3 | 4);
            return true;
_L6:
            return false;
_L7:
            skipRawBytes(4);
            return true;
        }

        public final void skipRawBytes(int i)
            throws IOException
        {
            if (i >= 0 && i <= limit - pos)
            {
                pos = pos + i;
                return;
            }
            if (i < 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            } else
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
        }

        ArrayDecoder(byte abyte0[], int i, int j, boolean flag)
        {
            currentLimit = 0x7fffffff;
            buffer = abyte0;
            limit = i + j;
            pos = i;
            startPos = pos;
            immutable = flag;
        }
    }

}
