// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.parameter.ScheduleAgent;

// Referenced classes of package net.fortuna.ical4j.model:
//            ParameterFactory, Parameter

final class 
    implements ParameterFactory
{

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        s1 = new ScheduleAgent(s1);
        if (ScheduleAgent.SERVER.equals(s1))
        {
            s = ScheduleAgent.SERVER;
        } else
        {
            if (ScheduleAgent.CLIENT.equals(s1))
            {
                return ScheduleAgent.CLIENT;
            }
            s = s1;
            if (ScheduleAgent.NONE.equals(s1))
            {
                return ScheduleAgent.NONE;
            }
        }
        return s;
    }

    ()
    {
    }
}
