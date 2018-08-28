// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.parameter.Range;

// Referenced classes of package net.fortuna.ical4j.model:
//            ParameterFactory, Parameter

final class 
    implements ParameterFactory
{

    public static final long serialVersionUID = 1L;

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        s1 = new Range(s1);
        if (Range.THISANDFUTURE.equals(s1))
        {
            s = Range.THISANDFUTURE;
        } else
        {
            s = s1;
            if (Range.THISANDPRIOR.equals(s1))
            {
                return Range.THISANDPRIOR;
            }
        }
        return s;
    }

    ()
    {
    }
}
