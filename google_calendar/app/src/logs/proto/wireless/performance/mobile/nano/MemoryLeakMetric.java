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
//            ObjectInfo

public final class MemoryLeakMetric extends ExtendableMessageNano
{

    public ObjectInfo objectInfo[];

    public MemoryLeakMetric()
    {
        objectInfo = ObjectInfo.emptyArray();
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int i = super.computeSerializedSize();
        int k = i;
        if (objectInfo != null)
        {
            k = i;
            if (objectInfo.length > 0)
            {
                int j = 0;
                do
                {
                    k = i;
                    if (j >= objectInfo.length)
                    {
                        break;
                    }
                    ObjectInfo objectinfo = objectInfo[j];
                    k = i;
                    if (objectinfo != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(1, objectinfo);
                    }
                    j++;
                    i = k;
                } while (true);
            }
        }
        return k;
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
                ObjectInfo aobjectinfo[];
                int j;
                if (objectInfo == null)
                {
                    j = 0;
                } else
                {
                    j = objectInfo.length;
                }
                aobjectinfo = new ObjectInfo[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(objectInfo, 0, aobjectinfo, 0, j);
                    k = j;
                }
                for (; k < aobjectinfo.length - 1; k++)
                {
                    aobjectinfo[k] = new ObjectInfo();
                    codedinputbytebuffernano.readMessage(aobjectinfo[k]);
                    codedinputbytebuffernano.readTag();
                }

                aobjectinfo[k] = new ObjectInfo();
                codedinputbytebuffernano.readMessage(aobjectinfo[k]);
                objectInfo = aobjectinfo;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (objectInfo != null && objectInfo.length > 0)
        {
            for (int i = 0; i < objectInfo.length; i++)
            {
                ObjectInfo objectinfo = objectInfo[i];
                if (objectinfo != null)
                {
                    codedoutputbytebuffernano.writeMessage(1, objectinfo);
                }
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
