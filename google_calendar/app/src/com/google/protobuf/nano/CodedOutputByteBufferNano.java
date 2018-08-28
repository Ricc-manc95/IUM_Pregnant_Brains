// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.nano;

import com.google.protobuf.CodedOutputStream;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

// Referenced classes of package com.google.protobuf.nano:
//            MessageNano

public final class CodedOutputByteBufferNano
{

    public final ByteBuffer buffer;
    private CodedOutputStream codedOutputStream;
    public int codedOutputStreamPosition;

    private CodedOutputByteBufferNano(ByteBuffer bytebuffer)
    {
        buffer = bytebuffer;
        buffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public CodedOutputByteBufferNano(byte abyte0[], int i, int j)
    {
        this(ByteBuffer.wrap(abyte0, i, j));
    }

    public static int computeInt32Size(int i, int j)
    {
        int k = computeRawVarint32Size(0 | i << 3);
        if (j >= 0)
        {
            i = computeRawVarint32Size(j);
        } else
        {
            i = 10;
        }
        return i + k;
    }

    public static int computeInt64Size(int i, long l)
    {
        int j = computeRawVarint32Size(0 | i << 3);
        if ((-128L & l) == 0L)
        {
            i = 1;
        } else
        if ((-16384L & l) == 0L)
        {
            i = 2;
        } else
        if ((0xffffffffffe00000L & l) == 0L)
        {
            i = 3;
        } else
        if ((0xfffffffff0000000L & l) == 0L)
        {
            i = 4;
        } else
        if ((0xfffffff800000000L & l) == 0L)
        {
            i = 5;
        } else
        if ((0xfffffc0000000000L & l) == 0L)
        {
            i = 6;
        } else
        if ((0xfffe000000000000L & l) == 0L)
        {
            i = 7;
        } else
        if ((0xff00000000000000L & l) == 0L)
        {
            i = 8;
        } else
        if ((0x8000000000000000L & l) == 0L)
        {
            i = 9;
        } else
        {
            i = 10;
        }
        return i + j;
    }

    public static int computeMessageSize(int i, MessageNano messagenano)
    {
        i = computeRawVarint32Size(0 | i << 3);
        int j = messagenano.computeSerializedSize();
        messagenano.cachedSize = j;
        return i + (j + computeRawVarint32Size(j));
    }

    public static int computeRawVarint32Size(int i)
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

    private static void encode(CharSequence charsequence, ByteBuffer bytebuffer)
    {
        int i;
        int j;
        j = 0;
        i = 0;
        if (bytebuffer.isReadOnly())
        {
            throw new ReadOnlyBufferException();
        }
        if (!bytebuffer.hasArray()) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        int k;
        int k1;
        abyte0 = bytebuffer.array();
        j = bytebuffer.arrayOffset() + bytebuffer.position();
        k = bytebuffer.remaining();
        k1 = charsequence.length();
        int l1 = j + k;
_L4:
        if (i >= k1 || i + j >= l1)
        {
            break; /* Loop/switch isn't completed */
        }
        k = charsequence.charAt(i);
        if (k >= '\200')
        {
            break; /* Loop/switch isn't completed */
        }
        abyte0[j + i] = (byte)k;
        i++;
        if (true) goto _L4; else goto _L3
_L6:
        if (i >= k1)
        {
            break MISSING_BLOCK_LABEL_604;
        }
        char c;
        c = charsequence.charAt(i);
        break MISSING_BLOCK_LABEL_150;
_L5:
        int i2;
        try
        {
            bytebuffer.position(i - bytebuffer.arrayOffset());
            return;
        }
        // Misplaced declaration of an exception variable
        catch (CharSequence charsequence)
        {
            bytebuffer = new BufferOverflowException();
        }
        bytebuffer.initCause(charsequence);
        throw bytebuffer;
        if (c < '\200' && j < l1)
        {
            k = j + 1;
            abyte0[j] = (byte)c;
            j = i;
            i = k;
            break MISSING_BLOCK_LABEL_943;
        }
        if (c < '\u0800' && j <= l1 - 2)
        {
            i2 = j + 1;
            abyte0[j] = (byte)(c >>> 6 | 0x3c0);
            k = i2 + 1;
            abyte0[i2] = (byte)(c & 0x3f | 0x80);
            j = i;
            i = k;
            break MISSING_BLOCK_LABEL_943;
        }
        if ((c < '\uD800' || '\uDFFF' < c) && j <= l1 - 3)
        {
            k = j + 1;
            byte byte0 = (byte)(c >>> 12 | 0x1e0);
            abyte0[j] = byte0;
            j = k + 1;
            abyte0[k] = (byte)(c >>> 6 & 0x3f | 0x80);
            k = j + 1;
            abyte0[j] = (byte)(c & 0x3f | 0x80);
            j = i;
            i = k;
            break MISSING_BLOCK_LABEL_943;
        }
        if (j > l1 - 4)
        {
            break MISSING_BLOCK_LABEL_565;
        }
        k = i;
        if (i + 1 == charsequence.length())
        {
            break MISSING_BLOCK_LABEL_424;
        }
        i++;
        char c2;
        c2 = charsequence.charAt(i);
        if (Character.isSurrogatePair(c, c2))
        {
            break MISSING_BLOCK_LABEL_456;
        }
        k = i;
        throw new IllegalArgumentException((new StringBuilder(39)).append("Unpaired surrogate at index ").append(k - 1).toString());
        int j2 = Character.toCodePoint(c, c2);
        int l = j + 1;
        abyte0[j] = (byte)(j2 >>> 18 | 0xf0);
        j = l + 1;
        abyte0[l] = (byte)(j2 >>> 12 & 0x3f | 0x80);
        int k2 = j + 1;
        abyte0[j] = (byte)(j2 >>> 6 & 0x3f | 0x80);
        l = k2 + 1;
        abyte0[k2] = (byte)(j2 & 0x3f | 0x80);
        j = i;
        i = l;
        break MISSING_BLOCK_LABEL_943;
        throw new ArrayIndexOutOfBoundsException((new StringBuilder(37)).append("Failed writing ").append(c).append(" at index ").append(j).toString());
        i = j;
          goto _L5
_L3:
        if (i != k1)
        {
            break MISSING_BLOCK_LABEL_933;
        }
        i = j + k1;
          goto _L5
_L2:
        int i1 = charsequence.length();
        i = j;
        while (i < i1) 
        {
            char c1 = charsequence.charAt(i);
            if (c1 < '\200')
            {
                bytebuffer.put((byte)c1);
            } else
            if (c1 < '\u0800')
            {
                bytebuffer.put((byte)(c1 >>> 6 | 0x3c0));
                bytebuffer.put((byte)(c1 & 0x3f | 0x80));
            } else
            if (c1 < '\uD800' || '\uDFFF' < c1)
            {
                bytebuffer.put((byte)(c1 >>> 12 | 0x1e0));
                bytebuffer.put((byte)(c1 >>> 6 & 0x3f | 0x80));
                bytebuffer.put((byte)(c1 & 0x3f | 0x80));
            } else
            {
                char c3;
label0:
                {
                    j = i;
                    if (i + 1 != charsequence.length())
                    {
                        i++;
                        c3 = charsequence.charAt(i);
                        if (Character.isSurrogatePair(c1, c3))
                        {
                            break label0;
                        }
                        j = i;
                    }
                    throw new IllegalArgumentException((new StringBuilder(39)).append("Unpaired surrogate at index ").append(j - 1).toString());
                }
                j = Character.toCodePoint(c1, c3);
                bytebuffer.put((byte)(j >>> 18 | 0xf0));
                bytebuffer.put((byte)(j >>> 12 & 0x3f | 0x80));
                bytebuffer.put((byte)(j >>> 6 & 0x3f | 0x80));
                bytebuffer.put((byte)(j & 0x3f | 0x80));
            }
            i++;
        }
        return;
        j += i;
          goto _L6
        int j1 = j + 1;
        j = i;
        i = j1;
          goto _L6
    }

    public static int encodedLength(CharSequence charsequence)
    {
_L2:
        int i;
        if (j < i1)
        {
            char c = charsequence.charAt(j);
            if (c < '\u0800')
            {
                i += 127 - c >>> 31;
                j++;
                continue; /* Loop/switch isn't completed */
            }
            int k1 = charsequence.length();
            while (j < k1) 
            {
                char c1 = charsequence.charAt(j);
                int l;
                if (c1 < '\u0800')
                {
                    k += 127 - c1 >>> 31;
                    l = j;
                } else
                {
                    int j1 = k + 2;
                    l = j;
                    k = j1;
                    if ('\uD800' <= c1)
                    {
                        l = j;
                        k = j1;
                        if (c1 <= '\uDFFF')
                        {
                            if (Character.codePointAt(charsequence, j) < 0x10000)
                            {
                                throw new IllegalArgumentException((new StringBuilder(39)).append("Unpaired surrogate at index ").append(j).toString());
                            }
                            l = j + 1;
                            k = j1;
                        }
                    }
                }
                j = l + 1;
            }
            i += k;
        }
        if (i < i1)
        {
            long l1 = i;
            throw new IllegalArgumentException((new StringBuilder(54)).append("UTF-8 length does not fit in int: ").append(l1 + 0x100000000L).toString());
        } else
        {
            return i;
        }
        int k = 0;
        int i1 = charsequence.length();
        int j;
        for (j = 0; j < i1 && charsequence.charAt(j) < '\200'; j++) { }
        i = i1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final void writeRawByte(int i)
        throws IOException
    {
        byte byte0 = (byte)i;
        if (!buffer.hasRemaining())
        {
            throw new OutOfSpaceException(buffer.position(), buffer.limit());
        } else
        {
            buffer.put(byte0);
            return;
        }
    }

    public final CodedOutputStream getCodedOutputStream()
        throws IOException
    {
        if (codedOutputStream != null) goto _L2; else goto _L1
_L1:
        codedOutputStream = CodedOutputStream.newInstance(buffer);
        codedOutputStreamPosition = buffer.position();
_L4:
        return codedOutputStream;
_L2:
        if (codedOutputStreamPosition != buffer.position())
        {
            codedOutputStream.write(buffer.array(), codedOutputStreamPosition, buffer.position() - codedOutputStreamPosition);
            codedOutputStreamPosition = buffer.position();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void writeBool(int i, boolean flag)
        throws IOException
    {
        boolean flag1 = false;
        writeRawVarint32(i << 3 | 0);
        i = ((flag1) ? 1 : 0);
        if (flag)
        {
            i = 1;
        }
        byte byte0 = (byte)i;
        if (!buffer.hasRemaining())
        {
            throw new OutOfSpaceException(buffer.position(), buffer.limit());
        } else
        {
            buffer.put(byte0);
            return;
        }
    }

    public final void writeFixed64(int i, long l)
        throws IOException
    {
        writeRawVarint32(1 | i << 3);
        if (buffer.remaining() < 8)
        {
            throw new OutOfSpaceException(buffer.position(), buffer.limit());
        } else
        {
            buffer.putLong(l);
            return;
        }
    }

    public final void writeInt32(int i, int j)
        throws IOException
    {
        writeRawVarint32(0 | i << 3);
        if (j >= 0)
        {
            writeRawVarint32(j);
            return;
        } else
        {
            writeRawVarint64(j);
            return;
        }
    }

    public final void writeInt64(int i, long l)
        throws IOException
    {
        writeRawVarint32(0 | i << 3);
        writeRawVarint64(l);
    }

    public final void writeMessage(int i, MessageNano messagenano)
        throws IOException
    {
        writeRawVarint32(2 | i << 3);
        if (messagenano.cachedSize < 0)
        {
            messagenano.cachedSize = messagenano.computeSerializedSize();
        }
        writeRawVarint32(messagenano.cachedSize);
        messagenano.writeTo(this);
    }

    public final void writeRawVarint32(int i)
        throws IOException
    {
        do
        {
            if ((i & 0xffffff80) == 0)
            {
                writeRawByte(i);
                return;
            }
            writeRawByte(i & 0x7f | 0x80);
            i >>>= 7;
        } while (true);
    }

    public final void writeRawVarint64(long l)
        throws IOException
    {
        do
        {
            if ((-128L & l) == 0L)
            {
                writeRawByte((int)l);
                return;
            }
            writeRawByte((int)l & 0x7f | 0x80);
            l >>>= 7;
        } while (true);
    }

    public final void writeString(int i, String s)
        throws IOException
    {
        writeRawVarint32(2 | i << 3);
        OutOfSpaceException outofspaceexception;
        int j;
        i = computeRawVarint32Size(s.length());
        if (i != computeRawVarint32Size(s.length() * 3))
        {
            break MISSING_BLOCK_LABEL_161;
        }
        j = buffer.position();
        if (buffer.remaining() < i)
        {
            throw new OutOfSpaceException(i + j, buffer.limit());
        }
        try
        {
            buffer.position(j + i);
            encode(s, buffer);
            int k = buffer.position();
            buffer.position(j);
            writeRawVarint32(k - j - i);
            buffer.position(k);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            outofspaceexception = new OutOfSpaceException(buffer.position(), buffer.limit());
            outofspaceexception.initCause(s);
            throw outofspaceexception;
        }
        writeRawVarint32(encodedLength(s));
        encode(s, buffer);
        return;
    }

    public final void writeStringNoTag(String s)
        throws IOException
    {
        OutOfSpaceException outofspaceexception;
        int i;
        int j;
        i = computeRawVarint32Size(s.length());
        if (i != computeRawVarint32Size(s.length() * 3))
        {
            break MISSING_BLOCK_LABEL_152;
        }
        j = buffer.position();
        if (buffer.remaining() < i)
        {
            throw new OutOfSpaceException(i + j, buffer.limit());
        }
        try
        {
            buffer.position(j + i);
            encode(s, buffer);
            int k = buffer.position();
            buffer.position(j);
            writeRawVarint32(k - j - i);
            buffer.position(k);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            outofspaceexception = new OutOfSpaceException(buffer.position(), buffer.limit());
            outofspaceexception.initCause(s);
            throw outofspaceexception;
        }
        writeRawVarint32(encodedLength(s));
        encode(s, buffer);
        return;
    }

    public final void writeUInt32(int i, int j)
        throws IOException
    {
        writeRawVarint32(0 | i << 3);
        writeRawVarint32(j);
    }

    private class OutOfSpaceException extends IOException
    {

        public static final long serialVersionUID = 0x9f95917c52ce6e25L;

        public OutOfSpaceException(int i, int j)
        {
            super((new StringBuilder(108)).append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ").append(i).append(" limit ").append(j).append(").").toString());
        }
    }

}
