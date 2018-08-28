// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.text.ParseException;
import java.util.List;
import net.fortuna.ical4j.model.DateList;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactory;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.parameter.TzId;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.util.Strings;

public abstract class DateListProperty extends Property
{

    public static final long serialVersionUID = 0x48a218e5be08c16fL;
    public DateList dates;
    private TimeZone timeZone;

    private DateListProperty(String s, DateList datelist, PropertyFactory propertyfactory)
    {
        this(s, new ParameterList(), datelist, propertyfactory);
    }

    private DateListProperty(String s, ParameterList parameterlist, DateList datelist, PropertyFactory propertyfactory)
    {
        super(s, parameterlist, propertyfactory);
        dates = datelist;
        if (datelist != null && !Value.DATE_TIME.equals(datelist.type))
        {
            super.parameters.replace(datelist.type);
        }
    }

    public DateListProperty(String s, PropertyFactory propertyfactory)
    {
        this(s, new DateList(Value.DATE_TIME), propertyfactory);
    }

    public String getValue()
    {
        return Strings.valueOf(dates);
    }

    public void setTimeZone(TimeZone timezone)
    {
        if (dates == null)
        {
            throw new UnsupportedOperationException("TimeZone is not applicable to current value");
        }
        timeZone = timezone;
        if (timezone != null)
        {
            if (!Value.DATE_TIME.equals(dates.type))
            {
                throw new UnsupportedOperationException("TimeZone is not applicable to current value");
            } else
            {
                dates.setTimeZone(timezone);
                ParameterList parameterlist = super.parameters;
                Parameter parameter1 = super.parameters.getParameter("TZID");
                parameterlist.parameters.remove(parameter1);
                timezone = new TzId(timezone.getID());
                super.parameters.replace(timezone);
                return;
            }
        }
        if (dates == null || !Value.DATE_TIME.equals(dates.type))
        {
            throw new UnsupportedOperationException("TimeZone is not applicable to current value");
        } else
        {
            dates.setUtc(false);
            timezone = super.parameters;
            Parameter parameter = super.parameters.getParameter("TZID");
            ((ParameterList) (timezone)).parameters.remove(parameter);
            return;
        }
    }

    public void setValue(String s)
        throws ParseException
    {
        dates = new DateList(s, (Value)super.parameters.getParameter("VALUE"), timeZone);
    }
}
