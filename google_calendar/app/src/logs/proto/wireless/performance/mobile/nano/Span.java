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

public final class Span extends ExtendableMessageNano
{

    private static volatile Span _emptyArray[];
    public String constantName;
    private Integer cpuTimeMs;
    public Long durationMs;
    public Long hashedName;
    public Long id;
    public String name;
    public Long parentId;
    public int spanType;
    public Long startTimeMs;
    public Long threadId;

    public Span()
    {
        constantName = null;
        hashedName = null;
        name = null;
        id = null;
        parentId = null;
        startTimeMs = null;
        durationMs = null;
        cpuTimeMs = null;
        threadId = null;
        spanType = 0x80000000;
        cachedSize = -1;
    }

    public static Span[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new Span[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final Span mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L14:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 11: default 104
    //                   0: 113
    //                   10: 115
    //                   17: 126
    //                   25: 140
    //                   32: 154
    //                   40: 168
    //                   48: 182
    //                   56: 196
    //                   65: 288
    //                   74: 302
    //                   80: 313;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        constantName = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L4:
        id = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
        continue; /* Loop/switch isn't completed */
_L5:
        parentId = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
        continue; /* Loop/switch isn't completed */
_L6:
        startTimeMs = Long.valueOf(codedinputbytebuffernano.readInt64());
        continue; /* Loop/switch isn't completed */
_L7:
        durationMs = Long.valueOf(codedinputbytebuffernano.readInt64());
        continue; /* Loop/switch isn't completed */
_L8:
        threadId = Long.valueOf(codedinputbytebuffernano.readInt64());
        continue; /* Loop/switch isn't completed */
_L9:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 3)
        {
            try
            {
                spanType = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(40)).append(l).append(" is not a valid enum SpanType").toString());
_L10:
        hashedName = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
        continue; /* Loop/switch isn't completed */
_L11:
        name = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L12:
        cpuTimeMs = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        if (true) goto _L14; else goto _L13
_L13:
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (constantName != null)
        {
            String s = constantName;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
        }
        j = i;
        if (id != null)
        {
            id.longValue();
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(16) + 8);
        }
        i = j;
        if (parentId != null)
        {
            parentId.longValue();
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(24) + 8);
        }
        j = i;
        if (startTimeMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(4, startTimeMs.longValue());
        }
        i = j;
        if (durationMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(5, durationMs.longValue());
        }
        j = i;
        if (threadId != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(6, threadId.longValue());
        }
        int l = j;
        if (spanType != 0x80000000)
        {
            l = j + CodedOutputByteBufferNano.computeInt32Size(7, spanType);
        }
        i = l;
        if (hashedName != null)
        {
            hashedName.longValue();
            i = l + (CodedOutputByteBufferNano.computeRawVarint32Size(64) + 8);
        }
        j = i;
        if (name != null)
        {
            String s1 = name;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(72);
            int i1 = CodedOutputByteBufferNano.encodedLength(s1);
            j = i + (i1 + CodedOutputByteBufferNano.computeRawVarint32Size(i1) + j);
        }
        i = j;
        if (cpuTimeMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(10, cpuTimeMs.intValue());
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
        if (constantName != null)
        {
            codedoutputbytebuffernano.writeString(1, constantName);
        }
        if (id != null)
        {
            codedoutputbytebuffernano.writeFixed64(2, id.longValue());
        }
        if (parentId != null)
        {
            codedoutputbytebuffernano.writeFixed64(3, parentId.longValue());
        }
        if (startTimeMs != null)
        {
            codedoutputbytebuffernano.writeInt64(4, startTimeMs.longValue());
        }
        if (durationMs != null)
        {
            codedoutputbytebuffernano.writeInt64(5, durationMs.longValue());
        }
        if (threadId != null)
        {
            codedoutputbytebuffernano.writeInt64(6, threadId.longValue());
        }
        if (spanType != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(7, spanType);
        }
        if (hashedName != null)
        {
            codedoutputbytebuffernano.writeFixed64(8, hashedName.longValue());
        }
        if (name != null)
        {
            codedoutputbytebuffernano.writeString(9, name);
        }
        if (cpuTimeMs != null)
        {
            codedoutputbytebuffernano.writeInt32(10, cpuTimeMs.intValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
