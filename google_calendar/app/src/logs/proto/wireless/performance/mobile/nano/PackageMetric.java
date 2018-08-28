// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class PackageMetric extends ExtendableMessageNano
{

    public Long cacheSize;
    public Long codeSize;
    private Long dataPartitionSizeBytes;
    public Long dataSize;
    public DirStats dirStats[];
    public Long externalCacheSize;
    public Long externalCodeSize;
    public Long externalDataSize;
    public Long externalMediaSize;
    public Long externalObbSize;
    private String packageName;

    public PackageMetric()
    {
        cacheSize = null;
        codeSize = null;
        dataSize = null;
        externalCacheSize = null;
        externalCodeSize = null;
        externalDataSize = null;
        externalMediaSize = null;
        externalObbSize = null;
        packageName = null;
        dirStats = DirStats.emptyArray();
        dataPartitionSizeBytes = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (cacheSize != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(1, cacheSize.longValue());
        }
        j = i;
        if (codeSize != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(2, codeSize.longValue());
        }
        i = j;
        if (dataSize != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(3, dataSize.longValue());
        }
        j = i;
        if (externalCacheSize != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(4, externalCacheSize.longValue());
        }
        i = j;
        if (externalCodeSize != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(5, externalCodeSize.longValue());
        }
        j = i;
        if (externalDataSize != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(6, externalDataSize.longValue());
        }
        i = j;
        if (externalMediaSize != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(7, externalMediaSize.longValue());
        }
        j = i;
        if (externalObbSize != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(8, externalObbSize.longValue());
        }
        i = j;
        if (packageName != null)
        {
            String s = packageName;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(72);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
        }
        j = i;
        if (dirStats != null)
        {
            j = i;
            if (dirStats.length > 0)
            {
                for (j = 0; j < dirStats.length;)
                {
                    DirStats dirstats = dirStats[j];
                    int l = i;
                    if (dirstats != null)
                    {
                        l = i + CodedOutputByteBufferNano.computeMessageSize(10, dirstats);
                    }
                    j++;
                    i = l;
                }

                j = i;
            }
        }
        i = j;
        if (dataPartitionSizeBytes != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(11, dataPartitionSizeBytes.longValue());
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
                cacheSize = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 16: // '\020'
                codeSize = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 24: // '\030'
                dataSize = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 32: // ' '
                externalCacheSize = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 40: // '('
                externalCodeSize = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 48: // '0'
                externalDataSize = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 56: // '8'
                externalMediaSize = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 64: // '@'
                externalObbSize = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 74: // 'J'
                packageName = codedinputbytebuffernano.readString();
                break;

            case 82: // 'R'
                int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 82);
                DirStats adirstats[];
                int j;
                if (dirStats == null)
                {
                    j = 0;
                } else
                {
                    j = dirStats.length;
                }
                adirstats = new DirStats[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(dirStats, 0, adirstats, 0, j);
                    k = j;
                }
                for (; k < adirstats.length - 1; k++)
                {
                    adirstats[k] = new DirStats();
                    codedinputbytebuffernano.readMessage(adirstats[k]);
                    codedinputbytebuffernano.readTag();
                }

                adirstats[k] = new DirStats();
                codedinputbytebuffernano.readMessage(adirstats[k]);
                dirStats = adirstats;
                break;

            case 88: // 'X'
                dataPartitionSizeBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (cacheSize != null)
        {
            codedoutputbytebuffernano.writeInt64(1, cacheSize.longValue());
        }
        if (codeSize != null)
        {
            codedoutputbytebuffernano.writeInt64(2, codeSize.longValue());
        }
        if (dataSize != null)
        {
            codedoutputbytebuffernano.writeInt64(3, dataSize.longValue());
        }
        if (externalCacheSize != null)
        {
            codedoutputbytebuffernano.writeInt64(4, externalCacheSize.longValue());
        }
        if (externalCodeSize != null)
        {
            codedoutputbytebuffernano.writeInt64(5, externalCodeSize.longValue());
        }
        if (externalDataSize != null)
        {
            codedoutputbytebuffernano.writeInt64(6, externalDataSize.longValue());
        }
        if (externalMediaSize != null)
        {
            codedoutputbytebuffernano.writeInt64(7, externalMediaSize.longValue());
        }
        if (externalObbSize != null)
        {
            codedoutputbytebuffernano.writeInt64(8, externalObbSize.longValue());
        }
        if (packageName != null)
        {
            codedoutputbytebuffernano.writeString(9, packageName);
        }
        if (dirStats != null && dirStats.length > 0)
        {
            for (int i = 0; i < dirStats.length; i++)
            {
                DirStats dirstats = dirStats[i];
                if (dirstats != null)
                {
                    codedoutputbytebuffernano.writeMessage(10, dirstats);
                }
            }

        }
        if (dataPartitionSizeBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(11, dataPartitionSizeBytes.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    private class DirStats extends ExtendableMessageNano
    {

        private static volatile DirStats _emptyArray[];
        public String dirPath;
        public long hashedDirPath[];
        public Long sizeBytes;

        public static DirStats[] emptyArray()
        {
            if (_emptyArray == null)
            {
                synchronized (InternalNano.LAZY_INIT_LOCK)
                {
                    if (_emptyArray == null)
                    {
                        _emptyArray = new DirStats[0];
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

        public DirStats()
        {
            dirPath = null;
            hashedDirPath = WireFormatNano.EMPTY_LONG_ARRAY;
            sizeBytes = null;
            cachedSize = -1;
        }
    }

}
