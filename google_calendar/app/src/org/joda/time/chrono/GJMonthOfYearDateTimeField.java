// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.util.Locale;
import java.util.TreeMap;
import org.joda.time.DateTimeFieldType;
import org.joda.time.IllegalFieldValueException;

// Referenced classes of package org.joda.time.chrono:
//            BasicMonthOfYearDateTimeField, GJLocaleSymbols, BasicChronology

final class GJMonthOfYearDateTimeField extends BasicMonthOfYearDateTimeField
{

    GJMonthOfYearDateTimeField(BasicChronology basicchronology)
    {
        super(basicchronology, 2);
    }

    protected final int convertText(String s, Locale locale)
    {
        locale = (Integer)GJLocaleSymbols.forLocale(locale).iParseMonths.get(s);
        if (locale != null)
        {
            return locale.intValue();
        } else
        {
            throw new IllegalFieldValueException(DateTimeFieldType.MONTH_OF_YEAR_TYPE, s);
        }
    }

    public final String getAsShortText(int i, Locale locale)
    {
        return GJLocaleSymbols.forLocale(locale).iShortMonths[i];
    }

    public final String getAsText(int i, Locale locale)
    {
        return GJLocaleSymbols.forLocale(locale).iMonths[i];
    }

    public final int getMaximumTextLength(Locale locale)
    {
        return GJLocaleSymbols.forLocale(locale).iMaxMonthLength;
    }
}
