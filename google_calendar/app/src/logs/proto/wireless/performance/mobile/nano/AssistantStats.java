// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class AssistantStats extends ExtendableMessageNano
{

    private Integer numPhotosLoaded;
    private Integer numPhotosRequested;

    public AssistantStats()
    {
        numPhotosLoaded = null;
        numPhotosRequested = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (numPhotosLoaded != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(2, numPhotosLoaded.intValue());
        }
        j = i;
        if (numPhotosRequested != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(3, numPhotosRequested.intValue());
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

            case 16: // '\020'
                numPhotosLoaded = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 24: // '\030'
                numPhotosRequested = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (numPhotosLoaded != null)
        {
            codedoutputbytebuffernano.writeInt32(2, numPhotosLoaded.intValue());
        }
        if (numPhotosRequested != null)
        {
            codedoutputbytebuffernano.writeInt32(3, numPhotosRequested.intValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
