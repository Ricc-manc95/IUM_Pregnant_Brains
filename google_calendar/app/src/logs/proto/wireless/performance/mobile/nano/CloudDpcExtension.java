// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class CloudDpcExtension extends ExtendableMessageNano
{

    private logs.proto.wireless.performance.mobile.ExtensionCloudDpc.AppInstallMetric appInstallMetric;
    private String emmId;
    private String eventName;
    private int eventState;
    private wireless.android.work.clouddpc.performance.schema.CommonEnums.MetricType metricType[];
    private int mitigation;
    private int provisionEntryPoint;
    private int provisionMode;
    private logs.proto.wireless.performance.mobile.ExtensionCloudDpc.TaskFailMetric taskFailMetric;

    public CloudDpcExtension()
    {
        metricType = new wireless.android.work.clouddpc.performance.schema.CommonEnums.MetricType[0];
        provisionMode = 0x80000000;
        eventName = null;
        eventState = 0x80000000;
        mitigation = 0x80000000;
        emmId = null;
        provisionEntryPoint = 0x80000000;
        cachedSize = -1;
    }

    private final CloudDpcExtension mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L26:
        int l = codedinputbytebuffernano.readTag();
        l;
        JVM INSTR lookupswitch 11: default 108
    //                   0: 118
    //                   8: 120
    //                   10: 337
    //                   18: 636
    //                   24: 663
    //                   34: 757
    //                   40: 768
    //                   48: 861
    //                   58: 954
    //                   66: 965
    //                   72: 992;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, l))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        wireless.android.work.clouddpc.performance.schema.CommonEnums.MetricType ametrictype[];
        int i;
        int j;
        int i1;
        i1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 8);
        ametrictype = new wireless.android.work.clouddpc.performance.schema.CommonEnums.MetricType[i1];
        j = 0;
        i = 0;
_L17:
        if (j >= i1) goto _L14; else goto _L13
_L13:
        int k;
        int k1;
        int l1;
        if (j != 0)
        {
            codedinputbytebuffernano.readTag();
        }
        k = codedinputbytebuffernano.bufferPos;
        l1 = codedinputbytebuffernano.bufferStart;
        k1 = codedinputbytebuffernano.readRawVarint32();
        k1;
        JVM INSTR tableswitch 0 0: default 196
    //                   0 226;
           goto _L15 _L16
_L15:
        codedinputbytebuffernano.rewindToPositionAndTag(k - l1, codedinputbytebuffernano.lastTag);
        storeUnknownField(codedinputbytebuffernano, l);
_L18:
        j++;
          goto _L17
_L16:
        k = i + 1;
        ametrictype[i] = wireless.android.work.clouddpc.performance.schema.CommonEnums.MetricType.forNumber(k1);
        i = k;
          goto _L18
_L14:
        if (i != 0)
        {
            if (metricType == null)
            {
                j = 0;
            } else
            {
                j = metricType.length;
            }
            if (j == 0 && i == ametrictype.length)
            {
                metricType = ametrictype;
            } else
            {
                wireless.android.work.clouddpc.performance.schema.CommonEnums.MetricType ametrictype1[] = new wireless.android.work.clouddpc.performance.schema.CommonEnums.MetricType[j + i];
                if (j != 0)
                {
                    System.arraycopy(metricType, 0, ametrictype1, 0, j);
                }
                System.arraycopy(ametrictype, 0, ametrictype1, j, i);
                metricType = ametrictype1;
            }
        }
        continue; /* Loop/switch isn't completed */
          goto _L17
_L4:
        k = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
        l = codedinputbytebuffernano.bufferPos;
        i1 = codedinputbytebuffernano.bufferStart;
        j = 0;
