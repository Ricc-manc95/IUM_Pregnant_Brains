// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.nano;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

// Referenced classes of package com.google.protobuf.nano:
//            InvalidProtocolBufferNanoException, MessageNano, InternalNano

public final class CodedInputByteBufferNano
{

    public final byte buffer[];
    public int bufferPos;
    private final int bufferSize;
    private int bufferSizeAfterLimit;
    public final int bufferStart;
    private CodedInputStream codedInputStream;
    public int currentLimit;
    public int lastTag;
    public int maybeLimitedBufferSize;
    private int recursionDepth;
    private int recursionLimit;

    CodedInputByteBufferNano(byte abyte0[], int i, int j)
    {
        currentLimit = 0x7fffffff;
        recursionLimit = 64;
        buffer = abyte0;
        bufferStart = i;
        j = i + j;
        maybeLimitedBufferSize = j;
        bufferSize = j;
        bufferPos = i;
    }

    private final void skipRawBytes(int i)
        throws IOException
    {
        if (i < 0)
        {
            throw new InvalidProtocolBufferNanoException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (bufferPos + i > currentLimit)
        {
            skipRawBytes(currentLimit - bufferPos);
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        if (i <= maybeLimitedBufferSize - bufferPos)
        {
            bufferPos = bufferPos + i;
            return;
        } else
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public final int pushLimit(int i)
        throws InvalidProtocolBufferNanoException
    {
        if (i < 0)
        {
            throw new InvalidProtocolBufferNanoException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        i = bufferPos + i;
        int j = currentLimit;
        if (i > j)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            currentLimit = i;
            recomputeBufferSizeAfterLimit();
            return j;
        }
    }

    public final long readInt64()
        throws IOException
    {
        int i = 0;
        long l = 0L;
        for (; i < 64; i += 7)
        {
            if (bufferPos == maybeLimitedBufferSize)
            {
                throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
            byte abyte0[] = buffer;
            int j = bufferPos;
            bufferPos = j + 1;
            j = abyte0[j];
            l |= (long)(j & 0x7f) << i;
            if ((j & 0x80) == 0)
            {
                return l;
            }
        }

        throw new InvalidProtocolBufferNanoException("CodedInputStream encountered a malformed varint.");
    }

    public final void readMessage(MessageNano messagenano)
        throws IOException
    {
        int i = readRawVarint32();
        if (recursionDepth >= recursionLimit)
        {
            throw new InvalidProtocolBufferNanoException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        i = pushLimit(i);
        recursionDepth = recursionDepth + 1;
        messagenano.mergeFrom(this);
        if (lastTag != 0)
        {
            throw new InvalidProtocolBufferNanoException("Protocol message end-group tag did not match expected tag.");
        } else
        {
            recursionDepth = recursionDepth - 1;
            currentLimit = i;
            recomputeBufferSizeAfterLimit();
            return;
        }
    }

    public final GeneratedMessageLite readMessageLite(Parser parser)
        throws IOException
    {
        int i;
        int j;
        try
        {
            if (codedInputStream == null)
            {
                codedInputStream = CodedInputStream.newInstance(buffer, bufferStart, bufferSize, false);
            }
            i = codedInputStream.getTotalBytesRead();
            j = bufferPos - bufferStart;
        }
        // Misplaced declaration of an exception variable
        catch (Parser parser)
        {
            throw new InvalidProtocolBufferNanoException("", parser);
        }
        if (i <= j)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        }));
        CodedInputStream codedinputstream;
        codedInputStream.skipRawBytes(j - i);
        codedinputstream = codedInputStream;
        i = recursionLimit - recursionDepth;
        if (i >= 0)
        {
            break MISSING_BLOCK_LABEL_155;
        }
        throw new IllegalArgumentException((new StringBuilder(47)).append("Recursion limit cannot be negative: ").append(i).toString());
        int k = codedinputstream.recursionLimit;
        codedinputstream.recursionLimit = i;
        parser = (GeneratedMessageLite)codedInputStream.readMessage(parser, ExtensionRegistryLite.getGeneratedRegistry());
        skipField(lastTag);
        return parser;
    }

    public final int readRawLittleEndian32()
        throws IOException
    {
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        byte abyte0[] = buffer;
        int i = bufferPos;
        bufferPos = i + 1;
        i = abyte0[i];
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte0 = buffer;
        int j = bufferPos;
        bufferPos = j + 1;
        j = abyte0[j];
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte0 = buffer;
        int k = bufferPos;
        bufferPos = k + 1;
        k = abyte0[k];
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            byte abyte1[] = buffer;
            int l = bufferPos;
            bufferPos = l + 1;
            return i & 0xff | (j & 0xff) << 8 | (k & 0xff) << 16 | (abyte1[l] & 0xff) << 24;
        }
    }

    public final long readRawLittleEndian64()
        throws IOException
    {
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        byte abyte0[] = buffer;
        int i = bufferPos;
        bufferPos = i + 1;
        i = abyte0[i];
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte0 = buffer;
        int j = bufferPos;
        bufferPos = j + 1;
        j = abyte0[j];
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte0 = buffer;
        int k = bufferPos;
        bufferPos = k + 1;
        k = abyte0[k];
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte0 = buffer;
        int l = bufferPos;
        bufferPos = l + 1;
        l = abyte0[l];
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte0 = buffer;
        int i1 = bufferPos;
        bufferPos = i1 + 1;
        i1 = abyte0[i1];
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte0 = buffer;
        int j1 = bufferPos;
        bufferPos = j1 + 1;
        j1 = abyte0[j1];
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte0 = buffer;
        int k1 = bufferPos;
        bufferPos = k1 + 1;
        k1 = abyte0[k1];
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            byte abyte1[] = buffer;
            int l1 = bufferPos;
            bufferPos = l1 + 1;
            l1 = abyte1[l1];
            long l2 = i;
            return ((long)j & 255L) << 8 | l2 & 255L | ((long)k & 255L) << 16 | ((long)l & 255L) << 24 | ((long)i1 & 255L) << 32 | ((long)j1 & 255L) << 40 | ((long)k1 & 255L) << 48 | ((long)l1 & 255L) << 56;
        }
    }

