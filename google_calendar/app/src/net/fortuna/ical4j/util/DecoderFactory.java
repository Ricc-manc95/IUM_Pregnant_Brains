// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import net.fortuna.ical4j.model.parameter.Encoding;
import org.apache.commons.codec.BinaryDecoder;

// Referenced classes of package net.fortuna.ical4j.util:
//            Configurator, DefaultDecoderFactory

public abstract class DecoderFactory
{

    public static DecoderFactory instance = new DefaultDecoderFactory();

    public DecoderFactory()
    {
    }

    public abstract BinaryDecoder createBinaryDecoder(Encoding encoding)
        throws UnsupportedEncodingException;

    static 
    {
        String s1 = Configurator.CONFIG.getProperty("net.fortuna.ical4j.factory.decoder");
        String s;
        s = s1;
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        s = System.getProperty("net.fortuna.ical4j.factory.decoder");
        instance = (DecoderFactory)Class.forName(s).newInstance();
        break MISSING_BLOCK_LABEL_46;
        Exception exception;
        exception;
    }
}
