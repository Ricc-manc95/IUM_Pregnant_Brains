// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class Role extends Parameter
{

    public static final Role CHAIR = new Role("CHAIR");
    public static final Role NON_PARTICIPANT = new Role("NON-PARTICIPANT");
    public static final Role OPT_PARTICIPANT = new Role("OPT-PARTICIPANT");
    public static final Role REQ_PARTICIPANT = new Role("REQ-PARTICIPANT");
    public static final long serialVersionUID = 0x13f59a977eae31ebL;
    private String value;

    public Role(String s)
    {
        super("ROLE", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
    }

    public final String getValue()
    {
        return value;
    }

}
