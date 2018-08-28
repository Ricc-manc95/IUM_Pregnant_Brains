// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.parameter.Encoding;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.QuotedPrintableCodec;

// Referenced classes of package net.fortuna.ical4j.util:
//            DecoderFactory

public final class DefaultDecoderFactory extends DecoderFactory
{

    public DefaultDecoderFactory()
    {
    }

    public final BinaryDecoder createBinaryDecoder(Encoding encoding)
        throws UnsupportedEncodingException
    {
        if (Encoding.QUOTED_PRINTABLE.equals(encoding))
        {
            return new QuotedPrintableCodec();
        }
        if (Encoding.BASE64.equals(encoding))
        {
            return new Base64();
        } else
        {
            throw new UnsupportedEncodingException(MessageFormat.format("Decoder not available for encoding [{0}]", new Object[] {
                encoding
            }));
        }
    }
}
