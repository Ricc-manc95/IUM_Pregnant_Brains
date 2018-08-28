// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.rosy.calendar.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class cachedSize extends ExtendableMessageNano
{

    private b doNotDisturb;
    public int oneof_status_;
    public  outOfOffice;
    private where workingElsewhere;

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (oneof_status_ == 0)
        {
              = outOfOffice;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = .computeSerializedSize();
            .cachedSize = k;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(k) + k + i);
        }
        j = i;
        if (oneof_status_ == 1)
        {
            b b = doNotDisturb;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            int l = b.computeSerializedSize();
            b.cachedSize = l;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(l) + l + j);
        }
        i = j;
        if (oneof_status_ == 2)
        {
            where where = workingElsewhere;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            int i1 = where.computeSerializedSize();
            where.cachedSize = i1;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(i1) + i1 + i);
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
                if (outOfOffice == null)
                {
                    outOfOffice = new ();
                }
                codedinputbytebuffernano.readMessage(outOfOffice);
                oneof_status_ = 0;
                break;

            case 18: // '\022'
                if (doNotDisturb == null)
                {
                    doNotDisturb = new b();
                }
                codedinputbytebuffernano.readMessage(doNotDisturb);
                oneof_status_ = 1;
                break;

            case 26: // '\032'
                if (workingElsewhere == null)
                {
                    workingElsewhere = new where();
                }
                codedinputbytebuffernano.readMessage(workingElsewhere);
                oneof_status_ = 2;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (oneof_status_ == 0)
        {
              = outOfOffice;
            codedoutputbytebuffernano.writeRawVarint32(10);
            if (((MessageNano) ()).cachedSize < 0)
            {
                .cachedSize = .computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) ()).cachedSize);
            .writeTo(codedoutputbytebuffernano);
        }
        if (oneof_status_ == 1)
        {
            b b = doNotDisturb;
            codedoutputbytebuffernano.writeRawVarint32(18);
            if (((MessageNano) (b)).cachedSize < 0)
            {
                b.cachedSize = b.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (b)).cachedSize);
            b.writeTo(codedoutputbytebuffernano);
        }
        if (oneof_status_ == 2)
        {
            where where = workingElsewhere;
            codedoutputbytebuffernano.writeRawVarint32(26);
            if (((MessageNano) (where)).cachedSize < 0)
            {
                where.cachedSize = where.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (where)).cachedSize);
            where.writeTo(codedoutputbytebuffernano);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public where()
    {
        oneof_status_ = -1;
        oneof_status_ = -1;
        outOfOffice = null;
        oneof_status_ = -1;
        doNotDisturb = null;
        oneof_status_ = -1;
        workingElsewhere = null;
        unknownFieldData = null;
        cachedSize = -1;
    }
}
