// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class XParameter extends Parameter
{

    public static final long serialVersionUID = 0xd133b426ecd64a51L;
    private String value;

    public XParameter(String s, String s1)
    {
        super(s, ParameterFactoryImpl.instance);
        value = Strings.unquote(s1);
    }

    public final String getValue()
    {
        return value;
    }
}
