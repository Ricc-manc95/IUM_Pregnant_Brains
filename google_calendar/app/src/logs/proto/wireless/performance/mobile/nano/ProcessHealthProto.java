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

public final class ProcessHealthProto extends ExtendableMessageNano
{

    private static volatile ProcessHealthProto _emptyArray[];
    public Long anrCount;
    public Long crashesCount;
    public Long foregroundMs;
    public HashedString name;
    public Long startsCount;
    public Long systemTimeMs;
    public Long userTimeMs;

    public ProcessHealthProto()
    {
        userTimeMs = null;
        systemTimeMs = null;
        startsCount = null;
        crashesCount = null;
        anrCount = null;
        foregroundMs = null;
        name = null;
        cachedSize = -1;
    }

    public static ProcessHealthProto[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new ProcessHealthProto[0];
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
        if (userTimeMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(1, userTimeMs.longValue());
        }
        j = i;
        if (systemTimeMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(2, systemTimeMs.longValue());
        }
        i = j;
        if (startsCount != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(3, startsCount.longValue());
        }
        j = i;
        if (crashesCount != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(4, crashesCount.longValue());
        }
        i = j;
        if (anrCount != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(5, anrCount.longValue());
        }
        j = i;
        if (foregroundMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(6, foregroundMs.longValue());
        }
        i = j;
        if (name != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(7, name);
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
                userTimeMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 16: // '\020'
                systemTimeMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 24: // '\030'
                startsCount = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 32: // ' '
                crashesCount = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 40: // '('
                anrCount = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 48: // '0'
                foregroundMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 58: // ':'
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
        if (userTimeMs != null)
        {
            codedoutputbytebuffernano.writeInt64(1, userTimeMs.longValue());
        }
        if (systemTimeMs != null)
        {
            codedoutputbytebuffernano.writeInt64(2, systemTimeMs.longValue());
        }
        if (startsCount != null)
        {
            codedoutputbytebuffernano.writeInt64(3, startsCount.longValue());
        }
        if (crashesCount != null)
        {
            codedoutputbytebuffernano.writeInt64(4, crashesCount.longValue());
        }
        if (anrCount != null)
        {
            codedoutputbytebuffernano.writeInt64(5, anrCount.longValue());
        }
        if (foregroundMs != null)
        {
            codedoutputbytebuffernano.writeInt64(6, foregroundMs.longValue());
        }
        if (name != null)
        {
            codedoutputbytebuffernano.writeMessage(7, name);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
