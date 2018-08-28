// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            SharedImageMetadata

public final class SharedImageTransform extends ExtendableMessageNano
{

    private static volatile SharedImageTransform _emptyArray[];
    private int resizedByType;
    private SharedImageMetadata sharedImageMetadatas[];

    public SharedImageTransform()
    {
        sharedImageMetadatas = SharedImageMetadata.emptyArray();
        resizedByType = 0x80000000;
        cachedSize = -1;
    }

    public static SharedImageTransform[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new SharedImageTransform[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final SharedImageTransform mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L2:
        int i;
        int k;
        int l;
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

        case 10: // '\n'
            int j = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 10);
            SharedImageMetadata asharedimagemetadata[];
            if (sharedImageMetadatas == null)
            {
                i = 0;
            } else
            {
                i = sharedImageMetadatas.length;
            }
            asharedimagemetadata = new SharedImageMetadata[j + i];
            j = i;
            if (i != 0)
            {
                System.arraycopy(sharedImageMetadatas, 0, asharedimagemetadata, 0, i);
                j = i;
            }
            for (; j < asharedimagemetadata.length - 1; j++)
            {
                asharedimagemetadata[j] = new SharedImageMetadata();
                codedinputbytebuffernano.readMessage(asharedimagemetadata[j]);
                codedinputbytebuffernano.readTag();
            }

            asharedimagemetadata[j] = new SharedImageMetadata();
            codedinputbytebuffernano.readMessage(asharedimagemetadata[j]);
            sharedImageMetadatas = asharedimagemetadata;
            continue; /* Loop/switch isn't completed */

        case 16: // '\020'
            k = codedinputbytebuffernano.bufferPos;
            l = codedinputbytebuffernano.bufferStart;
            break;
        }
        int i1 = codedinputbytebuffernano.readRawVarint32();
        if (i1 < 0 || i1 > 1)
        {
            break; /* Loop/switch isn't completed */
        }
        try
        {
            resizedByType = i1;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            codedinputbytebuffernano.rewindToPositionAndTag(k - l, codedinputbytebuffernano.lastTag);
            storeUnknownField(codedinputbytebuffernano, i);
        }
        if (true) goto _L2; else goto _L1
_L1:
        throw new IllegalArgumentException((new StringBuilder(45)).append(i1).append(" is not a valid enum ResizedByType").toString());
    }

    protected final int computeSerializedSize()
    {
        int i = super.computeSerializedSize();
        int j = i;
        if (sharedImageMetadatas != null)
        {
            j = i;
            if (sharedImageMetadatas.length > 0)
            {
                int k = 0;
                do
                {
                    j = i;
                    if (k >= sharedImageMetadatas.length)
                    {
                        break;
                    }
                    SharedImageMetadata sharedimagemetadata = sharedImageMetadatas[k];
                    j = i;
                    if (sharedimagemetadata != null)
                    {
                        j = i + CodedOutputByteBufferNano.computeMessageSize(1, sharedimagemetadata);
                    }
                    k++;
                    i = j;
                } while (true);
            }
        }
        i = j;
        if (resizedByType != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(2, resizedByType);
        }
        return i;
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        return mergeFrom(codedinputbytebuffernano);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (sharedImageMetadatas != null && sharedImageMetadatas.length > 0)
        {
            for (int i = 0; i < sharedImageMetadatas.length; i++)
            {
                SharedImageMetadata sharedimagemetadata = sharedImageMetadatas[i];
                if (sharedimagemetadata != null)
                {
                    codedoutputbytebuffernano.writeMessage(1, sharedimagemetadata);
                }
            }

        }
        if (resizedByType != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(2, resizedByType);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
