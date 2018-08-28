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
//            AssistantStats, SharingStats, BackgroundTaskStats, CameraAssistantStats

public final class PhotosExtension extends ExtendableMessageNano
{

    private AssistantStats assistantStats;
    private BackgroundTaskStats backgroundTaskStats;
    private CameraAssistantStats cameraAssistantStats;
    private SharingStats sharingStats;

    public PhotosExtension()
    {
        assistantStats = null;
        sharingStats = null;
        backgroundTaskStats = null;
        cameraAssistantStats = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (assistantStats != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(1, assistantStats);
        }
        j = i;
        if (sharingStats != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(2, sharingStats);
        }
        i = j;
        if (backgroundTaskStats != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(3, backgroundTaskStats);
        }
        j = i;
        if (cameraAssistantStats != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(4, cameraAssistantStats);
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
                if (assistantStats == null)
                {
                    assistantStats = new AssistantStats();
                }
                codedinputbytebuffernano.readMessage(assistantStats);
                break;

            case 18: // '\022'
                if (sharingStats == null)
                {
                    sharingStats = new SharingStats();
                }
                codedinputbytebuffernano.readMessage(sharingStats);
                break;

            case 26: // '\032'
                if (backgroundTaskStats == null)
                {
                    backgroundTaskStats = new BackgroundTaskStats();
                }
                codedinputbytebuffernano.readMessage(backgroundTaskStats);
                break;

            case 34: // '"'
                if (cameraAssistantStats == null)
                {
                    cameraAssistantStats = new CameraAssistantStats();
                }
                codedinputbytebuffernano.readMessage(cameraAssistantStats);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (assistantStats != null)
        {
            codedoutputbytebuffernano.writeMessage(1, assistantStats);
        }
        if (sharingStats != null)
        {
            codedoutputbytebuffernano.writeMessage(2, sharingStats);
        }
        if (backgroundTaskStats != null)
        {
            codedoutputbytebuffernano.writeMessage(3, backgroundTaskStats);
        }
        if (cameraAssistantStats != null)
        {
            codedoutputbytebuffernano.writeMessage(4, cameraAssistantStats);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
