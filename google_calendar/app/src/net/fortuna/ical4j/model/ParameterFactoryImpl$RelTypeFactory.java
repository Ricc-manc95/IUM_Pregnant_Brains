// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.parameter.RelType;

// Referenced classes of package net.fortuna.ical4j.model:
//            ParameterFactory, Parameter

final class 
    implements ParameterFactory
{

    public static final long serialVersionUID = 1L;

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        s1 = new RelType(s1);
        if (!RelType.PARENT.equals(s1)) goto _L2; else goto _L1
_L1:
        s = RelType.PARENT;
_L4:
        s1 = s;
        if (RelType.SIBLING.equals(s))
        {
            s1 = RelType.SIBLING;
        }
        return s1;
_L2:
        s = s1;
        if (RelType.CHILD.equals(s1))
        {
            s = RelType.CHILD;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    ()
    {
    }
}
