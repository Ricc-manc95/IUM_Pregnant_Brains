// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.content.Context;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.internal.ref.TaskRef;
import com.google.android.gms.reminders.model.RemindersBuffer;
import com.google.android.gms.reminders.model.Task;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.task:
//            TaskUtils

final class ArpRemindersBufferIterator
    implements Iterator
{

    private final int accountColor;
    private final String accountName;
    private int current;
    private final DateTimeService dateTimeService;
    private final RemindersBuffer remindersBuffer;
    private final int remindersBufferSize;
    private final long todayMillis;

    public ArpRemindersBufferIterator(Context context, RemindersBuffer remindersbuffer, String s)
    {
        current = 0;
        remindersBuffer = remindersbuffer;
        remindersbuffer = remindersBuffer;
        int i;
        long l;
        if (((AbstractDataBuffer) (remindersbuffer)).zzaKT == null)
        {
            i = 0;
        } else
        {
            i = ((AbstractDataBuffer) (remindersbuffer)).zzaKT.zzaNZ;
        }
        remindersBufferSize = i;
        accountName = s;
        accountColor = TaskUtils.getTaskCalendarColor(s);
        dateTimeService = new DateTimeService(context);
        context = dateTimeService;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        context = DateTimeService.toDateTimeImpl((new DateTimeImpl(l, ((DateTimeService) (context)).calendarTimeZone)).withTime(0, 0, 0)).time;
        context.writeFieldsToImpl();
        todayMillis = ((Time) (context)).impl.toMillis(false);
        filter();
    }

    private final void filter()
    {
        do
        {
            if (current >= remindersBufferSize)
            {
                break;
            }
            RemindersBuffer remindersbuffer = remindersBuffer;
            int i = current;
            if (TaskUtils.shouldTaskBeInCalendar((Task)new TaskRef(remindersbuffer.zzaKT, i)))
            {
                break;
            }
            current = current + 1;
        } while (true);
    }

    public final boolean hasNext()
    {
        return current < remindersBufferSize;
    }

    public final Object next()
    {
        Object obj = null;
        if (current < remindersBufferSize)
        {
            obj = remindersBuffer;
            int i = current;
            obj = new TimelineTask(TaskUtils.createTaskData((Task)new TaskRef(((RemindersBuffer) (obj)).zzaKT, i), accountName, accountColor, dateTimeService, todayMillis));
        }
        current = current + 1;
        filter();
        return obj;
    }

    public final void remove()
    {
        throw new UnsupportedOperationException();
    }
}
