// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;
import org.joda.time.chrono.ISOChronology;

// Referenced classes of package org.joda.time.base:
//            AbstractDateTime

public class BaseDateTime extends AbstractDateTime
    implements Serializable, ReadableDateTime
{

    public static final long serialVersionUID = 0xfffff9e14f5d2ea3L;
    public volatile Chronology iChronology;
    public volatile long iMillis;

    public BaseDateTime()
    {
        this(DateTimeUtils.cMillisProvider.getMillis(), ((Chronology) (ISOChronology.getInstance())));
    }

    public BaseDateTime(long l, Chronology chronology)
    {
        iChronology = DateTimeUtils.getChronology(chronology);
        chronology = iChronology;
        iMillis = l;
    }

    public BaseDateTime(long l, DateTimeZone datetimezone)
    {
        this(l, ((Chronology) (ISOChronology.getInstance(datetimezone))));
    }

    public final Chronology getChronology()
    {
        return iChronology;
    }

    public final long getMillis()
    {
        return iMillis;
    }

    public void setMillis(long l)
    {
        Chronology chronology = iChronology;
        iMillis = l;
    }
}
