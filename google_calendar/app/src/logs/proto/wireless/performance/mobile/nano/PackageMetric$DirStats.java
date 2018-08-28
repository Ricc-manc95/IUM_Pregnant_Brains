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

public final class cachedSize extends ExtendableMessageNano
{

    private static volatile hashedDirPath _emptyArray[];
    public String dirPath;
    public long hashedDirPath[];
    public Long sizeBytes;

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

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (dirPath != null)
        {
            String s = dirPath;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
        }
        j = i;
        if (sizeBytes != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(2, sizeBytes.longValue());
        }
        i = j;
        if (hashedDirPath != null)
        {
            i = j;
            if (hashedDirPath.length > 0)
            {
                i = j + hashedDirPath.length * 8 + hashedDirPath.length * 1;
            }
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
                dirPath = codedinputbytebuffernano.readString();
                break;

            case 16: // '\020'
                sizeBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 25: // '\031'
                int l = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 25);
                long al[];
                int j;
                if (hashedDirPath == null)
                {
                    j = 0;
                } else
                {
                    j = hashedDirPath.length;
                }
                al = new long[l + j];
                l = j;
                if (j != 0)
                {
                    System.arraycopy(hashedDirPath, 0, al, 0, j);
                    l = j;
                }
                for (; l < al.length - 1; l++)
                {
                    al[l] = codedinputbytebuffernano.readRawLittleEndian64();
                    codedinputbytebuffernano.readTag();
                }

                al[l] = codedinputbytebuffernano.readRawLittleEndian64();
                hashedDirPath = al;
                break;

            case 26: // '\032'
                int k = codedinputbytebuffernano.readRawVarint32();
                int j1 = codedinputbytebuffernano.pushLimit(k);
                int i1 = k / 8;
                long al1[];
                if (hashedDirPath == null)
                {
                    k = 0;
                } else
                {
                    k = hashedDirPath.length;
                }
                al1 = new long[i1 + k];
                i1 = k;
                if (k != 0)
                {
                    System.arraycopy(hashedDirPath, 0, al1, 0, k);
                    i1 = k;
                }
                for (; i1 < al1.length; i1++)
                {
                    al1[i1] = codedinputbytebuffernano.readRawLittleEndian64();
                }

                hashedDirPath = al1;
                codedinputbytebuffernano.currentLimit = j1;
                codedinputbytebuffernano.recomputeBufferSizeAfterLimit();
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (dirPath != null)
        {
            codedoutputbytebuffernano.writeString(1, dirPath);
        }
        if (sizeBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(2, sizeBytes.longValue());
        }
        if (hashedDirPath != null && hashedDirPath.length > 0)
        {
            for (int i = 0; i < hashedDirPath.length; i++)
            {
                codedoutputbytebuffernano.writeFixed64(3, hashedDirPath[i]);
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ()
    {
        dirPath = null;
        hashedDirPath = WireFormatNano.EMPTY_LONG_ARRAY;
        sizeBytes = null;
        cachedSize = -1;
    }
}
