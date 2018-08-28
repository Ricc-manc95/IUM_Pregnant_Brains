// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import android.text.format.Time;
import android.util.Log;
import java.util.Iterator;
import java.util.TreeSet;

// Referenced classes of package com.android.calendarcommon2:
//            DateException, EventRecurrence, RecurrenceSet

public final class RecurrenceProcessor
{

    private static final int DAYS_IN_YEAR_PRECEDING_MONTH[] = {
        0, 31, 59, 90, 120, 151, 180, 212, 243, 273, 
        304, 334
    };
    private static final int DAYS_PER_MONTH[] = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 
        30, 31
    };
    private DaySet days;
    private Time generated;
    private Time iterator;
    private StringBuilder stringBuilder;
    private Time until;

    public RecurrenceProcessor()
    {
        iterator = new Time("UTC");
        until = new Time("UTC");
        stringBuilder = new StringBuilder();
        generated = new Time("UTC");
        days = new DaySet();
    }

    private final void expand(Time time, EventRecurrence eventrecurrence, long l, long l1, boolean flag, 
            TreeSet treeset)
        throws DateException
    {
        int ai[];
        int i;
        byte byte0;
        int j;
        int k;
        int i1;
        int j1;
        int k1;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int k5;
        int l5;
        int i6;
        int j6;
        int l6;
        int i7;
        int j7;
        int k7;
        int i8;
        int j8;
        long l8;
        long l9;
        long l10;
        unsafeNormalize(time);
        l9 = normDateTimeComparisonValue(time);
        Object obj;
        String s;
        int ai1[];
        Time time1;
        Time time2;
        DaySet dayset;
        Time time3;
        StringBuilder stringbuilder;
        int i2;
        int i5;
        int k6;
        int l7;
        if (flag && l9 >= l && l9 < l1)
        {
            treeset.add(Long.valueOf(l9));
            j = 1;
        } else
        {
            j = 0;
        }
        time1 = iterator;
        time3 = until;
        stringbuilder = stringBuilder;
        time2 = generated;
        dayset = days;
        dayset.year = 0;
        dayset.month = -1;
        dayset.r = eventrecurrence;
        if (l1 != 0x7fffffffffffffffL)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        if (eventrecurrence.until == null && eventrecurrence.count == 0)
        {
            throw new DateException("No range end provided for a recurrence that has no UNTIL or COUNT.");
        }
        i = eventrecurrence.interval;
        k6 = eventrecurrence.freq;
        k6;
        JVM INSTR tableswitch 1 7: default 2174
    //                   1 336
    //                   2 2184
    //                   3 2190
    //                   4 2196
    //                   5 646
    //                   6 2202
    //                   7 2208;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        try
        {
            throw new DateException((new StringBuilder(20)).append("bad freq=").append(k6).toString());
        }
        // Misplaced declaration of an exception variable
        catch (Time time)
        {
            eventrecurrence = String.valueOf(eventrecurrence);
            Log.w("RecurrenceProcessor", (new StringBuilder(String.valueOf(eventrecurrence).length() + 83)).append("DateException with r=").append(eventrecurrence).append(" rangeStart=").append(l).append(" rangeEnd=").append(l1).toString());
            throw time;
        }
        // Misplaced declaration of an exception variable
        catch (Time time)
        {
            eventrecurrence = String.valueOf(eventrecurrence);
        }
        Log.w("RecurrenceProcessor", (new StringBuilder(String.valueOf(eventrecurrence).length() + 86)).append("RuntimeException with r=").append(eventrecurrence).append(" rangeStart=").append(l).append(" rangeEnd=").append(l1).toString());
        throw time;
_L2:
        byte0 = 1;
_L16:
        if (i <= 0)
        {
            k1 = 1;
        } else
        {
            k1 = i;
        }
        l6 = eventrecurrence.bymonthCount;
        if (k6 > 6 && l6 > 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (k6 < 5) goto _L10; else goto _L9
_L9:
        if (eventrecurrence.bydayCount <= 0 && eventrecurrence.bymonthdayCount <= 0) goto _L10; else goto _L11
_L106:
        i7 = eventrecurrence.byhourCount;
        if (k6 > 3 && i7 > 0)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        j7 = eventrecurrence.byminuteCount;
        if (k6 > 2 && j7 > 0)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        k7 = eventrecurrence.bysecondCount;
        if (k6 > 1 && k7 > 0)
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        time1.set(time);
        if (byte0 != 5 || !flag2)
        {
            break MISSING_BLOCK_LABEL_474;
        }
        time1.monthDay = 1;
        if (eventrecurrence.until == null) goto _L13; else goto _L12
_L12:
        s = eventrecurrence.until;
        obj = s;
        if (s.length() == 15)
        {
            obj = (new StringBuilder(String.valueOf(s).length() + 1)).append(s).append('Z').toString();
        }
        time3.parse(((String) (obj)));
        time3.switchTimezone(time.timezone);
        l8 = normDateTimeComparisonValue(time3);
_L107:
        stringbuilder.ensureCapacity(15);
        stringbuilder.setLength(15);
        i2 = 0;
        k = j;
_L101:
        if (i2 <= 2000) goto _L15; else goto _L14
_L14:
        time = String.valueOf(eventrecurrence);
        Log.w("RecurrenceProcessor", (new StringBuilder(String.valueOf(time).length() + 97)).append("Recurrence processing stuck with r=").append(time).append(" rangeStart=").append(l).append(" rangeEnd=").append(l1).toString());
        return;
_L6:
        i = eventrecurrence.interval * 7;
        if (i <= 0)
        {
            i = 7;
            byte0 = 4;
        } else
        {
            byte0 = 4;
        }
          goto _L16
_L15:
        unsafeNormalize(time1);
        l7 = time1.year;
        i8 = time1.month;
        k5 = time1.monthDay;
        l5 = time1.hour;
        i6 = time1.minute;
        j6 = time1.second;
        time2.set(time1);
        j2 = 0;
_L132:
        if (!flag1) goto _L18; else goto _L17
_L17:
        k2 = eventrecurrence.bymonth[j2];
_L109:
        j = 1;
        if (!flag2) goto _L20; else goto _L19
_L19:
        if (k6 != 5) goto _L22; else goto _L21
_L21:
        i = time1.weekDay;
        j = EventRecurrence.day2TimeDay(eventrecurrence.wkst);
        j = time1.monthDay - ((i - j) + 7) % 7;
        j1 = j + 6;
_L32:
        if (!flag2) goto _L24; else goto _L23
_L23:
        i1 = time1.year;
        i = time1.month;
        time = null;
        if (j > 0 && j <= 28) goto _L26; else goto _L25
_L25:
        time = dayset.time;
        time.set(j, i, i1);
        unsafeNormalize(time);
        i1 = time.year;
        i = time.month;
        l2 = time.monthDay;
_L105:
        if (i1 == dayset.year && i == dayset.month) goto _L28; else goto _L27
_L27:
        obj = time;
        if (time != null)
        {
            break MISSING_BLOCK_LABEL_918;
        }
        obj = dayset.time;
        ((Time) (obj)).set(l2, i, i1);
        unsafeNormalize(((Time) (obj)));
        dayset.year = i1;
        dayset.month = i;
        time = dayset.r;
        i = 0;
        i1 = 0;
        l3 = ((Time) (obj)).getActualMaximum(4);
        i4 = ((EventRecurrence) (time)).bydayCount;
        if (i4 <= 0) goto _L30; else goto _L29
_L29:
        i = ((Time) (obj)).monthDay;
          goto _L31
_L22:
        j1 = time2.getActualMaximum(4);
          goto _L32
_L108:
        i3 = ((Time) (obj)).weekDay;
        if (i3 >= i)
        {
            i3 = (i3 - i) + 1;
        } else
        {
            i3 = (i3 - i) + 8;
        }
        ai = ((EventRecurrence) (time)).byday;
        ai1 = ((EventRecurrence) (time)).bydayNum;
        j3 = 0;
_L110:
        i = i1;
        if (j3 >= i4) goto _L30; else goto _L33
_L33:
        j4 = ai1[j3];
        k3 = (EventRecurrence.day2TimeDay(ai[j3]) - i3) + 1;
        break MISSING_BLOCK_LABEL_1055;
_L30:
        i1 = i;
        if (((EventRecurrence) (time)).freq <= 5) goto _L35; else goto _L34
_L34:
        k3 = ((EventRecurrence) (time)).bymonthdayCount;
        i1 = i;
        if (k3 == 0) goto _L35; else goto _L36
_L36:
        ai = ((EventRecurrence) (time)).bymonthday;
        if (((EventRecurrence) (time)).bydayCount != 0) goto _L38; else goto _L37
_L37:
        i3 = 0;
          goto _L39
_L35:
        dayset.days = i1;
_L28:
        if ((dayset.days & 1 << l2) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L40
_L102:
        if (!flag3) goto _L42; else goto _L41
_L41:
        i3 = eventrecurrence.byhour[l2];
          goto _L43
_L103:
        if (!flag4) goto _L45; else goto _L44
_L44:
        k3 = eventrecurrence.byminute[j3];
          goto _L46
_L104:
        if (!flag5) goto _L48; else goto _L47
_L47:
        i = eventrecurrence.bysecond[l3];
_L143:
        time2.set(i, k3, i3, i1, k2 - 1, l7);
        unsafeNormalize(time2);
        l10 = normDateTimeComparisonValue(time2);
        if (l10 < l9) goto _L50; else goto _L49
_L49:
        i4 = eventrecurrence.freq;
        if (6 < i4) goto _L52; else goto _L51
_L51:
        if (eventrecurrence.bymonthCount <= 0) goto _L52; else goto _L53
_L53:
        time = eventrecurrence.bymonth;
        j4 = eventrecurrence.bymonthCount;
        k4 = time2.month;
        i = 0;
          goto _L54
_L122:
        if (l10 > l8 || l10 >= l1) goto _L56; else goto _L55
_L55:
        if (l10 < l) goto _L58; else goto _L57
_L57:
        if (!flag) goto _L60; else goto _L59
_L59:
        treeset.add(Long.valueOf(l10));
_L58:
        i = k;
        if (eventrecurrence.count <= 0) goto _L62; else goto _L61
_L61:
        if (eventrecurrence.count == k) goto _L56; else goto _L63
_L63:
        i = k;
          goto _L62
_L131:
        j = time1.monthDay;
        time2.set(time1);
        i = 1;
          goto _L64
_L133:
        throw new RuntimeException((new StringBuilder(21)).append("bad field=").append(byte0).toString());
_L52:
        if (5 < i4) goto _L66; else goto _L65
_L65:
        if (eventrecurrence.byweeknoCount <= 0 || listContains(eventrecurrence.byweekno, eventrecurrence.byweeknoCount, time2.getWeekNumber(), time2.getActualMaximum(9))) goto _L66; else goto _L67
_L67:
        i = 2;
          goto _L68
_L66:
        if (4 < i4) goto _L70; else goto _L69
_L69:
        if (eventrecurrence.byyeardayCount <= 0 || listContains(eventrecurrence.byyearday, eventrecurrence.byyeardayCount, time2.yearDay, time2.getActualMaximum(8))) goto _L72; else goto _L71
_L71:
        i = 3;
          goto _L68
_L72:
        if (eventrecurrence.bymonthdayCount <= 0 || listContains(eventrecurrence.bymonthday, eventrecurrence.bymonthdayCount, time2.monthDay, time2.getActualMaximum(4))) goto _L74; else goto _L73
_L73:
        i = 4;
          goto _L68
_L74:
        if (eventrecurrence.bydayCount <= 0) goto _L70; else goto _L75
_L75:
        time = eventrecurrence.byday;
        j4 = eventrecurrence.bydayCount;
        k4 = EventRecurrence.timeDay2Day(time2.weekDay);
        i = 0;
          goto _L76
_L70:
        if (3 < i4) goto _L78; else goto _L77
_L77:
        if (listContains(eventrecurrence.byhour, eventrecurrence.byhourCount, time2.hour, time2.getActualMaximum(3))) goto _L78; else goto _L79
_L79:
        i = 6;
          goto _L68
_L78:
        if (2 < i4) goto _L81; else goto _L80
_L80:
        if (listContains(eventrecurrence.byminute, eventrecurrence.byminuteCount, time2.minute, time2.getActualMaximum(2))) goto _L81; else goto _L82
_L82:
        i = 7;
          goto _L68
_L81:
        if (1 < i4) goto _L84; else goto _L83
_L83:
        if (listContains(eventrecurrence.bysecond, eventrecurrence.bysecondCount, time2.second, time2.getActualMaximum(1))) goto _L84; else goto _L85
_L85:
        i = 8;
          goto _L68
_L84:
        if (eventrecurrence.bysetposCount <= 0 || i4 != 6)
        {
            break MISSING_BLOCK_LABEL_3042;
        }
        if (eventrecurrence.bydayCount <= 0)
        {
            break MISSING_BLOCK_LABEL_3042;
        }
        i = eventrecurrence.bydayCount - 1;
_L87:
        if (i < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (eventrecurrence.bydayNum[i] != 0)
        {
            break MISSING_BLOCK_LABEL_3042;
        }
        i--;
        if (true) goto _L87; else goto _L86
_L86:
        j4 = time2.weekDay;
        k4 = time2.monthDay;
        i = 0;
        i4 = 0;
_L89:
        if (i >= eventrecurrence.bydayCount)
        {
            break; /* Loop/switch isn't completed */
        }
        i5 = eventrecurrence.byday[i];
        i++;
        i4 = i5 | i4;
        if (true) goto _L89; else goto _L88
_L88:
        j8 = time2.getActualMaximum(4);
        time = new int[j8];
        break MISSING_BLOCK_LABEL_1789;
_L148:
        i4 = eventrecurrence.bysetposCount - 1;
_L149:
        if (i4 < 0) goto _L91; else goto _L90
_L90:
        j4 = eventrecurrence.bysetpos[i4];
        if (j4 <= 0) goto _L93; else goto _L92
_L92:
        if (j4 > i)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (time[j4 - 1] != time2.monthDay)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = 1;
          goto _L94
_L93:
        if (j4 >= 0) goto _L96; else goto _L95
_L95:
        if (i + j4 < 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (time[j4 + i] != time2.monthDay)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = 1;
          goto _L94
_L96:
        throw new RuntimeException("invalid bysetpos value");
_L60:
        treeset.remove(Long.valueOf(l10));
          goto _L58
_L134:
        time1.second = i1 + time1.second;
_L100:
        unsafeNormalize(time1);
        if (byte0 != 6 && byte0 != 5) goto _L98; else goto _L97
_L97:
        if (time1.monthDay == j) goto _L98; else goto _L99
_L99:
        i++;
        time1.set(time2);
          goto _L64
_L135:
        time1.minute = i1 + time1.minute;
          goto _L100
_L136:
        time1.hour = i1 + time1.hour;
          goto _L100
_L137:
        time1.monthDay = i1 + time1.monthDay;
          goto _L100
_L138:
        time1.month = i1 + time1.month;
          goto _L100
_L139:
        time1.year = i1 + time1.year;
          goto _L100
_L140:
        time1.monthDay = i1 + time1.monthDay;
          goto _L100
_L141:
        time1.monthDay = i1 + time1.monthDay;
          goto _L100
_L98:
        i2++;
          goto _L101
_L20:
        j1 = 0;
          goto _L32
_L129:
        k = i;
          goto _L102
_L126:
        k = i;
          goto _L103
_L124:
        k = i;
          goto _L104
_L50:
        i = k;
          goto _L62
_L26:
        l2 = j;
          goto _L105
_L11:
        flag2 = true;
          goto _L106
_L56:
        return;
_L3:
        byte0 = 2;
          goto _L16
_L4:
        byte0 = 3;
          goto _L16
_L5:
        byte0 = 4;
          goto _L16
_L7:
        byte0 = 5;
          goto _L16
_L8:
        byte0 = 6;
          goto _L16
_L10:
        flag2 = false;
          goto _L106
_L13:
        l8 = 0x7fffffffffffffffL;
          goto _L107
_L31:
        while (i >= 8) 
        {
            i -= 7;
        }
          goto _L108
_L18:
        k2 = i8 + 1;
          goto _L109
        i = k3;
        if (k3 <= 0)
        {
            i = k3 + 7;
        }
        if (j4 == 0)
        {
            k3 = i;
            do
            {
                i = i1;
                if (k3 > l3)
                {
                    break;
                }
                i1 |= 1 << k3;
                k3 += 7;
            } while (true);
        } else
        {
            k3 = i;
            if (j4 > 0)
            {
                k3 = i + (j4 - 1) * 7;
                i = i1;
                if (k3 <= l3)
                {
                    i = i1 | 1 << k3;
                }
            } else
            {
                for (; k3 <= l3; k3 += 7) { }
                k3 += j4 * 7;
                i = i1;
                if (k3 > 0)
                {
                    i = i1 | 1 << k3;
                }
            }
        }
        j3++;
        i1 = i;
          goto _L110
_L39:
        i1 = i;
        if (i3 >= k3) goto _L35; else goto _L111
_L111:
        i1 = ai[i3];
        if (i1 >= 0)
        {
            i1 = i | 1 << i1;
        } else
        {
            j3 = i1 + l3 + 1;
            i1 = i;
            if (j3 > 0)
            {
                i1 = i;
                if (j3 <= l3)
                {
                    i1 = i | 1 << j3;
                }
            }
        }
        i3++;
        i = i1;
          goto _L39
_L38:
        i3 = 1;
_L113:
        i1 = i;
        if (i3 > l3) goto _L35; else goto _L112
_L112:
label0:
        {
            i1 = i;
            if ((1 << i3 & i) == 0)
            {
                break label0;
            }
            for (j3 = 0; j3 < k3; j3++)
            {
                i1 = i;
                if (ai[j3] == i3)
                {
                    break label0;
                }
            }

            i1 = i & ~(1 << i3);
        }
        i3++;
        i = i1;
          goto _L113
          goto _L35
_L40:
        if (i == 0) goto _L115; else goto _L114
_L114:
        i1 = j;
_L142:
        l2 = 0;
          goto _L102
_L43:
        j3 = 0;
          goto _L103
_L46:
        l3 = 0;
          goto _L104
_L54:
        if (i >= j4) goto _L117; else goto _L116
_L116:
        if (time[i] != k4 + 1) goto _L119; else goto _L118
_L118:
        i = 1;
_L144:
        if (i != 0) goto _L52; else goto _L120
_L120:
        i = 1;
_L68:
        if (i != 0) goto _L50; else goto _L121
_L121:
        if (l9 != l10 || !flag || l9 < l || l9 >= l1)
        {
            k++;
        }
          goto _L122
_L62:
        l3++;
        if (flag5 && l3 < k7) goto _L124; else goto _L123
_L123:
        j3++;
        if (flag4 && j3 < j7) goto _L126; else goto _L125
_L125:
        l2++;
        k = i;
        if (!flag3) goto _L115; else goto _L127
_L127:
        if (l2 < i7) goto _L129; else goto _L128
_L128:
        k = i;
_L115:
        j++;
        if (flag2 && j <= j1) goto _L32; else goto _L130
_L130:
        j2++;
        if (flag1 && j2 < l6) goto _L132; else goto _L131
_L64:
        i1 = k1 * i;
        byte0;
        JVM INSTR tableswitch 1 8: default 2860
    //                   1 1924
    //                   2 1981
    //                   3 1997
    //                   4 2013
    //                   5 2029
    //                   6 2045
    //                   7 2061
    //                   8 2077;
           goto _L133 _L134 _L135 _L136 _L137 _L138 _L139 _L140 _L141
_L24:
        i1 = k5;
          goto _L142
_L42:
        i3 = l5;
          goto _L43
_L45:
        k3 = i6;
          goto _L46
_L48:
        i = j6;
          goto _L143
_L119:
        i++;
          goto _L54
_L117:
        i = 0;
          goto _L144
_L76:
        if (i >= j4) goto _L146; else goto _L145
_L145:
        if (time[i] == k4) goto _L70; else goto _L147
_L147:
        i++;
          goto _L76
          goto _L70
_L146:
        i = 5;
          goto _L68
        i = 0;
        int j5 = ((j4 - k4) + 36) % 7;
        j4 = 1;
        while (j4 <= j8) 
        {
            int l4 = i;
            if ((0x10000 << j5 & i4) != 0)
            {
                time[i] = j4;
                l4 = i + 1;
            }
            j5++;
            i = j5;
            if (j5 == 7)
            {
                i = 0;
            }
            j4++;
            j5 = i;
            i = l4;
        }
          goto _L148
_L94:
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_3042;
        }
        i = 9;
          goto _L68
        i4--;
          goto _L149
_L91:
        i = 0;
          goto _L94
        i = 0;
          goto _L68
    }

    private static boolean listContains(int ai[], int i, int j, int k)
    {
        boolean flag = false;
        int l = k;
        for (k = ((flag) ? 1 : 0); k < i; k++)
        {
            int i1 = ai[k];
            if (i1 > 0)
            {
                if (i1 == j)
                {
                    return true;
                }
                continue;
            }
            i1 = l + i1;
            l = i1;
            if (i1 == j)
            {
                return true;
            }
        }

        return false;
    }

    private static final long normDateTimeComparisonValue(Time time)
    {
        return ((long)time.year << 26) + (long)(time.month << 22) + (long)(time.monthDay << 17) + (long)(time.hour << 12) + (long)(time.minute << 6) + (long)time.second;
    }

    static void unsafeNormalize(Time time)
    {
        int l1 = time.second;
        int j1 = time.minute;
        int i1 = time.hour;
        int j = time.monthDay;
        int l = time.month;
        int k = time.year;
        int i;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        if (l1 < 0)
        {
            i = l1 - 59;
        } else
        {
            i = l1;
        }
        k2 = i / 60;
        i2 = j1 + k2;
        if (i2 < 0)
        {
            i = i2 - 59;
        } else
        {
            i = i2;
        }
        l2 = i / 60;
        j2 = i1 + l2;
        if (j2 < 0)
        {
            i = j2 - 23;
        } else
        {
            i = j2;
        }
        i3 = i / 24;
        i = i3 + j;
        while (i <= 0) 
        {
            if (l > 1)
            {
                if (k % 4 == 0 && (k % 100 != 0 || k % 400 == 0))
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j != 0)
                {
                    j = 366;
                } else
                {
                    j = 365;
                }
            } else
            {
                j = k - 1;
                if (j % 4 == 0 && (j % 100 != 0 || j % 400 == 0))
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j != 0)
                {
                    j = 366;
                } else
                {
                    j = 365;
                }
            }
            i += j;
            k--;
        }
        if (l < 0)
        {
            i1 = (l + 1) / 12 - 1;
            j = k + i1;
            l -= i1 * 12;
            k = i;
            i = l;
        } else
        if (l >= 12)
        {
            i1 = l / 12;
            j = k + i1;
            l -= i1 * 12;
            k = i;
            i = l;
        } else
        {
            j = k;
            k = i;
            i = l;
        }
        do
        {
label0:
            {
                l = j;
                i1 = k;
                if (i == 0)
                {
                    int k1;
                    if (j % 4 == 0 && (j % 100 != 0 || j % 400 == 0))
                    {
                        l = 1;
                    } else
                    {
                        l = 0;
                    }
                    if (l != 0)
                    {
                        k1 = 366;
                    } else
                    {
                        k1 = 365;
                    }
                    l = j;
                    i1 = k;
                    if (k > k1)
                    {
                        l = j + 1;
                        i1 = k - k1;
                    }
                }
                j = DAYS_PER_MONTH[i];
                if (j == 28)
                {
                    if (l % 4 == 0 && (l % 100 != 0 || l % 400 == 0))
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j != 0)
                    {
                        j = 29;
                    } else
                    {
                        j = 28;
                    }
                }
                if (i1 <= j)
                {
                    break label0;
                }
                k1 = i + 1;
                i = l;
                k = k1;
                if (k1 >= 12)
                {
                    k = k1 - 12;
                    i = l + 1;
                }
                l = k;
                k = i1 - j;
                j = i;
                i = l;
            }
        } while (true);
        time.second = l1 - k2 * 60;
        time.minute = i2 - l2 * 60;
        time.hour = j2 - i3 * 24;
        time.monthDay = i1;
        time.month = i;
        time.year = l;
        if (i <= 1)
        {
            j = i + 12;
            k = l - 1;
        } else
        {
            j = i;
            k = l;
        }
        time.weekDay = ((((j * 13 - 14) / 5 + i1 + k + k / 4) - k / 100) + k / 400) % 7;
        j = (i1 + DAYS_IN_YEAR_PRECEDING_MONTH[i]) - 1;
        if (i < 2) goto _L2; else goto _L1
_L1:
        if (l % 4 == 0 && (l % 100 != 0 || l % 400 == 0))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L2; else goto _L3
_L3:
        i = j + 1;
_L5:
        time.yearDay = i;
        return;
_L2:
        i = j;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final long[] expand(Time time, RecurrenceSet recurrenceset, long l, long l1)
        throws DateException
    {
        Object obj = time.timezone;
        iterator.clear(((String) (obj)));
        generated.clear(((String) (obj)));
        iterator.set(l);
        long l2 = normDateTimeComparisonValue(iterator);
        if (l1 != -1L)
        {
            iterator.set(l1);
            l = normDateTimeComparisonValue(iterator);
        } else
        {
            l = 0x7fffffffffffffffL;
        }
        obj = new TreeSet();
        if (recurrenceset.rrules != null)
        {
            EventRecurrence aeventrecurrence[] = recurrenceset.rrules;
            int k1 = aeventrecurrence.length;
            for (int i = 0; i < k1; i++)
            {
                expand(time, aeventrecurrence[i], l2, l, true, ((TreeSet) (obj)));
            }

        }
        if (recurrenceset.rdates != null)
        {
            long al[] = recurrenceset.rdates;
            int i2 = al.length;
            for (int j = 0; j < i2; j++)
            {
                l1 = al[j];
                iterator.set(l1);
                ((TreeSet) (obj)).add(Long.valueOf(normDateTimeComparisonValue(iterator)));
            }

        }
        if (recurrenceset.exrules != null)
        {
            EventRecurrence aeventrecurrence1[] = recurrenceset.exrules;
            int j2 = aeventrecurrence1.length;
            for (int k = 0; k < j2; k++)
            {
                expand(time, aeventrecurrence1[k], l2, l, false, ((TreeSet) (obj)));
            }

        }
        if (recurrenceset.exdates != null)
        {
            time = recurrenceset.exdates;
            int k2 = time.length;
            for (int i1 = 0; i1 < k2; i1++)
            {
                l = time[i1];
                iterator.set(l);
                ((TreeSet) (obj)).remove(Long.valueOf(normDateTimeComparisonValue(iterator)));
            }

        }
        if (((TreeSet) (obj)).isEmpty())
        {
            return new long[0];
        }
        time = new long[((TreeSet) (obj)).size()];
        recurrenceset = ((TreeSet) (obj)).iterator();
        for (int j1 = 0; recurrenceset.hasNext(); j1++)
        {
            Long long1 = (Long)recurrenceset.next();
            Time time1 = iterator;
            l = long1.longValue();
            time1.year = (int)(l >> 26);
            time1.month = (int)(l >> 22) & 0xf;
            time1.monthDay = (int)(l >> 17) & 0x1f;
            time1.hour = (int)(l >> 12) & 0x1f;
            time1.minute = (int)(l >> 6) & 0x3f;
            time1.second = (int)(l & 63L);
            time[j1] = iterator.toMillis(true);
        }

        return time;
    }


    private class DaySet
    {

        public int days;
        public int month;
        public EventRecurrence r;
        public Time time;
        public int year;

        public DaySet()
        {
            time = new Time("UTC");
        }
    }

}
