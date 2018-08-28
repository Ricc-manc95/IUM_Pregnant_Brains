// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.parameter.Role;

// Referenced classes of package net.fortuna.ical4j.model:
//            ParameterFactory, Parameter

final class 
    implements ParameterFactory
{

    public static final long serialVersionUID = 1L;

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        s1 = new Role(s1);
        if (Role.CHAIR.equals(s1))
        {
            s = Role.CHAIR;
        } else
        {
            if (Role.REQ_PARTICIPANT.equals(s1))
            {
                return Role.REQ_PARTICIPANT;
            }
            if (Role.OPT_PARTICIPANT.equals(s1))
            {
                return Role.OPT_PARTICIPANT;
            }
            s = s1;
            if (Role.NON_PARTICIPANT.equals(s1))
            {
                return Role.NON_PARTICIPANT;
            }
        }
        return s;
    }

    ()
    {
    }
}
