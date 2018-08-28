// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.parameter.Encoding;

// Referenced classes of package net.fortuna.ical4j.model:
//            ParameterFactory, Parameter

final class 
    implements ParameterFactory
{

    public static final long serialVersionUID = 1L;

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        s1 = new Encoding(s1);
        if (Encoding.EIGHT_BIT.equals(s1))
        {
            s = Encoding.EIGHT_BIT;
        } else
        {
            s = s1;
            if (Encoding.BASE64.equals(s1))
            {
                return Encoding.BASE64;
            }
        }
        return s;
    }

    ()
    {
    }
}
