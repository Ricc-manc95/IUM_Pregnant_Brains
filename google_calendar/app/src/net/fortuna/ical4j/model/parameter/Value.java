// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class Value extends Parameter
{

    public static final Value BINARY = new Value("BINARY");
    public static final Value BOOLEAN = new Value("BOOLEAN");
    public static final Value CAL_ADDRESS = new Value("CAL-ADDRESS");
    public static final Value DATE = new Value("DATE");
    public static final Value DATE_TIME = new Value("DATE-TIME");
    public static final Value DURATION = new Value("DURATION");
    public static final Value FLOAT = new Value("FLOAT");
    public static final Value INTEGER = new Value("INTEGER");
    public static final Value PERIOD = new Value("PERIOD");
    public static final Value RECUR = new Value("RECUR");
    public static final Value TEXT = new Value("TEXT");
    public static final Value TIME = new Value("TIME");
    public static final Value URI = new Value("URI");
    public static final Value UTC_OFFSET = new Value("UTC-OFFSET");
    public static final long serialVersionUID = 0x9b8b2cc8f225b438L;
    private String value;

    public Value(String s)
    {
        super("VALUE", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
    }

    public final String getValue()
    {
        return value;
    }

}
