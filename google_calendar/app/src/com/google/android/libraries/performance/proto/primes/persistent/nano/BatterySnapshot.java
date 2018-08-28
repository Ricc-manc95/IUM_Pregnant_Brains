// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.proto.primes.persistent.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;
import java.nio.ByteBuffer;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.UidHealthProto;

public final class BatterySnapshot extends ExtendableMessageNano
{

    public Long currentTime;
    public String customEventName;
    public Long elapsedTime;
    public Boolean isEventNameConstant;
    public MetricExtension metricExtension;
    public Long primesVersion;
    public Integer sampleInfo;
    public UidHealthProto uidHealthProto;
    public Long versionNameHash;

    public BatterySnapshot()
    {
        uidHealthProto = null;
        elapsedTime = null;
        currentTime = null;
        primesVersion = null;
        versionNameHash = null;
        sampleInfo = null;
        customEventName = null;
        isEventNameConstant = null;
        metricExtension = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int i1 = 1;
        byte byte0 = 10;
        int i = super.computeSerializedSize();
        int j = i;
        if (uidHealthProto != null)
        {
            UidHealthProto uidhealthproto = uidHealthProto;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = uidhealthproto.computeSerializedSize();
            uidhealthproto.cachedSize = k;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(k) + k + j);
        }
        int l = j;
        long l1;
        if (elapsedTime != null)
        {
            l1 = elapsedTime.longValue();
            l = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            Object obj;
            if ((-128L & l1) == 0L)
            {
                i = 1;
            } else
            if ((-16384L & l1) == 0L)
            {
                i = 2;
            } else
            if ((0xffffffffffe00000L & l1) == 0L)
            {
                i = 3;
            } else
            if ((0xfffffffff0000000L & l1) == 0L)
            {
                i = 4;
            } else
            if ((0xfffffff800000000L & l1) == 0L)
            {
                i = 5;
            } else
            if ((0xfffffc0000000000L & l1) == 0L)
            {
                i = 6;
            } else
            if ((0xfffe000000000000L & l1) == 0L)
            {
                i = 7;
            } else
            if ((0xff00000000000000L & l1) == 0L)
            {
                i = 8;
            } else
            if ((l1 & 0x8000000000000000L) == 0L)
            {
                i = 9;
            } else
            {
                i = 10;
            }
            l = j + (i + l);
        }
        j = l;
        if (currentTime != null)
        {
            l1 = currentTime.longValue();
            j = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            if ((-128L & l1) == 0L)
            {
                i = 1;
            } else
            if ((-16384L & l1) == 0L)
            {
                i = 2;
            } else
            if ((0xffffffffffe00000L & l1) == 0L)
            {
                i = 3;
            } else
            if ((0xfffffffff0000000L & l1) == 0L)
            {
                i = 4;
            } else
            if ((0xfffffff800000000L & l1) == 0L)
            {
                i = 5;
            } else
            if ((0xfffffc0000000000L & l1) == 0L)
            {
                i = 6;
            } else
            if ((0xfffe000000000000L & l1) == 0L)
            {
                i = 7;
            } else
            if ((0xff00000000000000L & l1) == 0L)
            {
                i = 8;
            } else
            if ((l1 & 0x8000000000000000L) == 0L)
            {
                i = 9;
            } else
            {
                i = 10;
            }
            j = l + (i + j);
        }
        l = j;
        if (primesVersion != null)
        {
            l1 = primesVersion.longValue();
            l = CodedOutputByteBufferNano.computeRawVarint32Size(32);
            if ((-128L & l1) == 0L)
            {
                i = i1;
            } else
            if ((-16384L & l1) == 0L)
            {
                i = 2;
            } else
            if ((0xffffffffffe00000L & l1) == 0L)
            {
                i = 3;
            } else
            if ((0xfffffffff0000000L & l1) == 0L)
            {
                i = 4;
            } else
            if ((0xfffffff800000000L & l1) == 0L)
            {
                i = 5;
            } else
            if ((0xfffffc0000000000L & l1) == 0L)
            {
                i = 6;
            } else
            if ((0xfffe000000000000L & l1) == 0L)
            {
                i = 7;
            } else
            if ((0xff00000000000000L & l1) == 0L)
            {
                i = 8;
            } else
            if ((0x8000000000000000L & l1) == 0L)
            {
                i = 9;
            } else
            {
                i = 10;
            }
            l = j + (l + i);
        }
        i = l;
        if (versionNameHash != null)
        {
            versionNameHash.longValue();
            i = l + (CodedOutputByteBufferNano.computeRawVarint32Size(40) + 8);
        }
        j = i;
        if (sampleInfo != null)
        {
            i1 = sampleInfo.intValue();
            l = CodedOutputByteBufferNano.computeRawVarint32Size(48);
            j = byte0;
            if (i1 >= 0)
            {
                j = CodedOutputByteBufferNano.computeRawVarint32Size(i1);
            }
            j = i + (l + j);
        }
        l = j;
        if (customEventName != null)
        {
            obj = customEventName;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(56);
            l = CodedOutputByteBufferNano.encodedLength(((CharSequence) (obj)));
            l = j + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + i);
        }
        i = l;
        if (isEventNameConstant != null)
        {
            isEventNameConstant.booleanValue();
            i = l + (CodedOutputByteBufferNano.computeRawVarint32Size(64) + 1);
        }
        j = i;
        if (metricExtension != null)
        {
            obj = metricExtension;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(72);
            l = ((MessageNano) (obj)).computeSerializedSize();
            obj.cachedSize = l;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(l) + l + j);
        }
        return j;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
