// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;

public final class EventRecurrence
{

    public static final HashMap parseFreqMap;
    private static HashMap parsePartMap;
    public static final HashMap parseWeekdayMap;
    public int byday[];
    public int bydayCount;
    public int bydayNum[];
    public int byhour[];
    public int byhourCount;
    public int byminute[];
    public int byminuteCount;
    public int bymonth[];
    public int bymonthCount;
    public int bymonthday[];
    public int bymonthdayCount;
    public int bysecond[];
    public int bysecondCount;
    public int bysetpos[];
    public int bysetposCount;
    public int byweekno[];
    public int byweeknoCount;
    public int byyearday[];
    public int byyeardayCount;
    public int count;
    public int freq;
    public int interval;
    public String until;
    public int wkst;

    public EventRecurrence()
    {
    }

    private final void appendByDay(StringBuilder stringbuilder, int i)
    {
        int j = bydayNum[i];
        if (j != 0)
        {
            stringbuilder.append(j);
        }
        stringbuilder.append(day2String(byday[i]));
    }

    private static void appendNumbers(StringBuilder stringbuilder, String s, int i, int ai[])
    {
        if (i > 0)
        {
            stringbuilder.append(s);
            int j = i - 1;
            for (i = 0; i < j; i++)
            {
                stringbuilder.append(ai[i]);
                stringbuilder.append(",");
            }

            stringbuilder.append(ai[j]);
        }
    }

