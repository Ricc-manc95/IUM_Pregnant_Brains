// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;

final class iEndRecurrence extends DateTimeZone
{

    public static final long serialVersionUID = 0x605522c6413e57d1L;
    private final ce iEndRecurrence;
    private final int iStandardOffset;
    private final ce iStartRecurrence;

    private final ce findMatchingRecurrence(long l)
    {
        ce ce;
        ce ce1;
        int j;
        j = iStandardOffset;
        ce = iStartRecurrence;
        ce1 = iEndRecurrence;
        Object obj;
        int i;
        i = ce1.iSaveMillis;
        obj = ce.iOfYear;
        if (((ce.iOfYear) (obj)).Mode != 'w') goto _L2; else goto _L1
_L1:
        i += j;
_L9:
        long l4 = l + (long)i;
        ISOChronology isochronology;
        long l3;
        isochronology = ISOChronology.INSTANCE_UTC;
        long l1 = isochronology.monthOfYear().set(l4, ((TC) (obj)).MonthOfYear);
        l1 = isochronology.millisOfDay().set(l1, 0);
        l3 = ((MonthOfYear) (obj)).etDayOfMonthNext(isochronology, isochronology.millisOfDay().add(l1, ((etDayOfMonthNext) (obj)).MillisOfDay));
        if (((MillisOfDay) (obj)).DayOfWeek != 0) goto _L4; else goto _L3
_L3:
        long l2;
        l2 = l3;
        if (l3 > l4)
        {
            break MISSING_BLOCK_LABEL_153;
        }
        l2 = ((DayOfWeek) (obj)).etDayOfMonthNext(isochronology, isochronology.year().add(l3, 1));
_L11:
        l3 = l2 - (long)i;
_L12:
        i = ce.iSaveMillis;
        obj = ce1.iOfYear;
        if (((ce.iOfYear) (obj)).Mode != 'w') goto _L6; else goto _L5
_L5:
        i += j;
_L14:
        long l5 = l + (long)i;
        isochronology = ISOChronology.INSTANCE_UTC;
        l2 = isochronology.monthOfYear().set(l5, ((TC) (obj)).MonthOfYear);
        l2 = isochronology.millisOfDay().set(l2, 0);
        l4 = ((MonthOfYear) (obj)).etDayOfMonthNext(isochronology, isochronology.millisOfDay().add(l2, ((etDayOfMonthNext) (obj)).MillisOfDay));
        if (((MillisOfDay) (obj)).DayOfWeek != 0) goto _L8; else goto _L7
_L7:
        l2 = l4;
        if (l4 > l5)
        {
            break MISSING_BLOCK_LABEL_297;
        }
        l2 = ((DayOfWeek) (obj)).etDayOfMonthNext(isochronology, isochronology.year().add(l4, 1));
_L16:
        l = l2 - (long)i;
_L17:
        if (l3 > l)
        {
            return ce;
        } else
        {
            return ce1;
        }
_L2:
        Object obj1;
        if (((etDayOfMonthNext) (obj)).Mode == 's')
        {
            i = j;
        } else
        {
            i = 0;
        }
          goto _L9
_L4:
        l3 = ((Mode) (obj)).etDayOfWeek(isochronology, l3);
        l2 = l3;
        if (l3 > l4) goto _L11; else goto _L10
_L10:
        l2 = isochronology.year().add(l3, 1);
        l2 = ((etDayOfWeek) (obj)).etDayOfWeek(isochronology, ((etDayOfWeek) (obj)).etDayOfMonthNext(isochronology, isochronology.monthOfYear().set(l2, ((etDayOfMonthNext) (obj)).MonthOfYear)));
          goto _L11
        obj;
        l3 = l;
          goto _L12
        obj;
        l3 = l;
          goto _L12
_L6:
        i = j;
        if (((MonthOfYear) (obj)).Mode == 's') goto _L14; else goto _L13
_L13:
        i = 0;
          goto _L14
_L8:
        l4 = ((Mode) (obj)).etDayOfWeek(isochronology, l4);
        l2 = l4;
        if (l4 > l5) goto _L16; else goto _L15
_L15:
        l2 = isochronology.year().add(l4, 1);
        l2 = ((etDayOfWeek) (obj)).etDayOfWeek(isochronology, ((etDayOfWeek) (obj)).etDayOfMonthNext(isochronology, isochronology.monthOfYear().set(l2, ((etDayOfMonthNext) (obj)).MonthOfYear)));
          goto _L16
        obj1;
          goto _L17
        obj1;
          goto _L17
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj instanceof MonthOfYear)
            {
                if (!super.iID.equals(((DateTimeZone) (obj = (MonthOfYear)obj)).iID) || iStandardOffset != ((iStandardOffset) (obj)).iStandardOffset || !iStartRecurrence.equals(((ce.equals) (obj)).iStartRecurrence) || !iEndRecurrence.equals(((ce.equals) (obj)).iEndRecurrence))
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

    public final String getNameKey(long l)
    {
        return findMatchingRecurrence(l).iNameKey;
    }

    public final int getOffset(long l)
    {
        return iStandardOffset + findMatchingRecurrence(l).iSaveMillis;
    }

    public final int getStandardOffset(long l)
    {
        return iStandardOffset;
    }

    public final boolean isFixed()
    {
        return false;
    }

    public final long nextTransition(long l)
    {
        Object obj;
        Object obj2;
        int j;
        j = iStandardOffset;
        obj = iStartRecurrence;
        obj2 = iEndRecurrence;
        ce ce;
        int i;
        i = ((ce) (obj2)).iSaveMillis;
        ce = ((ce) (obj)).iOfYear;
        if (ce.Mode != 'w') goto _L2; else goto _L1
_L1:
        i += j;
_L9:
        long l4 = l + (long)i;
        ISOChronology isochronology;
        long l3;
        isochronology = ISOChronology.INSTANCE_UTC;
        long l1 = isochronology.monthOfYear().set(l4, ce.MonthOfYear);
        l1 = isochronology.millisOfDay().set(l1, 0);
        l3 = ce.etDayOfMonthNext(isochronology, isochronology.millisOfDay().add(l1, ce.MillisOfDay));
        if (ce.DayOfWeek != 0) goto _L4; else goto _L3
_L3:
        long l2;
        l2 = l3;
        if (l3 > l4)
        {
            break MISSING_BLOCK_LABEL_153;
        }
        l2 = ce.etDayOfMonthNext(isochronology, isochronology.year().add(l3, 1));
_L11:
        l3 = l2 - (long)i;
        l2 = l3;
        if (l > 0L)
        {
            l2 = l3;
            if (l3 < 0L)
            {
                l2 = l;
            }
        }
        l3 = l2;
_L12:
        i = ((ce) (obj)).iSaveMillis;
        obj = ((ce) (obj2)).iOfYear;
        if (((ce.iOfYear) (obj)).Mode != 'w') goto _L6; else goto _L5
_L5:
        i += j;
_L14:
        long l5 = l + (long)i;
        obj2 = ISOChronology.INSTANCE_UTC;
        l2 = ((Chronology) (obj2)).monthOfYear().set(l5, ((TC) (obj)).MonthOfYear);
        l2 = ((Chronology) (obj2)).millisOfDay().set(l2, 0);
        l4 = ((MonthOfYear) (obj)).etDayOfMonthNext(((Chronology) (obj2)), ((Chronology) (obj2)).millisOfDay().add(l2, ((etDayOfMonthNext) (obj)).MillisOfDay));
        if (((MillisOfDay) (obj)).DayOfWeek != 0) goto _L8; else goto _L7
_L7:
        l2 = l4;
        if (l4 > l5)
        {
            break MISSING_BLOCK_LABEL_318;
        }
        l2 = ((DayOfWeek) (obj)).etDayOfMonthNext(((Chronology) (obj2)), ((Chronology) (obj2)).year().add(l4, 1));
_L16:
        l2 -= i;
        Object obj1;
        Object obj3;
        if (l <= 0L || l2 >= 0L)
        {
            l = l2;
        }
_L17:
        if (l3 > l)
        {
            return l;
        } else
        {
            return l3;
        }
_L2:
        if (ce.Mode == 's')
        {
            i = j;
        } else
        {
            i = 0;
        }
          goto _L9
_L4:
        l3 = ce.etDayOfWeek(isochronology, l3);
        l2 = l3;
        if (l3 > l4) goto _L11; else goto _L10
_L10:
        l2 = isochronology.year().add(l3, 1);
        l2 = ce.etDayOfWeek(isochronology, ce.etDayOfMonthNext(isochronology, isochronology.monthOfYear().set(l2, ce.MonthOfYear)));
          goto _L11
        obj3;
        l3 = l;
          goto _L12
        obj3;
        l3 = l;
          goto _L12
_L6:
        i = j;
        if (((MonthOfYear) (obj)).Mode == 's') goto _L14; else goto _L13
_L13:
        i = 0;
          goto _L14
_L8:
        l4 = ((Mode) (obj)).etDayOfWeek(((Chronology) (obj2)), l4);
        l2 = l4;
        if (l4 > l5) goto _L16; else goto _L15
_L15:
        l2 = ((Chronology) (obj2)).year().add(l4, 1);
        l2 = ((etDayOfWeek) (obj)).etDayOfWeek(((Chronology) (obj2)), ((etDayOfWeek) (obj)).etDayOfMonthNext(((Chronology) (obj2)), ((Chronology) (obj2)).monthOfYear().set(l2, ((etDayOfMonthNext) (obj)).MonthOfYear)));
          goto _L16
        obj1;
          goto _L17
        obj1;
          goto _L17
    }

    public final long previousTransition(long l)
    {
        Object obj;
        Object obj2;
        int j;
        l++;
        j = iStandardOffset;
        obj = iStartRecurrence;
        obj2 = iEndRecurrence;
        ce ce;
        int i;
        i = ((ce) (obj2)).iSaveMillis;
        ce = ((ce) (obj)).iOfYear;
        if (ce.Mode != 'w') goto _L2; else goto _L1
_L1:
        i += j;
_L9:
        long l4 = l + (long)i;
        ISOChronology isochronology;
        long l3;
        isochronology = ISOChronology.INSTANCE_UTC;
        long l1 = isochronology.monthOfYear().set(l4, ce.MonthOfYear);
        l1 = isochronology.millisOfDay().set(l1, 0);
        l3 = ce.etDayOfMonthPrevious(isochronology, isochronology.millisOfDay().add(l1, ce.MillisOfDay));
        if (ce.DayOfWeek != 0) goto _L4; else goto _L3
_L3:
        long l2;
        l2 = l3;
        if (l3 < l4)
        {
            break MISSING_BLOCK_LABEL_157;
        }
        l2 = ce.etDayOfMonthPrevious(isochronology, isochronology.year().add(l3, -1));
_L11:
        l3 = l2 - (long)i;
        l2 = l3;
        if (l < 0L)
        {
            l2 = l3;
            if (l3 > 0L)
            {
                l2 = l;
            }
        }
_L12:
        i = ((ce) (obj)).iSaveMillis;
        obj = ((ce) (obj2)).iOfYear;
        if (((ce.iOfYear) (obj)).Mode != 'w') goto _L6; else goto _L5
_L5:
        i += j;
_L14:
        long l5 = l + (long)i;
        obj2 = ISOChronology.INSTANCE_UTC;
        l3 = ((Chronology) (obj2)).monthOfYear().set(l5, ((TC) (obj)).MonthOfYear);
        l3 = ((Chronology) (obj2)).millisOfDay().set(l3, 0);
        l4 = ((MonthOfYear) (obj)).etDayOfMonthPrevious(((Chronology) (obj2)), ((Chronology) (obj2)).millisOfDay().add(l3, ((etDayOfMonthPrevious) (obj)).MillisOfDay));
        if (((MillisOfDay) (obj)).DayOfWeek != 0) goto _L8; else goto _L7
_L7:
        l3 = l4;
        if (l4 < l5)
        {
            break MISSING_BLOCK_LABEL_318;
        }
        l3 = ((DayOfWeek) (obj)).etDayOfMonthPrevious(((Chronology) (obj2)), ((Chronology) (obj2)).year().add(l4, -1));
_L16:
        l3 -= i;
        Object obj1;
        Object obj3;
        if (l >= 0L || l3 <= 0L)
        {
            l = l3;
        }
_L17:
        l3 = l;
        if (l2 > l)
        {
            l3 = l2;
        }
        return l3 - 1L;
_L2:
        if (ce.Mode == 's')
        {
            i = j;
        } else
        {
            i = 0;
        }
          goto _L9
_L4:
        l3 = ce.etDayOfWeek(isochronology, l3);
        l2 = l3;
        if (l3 < l4) goto _L11; else goto _L10
_L10:
        l2 = isochronology.year().add(l3, -1);
        l2 = ce.etDayOfWeek(isochronology, ce.etDayOfMonthPrevious(isochronology, isochronology.monthOfYear().set(l2, ce.MonthOfYear)));
          goto _L11
        obj3;
        l2 = l;
          goto _L12
        obj3;
        l2 = l;
          goto _L12
_L6:
        i = j;
        if (((MonthOfYear) (obj)).Mode == 's') goto _L14; else goto _L13
_L13:
        i = 0;
          goto _L14
_L8:
        l4 = ((Mode) (obj)).etDayOfWeek(((Chronology) (obj2)), l4);
        l3 = l4;
        if (l4 < l5) goto _L16; else goto _L15
_L15:
        l3 = ((Chronology) (obj2)).year().add(l4, -1);
        l3 = ((etDayOfWeek) (obj)).etDayOfWeek(((Chronology) (obj2)), ((etDayOfWeek) (obj)).etDayOfMonthPrevious(((Chronology) (obj2)), ((Chronology) (obj2)).monthOfYear().set(l3, ((etDayOfMonthPrevious) (obj)).MonthOfYear)));
          goto _L16
        obj1;
          goto _L17
        obj1;
          goto _L17
    }

    ce(String s, int i, ce ce, ce ce1)
    {
        super(s);
        iStandardOffset = i;
        iStartRecurrence = ce;
        iEndRecurrence = ce1;
    }
}
