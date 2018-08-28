// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public class Clazz extends Property
{

    public static final Clazz CONFIDENTIAL = new ImmutableClazz("CONFIDENTIAL");
    public static final Clazz PRIVATE = new ImmutableClazz("PRIVATE");
    public static final Clazz PUBLIC = new ImmutableClazz("PUBLIC");
    public static final long serialVersionUID = 0x448e34911f8259f9L;
    private String value;

    public Clazz()
    {
        super("CLASS", PropertyFactoryImpl.instance);
    }

    public Clazz(ParameterList parameterlist, String s)
    {
        super("CLASS", parameterlist, PropertyFactoryImpl.instance);
        value = s;
    }

    public final String getValue()
    {
        return value;
    }

    public void setValue(String s)
    {
        value = s;
    }

    public final void validate()
        throws ValidationException
    {
    }


    private class ImmutableClazz extends Clazz
    {

        public static final long serialVersionUID = 0x52f7865c858d4932L;

        public final void setValue(String s)
        {
            throw new UnsupportedOperationException("Cannot modify constant instances");
        }

        ImmutableClazz(String s)
        {
            super(new ParameterList(true), s);
        }
    }

}
