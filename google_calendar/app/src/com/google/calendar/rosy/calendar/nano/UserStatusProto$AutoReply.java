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

    public String body;
    public boolean enabled;
    public boolean restrictToContacts;
    public boolean restrictToDomain;
    public String subject;

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (enabled)
        {
            boolean flag = enabled;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(8) + 1);
        }
        j = i;
        if (subject != null)
        {
            j = i;
            if (!subject.equals(""))
            {
                String s = subject;
                j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
                int k = CodedOutputByteBufferNano.encodedLength(s);
                j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
            }
        }
        i = j;
        if (body != null)
        {
            i = j;
            if (!body.equals(""))
            {
                String s1 = body;
                i = CodedOutputByteBufferNano.computeRawVarint32Size(24);
                int l = CodedOutputByteBufferNano.encodedLength(s1);
                i = j + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + i);
            }
        }
        j = i;
        if (restrictToContacts)
        {
            boolean flag1 = restrictToContacts;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(32) + 1);
        }
        i = j;
        if (restrictToDomain)
        {
            boolean flag2 = restrictToDomain;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(40) + 1);
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

            case 8: // '\b'
                boolean flag;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                enabled = flag;
                break;

            case 18: // '\022'
                subject = codedinputbytebuffernano.readString();
                break;

            case 26: // '\032'
                body = codedinputbytebuffernano.readString();
                break;

            case 32: // ' '
                boolean flag1;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                restrictToContacts = flag1;
                break;

            case 40: // '('
                boolean flag2;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                restrictToDomain = flag2;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        boolean flag = true;
        if (enabled)
        {
            boolean flag1 = enabled;
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
                throw new com.google.protobuf.nano.OutOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte0);
        }
        if (subject != null && !subject.equals(""))
        {
            String s = subject;
            codedoutputbytebuffernano.writeRawVarint32(18);
            codedoutputbytebuffernano.writeStringNoTag(s);
        }
        if (body != null && !body.equals(""))
        {
            String s1 = body;
            codedoutputbytebuffernano.writeRawVarint32(26);
            codedoutputbytebuffernano.writeStringNoTag(s1);
        }
        if (restrictToContacts)
        {
            boolean flag2 = restrictToContacts;
            codedoutputbytebuffernano.writeRawVarint32(32);
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
                throw new com.google.protobuf.nano.OutOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte1);
        }
        if (restrictToDomain)
        {
            boolean flag3 = restrictToDomain;
            codedoutputbytebuffernano.writeRawVarint32(40);
            byte byte2;
            int k;
            if (flag3)
            {
                k = ((flag) ? 1 : 0);
            } else
            {
                k = 0;
            }
            byte2 = (byte)k;
            if (!codedoutputbytebuffernano.buffer.hasRemaining())
            {
                throw new com.google.protobuf.nano.OutOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.put(byte2);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ption()
    {
        enabled = false;
        subject = "";
        body = "";
        restrictToContacts = false;
        restrictToDomain = false;
        unknownFieldData = null;
        cachedSize = -1;
    }
}
