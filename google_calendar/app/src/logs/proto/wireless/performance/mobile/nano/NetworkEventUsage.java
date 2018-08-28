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
//            SubRequestData, PrimesScenario, AndroidProcessStats, NetworkConnectionInfo, 
//            MetricExtension, RpcStats

public final class NetworkEventUsage extends ExtendableMessageNano
{

    private static volatile NetworkEventUsage _emptyArray[];
    private String contentType;
    private String domainPath;
    public long hashedRpcPath[];
    private int httpMethod;
    private Integer httpStatusCode;
    private MetricExtension metricExtension;
    private NetworkConnectionInfo networkConnectionInfo;
    private PrimesScenario primesScenario[];
    private AndroidProcessStats processStats;
    private Integer quicDetailedErrorCode;
    private int requestFailedReason;
    private int requestNegotiatedProtocol;
    private String requestPath;
    private Integer requestSizeBytes;
    private int requestStatus;
    private Integer responseSizeBytes;
    private Integer retryCount;
    public String rpcPath;
    private RpcStats rpcStats;
    private Long startTimeMs;
    private SubRequestData subRequest[];
    private Integer timeToResponseDataFinishMs;
    private Integer timeToResponseHeaderMs;

    public NetworkEventUsage()
    {
        contentType = null;
        requestPath = null;
        timeToResponseDataFinishMs = null;
        timeToResponseHeaderMs = null;
        httpStatusCode = null;
        responseSizeBytes = null;
        requestSizeBytes = null;
        requestNegotiatedProtocol = 0x80000000;
        subRequest = SubRequestData.emptyArray();
        processStats = null;
        networkConnectionInfo = null;
        metricExtension = null;
        startTimeMs = null;
        requestStatus = 0x80000000;
        requestFailedReason = 0x80000000;
        quicDetailedErrorCode = null;
        retryCount = null;
        rpcPath = null;
        hashedRpcPath = WireFormatNano.EMPTY_LONG_ARRAY;
        httpMethod = 0x80000000;
        domainPath = null;
        rpcStats = null;
        primesScenario = PrimesScenario.emptyArray();
        cachedSize = -1;
    }

