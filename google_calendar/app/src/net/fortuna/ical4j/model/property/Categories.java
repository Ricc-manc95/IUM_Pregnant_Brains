// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.TextList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.ParameterValidator;

public final class Categories extends Property
{

    public static final long serialVersionUID = 0x942b75ec80a436deL;
    private TextList categories;

    public Categories()
    {
        super("CATEGORIES", PropertyFactoryImpl.instance);
        categories = new TextList();
    }

    public final String getValue()
    {
        return categories.toString();
    }

    public final void setValue(String s)
    {
        categories = new TextList(s);
    }

    public final void validate()
        throws ValidationException
    {
        ParameterValidator.assertOneOrLess("LANGUAGE", super.parameters);
    }
}
