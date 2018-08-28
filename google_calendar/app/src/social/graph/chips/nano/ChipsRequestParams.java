// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package social.graph.chips.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class ChipsRequestParams extends ExtendableMessageNano
{

    public int autocompleteType;
    public Integer queryLength;

    public ChipsRequestParams()
    {
        queryLength = null;
        autocompleteType = 0x80000000;
        cachedSize = -1;
    }

    private final ChipsRequestParams mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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

        case 8: // '\b'
            queryLength = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
            continue; /* Loop/switch isn't completed */

        case 16: // '\020'
            j = codedinputbytebuffernano.bufferPos;
            k = codedinputbytebuffernano.bufferStart;
            break;
        }
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l < 0 || l > 2)
        {
            break; /* Loop/switch isn't completed */
        }
        try
        {
            autocompleteType = l;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
            storeUnknownField(codedinputbytebuffernano, i);
        }
        if (true) goto _L2; else goto _L1
_L1:
        throw new IllegalArgumentException((new StringBuilder(48)).append(l).append(" is not a valid enum AutocompleteType").toString());
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (queryLength != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, queryLength.intValue());
        }
        j = i;
        if (autocompleteType != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, autocompleteType);
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
        if (queryLength != null)
        {
            codedoutputbytebuffernano.writeInt32(1, queryLength.intValue());
        }
        if (autocompleteType != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(2, autocompleteType);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