    private static boolean arraysEqual(int ai[], int i, int ai1[], int j)
    {
        if (i == j) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        j = 0;
label0:
        do
        {
label1:
            {
                if (j >= i)
                {
                    break label1;
                }
                if (ai[j] != ai1[j])
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }

    private static String day2String(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder(29)).append("bad day argument: ").append(i).toString());

        case 65536: 
            return "SU";

        case 131072: 
            return "MO";

        case 262144: 
            return "TU";

        case 524288: 
            return "WE";

        case 1048576: 
            return "TH";

        case 2097152: 
            return "FR";

        case 4194304: 
            return "SA";
        }
    }

    public static int day2TimeDay(int i)
    {
        switch (i)
        {
        default:
            throw new RuntimeException((new StringBuilder(28)).append("bad day of week: ").append(i).toString());

        case 65536: 
            return 0;

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
        }
    }

    public static int timeDay2Day(int i)
    {
        switch (i)
        {
        default:
            throw new RuntimeException((new StringBuilder(28)).append("bad day of week: ").append(i).toString());

        case 0: // '\0'
            return 0x10000;

        case 1: // '\001'
            return 0x20000;

        case 2: // '\002'
            return 0x40000;

        case 3: // '\003'
            return 0x80000;

        case 4: // '\004'
            return 0x100000;

        case 5: // '\005'
            return 0x200000;

        case 6: // '\006'
            return 0x400000;
        }
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof EventRecurrence))
            {
                return false;
            }
            obj = (EventRecurrence)obj;
            if (freq != ((EventRecurrence) (obj)).freq || (until != null ? !until.equals(((EventRecurrence) (obj)).until) : ((EventRecurrence) (obj)).until != null) || (count != ((EventRecurrence) (obj)).count || interval != ((EventRecurrence) (obj)).interval || wkst != ((EventRecurrence) (obj)).wkst || !arraysEqual(bysecond, bysecondCount, ((EventRecurrence) (obj)).bysecond, ((EventRecurrence) (obj)).bysecondCount) || !arraysEqual(byminute, byminuteCount, ((EventRecurrence) (obj)).byminute, ((EventRecurrence) (obj)).byminuteCount) || !arraysEqual(byhour, byhourCount, ((EventRecurrence) (obj)).byhour, ((EventRecurrence) (obj)).byhourCount) || !arraysEqual(byday, bydayCount, ((EventRecurrence) (obj)).byday, ((EventRecurrence) (obj)).bydayCount) || !arraysEqual(bydayNum, bydayCount, ((EventRecurrence) (obj)).bydayNum, ((EventRecurrence) (obj)).bydayCount) || !arraysEqual(bymonthday, bymonthdayCount, ((EventRecurrence) (obj)).bymonthday, ((EventRecurrence) (obj)).bymonthdayCount) || !arraysEqual(byyearday, byyeardayCount, ((EventRecurrence) (obj)).byyearday, ((EventRecurrence) (obj)).byyeardayCount) || !arraysEqual(byweekno, byweeknoCount, ((EventRecurrence) (obj)).byweekno, ((EventRecurrence) (obj)).byweeknoCount) || !arraysEqual(bymonth, bymonthCount, ((EventRecurrence) (obj)).bymonth, ((EventRecurrence) (obj)).bymonthCount) || !arraysEqual(bysetpos, bysetposCount, ((EventRecurrence) (obj)).bysetpos, ((EventRecurrence) (obj)).bysetposCount)))
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        throw new UnsupportedOperationException();
    }

    public final void parse(String s)
    {
        until = null;
        bysetposCount = 0;
        bymonthCount = 0;
        byweeknoCount = 0;
        byyeardayCount = 0;
        bymonthdayCount = 0;
        bydayCount = 0;
        byhourCount = 0;
        byminuteCount = 0;
        bysecondCount = 0;
        interval = 0;
        count = 0;
        freq = 0;
        String as[] = s.toUpperCase().split(";");
        int k = as.length;
        int j = 0;
        int i = 0;
        for (; j < k; j++)
        {
            Object obj = as[j];
            if (TextUtils.isEmpty(((CharSequence) (obj))))
            {
                continue;
            }
            int l = ((String) (obj)).indexOf('=');
            if (l <= 0)
            {
                s = String.valueOf(obj);
                if (s.length() != 0)
                {
                    s = "Missing LHS in ".concat(s);
                } else
                {
                    s = new String("Missing LHS in ");
                }
                throw new InvalidFormatException(s);
            }
            String s1 = ((String) (obj)).substring(0, l);
            String s2 = ((String) (obj)).substring(l + 1);
            if (s2.length() == 0)
            {
                s = String.valueOf(obj);
                if (s.length() != 0)
                {
                    s = "Missing RHS in ".concat(s);
                } else
                {
                    s = new String("Missing RHS in ");
                }
                throw new InvalidFormatException(s);
            }
            obj = (PartParser)parsePartMap.get(s1);
            if (obj == null)
            {
                if (s1.startsWith("X-"))
                {
                    continue;
                }
                s = String.valueOf(s1);
                if (s.length() != 0)
                {
                    s = "Couldn't find parser for ".concat(s);
                } else
                {
                    s = new String("Couldn't find parser for ");
                }
                throw new InvalidFormatException(s);
            }
            l = ((PartParser) (obj)).parsePart(s2, this);
            if ((i & l) != 0)
            {
                throw new InvalidFormatException((new StringBuilder(String.valueOf(s1).length() + 25)).append("Part ").append(s1).append(" was specified twice").toString());
            }
            i = l | i;
        }

        if ((i & 0x2000) == 0)
        {
            wkst = 0x20000;
        }
        if ((i & 1) == 0)
        {
            throw new InvalidFormatException("Must specify a FREQ value");
        }
        if ((i & 6) == 6)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Warning: rrule has both UNTIL and COUNT: ".concat(s);
            } else
            {
                s = new String("Warning: rrule has both UNTIL and COUNT: ");
            }
            Log.w("EventRecur", s);
        }
    }

    public final String toString()
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        stringbuilder.append("FREQ=");
        freq;
        JVM INSTR tableswitch 1 7: default 64
    //                   1 259
    //                   2 269
    //                   3 279
    //                   4 289
    //                   5 299
    //                   6 309
    //                   7 319;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        int k;
        if (!TextUtils.isEmpty(until))
        {
            stringbuilder.append(";UNTIL=");
            stringbuilder.append(until);
        }
        if (count != 0)
        {
            stringbuilder.append(";COUNT=");
            stringbuilder.append(count);
        }
        if (interval != 0)
        {
            stringbuilder.append(";INTERVAL=");
            stringbuilder.append(interval);
        }
        if (wkst != 0)
        {
            stringbuilder.append(";WKST=");
            stringbuilder.append(day2String(wkst));
        }
        appendNumbers(stringbuilder, ";BYSECOND=", bysecondCount, bysecond);
        appendNumbers(stringbuilder, ";BYMINUTE=", byminuteCount, byminute);
        appendNumbers(stringbuilder, ";BYSECOND=", byhourCount, byhour);
        int i = bydayCount;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_335;
        }
        stringbuilder.append(";BYDAY=");
        k = i - 1;
        for (int j = 0; j < k; j++)
        {
            appendByDay(stringbuilder, j);
            stringbuilder.append(",");
        }

        break; /* Loop/switch isn't completed */