    public final int readRawVarint32()
        throws IOException
    {
        int i;
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        byte abyte0[] = buffer;
        i = bufferPos;
        bufferPos = i + 1;
        i = abyte0[i];
        if (i < 0) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        i &= 0x7f;
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        byte abyte1[] = buffer;
        int j = bufferPos;
        bufferPos = j + 1;
        j = abyte1[j];
        if (j >= 0)
        {
            return i | j << 7;
        }
        i |= (j & 0x7f) << 7;
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte1 = buffer;
        j = bufferPos;
        bufferPos = j + 1;
        j = abyte1[j];
        if (j >= 0)
        {
            return i | j << 14;
        }
        i |= (j & 0x7f) << 14;
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte1 = buffer;
        j = bufferPos;
        bufferPos = j + 1;
        int l = abyte1[j];
        if (l >= 0)
        {
            return i | l << 21;
        }
        if (bufferPos == maybeLimitedBufferSize)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte1 = buffer;
        j = bufferPos;
        bufferPos = j + 1;
        j = abyte1[j];
        l = i | (l & 0x7f) << 21 | j << 28;
        i = l;
        if (j < 0)
        {
            int k = 0;
label0:
            do
            {
label1:
                {
                    if (k >= 5)
                    {
                        break label1;
                    }
                    if (bufferPos == maybeLimitedBufferSize)
                    {
                        throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
                    }
                    byte abyte2[] = buffer;
                    int i1 = bufferPos;
                    bufferPos = i1 + 1;
                    i = l;
                    if (abyte2[i1] >= 0)
                    {
                        break label0;
                    }
                    k++;
                }
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        throw new InvalidProtocolBufferNanoException("CodedInputStream encountered a malformed varint.");
    }

    public final String readString()
        throws IOException
    {
        int i = readRawVarint32();
        if (i < 0)
        {
            throw new InvalidProtocolBufferNanoException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (i > maybeLimitedBufferSize - bufferPos)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            String s = new String(buffer, bufferPos, i, InternalNano.UTF_8);
            bufferPos = i + bufferPos;
            return s;
        }
    }

    public final int readTag()
        throws IOException
    {
        if (bufferPos == maybeLimitedBufferSize)
        {
            lastTag = 0;
            return 0;
        }
        lastTag = readRawVarint32();
        if (lastTag == 0)
        {
            throw new InvalidProtocolBufferNanoException("Protocol message contained an invalid tag (zero).");
        } else
        {
            return lastTag;
        }
    }

    public final void recomputeBufferSizeAfterLimit()
    {
        maybeLimitedBufferSize = maybeLimitedBufferSize + bufferSizeAfterLimit;
        int i = maybeLimitedBufferSize;
        if (i > currentLimit)
        {
            bufferSizeAfterLimit = i - currentLimit;
            maybeLimitedBufferSize = maybeLimitedBufferSize - bufferSizeAfterLimit;
            return;
        } else
        {
            bufferSizeAfterLimit = 0;
            return;
        }
    }

    public final void rewindToPositionAndTag(int i, int j)
    {
        if (i > bufferPos - bufferStart)
        {
            j = bufferPos;
            int k = bufferStart;
            throw new IllegalArgumentException((new StringBuilder(50)).append("Position ").append(i).append(" is beyond current ").append(j - k).toString());
        }
        if (i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder(24)).append("Bad position ").append(i).toString());
        } else
        {
            bufferPos = bufferStart + i;
            lastTag = j;
            return;
        }
    }

    public final boolean skipField(int i)
        throws IOException
    {
        i & 7;
        JVM INSTR tableswitch 0 5: default 44
    //                   0 54
    //                   1 61
    //                   2 68
    //                   3 78
    //                   4 119
    //                   5 121;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        throw new InvalidProtocolBufferNanoException("Protocol message tag had invalid wire type.");
_L2:
        readRawVarint32();
_L9:
        return true;
_L3:
        readRawLittleEndian64();
        return true;
_L4:
        skipRawBytes(readRawVarint32());
        return true;
_L5:
        int j;
        do
        {
            j = readTag();
        } while (j != 0 && skipField(j));
        if (lastTag == ((i >>> 3) << 3 | 4)) goto _L9; else goto _L8
_L8:
        throw new InvalidProtocolBufferNanoException("Protocol message end-group tag did not match expected tag.");
_L6:
        return false;
_L7:
        readRawLittleEndian32();
        return true;
    }
}
