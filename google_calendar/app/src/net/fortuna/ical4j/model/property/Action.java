// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public class Action extends Property
{

    public static final Action AUDIO = new ImmutableAction("AUDIO");
    public static final Action DISPLAY = new ImmutableAction("DISPLAY");
    public static final Action EMAIL = new ImmutableAction("EMAIL");
    public static final Action PROCEDURE = new ImmutableAction("PROCEDURE");
    public static final long serialVersionUID = 0xdf5735270eaa5710L;
    private String value;

    public Action()
    {
        super("ACTION", PropertyFactoryImpl.instance);
    }

    public Action(ParameterList parameterlist, String s)
    {
        super("ACTION", parameterlist, PropertyFactoryImpl.instance);
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


    private class ImmutableAction extends Action
    {

        public static final long serialVersionUID = 0xd9ce17fbfc0b3a8fL;

        public final void setValue(String s)
        {
            throw new UnsupportedOperationException("Cannot modify constant instances");
        }

        ImmutableAction(String s)
        {
            super(new ParameterList(true), s);
        }
    }

}
