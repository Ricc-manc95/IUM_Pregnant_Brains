// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.intention.habit.client.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class cachedSize extends ExtendableMessageNano
{

    public boolean enableFollowup;
    public boolean enableRecommit;
    public stance reminderOverride[];
    public boolean useDefaultReminders;

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (!useDefaultReminders)
        {
            boolean flag = useDefaultReminders;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(8) + 1);
        }
        j = i;
        if (reminderOverride != null)
        {
            j = i;
            if (reminderOverride.length > 0)
            {
                for (j = 0; j < reminderOverride.length;)
                {
                    stance stance = reminderOverride[j];
                    int k = i;
                    if (stance != null)
                    {
                        k = CodedOutputByteBufferNano.computeRawVarint32Size(16);
                        int l = stance.computeSerializedSize();
                        stance.cachedSize = l;
                        k = i + (CodedOutputByteBufferNano.computeRawVarint32Size(l) + l + k);
                    }
                    j++;
                    i = k;
                }

                j = i;
            }
        }
        i = j;
        if (enableRecommit)
        {
            boolean flag1 = enableRecommit;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(24) + 1);
        }
        j = i;
        if (enableFollowup)
        {
            boolean flag2 = enableFollowup;
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
                useDefaultReminders = flag;
                break;

            case 18: // '\022'
                int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 18);
                stance astance[];
                int j;
                if (reminderOverride == null)
                {
                    j = 0;
                } else
                {
                    j = reminderOverride.length;
                }
                astance = new stance[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(reminderOverride, 0, astance, 0, j);
                    k = j;
                }
                for (; k < astance.length - 1; k++)
                {
                    astance[k] = new stance();
                    codedinputbytebuffernano.readMessage(astance[k]);
                    codedinputbytebuffernano.readTag();
                }

                astance[k] = new stance();
                codedinputbytebuffernano.readMessage(astance[k]);
                reminderOverride = astance;
                break;

            case 24: // '\030'
                boolean flag1;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                enableRecommit = flag1;
                break;

            case 32: // ' '
                boolean flag2;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                enableFollowup = flag2;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        boolean flag = true;
        if (!useDefaultReminders)
        {
            boolean flag1 = useDefaultReminders;
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
                throw new com.google.protobuf.nano.utOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte0);
        }
        if (reminderOverride != null && reminderOverride.length > 0)
        {
            for (int j = 0; j < reminderOverride.length; j++)
            {
                stance stance = reminderOverride[j];
                if (stance == null)
                {
                    continue;
                }
                codedoutputbytebuffernano.writeRawVarint32(18);
                if (((MessageNano) (stance)).cachedSize < 0)
                {
                    stance.cachedSize = stance.computeSerializedSize();
                }
                codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (stance)).cachedSize);
                stance.writeTo(codedoutputbytebuffernano);
            }

        }
        if (enableRecommit)
        {
            boolean flag2 = enableRecommit;
            codedoutputbytebuffernano.writeRawVarint32(24);
            byte byte1;
            int k;
            if (flag2)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            byte1 = (byte)k;
            if (!codedoutputbytebuffernano.buffer.hasRemaining())
            {
                throw new com.google.protobuf.nano.utOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte1);
        }
        if (enableFollowup)
        {
            boolean flag3 = enableFollowup;
            codedoutputbytebuffernano.writeRawVarint32(32);
            byte byte2;
            int l;
            if (flag3)
            {
                l = ((flag) ? 1 : 0);
            } else
            {
                l = 0;
            }
            byte2 = (byte)l;
            if (!codedoutputbytebuffernano.buffer.hasRemaining())
            {
                throw new com.google.protobuf.nano.utOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte2);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public stance()
    {
        useDefaultReminders = true;
        reminderOverride = stance.emptyArray();
        enableRecommit = false;
        enableFollowup = false;
        unknownFieldData = null;
        cachedSize = -1;
    }
}
