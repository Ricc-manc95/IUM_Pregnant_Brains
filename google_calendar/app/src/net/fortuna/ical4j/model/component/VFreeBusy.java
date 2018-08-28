// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import java.util.HashMap;
import java.util.Map;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.Time;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.model.property.DateProperty;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStamp;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            CalendarComponent

public final class VFreeBusy extends CalendarComponent
{

    public static final long serialVersionUID = 0xe86092d3e26fcf8L;
    private final Map methodValidators;

    public VFreeBusy()
    {
        super("VFREEBUSY");
        methodValidators = new HashMap();
        methodValidators.put(Method.PUBLISH, new PublishValidator());
        methodValidators.put(Method.REPLY, new ReplyValidator());
        methodValidators.put(Method.REQUEST, new RequestValidator());
        super.properties.add(new DtStamp());
    }

    public VFreeBusy(PropertyList propertylist)
    {
        super("VFREEBUSY", propertylist);
        methodValidators = new HashMap();
        methodValidators.put(Method.PUBLISH, new PublishValidator());
        methodValidators.put(Method.REPLY, new ReplyValidator());
        methodValidators.put(Method.REQUEST, new RequestValidator());
    }

    protected final Validator getValidator(Method method)
    {
        return (Validator)methodValidators.get(method);
    }

    public final void validate(boolean flag)
        throws ValidationException
    {
        boolean flag3 = false;
        if (!CompatibilityHints.isHintEnabled("ical4j.validation.relaxed"))
        {
            PropertyValidator.assertOne("UID", super.properties);
            PropertyValidator.assertOne("DTSTAMP", super.properties);
        }
        PropertyValidator.assertOneOrLess("CONTACT", super.properties);
        PropertyValidator.assertOneOrLess("DTSTART", super.properties);
        PropertyValidator.assertOneOrLess("DTEND", super.properties);
        PropertyValidator.assertOneOrLess("DURATION", super.properties);
        PropertyValidator.assertOneOrLess("DTSTAMP", super.properties);
        PropertyValidator.assertOneOrLess("ORGANIZER", super.properties);
        PropertyValidator.assertOneOrLess("UID", super.properties);
        PropertyValidator.assertOneOrLess("URL", super.properties);
        PropertyValidator.assertNone("RRULE", super.properties);
        PropertyValidator.assertNone("EXRULE", super.properties);
        PropertyValidator.assertNone("RDATE", super.properties);
        PropertyValidator.assertNone("EXDATE", super.properties);
        DtStart dtstart = (DtStart)super.properties.getProperty("DTSTART");
        if (dtstart != null)
        {
            boolean flag1;
            if (((DateProperty) (dtstart)).date instanceof DateTime)
            {
                flag1 = ((DateTime)((DateProperty) (dtstart)).date).time.utc;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new ValidationException("DTSTART must be specified in UTC time");
            }
        }
        DtEnd dtend = (DtEnd)super.properties.getProperty("DTEND");
        if (dtend != null)
        {
            boolean flag2 = flag3;
            if (((DateProperty) (dtend)).date instanceof DateTime)
            {
                flag2 = ((DateTime)((DateProperty) (dtend)).date).time.utc;
            }
            if (!flag2)
            {
                throw new ValidationException("DTEND must be specified in UTC time");
            }
        }
        if (dtstart != null && dtend != null && !((DateProperty) (dtstart)).date.before(((DateProperty) (dtend)).date))
        {
            throw new ValidationException("Property [DTEND] must be later in time than [DTSTART]");
        }
        if (flag)
        {
            validateProperties();
        }
    }

    private class PublishValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VFreeBusy this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOneOrMore("FREEBUSY", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("DTSTART", properties);
            PropertyValidator.assertOne("DTEND", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            PropertyValidator.assertNone("ATTENDEE", properties);
            PropertyValidator.assertNone("DURATION", properties);
            PropertyValidator.assertNone("REQUEST-STATUS", properties);
        }

        PublishValidator()
        {
            this$0 = VFreeBusy.this;
            super();
        }
    }


    private class ReplyValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VFreeBusy this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("ATTENDEE", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("DTEND", properties);
            PropertyValidator.assertOne("DTSTART", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            PropertyValidator.assertNone("DURATION", properties);
            PropertyValidator.assertNone("SEQUENCE", properties);
        }

        ReplyValidator()
        {
            this$0 = VFreeBusy.this;
            super();
        }
    }


    private class RequestValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VFreeBusy this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOneOrMore("ATTENDEE", properties);
            PropertyValidator.assertOne("DTEND", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("DTSTART", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertNone("FREEBUSY", properties);
            PropertyValidator.assertNone("DURATION", properties);
            PropertyValidator.assertNone("REQUEST-STATUS", properties);
            PropertyValidator.assertNone("URL", properties);
        }

        RequestValidator()
        {
            this$0 = VFreeBusy.this;
            super();
        }
    }

}
