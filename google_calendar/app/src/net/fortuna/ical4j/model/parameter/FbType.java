// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class FbType extends Parameter
{

    public static final FbType BUSY = new FbType("BUSY");
    public static final FbType BUSY_TENTATIVE = new FbType("BUSY-TENTATIVE");
    public static final FbType BUSY_UNAVAILABLE = new FbType("BUSY-UNAVAILABLE");
    public static final FbType FREE = new FbType("FREE");
    public static final long serialVersionUID = 0xe1392ef3cea0dc31L;
    private String value;

    public FbType(String s)
    {
        super("FBTYPE", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
    }

    public final String getValue()
    {
        return value;
    }

}
