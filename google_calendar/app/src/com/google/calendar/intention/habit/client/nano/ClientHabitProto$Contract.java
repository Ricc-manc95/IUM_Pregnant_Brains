// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.intention.habit.client.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class cachedSize extends ExtendableMessageNano
{

    public int durationMinutes;
    public int interval;
    public int numInstancesPerInterval;
    public rn timePattern;
    public long untilMillisUtc;

    private final cachedSize mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L9:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 6: default 64
    //                   0: 73
    //                   8: 75
    //                   16: 86
    //                   24: 178
    //                   34: 189
    //                   40: 218;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        durationMinutes = codedinputbytebuffernano.readRawVarint32();
        continue; /* Loop/switch isn't completed */
_L4:
        int k;
        int i1;
        k = codedinputbytebuffernano.bufferPos;
        i1 = codedinputbytebuffernano.bufferStart;
        int j1 = codedinputbytebuffernano.readRawVarint32();
        if (j1 >= 0 && j1 <= 3)
        {
            try
            {
                interval = j1;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(k - i1, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(40)).append(j1).append(" is not a valid enum Interval").toString());
_L5:
        numInstancesPerInterval = codedinputbytebuffernano.readRawVarint32();
        continue; /* Loop/switch isn't completed */
_L6:
        if (timePattern == null)
        {
            timePattern = new rn();
        }
        codedinputbytebuffernano.readMessage(timePattern);
        continue; /* Loop/switch isn't completed */
_L7:
        int j = 0;
        long l1 = 0L;
        do
        {
            if (j >= 64)
            {
                break;
            }
            if (codedinputbytebuffernano.bufferPos == codedinputbytebuffernano.maybeLimitedBufferSize)
            {
                throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
            byte abyte0[] = codedinputbytebuffernano.buffer;
            int l = codedinputbytebuffernano.bufferPos;
            codedinputbytebuffernano.bufferPos = l + 1;
            l = abyte0[l];
            l1 |= (long)(l & 0x7f) << j;
            if ((l & 0x80) == 0)
            {
                untilMillisUtc = l1;
                continue; /* Loop/switch isn't completed */
            }
            j += 7;
        } while (true);
        throw new InvalidProtocolBufferNanoException("CodedInputStream encountered a malformed varint.");
        if (true) goto _L9; else goto _L8
_L8:
    }

    protected final int computeSerializedSize()
    {
        int i;
        byte byte0;
        long l;
        byte0 = 10;
        int j = super.computeSerializedSize();
        if (durationMinutes != 0)
        {
            i = durationMinutes;
            int k = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            rn rn;
            if (i >= 0)
            {
                i = CodedOutputByteBufferNano.computeRawVarint32Size(i);
            } else
            {
                i = 10;
            }
            j = i + k + j;
        }
        i = j;
        if (interval != 0)
        {
            i = interval;
            k = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            if (i >= 0)
            {
                i = CodedOutputByteBufferNano.computeRawVarint32Size(i);
            } else
            {
                i = 10;
            }
            i = j + (i + k);
        }
        k = i;
        if (numInstancesPerInterval != 0)
        {
            j = numInstancesPerInterval;
            k = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            if (j >= 0)
            {
                j = CodedOutputByteBufferNano.computeRawVarint32Size(j);
            } else
            {
                j = 10;
            }
            k = i + (j + k);
        }
        j = k;
        if (timePattern != null)
        {
            rn = timePattern;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(32);
            j = rn.computeSerializedSize();
            rn.cachedSize = j;
            j = k + (CodedOutputByteBufferNano.computeRawVarint32Size(j) + j + i);
        }
        i = j;
        if (untilMillisUtc == 0L) goto _L2; else goto _L1
_L1:
        l = untilMillisUtc;
        k = CodedOutputByteBufferNano.computeRawVarint32Size(40);
        if ((-128L & l) != 0L) goto _L4; else goto _L3
_L3:
        i = 1;
_L6:
        i = j + (i + k);
_L2:
        return i;
_L4:
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
        {
            i = byte0;
            if ((l & 0x8000000000000000L) == 0L)
            {
                i = 9;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        return mergeFrom(codedinputbytebuffernano);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        int i;
        if (durationMinutes != 0)
        {
            i = durationMinutes;
            codedoutputbytebuffernano.writeRawVarint32(8);
            rn rn;
            long l;
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (interval != 0)
        {
            i = interval;
            codedoutputbytebuffernano.writeRawVarint32(16);
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (numInstancesPerInterval != 0)
        {
            i = numInstancesPerInterval;
            codedoutputbytebuffernano.writeRawVarint32(24);
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (timePattern != null)
        {
            rn = timePattern;
            codedoutputbytebuffernano.writeRawVarint32(34);
            if (((MessageNano) (rn)).cachedSize < 0)
            {
                rn.cachedSize = rn.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (rn)).cachedSize);
            rn.writeTo(codedoutputbytebuffernano);
        }
        if (untilMillisUtc != 0L)
        {
            l = untilMillisUtc;
            codedoutputbytebuffernano.writeRawVarint32(40);
            codedoutputbytebuffernano.writeRawVarint64(l);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public rn()
    {
        durationMinutes = 0;
        interval = 0;
        numInstancesPerInterval = 0;
        timePattern = null;
        untilMillisUtc = 0L;
        unknownFieldData = null;
        cachedSize = -1;
    }
}