_L2:
        stringbuilder.append("SECONDLY");
        continue; /* Loop/switch isn't completed */
_L3:
        stringbuilder.append("MINUTELY");
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append("HOURLY");
        continue; /* Loop/switch isn't completed */
_L5:
        stringbuilder.append("DAILY");
        continue; /* Loop/switch isn't completed */
_L6:
        stringbuilder.append("WEEKLY");
        continue; /* Loop/switch isn't completed */
_L7:
        stringbuilder.append("MONTHLY");
        continue; /* Loop/switch isn't completed */
_L8:
        stringbuilder.append("YEARLY");
        if (true) goto _L1; else goto _L9
_L9:
        appendByDay(stringbuilder, k);
        appendNumbers(stringbuilder, ";BYMONTHDAY=", bymonthdayCount, bymonthday);
        appendNumbers(stringbuilder, ";BYYEARDAY=", byyeardayCount, byyearday);
        appendNumbers(stringbuilder, ";BYWEEKNO=", byweeknoCount, byweekno);
        appendNumbers(stringbuilder, ";BYMONTH=", bymonthCount, bymonth);
        appendNumbers(stringbuilder, ";BYSETPOS=", bysetposCount, bysetpos);
        return stringbuilder.toString();
    }

    static 
    {
        HashMap hashmap = new HashMap();
        parsePartMap = hashmap;
        hashmap.put("FREQ", new ParseFreq());
        parsePartMap.put("UNTIL", new ParseUntil());
        parsePartMap.put("COUNT", new ParseCount());
        parsePartMap.put("INTERVAL", new ParseInterval());
        parsePartMap.put("BYSECOND", new ParseBySecond());
        parsePartMap.put("BYMINUTE", new ParseByMinute());
        parsePartMap.put("BYHOUR", new ParseByHour());
        parsePartMap.put("BYDAY", new ParseByDay());
        parsePartMap.put("BYMONTHDAY", new ParseByMonthDay());
        parsePartMap.put("BYYEARDAY", new ParseByYearDay());
        parsePartMap.put("BYWEEKNO", new ParseByWeekNo());
        parsePartMap.put("BYMONTH", new ParseByMonth());
        parsePartMap.put("BYSETPOS", new ParseBySetPos());
        parsePartMap.put("WKST", new ParseWkst());
        hashmap = new HashMap();
        parseFreqMap = hashmap;
        hashmap.put("SECONDLY", Integer.valueOf(1));
        parseFreqMap.put("MINUTELY", Integer.valueOf(2));
        parseFreqMap.put("HOURLY", Integer.valueOf(3));
        parseFreqMap.put("DAILY", Integer.valueOf(4));
        parseFreqMap.put("WEEKLY", Integer.valueOf(5));
        parseFreqMap.put("MONTHLY", Integer.valueOf(6));
        parseFreqMap.put("YEARLY", Integer.valueOf(7));
        hashmap = new HashMap();
        parseWeekdayMap = hashmap;
        hashmap.put("SU", Integer.valueOf(0x10000));
        parseWeekdayMap.put("MO", Integer.valueOf(0x20000));
        parseWeekdayMap.put("TU", Integer.valueOf(0x40000));
        parseWeekdayMap.put("WE", Integer.valueOf(0x80000));
        parseWeekdayMap.put("TH", Integer.valueOf(0x100000));
        parseWeekdayMap.put("FR", Integer.valueOf(0x200000));
        parseWeekdayMap.put("SA", Integer.valueOf(0x400000));
    }

    private class InvalidFormatException extends RuntimeException
    {

        InvalidFormatException(String s)
        {
            super(s);
        }
    }


    private class PartParser
    {

        public static int parseIntRange(String s, int i, int j, boolean flag)
        {
            String s1;
            String s2;
            s1 = s;
            s2 = s;
            if (s.charAt(0) != '+')
            {
                break MISSING_BLOCK_LABEL_26;
            }
            s2 = s;
            s1 = s.substring(1);
            s2 = s1;
            int k = Integer.parseInt(s1);
            if (k >= i && k <= j && (k != 0 || flag))
            {
                break MISSING_BLOCK_LABEL_163;
            }
            s2 = s1;
            s = String.valueOf(s1);
            s2 = s1;
            if (s.length() == 0) goto _L2; else goto _L1
_L1:
            s2 = s1;
            s = "Integer value out of range: ".concat(s);
_L3:
            s2 = s1;
            try
            {
                throw new InvalidFormatException(s);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                s = String.valueOf(s2);
            }
            if (s.length() != 0)
            {
                s = "Invalid integer value: ".concat(s);
            } else
            {
                s = new String("Invalid integer value: ");
            }
            throw new InvalidFormatException(s);
_L2:
            s2 = s1;
            s = new String("Integer value out of range: ");
              goto _L3
            return k;
        }

        public static int[] parseNumberList(String s, int i, int j, boolean flag)
        {
            int k = 0;
            if (s.indexOf(",") >= 0) goto _L2; else goto _L1
_L1:
            int ai[] = new int[1];
            ai[0] = parseIntRange(s, i, j, flag);
            s = ai;
_L4:
            return s;
_L2:
            String as[] = s.split(",");
            int l = as.length;
            int ai1[] = new int[l];
            do
            {
                s = ai1;
                if (k >= l)
                {
                    continue;
                }
                ai1[k] = parseIntRange(as[k], i, j, flag);
                k++;
            } while (true);
            if (true) goto _L4; else goto _L3
_L3:
        }

        public abstract int parsePart(String s, EventRecurrence eventrecurrence);

        PartParser()
        {
        }
    }


    private class ParseFreq extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            Integer integer = (Integer)EventRecurrence.parseFreqMap.get(s);
            if (integer == null)
            {
                s = String.valueOf(s);
                if (s.length() != 0)
                {
                    s = "Invalid FREQ value: ".concat(s);
                } else
                {
                    s = new String("Invalid FREQ value: ");
                }
                throw new InvalidFormatException(s);
            } else
            {
                eventrecurrence.freq = integer.intValue();
                return 1;
            }
        }

        ParseFreq()
        {
        }
    }


    private class ParseUntil extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            eventrecurrence.until = s;
            return 2;
        }

        ParseUntil()
        {
        }
    }


    private class ParseCount extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            eventrecurrence.count = parseIntRange(s, 0x80000000, 0x7fffffff, true);
            if (eventrecurrence.count < 0)
            {
                s = String.valueOf(s);
                if (s.length() != 0)
                {
                    "Invalid Count. Forcing COUNT to 1 from ".concat(s);
                } else
                {
                    new String("Invalid Count. Forcing COUNT to 1 from ");
                }
                eventrecurrence.count = 1;
            }
            return 4;
        }

        ParseCount()
        {
        }
    }


    private class ParseInterval extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            eventrecurrence.interval = parseIntRange(s, 0x80000000, 0x7fffffff, true);
            if (eventrecurrence.interval <= 0)
            {
                s = String.valueOf(s);
                if (s.length() != 0)
                {
                    "Invalid Interval. Forcing INTERVAL to 1 from ".concat(s);
                } else
                {
                    new String("Invalid Interval. Forcing INTERVAL to 1 from ");
                }
                eventrecurrence.interval = 1;
            }
            return 8;
        }

        ParseInterval()
        {
        }
    }


    private class ParseBySecond extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            s = parseNumberList(s, 0, 59, true);
            eventrecurrence.bysecond = s;
            eventrecurrence.bysecondCount = s.length;
            return 16;
        }

        ParseBySecond()
        {
        }
    }


    private class ParseByMinute extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            s = parseNumberList(s, 0, 59, true);
            eventrecurrence.byminute = s;
            eventrecurrence.byminuteCount = s.length;
            return 32;
        }

        ParseByMinute()
        {
        }
    }


    private class ParseByHour extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            s = parseNumberList(s, 0, 23, true);
            eventrecurrence.byhour = s;
            eventrecurrence.byhourCount = s.length;
            return 64;
        }

        ParseByHour()
        {
        }
    }


    private class ParseByDay extends PartParser
    {

        private static void parseWday(String s, int ai[], int ai1[], int i)
        {
            int j = s.length() - 2;
            if (j > 0)
            {
                ai1[i] = parseIntRange(s.substring(0, j), -53, 53, false);
                ai1 = s.substring(j);
            } else
            {
                ai1 = s;
            }
            ai1 = (Integer)EventRecurrence.parseWeekdayMap.get(ai1);
            if (ai1 == null)
            {
                s = String.valueOf(s);
                if (s.length() != 0)
                {
                    s = "Invalid BYDAY value: ".concat(s);
                } else
                {
                    s = new String("Invalid BYDAY value: ");
                }
                throw new InvalidFormatException(s);
            } else
            {
                ai[i] = ai1.intValue();
                return;
            }
        }

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            int i;
            int j;
            j = 0;
            i = 1;
            if (s.indexOf(",") >= 0) goto _L2; else goto _L1
_L1:
            int ai[];
            int ai1[] = new int[1];
            ai = new int[1];
            parseWday(s, ai1, ai, 0);
            s = ai1;
_L4:
            eventrecurrence.byday = s;
            eventrecurrence.bydayNum = ai;
            eventrecurrence.bydayCount = i;
            return 128;
_L2:
            String as[] = s.split(",");
            int k = as.length;
            int ai2[] = new int[k];
            int ai3[] = new int[k];
            do
            {
                i = k;
                ai = ai3;
                s = ai2;
                if (j >= k)
                {
                    continue;
                }
                parseWday(as[j], ai2, ai3, j);
                j++;
            } while (true);
            if (true) goto _L4; else goto _L3
_L3:
        }

        ParseByDay()
        {
        }
    }


    private class ParseByMonthDay extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            s = parseNumberList(s, -31, 31, false);
            eventrecurrence.bymonthday = s;
            eventrecurrence.bymonthdayCount = s.length;
            return 256;
        }

        ParseByMonthDay()
        {
        }
    }


    private class ParseByYearDay extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            s = parseNumberList(s, -366, 366, false);
            eventrecurrence.byyearday = s;
            eventrecurrence.byyeardayCount = s.length;
            return 512;
        }

        ParseByYearDay()
        {
        }
    }


    private class ParseByWeekNo extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            s = parseNumberList(s, -53, 53, false);
            eventrecurrence.byweekno = s;
            eventrecurrence.byweeknoCount = s.length;
            return 1024;
        }

        ParseByWeekNo()
        {
        }
    }


    private class ParseByMonth extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            s = parseNumberList(s, 1, 12, false);
            eventrecurrence.bymonth = s;
            eventrecurrence.bymonthCount = s.length;
            return 2048;
        }

        ParseByMonth()
        {
        }
    }


    private class ParseBySetPos extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            s = parseNumberList(s, 0x80000000, 0x7fffffff, true);
            eventrecurrence.bysetpos = s;
            eventrecurrence.bysetposCount = s.length;
            return 4096;
        }

        ParseBySetPos()
        {
        }
    }


    private class ParseWkst extends PartParser
    {

        public final int parsePart(String s, EventRecurrence eventrecurrence)
        {
            Integer integer = (Integer)EventRecurrence.parseWeekdayMap.get(s);
            if (integer == null)
            {
                s = String.valueOf(s);
                if (s.length() != 0)
                {
                    s = "Invalid WKST value: ".concat(s);
                } else
                {
                    s = new String("Invalid WKST value: ");
                }
                throw new InvalidFormatException(s);
            } else
            {
                eventrecurrence.wkst = integer.intValue();
                return 8192;
            }
        }

        ParseWkst()
        {
        }
    }

}
