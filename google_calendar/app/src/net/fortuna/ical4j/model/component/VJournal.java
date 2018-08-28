// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import java.util.HashMap;
import java.util.Map;
import net.fortuna.ical4j.model.Component;
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

// Referenced classes of package net.fortuna.ical4j.model.component:
//            CalendarComponent

public final class VJournal extends CalendarComponent
{

    public static final long serialVersionUID = 0x960a87c269e4ed52L;
    private final Map methodValidators;

    public VJournal()
    {
        super("VJOURNAL");
        methodValidators = new HashMap();
        methodValidators.put(Method.ADD, new AddValidator());
        methodValidators.put(Method.CANCEL, new CancelValidator());
        methodValidators.put(Method.PUBLISH, new PublishValidator());
        super.properties.add(new DtStamp());
    }

    public VJournal(PropertyList propertylist)
    {
        super("VJOURNAL", propertylist);
        methodValidators = new HashMap();
        methodValidators.put(Method.ADD, new AddValidator());
        methodValidators.put(Method.CANCEL, new CancelValidator());
        methodValidators.put(Method.PUBLISH, new PublishValidator());
    }

    protected final Validator getValidator(Method method)
    {
        return (Validator)methodValidators.get(method);
    }

    public final void validate(boolean flag)
        throws ValidationException
    {
        if (!CompatibilityHints.isHintEnabled("ical4j.validation.relaxed"))
        {
            PropertyValidator.assertOne("UID", super.properties);
            PropertyValidator.assertOne("DTSTAMP", super.properties);
        }
        PropertyValidator.assertOneOrLess("CLASS", super.properties);
        PropertyValidator.assertOneOrLess("CREATED", super.properties);
        PropertyValidator.assertOneOrLess("DESCRIPTION", super.properties);
        PropertyValidator.assertOneOrLess("DTSTART", super.properties);
        PropertyValidator.assertOneOrLess("DTSTAMP", super.properties);
        PropertyValidator.assertOneOrLess("LAST-MODIFIED", super.properties);
        PropertyValidator.assertOneOrLess("ORGANIZER", super.properties);
        PropertyValidator.assertOneOrLess("RECURRENCE-ID", super.properties);
        PropertyValidator.assertOneOrLess("SEQUENCE", super.properties);
        PropertyValidator.assertOneOrLess("STATUS", super.properties);
        PropertyValidator.assertOneOrLess("SUMMARY", super.properties);
        PropertyValidator.assertOneOrLess("UID", super.properties);
        PropertyValidator.assertOneOrLess("URL", super.properties);
        Object obj = (Status)super.properties.getProperty("STATUS");
        if (obj != null && !Status.VJOURNAL_DRAFT.getValue().equals(((Content) (obj)).getValue()) && !Status.VJOURNAL_FINAL.getValue().equals(((Content) (obj)).getValue()) && !Status.VJOURNAL_CANCELLED.getValue().equals(((Content) (obj)).getValue()))
        {
            obj = ((Property) (obj)).toString();
            throw new ValidationException((new StringBuilder(String.valueOf(obj).length() + 44)).append("Status property [").append(((String) (obj))).append("] may not occur in VJOURNAL").toString());
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
        private final VJournal this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("DESCRIPTION", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("DTSTART", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("SEQUENCE", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("CATEGORIES", properties);
            PropertyValidator.assertOneOrLess("CLASS", properties);
            PropertyValidator.assertOneOrLess("CREATED", properties);
            PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
            PropertyValidator.assertOneOrLess("STATUS", properties);
            PropertyValidator.assertOneOrLess("SUMMARY", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            PropertyValidator.assertNone("ATTENDEE", properties);
            PropertyValidator.assertNone("RECURRENCE-ID", properties);
        }

        AddValidator()
        {
            this$0 = VJournal.this;
            super();
        }
    }


    private class CancelValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VJournal this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("SEQUENCE", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("CATEGORIES", properties);
            PropertyValidator.assertOneOrLess("CLASS", properties);
            PropertyValidator.assertOneOrLess("CREATED", properties);
            PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
            PropertyValidator.assertOneOrLess("DTSTART", properties);
            PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
            PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
            PropertyValidator.assertOneOrLess("STATUS", properties);
            PropertyValidator.assertOneOrLess("SUMMARY", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            PropertyValidator.assertNone("REQUEST-STATUS", properties);
        }

        CancelValidator()
        {
            this$0 = VJournal.this;
            super();
        }
    }


    private class PublishValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VJournal this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("DESCRIPTION", properties);
            PropertyValidator.assertOne("DTSTAMP", properties);
            PropertyValidator.assertOne("DTSTART", properties);
            PropertyValidator.assertOne("ORGANIZER", properties);
            PropertyValidator.assertOne("UID", properties);
            PropertyValidator.assertOneOrLess("CATEGORIES", properties);
            PropertyValidator.assertOneOrLess("CLASS", properties);
            PropertyValidator.assertOneOrLess("CREATED", properties);
            PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
            PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
            PropertyValidator.assertOneOrLess("SEQUENCE", properties);
            PropertyValidator.assertOneOrLess("STATUS", properties);
            PropertyValidator.assertOneOrLess("SUMMARY", properties);
            PropertyValidator.assertOneOrLess("URL", properties);
            PropertyValidator.assertNone("ATTENDEE", properties);
        }

        PublishValidator()
        {
            this$0 = VJournal.this;
            super();
        }
    }

}
