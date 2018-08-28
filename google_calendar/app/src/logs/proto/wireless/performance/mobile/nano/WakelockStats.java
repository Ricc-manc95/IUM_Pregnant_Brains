// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            HashedString

public final class WakelockStats extends ExtendableMessageNano
{

    private static volatile WakelockStats _emptyArray[];
    private Integer count;
    private Long durationMs;
    private HashedString name;

    public WakelockStats()
    {
        name = null;
        count = null;
        durationMs = null;
        cachedSize = -1;
    }

    public static WakelockStats[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new WakelockStats[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (name != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(1, name);
        }
        j = i;
        if (count != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, count.intValue());
        }
        i = j;
        if (durationMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(3, durationMs.longValue());
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
                if (name == null)
                {
                    name = new HashedString();
                }
                codedinputbytebuffernano.readMessage(name);
                break;

            case 16: // '\020'
                count = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 24: // '\030'
                durationMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (name != null)
        {
            codedoutputbytebuffernano.writeMessage(1, name);
        }
        if (count != null)
        {
            codedoutputbytebuffernano.writeInt32(2, count.intValue());
        }
        if (durationMs != null)
        {
            codedoutputbytebuffernano.writeInt64(3, durationMs.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
