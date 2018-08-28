// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.android.gmm.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class SystemHealthMetricExtension extends ExtendableMessageNano
{

    private Integer customEntryPoint;
    private Integer customExitPoint;
    private Integer customFeatureName;
    private int overlappingEvents[];
    private VEInfo vesAppearedInTheFlow[];

    public SystemHealthMetricExtension()
    {
        customEntryPoint = null;
        customExitPoint = null;
        customFeatureName = null;
        vesAppearedInTheFlow = VEInfo.emptyArray();
        overlappingEvents = WireFormatNano.EMPTY_INT_ARRAY;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int j = super.computeSerializedSize();
        int i = j;
        if (customEntryPoint != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, customEntryPoint.intValue());
        }
        j = i;
        if (customExitPoint != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, customExitPoint.intValue());
        }
        i = j;
        if (customFeatureName != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(3, customFeatureName.intValue());
        }
        j = i;
        if (vesAppearedInTheFlow != null)
        {
            j = i;
            if (vesAppearedInTheFlow.length > 0)
            {
                for (j = 0; j < vesAppearedInTheFlow.length;)
                {
                    VEInfo veinfo = vesAppearedInTheFlow[j];
                    int k = i;
                    if (veinfo != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(4, veinfo);
                    }
                    j++;
                    i = k;
                }

                j = i;
            }
        }
        i = j;
        if (overlappingEvents != null)
        {
            i = j;
            if (overlappingEvents.length > 0)
            {
                int l = 0;
                i = ((flag) ? 1 : 0);
                while (i < overlappingEvents.length) 
                {
                    int i1 = overlappingEvents[i];
                    if (i1 >= 0)
                    {
                        i1 = CodedOutputByteBufferNano.computeRawVarint32Size(i1);
                    } else
                    {
                        i1 = 10;
                    }
                    l += i1;
                    i++;
                }
                i = j + l + overlappingEvents.length * 1;
            }
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L2:
        int l1;
        int i2;
        int j2;
        int k2;
        int i = codedinputbytebuffernano.readTag();
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
            customEntryPoint = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
            continue; /* Loop/switch isn't completed */

        case 16: // '\020'
            customExitPoint = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
            continue; /* Loop/switch isn't completed */

        case 24: // '\030'
            customFeatureName = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
            continue; /* Loop/switch isn't completed */

        case 34: // '"'
            int j1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 34);
            VEInfo aveinfo[];
            int j;
            if (vesAppearedInTheFlow == null)
            {
                j = 0;
            } else
            {
                j = vesAppearedInTheFlow.length;
            }
            aveinfo = new VEInfo[j1 + j];
            j1 = j;
            if (j != 0)
            {
                System.arraycopy(vesAppearedInTheFlow, 0, aveinfo, 0, j);
                j1 = j;
            }
            for (; j1 < aveinfo.length - 1; j1++)
            {
                aveinfo[j1] = new VEInfo();
                codedinputbytebuffernano.readMessage(aveinfo[j1]);
                codedinputbytebuffernano.readTag();
            }

            aveinfo[j1] = new VEInfo();
            codedinputbytebuffernano.readMessage(aveinfo[j1]);
            vesAppearedInTheFlow = aveinfo;
            continue; /* Loop/switch isn't completed */

        case 40: // '('
            int k1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 40);
            int ai[];
            int k;
            if (overlappingEvents == null)
            {
                k = 0;
            } else
            {
                k = overlappingEvents.length;
            }
            ai = new int[k1 + k];
            k1 = k;
            if (k != 0)
            {
                System.arraycopy(overlappingEvents, 0, ai, 0, k);
                k1 = k;
            }
            for (; k1 < ai.length - 1; k1++)
            {
                ai[k1] = codedinputbytebuffernano.readRawVarint32();
                codedinputbytebuffernano.readTag();
            }

            ai[k1] = codedinputbytebuffernano.readRawVarint32();
            overlappingEvents = ai;
            continue; /* Loop/switch isn't completed */

        case 42: // '*'
            i2 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
            j2 = codedinputbytebuffernano.bufferPos;
            k2 = codedinputbytebuffernano.bufferStart;
            l1 = 0;
            break;
        }
        do
        {
label0:
            {
                int l;
                if (codedinputbytebuffernano.currentLimit == 0x7fffffff)
                {
                    l = -1;
                } else
                {
                    l = codedinputbytebuffernano.bufferPos;
                    l = codedinputbytebuffernano.currentLimit - l;
                }
                if (l <= 0)
                {
                    break label0;
                }
                codedinputbytebuffernano.readRawVarint32();
                l1++;
            }
        } while (true);
        codedinputbytebuffernano.rewindToPositionAndTag(j2 - k2, codedinputbytebuffernano.lastTag);
        int ai1[];
        int i1;
        if (overlappingEvents == null)
        {
            i1 = 0;
        } else
        {
            i1 = overlappingEvents.length;
        }
        ai1 = new int[l1 + i1];
        l1 = i1;
        if (i1 != 0)
        {
            System.arraycopy(overlappingEvents, 0, ai1, 0, i1);
            l1 = i1;
        }
        for (; l1 < ai1.length; l1++)
        {
            ai1[l1] = codedinputbytebuffernano.readRawVarint32();
        }

        overlappingEvents = ai1;
        codedinputbytebuffernano.currentLimit = i2;
        codedinputbytebuffernano.recomputeBufferSizeAfterLimit();
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        boolean flag = false;
        if (customEntryPoint != null)
        {
            codedoutputbytebuffernano.writeInt32(1, customEntryPoint.intValue());
        }
        if (customExitPoint != null)
        {
            codedoutputbytebuffernano.writeInt32(2, customExitPoint.intValue());
        }
        if (customFeatureName != null)
        {
            codedoutputbytebuffernano.writeInt32(3, customFeatureName.intValue());
        }
        if (vesAppearedInTheFlow != null && vesAppearedInTheFlow.length > 0)
        {
            for (int i = 0; i < vesAppearedInTheFlow.length; i++)
            {
                VEInfo veinfo = vesAppearedInTheFlow[i];
                if (veinfo != null)
                {
                    codedoutputbytebuffernano.writeMessage(4, veinfo);
                }
            }

        }
        if (overlappingEvents != null && overlappingEvents.length > 0)
        {
            for (int j = ((flag) ? 1 : 0); j < overlappingEvents.length; j++)
            {
                codedoutputbytebuffernano.writeInt32(5, overlappingEvents[j]);
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }

    private class VEInfo extends ExtendableMessageNano
    {

        private static volatile VEInfo _emptyArray[];
        private Integer leafVeTypeId;
        private int veAction;

        public static VEInfo[] emptyArray()
        {
            if (_emptyArray == null)
            {
                synchronized (InternalNano.LAZY_INIT_LOCK)
                {
                    if (_emptyArray == null)
                    {
                        _emptyArray = new VEInfo[0];
                    }
                }
            }
            return _emptyArray;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private final VEInfo mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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

        public VEInfo()
        {
            leafVeTypeId = null;
            veAction = 0x80000000;
            cachedSize = -1;
        }
    }

}
