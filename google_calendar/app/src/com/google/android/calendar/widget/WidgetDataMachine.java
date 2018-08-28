// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.task.ArpTaskLoader;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.calendar.task.TaskLoader;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.RangedData;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineItem;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.widget:
//            CalendarAppWidgetModel

final class WidgetDataMachine extends CalendarAppWidgetModel
    implements com.google.android.calendar.task.TaskLoader.TaskProcessor, WidgetEventLoader.EventsProcessor
{

    private final OnProcessCompleteListener listener;

    WidgetDataMachine(Context context, String s, OnProcessCompleteListener onprocesscompletelistener)
    {
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        super(context, s, l);
        listener = onprocesscompletelistener;
    }

    public final volatile com.google.android.calendar.timely.RangedData.EventResults getStorage()
    {
        return super.getStorage();
    }

    public final void processResults$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL4OBECTIM8H31EHGJMJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NN8QBDCLM7IBQIC5N6EPB48HGN8O948LR6ARJKA9IN6TBCEHPJMAAM0(RangedData rangeddata)
    {
        boolean flag;
        if (this == rangeddata || this != null && equals(rangeddata))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            LogUtils.e("CalendarWidget", "Unable to process %d. This does not match %s", new Object[] {
                Integer.valueOf(super.eventToken), rangeddata
            });
            return;
        }
        LogUtils.d("CalendarWidget", "Querying completed for %d ...", new Object[] {
            Integer.valueOf(super.eventToken)
        });
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        rangeddata = TaskDataFactory.instance;
        (new ArpTaskLoader(context, rangeddata.getTaskConnection())).loadTasks(new _cls1(), true);
        rangeddata = getStorage();
        if (rangeddata != null && ((CalendarAppWidgetModel.RangedEventResults) (rangeddata)).allDayBuckets.size() + ((CalendarAppWidgetModel.RangedEventResults) (rangeddata)).timedBuckets.size() < 7)
        {
            LogUtils.d("CalendarWidget", "... storage.size: too small", new Object[0]);
        }
        if (rangeddata != null)
        {
            Object obj = ((CalendarAppWidgetModel.RangedEventResults) (rangeddata)).birthdays;
            int i;
            int j;
            if (obj != null && ((List) (obj)).size() != 0)
            {
                obj = ((List) (obj)).iterator();
                while (((Iterator) (obj)).hasNext()) 
                {
                    TimelineBirthday timelinebirthday = (TimelineBirthday)((Iterator) (obj)).next();
                    timelinebirthday.loadBirthdays(context);
                    timelinebirthday.validate(context);
                }
            }
            i = super.todayJulianDay;
            j = super.maxJulianDay;
            CalendarAppWidgetModel.RangedEventResults.sortBucketSet(((CalendarAppWidgetModel.RangedEventResults) (rangeddata)).allDayBuckets, i, j, TimelineItem.AllDayComparator);
            CalendarAppWidgetModel.RangedEventResults.sortBucketSet(((CalendarAppWidgetModel.RangedEventResults) (rangeddata)).timedBuckets, i, j, TimelineItem.ItemComparator);
        }
        listener.onProcessComplete(this);
    }

    private class _cls1
        implements com.google.android.calendar.task.TaskLoader.TaskProcessor
    {

        private final WidgetDataMachine this$0;

        public final int getEndDay()
        {
            return maxJulianDay;
        }

        public final int getStartDay()
        {
            return monthStartJulianDay;
        }

        public final com.google.android.calendar.timely.RangedData.EventResults getStorage()
        {
            return CalendarAppWidgetModel.this.getStorage();
        }

        _cls1()
        {
            this$0 = WidgetDataMachine.this;
            super();
        }
    }


    private class OnProcessCompleteListener
    {

        public abstract void onProcessComplete(CalendarAppWidgetModel calendarappwidgetmodel);
    }

}
