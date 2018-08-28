// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import com.android.calendarcommon2.LogUtils;
import com.google.personalization.assist.annotate.DayOfWeek;
import com.google.personalization.assist.annotate.api.TimeComponent;
import com.google.personalization.assist.annotate.api.TimeEndpoint;
import com.google.personalization.assist.annotate.api.TimeInterval;
import com.google.personalization.assist.annotate.api.TimeSchedule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TimeScheduleUtil

class ParsedOpeningHours
{
    static final class Interval
        implements Comparable
    {

        public int end;
        public int start;

        public final int compareTo(Object obj)
        {
            obj = (Interval)obj;
            if (start != ((Interval) (obj)).start)
            {
                return start - ((Interval) (obj)).start;
            } else
            {
                return end - ((Interval) (obj)).end;
            }
        }

        public final boolean equals(Object obj)
        {
            if (obj != this)
            {
                if (obj == null || getClass() != obj.getClass())
                {
                    return false;
                }
                obj = (Interval)obj;
                if (start != ((Interval) (obj)).start || end != ((Interval) (obj)).end)
                {
                    return false;
                }
            }
            return true;
        }

        public Interval(int i, int j)
        {
            start = i;
            end = j;
        }
    }

    static final class NextTime
    {

        public final int duration;
        public final boolean isOpen;
        public final int weekSecond;

        public final boolean equals(Object obj)
        {
            if (obj != this)
            {
                if (obj == null || getClass() != obj.getClass())
                {
                    return false;
                }
                obj = (NextTime)obj;
                if (isOpen != ((NextTime) (obj)).isOpen || duration != ((NextTime) (obj)).duration || weekSecond != ((NextTime) (obj)).weekSecond)
                {
                    return false;
                }
            }
            return true;
        }

        public final int hashCode()
        {
            return Arrays.hashCode(new Object[] {
                Boolean.valueOf(isOpen), Integer.valueOf(duration), Integer.valueOf(weekSecond)
            });
        }

        public NextTime(boolean flag, int i, int j)
        {
            isOpen = flag;
            duration = i;
            weekSecond = j;
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/task/assist/ParsedOpeningHours);
    private final List intervals;
    private final TimeSchedule schedule;

    public ParsedOpeningHours(TimeSchedule timeschedule)
    {
        intervals = new ArrayList();
        if (timeschedule == null)
        {
            throw new NullPointerException();
        }
        schedule = (TimeSchedule)timeschedule;
        if (TimeScheduleUtil.isWeeklySchedule(timeschedule)) goto _L2; else goto _L1
_L1:
        LogUtils.i(TAG, "Schedule in unsupported format.", new Object[0]);
_L8:
        return;
_L2:
        if (timeschedule.component_.isEmpty())
        {
            LogUtils.i(TAG, "Empty schedule.", new Object[0]);
            return;
        }
        timeschedule = timeschedule.component_.iterator();
_L4:
        TimeComponent timecomponent;
        int j;
        if (!timeschedule.hasNext())
        {
            break MISSING_BLOCK_LABEL_272;
        }
        timecomponent = (TimeComponent)timeschedule.next();
        j = 0;
_L5:
        if (j >= 7) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (!TimeScheduleUtil.isWeeklyComponent(timecomponent))
        {
            flag = false;
        } else
        {
label0:
            {
                Iterator iterator = timecomponent.interval_.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break label0;
                    }
                } while (isIntervalApplicableOnDay((TimeInterval)iterator.next(), j));
                flag = false;
            }
        }
_L6:
        if (flag)
        {
            addInterval((TimeInterval)timecomponent.interval_.get(0), j);
        } else
        {
            boolean flag2;
            if (!TimeScheduleUtil.isInvertedWeeklyComponent(timecomponent))
            {
                flag2 = false;
            } else
            {
                flag2 = isIntervalApplicableOnDay((TimeInterval)timecomponent.interval_.get(0), j);
            }
            if (flag2)
            {
                addInterval((TimeInterval)timecomponent.interval_.get(1), j);
            }
        }
        j++;
          goto _L5
          goto _L4
        flag = true;
          goto _L6
        Collections.sort(intervals);
        timeschedule = intervals;
        int i = 1;
        while (i < timeschedule.size()) 
        {
            Interval interval = (Interval)timeschedule.get(i - 1);
            Interval interval1 = (Interval)timeschedule.get(i);
            boolean flag1;
            if (interval.end >= interval1.start && interval1.end >= interval.start)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                interval1.start = interval.start;
                timeschedule.remove(i - 1);
            } else
            {
                i++;
            }
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    private final void addInterval(TimeInterval timeinterval, int i)
    {
        i = (int)TimeUnit.DAYS.toSeconds((i + 6) % 7);
        TimeEndpoint timeendpoint;
        int j;
        int k;
        int l;
        int i1;
        if (timeinterval.begin_ == null)
        {
            timeendpoint = TimeEndpoint.DEFAULT_INSTANCE;
        } else
        {
            timeendpoint = timeinterval.begin_;
        }
        j = timeendpoint.hour_;
        k = timeendpoint.minute_;
        j = (int)TimeUnit.HOURS.toSeconds(j);
        k = (int)TimeUnit.MINUTES.toSeconds(k);
        if (timeinterval.end_ == null)
        {
            timeinterval = TimeEndpoint.DEFAULT_INSTANCE;
        } else
        {
            timeinterval = timeinterval.end_;
        }
        i1 = ((TimeEndpoint) (timeinterval)).hour_;
        l = ((TimeEndpoint) (timeinterval)).minute_;
        i1 = (int)TimeUnit.HOURS.toSeconds(i1);
        l = (int)TimeUnit.MINUTES.toSeconds(l);
        intervals.add(new Interval(i + (k + j), l + i1 + i));
    }

