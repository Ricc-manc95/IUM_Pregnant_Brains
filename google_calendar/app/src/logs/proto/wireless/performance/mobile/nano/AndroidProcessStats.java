// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class AndroidProcessStats extends ExtendableMessageNano
{

    public Boolean isInForeground;
    public Long processElapsedTimeMs;
    public String processName;
    public Integer threadCount;

    public AndroidProcessStats()
    {
        processElapsedTimeMs = null;
        isInForeground = null;
        threadCount = null;
        processName = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (processElapsedTimeMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(1, processElapsedTimeMs.longValue());
        }
        j = i;
        if (isInForeground != null)
        {
            isInForeground.booleanValue();
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(16) + 1);
        }
        i = j;
        if (threadCount != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(3, threadCount.intValue());
        }
        j = i;
        if (processName != null)
        {
            String s = processName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(32);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
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
                processElapsedTimeMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 16: // '\020'
                boolean flag;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                isInForeground = Boolean.valueOf(flag);
                break;

            case 24: // '\030'
                threadCount = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 34: // '"'
                processName = codedinputbytebuffernano.readString();
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (processElapsedTimeMs != null)
        {
            codedoutputbytebuffernano.writeInt64(1, processElapsedTimeMs.longValue());
        }
        if (isInForeground != null)
        {
            codedoutputbytebuffernano.writeBool(2, isInForeground.booleanValue());
        }
        if (threadCount != null)
        {
            codedoutputbytebuffernano.writeInt32(3, threadCount.intValue());
        }
        if (processName != null)
        {
            codedoutputbytebuffernano.writeString(4, processName);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
