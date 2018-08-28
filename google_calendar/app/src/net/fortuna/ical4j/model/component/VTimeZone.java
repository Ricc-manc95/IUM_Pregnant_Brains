// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import java.util.Iterator;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.util.PropertyValidator;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            CalendarComponent, Observance

public final class VTimeZone extends CalendarComponent
{

    public static final long serialVersionUID = 0x4e20a3e5ac8d93b7L;
    private final Validator itipValidator;
    public ComponentList observances;

    public VTimeZone()
    {
        super("VTIMEZONE");
        itipValidator = new ITIPValidator();
        observances = new ComponentList();
    }

    public VTimeZone(PropertyList propertylist)
    {
        super("VTIMEZONE", propertylist);
        itipValidator = new ITIPValidator();
        observances = new ComponentList();
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof VTimeZone)
        {
            return super.equals(obj) && ObjectUtils.equals(observances, ((VTimeZone)obj).observances);
        } else
        {
            return super.equals(obj);
        }
    }

    public final Observance getApplicableObservance(Date date)
    {
        Object obj1 = null;
        Iterator iterator = observances.iterator();
        Object obj = null;
        while (iterator.hasNext()) 
        {
            Observance observance = (Observance)iterator.next();
            Object obj2 = observance.getLatestOnset(date);
            if (obj1 == null || obj2 != null && ((Date) (obj2)).after(((java.util.Date) (obj1))))
            {
                obj1 = observance;
                obj = obj2;
            } else
            {
                Object obj3 = obj;
                obj = obj1;
                obj1 = obj3;
            }
            obj2 = obj1;
            obj1 = obj;
            obj = obj2;
        }
        return ((Observance) (obj));
    }

    protected final Validator getValidator(Method method)
    {
        return itipValidator;
    }

    public final int hashCode()
    {
        return (new HashCodeBuilder()).append(super.name).append(super.properties).append(observances).iTotal;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("BEGIN");
        stringbuffer.append(':');
        stringbuffer.append(super.name);
        stringbuffer.append("\r\n");
        stringbuffer.append(super.properties);
        stringbuffer.append(observances);
        stringbuffer.append("END");
        stringbuffer.append(':');
        stringbuffer.append(super.name);
        stringbuffer.append("\r\n");
        return stringbuffer.toString();
    }

    public final void validate(boolean flag)
        throws ValidationException
    {
        PropertyValidator.assertOne("TZID", super.properties);
        PropertyValidator.assertOneOrLess("LAST-MODIFIED", super.properties);
        PropertyValidator.assertOneOrLess("TZURL", super.properties);
        if (observances.getComponent("STANDARD") == null && observances.getComponent("DAYLIGHT") == null)
        {
            throw new ValidationException("Sub-components [STANDARD,DAYLIGHT] must be specified at least once");
        }
        for (Iterator iterator = observances.iterator(); iterator.hasNext(); ((Component)iterator.next()).validate(flag)) { }
        if (flag)
        {
            validateProperties();
        }
    }

    private class ITIPValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VTimeZone this$0;

        public final void validate()
            throws ValidationException
        {
            Observance observance;
            for (Iterator iterator = observances.iterator(); iterator.hasNext(); PropertyValidator.assertOneOrLess("TZNAME", ((Component) (observance)).properties))
            {
                observance = (Observance)iterator.next();
                PropertyValidator.assertOne("DTSTART", ((Component) (observance)).properties);
                PropertyValidator.assertOne("TZOFFSETFROM", ((Component) (observance)).properties);
                PropertyValidator.assertOne("TZOFFSETTO", ((Component) (observance)).properties);
            }

        }

        ITIPValidator()
        {
            this$0 = VTimeZone.this;
            super();
        }
    }

}
