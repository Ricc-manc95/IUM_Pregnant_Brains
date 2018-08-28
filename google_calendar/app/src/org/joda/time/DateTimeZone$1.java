// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;

import org.joda.time.chrono.BaseChronology;

// Referenced classes of package org.joda.time:
//            DateTimeZone, Chronology

final class nology extends BaseChronology
{

    public static final long serialVersionUID = 0xd4947ab9cf0bf864L;

    public final DateTimeZone getZone()
    {
        return null;
    }

    public final String toString()
    {
        return getClass().getName();
    }

    public final Chronology withUTC()
    {
        return this;
    }

    public final Chronology withZone(DateTimeZone datetimezone)
    {
        return this;
    }

    nology()
    {
    }
}
