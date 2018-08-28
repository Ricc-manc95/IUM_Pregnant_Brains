// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.text.ParseException;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.util.ParameterValidator;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            UtcProperty, DateProperty

public final class Trigger extends UtcProperty
{

    public static final long serialVersionUID = 0x46132619c0b71e52L;
    private Dur duration;

    public Trigger()
    {
        super("TRIGGER", PropertyFactoryImpl.instance);
    }

    public final String getValue()
    {
        if (duration != null)
        {
            return duration.toString();
        } else
        {
            return super.getValue();
        }
    }

    public final void setDateTime(DateTime datetime)
    {
        super.setDateTime(datetime);
        duration = null;
        super.parameters.replace(Value.DATE_TIME);
    }

    public final void setValue(String s)
    {
        try
        {
            super.setValue(s);
            duration = null;
            return;
        }
        catch (ParseException parseexception)
        {
            duration = new Dur(s);
        }
        super.setDateTime(null);
    }

    public final void validate()
        throws ValidationException
    {
        super.validate();
        Parameter parameter = super.parameters.getParameter("RELATED");
        Parameter parameter1 = super.parameters.getParameter("VALUE");
        if (parameter != null || !Value.DATE_TIME.equals(parameter1))
        {
            ParameterValidator.assertOneOrLess("RELATED", super.parameters);
            ParameterValidator.assertNullOrEqual(Value.DURATION, super.parameters);
            if (duration == null)
            {
                throw new ValidationException("Duration value not specified");
            }
        } else
        {
            ParameterValidator.assertOne("VALUE", super.parameters);
            ParameterValidator.assertNullOrEqual(Value.DATE_TIME, super.parameters);
            if ((DateTime)super.date == null)
            {
                throw new ValidationException("DATE-TIME value not specified");
            }
        }
    }
}
