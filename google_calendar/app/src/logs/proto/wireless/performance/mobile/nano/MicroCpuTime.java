// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class MicroCpuTime extends ExtendableMessageNano
{

    public Long cpuMicros;
    public Long wallMicros;

    public MicroCpuTime()
    {
        cpuMicros = null;
        wallMicros = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (cpuMicros != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(1, cpuMicros.longValue());
        }
        j = i;
        if (wallMicros != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(2, wallMicros.longValue());
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
                cpuMicros = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 16: // '\020'
                wallMicros = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (cpuMicros != null)
        {
            codedoutputbytebuffernano.writeInt64(1, cpuMicros.longValue());
        }
        if (wallMicros != null)
        {
            codedoutputbytebuffernano.writeInt64(2, wallMicros.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
