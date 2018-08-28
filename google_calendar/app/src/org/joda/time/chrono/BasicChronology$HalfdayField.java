// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.field.PreciseDateTimeField;

// Referenced classes of package org.joda.time.chrono:
//            BasicChronology, GJLocaleSymbols

final class d extends PreciseDateTimeField
{

    public final String getAsText(int i, Locale locale)
    {
        return GJLocaleSymbols.forLocale(locale).iHalfday[i];
    }

    public final int getMaximumTextLength(Locale locale)
    {
        return GJLocaleSymbols.forLocale(locale).iMaxHalfdayLength;
    }

    public final long set(long l, String s, Locale locale)
    {
        locale = GJLocaleSymbols.forLocale(locale).iHalfday;
        int i = locale.length;
        do
        {
            int j = i - 1;
            if (j >= 0)
            {
                i = j;
                if (locale[j].equalsIgnoreCase(s))
                {
                    return set(l, j);
                }
            } else
            {
                throw new IllegalFieldValueException(DateTimeFieldType.HALFDAY_OF_DAY_TYPE, s);
            }
        } while (true);
    }

    ()
    {
        super(DateTimeFieldType.HALFDAY_OF_DAY_TYPE, BasicChronology.cHalfdaysField, BasicChronology.cDaysField);
    }
}
