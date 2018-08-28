// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.common.ical4jutils;

import java.io.IOException;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.data.UnfoldingReader;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Content;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Iso8601;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.property.DateProperty;

public final class CalendarBuilderWrapper extends CalendarBuilder
{

    public CalendarBuilderWrapper(TimeZoneRegistry timezoneregistry)
    {
        super(timezoneregistry);
    }

    private final void fixTimesInProperties(PropertyList propertylist)
        throws ParseException
    {
        propertylist = propertylist.iterator();
        do
        {
            if (!propertylist.hasNext())
            {
                break;
            }
            Object obj = propertylist.next();
            if (obj instanceof DateProperty)
            {
                obj = (DateProperty)obj;
                Object obj1 = ((DateProperty) (obj)).date;
                if (obj1 instanceof DateTime)
                {
                    obj1 = (DateTime)obj1;
                    Object obj2 = ((Property) (obj)).parameters.getParameter("TZID");
                    if (obj2 != null)
                    {
                        obj2 = super.tzRegistry.getTimeZone(((Content) (obj2)).getValue());
                        if (obj2 != null)
                        {
                            Object obj3 = ((Iso8601) (obj1)).toString();
                            Matcher matcher = TimeUtils.ICAL_DATETIME_PATTERN.matcher(((CharSequence) (obj3)));
                            if (!matcher.matches())
                            {
                                throw new NumberFormatException((new StringBuilder(String.valueOf(obj3).length() + 12)).append("Bad date: \"").append(((String) (obj3))).append("\"").toString());
                            }
                            obj3 = new GregorianCalendar(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)) - 1, Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
                            ((GregorianCalendar) (obj3)).setTimeZone(((java.util.TimeZone) (obj2)));
                            ((Iso8601) (obj1)).setTime(((GregorianCalendar) (obj3)).getTimeInMillis());
                            ((DateProperty) (obj)).setTimeZone(((net.fortuna.ical4j.model.TimeZone) (obj2)));
                        }
                    }
                }
            }
        } while (true);
    }

    public final Calendar build(UnfoldingReader unfoldingreader)
        throws IOException, ParserException
    {
        unfoldingreader = super.build(unfoldingreader);
        try
        {
            fixTimesInProperties(((Calendar) (unfoldingreader)).properties);
            for (Iterator iterator = ((Calendar) (unfoldingreader)).components.iterator(); iterator.hasNext(); fixTimesInProperties(((Component)iterator.next()).properties)) { }
        }
        // Misplaced declaration of an exception variable
        catch (UnfoldingReader unfoldingreader)
        {
            throw new ParserException("Builder can't fix time property", 0, unfoldingreader);
        }
        return unfoldingreader;
    }

    private class TimeUtils
    {

        public static final Pattern ICAL_DATETIME_PATTERN = Pattern.compile("^(\\d\\d\\d\\d)(\\d\\d)(\\d\\d)T(\\d\\d)(\\d\\d)(\\d\\d)Z?$");

    }

}
