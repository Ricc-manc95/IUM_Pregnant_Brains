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
import java.io.IOException;
import java.nio.ByteBuffer;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            AndroidProcessStats

public final class ProcessStats extends ExtendableMessageNano
{

    public AndroidProcessStats androidProcessStats;
    private logs.proto.wireless.performance.mobile.ProcessProto.IosProcessStats iosProcessStats;

    public ProcessStats()
    {
        androidProcessStats = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (androidProcessStats != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(1, androidProcessStats);
        }
        j = i;
        if (iosProcessStats != null)
        {
            j = i + CodedOutputStream.computeMessageSize(2, iosProcessStats);
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
                if (androidProcessStats == null)
                {
                    androidProcessStats = new AndroidProcessStats();
                }
                codedinputbytebuffernano.readMessage(androidProcessStats);
                break;

            case 18: // '\022'
                iosProcessStats = (logs.proto.wireless.performance.mobile.ProcessProto.IosProcessStats)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.ProcessProto.IosProcessStats.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (androidProcessStats != null)
        {
            codedoutputbytebuffernano.writeMessage(1, androidProcessStats);
        }
        if (iosProcessStats != null)
        {
            logs.proto.wireless.performance.mobile.ProcessProto.IosProcessStats iosprocessstats = iosProcessStats;
            CodedOutputStream codedoutputstream = codedoutputbytebuffernano.getCodedOutputStream();
            codedoutputstream.writeMessage(2, iosprocessstats);
            codedoutputstream.flush();
            codedoutputbytebuffernano.codedOutputStreamPosition = codedoutputbytebuffernano.buffer.position();
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
