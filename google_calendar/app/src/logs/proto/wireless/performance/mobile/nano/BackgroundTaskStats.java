// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class BackgroundTaskStats extends ExtendableMessageNano
{

    private Boolean isBackgroundServiceRunningAlready;
    private Boolean isPhotosInForeground;

    public BackgroundTaskStats()
    {
        isPhotosInForeground = null;
        isBackgroundServiceRunningAlready = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (isPhotosInForeground != null)
        {
            isPhotosInForeground.booleanValue();
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(8) + 1);
        }
        j = i;
        if (isBackgroundServiceRunningAlready != null)
        {
            isBackgroundServiceRunningAlready.booleanValue();
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(16) + 1);
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
                isPhotosInForeground = Boolean.valueOf(flag);
                break;

            case 16: // '\020'
                boolean flag1;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                isBackgroundServiceRunningAlready = Boolean.valueOf(flag1);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (isPhotosInForeground != null)
        {
            codedoutputbytebuffernano.writeBool(1, isPhotosInForeground.booleanValue());
        }
        if (isBackgroundServiceRunningAlready != null)
        {
            codedoutputbytebuffernano.writeBool(2, isBackgroundServiceRunningAlready.booleanValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
