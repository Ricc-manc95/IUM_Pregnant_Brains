// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.text.ParseException;
import java.util.List;
import net.fortuna.ical4j.model.Content;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactory;
import net.fortuna.ical4j.model.Time;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.parameter.TzId;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.util.ParameterValidator;
import net.fortuna.ical4j.util.Strings;

public class DateProperty extends Property
{

    public static final long serialVersionUID = 0x2bddb6764f127229L;
    public Date date;
    private TimeZone timeZone;

    public DateProperty(String s, PropertyFactory propertyfactory)
    {
        super(s, propertyfactory);
    }

    private final void updateTimeZone(TimeZone timezone)
    {
        timeZone = timezone;
        if (timezone != null)
        {
            if (date != null && !(date instanceof DateTime))
            {
                throw new UnsupportedOperationException("TimeZone is not applicable to current value");
            }
            if (date != null)
            {
                ((DateTime)date).setTimeZone(timezone);
            }
            super.parameters.replace(new TzId(timezone.getID()));
            return;
        }
        boolean flag;
        if (date instanceof DateTime)
        {
            flag = ((DateTime)date).time.utc;
        } else
        {
            flag = false;
        }
        setUtc(flag);
    }

    public String getValue()
    {
        return Strings.valueOf(date);
    }

    public int hashCode()
    {
        return date.hashCode();
    }

    public final boolean isUtc()
    {
        if (date instanceof DateTime)
        {
            return ((DateTime)date).time.utc;
        } else
        {
            return false;
        }
    }

    public final void setDate(Date date1)
    {
        date = date1;
        if (date1 instanceof DateTime)
        {
            if (Value.DATE.equals(super.parameters.getParameter("VALUE")))
            {
                super.parameters.replace(Value.DATE_TIME);
            }
            updateTimeZone(((DateTime)date1).timezone);
            return;
        }
        if (date1 != null)
        {
            super.parameters.replace(Value.DATE);
        }
        updateTimeZone(null);
    }

    public void setTimeZone(TimeZone timezone)
    {
        updateTimeZone(timezone);
    }

    public final void setUtc(boolean flag)
    {
        if (date != null && (date instanceof DateTime))
        {
            ((DateTime)date).setUtc(flag);
        }
        ParameterList parameterlist = super.parameters;
        Parameter parameter = super.parameters.getParameter("TZID");
        parameterlist.parameters.remove(parameter);
    }

    public void setValue(String s)
        throws ParseException
    {
        s = s.trim();
        boolean flag;
        if (s.length() == 8)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || Value.DATE.equals(super.parameters.getParameter("VALUE")))
        {
            updateTimeZone(null);
            date = new Date(s);
            return;
        } else
        {
            date = new DateTime(s, timeZone);
            return;
        }
    }

    public void validate()
        throws ValidationException
    {
        ParameterValidator.assertOneOrLess("VALUE", super.parameters);
        Object obj;
        if (isUtc())
        {
            ParameterValidator.assertNone("TZID", super.parameters);
        } else
        {
            ParameterValidator.assertOneOrLess("TZID", super.parameters);
        }
        obj = (Value)super.parameters.getParameter("VALUE");
        if (date instanceof DateTime)
        {
            if (obj != null && !Value.DATE_TIME.equals(obj))
            {
                obj = String.valueOf(obj);
                throw new ValidationException((new StringBuilder(String.valueOf(obj).length() + 52)).append("VALUE parameter [").append(((String) (obj))).append("] is invalid for DATE-TIME instance").toString());
            }
            obj = (DateTime)date;
            Object obj1 = super.parameters.getParameter("TZID");
            if (((DateTime) (obj)).timezone != null && (obj1 == null || !((Content) (obj1)).getValue().equals(((DateTime) (obj)).timezone.getID())))
            {
                obj1 = String.valueOf(obj1);
                obj = ((DateTime) (obj)).timezone.getID();
                throw new ValidationException((new StringBuilder(String.valueOf(obj1).length() + 48 + String.valueOf(obj).length())).append("TZID parameter [").append(((String) (obj1))).append("] does not match the timezone [").append(((String) (obj))).append("]").toString());
            }
        } else
        if (date != null)
        {
            if (obj == null)
            {
                obj = String.valueOf(Value.DATE);
                throw new ValidationException((new StringBuilder(String.valueOf(obj).length() + 54)).append("VALUE parameter [").append(((String) (obj))).append("] must be specified for DATE instance").toString());
            }
            if (!Value.DATE.equals(obj))
            {
                obj = String.valueOf(obj);
                throw new ValidationException((new StringBuilder(String.valueOf(obj).length() + 47)).append("VALUE parameter [").append(((String) (obj))).append("] is invalid for DATE instance").toString());
            }
        }
    }
}
