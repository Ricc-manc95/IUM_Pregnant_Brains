// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.android.gmm.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class cachedSize extends ExtendableMessageNano
{

    private static volatile veAction _emptyArray[];
    private Integer leafVeTypeId;
    private int veAction;

    public static cachedSize[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new _emptyArray[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final _emptyArray mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
            leafVeTypeId = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
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
            veAction = l;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
            storeUnknownField(codedinputbytebuffernano, i);
        }
        if (true) goto _L2; else goto _L1
_L1:
        throw new IllegalArgumentException((new StringBuilder(40)).append(l).append(" is not a valid enum VEAction").toString());
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (leafVeTypeId != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, leafVeTypeId.intValue());
        }
        j = i;
        if (veAction != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, veAction);
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
        if (leafVeTypeId != null)
        {
            codedoutputbytebuffernano.writeInt32(1, leafVeTypeId.intValue());
        }
        if (veAction != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(2, veAction);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ()
    {
        leafVeTypeId = null;
        veAction = 0x80000000;
        cachedSize = -1;
    }
}
