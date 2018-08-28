// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Content;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.CompatibilityHints;

public class CalScale extends Property
{

    public static final CalScale GREGORIAN = new ImmutableCalScale("GREGORIAN");
    public static final long serialVersionUID = 0x6756299cb82853afL;
    private String value;

    public CalScale()
    {
        super("CALSCALE", PropertyFactoryImpl.instance);
    }

    public CalScale(ParameterList parameterlist, String s)
    {
        super("CALSCALE", parameterlist, PropertyFactoryImpl.instance);
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
        if (CompatibilityHints.isHintEnabled("ical4j.validation.relaxed"))
        {
            if (!GREGORIAN.getValue().equalsIgnoreCase(value))
            {
                String s = value;
                throw new ValidationException((new StringBuilder(String.valueOf(s).length() + 16)).append("Invalid value [").append(s).append("]").toString());
            }
        } else
        if (!GREGORIAN.getValue().equals(value))
        {
            String s1 = value;
            throw new ValidationException((new StringBuilder(String.valueOf(s1).length() + 16)).append("Invalid value [").append(s1).append("]").toString());
        }
    }


    private class ImmutableCalScale extends CalScale
    {

        public static final long serialVersionUID = 0x184c9f56e4c53636L;

        public final void setValue(String s)
        {
            throw new UnsupportedOperationException("Cannot modify constant instances");
        }

        ImmutableCalScale(String s)
        {
            super(new ParameterList(true), s);
        }
    }

}
