// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import com.google.common.collect.ImmutableSet;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.recurrencepicker:
//            RecurrencePickerState, AutoValue_RecurrencePickerState

public final class startWeekday extends startWeekday
{

    private ImmutableSet byDay;
    private ImmutableSet byMonthDay;
    private Integer count;
    private untilDateTimeMillis end;
    private Integer firstDayOfWeek;
    private untilDateTimeMillis frequency;
    private Boolean hasLastOption;
    private Boolean hasNthOption;
    private Integer interval;
    private untilDateTimeMillis monthFrequency;
    private Long startTimeInMillis;
    private Integer startWeekday;
    private TimeZone timeZone;
    private Long untilDateTimeMillis;

    public final RecurrencePickerState build()
    {
        String s1 = "";
        if (frequency == null)
        {
            s1 = String.valueOf("").concat(" frequency");
        }
        String s = s1;
        if (untilDateTimeMillis == null)
        {
            s = String.valueOf(s1).concat(" untilDateTimeMillis");
        }
        s1 = s;
        if (count == null)
        {
            s1 = String.valueOf(s).concat(" count");
        }
        s = s1;
        if (interval == null)
        {
            s = String.valueOf(s1).concat(" interval");
        }
        s1 = s;
        if (byDay == null)
        {
            s1 = String.valueOf(s).concat(" byDay");
        }
        s = s1;
        if (byMonthDay == null)
        {
            s = String.valueOf(s1).concat(" byMonthDay");
        }
        s1 = s;
        if (end == null)
        {
            s1 = String.valueOf(s).concat(" end");
        }
        s = s1;
        if (monthFrequency == null)
        {
            s = String.valueOf(s1).concat(" monthFrequency");
        }
        s1 = s;
        if (hasLastOption == null)
        {
            s1 = String.valueOf(s).concat(" hasLastOption");
        }
        s = s1;
        if (hasNthOption == null)
        {
            s = String.valueOf(s1).concat(" hasNthOption");
        }
        s1 = s;
        if (startTimeInMillis == null)
        {
            s1 = String.valueOf(s).concat(" startTimeInMillis");
        }
        s = s1;
        if (timeZone == null)
        {
            s = String.valueOf(s1).concat(" timeZone");
        }
        s1 = s;
        if (firstDayOfWeek == null)
        {
            s1 = String.valueOf(s).concat(" firstDayOfWeek");
        }
        s = s1;
        if (startWeekday == null)
        {
            s = String.valueOf(s1).concat(" startWeekday");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_RecurrencePickerState(frequency, untilDateTimeMillis, count, interval, byDay, byMonthDay, end, monthFrequency, hasLastOption, hasNthOption, startTimeInMillis, timeZone, firstDayOfWeek, startWeekday);
        }
    }

    public final startWeekday setByDay(ImmutableSet immutableset)
    {
        if (immutableset == null)
        {
            throw new NullPointerException("Null byDay");
        } else
        {
            byDay = immutableset;
            return this;
        }
    }

    public final byDay setByMonthDay(ImmutableSet immutableset)
    {
        if (immutableset == null)
        {
            throw new NullPointerException("Null byMonthDay");
        } else
        {
            byMonthDay = immutableset;
            return this;
        }
    }

    public final byMonthDay setCount(Integer integer)
    {
        if (integer == null)
        {
            throw new NullPointerException("Null count");
        } else
        {
            count = integer;
            return this;
        }
    }

    public final count setEnd(count count1)
    {
        if (count1 == null)
        {
            throw new NullPointerException("Null end");
        } else
        {
            end = count1;
            return this;
        }
    }

    public final end setFirstDayOfWeek(Integer integer)
    {
        if (integer == null)
        {
            throw new NullPointerException("Null firstDayOfWeek");
        } else
        {
            firstDayOfWeek = integer;
            return this;
        }
    }

    public final firstDayOfWeek setFrequency(firstDayOfWeek firstdayofweek)
    {
        if (firstdayofweek == null)
        {
            throw new NullPointerException("Null frequency");
        } else
        {
            frequency = firstdayofweek;
            return this;
        }
    }

    public final frequency setHasLastOption(Boolean boolean1)
    {
        if (boolean1 == null)
        {
            throw new NullPointerException("Null hasLastOption");
        } else
        {
            hasLastOption = boolean1;
            return this;
        }
    }

    public final hasLastOption setHasNthOption(Boolean boolean1)
    {
        if (boolean1 == null)
        {
            throw new NullPointerException("Null hasNthOption");
        } else
        {
            hasNthOption = boolean1;
            return this;
        }
    }

    public final hasNthOption setInterval(Integer integer)
    {
        if (integer == null)
        {
            throw new NullPointerException("Null interval");
        } else
        {
            interval = integer;
            return this;
        }
    }

    public final interval setMonthFrequency(interval interval1)
    {
        if (interval1 == null)
        {
            throw new NullPointerException("Null monthFrequency");
        } else
        {
            monthFrequency = interval1;
            return this;
        }
    }

    public final monthFrequency setStartTimeInMillis(Long long1)
    {
        if (long1 == null)
        {
            throw new NullPointerException("Null startTimeInMillis");
        } else
        {
            startTimeInMillis = long1;
            return this;
        }
    }

    public final startTimeInMillis setStartWeekday(Integer integer)
    {
        if (integer == null)
        {
            throw new NullPointerException("Null startWeekday");
        } else
        {
            startWeekday = integer;
            return this;
        }
    }

    public final startWeekday setTimeZone(TimeZone timezone)
    {
        if (timezone == null)
        {
            throw new NullPointerException("Null timeZone");
        } else
        {
            timeZone = timezone;
            return this;
        }
    }

    public final timeZone setUntilDateTimeMillis(Long long1)
    {
        if (long1 == null)
        {
            throw new NullPointerException("Null untilDateTimeMillis");
        } else
        {
            untilDateTimeMillis = long1;
            return this;
        }
    }

    public ()
    {
    }

    (RecurrencePickerState recurrencepickerstate)
    {
        frequency = recurrencepickerstate.getFrequency();
        untilDateTimeMillis = recurrencepickerstate.getUntilDateTimeMillis();
        count = recurrencepickerstate.getCount();
        interval = recurrencepickerstate.getInterval();
        byDay = recurrencepickerstate.getByDay();
        byMonthDay = recurrencepickerstate.getByMonthDay();
        end = recurrencepickerstate.getEnd();
        monthFrequency = recurrencepickerstate.getMonthFrequency();
        hasLastOption = recurrencepickerstate.getHasLastOption();
        hasNthOption = recurrencepickerstate.getHasNthOption();
        startTimeInMillis = recurrencepickerstate.getStartTimeInMillis();
        timeZone = recurrencepickerstate.getTimeZone();
        firstDayOfWeek = recurrencepickerstate.getFirstDayOfWeek();
        startWeekday = recurrencepickerstate.getStartWeekday();
    }
}
