// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimePrinter;

// Referenced classes of package org.joda.time:
//            ReadableDateTime, ReadableInstant, DateTimeUtils, DateTimeZone

public final class MutableDateTime extends BaseDateTime
    implements Serializable, Cloneable, ReadableDateTime, ReadableInstant
{

    public static final long serialVersionUID = 0x2796807cf37e0267L;

    public MutableDateTime()
    {
    }

    public MutableDateTime(long l, DateTimeZone datetimezone)
    {
        super(l, datetimezone);
    }

    public final Object clone()
    {
        Object obj;
        try
        {
            obj = super.clone();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new InternalError("Clone error");
        }
        return obj;
    }

    public final String toString()
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
