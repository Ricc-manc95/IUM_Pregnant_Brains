// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.text.ParseException;
import java.util.Iterator;
import net.fortuna.ical4j.model.DateRange;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.PeriodList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.util.ParameterValidator;
import net.fortuna.ical4j.util.Strings;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            DateListProperty

public final class RDate extends DateListProperty
{

    public static final long serialVersionUID = 0xd1eba27b4032ce9fL;
    private PeriodList periods;

    public RDate()
    {
        super("RDATE", PropertyFactoryImpl.instance);
        periods = new PeriodList(false, true);
    }

    public final String getValue()
    {
        if (periods != null && (!periods.isEmpty() || !periods.unmodifiable))
        {
            return Strings.valueOf(periods);
        } else
        {
            return super.getValue();
        }
    }

    public final void setTimeZone(TimeZone timezone)
    {
        if (periods != null && (!periods.isEmpty() || !periods.unmodifiable))
        {
            PeriodList periodlist = periods;
            Period period;
            for (Iterator iterator = periodlist.iterator(); iterator.hasNext(); ((DateTime)((DateRange) (period)).rangeEnd).setTimeZone(timezone))
            {
                period = (Period)iterator.next();
                ((DateTime)((DateRange) (period)).rangeStart).setUtc(false);
                ((DateTime)((DateRange) (period)).rangeStart).setTimeZone(timezone);
                ((DateTime)((DateRange) (period)).rangeEnd).setUtc(false);
            }

            periodlist.timezone = timezone;
            periodlist.utc = false;
            return;
        } else
        {
            super.setTimeZone(timezone);
            return;
        }
    }

    public final void setValue(String s)
        throws ParseException
    {
        if (Value.PERIOD.equals(super.parameters.getParameter("VALUE")))
        {
            periods = new PeriodList(s);
            return;
        } else
        {
            super.setValue(s);
            return;
        }
    }

    public final void validate()
        throws ValidationException
    {
        ParameterValidator.assertOneOrLess("VALUE", super.parameters);
        Parameter parameter = super.parameters.getParameter("VALUE");
        if (parameter != null && !Value.DATE_TIME.equals(parameter) && !Value.DATE.equals(parameter) && !Value.PERIOD.equals(parameter))
        {
            throw new ValidationException("Parameter [VALUE] is invalid");
        } else
        {
            ParameterValidator.assertOneOrLess("TZID", super.parameters);
            return;
        }
    }
}
