// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import com.google.android.calendar.api.common.CopyHelper;
import com.google.calendar.v2a.android.util.time.TimestampUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            RecurRulePart, ByDayRecurrence

public static final class wkst
{

    public List byDay;
    public List byHour;
    public List byMinute;
    public List byMonth;
    public List byMonthDay;
    public List bySecond;
    public List bySetPos;
    public List byWeekNo;
    public List byYearDay;
    public Integer count;
    private int freq;
    public Integer interval;
    public Long untilDateMillis;
    public Long untilDateTimeMillis;
    public Integer wkst;

    public final RecurRulePart build()
    {
        return new RecurRulePart(freq, untilDateMillis, untilDateTimeMillis, count, interval, bySecond, byMinute, byHour, byDay, byMonthDay, byYearDay, byWeekNo, byMonth, bySetPos, wkst);
    }

    public final wkst setByDay(List list)
    {
        list = new ArrayList(list);
        if (!RecurRulePart.areAllByDayOffsetsInAbsRange(list, 1, 53))
        {
            throw new IllegalArgumentException(String.valueOf("all offsets in byDay must be within 1-53, can be negative"));
        } else
        {
            byDay = list;
            return this;
        }
    }

    public final byDay setByDay(ByDayRecurrence abydayrecurrence[])
    {
        if (abydayrecurrence == null || abydayrecurrence.length == 0)
        {
            abydayrecurrence = Collections.emptyList();
        } else
        {
            abydayrecurrence = Arrays.asList(Arrays.copyOf(abydayrecurrence, abydayrecurrence.length));
        }
        if (!RecurRulePart.areAllByDayOffsetsInAbsRange(abydayrecurrence, 1, 53))
        {
            throw new IllegalArgumentException(String.valueOf("all offsets in byDay must be within 1-53, can be negative"));
        } else
        {
            byDay = abydayrecurrence;
            return this;
        }
    }

    public final byDay setByMonth(int ai[])
    {
        ai = CopyHelper.copyArrayToList(ai);
        if (!RecurRulePart.areAllValuesInRange(ai, 1, 12, false))
        {
            throw new IllegalArgumentException(String.valueOf("all byMonth values must be within 1-12"));
        } else
        {
            byMonth = ai;
            return this;
        }
    }

    public final byMonth setByMonthDay(List list)
    {
        list = new ArrayList(list);
        if (!RecurRulePart.areAllValuesInRange(list, 1, 31, true))
        {
            throw new IllegalArgumentException(String.valueOf("all byMonthDay values must be within 1-31, can be negative"));
        } else
        {
            byMonthDay = list;
            return this;
        }
    }

    public final byMonthDay setByMonthDay(int ai[])
    {
        ai = CopyHelper.copyArrayToList(ai);
        if (!RecurRulePart.areAllValuesInRange(ai, 1, 31, true))
        {
            throw new IllegalArgumentException(String.valueOf("all byMonthDay values must be within 1-31, can be negative"));
        } else
        {
            byMonthDay = ai;
            return this;
        }
    }

    public final byMonthDay setCount(Integer integer)
    {
        boolean flag;
        if (integer == null || integer.intValue() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("count has to be positive"));
        } else
        {
            count = integer;
            return this;
        }
    }

    public final count setInterval(Integer integer)
    {
        boolean flag;
        if (integer == null || integer.intValue() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("interval has to be positive"));
        } else
        {
            interval = integer;
            return this;
        }
    }

    public final interval setUntilDateMillis(Long long1)
    {
        boolean flag;
        if (long1 == null || TimestampUtil.isTimestampUtcMidnight(long1.longValue()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("untilDateMillis has to be UTC midnight"));
        } else
        {
            untilDateMillis = long1;
            return this;
        }
    }

    public (int i)
    {
        bySecond = Collections.emptyList();
        byMinute = Collections.emptyList();
        byHour = Collections.emptyList();
        byDay = Collections.emptyList();
        byMonthDay = Collections.emptyList();
        byYearDay = Collections.emptyList();
        byWeekNo = Collections.emptyList();
        byMonth = Collections.emptyList();
        bySetPos = Collections.emptyList();
        freq = i;
    }

    public freq(RecurRulePart recurrulepart)
    {
        bySecond = Collections.emptyList();
        byMinute = Collections.emptyList();
        byHour = Collections.emptyList();
        byDay = Collections.emptyList();
        byMonthDay = Collections.emptyList();
        byYearDay = Collections.emptyList();
        byWeekNo = Collections.emptyList();
        byMonth = Collections.emptyList();
        bySetPos = Collections.emptyList();
        freq = recurrulepart.freq;
        untilDateMillis = recurrulepart.untilDateMillis;
        untilDateTimeMillis = recurrulepart.untilDateTimeMillis;
        count = recurrulepart.count;
        interval = recurrulepart.interval;
        bySecond = recurrulepart.bySecond;
        byMinute = recurrulepart.byMinute;
        byHour = recurrulepart.byHour;
        byDay = recurrulepart.byDay;
        byMonthDay = recurrulepart.byMonthDay;
        byYearDay = recurrulepart.byYearDay;
        byWeekNo = recurrulepart.byWeekNo;
        byMonth = recurrulepart.byMonth;
        bySetPos = recurrulepart.bySetPos;
        wkst = recurrulepart.wkst;
    }
}
