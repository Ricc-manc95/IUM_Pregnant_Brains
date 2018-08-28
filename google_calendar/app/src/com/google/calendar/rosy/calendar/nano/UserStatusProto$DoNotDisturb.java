// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.rosy.calendar.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class cachedSize extends ExtendableMessageNano
{

    private String calendarDeclineMessage;
    private boolean declineConflictingEvents;

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (declineConflictingEvents)
        {
            boolean flag = declineConflictingEvents;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(8) + 1);
        }
        j = i;
        if (calendarDeclineMessage != null)
        {
            j = i;
            if (!calendarDeclineMessage.equals(""))
            {
                String s = calendarDeclineMessage;
                j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
                int k = CodedOutputByteBufferNano.encodedLength(s);
                j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
            }
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
                declineConflictingEvents = flag;
                break;

            case 18: // '\022'
                calendarDeclineMessage = codedinputbytebuffernano.readString();
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (declineConflictingEvents)
        {
            boolean flag = declineConflictingEvents;
            codedoutputbytebuffernano.writeRawVarint32(8);
            byte byte0;
            int i;
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
                throw new com.google.protobuf.nano.OfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte0);
        }
        if (calendarDeclineMessage != null && !calendarDeclineMessage.equals(""))
        {
            String s = calendarDeclineMessage;
            codedoutputbytebuffernano.writeRawVarint32(18);
            codedoutputbytebuffernano.writeStringNoTag(s);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public on()
    {
        declineConflictingEvents = false;
        calendarDeclineMessage = "";
        unknownFieldData = null;
        cachedSize = -1;
    }
}
