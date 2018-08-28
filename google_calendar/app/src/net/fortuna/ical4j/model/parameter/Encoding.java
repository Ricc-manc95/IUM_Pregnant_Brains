// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class Encoding extends Parameter
{

    public static final Encoding BASE64 = new Encoding("BASE64");
    public static final Encoding EIGHT_BIT = new Encoding("8BIT");
    public static final Encoding QUOTED_PRINTABLE = new Encoding("QUOTED-PRINTABLE");
    public static final long serialVersionUID = 0x68967215064883e5L;
    private String value;

    public Encoding(String s)
    {
        super("ENCODING", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
    }

    public final String getValue()
    {
        return value;
    }

    static 
    {
        new Encoding("7BIT");
        new Encoding("BINARY");
    }
}
