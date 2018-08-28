// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Content;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.model.property.DtStamp;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.PropertyValidator;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            CalendarComponent, VAlarm

public final class VToDo extends CalendarComponent
{

    public static final long serialVersionUID = 0xfc41fb4962412b24L;
    public ComponentList alarms;
    private final Map methodValidators;

    public VToDo()
    {
        super("VTODO");
        methodValidators = new HashMap();
        methodValidators.put(Method.ADD, new AddValidator());
        methodValidators.put(Method.CANCEL, new CancelValidator());
        methodValidators.put(Method.COUNTER, new CounterValidator());
        methodValidators.put(Method.DECLINE_COUNTER, new DeclineCounterValidator());
        methodValidators.put(Method.PUBLISH, new PublishValidator());
        methodValidators.put(Method.REFRESH, new RefreshValidator());
        methodValidators.put(Method.REPLY, new ReplyValidator());
        methodValidators.put(Method.REQUEST, new RequestValidator());
        alarms = new ComponentList();
        super.properties.add(new DtStamp());
    }

    public VToDo(PropertyList propertylist)
    {
        super("VTODO", propertylist);
        methodValidators = new HashMap();
        methodValidators.put(Method.ADD, new AddValidator());
        methodValidators.put(Method.CANCEL, new CancelValidator());
        methodValidators.put(Method.COUNTER, new CounterValidator());
        methodValidators.put(Method.DECLINE_COUNTER, new DeclineCounterValidator());
        methodValidators.put(Method.PUBLISH, new PublishValidator());
        methodValidators.put(Method.REFRESH, new RefreshValidator());
        methodValidators.put(Method.REPLY, new ReplyValidator());
        methodValidators.put(Method.REQUEST, new RequestValidator());
        alarms = new ComponentList();
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof VToDo)
        {
            return super.equals(obj) && ObjectUtils.equals(alarms, ((VToDo)obj).alarms);
        } else
        {
            return super.equals(obj);
        }
    }

    protected final Validator getValidator(Method method)
    {
        return (Validator)methodValidators.get(method);
    }

    public final int hashCode()
    {
        return (new HashCodeBuilder()).append(super.name).append(super.properties).append(alarms).iTotal;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("BEGIN");
        stringbuffer.append(':');
        stringbuffer.append(super.name);
        stringbuffer.append("\r\n");
        stringbuffer.append(super.properties);
        stringbuffer.append(alarms);
        stringbuffer.append("END");
        stringbuffer.append(':');
        stringbuffer.append(super.name);
        stringbuffer.append("\r\n");
        return stringbuffer.toString();
    }

    public final void validate(boolean flag)
        throws ValidationException
    {
        Component component;
        for (Iterator iterator = alarms.iterator(); iterator.hasNext(); ((VAlarm)component).validate(flag))
        {
            component = (Component)iterator.next();
            if (!(component instanceof VAlarm))
            {
                String s = component.name;
                throw new ValidationException((new StringBuilder(String.valueOf(s).length() + 35)).append("Component [").append(s).append("] may not occur in VTODO").toString());
            }
        }

        if (!CompatibilityHints.isHintEnabled("ical4j.validation.relaxed"))
        {
            PropertyValidator.assertOne("UID", super.properties);
            PropertyValidator.assertOne("DTSTAMP", super.properties);
        }
        PropertyValidator.assertOneOrLess("CLASS", super.properties);
        PropertyValidator.assertOneOrLess("COMPLETED", super.properties);
        PropertyValidator.assertOneOrLess("CREATED", super.properties);
        PropertyValidator.assertOneOrLess("DESCRIPTION", super.properties);
        PropertyValidator.assertOneOrLess("DTSTAMP", super.properties);
        PropertyValidator.assertOneOrLess("DTSTART", super.properties);
        PropertyValidator.assertOneOrLess("GEO", super.properties);
        PropertyValidator.assertOneOrLess("LAST-MODIFIED", super.properties);
        PropertyValidator.assertOneOrLess("LOCATION", super.properties);
        PropertyValidator.assertOneOrLess("ORGANIZER", super.properties);
        PropertyValidator.assertOneOrLess("PERCENT-COMPLETE", super.properties);
        PropertyValidator.assertOneOrLess("PRIORITY", super.properties);
        PropertyValidator.assertOneOrLess("RECURRENCE-ID", super.properties);
        PropertyValidator.assertOneOrLess("SEQUENCE", super.properties);
        PropertyValidator.assertOneOrLess("STATUS", super.properties);
        PropertyValidator.assertOneOrLess("SUMMARY", super.properties);
        PropertyValidator.assertOneOrLess("UID", super.properties);
        PropertyValidator.assertOneOrLess("URL", super.properties);
        Object obj = (Status)super.properties.getProperty("STATUS");
        if (obj != null && !Status.VTODO_NEEDS_ACTION.getValue().equals(((Content) (obj)).getValue()) && !Status.VTODO_COMPLETED.getValue().equals(((Content) (obj)).getValue()) && !Status.VTODO_IN_PROCESS.getValue().equals(((Content) (obj)).getValue()) && !Status.VTODO_CANCELLED.getValue().equals(((Content) (obj)).getValue()))
        {
            obj = ((Property) (obj)).toString();
            throw new ValidationException((new StringBuilder(String.valueOf(obj).length() + 41)).append("Status property [").append(((String) (obj))).append("] may not occur in VTODO").toString());
        }
        try
        {
            PropertyValidator.assertNone("DUE", super.properties);
        }
        catch (ValidationException validationexception)
        {
            PropertyValidator.assertNone("DURATION", super.properties);
        }
        if (flag)
        {
            validateProperties();
        }
    }

    private class AddValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VToDo this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("PRIORITY", properties);
            PropertyValidator.assertOne("SEQUENCE", properties);
            PropertyValidator.assertOne("SUMMARY", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("CATEGORIES", properties);
            PropertyValidator.assertOneOrLess("CLASS", properties);
            PropertyValidator.assertOneOrLess("CREATED", properties);
            PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
            PropertyValidator.assertOneOrLess("DTSTART", properties);
            PropertyValidator.assertOneOrLess("DUE", properties);
            PropertyValidator.assertOneOrLess("DURATION", properties);
            PropertyValidator.assertOneOrLess("GEO", properties);
            PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
            PropertyValidator.assertOneOrLess("LOCATION", properties);
            PropertyValidator.assertOneOrLess("PERCENT-COMPLETE", properties);
            PropertyValidator.assertOneOrLess("RESOURCES", properties);
            PropertyValidator.assertOneOrLess("STATUS", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            PropertyValidator.assertNone("RECURRENCE-ID", properties);
            PropertyValidator.assertNone("REQUEST-STATUS", properties);
            for (Iterator iterator = alarms.iterator(); iterator.hasNext(); ((VAlarm)iterator.next()).validate(Method.ADD)) { }
        }

        AddValidator()
        {
            this$0 = VToDo.this;
            super();
        }
    }


    private class CancelValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VToDo this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("SEQUENCE", properties);
            PropertyValidator.assertOneOrLess("CATEGORIES", properties);
            PropertyValidator.assertOneOrLess("CLASS", properties);
            PropertyValidator.assertOneOrLess("CREATED", properties);
            PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
            PropertyValidator.assertOneOrLess("DTSTART", properties);
            PropertyValidator.assertOneOrLess("DUE", properties);
            PropertyValidator.assertOneOrLess("DURATION", properties);
            PropertyValidator.assertOneOrLess("GEO", properties);
            PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
            PropertyValidator.assertOneOrLess("LOCATION", properties);
            PropertyValidator.assertOneOrLess("PERCENT-COMPLETE", properties);
            PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
            PropertyValidator.assertOneOrLess("RESOURCES", properties);
            PropertyValidator.assertOneOrLess("PRIORITY", properties);
            PropertyValidator.assertOneOrLess("STATUS", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            PropertyValidator.assertNone("REQUEST-STATUS", properties);
            if (alarms.getComponent("VALARM") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VALARM"
                });
            } else
            {
                return;
            }
        }

        CancelValidator()
        {
            this$0 = VToDo.this;
            super();
        }
    }


    private class CounterValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VToDo this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOneOrMore("ATTENDEE", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("PRIORITY", properties);
            PropertyValidator.assertOne("SUMMARY", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("CATEGORIES", properties);
            PropertyValidator.assertOneOrLess("CLASS", properties);
            PropertyValidator.assertOneOrLess("CREATED", properties);
            PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
            PropertyValidator.assertOneOrLess("DTSTART", properties);
            PropertyValidator.assertOneOrLess("DUE", properties);
            PropertyValidator.assertOneOrLess("DURATION", properties);
            PropertyValidator.assertOneOrLess("GEO", properties);
            PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
            PropertyValidator.assertOneOrLess("LOCATION", properties);
            PropertyValidator.assertOneOrLess("PERCENT-COMPLETE", properties);
            PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
            PropertyValidator.assertOneOrLess("RESOURCES", properties);
            PropertyValidator.assertOneOrLess("RRULE", properties);
            PropertyValidator.assertOneOrLess("SEQUENCE", properties);
            PropertyValidator.assertOneOrLess("STATUS", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            for (Iterator iterator = alarms.iterator(); iterator.hasNext(); ((VAlarm)iterator.next()).validate(Method.COUNTER)) { }
        }

        CounterValidator()
        {
            this$0 = VToDo.this;
            super();
        }
    }


    private class DeclineCounterValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VToDo this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOneOrMore("ATTENDEE", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("SEQUENCE", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("CATEGORIES", properties);
            PropertyValidator.assertOneOrLess("CLASS", properties);
            PropertyValidator.assertOneOrLess("CREATED", properties);
            PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
            PropertyValidator.assertOneOrLess("DTSTART", properties);
            PropertyValidator.assertOneOrLess("DUE", properties);
            PropertyValidator.assertOneOrLess("DURATION", properties);
            PropertyValidator.assertOneOrLess("GEO", properties);
            PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
            PropertyValidator.assertOneOrLess("LOCATION", properties);
            PropertyValidator.assertOneOrLess("PERCENT-COMPLETE", properties);
            PropertyValidator.assertOneOrLess("PRIORITY", properties);
            PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
            PropertyValidator.assertOneOrLess("RESOURCES", properties);
            PropertyValidator.assertOneOrLess("STATUS", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            if (alarms.getComponent("VALARM") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VALARM"
                });
            } else
            {
                return;
            }
        }

        DeclineCounterValidator()
        {
            this$0 = VToDo.this;
            super();
        }
    }


    private class PublishValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VToDo this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("DTSTAMP", properties);
            if (!CompatibilityHints.isHintEnabled("ical4j.validation.relaxed"))
            {
                PropertyValidator.assertOne("ORGANIZER", properties);
                PropertyValidator.assertOne("PRIORITY", properties);
            }
            PropertyValidator.assertOne("SUMMARY", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("DTSTART", properties);
            PropertyValidator.assertOneOrLess("SEQUENCE", properties);
            PropertyValidator.assertOneOrLess("CATEGORIES", properties);
            PropertyValidator.assertOneOrLess("CLASS", properties);
            PropertyValidator.assertOneOrLess("CREATED", properties);
            PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
            PropertyValidator.assertOneOrLess("DUE", properties);
            PropertyValidator.assertOneOrLess("DURATION", properties);
            PropertyValidator.assertOneOrLess("GEO", properties);
            PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
            PropertyValidator.assertOneOrLess("LOCATION", properties);
            PropertyValidator.assertOneOrLess("PERCENT-COMPLETE", properties);
            PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
            PropertyValidator.assertOneOrLess("RESOURCES", properties);
            PropertyValidator.assertOneOrLess("STATUS", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            PropertyValidator.assertNone("ATTENDEE", properties);
            PropertyValidator.assertNone("REQUEST-STATUS", properties);
            for (Iterator iterator = alarms.iterator(); iterator.hasNext(); ((VAlarm)iterator.next()).validate(Method.PUBLISH)) { }
        }

        PublishValidator()
        {
            this$0 = VToDo.this;
            super();
        }
    }


    private class RefreshValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VToDo this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("ATTENDEE", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
            PropertyValidator.assertNone("ATTACH", properties);
            PropertyValidator.assertNone("CATEGORIES", properties);
            PropertyValidator.assertNone("CLASS", properties);
            PropertyValidator.assertNone("CONTACT", properties);
            PropertyValidator.assertNone("CREATED", properties);
            PropertyValidator.assertNone("DESCRIPTION", properties);
            PropertyValidator.assertNone("DTSTART", properties);
            PropertyValidator.assertNone("DUE", properties);
            PropertyValidator.assertNone("DURATION", properties);
            PropertyValidator.assertNone("EXDATE", properties);
            PropertyValidator.assertNone("EXRULE", properties);
            PropertyValidator.assertNone("GEO", properties);
            PropertyValidator.assertNone("LAST-MODIFIED", properties);
            PropertyValidator.assertNone("LOCATION", properties);
            PropertyValidator.assertNone("ORGANIZER", properties);
            PropertyValidator.assertNone("PERCENT-COMPLETE", properties);
            PropertyValidator.assertNone("PRIORITY", properties);
            PropertyValidator.assertNone("RDATE", properties);
            PropertyValidator.assertNone("RELATED-TO", properties);
            PropertyValidator.assertNone("REQUEST-STATUS", properties);
            PropertyValidator.assertNone("RESOURCES", properties);
            PropertyValidator.assertNone("RRULE", properties);
            PropertyValidator.assertNone("SEQUENCE", properties);
            PropertyValidator.assertNone("STATUS", properties);
            PropertyValidator.assertNone("URL", properties);
            if (alarms.getComponent("VALARM") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VALARM"
                });
            } else
            {
                return;
            }
        }

        RefreshValidator()
        {
            this$0 = VToDo.this;
            super();
        }
    }


    private class ReplyValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VToDo this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOneOrMore("ATTENDEE", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("CATEGORIES", properties);
            PropertyValidator.assertOneOrLess("CLASS", properties);
            PropertyValidator.assertOneOrLess("CREATED", properties);
            PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
            PropertyValidator.assertOneOrLess("DTSTART", properties);
            PropertyValidator.assertOneOrLess("DUE", properties);
            PropertyValidator.assertOneOrLess("DURATION", properties);
            PropertyValidator.assertOneOrLess("GEO", properties);
            PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
            PropertyValidator.assertOneOrLess("LOCATION", properties);
            PropertyValidator.assertOneOrLess("PERCENT-COMPLETE", properties);
            PropertyValidator.assertOneOrLess("PRIORITY", properties);
            PropertyValidator.assertOneOrLess("RESOURCES", properties);
            PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
            PropertyValidator.assertOneOrLess("SEQUENCE", properties);
            PropertyValidator.assertOneOrLess("STATUS", properties);
            PropertyValidator.assertOneOrLess("SUMMARY", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            if (alarms.getComponent("VALARM") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VALARM"
                });
            } else
            {
                return;
            }
        }

        ReplyValidator()
        {
            this$0 = VToDo.this;
            super();
        }
    }


    private class RequestValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VToDo this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOneOrMore("ATTENDEE", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("DTSTART", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("PRIORITY", properties);
            PropertyValidator.assertOne("SUMMARY", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("SEQUENCE", properties);
            PropertyValidator.assertOneOrLess("CATEGORIES", properties);
            PropertyValidator.assertOneOrLess("CLASS", properties);
            PropertyValidator.assertOneOrLess("CREATED", properties);
            PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
            PropertyValidator.assertOneOrLess("DUE", properties);
            PropertyValidator.assertOneOrLess("DURATION", properties);
            PropertyValidator.assertOneOrLess("GEO", properties);
            PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
            PropertyValidator.assertOneOrLess("LOCATION", properties);
            PropertyValidator.assertOneOrLess("PERCENT-COMPLETE", properties);
            PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
            PropertyValidator.assertOneOrLess("RESOURCES", properties);
            PropertyValidator.assertOneOrLess("STATUS", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            PropertyValidator.assertNone("REQUEST-STATUS", properties);
            for (Iterator iterator = alarms.iterator(); iterator.hasNext(); ((VAlarm)iterator.next()).validate(Method.REQUEST)) { }
        }

        RequestValidator()
        {
            this$0 = VToDo.this;
            super();
        }
    }

}
