// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.framework.logging.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class GCoreClientInfo extends ExtendableMessageNano
{

    private String callingPackage;
    private int clientType;
    private Integer sdkVersion;
    private Integer versionCode;
    private String versionName;

    public GCoreClientInfo()
    {
        callingPackage = null;
        sdkVersion = null;
        versionCode = null;
        versionName = null;
        clientType = 0x80000000;
        cachedSize = -1;
    }

    private final GCoreClientInfo mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L2:
        int i;
        int j;
        int k;
        i = codedinputbytebuffernano.readTag();
        switch (i)
        {
        default:
            if (super.storeUnknownField(codedinputbytebuffernano, i))
            {
                continue; /* Loop/switch isn't completed */
            }
            // fall through

        case 0: // '\0'
            return this;

        case 10: // '\n'
            callingPackage = codedinputbytebuffernano.readString();
            continue; /* Loop/switch isn't completed */

        case 16: // '\020'
            sdkVersion = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
            continue; /* Loop/switch isn't completed */

        case 24: // '\030'
            versionCode = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
            continue; /* Loop/switch isn't completed */

        case 34: // '"'
            versionName = codedinputbytebuffernano.readString();
            continue; /* Loop/switch isn't completed */

        case 40: // '('
            j = codedinputbytebuffernano.bufferPos;
            k = codedinputbytebuffernano.bufferStart;
            break;
        }
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l < 0 || l > 3)
        {
            break; /* Loop/switch isn't completed */
        }
        try
        {
            clientType = l;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
            storeUnknownField(codedinputbytebuffernano, i);
        }
        if (true) goto _L2; else goto _L1
_L1:
        throw new IllegalArgumentException((new StringBuilder(42)).append(l).append(" is not a valid enum ClientType").toString());
    }

    protected final int computeSerializedSize()
    {
        byte byte0 = 10;
        int j = super.computeSerializedSize();
        int i = j;
        if (callingPackage != null)
        {
            String s = callingPackage;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
        }
        j = i;
        if (sdkVersion != null)
        {
            j = sdkVersion.intValue();
            int l = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            String s1;
            int i1;
            if (j >= 0)
            {
                j = CodedOutputByteBufferNano.computeRawVarint32Size(j);
            } else
            {
                j = 10;
            }
            j = i + (j + l);
        }
        l = j;
        if (versionCode != null)
        {
            i = versionCode.intValue();
            l = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            if (i >= 0)
            {
                i = CodedOutputByteBufferNano.computeRawVarint32Size(i);
            } else
            {
                i = 10;
            }
            l = j + (i + l);
        }
        i = l;
        if (versionName != null)
        {
            s1 = versionName;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(32);
            j = CodedOutputByteBufferNano.encodedLength(s1);
            i = l + (j + CodedOutputByteBufferNano.computeRawVarint32Size(j) + i);
        }
        j = i;
        if (clientType != 0x80000000)
        {
            i1 = clientType;
            l = CodedOutputByteBufferNano.computeRawVarint32Size(40);
            j = byte0;
            if (i1 >= 0)
            {
                j = CodedOutputByteBufferNano.computeRawVarint32Size(i1);
            }
            j = i + (l + j);
        }
        return j;
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        return mergeFrom(codedinputbytebuffernano);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (callingPackage != null)
        {
            String s = callingPackage;
            codedoutputbytebuffernano.writeRawVarint32(10);
            codedoutputbytebuffernano.writeStringNoTag(s);
        }
        int i;
        if (sdkVersion != null)
        {
            i = sdkVersion.intValue();
            codedoutputbytebuffernano.writeRawVarint32(16);
            String s1;
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (versionCode != null)
        {
            i = versionCode.intValue();
            codedoutputbytebuffernano.writeRawVarint32(24);
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (versionName != null)
        {
            s1 = versionName;
            codedoutputbytebuffernano.writeRawVarint32(34);
            codedoutputbytebuffernano.writeStringNoTag(s1);
        }
        if (clientType != 0x80000000)
        {
            i = clientType;
            codedoutputbytebuffernano.writeRawVarint32(40);
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
