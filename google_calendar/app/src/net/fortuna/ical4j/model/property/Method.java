// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public class Method extends Property
{

    public static final Method ADD = new ImmutableMethod("ADD");
    public static final Method CANCEL = new ImmutableMethod("CANCEL");
    public static final Method COUNTER = new ImmutableMethod("COUNTER");
    public static final Method DECLINE_COUNTER = new ImmutableMethod("DECLINE-COUNTER");
    public static final Method PUBLISH = new ImmutableMethod("PUBLISH");
    public static final Method REFRESH = new ImmutableMethod("REFRESH");
    public static final Method REPLY = new ImmutableMethod("REPLY");
    public static final Method REQUEST = new ImmutableMethod("REQUEST");
    public static final long serialVersionUID = 0x6435fdb54d3f949fL;
    private String value;

    public Method()
    {
        super("METHOD", PropertyFactoryImpl.instance);
    }

    public Method(ParameterList parameterlist, String s)
    {
        super("METHOD", parameterlist, PropertyFactoryImpl.instance);
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


    private class ImmutableMethod extends Method
    {

        public static final long serialVersionUID = 0x4a013aaf122c7731L;

        public final void setValue(String s)
        {
            throw new UnsupportedOperationException("Cannot modify constant instances");
        }

        ImmutableMethod(String s)
        {
            super(new ParameterList(true), s);
        }
    }

}
