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
//            AndroidMemoryStats

public final class MemoryStats extends ExtendableMessageNano
{

    public AndroidMemoryStats androidMemoryStats;
    private logs.proto.wireless.performance.mobile.MemoryMetric.IosMemoryStats iosMemoryStats;

    public MemoryStats()
    {
        androidMemoryStats = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (androidMemoryStats != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(1, androidMemoryStats);
        }
        j = i;
        if (iosMemoryStats != null)
        {
            j = i + CodedOutputStream.computeMessageSize(2, iosMemoryStats);
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
                if (androidMemoryStats == null)
                {
                    androidMemoryStats = new AndroidMemoryStats();
                }
                codedinputbytebuffernano.readMessage(androidMemoryStats);
                break;

            case 18: // '\022'
                iosMemoryStats = (logs.proto.wireless.performance.mobile.MemoryMetric.IosMemoryStats)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.MemoryMetric.IosMemoryStats.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (androidMemoryStats != null)
        {
            codedoutputbytebuffernano.writeMessage(1, androidMemoryStats);
        }
        if (iosMemoryStats != null)
        {
            logs.proto.wireless.performance.mobile.MemoryMetric.IosMemoryStats iosmemorystats = iosMemoryStats;
            CodedOutputStream codedoutputstream = codedoutputbytebuffernano.getCodedOutputStream();
            codedoutputstream.writeMessage(2, iosmemorystats);
            codedoutputstream.flush();
            codedoutputbytebuffernano.codedOutputStreamPosition = codedoutputbytebuffernano.buffer.position();
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
