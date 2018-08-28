// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.framework.logging.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class GCoreModuleInfo extends ExtendableMessageNano
{

    private String moduleId;
    private Integer moduleVersion;

    public GCoreModuleInfo()
    {
        moduleId = null;
        moduleVersion = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (moduleId != null)
        {
            String s = moduleId;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
        }
        j = i;
        if (moduleVersion != null)
        {
            j = moduleVersion.intValue();
            int l = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            if (j >= 0)
            {
                j = CodedOutputByteBufferNano.computeRawVarint32Size(j);
            } else
            {
                j = 10;
            }
            j = i + (j + l);
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

            case 10: // '\n'
                moduleId = codedinputbytebuffernano.readString();
                break;

            case 16: // '\020'
                moduleVersion = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (moduleId != null)
        {
            String s = moduleId;
            codedoutputbytebuffernano.writeRawVarint32(10);
            codedoutputbytebuffernano.writeStringNoTag(s);
        }
        if (moduleVersion != null)
        {
            int i = moduleVersion.intValue();
            codedoutputbytebuffernano.writeRawVarint32(16);
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
