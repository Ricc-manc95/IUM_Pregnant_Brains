// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.android.gms.framework.logging.proto.nano.GCoreDimensions;
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
//            GmmExtension, PhotosScannerExtension, CalendarExtension, PhotosExtension, 
//            YouTubeExtension, CloudDpcExtension, TranslateExtension, BugleExtension, 
//            CsapkExtension

public final class MetricExtension extends ExtendableMessageNano
{

    private BugleExtension bugleExtension;
    public CalendarExtension calendarExtension;
    private CloudDpcExtension cloudDpcExtension;
    private CsapkExtension csapkExtension;
    private GCoreDimensions gcoreDimension;
    private logs.proto.wireless.performance.mobile.ExtensionGcs.GcsExtension gcsExtension;
    private logs.proto.wireless.performance.mobile.ExtensionGmail.GmailExtension gmailExtension;
    private GmmExtension gmmExtension;
    private PhotosExtension photosExtension;
    private PhotosScannerExtension photosScannerExtension;
    private TranslateExtension translateExtension;
    private YouTubeExtension youtubeExtension;

    public MetricExtension()
    {
        gmmExtension = null;
        photosScannerExtension = null;
        calendarExtension = null;
        photosExtension = null;
        youtubeExtension = null;
        cloudDpcExtension = null;
        gcoreDimension = null;
        translateExtension = null;
        bugleExtension = null;
        csapkExtension = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (gmailExtension != null)
        {
            i = j + CodedOutputStream.computeMessageSize(1, gmailExtension);
        }
        j = i;
        if (gmmExtension != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(2, gmmExtension);
        }
        i = j;
        if (photosScannerExtension != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(3, photosScannerExtension);
        }
        j = i;
        if (calendarExtension != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(4, calendarExtension);
        }
        i = j;
        if (photosExtension != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(5, photosExtension);
        }
        j = i;
        if (youtubeExtension != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(6, youtubeExtension);
        }
        i = j;
        if (cloudDpcExtension != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(7, cloudDpcExtension);
        }
        j = i;
        if (gcoreDimension != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(8, gcoreDimension);
        }
        i = j;
        if (gcsExtension != null)
        {
            i = j + CodedOutputStream.computeMessageSize(9, gcsExtension);
        }
        j = i;
        if (translateExtension != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(10, translateExtension);
        }
        i = j;
        if (bugleExtension != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(11, bugleExtension);
        }
        j = i;
        if (csapkExtension != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(12, csapkExtension);
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
                gmailExtension = (logs.proto.wireless.performance.mobile.ExtensionGmail.GmailExtension)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.ExtensionGmail.GmailExtension.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
                break;

            case 18: // '\022'
                if (gmmExtension == null)
                {
                    gmmExtension = new GmmExtension();
                }
                codedinputbytebuffernano.readMessage(gmmExtension);
                break;

            case 26: // '\032'
                if (photosScannerExtension == null)
                {
                    photosScannerExtension = new PhotosScannerExtension();
                }
                codedinputbytebuffernano.readMessage(photosScannerExtension);
                break;

            case 34: // '"'
                if (calendarExtension == null)
                {
                    calendarExtension = new CalendarExtension();
                }
                codedinputbytebuffernano.readMessage(calendarExtension);
                break;

            case 42: // '*'
                if (photosExtension == null)
                {
                    photosExtension = new PhotosExtension();
                }
                codedinputbytebuffernano.readMessage(photosExtension);
                break;

            case 50: // '2'
                if (youtubeExtension == null)
                {
                    youtubeExtension = new YouTubeExtension();
                }
                codedinputbytebuffernano.readMessage(youtubeExtension);
                break;

            case 58: // ':'
                if (cloudDpcExtension == null)
                {
                    cloudDpcExtension = new CloudDpcExtension();
                }
                codedinputbytebuffernano.readMessage(cloudDpcExtension);
                break;

            case 66: // 'B'
                if (gcoreDimension == null)
                {
                    gcoreDimension = new GCoreDimensions();
                }
                codedinputbytebuffernano.readMessage(gcoreDimension);
                break;

            case 74: // 'J'
                gcsExtension = (logs.proto.wireless.performance.mobile.ExtensionGcs.GcsExtension)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.ExtensionGcs.GcsExtension.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
                break;

            case 82: // 'R'
                if (translateExtension == null)
                {
                    translateExtension = new TranslateExtension();
                }
                codedinputbytebuffernano.readMessage(translateExtension);
                break;

            case 90: // 'Z'
                if (bugleExtension == null)
                {
                    bugleExtension = new BugleExtension();
                }
                codedinputbytebuffernano.readMessage(bugleExtension);
                break;

            case 98: // 'b'
                if (csapkExtension == null)
                {
                    csapkExtension = new CsapkExtension();
                }
                codedinputbytebuffernano.readMessage(csapkExtension);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (gmailExtension != null)
        {
            logs.proto.wireless.performance.mobile.ExtensionGmail.GmailExtension gmailextension = gmailExtension;
            CodedOutputStream codedoutputstream = codedoutputbytebuffernano.getCodedOutputStream();
            codedoutputstream.writeMessage(1, gmailextension);
            codedoutputstream.flush();
            codedoutputbytebuffernano.codedOutputStreamPosition = codedoutputbytebuffernano.buffer.position();
        }
        if (gmmExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(2, gmmExtension);
        }
        if (photosScannerExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(3, photosScannerExtension);
        }
        if (calendarExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(4, calendarExtension);
        }
        if (photosExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(5, photosExtension);
        }
        if (youtubeExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(6, youtubeExtension);
        }
        if (cloudDpcExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(7, cloudDpcExtension);
        }
        if (gcoreDimension != null)
        {
            codedoutputbytebuffernano.writeMessage(8, gcoreDimension);
        }
        if (gcsExtension != null)
        {
            logs.proto.wireless.performance.mobile.ExtensionGcs.GcsExtension gcsextension = gcsExtension;
            CodedOutputStream codedoutputstream1 = codedoutputbytebuffernano.getCodedOutputStream();
            codedoutputstream1.writeMessage(9, gcsextension);
            codedoutputstream1.flush();
            codedoutputbytebuffernano.codedOutputStreamPosition = codedoutputbytebuffernano.buffer.position();
        }
        if (translateExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(10, translateExtension);
        }
        if (bugleExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(11, bugleExtension);
        }
        if (csapkExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(12, csapkExtension);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
