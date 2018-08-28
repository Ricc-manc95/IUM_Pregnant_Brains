// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import com.google.personalization.assist.annotate.DayOfWeek;
import com.google.personalization.assist.annotate.api.TimeComponent;
import com.google.personalization.assist.annotate.api.TimeEndpoint;
import com.google.personalization.assist.annotate.api.TimeInterval;
import com.google.personalization.assist.annotate.api.TimeSchedule;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

final class TimeScheduleUtil
{

    private static boolean isDayOfWeekEndpoint(TimeEndpoint timeendpoint)
    {
        DayOfWeek dayofweek2 = DayOfWeek.forNumber(timeendpoint.day_);
        DayOfWeek dayofweek = dayofweek2;
        if (dayofweek2 == null)
        {
            dayofweek = DayOfWeek.SUNDAY;
        }
        if (dayofweek.value >= DayOfWeek.SUNDAY.value)
        {
            DayOfWeek dayofweek1 = DayOfWeek.forNumber(timeendpoint.day_);
            timeendpoint = dayofweek1;
            if (dayofweek1 == null)
            {
                timeendpoint = DayOfWeek.SUNDAY;
            }
            if (((DayOfWeek) (timeendpoint)).value <= DayOfWeek.NEXT_SUNDAY.value)
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDayOfWeekInterval(TimeInterval timeinterval)
    {
        if (!isStrictInterval(timeinterval))
        {
            return false;
        }
        Object obj;
        if (timeinterval.begin_ == null)
        {
            obj = TimeEndpoint.DEFAULT_INSTANCE;
        } else
        {
            obj = timeinterval.begin_;
        }
        if (timeinterval.end_ == null)
        {
            timeinterval = TimeEndpoint.DEFAULT_INSTANCE;
        } else
        {
            timeinterval = timeinterval.end_;
        }
        if (isDayOfWeekEndpoint(((TimeEndpoint) (obj))) && isDayOfWeekEndpoint(timeinterval))
        {
            DayOfWeek dayofweek = DayOfWeek.forNumber(((TimeEndpoint) (obj)).day_);
            obj = dayofweek;
            if (dayofweek == null)
            {
                obj = DayOfWeek.SUNDAY;
            }
            int i = ((DayOfWeek) (obj)).value;
            obj = DayOfWeek.forNumber(((TimeEndpoint) (timeinterval)).day_);
            timeinterval = ((TimeInterval) (obj));
            if (obj == null)
            {
                timeinterval = DayOfWeek.SUNDAY;
            }
            if (i < ((DayOfWeek) (timeinterval)).value)
            {
                return true;
            }
        }
        return false;
    }

    static boolean isInvertedWeeklyComponent(TimeComponent timecomponent)
    {
        return timecomponent.interval_.size() == 2 && isDayOfWeekInterval((TimeInterval)timecomponent.interval_.get(0)) && isTimeInterval((TimeInterval)timecomponent.interval_.get(1));
    }

    private static boolean isStrictInterval(TimeInterval timeinterval)
    {
        return (timeinterval.bitField0_ & 1) == 1 && (timeinterval.bitField0_ & 2) == 2;
    }

    private static boolean isTimeEndpoint(TimeEndpoint timeendpoint)
    {
        int i = timeendpoint.hour_;
        for (int j = timeendpoint.minute_; i == 24 && j == 0 || i >= 0 && i < 24 && j >= 0 && j < 60;)
        {
            return true;
        }

        return false;
    }

    private static boolean isTimeInterval(TimeInterval timeinterval)
    {
        if (!isStrictInterval(timeinterval))
        {
            return false;
        }
        TimeEndpoint timeendpoint;
        if (timeinterval.begin_ == null)
        {
            timeendpoint = TimeEndpoint.DEFAULT_INSTANCE;
        } else
        {
            timeendpoint = timeinterval.begin_;
        }
        if (timeinterval.end_ == null)
        {
            timeinterval = TimeEndpoint.DEFAULT_INSTANCE;
        } else
        {
            timeinterval = timeinterval.end_;
        }
        if (isTimeEndpoint(timeendpoint) && isTimeEndpoint(timeinterval))
        {
            int i = timeendpoint.hour_;
            int j = timeendpoint.minute_;
            i = (int)TimeUnit.HOURS.toSeconds(i);
            j = (int)TimeUnit.MINUTES.toSeconds(j);
            int l = ((TimeEndpoint) (timeinterval)).hour_;
            int k = ((TimeEndpoint) (timeinterval)).minute_;
            l = (int)TimeUnit.HOURS.toSeconds(l);
            if (j + i < (int)TimeUnit.MINUTES.toSeconds(k) + l)
            {
                return true;
            }
        }
        return false;
    }

    static boolean isWeeklyComponent(TimeComponent timecomponent)
    {
        if (timecomponent.interval_.isEmpty())
        {
            return false;
        }
        if (!isTimeInterval((TimeInterval)timecomponent.interval_.get(0)))
        {
            return false;
        }
        for (timecomponent = timecomponent.interval_.iterator(); timecomponent.hasNext();)
        {
            if (!isDayOfWeekInterval((TimeInterval)timecomponent.next()))
            {
                return false;
            }
        }

        return true;
    }

    static boolean isWeeklySchedule(TimeSchedule timeschedule)
    {
        for (Iterator iterator = timeschedule.component_.iterator(); iterator.hasNext();)
        {
            TimeComponent timecomponent = (TimeComponent)iterator.next();
            boolean flag = isInvertedWeeklyComponent(timecomponent);
            if (!isWeeklyComponent(timecomponent) && !flag)
            {
                return false;
            }
        }

        return !timeschedule.component_.isEmpty();
    }
}
