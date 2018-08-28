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

public class Attendee extends Property
{

    public static final long serialVersionUID = 0x7500ada329648df3L;
    private URI calAddress;

    public Attendee()
    {
        super("ATTENDEE", PropertyFactoryImpl.instance);
    }

    public Attendee(URI uri)
    {
        super("ATTENDEE", PropertyFactoryImpl.instance);
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
        ParameterValidator.assertOneOrLess("CUTYPE", super.parameters);
        ParameterValidator.assertOneOrLess("MEMBER", super.parameters);
        ParameterValidator.assertOneOrLess("ROLE", super.parameters);
        ParameterValidator.assertOneOrLess("PARTSTAT", super.parameters);
        ParameterValidator.assertOneOrLess("RSVP", super.parameters);
        ParameterValidator.assertOneOrLess("DELEGATED-TO", super.parameters);
        ParameterValidator.assertOneOrLess("DELEGATED-FROM", super.parameters);
        ParameterValidator.assertOneOrLess("SENT-BY", super.parameters);
        ParameterValidator.assertOneOrLess("CN", super.parameters);
        ParameterValidator.assertOneOrLess("DIR", super.parameters);
        ParameterValidator.assertOneOrLess("LANGUAGE", super.parameters);
        ParameterValidator.assertOneOrLess("SCHEDULE-AGENT", super.parameters);
        ParameterValidator.assertOneOrLess("SCHEDULE-STATUS", super.parameters);
    }
}
