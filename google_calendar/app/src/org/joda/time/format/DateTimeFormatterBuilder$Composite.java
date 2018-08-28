// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.format:
//            DateTimeParser, DateTimePrinter, DateTimeParserBucket

final class iParsedLengthEstimate
    implements DateTimeParser, DateTimePrinter
{

    private final int iParsedLengthEstimate;
    public final DateTimeParser iParsers[];
    private final int iPrintedLengthEstimate;
    public final DateTimePrinter iPrinters[];

    public final int estimateParsedLength()
    {
        return iParsedLengthEstimate;
    }

    public final int estimatePrintedLength()
    {
        return iPrintedLengthEstimate;
    }

    public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
    {
        DateTimeParser adatetimeparser[] = iParsers;
        if (adatetimeparser == null)
        {
            throw new UnsupportedOperationException();
        }
        int k = adatetimeparser.length;
        boolean flag = false;
        int j = i;
        for (i = ((flag) ? 1 : 0); i < k && j >= 0; i++)
        {
            j = adatetimeparser[i].parseInto(datetimeparserbucket, s, j);
        }

        return j;
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
    {
        DateTimePrinter adatetimeprinter[] = iPrinters;
        if (adatetimeprinter == null)
        {
            throw new UnsupportedOperationException();
        }
        if (locale == null)
        {
            locale = Locale.getDefault();
        }
        int k = adatetimeprinter.length;
        for (int j = 0; j < k; j++)
        {
            adatetimeprinter[j].printTo(stringbuffer, l, chronology, i, datetimezone, locale);
        }

    }

    (List list)
    {
        boolean flag = false;
        super();
        ArrayList arraylist1 = new ArrayList();
        ArrayList arraylist = new ArrayList();
        int l1 = list.size();
        for (int i = 0; i < l1; i += 2)
        {
            Object obj = list.get(i);
            if (obj instanceof )
            {
                obj = (()obj).iPrinters;
                if (obj != null)
                {
                    for (int l = 0; l < obj.length; l++)
                    {
                        arraylist1.add(obj[l]);
                    }

                }
            } else
            {
                arraylist1.add(obj);
            }
            obj = list.get(i + 1);
            if (obj instanceof iPrinters)
            {
                obj = ((iPrinters)obj).iParsers;
                if (obj == null)
                {
                    continue;
                }
                for (int i1 = 0; i1 < obj.length; i1++)
                {
                    arraylist.add(obj[i1]);
                }

            } else
            {
                arraylist.add(obj);
            }
        }

        if (arraylist1.contains(null) || arraylist1.isEmpty())
        {
            iPrinters = null;
            iPrintedLengthEstimate = 0;
        } else
        {
            l1 = arraylist1.size();
            iPrinters = new DateTimePrinter[l1];
            int j = 0;
            int j1 = 0;
            for (; j < l1; j++)
            {
                list = (DateTimePrinter)arraylist1.get(j);
                j1 += list.estimatePrintedLength();
                iPrinters[j] = list;
            }

            iPrintedLengthEstimate = j1;
        }
        if (arraylist.contains(null) || arraylist.isEmpty())
        {
            iParsers = null;
            iParsedLengthEstimate = 0;
            return;
        }
        l1 = arraylist.size();
        iParsers = new DateTimeParser[l1];
        int k1 = 0;
        for (int k = ((flag) ? 1 : 0); k < l1; k++)
        {
            list = (DateTimeParser)arraylist.get(k);
            k1 += list.estimateParsedLength();
            iParsers[k] = list;
        }

        iParsedLengthEstimate = k1;
    }
}
