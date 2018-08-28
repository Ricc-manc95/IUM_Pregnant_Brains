// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package social.graph.chips.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

// Referenced classes of package social.graph.chips.nano:
//            ChipsRequest, ChipsResponse, ChipsDataSourceRequest, ChipsDataSourceResponse

public final class ChipsExtension extends ExtendableMessageNano
{

    public ChipsDataSourceRequest chipsDataSourceRequest;
    public ChipsDataSourceResponse chipsDataSourceResponse;
    public ChipsRequest chipsRequest;
    public ChipsResponse chipsResponse;
    public int clientLabel;
    public int eventType;
    public int oneof_event_;

    public ChipsExtension()
    {
        oneof_event_ = -1;
        oneof_event_ = -1;
        chipsRequest = null;
        oneof_event_ = -1;
        chipsResponse = null;
        oneof_event_ = -1;
        chipsDataSourceRequest = null;
        oneof_event_ = -1;
        chipsDataSourceResponse = null;
        eventType = 0x80000000;
        clientLabel = 0x80000000;
        cachedSize = -1;
    }

    private final ChipsExtension mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L10:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 7: default 72
    //                   0: 81
    //                   8: 83
    //                   16: 175
    //                   26: 267
    //                   34: 301
    //                   42: 335
    //                   50: 369;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 4)
        {
            try
            {
                eventType = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(46)).append(l).append(" is not a valid enum ChipsEventType").toString());
_L4:
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 1)
        {
            try
            {
                clientLabel = l;
            }
            catch (IllegalArgumentException illegalargumentexception1)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(43)).append(l).append(" is not a valid enum ClientLabel").toString());
_L5:
        if (chipsRequest == null)
        {
            chipsRequest = new ChipsRequest();
        }
        codedinputbytebuffernano.readMessage(chipsRequest);
        oneof_event_ = 0;
        continue; /* Loop/switch isn't completed */
_L6:
        if (chipsResponse == null)
        {
            chipsResponse = new ChipsResponse();
        }
        codedinputbytebuffernano.readMessage(chipsResponse);
        oneof_event_ = 1;
        continue; /* Loop/switch isn't completed */
_L7:
        if (chipsDataSourceRequest == null)
        {
            chipsDataSourceRequest = new ChipsDataSourceRequest();
        }
        codedinputbytebuffernano.readMessage(chipsDataSourceRequest);
        oneof_event_ = 2;
        continue; /* Loop/switch isn't completed */
_L8:
        if (chipsDataSourceResponse == null)
        {
            chipsDataSourceResponse = new ChipsDataSourceResponse();
        }
        codedinputbytebuffernano.readMessage(chipsDataSourceResponse);
        oneof_event_ = 3;
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (eventType != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, eventType);
        }
        j = i;
        if (clientLabel != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, clientLabel);
        }
        i = j;
        if (oneof_event_ == 0)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(3, chipsRequest);
        }
        j = i;
        if (oneof_event_ == 1)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(4, chipsResponse);
        }
        i = j;
        if (oneof_event_ == 2)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(5, chipsDataSourceRequest);
        }
        j = i;
        if (oneof_event_ == 3)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(6, chipsDataSourceResponse);
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
        if (eventType != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(1, eventType);
        }
        if (clientLabel != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(2, clientLabel);
        }
        if (oneof_event_ == 0)
        {
            codedoutputbytebuffernano.writeMessage(3, chipsRequest);
        }
        if (oneof_event_ == 1)
        {
            codedoutputbytebuffernano.writeMessage(4, chipsResponse);
        }
        if (oneof_event_ == 2)
        {
            codedoutputbytebuffernano.writeMessage(5, chipsDataSourceRequest);
        }
        if (oneof_event_ == 3)
        {
            codedoutputbytebuffernano.writeMessage(6, chipsDataSourceResponse);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
