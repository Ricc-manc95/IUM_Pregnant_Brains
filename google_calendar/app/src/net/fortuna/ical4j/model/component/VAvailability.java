// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import java.util.Iterator;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStamp;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            CalendarComponent, Available

public final class VAvailability extends CalendarComponent
{

    public static final long serialVersionUID = 0xd65829b1828c1386L;
    public ComponentList available;

    public VAvailability()
    {
        super("VAVAILABILITY");
        available = new ComponentList();
        super.properties.add(new DtStamp());
    }

    public VAvailability(PropertyList propertylist)
    {
        super("VAVAILABILITY", propertylist);
        available = new ComponentList();
    }

    protected final Validator getValidator(Method method)
    {
        return null;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("BEGIN");
        stringbuffer.append(':');
        stringbuffer.append(super.name);
        stringbuffer.append("\r\n");
        stringbuffer.append(super.properties);
        stringbuffer.append(available);
        stringbuffer.append("END");
        stringbuffer.append(':');
        stringbuffer.append(super.name);
        stringbuffer.append("\r\n");
        return stringbuffer.toString();
    }

    public final void validate(boolean flag)
        throws ValidationException
    {
        for (Iterator iterator = available.iterator(); iterator.hasNext();)
        {
            Component component = (Component)iterator.next();
            if (!(component instanceof Available))
            {
                String s = component.name;
                throw new ValidationException((new StringBuilder(String.valueOf(s).length() + 43)).append("Component [").append(s).append("] may not occur in VAVAILABILITY").toString());
            }
        }

        PropertyValidator.assertOne("DTSTART", super.properties);
        PropertyValidator.assertOne("DTSTAMP", super.properties);
        PropertyValidator.assertOne("UID", super.properties);
        DtStart dtstart = (DtStart)super.properties.getProperty("DTSTART");
        if (Value.DATE.equals(((Property) (dtstart)).parameters.getParameter("VALUE")))
        {
            String s1 = String.valueOf(Value.DATE_TIME);
            throw new ValidationException((new StringBuilder(String.valueOf(s1).length() + 29)).append("Property [DTSTART] must be a ").append(s1).toString());
        }
        if (super.properties.getProperty("DTEND") != null)
        {
            PropertyValidator.assertOne("DTEND", super.properties);
            DtEnd dtend = (DtEnd)super.properties.getProperty("DTEND");
            if (Value.DATE.equals(((Property) (dtend)).parameters.getParameter("VALUE")))
            {
                String s2 = String.valueOf(Value.DATE_TIME);
                throw new ValidationException((new StringBuilder(String.valueOf(s2).length() + 27)).append("Property [DTEND] must be a ").append(s2).toString());
            }
            if (super.properties.getProperty("DURATION") != null)
            {
                throw new ValidationException("Only one of Property [DTEND] or [DURATION must appear a VAVAILABILITY");
            }
        }
        PropertyValidator.assertOneOrLess("BUSYTYPE", super.properties);
        PropertyValidator.assertOneOrLess("CREATED", super.properties);
        PropertyValidator.assertOneOrLess("LAST-MODIFIED", super.properties);
        PropertyValidator.assertOneOrLess("ORGANIZER", super.properties);
        PropertyValidator.assertOneOrLess("SEQUENCE", super.properties);
        PropertyValidator.assertOneOrLess("SUMMARY", super.properties);
        PropertyValidator.assertOneOrLess("URL", super.properties);
        if (flag)
        {
            validateProperties();
        }
    }
}
