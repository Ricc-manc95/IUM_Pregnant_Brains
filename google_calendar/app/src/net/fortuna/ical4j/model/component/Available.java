// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.util.PropertyValidator;

public final class Available extends Component
{

    public static final long serialVersionUID = 0xdd6101ea7a2a4435L;

    public Available()
    {
        super("AVAILABLE");
    }

    public Available(PropertyList propertylist)
    {
        super("AVAILABLE", propertylist);
    }

    public final void validate(boolean flag)
        throws ValidationException
    {
        PropertyValidator.assertOne("DTSTART", super.properties);
        PropertyValidator.assertOne("DTSTAMP", super.properties);
        PropertyValidator.assertOne("UID", super.properties);
        DtStart dtstart = (DtStart)super.properties.getProperty("DTSTART");
        if (Value.DATE.equals(((Property) (dtstart)).parameters.getParameter("VALUE")))
        {
            String s = String.valueOf(Value.DATE_TIME);
            throw new ValidationException((new StringBuilder(String.valueOf(s).length() + 29)).append("Property [DTSTART] must be a ").append(s).toString());
        }
        PropertyValidator.assertOneOrLess("CREATED", super.properties);
        PropertyValidator.assertOneOrLess("LAST-MODIFIED", super.properties);
        PropertyValidator.assertOneOrLess("RECURRENCE-ID", super.properties);
        PropertyValidator.assertOneOrLess("RRULE", super.properties);
        PropertyValidator.assertOneOrLess("SUMMARY", super.properties);
        if (super.properties.getProperty("DTEND") != null)
        {
            PropertyValidator.assertOne("DTEND", super.properties);
            DtEnd dtend = (DtEnd)super.properties.getProperty("DTEND");
            if (Value.DATE.equals(((Property) (dtend)).parameters.getParameter("VALUE")))
            {
                String s1 = String.valueOf(Value.DATE_TIME);
                throw new ValidationException((new StringBuilder(String.valueOf(s1).length() + 27)).append("Property [DTEND] must be a ").append(s1).toString());
            }
        } else
        {
            PropertyValidator.assertOne("DURATION", super.properties);
        }
        if (flag)
        {
            validateProperties();
        }
    }
}
