// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.net.URI;
import java.net.URISyntaxException;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.ParameterValidator;
import net.fortuna.ical4j.util.Strings;
import net.fortuna.ical4j.util.Uris;

public final class Organizer extends Property
{

    public static final long serialVersionUID = 0xb7999d610dae585bL;
    private URI calAddress;

    public Organizer()
    {
        super("ORGANIZER", PropertyFactoryImpl.instance);
    }

    public Organizer(URI uri)
    {
        super("ORGANIZER", PropertyFactoryImpl.instance);
        calAddress = uri;
    }

    public final String getValue()
    {
        return Uris.decode(Strings.valueOf(calAddress));
    }

    public final void setValue(String s)
        throws URISyntaxException
    {
        calAddress = Uris.create(s);
    }

    public final void validate()
        throws ValidationException
    {
        ParameterValidator.assertOneOrLess("CN", super.parameters);
        ParameterValidator.assertOneOrLess("DIR", super.parameters);
        ParameterValidator.assertOneOrLess("SENT-BY", super.parameters);
        ParameterValidator.assertOneOrLess("LANGUAGE", super.parameters);
        ParameterValidator.assertOneOrLess("SCHEDULE-STATUS", super.parameters);
    }
}
