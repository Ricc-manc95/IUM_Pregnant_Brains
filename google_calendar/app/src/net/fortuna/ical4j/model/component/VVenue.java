// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            CalendarComponent

public final class VVenue extends CalendarComponent
{

    public static final long serialVersionUID = 0x3e7bd1e55b996633L;

    public VVenue()
    {
        super("VVENUE");
    }

    public VVenue(PropertyList propertylist)
    {
        super("VVENUE", propertylist);
    }

    protected final Validator getValidator(Method method)
    {
        return EMPTY_VALIDATOR;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("BEGIN");
        stringbuffer.append(':');
        stringbuffer.append(super.name);
        stringbuffer.append("\r\n");
        stringbuffer.append(super.properties);
        stringbuffer.append("END");
        stringbuffer.append(':');
        stringbuffer.append(super.name);
        stringbuffer.append("\r\n");
        return stringbuffer.toString();
    }

    public final void validate(boolean flag)
        throws ValidationException
    {
        PropertyValidator.assertOne("UID", super.properties);
        PropertyValidator.assertOneOrLess("NAME", super.properties);
        PropertyValidator.assertOneOrLess("DESCRIPTION", super.properties);
        PropertyValidator.assertOneOrLess("STREET-ADDRESS", super.properties);
        PropertyValidator.assertOneOrLess("EXTENDED-ADDRESS", super.properties);
        PropertyValidator.assertOneOrLess("LOCALITY", super.properties);
        PropertyValidator.assertOneOrLess("REGION", super.properties);
        PropertyValidator.assertOneOrLess("COUNTRY", super.properties);
        PropertyValidator.assertOneOrLess("POSTAL-CODE", super.properties);
        PropertyValidator.assertOneOrLess("TZID", super.properties);
        PropertyValidator.assertOneOrLess("GEO", super.properties);
        PropertyValidator.assertOneOrLess("LOCATION-TYPE", super.properties);
        PropertyValidator.assertOneOrLess("CATEGORIES", super.properties);
        PropertyValidator.assertOneOrLess("DTSTAMP", super.properties);
        PropertyValidator.assertOneOrLess("CREATED", super.properties);
        PropertyValidator.assertOneOrLess("LAST-MODIFIED", super.properties);
        if (flag)
        {
            validateProperties();
        }
    }
}
