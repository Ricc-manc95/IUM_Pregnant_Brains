// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.base;

import org.joda.time.DateTimeUtils;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimePrinter;

public abstract class AbstractInstant
    implements ReadableInstant
{

    public AbstractInstant()
    {
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((ReadableInstant)obj);
    }

    public final int compareTo(ReadableInstant readableinstant)
    {
        if (this != readableinstant)
        {
            long l = readableinstant.getMillis();
            long l1 = getMillis();
            if (l1 != l)
            {
                return l1 >= l ? 1 : -1;
            }
        }
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof ReadableInstant))
        {
            return false;
        }
        Object obj1 = (ReadableInstant)obj;
        if (getMillis() != ((ReadableInstant) (obj1)).getMillis())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = getChronology();
        obj1 = ((ReadableInstant) (obj1)).getChronology();
        boolean flag;
        if (obj == obj1)
        {
            flag = true;
        } else
        if (obj == null || obj1 == null)
        {
            flag = false;
        } else
        {
            flag = obj.equals(obj1);
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public int hashCode()
    {
        return (int)(getMillis() ^ getMillis() >>> 32) + getChronology().hashCode();
    }

    public String toString()
    {
        DateTimeFormatter datetimeformatter = org.joda.time.format.ISODateTimeFormat.Constants.dt;
        Object obj = datetimeformatter.iPrinter;
        if (obj == null)
        {
            throw new UnsupportedOperationException("Printing not supported");
        } else
        {
            obj = new StringBuffer(((DateTimePrinter) (obj)).estimatePrintedLength());
            datetimeformatter.printTo(((StringBuffer) (obj)), DateTimeUtils.getInstantMillis(this), DateTimeUtils.getInstantChronology(this));
            return ((StringBuffer) (obj)).toString();
        }
    }
}
