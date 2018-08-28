// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class CuType extends Parameter
{

    public static final CuType GROUP = new CuType("GROUP");
    public static final CuType INDIVIDUAL = new CuType("INDIVIDUAL");
    public static final CuType RESOURCE = new CuType("RESOURCE");
    public static final CuType ROOM = new CuType("ROOM");
    public static final CuType UNKNOWN = new CuType("UNKNOWN");
    public static final long serialVersionUID = 0xd481911a076400b4L;
    private String value;

    public CuType(String s)
    {
        super("CUTYPE", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
    }

    public final String getValue()
    {
        return value;
    }

}
