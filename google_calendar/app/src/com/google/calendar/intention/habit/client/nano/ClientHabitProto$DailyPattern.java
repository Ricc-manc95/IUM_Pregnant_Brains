// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.intention.habit.client.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class cachedSize extends ExtendableMessageNano
{

    public boolean afternoon;
    public boolean any;
    public boolean evening;
    public boolean morning;

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (any)
        {
            boolean flag = any;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(8) + 1);
        }
        j = i;
        if (morning)
        {
            boolean flag1 = morning;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(16) + 1);
        }
        i = j;
        if (afternoon)
        {
            boolean flag2 = afternoon;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(24) + 1);
        }
        j = i;
        if (evening)
        {
            boolean flag3 = evening;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(32) + 1);
        }
        return j;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
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
                boolean flag;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                any = flag;
                break;

            case 16: // '\020'
                boolean flag1;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                morning = flag1;
                break;

            case 24: // '\030'
                boolean flag2;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                afternoon = flag2;
                break;

            case 32: // ' '
                boolean flag3;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag3 = true;
                } else
                {
                    flag3 = false;
                }
                evening = flag3;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        boolean flag = true;
        if (any)
        {
            boolean flag1 = any;
            codedoutputbytebuffernano.writeRawVarint32(8);
            byte byte0;
            int i;
            if (flag1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            byte0 = (byte)i;
            if (!codedoutputbytebuffernano.buffer.hasRemaining())
            {
                throw new com.google.protobuf.nano.fSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte0);
        }
        if (morning)
        {
            boolean flag2 = morning;
            codedoutputbytebuffernano.writeRawVarint32(16);
            byte byte1;
            int j;
            if (flag2)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            byte1 = (byte)j;
            if (!codedoutputbytebuffernano.buffer.hasRemaining())
            {
                throw new com.google.protobuf.nano.fSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte1);
        }
        if (afternoon)
        {
            boolean flag3 = afternoon;
            codedoutputbytebuffernano.writeRawVarint32(24);
            byte byte2;
            int k;
            if (flag3)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            byte2 = (byte)k;
            if (!codedoutputbytebuffernano.buffer.hasRemaining())
            {
                throw new com.google.protobuf.nano.fSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte2);
        }
        if (evening)
        {
            boolean flag4 = evening;
            codedoutputbytebuffernano.writeRawVarint32(32);
            byte byte3;
            int l;
            if (flag4)
            {
                l = ((flag) ? 1 : 0);
            } else
            {
                l = 0;
            }
            byte3 = (byte)l;
            if (!codedoutputbytebuffernano.buffer.hasRemaining())
            {
                throw new com.google.protobuf.nano.fSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte3);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ()
    {
        any = false;
        morning = false;
        afternoon = false;
        evening = false;
        unknownFieldData = null;
        cachedSize = -1;
    }
}