_L24:
        if (codedinputbytebuffernano.currentLimit == 0x7fffffff)
        {
            i = -1;
        } else
        {
            i = codedinputbytebuffernano.bufferPos;
            i = codedinputbytebuffernano.currentLimit - i;
        }
        if (i > 0)
        {
            switch (codedinputbytebuffernano.readRawVarint32())
            {
            case 0: // '\0'
                j++;
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (j == 0) goto _L20; else goto _L19
_L19:
        codedinputbytebuffernano.rewindToPositionAndTag(l - i1, codedinputbytebuffernano.lastTag);
        if (metricType == null)
        {
            i = 0;
        } else
        {
            i = metricType.length;
        }
        ametrictype = new wireless.android.work.clouddpc.performance.schema.CommonEnums.MetricType[j + i];
        j = i;
        if (i != 0)
        {
            System.arraycopy(metricType, 0, ametrictype, 0, i);
            j = i;
        }
_L22:
        if (codedinputbytebuffernano.currentLimit == 0x7fffffff)
        {
            i = -1;
        } else
        {
            i = codedinputbytebuffernano.bufferPos;
            i = codedinputbytebuffernano.currentLimit - i;
        }
        if (i > 0)
        {
            i = codedinputbytebuffernano.bufferPos;
            l = codedinputbytebuffernano.bufferStart;
            int j1 = codedinputbytebuffernano.readRawVarint32();
            switch (j1)
            {
            default:
                codedinputbytebuffernano.rewindToPositionAndTag(i - l, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, 8);
                break;

            case 0: // '\0'
                ametrictype[j] = wireless.android.work.clouddpc.performance.schema.CommonEnums.MetricType.forNumber(j1);
                j++;
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
        metricType = ametrictype;
          goto _L20
        if (true) goto _L22; else goto _L21
_L21:
        if (true) goto _L24; else goto _L23
_L23:
_L20:
        codedinputbytebuffernano.currentLimit = k;
        codedinputbytebuffernano.recomputeBufferSizeAfterLimit();
        continue; /* Loop/switch isn't completed */
_L5:
        appInstallMetric = (logs.proto.wireless.performance.mobile.ExtensionCloudDpc.AppInstallMetric)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.ExtensionCloudDpc.AppInstallMetric.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
        continue; /* Loop/switch isn't completed */
_L6:
        i = codedinputbytebuffernano.bufferPos;
        j = codedinputbytebuffernano.bufferStart;
        k = codedinputbytebuffernano.readRawVarint32();
        if (k >= 0 && k <= 11)
        {
            try
            {
                provisionMode = k;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(i - j, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, l);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(45)).append(k).append(" is not a valid enum ProvisionMode").toString());
_L7:
        eventName = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L8:
        i = codedinputbytebuffernano.bufferPos;
        j = codedinputbytebuffernano.bufferStart;
        k = codedinputbytebuffernano.readRawVarint32();
        if (k >= 0 && k <= 3)
        {
            try
            {
                eventState = k;
            }
            catch (IllegalArgumentException illegalargumentexception1)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(i - j, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, l);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(42)).append(k).append(" is not a valid enum EventState").toString());
_L9:
        i = codedinputbytebuffernano.bufferPos;
        j = codedinputbytebuffernano.bufferStart;
        k = codedinputbytebuffernano.readRawVarint32();
        if (k >= 0 && k <= 2)
        {
            try
            {
                mitigation = k;
            }
            catch (IllegalArgumentException illegalargumentexception2)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(i - j, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, l);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(42)).append(k).append(" is not a valid enum Mitigation").toString());
_L10:
        emmId = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L11:
        taskFailMetric = (logs.proto.wireless.performance.mobile.ExtensionCloudDpc.TaskFailMetric)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.ExtensionCloudDpc.TaskFailMetric.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
        continue; /* Loop/switch isn't completed */
_L12:
        i = codedinputbytebuffernano.bufferPos;
        j = codedinputbytebuffernano.bufferStart;
        k = codedinputbytebuffernano.readRawVarint32();
        if (k < 0 || k > 7)
        {
            break; /* Loop/switch isn't completed */
        }
        try
        {
            provisionEntryPoint = k;
        }
        catch (IllegalArgumentException illegalargumentexception3)
        {
            codedinputbytebuffernano.rewindToPositionAndTag(i - j, codedinputbytebuffernano.lastTag);
            storeUnknownField(codedinputbytebuffernano, l);
        }
        if (true) goto _L26; else goto _L25
_L25:
        throw new IllegalArgumentException((new StringBuilder(51)).append(k).append(" is not a valid enum ProvisionEntryPoint").toString());
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int l1 = super.computeSerializedSize();
        int k;
        if (metricType != null && metricType.length > 0)
        {
            int i = 0;
            k = 0;
            while (i < metricType.length) 
            {
                wireless.android.work.clouddpc.performance.schema.CommonEnums.MetricType metrictype = metricType[i];
                int l = k;
                if (metrictype != null)
                {
                    l = metrictype.value;
                    if (l >= 0)
                    {
                        l = CodedOutputByteBufferNano.computeRawVarint32Size(l);
                    } else
                    {
                        l = 10;
                    }
                    l = k + l;
                }
                i++;
                k = l;
            }
            i = l1 + k;
            int i1 = ((flag) ? 1 : 0);
            do
            {
                k = i;
                if (i1 >= metricType.length)
                {
                    break;
                }
                k = i;
                if (metricType[i1] != null)
                {
                    k = i + 1;
                }
                i1++;
                i = k;
            } while (true);
        } else
        {
            k = l1;
        }
        int j1 = k;
        if (appInstallMetric != null)
        {
            j1 = k + CodedOutputStream.computeMessageSize(2, appInstallMetric);
        }
        int j = j1;
        if (provisionMode != 0x80000000)
        {
            j = j1 + CodedOutputByteBufferNano.computeInt32Size(3, provisionMode);
        }
        k = j;
        if (eventName != null)
        {
            String s = eventName;
            k = CodedOutputByteBufferNano.computeRawVarint32Size(32);
            j1 = CodedOutputByteBufferNano.encodedLength(s);
            k = j + (j1 + CodedOutputByteBufferNano.computeRawVarint32Size(j1) + k);
        }
        j1 = k;
        if (eventState != 0x80000000)
        {
            j1 = k + CodedOutputByteBufferNano.computeInt32Size(5, eventState);
        }
        j = j1;
        if (mitigation != 0x80000000)
        {
            j = j1 + CodedOutputByteBufferNano.computeInt32Size(6, mitigation);
        }
        k = j;
        if (emmId != null)
        {
            String s1 = emmId;
            k = CodedOutputByteBufferNano.computeRawVarint32Size(56);
            int k1 = CodedOutputByteBufferNano.encodedLength(s1);
            k = j + (k1 + CodedOutputByteBufferNano.computeRawVarint32Size(k1) + k);
        }
        j = k;
        if (taskFailMetric != null)
        {
            j = k + CodedOutputStream.computeMessageSize(8, taskFailMetric);
        }
        k = j;
        if (provisionEntryPoint != 0x80000000)
        {
            k = j + CodedOutputByteBufferNano.computeInt32Size(9, provisionEntryPoint);
        }
        return k;
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        return mergeFrom(codedinputbytebuffernano);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (metricType != null && metricType.length > 0)
        {
            for (int i = 0; i < metricType.length; i++)
            {
                if (metricType[i] != null)
                {
                    codedoutputbytebuffernano.writeInt32(1, metricType[i].value);
                }
            }

        }
        if (appInstallMetric != null)
        {
            logs.proto.wireless.performance.mobile.ExtensionCloudDpc.AppInstallMetric appinstallmetric = appInstallMetric;
            CodedOutputStream codedoutputstream = codedoutputbytebuffernano.getCodedOutputStream();
            codedoutputstream.writeMessage(2, appinstallmetric);
            codedoutputstream.flush();
            codedoutputbytebuffernano.codedOutputStreamPosition = codedoutputbytebuffernano.buffer.position();
        }
        if (provisionMode != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(3, provisionMode);
        }
        if (eventName != null)
        {
            codedoutputbytebuffernano.writeString(4, eventName);
        }
        if (eventState != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(5, eventState);
        }
        if (mitigation != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(6, mitigation);
        }
        if (emmId != null)
        {
            codedoutputbytebuffernano.writeString(7, emmId);
        }
        if (taskFailMetric != null)
        {
            logs.proto.wireless.performance.mobile.ExtensionCloudDpc.TaskFailMetric taskfailmetric = taskFailMetric;
            CodedOutputStream codedoutputstream1 = codedoutputbytebuffernano.getCodedOutputStream();
            codedoutputstream1.writeMessage(8, taskfailmetric);
            codedoutputstream1.flush();
            codedoutputbytebuffernano.codedOutputStreamPosition = codedoutputbytebuffernano.buffer.position();
        }
        if (provisionEntryPoint != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(9, provisionEntryPoint);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
