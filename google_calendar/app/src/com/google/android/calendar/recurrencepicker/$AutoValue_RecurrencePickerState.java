// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import com.google.common.collect.ImmutableSet;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.recurrencepicker:
//            RecurrencePickerState

abstract class $AutoValue_RecurrencePickerState extends RecurrencePickerState
{

    private final ImmutableSet byDay;
    private final ImmutableSet byMonthDay;
    private final Integer count;
    private final RecurrencePickerState.End end;
    private final Integer firstDayOfWeek;
    private final RecurrencePickerState.Frequency frequency;
    private final Boolean hasLastOption;
    private final Boolean hasNthOption;
    private final Integer interval;
    private final RecurrencePickerState.MonthFrequency monthFrequency;
    private final Long startTimeInMillis;
    private final Integer startWeekday;
    private final TimeZone timeZone;
    private final Long untilDateTimeMillis;

    $AutoValue_RecurrencePickerState(RecurrencePickerState.Frequency frequency1, Long long1, Integer integer, Integer integer1, ImmutableSet immutableset, ImmutableSet immutableset1, RecurrencePickerState.End end1, 
            RecurrencePickerState.MonthFrequency monthfrequency, Boolean boolean1, Boolean boolean2, Long long2, TimeZone timezone, Integer integer2, Integer integer3)
    {
        if (frequency1 == null)
        {
            throw new NullPointerException("Null frequency");
        }
        frequency = frequency1;
        if (long1 == null)
        {
            throw new NullPointerException("Null untilDateTimeMillis");
        }
        untilDateTimeMillis = long1;
        if (integer == null)
        {
            throw new NullPointerException("Null count");
        }
        count = integer;
        if (integer1 == null)
        {
            throw new NullPointerException("Null interval");
        }
        interval = integer1;
        if (immutableset == null)
        {
            throw new NullPointerException("Null byDay");
        }
        byDay = immutableset;
        if (immutableset1 == null)
        {
            throw new NullPointerException("Null byMonthDay");
        }
        byMonthDay = immutableset1;
        if (end1 == null)
        {
            throw new NullPointerException("Null end");
        }
        end = end1;
        if (monthfrequency == null)
        {
            throw new NullPointerException("Null monthFrequency");
        }
        monthFrequency = monthfrequency;
        if (boolean1 == null)
        {
            throw new NullPointerException("Null hasLastOption");
        }
        hasLastOption = boolean1;
        if (boolean2 == null)
        {
            throw new NullPointerException("Null hasNthOption");
        }
        hasNthOption = boolean2;
        if (long2 == null)
        {
            throw new NullPointerException("Null startTimeInMillis");
        }
        startTimeInMillis = long2;
        if (timezone == null)
        {
            throw new NullPointerException("Null timeZone");
        }
        timeZone = timezone;
        if (integer2 == null)
        {
            throw new NullPointerException("Null firstDayOfWeek");
        }
        firstDayOfWeek = integer2;
        if (integer3 == null)
        {
            throw new NullPointerException("Null startWeekday");
        } else
        {
            startWeekday = integer3;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof RecurrencePickerState)
            {
                if (!frequency.equals(((RecurrencePickerState) (obj = (RecurrencePickerState)obj)).getFrequency()) || !untilDateTimeMillis.equals(((RecurrencePickerState) (obj)).getUntilDateTimeMillis()) || !count.equals(((RecurrencePickerState) (obj)).getCount()) || !interval.equals(((RecurrencePickerState) (obj)).getInterval()) || !byDay.equals(((RecurrencePickerState) (obj)).getByDay()) || !byMonthDay.equals(((RecurrencePickerState) (obj)).getByMonthDay()) || !end.equals(((RecurrencePickerState) (obj)).getEnd()) || !monthFrequency.equals(((RecurrencePickerState) (obj)).getMonthFrequency()) || !hasLastOption.equals(((RecurrencePickerState) (obj)).getHasLastOption()) || !hasNthOption.equals(((RecurrencePickerState) (obj)).getHasNthOption()) || !startTimeInMillis.equals(((RecurrencePickerState) (obj)).getStartTimeInMillis()) || !timeZone.equals(((RecurrencePickerState) (obj)).getTimeZone()) || !firstDayOfWeek.equals(((RecurrencePickerState) (obj)).getFirstDayOfWeek()) || !startWeekday.equals(((RecurrencePickerState) (obj)).getStartWeekday()))
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

    public final ImmutableSet getByDay()
    {
        return byDay;
    }

    public final ImmutableSet getByMonthDay()
    {
        return byMonthDay;
    }

    public final Integer getCount()
    {
        return count;
    }

    public final RecurrencePickerState.End getEnd()
    {
        return end;
    }

    public final Integer getFirstDayOfWeek()
    {
        return firstDayOfWeek;
    }

    public final RecurrencePickerState.Frequency getFrequency()
    {
        return frequency;
    }

    public final Boolean getHasLastOption()
    {
        return hasLastOption;
    }

    public final Boolean getHasNthOption()
    {
        return hasNthOption;
    }

    public final Integer getInterval()
    {
        return interval;
    }

    public final RecurrencePickerState.MonthFrequency getMonthFrequency()
    {
        return monthFrequency;
    }

    public final Long getStartTimeInMillis()
    {
        return startTimeInMillis;
    }

    public final Integer getStartWeekday()
    {
        return startWeekday;
    }

    public final TimeZone getTimeZone()
    {
        return timeZone;
    }

    public final Long getUntilDateTimeMillis()
    {
        return untilDateTimeMillis;
    }

    public int hashCode()
    {
        return (((((((((((((frequency.hashCode() ^ 0xf4243) * 0xf4243 ^ untilDateTimeMillis.hashCode()) * 0xf4243 ^ count.hashCode()) * 0xf4243 ^ interval.hashCode()) * 0xf4243 ^ byDay.hashCode()) * 0xf4243 ^ byMonthDay.hashCode()) * 0xf4243 ^ end.hashCode()) * 0xf4243 ^ monthFrequency.hashCode()) * 0xf4243 ^ hasLastOption.hashCode()) * 0xf4243 ^ hasNthOption.hashCode()) * 0xf4243 ^ startTimeInMillis.hashCode()) * 0xf4243 ^ timeZone.hashCode()) * 0xf4243 ^ firstDayOfWeek.hashCode()) * 0xf4243 ^ startWeekday.hashCode();
    }

    public final RecurrencePickerState.Builder toBuilder()
    {
        class Builder extends RecurrencePickerState.Builder
        {

            private ImmutableSet byDay;
            private ImmutableSet byMonthDay;
            private Integer count;
            private RecurrencePickerState.End end;
            private Integer firstDayOfWeek;
            private RecurrencePickerState.Frequency frequency;
            private Boolean hasLastOption;
            private Boolean hasNthOption;
            private Integer interval;
            private RecurrencePickerState.MonthFrequency monthFrequency;
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

            public final RecurrencePickerState.Builder setByDay(ImmutableSet immutableset)
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

            public final RecurrencePickerState.Builder setByMonthDay(ImmutableSet immutableset)
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

            public final RecurrencePickerState.Builder setCount(Integer integer)
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

            public final RecurrencePickerState.Builder setEnd(RecurrencePickerState.End end1)
            {
                if (end1 == null)
                {
                    throw new NullPointerException("Null end");
                } else
                {
                    end = end1;
                    return this;
                }
            }

            public final RecurrencePickerState.Builder setFirstDayOfWeek(Integer integer)
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

            public final RecurrencePickerState.Builder setFrequency(RecurrencePickerState.Frequency frequency1)
            {
                if (frequency1 == null)
                {
                    throw new NullPointerException("Null frequency");
                } else
                {
                    frequency = frequency1;
                    return this;
                }
            }

            public final RecurrencePickerState.Builder setHasLastOption(Boolean boolean1)
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

            public final RecurrencePickerState.Builder setHasNthOption(Boolean boolean1)
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

            public final RecurrencePickerState.Builder setInterval(Integer integer)
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

            public final RecurrencePickerState.Builder setMonthFrequency(RecurrencePickerState.MonthFrequency monthfrequency)
            {
                if (monthfrequency == null)
                {
                    throw new NullPointerException("Null monthFrequency");
                } else
                {
                    monthFrequency = monthfrequency;
                    return this;
                }
            }

            public final RecurrencePickerState.Builder setStartTimeInMillis(Long long1)
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

            public final RecurrencePickerState.Builder setStartWeekday(Integer integer)
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

            public final RecurrencePickerState.Builder setTimeZone(TimeZone timezone)
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

            public final RecurrencePickerState.Builder setUntilDateTimeMillis(Long long1)
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

            public Builder()
            {
            }

            Builder(RecurrencePickerState recurrencepickerstate)
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

        return new Builder(this);
    }

    public String toString()
    {
        String s = String.valueOf(frequency);
        String s1 = String.valueOf(untilDateTimeMillis);
        String s2 = String.valueOf(count);
        String s3 = String.valueOf(interval);
        String s4 = String.valueOf(byDay);
        String s5 = String.valueOf(byMonthDay);
        String s6 = String.valueOf(end);
        String s7 = String.valueOf(monthFrequency);
        String s8 = String.valueOf(hasLastOption);
        String s9 = String.valueOf(hasNthOption);
        String s10 = String.valueOf(startTimeInMillis);
        String s11 = String.valueOf(timeZone);
        String s12 = String.valueOf(firstDayOfWeek);
        String s13 = String.valueOf(startWeekday);
        return (new StringBuilder(String.valueOf(s).length() + 212 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length() + String.valueOf(s8).length() + String.valueOf(s9).length() + String.valueOf(s10).length() + String.valueOf(s11).length() + String.valueOf(s12).length() + String.valueOf(s13).length())).append("RecurrencePickerState{frequency=").append(s).append(", untilDateTimeMillis=").append(s1).append(", count=").append(s2).append(", interval=").append(s3).append(", byDay=").append(s4).append(", byMonthDay=").append(s5).append(", end=").append(s6).append(", monthFrequency=").append(s7).append(", hasLastOption=").append(s8).append(", hasNthOption=").append(s9).append(", startTimeInMillis=").append(s10).append(", timeZone=").append(s11).append(", firstDayOfWeek=").append(s12).append(", startWeekday=").append(s13).append("}").toString();
    }
}
