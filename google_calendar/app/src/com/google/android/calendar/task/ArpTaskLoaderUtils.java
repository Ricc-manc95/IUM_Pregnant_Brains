// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineTask;

// Referenced classes of package com.google.android.calendar.task:
//            TaskConnection, TaskUtils

public final class ArpTaskLoaderUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/task/ArpTaskLoaderUtils);

    ArpTaskLoaderUtils()
    {
    }

    public static TimelineItem loadTimelineItem(Context context, TaskConnection taskconnection, String s, String s1)
    {
        taskconnection = taskconnection.loadTaskSynchronous(context, s, s1);
        if (taskconnection == null)
        {
            LogUtils.e(TAG, "Unable to find task %s", new Object[] {
                s1
            });
            return null;
        }
        context = new DateTimeService(context);
        int i = TaskUtils.getTaskCalendarColor(s);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        s1 = DateTimeService.toDateTimeImpl((new DateTimeImpl(l, ((DateTimeService) (context)).calendarTimeZone)).withTime(0, 0, 0)).time;
        s1.writeFieldsToImpl();
        return new TimelineTask(TaskUtils.createTaskData(taskconnection, s, i, context, ((Time) (s1)).impl.toMillis(false)));
    }

}
