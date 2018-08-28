// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.intention.habit.client.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class cachedSize extends ExtendableMessageNano
{

    private long clientSchedulingWindowEndMillisUtc;
    private Instance instances[];

    protected final int computeSerializedSize()
    {
        int i = 8;
        int j = super.computeSerializedSize();
        if (clientSchedulingWindowEndMillisUtc != 0L)
        {
            long l1 = clientSchedulingWindowEndMillisUtc;
            int k = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            Instance instance;
            int l;
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
            if ((0xff00000000000000L & l1) != 0L)
            {
                if ((l1 & 0x8000000000000000L) == 0L)
                {
                    i = 9;
                } else
                {
                    i = 10;
                }
            }
            i = i + k + j;
        } else
        {
            i = j;
        }
        j = i;
        if (instances != null)
        {
            j = i;
            if (instances.length > 0)
            {
                for (j = 0; j < instances.length;)
                {
                    instance = instances[j];
                    k = i;
                    if (instance != null)
                    {
                        k = CodedOutputByteBufferNano.computeRawVarint32Size(16);
                        l = instance.computeSerializedSize();
                        instance.cachedSize = l;
                        k = i + (CodedOutputByteBufferNano.computeRawVarint32Size(l) + l + k);
                    }
                    j++;
                    i = k;
                }

                j = i;
            }
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

            case 8: // '\b'
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
                    int l = codedinputbytebuffernano.bufferPos;
                    codedinputbytebuffernano.bufferPos = l + 1;
                    l = abyte0[l];
                    l1 |= (long)(l & 0x7f) << j;
                    if ((l & 0x80) == 0)
                    {
                        clientSchedulingWindowEndMillisUtc = l1;
                        continue label0;
                    }
                    j += 7;
                } while (true);
                throw new InvalidProtocolBufferNanoException("CodedInputStream encountered a malformed varint.");

            case 18: // '\022'
                int i1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 18);
                Instance ainstance[];
                int k;
                if (instances == null)
                {
                    k = 0;
                } else
                {
                    k = instances.length;
                }
                ainstance = new Instance[i1 + k];
                i1 = k;
                if (k != 0)
                {
                    System.arraycopy(instances, 0, ainstance, 0, k);
                    i1 = k;
                }
                for (; i1 < ainstance.length - 1; i1++)
                {
                    ainstance[i1] = new Instance();
                    codedinputbytebuffernano.readMessage(ainstance[i1]);
                    codedinputbytebuffernano.readTag();
                }

                ainstance[i1] = new Instance();
                codedinputbytebuffernano.readMessage(ainstance[i1]);
                instances = ainstance;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (clientSchedulingWindowEndMillisUtc != 0L)
        {
            long l = clientSchedulingWindowEndMillisUtc;
            codedoutputbytebuffernano.writeRawVarint32(8);
            codedoutputbytebuffernano.writeRawVarint64(l);
        }
        if (instances != null && instances.length > 0)
        {
            for (int i = 0; i < instances.length; i++)
            {
                Instance instance = instances[i];
                if (instance == null)
                {
                    continue;
                }
                codedoutputbytebuffernano.writeRawVarint32(18);
                if (((MessageNano) (instance)).cachedSize < 0)
                {
                    instance.cachedSize = instance.computeSerializedSize();
                }
                codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (instance)).cachedSize);
                instance.writeTo(codedoutputbytebuffernano);
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public Instance.cachedSize()
    {
        clientSchedulingWindowEndMillisUtc = 0L;
        class Instance extends ExtendableMessageNano
        {

            private static volatile Instance _emptyArray[];
            private String eventId;
            private long startTimeMillisUtc;

            public static Instance[] emptyArray()
            {
                if (_emptyArray == null)
                {
                    synchronized (InternalNano.LAZY_INIT_LOCK)
                    {
                        if (_emptyArray == null)
                        {
                            _emptyArray = new Instance[0];
                        }
                    }
                }
                return _emptyArray;
                exception;
                obj;
                JVM INSTR monitorexit ;
                throw exception;
            }

            protected final int computeSerializedSize()
            {
                int i;
                int j;
                byte byte0;
                byte0 = 8;
                i = super.computeSerializedSize();
                j = i;
                if (eventId != null)
                {
                    j = i;
                    if (!eventId.equals(""))
                    {
                        String s = eventId;
                        j = CodedOutputByteBufferNano.computeRawVarint32Size(8);
                        int k = CodedOutputByteBufferNano.encodedLength(s);
                        j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
                    }
                }
                i = j;
                if (startTimeMillisUtc == 0L) goto _L2; else goto _L1
_L1:
                int l;
                long l1;
                l1 = startTimeMillisUtc;
                l = CodedOutputByteBufferNano.computeRawVarint32Size(16);
                if ((-128L & l1) != 0L) goto _L4; else goto _L3
_L3:
                i = 1;
_L6:
                i = j + (i + l);
_L2:
                return i;
_L4:
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
                {
                    i = byte0;
                    if ((0xff00000000000000L & l1) != 0L)
                    {
                        if ((l1 & 0x8000000000000000L) == 0L)
                        {
                            i = 9;
                        } else
                        {
                            i = 10;
                        }
                    }
                }
                if (true) goto _L6; else goto _L5
_L5:
            }

            public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
                throws IOException
            {
label0:
                do
                {
                    int i = codedinputbytebuffernano.readTag();
                    long l;
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
                        eventId = codedinputbytebuffernano.readString();
                        continue;

                    case 16: // '\020'
                        i = 0;
                        l = 0L;
                        break;
                    }
                    do
                    {
                        if (i >= 64)
                        {
                            break;
                        }
                        if (codedinputbytebuffernano.bufferPos == codedinputbytebuffernano.maybeLimitedBufferSize)
                        {
                            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
                        }
                        byte abyte0[] = codedinputbytebuffernano.buffer;
                        int j = codedinputbytebuffernano.bufferPos;
                        codedinputbytebuffernano.bufferPos = j + 1;
                        j = abyte0[j];
                        l |= (long)(j & 0x7f) << i;
                        if ((j & 0x80) == 0)
                        {
                            startTimeMillisUtc = l;
                            continue label0;
                        }
                        i += 7;
                    } while (true);
                    throw new InvalidProtocolBufferNanoException("CodedInputStream encountered a malformed varint.");
                } while (true);
            }

            public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
                throws IOException
            {
                if (eventId != null && !eventId.equals(""))
                {
                    String s = eventId;
                    codedoutputbytebuffernano.writeRawVarint32(10);
                    codedoutputbytebuffernano.writeStringNoTag(s);
                }
                if (startTimeMillisUtc != 0L)
                {
                    long l = startTimeMillisUtc;
                    codedoutputbytebuffernano.writeRawVarint32(16);
                    codedoutputbytebuffernano.writeRawVarint64(l);
                }
                super.writeTo(codedoutputbytebuffernano);
            }

            public Instance()
            {
                eventId = "";
                startTimeMillisUtc = 0L;
                unknownFieldData = null;
                cachedSize = -1;
            }
        }

        instances = Instance.emptyArray();
        unknownFieldData = null;
        cachedSize = -1;
    }
}
