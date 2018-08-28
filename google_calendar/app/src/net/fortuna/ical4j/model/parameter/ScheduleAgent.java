// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class ScheduleAgent extends Parameter
{

    public static final ScheduleAgent CLIENT = new ScheduleAgent("CLIENT");
    public static final ScheduleAgent NONE = new ScheduleAgent("NONE");
    public static final ScheduleAgent SERVER = new ScheduleAgent("SERVER");
    private String value;

    public ScheduleAgent(String s)
    {
        super("SCHEDULE-AGENT", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
    }

    public final String getValue()
    {
        return value;
    }

}
