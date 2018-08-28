// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            InnerTubeRequestInfo

public final class YouTubeExtension extends ExtendableMessageNano
{

    private InnerTubeRequestInfo innertubeRequestInfo;

    public YouTubeExtension()
    {
        innertubeRequestInfo = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (innertubeRequestInfo != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(1, innertubeRequestInfo);
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
                if (innertubeRequestInfo == null)
                {
                    innertubeRequestInfo = new InnerTubeRequestInfo();
                }
                codedinputbytebuffernano.readMessage(innertubeRequestInfo);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (innertubeRequestInfo != null)
        {
            codedoutputbytebuffernano.writeMessage(1, innertubeRequestInfo);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
