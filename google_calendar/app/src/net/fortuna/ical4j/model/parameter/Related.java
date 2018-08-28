// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class Related extends Parameter
{

    public static final Related END = new Related("END");
    public static final Related START = new Related("START");
    public static final long serialVersionUID = 0x15cba0e5d8e93f7dL;
    private String value;

    public Related(String s)
    {
        super("RELATED", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
        if (!"START".equals(value) && !"END".equals(value))
        {
            s = value;
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 16)).append("Invalid value [").append(s).append("]").toString());
        } else
        {
            return;
        }
    }

    public final String getValue()
    {
        return value;
    }

}
