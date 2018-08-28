// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class TranslationStats extends ExtendableMessageNano
{

    private int engine;
    private String packageVersionCode;
    private Integer queryLength;
    private String sourceLang;
    private String targetLang;
    private int translationSource;

    public TranslationStats()
    {
        sourceLang = null;
        targetLang = null;
        queryLength = null;
        engine = 0x80000000;
        translationSource = 0x80000000;
        packageVersionCode = null;
        cachedSize = -1;
    }

    private final TranslationStats mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L10:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 7: default 72
    //                   0: 81
    //                   10: 83
    //                   18: 94
    //                   24: 105
    //                   32: 119
    //                   40: 211
    //                   50: 303;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        sourceLang = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L4:
        targetLang = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L5:
        queryLength = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L6:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 2)
        {
            try
            {
                engine = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(49)).append(l).append(" is not a valid enum TranslationEngine").toString());
_L7:
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 5)
        {
            try
            {
                translationSource = l;
            }
            catch (IllegalArgumentException illegalargumentexception1)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(38)).append(l).append(" is not a valid enum Source").toString());
_L8:
        packageVersionCode = codedinputbytebuffernano.readString();
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (sourceLang != null)
        {
            String s = sourceLang;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
        }
        j = i;
        if (targetLang != null)
        {
            String s1 = targetLang;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            int l = CodedOutputByteBufferNano.encodedLength(s1);
            j = i + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + j);
        }
        i = j;
        if (queryLength != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(3, queryLength.intValue());
        }
        j = i;
        if (engine != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(4, engine);
        }
        i = j;
        if (translationSource != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(5, translationSource);
        }
        j = i;
        if (packageVersionCode != null)
        {
            String s2 = packageVersionCode;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(48);
            int i1 = CodedOutputByteBufferNano.encodedLength(s2);
            j = i + (i1 + CodedOutputByteBufferNano.computeRawVarint32Size(i1) + j);
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
        if (sourceLang != null)
        {
            codedoutputbytebuffernano.writeString(1, sourceLang);
        }
        if (targetLang != null)
        {
            codedoutputbytebuffernano.writeString(2, targetLang);
        }
        if (queryLength != null)
        {
            codedoutputbytebuffernano.writeInt32(3, queryLength.intValue());
        }
        if (engine != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(4, engine);
        }
        if (translationSource != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(5, translationSource);
        }
        if (packageVersionCode != null)
        {
            codedoutputbytebuffernano.writeString(6, packageVersionCode);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
