// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;

final class iMillisOfDay
{

    private final boolean iAdvance;
    private final int iDayOfMonth;
    public final int iDayOfWeek;
    public final int iMillisOfDay;
    public final char iMode;
    public final int iMonthOfYear;

    private final long setDayOfMonth(Chronology chronology, long l)
    {
        if (iDayOfMonth >= 0)
        {
            return chronology.dayOfMonth().set(l, iDayOfMonth);
        } else
        {
            l = chronology.dayOfMonth().set(l, 1);
            l = chronology.monthOfYear().add(l, 1);
            return chronology.dayOfMonth().add(l, iDayOfMonth);
        }
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj instanceof iDayOfMonth)
            {
                if (iMode != ((iMode) (obj = (iMode)obj)).iMode || iMonthOfYear != ((iMonthOfYear) (obj)).iMonthOfYear || iDayOfMonth != ((iDayOfMonth) (obj)).iDayOfMonth || iDayOfWeek != ((iDayOfWeek) (obj)).iDayOfWeek || iAdvance != ((iAdvance) (obj)).iAdvance || iMillisOfDay != ((iMillisOfDay) (obj)).iMillisOfDay)
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    final long setDayOfMonthNext(Chronology chronology, long l)
    {
        long l1;
        try
        {
            l1 = setDayOfMonth(chronology, l);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            if (iMonthOfYear == 2 && iDayOfMonth == 29)
            {
                for (; !chronology.year().isLeap(l); l = chronology.year().add(l, 1)) { }
                return setDayOfMonth(chronology, l);
            } else
            {
                throw illegalargumentexception;
            }
        }
        return l1;
    }

    final long setDayOfMonthPrevious(Chronology chronology, long l)
    {
        long l1;
        try
        {
            l1 = setDayOfMonth(chronology, l);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            if (iMonthOfYear == 2 && iDayOfMonth == 29)
            {
                for (; !chronology.year().isLeap(l); l = chronology.year().add(l, -1)) { }
                return setDayOfMonth(chronology, l);
            } else
            {
                throw illegalargumentexception;
            }
        }
        return l1;
    }

    final long setDayOfWeek(Chronology chronology, long l)
    {
        int k;
        long l1;
        int i = chronology.dayOfWeek().get(l);
        k = iDayOfWeek - i;
        l1 = l;
        if (k == 0) goto _L2; else goto _L1
_L1:
        if (!iAdvance) goto _L4; else goto _L3
_L3:
        int j;
        j = k;
        if (k < 0)
        {
            j = k + 7;
        }
_L6:
        l1 = chronology.dayOfWeek().add(l, j);
_L2:
        return l1;
_L4:
        j = k;
        if (k > 0)
        {
            j = k - 7;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    (char c, int i, int j, int k, boolean flag, int l)
    {
        if (c != 'u' && c != 'w' && c != 's')
        {
            throw new IllegalArgumentException((new StringBuilder(15)).append("Unknown mode: ").append(c).toString());
        } else
        {
            iMode = c;
            iMonthOfYear = i;
            iDayOfMonth = j;
            iDayOfWeek = k;
            iAdvance = flag;
            iMillisOfDay = l;
            return;
        }
    }
}