    private static NextTime createNextTime(boolean flag, int i, int j)
    {
        int k;
        if (j > i)
        {
            i = j - i;
        } else
        {
            i = (j - i) + (int)TimeUnit.DAYS.toSeconds(7L);
        }
        k = j;
        if ((long)j == TimeUnit.DAYS.toSeconds(7L))
        {
            k = 0;
        }
        return new NextTime(flag, i, k);
    }

    private static boolean isIntervalApplicableOnDay(TimeInterval timeinterval, int i)
    {
        Object obj;
        DayOfWeek dayofweek;
        int j;
        int k;
        if (timeinterval.begin_ == null)
        {
            obj = TimeEndpoint.DEFAULT_INSTANCE;
        } else
        {
            obj = timeinterval.begin_;
        }
        dayofweek = DayOfWeek.forNumber(((TimeEndpoint) (obj)).day_);
        obj = dayofweek;
        if (dayofweek == null)
        {
            obj = DayOfWeek.SUNDAY;
        }
        j = ((DayOfWeek) (obj)).value;
        if (timeinterval.end_ == null)
        {
            timeinterval = TimeEndpoint.DEFAULT_INSTANCE;
        } else
        {
            timeinterval = timeinterval.end_;
        }
        obj = DayOfWeek.forNumber(((TimeEndpoint) (timeinterval)).day_);
        timeinterval = ((TimeInterval) (obj));
        if (obj == null)
        {
            timeinterval = DayOfWeek.SUNDAY;
        }
        k = ((DayOfWeek) (timeinterval)).value;
        if (j <= i && i < k)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return i == 1;
    }

    final NextTime getNextTime(long l)
    {
        Interval interval;
        int j;
        if (!isValidSchedule())
        {
            return null;
        }
        j = getSecondOfWeek(l);
        Iterator iterator = intervals.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_109;
            }
            interval = (Interval)iterator.next();
        } while (j >= interval.end);
_L1:
        Interval interval1;
        interval1 = (Interval)intervals.get(0);
        boolean flag;
        if (interval.start <= j && j < interval.end)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return createNextTime(false, j, interval.start);
        }
        break MISSING_BLOCK_LABEL_132;
        interval = (Interval)intervals.get(0);
          goto _L1
        if ((long)interval.end == TimeUnit.DAYS.toSeconds(7L) && interval1.start == 0)
        {
            int i;
            if (interval == interval1)
            {
                i = j;
            } else
            {
                i = interval1.end;
            }
            return createNextTime(true, j, i);
        } else
        {
            return createNextTime(true, j, interval.end);
        }
    }

    final int getSecondOfWeek(long l)
    {
        long l1 = TimeZone.getDefault().getOffset(l);
        long l2 = TimeUnit.MINUTES.toMillis(schedule.timeZoneOffsetMinutes_);
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        gregoriancalendar.setTimeInMillis(l);
        gregoriancalendar.set(11, 0);
        gregoriancalendar.set(12, 0);
        gregoriancalendar.set(13, 0);
        gregoriancalendar.set(14, 0);
        gregoriancalendar.set(7, 2);
        long l3 = gregoriancalendar.getTimeInMillis();
        return (int)(TimeUnit.MILLISECONDS.toSeconds(l - ((l1 - l2) + l3)) % TimeUnit.DAYS.toSeconds(7L));
    }

    public final boolean isValidSchedule()
    {
        return !intervals.isEmpty() && TimeScheduleUtil.isWeeklySchedule(schedule) && !schedule.component_.isEmpty();
    }

}