    public static NetworkEventUsage[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new NetworkEventUsage[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final NetworkEventUsage mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L28:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 25: default 216
    //                   0: 225
    //                   10: 227
    //                   18: 238
    //                   24: 249
    //                   32: 263
    //                   40: 277
    //                   48: 291
    //                   56: 305
    //                   64: 319
    //                   74: 412
    //                   82: 536
    //                   90: 565
    //                   98: 594
    //                   104: 623
    //                   112: 637
    //                   120: 729
    //                   128: 822
    //                   138: 836
    //                   144: 847
    //                   152: 940
    //                   162: 954
    //                   169: 965
    //                   170: 1067
    //                   178: 1173
    //                   186: 1202;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        contentType = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L4:
        requestPath = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L5:
        timeToResponseDataFinishMs = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L6:
        timeToResponseHeaderMs = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L7:
        httpStatusCode = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L8:
        responseSizeBytes = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L9:
        requestSizeBytes = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L10:
        int i1;
        int i2;
        i1 = codedinputbytebuffernano.bufferPos;
        i2 = codedinputbytebuffernano.bufferStart;
        int k2 = codedinputbytebuffernano.readRawVarint32();
        if (k2 >= 0 && k2 <= 6)
        {
            try
            {
                requestNegotiatedProtocol = k2;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(i1 - i2, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(57)).append(k2).append(" is not a valid enum RequestNegotiatedProtocol").toString());
_L11:
        i1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 74);
        SubRequestData asubrequestdata[];
        if (subRequest == null)
        {
            i = 0;
        } else
        {
            i = subRequest.length;
        }
        asubrequestdata = new SubRequestData[i1 + i];
        i1 = i;
        if (i != 0)
        {
            System.arraycopy(subRequest, 0, asubrequestdata, 0, i);
            i1 = i;
        }
        for (; i1 < asubrequestdata.length - 1; i1++)
        {
            asubrequestdata[i1] = new SubRequestData();
            codedinputbytebuffernano.readMessage(asubrequestdata[i1]);
            codedinputbytebuffernano.readTag();
        }

        asubrequestdata[i1] = new SubRequestData();
        codedinputbytebuffernano.readMessage(asubrequestdata[i1]);
        subRequest = asubrequestdata;
        continue; /* Loop/switch isn't completed */
_L12:
        if (processStats == null)
        {
            processStats = new AndroidProcessStats();
        }
        codedinputbytebuffernano.readMessage(processStats);
        continue; /* Loop/switch isn't completed */
_L13:
        if (networkConnectionInfo == null)
        {
            networkConnectionInfo = new NetworkConnectionInfo();
        }
        codedinputbytebuffernano.readMessage(networkConnectionInfo);
        continue; /* Loop/switch isn't completed */
_L14:
        if (metricExtension == null)
        {
            metricExtension = new MetricExtension();
        }
        codedinputbytebuffernano.readMessage(metricExtension);
        continue; /* Loop/switch isn't completed */
_L15:
        startTimeMs = Long.valueOf(codedinputbytebuffernano.readInt64());
        continue; /* Loop/switch isn't completed */
_L16:
        i1 = codedinputbytebuffernano.bufferPos;
        i2 = codedinputbytebuffernano.bufferStart;
        k2 = codedinputbytebuffernano.readRawVarint32();
        if (k2 >= 0 && k2 <= 3)
        {
            try
            {
                requestStatus = k2;
            }
            catch (IllegalArgumentException illegalargumentexception1)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(i1 - i2, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(45)).append(k2).append(" is not a valid enum RequestStatus").toString());
_L17:
        i1 = codedinputbytebuffernano.bufferPos;
        i2 = codedinputbytebuffernano.bufferStart;
        k2 = codedinputbytebuffernano.readRawVarint32();
        if (k2 >= 0 && k2 <= 12)
        {
            try
            {
                requestFailedReason = k2;
            }
            catch (IllegalArgumentException illegalargumentexception2)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(i1 - i2, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(51)).append(k2).append(" is not a valid enum RequestFailedReason").toString());
_L18:
        retryCount = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L19:
        rpcPath = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L20:
        i1 = codedinputbytebuffernano.bufferPos;
        i2 = codedinputbytebuffernano.bufferStart;
        k2 = codedinputbytebuffernano.readRawVarint32();
        if (k2 >= 0 && k2 <= 7)
        {
            try
            {
                httpMethod = k2;
            }
            catch (IllegalArgumentException illegalargumentexception3)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(i1 - i2, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(42)).append(k2).append(" is not a valid enum HttpMethod").toString());
_L21:
        quicDetailedErrorCode = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L22:
        domainPath = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L23:
        int j1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 169);
        long al[];
        int j;
        if (hashedRpcPath == null)
        {
            j = 0;
        } else
        {
            j = hashedRpcPath.length;
        }
        al = new long[j1 + j];
        j1 = j;
        if (j != 0)
        {
            System.arraycopy(hashedRpcPath, 0, al, 0, j);
            j1 = j;
        }
        for (; j1 < al.length - 1; j1++)
        {
            al[j1] = codedinputbytebuffernano.readRawLittleEndian64();
            codedinputbytebuffernano.readTag();
        }

        al[j1] = codedinputbytebuffernano.readRawLittleEndian64();
        hashedRpcPath = al;
        continue; /* Loop/switch isn't completed */
_L24:
        int k = codedinputbytebuffernano.readRawVarint32();
        int j2 = codedinputbytebuffernano.pushLimit(k);
        int k1 = k / 8;
        long al1[];
        if (hashedRpcPath == null)
        {
            k = 0;
        } else
        {
            k = hashedRpcPath.length;
        }
        al1 = new long[k1 + k];
        k1 = k;
        if (k != 0)
        {
            System.arraycopy(hashedRpcPath, 0, al1, 0, k);
            k1 = k;
        }
        for (; k1 < al1.length; k1++)
        {
            al1[k1] = codedinputbytebuffernano.readRawLittleEndian64();
        }

        hashedRpcPath = al1;
        codedinputbytebuffernano.currentLimit = j2;
        codedinputbytebuffernano.recomputeBufferSizeAfterLimit();
        continue; /* Loop/switch isn't completed */
_L25:
        if (rpcStats == null)
        {
            rpcStats = new RpcStats();
        }
        codedinputbytebuffernano.readMessage(rpcStats);
        continue; /* Loop/switch isn't completed */
_L26:
        int l1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 186);
        PrimesScenario aprimesscenario[];
        int l;
        if (primesScenario == null)
        {
            l = 0;
        } else
        {
            l = primesScenario.length;
        }
        aprimesscenario = new PrimesScenario[l1 + l];
        l1 = l;
        if (l != 0)
        {
            System.arraycopy(primesScenario, 0, aprimesscenario, 0, l);
            l1 = l;
        }
        for (; l1 < aprimesscenario.length - 1; l1++)
        {
            aprimesscenario[l1] = new PrimesScenario();
            codedinputbytebuffernano.readMessage(aprimesscenario[l1]);
            codedinputbytebuffernano.readTag();
        }

