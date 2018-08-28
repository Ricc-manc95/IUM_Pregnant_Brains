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
//            MicroCpuTime

public final class cachedSize extends ExtendableMessageNano
{

    private static volatile duration _emptyArray[];
    public MicroCpuTime duration;
    public int primesForPrimesEvent;

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
_L6:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 3: default 40
    //                   0: 49
    //                   8: 51
    //                   18: 143;
           goto _L1 _L2 _L3 _L4
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 4)
        {
            try
            {
                primesForPrimesEvent = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(52)).append(l).append(" is not a valid enum PrimesForPrimesEvent").toString());
_L4:
        if (duration == null)
        {
            duration = new MicroCpuTime();
        }
        codedinputbytebuffernano.readMessage(duration);
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (primesForPrimesEvent != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, primesForPrimesEvent);
        }
        j = i;
        if (duration != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(2, duration);
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
        if (primesForPrimesEvent != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(1, primesForPrimesEvent);
        }
        if (duration != null)
        {
            codedoutputbytebuffernano.writeMessage(2, duration);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ()
    {
        primesForPrimesEvent = 0x80000000;
        duration = null;
        cachedSize = -1;
    }
}
