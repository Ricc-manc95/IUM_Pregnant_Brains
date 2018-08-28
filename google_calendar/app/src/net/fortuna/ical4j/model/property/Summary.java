// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Escapable;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.ParameterValidator;

public final class Summary extends Property
    implements Escapable
{

    public static final long serialVersionUID = 0x6afd6cb32b591b90L;
    private String value;

    public Summary()
    {
        super("SUMMARY", PropertyFactoryImpl.instance);
    }

    public Summary(String s)
    {
        super("SUMMARY", PropertyFactoryImpl.instance);
        setValue(s);
    }

    public final String getValue()
    {
        return value;
    }

    public final void setValue(String s)
    {
        value = s;
    }

    public final void validate()
        throws ValidationException
    {
        ParameterValidator.assertOneOrLess("ALTREP", super.parameters);
        ParameterValidator.assertOneOrLess("LANGUAGE", super.parameters);
    }
}
