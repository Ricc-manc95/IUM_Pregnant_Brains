// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimePrinter;

// Referenced classes of package org.joda.time:
//            Instant, DateTimeUtils

public final class IllegalInstantException extends IllegalArgumentException
{

    public static final long serialVersionUID = 0x299988b9c68L;

    public IllegalInstantException(long l, String s)
    {
        Object obj = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Instant instant = new Instant(l);
        Object obj1 = ((DateTimeFormatter) (obj)).iPrinter;
        if (obj1 == null)
        {
            throw new UnsupportedOperationException("Printing not supported");
        }
        obj1 = new StringBuffer(((DateTimePrinter) (obj1)).estimatePrintedLength());
        ((DateTimeFormatter) (obj)).printTo(((StringBuffer) (obj1)), DateTimeUtils.getInstantMillis(instant), DateTimeUtils.getInstantChronology(instant));
        obj = ((StringBuffer) (obj1)).toString();
        if (s != null)
        {
            s = (new StringBuilder(String.valueOf(s).length() + 3)).append(" (").append(s).append(")").toString();
        } else
        {
            s = "";
        }
        super((new StringBuilder(String.valueOf(obj).length() + 82 + String.valueOf(s).length())).append("Illegal instant due to time zone offset transition (daylight savings time 'gap'): ").append(((String) (obj))).append(s).toString());
    }

    public IllegalInstantException(String s)
    {
        super(s);
    }
}
