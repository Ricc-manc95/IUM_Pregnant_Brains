// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.util.Date;

public class DateRange
    implements Serializable
{

    public static final long serialVersionUID = 0x9aa386243759040aL;
    public final Date rangeEnd;
    public final Date rangeStart;

    public DateRange(Date date, Date date1)
    {
        if (date == null)
        {
            throw new IllegalArgumentException("Range start is null");
        }
        if (date1 == null)
        {
            throw new IllegalArgumentException("Range end is null");
        }
        if (date1.before(date))
        {
            throw new IllegalArgumentException("Range start must be before range end");
        } else
        {
            rangeStart = date;
            rangeEnd = date1;
            return;
        }
    }
}
