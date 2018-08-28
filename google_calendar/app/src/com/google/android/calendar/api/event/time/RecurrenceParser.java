// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import com.android.calendarcommon2.EventRecurrence;
import com.android.calendarcommon2.RecurrenceSet;
import com.google.android.calendar.api.common.CopyHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            RecurRulePart, ByDayRecurrence, Recurrence

public final class RecurrenceParser
{

    private static int convertToApiWeekday(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Unknown weekday");

        case 131072: 
            return 1;

        case 262144: 
            return 2;

        case 524288: 
            return 3;

        case 1048576: 
            return 4;

        case 2097152: 
            return 5;

        case 4194304: 
            return 6;

        case 65536: 
            return 7;
        }
    }

    private static long[] extractDates(String s)
    {
        boolean flag = false;
        if (s == null || s.isEmpty())
        {
            return null;
        }
        Object aobj[] = s.split("\n");
        s = new ArrayList();
        int i = 0;
        while (i < aobj.length) 
        {
            long al[];
            try
            {
                al = RecurrenceSet.parseRecurrenceDates(aobj[i]);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                throw new IllegalArgumentException(s);
            }
            s.addAll(CopyHelper.copyArrayToList(al));
            i++;
        }
        aobj = new long[s.size()];
        for (int j = ((flag) ? 1 : 0); j < s.size(); j++)
        {
            aobj[j] = ((Long)s.get(j)).longValue();
        }

        return ((long []) (aobj));
    }

    private static RecurRulePart[] extractRuleParts(String s)
    {
        String as[];
        RecurRulePart arecurrulepart[];
        int j;
        if (s == null || s.isEmpty())
        {
            return null;
        }
        as = s.split("\n");
        arecurrulepart = new RecurRulePart[as.length];
        j = 0;
_L11:
        Object obj1;
        if (j >= as.length)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = new EventRecurrence();
        try
        {
            ((EventRecurrence) (obj1)).parse(as[j]);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new IllegalArgumentException(s);
        }
        ((EventRecurrence) (obj1)).freq;
        JVM INSTR tableswitch 1 7: default 108
    //                   1 128
    //                   2 597
    //                   3 603
    //                   4 609
    //                   5 615
    //                   6 621
    //                   7 627;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_627;
_L1:
        throw new IllegalArgumentException("Unknown recurrence frequency");
_L2:
        int i = 0;
_L9:
        int ai[];
        int ai1[];
        int ai2[];
        int ai3[];
        int ai4[];
        int ai5[];
        int ai6[];
        Object obj;
        ByDayRecurrence abydayrecurrence[];
        Integer integer;
        Long long1;
        Long long2;
        Integer integer1;
        int k;
        if (((EventRecurrence) (obj1)).count == 0)
        {
            integer = null;
        } else
        {
            integer = Integer.valueOf(((EventRecurrence) (obj1)).count);
        }
        long2 = null;
        long1 = null;
        s = ((EventRecurrence) (obj1)).until;
        if (s != null && s.length() == 8)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0)
        {
            long2 = parseDateString(((EventRecurrence) (obj1)).until);
        } else
        {
            long1 = parseDateTimeString(((EventRecurrence) (obj1)).until);
        }
        if (((EventRecurrence) (obj1)).interval == 0)
        {
            integer1 = null;
        } else
        {
            integer1 = Integer.valueOf(((EventRecurrence) (obj1)).interval);
        }
        ai = ((EventRecurrence) (obj1)).bysecond;
        k = ((EventRecurrence) (obj1)).bysecondCount;
        if (ai == null || k == 0 || ai.length == 0)
        {
            s = null;
        } else
        {
            s = ai;
            if (ai.length > k)
            {
                s = Arrays.copyOf(ai, k);
            }
        }
        ai1 = ((EventRecurrence) (obj1)).byminute;
        k = ((EventRecurrence) (obj1)).byminuteCount;
        if (ai1 == null || k == 0 || ai1.length == 0)
        {
            ai = null;
        } else
        {
            ai = ai1;
            if (ai1.length > k)
            {
                ai = Arrays.copyOf(ai1, k);
            }
        }
        ai2 = ((EventRecurrence) (obj1)).byhour;
        k = ((EventRecurrence) (obj1)).byhourCount;
        if (ai2 == null || k == 0 || ai2.length == 0)
        {
            ai1 = null;
        } else
        {
            ai1 = ai2;
            if (ai2.length > k)
            {
                ai1 = Arrays.copyOf(ai2, k);
            }
        }
        ai3 = ((EventRecurrence) (obj1)).byday;
        ai4 = ((EventRecurrence) (obj1)).bydayNum;
        k = ((EventRecurrence) (obj1)).bydayCount;
        if (ai3 == null || k == 0 || ai3.length == 0)
        {
            abydayrecurrence = null;
        } else
        {
            int l = Math.min(ai3.length, k);
            abydayrecurrence = new ByDayRecurrence[l];
            k = 0;
            while (k < l) 
            {
                int i1 = convertToApiWeekday(ai3[k]);
                if (ai4 == null || ai4.length < l || ai4[k] == 0)
                {
                    ai2 = null;
                } else
                {
                    ai2 = Integer.valueOf(ai4[k]);
                }
                abydayrecurrence[k] = new ByDayRecurrence(i1, ai2);
                k++;
            }
        }
        ai3 = ((EventRecurrence) (obj1)).bymonthday;
        k = ((EventRecurrence) (obj1)).bymonthdayCount;
        if (ai3 == null || k == 0 || ai3.length == 0)
        {
            ai2 = null;
        } else
        {
            ai2 = ai3;
            if (ai3.length > k)
            {
                ai2 = Arrays.copyOf(ai3, k);
            }
        }
        ai4 = ((EventRecurrence) (obj1)).byyearday;
        k = ((EventRecurrence) (obj1)).byyeardayCount;
        if (ai4 == null || k == 0 || ai4.length == 0)
        {
            ai3 = null;
        } else
        {
            ai3 = ai4;
            if (ai4.length > k)
            {
                ai3 = Arrays.copyOf(ai4, k);
            }
        }
        ai5 = ((EventRecurrence) (obj1)).byweekno;
        k = ((EventRecurrence) (obj1)).byweeknoCount;
        if (ai5 == null || k == 0 || ai5.length == 0)
        {
            ai4 = null;
        } else
        {
            ai4 = ai5;
            if (ai5.length > k)
            {
                ai4 = Arrays.copyOf(ai5, k);
            }
        }
        ai6 = ((EventRecurrence) (obj1)).bymonth;
        k = ((EventRecurrence) (obj1)).bymonthCount;
        if (ai6 == null || k == 0 || ai6.length == 0)
        {
            ai5 = null;
        } else
        {
            ai5 = ai6;
            if (ai6.length > k)
            {
                ai5 = Arrays.copyOf(ai6, k);
            }
        }
        obj = ((EventRecurrence) (obj1)).bysetpos;
        k = ((EventRecurrence) (obj1)).bysetposCount;
        if (obj == null || k == 0 || obj.length == 0)
        {
            ai6 = null;
        } else
        {
            ai6 = ((int []) (obj));
            if (obj.length > k)
            {
                ai6 = Arrays.copyOf(((int []) (obj)), k);
            }
        }
        if (((EventRecurrence) (obj1)).wkst == 0)
        {
            obj = null;
        } else
        {
            obj = Integer.valueOf(convertToApiWeekday(((EventRecurrence) (obj1)).wkst));
        }
        obj1 = obj;
        if (((Integer) (obj)).intValue() == 1)
        {
            obj1 = obj;
            if (!as[j].contains("WKST=MO"))
            {
                obj1 = null;
            }
        }
        obj = (new RecurRulePart.Builder(i)).setCount(integer).setUntilDateMillis(long2);
        obj.untilDateTimeMillis = long1;
        obj = ((RecurRulePart.Builder) (obj)).setInterval(integer1);
        s = CopyHelper.copyArrayToList(s);
        if (!RecurRulePart.areAllValuesInRange(s, 0, 60, false))
        {
            throw new IllegalArgumentException(String.valueOf("all bySecond values must be within 0-60"));
        }
        break MISSING_BLOCK_LABEL_969;
