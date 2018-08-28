// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.parameter.Encoding;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.util.DecoderFactory;
import net.fortuna.ical4j.util.EncoderFactory;
import net.fortuna.ical4j.util.ParameterValidator;
import net.fortuna.ical4j.util.Strings;
import net.fortuna.ical4j.util.Uris;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Attach extends Property
{

    public static final long serialVersionUID = 0x3d9dde8dae7d5cdcL;
    private byte binary[];
    private URI uri;

    public Attach()
    {
        super("ATTACH", PropertyFactoryImpl.instance);
    }

    public Attach(URI uri1)
    {
        super("ATTACH", PropertyFactoryImpl.instance);
        uri = uri1;
    }

    public final String getValue()
    {
        if (uri != null)
        {
            return Uris.decode(Strings.valueOf(uri));
        }
        if (binary == null) goto _L2; else goto _L1
_L1:
        String s = new String(EncoderFactory.instance.createBinaryEncoder((Encoding)super.parameters.getParameter("ENCODING")).encode(binary));
        return s;
        Object obj;
        obj;
_L4:
        LogFactory.getLog(net/fortuna/ical4j/model/property/Attach).error("Error encoding binary data", ((Throwable) (obj)));
_L2:
        return null;
        obj;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void setValue(String s)
        throws IOException, URISyntaxException
    {
        if (super.parameters.getParameter("ENCODING") != null)
        {
            try
            {
                binary = DecoderFactory.instance.createBinaryDecoder((Encoding)super.parameters.getParameter("ENCODING")).decode(s.getBytes());
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                LogFactory.getLog(net/fortuna/ical4j/model/property/Attach).error("Error encoding binary data", s);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                LogFactory.getLog(net/fortuna/ical4j/model/property/Attach).error("Error decoding binary data", s);
            }
            return;
        } else
        {
            uri = Uris.create(s);
            return;
        }
    }

    public final void validate()
        throws ValidationException
    {
        ParameterValidator.assertOneOrLess("FMTTYPE", super.parameters);
        if (Value.BINARY.equals(super.parameters.getParameter("VALUE")))
        {
            ParameterValidator.assertOne("ENCODING", super.parameters);
            if (!Encoding.BASE64.equals(super.parameters.getParameter("ENCODING")))
            {
                throw new ValidationException("If the value type parameter is [BINARY], the inlineencoding parameter MUST be specified with the value [BASE64]");
            }
        }
    }
}
