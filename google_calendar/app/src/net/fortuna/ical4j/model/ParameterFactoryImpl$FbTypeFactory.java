// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.parameter.FbType;

// Referenced classes of package net.fortuna.ical4j.model:
//            ParameterFactory, Parameter

final class A
    implements ParameterFactory
{

    public static final long serialVersionUID = 1L;

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        s1 = new FbType(s1);
        if (FbType.FREE.equals(s1))
        {
            s = FbType.FREE;
        } else
        {
            if (FbType.BUSY.equals(s1))
            {
                return FbType.BUSY;
            }
            if (FbType.BUSY_TENTATIVE.equals(s1))
            {
                return FbType.BUSY_TENTATIVE;
            }
            s = s1;
            if (FbType.BUSY_UNAVAILABLE.equals(s1))
            {
                return FbType.BUSY_UNAVAILABLE;
            }
        }
        return s;
    }

    A()
    {
    }
}