_L3:
        i = 1;
          goto _L9
_L4:
        i = 2;
          goto _L9
_L5:
        i = 3;
          goto _L9
_L6:
        i = 4;
          goto _L9
_L7:
        i = 5;
          goto _L9
        i = 6;
          goto _L9
        obj.bySecond = s;
        s = CopyHelper.copyArrayToList(ai);
        if (!RecurRulePart.areAllValuesInRange(s, 0, 59, false))
        {
            throw new IllegalArgumentException(String.valueOf("all byMinute values must be within 0-59"));
        }
        obj.byMinute = s;
        s = CopyHelper.copyArrayToList(ai1);
        if (!RecurRulePart.areAllValuesInRange(s, 0, 23, false))
        {
            throw new IllegalArgumentException(String.valueOf("all byHour values must be within 0-23"));
        }
        obj.byHour = s;
        s = ((RecurRulePart.Builder) (obj)).setByDay(abydayrecurrence).setByMonthDay(ai2);
        List list = CopyHelper.copyArrayToList(ai3);
        if (!RecurRulePart.areAllValuesInRange(list, 1, 366, true))
        {
            throw new IllegalArgumentException(String.valueOf("all byYearDay values must be within 1-366, can be negative"));
        }
        s.byYearDay = list;
        list = CopyHelper.copyArrayToList(ai4);
        if (!RecurRulePart.areAllValuesInRange(list, 1, 53, true))
        {
            throw new IllegalArgumentException(String.valueOf("all byWeekNo values must be within 1-53, can be negative"));
        }
        s.byWeekNo = list;
        s = s.setByMonth(ai5);
        list = CopyHelper.copyArrayToList(ai6);
        if (!RecurRulePart.areAllValuesInRange(list, 1, 366, true))
        {
            throw new IllegalArgumentException(String.valueOf("all bySetPos values must be within 1-366, can be negative"));
        }
        s.bySetPos = list;
        s.wkst = ((Integer) (obj1));
        arecurrulepart[j] = s.build();
        j++;
        if (true) goto _L11; else goto _L10
