// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.parameter.ScheduleStatus;

// Referenced classes of package net.fortuna.ical4j.model:
//            ParameterFactory, Parameter

final class I
    implements ParameterFactory
{

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        return new ScheduleStatus(s1);
    }

    I()
    {
    }
}
