// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Escapable;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.ParameterValidator;

public final class RelatedTo extends Property
    implements Escapable
{

    public static final long serialVersionUID = 0xfe7b6bbeb31f3638L;
    private String value;

    public RelatedTo()
    {
        super("RELATED-TO", PropertyFactoryImpl.instance);
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
        ParameterValidator.assertOneOrLess("RELTYPE", super.parameters);
    }
}