_L10:
        return arecurrulepart;
    }

    private static Long parseDateString(String s)
    {
        if (s == null)
        {
            return null;
        }
        if (s.length() != 8)
        {
            throw new IllegalArgumentException("DATE string has to be of length 8");
        }
        int i;
        int j;
        int k;
        try
        {
            i = Integer.parseInt(s.substring(0, 4));
            j = Integer.parseInt(s.substring(4, 6));
            k = Integer.parseInt(s.substring(6, 8));
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new IllegalArgumentException(s);
        }
        verifyDateCorrectness(j, k);
        s = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        s.set(1, i);
        s.set(2, j - 1);
        s.set(5, k);
        s.set(11, 0);
        s.set(12, 0);
        s.set(13, 0);
        s.set(14, 0);
        return Long.valueOf(s.getTimeInMillis());
    }

    private static Long parseDateTimeString(String s)
    {
        if (s == null)
        {
            return null;
        }
        if (s.length() < 15 || s.length() > 16)
        {
            throw new IllegalArgumentException("DATE-TIME string has to be of length 15 or 16");
        }
        if (s.charAt(8) != 'T')
        {
            throw new IllegalArgumentException("DATE-TIME string has to have 'T' at position 9");
        }
        if (s.length() == 16 && s.charAt(15) != 'Z')
        {
            throw new IllegalArgumentException("DATE-TIME string has to have 'Z' or nothing at position 16");
        }
        boolean flag;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        if (s.length() == 16)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        try
        {
            i = Integer.parseInt(s.substring(0, 4));
            j = Integer.parseInt(s.substring(4, 6));
            k = Integer.parseInt(s.substring(6, 8));
            l = Integer.parseInt(s.substring(9, 11));
            i1 = Integer.parseInt(s.substring(11, 13));
            j1 = Integer.parseInt(s.substring(13, 15));
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new IllegalArgumentException(s);
        }
        verifyDateCorrectness(j, k);
        if (l < 0 || l > 23)
        {
            throw new IllegalArgumentException("Invalid hour in the date-time");
        }
        if (i1 < 0 || i1 > 59)
        {
            throw new IllegalArgumentException("Invalid minute in the date-time");
        }
        if (j1 < 0 || j1 > 60)
        {
            throw new IllegalArgumentException("Invalid second in the date-time");
        }
        if (flag)
        {
            s = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        } else
        {
            s = Calendar.getInstance();
        }
        s.set(1, i);
        s.set(2, j - 1);
        s.set(5, k);
        s.set(11, l);
        s.set(12, i1);
        s.set(13, j1);
        s.set(14, 0);
        return Long.valueOf(s.getTimeInMillis());
    }

    public static Recurrence parseFromStoreStrings(String s, String s1, String s2, String s3)
    {
        Object obj = null;
        RecurRulePart arecurrulepart[] = extractRuleParts(s);
        long al[] = extractDates(s1);
        s1 = extractRuleParts(s2);
        s = extractDates(s3);
        if (s1 != null && s1.length > 1)
        {
            throw new IllegalArgumentException("exruleString contains multiple rules");
        }
        if (arecurrulepart == null && al == null)
        {
            s1 = null;
            s = obj;
        }
        return new Recurrence(arecurrulepart, al, s1, s);
    }

    private static void verifyDateCorrectness(int i, int j)
        throws IllegalArgumentException
    {
        if (j <= 0)
        {
            throw new IllegalArgumentException("Invalid day in the date");
        }
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Invalid month in the date");

        case 1: // '\001'
        case 3: // '\003'
        case 5: // '\005'
        case 7: // '\007'
        case 8: // '\b'
        case 10: // '\n'
        case 12: // '\f'
            if (j > 31)
            {
                throw new IllegalArgumentException("Invalid day in the date");
            }
            break;

        case 4: // '\004'
        case 6: // '\006'
        case 9: // '\t'
        case 11: // '\013'
            if (j > 30)
            {
                throw new IllegalArgumentException("Invalid day in the date");
            }
            break;

        case 2: // '\002'
            if (j > 29)
            {
                throw new IllegalArgumentException("Invalid day in the date");
            }
            break;
        }
    }
}
