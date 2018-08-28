// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import net.fortuna.ical4j.model.parameter.Encoding;
import org.apache.commons.codec.BinaryEncoder;

// Referenced classes of package net.fortuna.ical4j.util:
//            Configurator, DefaultEncoderFactory

public abstract class EncoderFactory
{

    public static EncoderFactory instance = new DefaultEncoderFactory();

    public EncoderFactory()
    {
    }

    public abstract BinaryEncoder createBinaryEncoder(Encoding encoding)
        throws UnsupportedEncodingException;

    static 
    {
        String s1 = Configurator.CONFIG.getProperty("net.fortuna.ical4j.factory.encoder");
        String s;
        s = s1;
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        s = System.getProperty("net.fortuna.ical4j.factory.encoder");
        instance = (EncoderFactory)Class.forName(s).newInstance();
        break MISSING_BLOCK_LABEL_46;
        Exception exception;
        exception;
    }
}
