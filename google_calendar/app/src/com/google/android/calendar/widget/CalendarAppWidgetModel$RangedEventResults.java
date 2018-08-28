// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineHoliday;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.timely.TimelineTaskType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

final class todayJulianDay
    implements com.google.android.calendar.timely.todayJulianDay
{

    public final SparseArray allDayBuckets = new SparseArray(14);
    public final List birthdays = new ArrayList(14);
    public boolean hadEventsToday;
    public boolean hasEventsToday;
    private final List holidays = new ArrayList(14);
    private final int maxJulianDay;
    private final long now;
    public final SparseArray timedBuckets = new SparseArray(14);
    private final String timezone;
    public final int todayJulianDay;

    static void sortBucketSet(SparseArray sparsearray, int i, int j, Comparator comparator)
    {
        for (; i <= j; i++)
        {
            List list = (List)sparsearray.get(i);
            if (list != null && list.size() > 1)
            {
                Collections.sort(list, comparator);
            }
        }

    }

    public final void addTimelineItem(TimelineItem timelineitem)
    {
        if ((timelineitem instanceof TimelineEvent) || (timelineitem instanceof TimelineTaskType) || (timelineitem instanceof TimelineBirthday)) goto _L2; else goto _L1
_L1:
        LogUtils.d("CalendarWidgetModel", "dropping:%s", new Object[] {
            timelineitem
        });
_L7:
        return;
_L2:
        if ((timelineitem instanceof TimelineTaskType) && ((TimelineTaskType)timelineitem).isDone())
        {
            LogUtils.d("CalendarWidgetModel", "dropping:%s", new Object[] {
                timelineitem
            });
            return;
        }
        Object obj;
        List list;
        int i;
        int j;
        if (timelineitem.getStartDay() <= todayJulianDay && timelineitem.getEndDay() >= todayJulianDay)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        hadEventsToday = hadEventsToday | i;
        if (TimelineItemUtil.isItemInPast(timelineitem, now, timezone)) goto _L4; else goto _L3
_L3:
        hasEventsToday = i | hasEventsToday;
        if (!(timelineitem instanceof TimelineBirthday)) goto _L6; else goto _L5
_L5:
        birthdays.add((TimelineBirthday)timelineitem);
_L8:
        i = Math.max(timelineitem.getStartDay(), todayJulianDay);
        j = Math.min(timelineitem.getEndDay(), maxJulianDay);
        while (i <= j) 
        {
            SparseArray sparsearray;
            boolean flag;
            if (timelineitem.isAllDay() || !TimelineItemUtil.isFirstDay(timelineitem, i) && TimelineItemUtil.getDurationHours(timelineitem) >= 24L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                sparsearray = allDayBuckets;
            } else
            {
                sparsearray = timedBuckets;
            }
            list = (List)sparsearray.get(i);
            obj = list;
            if (list == null)
            {
                obj = new ArrayList();
                sparsearray.put(i, obj);
            }
            ((List) (obj)).add(timelineitem);
            i++;
        }
_L4:
        if (true) goto _L7; else goto _L6
_L6:
        if (timelineitem instanceof TimelineHoliday)
        {
            holidays.add((TimelineHoliday)timelineitem);
        }
          goto _L8
    }

    public final String toString()
    {
        int k = todayJulianDay;
        Object obj = timedBuckets;
        Object obj1 = allDayBuckets;
        int j = todayJulianDay;
        int l = ((SparseArray) (obj1)).size();
        int i = j;
        if (l != 0)
        {
            i = Math.max(j, ((SparseArray) (obj1)).keyAt(l - 1));
        }
        l = ((SparseArray) (obj)).size();
        j = i;
        if (l != 0)
        {
            j = Math.max(i, ((SparseArray) (obj)).keyAt(l - 1));
        }
        obj = allDayBuckets.toString();
        obj1 = timedBuckets.toString();
        return (new StringBuilder(String.valueOf(obj).length() + 64 + String.valueOf(obj1).length())).append("from-to=").append(k).append("-").append(j).append("[all day events=").append(((String) (obj))).append("]\n[timed events=").append(((String) (obj1))).append("]").toString();
    }

    (String s)
    {
        hadEventsToday = false;
        hasEventsToday = false;
        timezone = s;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        now = l;
        s = new Time(s);
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        s.set(l);
        todayJulianDay = android.text.format.Time.getJulianDay(now, ((Time) (s)).gmtoff);
        maxJulianDay = (todayJulianDay + 14) - 1;
    }
}
