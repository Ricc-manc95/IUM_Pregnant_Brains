// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth.model;

import android.content.Context;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.Utils;
import com.google.android.calendar.task.ArpTaskLoader;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.calendar.task.TaskLoader;
import com.google.android.calendar.timely.EventRangedQueryHandler;
import com.google.android.calendar.timely.RangedData;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.widgetmonth.model:
//            MonthViewWidgetRangedQueryHandler

final class this._cls0 extends EventRangedQueryHandler
{

    private final MonthViewWidgetRangedQueryHandler this$0;

    protected final com.google.android.calendar.timely.entsLoader createStorage(int i)
    {
        return new s(i);
    }

    protected final void processResults(RangedData rangeddata, com.google.android.calendar.timely.entsLoader entsloader)
    {
        Object obj = (s)entsloader;
        if (entsloader == null)
        {
            obj = (s)createStorage(0);
        }
        rangeddata = (s)rangeddata;
        rangeddata.events = ((s) (obj)).results;
        entsloader = MonthViewWidgetRangedQueryHandler.this;
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        obj = TaskDataFactory.instance;
        obj = new ArpTaskLoader(((MonthViewWidgetRangedQueryHandler) (entsloader)).context, ((TaskDataFactory) (obj)).getTaskConnection());
        s s = new s();
        s s1 = new s(s, rangeddata);
        int i = Utils.getTodayJulianDay(((MonthViewWidgetRangedQueryHandler) (entsloader)).context);
        boolean flag;
        if (((s) (rangeddata)).startDay <= i && i <= ((startDay) (rangeddata)).endDay)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((TaskLoader) (obj)).loadTasks(s1, flag);
        rangeddata.tasks = s.results;
        obj = ((s.results) (rangeddata)).events.iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            Object obj1 = (TimelineItem)((Iterator) (obj)).next();
            if (TimelineItemUtil.isBirthday(((TimelineItem) (obj1))))
            {
                obj1 = (TimelineBirthday)obj1;
                ((TimelineBirthday) (obj1)).loadBirthdays(((MonthViewWidgetRangedQueryHandler) (entsloader)).context);
                ((TimelineBirthday) (obj1)).validate(((MonthViewWidgetRangedQueryHandler) (entsloader)).context);
            }
        } while (true);
        CalendarExecutor.MAIN.execute(new .Lambda._cls0(rangeddata));
    }

    public .Lambda._cls0(Context context, boolean flag)
    {
        this$0 = MonthViewWidgetRangedQueryHandler.this;
        super(context.getApplicationContext(), false);
        setHideDeclinedEvents(flag);
    }
}
