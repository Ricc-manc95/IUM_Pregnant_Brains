// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class PartStat extends Parameter
{

    public static final PartStat ACCEPTED = new PartStat("ACCEPTED");
    public static final PartStat COMPLETED = new PartStat("COMPLETED");
    public static final PartStat DECLINED = new PartStat("DECLINED");
    public static final PartStat DELEGATED = new PartStat("DELEGATED");
    public static final PartStat IN_PROCESS = new PartStat("IN-PROCESS");
    public static final PartStat NEEDS_ACTION = new PartStat("NEEDS-ACTION");
    public static final PartStat TENTATIVE = new PartStat("TENTATIVE");
    public static final long serialVersionUID = 0x92f8a5e992827b77L;
    private String value;

    public PartStat(String s)
    {
        super("PARTSTAT", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
    }

    public final String getValue()
    {
        return value;
    }

}
