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

public final class ServiceHealthProto extends ExtendableMessageNano
{

    private static volatile ServiceHealthProto _emptyArray[];
    public Integer launchCount;
    public HashedString name;
    public Integer startServiceCount;

    public ServiceHealthProto()
    {
        startServiceCount = null;
        launchCount = null;
        name = null;
        cachedSize = -1;
    }

    public static ServiceHealthProto[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new ServiceHealthProto[0];
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
        if (startServiceCount != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, startServiceCount.intValue());
        }
        j = i;
        if (launchCount != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, launchCount.intValue());
        }
        i = j;
        if (name != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(3, name);
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

            case 8: // '\b'
                startServiceCount = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 16: // '\020'
                launchCount = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 26: // '\032'
                if (name == null)
                {
                    name = new HashedString();
                }
                codedinputbytebuffernano.readMessage(name);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (startServiceCount != null)
        {
            codedoutputbytebuffernano.writeInt32(1, startServiceCount.intValue());
        }
        if (launchCount != null)
        {
            codedoutputbytebuffernano.writeInt32(2, launchCount.intValue());
        }
        if (name != null)
        {
            codedoutputbytebuffernano.writeMessage(3, name);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
