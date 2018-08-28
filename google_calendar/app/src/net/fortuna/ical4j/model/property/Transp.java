// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public class Transp extends Property
{

    public static final Transp OPAQUE = new ImmutableTransp("OPAQUE");
    public static final Transp TRANSPARENT = new ImmutableTransp("TRANSPARENT");
    public static final long serialVersionUID = 0x34c1919b72f1522eL;
    private String value;

    public Transp()
    {
        super("TRANSP", PropertyFactoryImpl.instance);
    }

    public Transp(ParameterList parameterlist, String s)
    {
        super("TRANSP", parameterlist, PropertyFactoryImpl.instance);
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


    private class ImmutableTransp extends Transp
    {

        public static final long serialVersionUID = 0xa476e776aef44304L;

        public final void setValue(String s)
        {
            throw new UnsupportedOperationException("Cannot modify constant instances");
        }

        ImmutableTransp(String s)
        {
            super(new ParameterList(true), s);
        }
    }

}
