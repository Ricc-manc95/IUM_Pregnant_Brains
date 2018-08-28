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

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            SharedImageTransform

public final class SharingStats extends ExtendableMessageNano
{

    private Integer numCollections;
    private Integer numItemsCollection;
    private Integer numSharingApps;
    private SharedImageTransform sharedImageTransforms[];

    public SharingStats()
    {
        numItemsCollection = null;
        sharedImageTransforms = SharedImageTransform.emptyArray();
        numSharingApps = null;
        numCollections = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (numItemsCollection != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, numItemsCollection.intValue());
        }
        j = i;
        if (sharedImageTransforms != null)
        {
            j = i;
            if (sharedImageTransforms.length > 0)
            {
                for (j = 0; j < sharedImageTransforms.length;)
                {
                    SharedImageTransform sharedimagetransform = sharedImageTransforms[j];
                    int k = i;
                    if (sharedimagetransform != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(2, sharedimagetransform);
                    }
                    j++;
                    i = k;
                }

                j = i;
            }
        }
        i = j;
        if (numSharingApps != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(3, numSharingApps.intValue());
        }
        j = i;
        if (numCollections != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(4, numCollections.intValue());
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
                numItemsCollection = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 18: // '\022'
                int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 18);
                SharedImageTransform asharedimagetransform[];
                int j;
                if (sharedImageTransforms == null)
                {
                    j = 0;
                } else
                {
                    j = sharedImageTransforms.length;
                }
                asharedimagetransform = new SharedImageTransform[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(sharedImageTransforms, 0, asharedimagetransform, 0, j);
                    k = j;
                }
                for (; k < asharedimagetransform.length - 1; k++)
                {
                    asharedimagetransform[k] = new SharedImageTransform();
                    codedinputbytebuffernano.readMessage(asharedimagetransform[k]);
                    codedinputbytebuffernano.readTag();
                }

                asharedimagetransform[k] = new SharedImageTransform();
                codedinputbytebuffernano.readMessage(asharedimagetransform[k]);
                sharedImageTransforms = asharedimagetransform;
                break;

            case 24: // '\030'
                numSharingApps = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 32: // ' '
                numCollections = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (numItemsCollection != null)
        {
            codedoutputbytebuffernano.writeInt32(1, numItemsCollection.intValue());
        }
        if (sharedImageTransforms != null && sharedImageTransforms.length > 0)
        {
            for (int i = 0; i < sharedImageTransforms.length; i++)
            {
                SharedImageTransform sharedimagetransform = sharedImageTransforms[i];
                if (sharedimagetransform != null)
                {
                    codedoutputbytebuffernano.writeMessage(2, sharedimagetransform);
                }
            }

        }
        if (numSharingApps != null)
        {
            codedoutputbytebuffernano.writeInt32(3, numSharingApps.intValue());
        }
        if (numCollections != null)
        {
            codedoutputbytebuffernano.writeInt32(4, numCollections.intValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
