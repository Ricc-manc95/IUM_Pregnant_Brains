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

    public ion autoReply;
    public String calendarDeclineMessage;
    public boolean declineConflictingEvents;

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (calendarDeclineMessage != null)
        {
            i = j;
            if (!calendarDeclineMessage.equals(""))
            {
                String s = calendarDeclineMessage;
                i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
                int k = CodedOutputByteBufferNano.encodedLength(s);
                i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
            }
        }
        j = i;
        if (autoReply != null)
        {
            cachedSize cachedsize = autoReply;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            int l = cachedsize.computeSerializedSize();
            cachedsize.cachedSize = l;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(l) + l + j);
        }
        i = j;
        if (declineConflictingEvents)
        {
            boolean flag = declineConflictingEvents;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(24) + 1);
        }
        return i;
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

            case 10: // '\n'
                calendarDeclineMessage = codedinputbytebuffernano.readString();
                break;

            case 18: // '\022'
                if (autoReply == null)
                {
                    autoReply = new nit>();
                }
                codedinputbytebuffernano.readMessage(autoReply);
                break;

            case 24: // '\030'
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
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (calendarDeclineMessage != null && !calendarDeclineMessage.equals(""))
        {
            String s = calendarDeclineMessage;
            codedoutputbytebuffernano.writeRawVarint32(10);
            codedoutputbytebuffernano.writeStringNoTag(s);
        }
        if (autoReply != null)
        {
            declineConflictingEvents declineconflictingevents = autoReply;
            codedoutputbytebuffernano.writeRawVarint32(18);
            if (((MessageNano) (declineconflictingevents)).cachedSize < 0)
            {
                declineconflictingevents.cachedSize = declineconflictingevents.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (declineconflictingevents)).cachedSize);
            declineconflictingevents.writeTo(codedoutputbytebuffernano);
        }
        if (declineConflictingEvents)
        {
            boolean flag = declineConflictingEvents;
            codedoutputbytebuffernano.writeRawVarint32(24);
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
                throw new com.google.protobuf.nano.tOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte0);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ion()
    {
        declineConflictingEvents = false;
        calendarDeclineMessage = "";
        autoReply = null;
        unknownFieldData = null;
        cachedSize = -1;
    }
}
