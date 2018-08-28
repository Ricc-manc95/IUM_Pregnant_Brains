// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.util.Locale;
import java.util.TreeMap;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.UnsupportedDurationField;

// Referenced classes of package org.joda.time.chrono:
//            BasicChronology, GJLocaleSymbols

final class GJEraDateTimeField extends BaseDateTimeField
{

    private final BasicChronology iChronology;

    GJEraDateTimeField(BasicChronology basicchronology)
    {
        super(DateTimeFieldType.ERA_TYPE);
        iChronology = basicchronology;
    }

    public final int get(long l)
    {
        return iChronology.getYear(l) > 0 ? 1 : 0;
    }

    public final String getAsText(int i, Locale locale)
    {
        return GJLocaleSymbols.forLocale(locale).iEras[i];
    }

    public final DurationField getDurationField()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.ERAS_TYPE);
    }

    public final int getMaximumTextLength(Locale locale)
    {
        return GJLocaleSymbols.forLocale(locale).iMaxEraLength;
    }

    public final int getMaximumValue()
    {
        return 1;
    }

    public final int getMinimumValue()
    {
        return 0;
    }

    public final DurationField getRangeDurationField()
    {
        return null;
    }

    public final long roundFloor(long l)
    {
        if (get(l) == 1)
        {
            return iChronology.setYear(0L, 1);
        } else
        {
            return 0x8000000000000000L;
        }
    }

    public final long set(long l, int i)
    {
        FieldUtils.verifyValueBounds(this, i, 0, 1);
        long l1 = l;
        if (get(l) != i)
        {
            i = iChronology.getYear(l);
            l1 = iChronology.setYear(l, -i);
        }
        return l1;
    }

    public final long set(long l, String s, Locale locale)
    {
        locale = (Integer)GJLocaleSymbols.forLocale(locale).iParseEras.get(s);
        if (locale != null)
        {
            return set(l, locale.intValue());
        } else
        {
            throw new IllegalFieldValueException(DateTimeFieldType.ERA_TYPE, s);
        }
    }
}