label0:
        do
        {
            int i = codedinputbytebuffernano.readTag();
            switch (i)
            {
            default:
                if (super.storeUnknownField(codedinputbytebuffernano, i))
                {
                    continue;
                }
                // fall through

            case 0: // '\0'
                return this;

            case 10: // '\n'
                if (uidHealthProto == null)
                {
                    uidHealthProto = new UidHealthProto();
                }
                codedinputbytebuffernano.readMessage(uidHealthProto);
                continue;

            case 16: // '\020'
                long l1 = 0L;
                int j = 0;
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
                    int i1 = codedinputbytebuffernano.bufferPos;
                    codedinputbytebuffernano.bufferPos = i1 + 1;
                    i1 = abyte0[i1];
                    l1 |= (long)(i1 & 0x7f) << j;
                    if ((i1 & 0x80) == 0)
                    {
                        elapsedTime = Long.valueOf(l1);
                        continue label0;
                    }
                    j += 7;
                } while (true);
                throw new InvalidProtocolBufferNanoException("CodedInputStream encountered a malformed varint.");

            case 24: // '\030'
                long l2 = 0L;
                int k = 0;
                do
                {
                    if (k >= 64)
                    {
                        break;
                    }
                    if (codedinputbytebuffernano.bufferPos == codedinputbytebuffernano.maybeLimitedBufferSize)
                    {
                        throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
                    }
                    byte abyte1[] = codedinputbytebuffernano.buffer;
                    int j1 = codedinputbytebuffernano.bufferPos;
                    codedinputbytebuffernano.bufferPos = j1 + 1;
                    j1 = abyte1[j1];
                    l2 |= (long)(j1 & 0x7f) << k;
                    if ((j1 & 0x80) == 0)
                    {
                        currentTime = Long.valueOf(l2);
                        continue label0;
                    }
                    k += 7;
                } while (true);
                throw new InvalidProtocolBufferNanoException("CodedInputStream encountered a malformed varint.");

            case 32: // ' '
                long l3 = 0L;
                int l = 0;
                do
                {
                    if (l >= 64)
                    {
                        break;
                    }
                    if (codedinputbytebuffernano.bufferPos == codedinputbytebuffernano.maybeLimitedBufferSize)
                    {
                        throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
                    }
                    byte abyte2[] = codedinputbytebuffernano.buffer;
                    int k1 = codedinputbytebuffernano.bufferPos;
                    codedinputbytebuffernano.bufferPos = k1 + 1;
                    k1 = abyte2[k1];
                    l3 |= (long)(k1 & 0x7f) << l;
                    if ((k1 & 0x80) == 0)
                    {
                        primesVersion = Long.valueOf(l3);
                        continue label0;
                    }
                    l += 7;
                } while (true);
                throw new InvalidProtocolBufferNanoException("CodedInputStream encountered a malformed varint.");

            case 41: // ')'
                versionNameHash = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
                break;

            case 48: // '0'
                sampleInfo = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 58: // ':'
                customEventName = codedinputbytebuffernano.readString();
                break;

            case 64: // '@'
                boolean flag;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                isEventNameConstant = Boolean.valueOf(flag);
                break;

            case 74: // 'J'
                if (metricExtension == null)
                {
                    metricExtension = new MetricExtension();
                }
                codedinputbytebuffernano.readMessage(metricExtension);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (uidHealthProto != null)
        {
            UidHealthProto uidhealthproto = uidHealthProto;
            codedoutputbytebuffernano.writeRawVarint32(10);
            if (((MessageNano) (uidhealthproto)).cachedSize < 0)
            {
                uidhealthproto.cachedSize = uidhealthproto.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (uidhealthproto)).cachedSize);
            uidhealthproto.writeTo(codedoutputbytebuffernano);
        }
        if (elapsedTime != null)
        {
            long l = elapsedTime.longValue();
            codedoutputbytebuffernano.writeRawVarint32(16);
            codedoutputbytebuffernano.writeRawVarint64(l);
        }
        if (currentTime != null)
        {
            long l1 = currentTime.longValue();
            codedoutputbytebuffernano.writeRawVarint32(24);
            codedoutputbytebuffernano.writeRawVarint64(l1);
        }
        if (primesVersion != null)
        {
            long l2 = primesVersion.longValue();
            codedoutputbytebuffernano.writeRawVarint32(32);
            codedoutputbytebuffernano.writeRawVarint64(l2);
        }
        if (versionNameHash != null)
        {
            long l3 = versionNameHash.longValue();
            codedoutputbytebuffernano.writeRawVarint32(41);
            if (codedoutputbytebuffernano.buffer.remaining() < 8)
            {
                throw new com.google.protobuf.nano.CodedOutputByteBufferNano.OutOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.putLong(l3);
        }
        int i;
        if (sampleInfo != null)
        {
            i = sampleInfo.intValue();
            codedoutputbytebuffernano.writeRawVarint32(48);
            String s;
            boolean flag;
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (customEventName != null)
        {
            s = customEventName;
            codedoutputbytebuffernano.writeRawVarint32(58);
            codedoutputbytebuffernano.writeStringNoTag(s);
        }
        if (isEventNameConstant != null)
        {
            flag = isEventNameConstant.booleanValue();
            codedoutputbytebuffernano.writeRawVarint32(64);
            byte byte0;
            if (flag)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            byte0 = (byte)i;
            if (!codedoutputbytebuffernano.buffer.hasRemaining())
            {
                throw new com.google.protobuf.nano.CodedOutputByteBufferNano.OutOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte0);
        }
        if (metricExtension != null)
        {
            MetricExtension metricextension = metricExtension;
            codedoutputbytebuffernano.writeRawVarint32(74);
            if (((MessageNano) (metricextension)).cachedSize < 0)
            {
                metricextension.cachedSize = metricextension.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (metricextension)).cachedSize);
            metricextension.writeTo(codedoutputbytebuffernano);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
