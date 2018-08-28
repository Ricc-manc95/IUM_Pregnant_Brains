// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.TextList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.ParameterValidator;

public final class Resources extends Property
{

    public static final long serialVersionUID = 0xf4394cec3f2c9c49L;
    private TextList resources;

    public Resources()
    {
        super("RESOURCES", PropertyFactoryImpl.instance);
        resources = new TextList();
    }

    public final String getValue()
    {
        return resources.toString();
    }

    public final void setValue(String s)
    {
        resources = new TextList(s);
    }

    public final void validate()
        throws ValidationException
    {
        ParameterValidator.assertOneOrLess("ALTREP", super.parameters);
        ParameterValidator.assertOneOrLess("LANGUAGE", super.parameters);
    }
}
