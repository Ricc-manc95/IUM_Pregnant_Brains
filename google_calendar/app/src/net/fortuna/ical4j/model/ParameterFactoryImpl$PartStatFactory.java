// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.parameter.PartStat;

// Referenced classes of package net.fortuna.ical4j.model:
//            ParameterFactory, Parameter

final class 
    implements ParameterFactory
{

    public static final long serialVersionUID = 1L;

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        s1 = new PartStat(s1);
        if (PartStat.NEEDS_ACTION.equals(s1))
        {
            s = PartStat.NEEDS_ACTION;
        } else
        {
            if (PartStat.ACCEPTED.equals(s1))
            {
                return PartStat.ACCEPTED;
            }
            if (PartStat.DECLINED.equals(s1))
            {
                return PartStat.DECLINED;
            }
            if (PartStat.TENTATIVE.equals(s1))
            {
                return PartStat.TENTATIVE;
            }
            if (PartStat.DELEGATED.equals(s1))
            {
                return PartStat.DELEGATED;
            }
            if (PartStat.COMPLETED.equals(s1))
            {
                return PartStat.COMPLETED;
            }
            s = s1;
            if (PartStat.IN_PROCESS.equals(s1))
            {
                return PartStat.IN_PROCESS;
            }
        }
        return s;
    }

    ()
    {
    }
}
