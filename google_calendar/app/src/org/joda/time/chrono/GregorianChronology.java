// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.chrono:
//            BasicGJChronology, ZonedChronology, AssembledChronology, BasicChronology

public final class GregorianChronology extends BasicGJChronology
{

    public static final GregorianChronology INSTANCE_UTC;
    private static final ConcurrentHashMap cCache = new ConcurrentHashMap();
    public static final long serialVersionUID = 0xf40baa8c7e176bc6L;

    private GregorianChronology(Chronology chronology, Object obj, int i)
    {
        super(chronology, null, i);
    }

    public static GregorianChronology getInstance(DateTimeZone datetimezone, int i)
    {
        Object obj;
        DateTimeZone datetimezone1;
        Object obj1;
        datetimezone1 = datetimezone;
        if (datetimezone == null)
        {
            datetimezone1 = DateTimeZone.getDefault();
        }
        datetimezone = (GregorianChronology[])cCache.get(datetimezone1);
        if (datetimezone == null)
        {
            datetimezone = new GregorianChronology[7];
            GregorianChronology agregorianchronology[] = (GregorianChronology[])cCache.putIfAbsent(datetimezone1, datetimezone);
            if (agregorianchronology != null)
            {
                datetimezone = agregorianchronology;
            }
        }
        obj1 = datetimezone[i - 1];
        obj = obj1;
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        datetimezone;
        JVM INSTR monitorenter ;
        obj1 = datetimezone[i - 1];
        obj = obj1;
        if (obj1 != null) goto _L4; else goto _L3
_L3:
        if (datetimezone1 != DateTimeZone.UTC) goto _L6; else goto _L5
_L5:
        obj = new GregorianChronology(null, null, i);
          goto _L7
_L4:
        datetimezone;
        JVM INSTR monitorexit ;
_L2:
        return ((GregorianChronology) (obj));
_L6:
        obj = new GregorianChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, i), datetimezone1), null, i);
          goto _L7
        obj;
        datetimezone;
        JVM INSTR monitorexit ;
        throw obj;
_L7:
        datetimezone[i - 1] = obj;
        if (true) goto _L4; else goto _L8
_L8:
    }

    private final Object readResolve()
    {
        Chronology chronology = super.iBase;
        int j = getMinimumDaysInFirstWeek();
        int i = j;
        if (j == 0)
        {
            i = 4;
        }
        if (chronology == null)
        {
            return getInstance(DateTimeZone.UTC, i);
        } else
        {
            return getInstance(chronology.getZone(), i);
        }
    }

    protected final void assemble(AssembledChronology.Fields fields)
    {
        if (super.iBase == null)
        {
            super.assemble(fields);
        }
    }

    final long calculateFirstDayOfYearMillis(int i)
    {
        int j = i / 100;
        if (i >= 0) goto _L2; else goto _L1
_L1:
        j = ((j + 3 >> 2) + ((i + 3 >> 2) - j)) - 1;
_L4:
        long l = i;
        return ((long)(j - 0xafaa7) + l * 365L) * 0x5265c00L;
_L2:
        int k = (j >> 2) + ((i >> 2) - j);
        j = k;
        if (isLeapYear(i))
        {
            j = k - 1;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final volatile boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    final long getApproxMillisAtEpochDividedByTwo()
    {
        return 0x1c4536cce9c0L;
    }

    final long getAverageMillisPerMonth()
    {
        return 0x9cbebd50L;
    }

    final long getAverageMillisPerYear()
    {
        return 0x758f0dfc0L;
    }

    final long getAverageMillisPerYearDividedByTwo()
    {
        return 0x3ac786fe0L;
    }

    final int getMaxYear()
    {
        return 0x116bd2d1;
    }

    final int getMinYear()
    {
        return 0xee943c92;
    }

    public final volatile int getMinimumDaysInFirstWeek()
    {
        return super.getMinimumDaysInFirstWeek();
    }

    public final volatile DateTimeZone getZone()
    {
        return super.getZone();
    }

    public final volatile int hashCode()
    {
        return super.hashCode();
    }

    final boolean isLeapYear(int i)
    {
        return (i & 3) == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    public final volatile String toString()
    {
        return super.toString();
    }

    public final Chronology withUTC()
    {
        return INSTANCE_UTC;
    }

    public final Chronology withZone(DateTimeZone datetimezone)
    {
        DateTimeZone datetimezone1 = datetimezone;
        if (datetimezone == null)
        {
            datetimezone1 = DateTimeZone.getDefault();
        }
        if (datetimezone1 == getZone())
        {
            return this;
        } else
        {
            return getInstance(datetimezone1, 4);
        }
    }

    static 
    {
        INSTANCE_UTC = getInstance(DateTimeZone.UTC, 4);
    }
}