        aprimesscenario[l1] = new PrimesScenario();
        codedinputbytebuffernano.readMessage(aprimesscenario[l1]);
        primesScenario = aprimesscenario;
        if (true) goto _L28; else goto _L27
_L27:
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int j = super.computeSerializedSize();
        int i = j;
        if (contentType != null)
        {
            String s = contentType;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int l = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + i);
        }
        j = i;
        if (requestPath != null)
        {
            String s1 = requestPath;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            int i1 = CodedOutputByteBufferNano.encodedLength(s1);
            j = i + (i1 + CodedOutputByteBufferNano.computeRawVarint32Size(i1) + j);
        }
        i = j;
        if (timeToResponseDataFinishMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(3, timeToResponseDataFinishMs.intValue());
        }
        j = i;
        if (timeToResponseHeaderMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(4, timeToResponseHeaderMs.intValue());
        }
        i = j;
        if (httpStatusCode != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(5, httpStatusCode.intValue());
        }
        j = i;
        if (responseSizeBytes != null)
        {
            j = responseSizeBytes.intValue();
            int j1 = CodedOutputByteBufferNano.computeRawVarint32Size(48);
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(j) + j1);
        }
        int k1 = j;
        if (requestSizeBytes != null)
        {
            i = requestSizeBytes.intValue();
            k1 = CodedOutputByteBufferNano.computeRawVarint32Size(56);
            k1 = j + (CodedOutputByteBufferNano.computeRawVarint32Size(i) + k1);
        }
        i = k1;
        if (requestNegotiatedProtocol != 0x80000000)
        {
            i = k1 + CodedOutputByteBufferNano.computeInt32Size(8, requestNegotiatedProtocol);
        }
        j = i;
        if (subRequest != null)
        {
            j = i;
            if (subRequest.length > 0)
            {
                for (j = 0; j < subRequest.length;)
                {
                    SubRequestData subrequestdata = subRequest[j];
                    k1 = i;
                    if (subrequestdata != null)
                    {
                        k1 = i + CodedOutputByteBufferNano.computeMessageSize(9, subrequestdata);
                    }
                    j++;
                    i = k1;
                }

                j = i;
            }
        }
        i = j;
        if (processStats != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(10, processStats);
        }
        j = i;
        if (networkConnectionInfo != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(11, networkConnectionInfo);
        }
        i = j;
        if (metricExtension != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(12, metricExtension);
        }
        j = i;
        if (startTimeMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(13, startTimeMs.longValue());
        }
        i = j;
        if (requestStatus != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(14, requestStatus);
        }
        j = i;
        if (requestFailedReason != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(15, requestFailedReason);
        }
        i = j;
        if (retryCount != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(16, retryCount.intValue());
        }
        j = i;
        if (rpcPath != null)
        {
            String s2 = rpcPath;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(136);
            k1 = CodedOutputByteBufferNano.encodedLength(s2);
            j = i + (k1 + CodedOutputByteBufferNano.computeRawVarint32Size(k1) + j);
        }
        k1 = j;
        if (httpMethod != 0x80000000)
        {
            k1 = j + CodedOutputByteBufferNano.computeInt32Size(18, httpMethod);
        }
        i = k1;
        if (quicDetailedErrorCode != null)
        {
            i = k1 + CodedOutputByteBufferNano.computeInt32Size(19, quicDetailedErrorCode.intValue());
        }
        j = i;
        if (domainPath != null)
        {
            String s3 = domainPath;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(160);
            k1 = CodedOutputByteBufferNano.encodedLength(s3);
            j = i + (k1 + CodedOutputByteBufferNano.computeRawVarint32Size(k1) + j);
        }
        k1 = j;
        if (hashedRpcPath != null)
        {
            k1 = j;
            if (hashedRpcPath.length > 0)
            {
                k1 = j + hashedRpcPath.length * 8 + hashedRpcPath.length * 2;
            }
        }
        i = k1;
        if (rpcStats != null)
        {
            i = k1 + CodedOutputByteBufferNano.computeMessageSize(22, rpcStats);
        }
        k1 = i;
        if (primesScenario != null)
        {
            k1 = i;
            if (primesScenario.length > 0)
            {
                int k = ((flag) ? 1 : 0);
                do
                {
                    k1 = i;
                    if (k >= primesScenario.length)
                    {
                        break;
                    }
                    PrimesScenario primesscenario = primesScenario[k];
                    k1 = i;
                    if (primesscenario != null)
                    {
                        k1 = i + CodedOutputByteBufferNano.computeMessageSize(23, primesscenario);
                    }
                    k++;
                    i = k1;
                } while (true);
            }
        }
        return k1;
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        return mergeFrom(codedinputbytebuffernano);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        boolean flag = false;
        if (contentType != null)
        {
            codedoutputbytebuffernano.writeString(1, contentType);
        }
        if (requestPath != null)
        {
            codedoutputbytebuffernano.writeString(2, requestPath);
        }
        if (timeToResponseDataFinishMs != null)
        {
            codedoutputbytebuffernano.writeInt32(3, timeToResponseDataFinishMs.intValue());
        }
        if (timeToResponseHeaderMs != null)
        {
            codedoutputbytebuffernano.writeInt32(4, timeToResponseHeaderMs.intValue());
        }
        if (httpStatusCode != null)
        {
            codedoutputbytebuffernano.writeInt32(5, httpStatusCode.intValue());
        }
        if (responseSizeBytes != null)
        {
            codedoutputbytebuffernano.writeUInt32(6, responseSizeBytes.intValue());
        }
        if (requestSizeBytes != null)
        {
            codedoutputbytebuffernano.writeUInt32(7, requestSizeBytes.intValue());
        }
        if (requestNegotiatedProtocol != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(8, requestNegotiatedProtocol);
        }
        if (subRequest != null && subRequest.length > 0)
        {
            for (int i = 0; i < subRequest.length; i++)
            {
                SubRequestData subrequestdata = subRequest[i];
                if (subrequestdata != null)
                {
                    codedoutputbytebuffernano.writeMessage(9, subrequestdata);
                }
            }

        }
        if (processStats != null)
        {
            codedoutputbytebuffernano.writeMessage(10, processStats);
        }
        if (networkConnectionInfo != null)
        {
            codedoutputbytebuffernano.writeMessage(11, networkConnectionInfo);
        }
        if (metricExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(12, metricExtension);
        }
        if (startTimeMs != null)
        {
            codedoutputbytebuffernano.writeInt64(13, startTimeMs.longValue());
        }
        if (requestStatus != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(14, requestStatus);
        }
        if (requestFailedReason != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(15, requestFailedReason);
        }
        if (retryCount != null)
        {
            codedoutputbytebuffernano.writeInt32(16, retryCount.intValue());
        }
        if (rpcPath != null)
        {
            codedoutputbytebuffernano.writeString(17, rpcPath);
        }
        if (httpMethod != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(18, httpMethod);
        }
        if (quicDetailedErrorCode != null)
        {
            codedoutputbytebuffernano.writeInt32(19, quicDetailedErrorCode.intValue());
        }
        if (domainPath != null)
        {
            codedoutputbytebuffernano.writeString(20, domainPath);
        }
        if (hashedRpcPath != null && hashedRpcPath.length > 0)
        {
            for (int j = 0; j < hashedRpcPath.length; j++)
            {
                codedoutputbytebuffernano.writeFixed64(21, hashedRpcPath[j]);
            }

        }
        if (rpcStats != null)
        {
            codedoutputbytebuffernano.writeMessage(22, rpcStats);
        }
        if (primesScenario != null && primesScenario.length > 0)
        {
            for (int k = ((flag) ? 1 : 0); k < primesScenario.length; k++)
            {
                PrimesScenario primesscenario = primesScenario[k];
                if (primesscenario != null)
                {
                    codedoutputbytebuffernano.writeMessage(23, primesscenario);
                }
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
