// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.Locale;
import org.joda.time.DateTimeField;

// Referenced classes of package org.joda.time.format:
//            DateTimeParserBucket

final class iLocale
    implements Comparable
{

    public final DateTimeField iField;
    private final Locale iLocale;
    private final String iText;
    private final int iValue;

    public final volatile int compareTo(Object obj)
    {
        return compareTo((compareTo)obj);
    }

    public final int compareTo(compareTo compareto)
    {
        compareto = compareto.iField;
        int i = DateTimeParserBucket.compareReverse(iField.getRangeDurationField(), compareto.getRangeDurationField());
        if (i != 0)
        {
            return i;
        } else
        {
            return DateTimeParserBucket.compareReverse(iField.getDurationField(), compareto.getDurationField());
        }
    }

    final long set(long l, boolean flag)
    {
        long l1;
        if (iText == null)
        {
            l = iField.set(l, iValue);
        } else
        {
            l = iField.set(l, iText, iLocale);
        }
        l1 = l;
        if (flag)
        {
            l1 = iField.roundFloor(l);
        }
        return l1;
    }

    (DateTimeField datetimefield, int i)
    {
        iField = datetimefield;
        iValue = i;
        iText = null;
        iLocale = null;
    }

    iLocale(DateTimeField datetimefield, String s, Locale locale)
    {
        iField = datetimefield;
        iValue = 0;
        iText = s;
        iLocale = locale;
    }
}
