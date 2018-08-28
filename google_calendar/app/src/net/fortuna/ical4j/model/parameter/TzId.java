// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Escapable;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class TzId extends Parameter
    implements Escapable
{

    public static final long serialVersionUID = 0x20d78dff949b26d7L;
    private String value;

    public TzId(String s)
    {
        super("TZID", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
    }

    public final String getValue()
    {
        return value;
    }
}
