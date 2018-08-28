// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.parameter.CuType;

// Referenced classes of package net.fortuna.ical4j.model:
//            ParameterFactory, Parameter

final class A
    implements ParameterFactory
{

    public static final long serialVersionUID = 1L;

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        s1 = new CuType(s1);
        if (CuType.INDIVIDUAL.equals(s1))
        {
            s = CuType.INDIVIDUAL;
        } else
        {
            if (CuType.GROUP.equals(s1))
            {
                return CuType.GROUP;
            }
            if (CuType.RESOURCE.equals(s1))
            {
                return CuType.RESOURCE;
            }
            if (CuType.ROOM.equals(s1))
            {
                return CuType.ROOM;
            }
            s = s1;
            if (CuType.UNKNOWN.equals(s1))
            {
                return CuType.UNKNOWN;
            }
        }
        return s;
    }

    A()
    {
    }
}
