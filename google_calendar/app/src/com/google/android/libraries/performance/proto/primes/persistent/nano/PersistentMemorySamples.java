// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.proto.primes.persistent.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

// Referenced classes of package com.google.android.libraries.performance.proto.primes.persistent.nano:
//            MemorySample

public final class PersistentMemorySamples extends ExtendableMessageNano
{

    public MemorySample samples[];
    public Integer versionNameHash;

    public PersistentMemorySamples()
    {
        samples = MemorySample.emptyArray();
        versionNameHash = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (samples != null)
        {
            i = j;
            if (samples.length > 0)
            {
                int k = 0;
                do
                {
                    i = j;
                    if (k >= samples.length)
                    {
                        break;
                    }
                    MemorySample memorysample = samples[k];
                    i = j;
                    if (memorysample != null)
                    {
                        i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
                        int i1 = memorysample.computeSerializedSize();
                        memorysample.cachedSize = i1;
                        i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(i1) + i1 + i);
                    }
                    k++;
                    j = i;
                } while (true);
            }
        }
        j = i;
        if (versionNameHash != null)
        {
            j = versionNameHash.intValue();
            int l = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            if (j >= 0)
            {
                j = CodedOutputByteBufferNano.computeRawVarint32Size(j);
            } else
            {
                j = 10;
            }
            j = i + (j + l);
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

            case 10: // '\n'
                int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 10);
                MemorySample amemorysample[];
                int j;
                if (samples == null)
                {
                    j = 0;
                } else
                {
                    j = samples.length;
                }
                amemorysample = new MemorySample[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(samples, 0, amemorysample, 0, j);
                    k = j;
                }
                for (; k < amemorysample.length - 1; k++)
                {
                    amemorysample[k] = new MemorySample();
                    codedinputbytebuffernano.readMessage(amemorysample[k]);
                    codedinputbytebuffernano.readTag();
                }

                amemorysample[k] = new MemorySample();
                codedinputbytebuffernano.readMessage(amemorysample[k]);
                samples = amemorysample;
                break;

            case 16: // '\020'
                versionNameHash = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (samples != null && samples.length > 0)
        {
            for (int i = 0; i < samples.length; i++)
            {
                MemorySample memorysample = samples[i];
                if (memorysample == null)
                {
                    continue;
                }
                codedoutputbytebuffernano.writeRawVarint32(10);
                if (((MessageNano) (memorysample)).cachedSize < 0)
                {
                    memorysample.cachedSize = memorysample.computeSerializedSize();
                }
                codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (memorysample)).cachedSize);
                memorysample.writeTo(codedoutputbytebuffernano);
            }

        }
        if (versionNameHash != null)
        {
            int j = versionNameHash.intValue();
            codedoutputbytebuffernano.writeRawVarint32(16);
            if (j >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(j);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(j);
            }
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
