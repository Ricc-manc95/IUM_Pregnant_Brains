// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import java.util.HashMap;
import java.util.Map;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.model.property.Action;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            CalendarComponent

public final class VAlarm extends CalendarComponent
{

    public static final long serialVersionUID = 0x8e492fcfd0e2b096L;
    private final Map actionValidators;
    private final Validator itipValidator;

    public VAlarm()
    {
        super("VALARM");
        actionValidators = new HashMap();
        actionValidators.put(Action.AUDIO, new AudioValidator());
        actionValidators.put(Action.DISPLAY, new DisplayValidator());
        actionValidators.put(Action.EMAIL, new EmailValidator());
        actionValidators.put(Action.PROCEDURE, new ProcedureValidator());
        itipValidator = new ITIPValidator();
    }

    public VAlarm(PropertyList propertylist)
    {
        super("VALARM", propertylist);
        actionValidators = new HashMap();
        actionValidators.put(Action.AUDIO, new AudioValidator());
        actionValidators.put(Action.DISPLAY, new DisplayValidator());
        actionValidators.put(Action.EMAIL, new EmailValidator());
        actionValidators.put(Action.PROCEDURE, new ProcedureValidator());
        itipValidator = new ITIPValidator();
    }

    protected final Validator getValidator(Method method)
    {
        return itipValidator;
    }

    public final void validate(boolean flag)
        throws ValidationException
    {
        PropertyValidator.assertOne("ACTION", super.properties);
        PropertyValidator.assertOne("TRIGGER", super.properties);
        PropertyValidator.assertOneOrLess("DURATION", super.properties);
        PropertyValidator.assertOneOrLess("REPEAT", super.properties);
        Validator validator;
        try
        {
            PropertyValidator.assertNone("DURATION", super.properties);
            PropertyValidator.assertNone("REPEAT", super.properties);
        }
        catch (ValidationException validationexception)
        {
            PropertyValidator.assertOne("DURATION", super.properties);
            PropertyValidator.assertOne("REPEAT", super.properties);
        }
        validator = (Validator)actionValidators.get((Action)super.properties.getProperty("ACTION"));
        if (validator != null)
        {
            validator.validate();
        }
        if (flag)
        {
            validateProperties();
        }
    }

    private class AudioValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VAlarm this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOneOrLess("ATTACH", properties);
        }

        AudioValidator()
        {
            this$0 = VAlarm.this;
            super();
        }
    }


    private class DisplayValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VAlarm this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("DESCRIPTION", properties);
        }

        DisplayValidator()
        {
            this$0 = VAlarm.this;
            super();
        }
    }


    private class EmailValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VAlarm this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("DESCRIPTION", properties);
            PropertyValidator.assertOne("SUMMARY", properties);
            PropertyValidator.assertOneOrMore("ATTENDEE", properties);
        }

        EmailValidator()
        {
            this$0 = VAlarm.this;
            super();
        }
    }


    private class ProcedureValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VAlarm this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("ATTACH", properties);
            PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
        }

        ProcedureValidator()
        {
            this$0 = VAlarm.this;
            super();
        }
    }


    private class ITIPValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;
        private final VAlarm this$0;

        public final void validate()
            throws ValidationException
        {
            PropertyValidator.assertOne("ACTION", properties);
            PropertyValidator.assertOne("TRIGGER", properties);
            PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
            PropertyValidator.assertOneOrLess("DURATION", properties);
            PropertyValidator.assertOneOrLess("REPEAT", properties);
            PropertyValidator.assertOneOrLess("SUMMARY", properties);
        }

        ITIPValidator()
        {
            this$0 = VAlarm.this;
            super();
        }
    }

}
