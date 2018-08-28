// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.content.Context;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.newapi.segment.recurrence.ReminderRecurrenceConverter;
import com.google.android.calendar.task.RecurrenceConverter;
import com.google.android.calendar.task.TaskConnection;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.calendar.task.TaskUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import com.google.android.gms.reminders.model.zzab;
import com.google.common.base.Platform;
import java.util.List;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderSaveFlow

final class arg._cls3
    implements Callable
{

    private final ReminderSaveFlow arg$1;
    private final Context arg$2;
    private final int arg$3;

    public final Object call()
    {
        Object obj1 = arg$1;
        Context context = arg$2;
        int i = arg$3;
        Object obj = ((ReminderSaveFlow) (obj1)).dateTimeService;
        String s = ((ReminderSaveFlow) (obj1)).accountName;
        Task task = ((ReminderSaveFlow) (obj1)).original;
        Object obj2 = ((ReminderSaveFlow) (obj1)).task;
        obj1 = new com.google.android.gms.reminders.model.w.task(((Task) (obj2)));
        boolean flag;
        long l;
        long l1;
        boolean flag1;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        if (((Task) (obj2)).getDueDateMillis() != null)
        {
            l1 = ((Task) (obj2)).getDueDateMillis().longValue();
        } else
        {
            com.google.android.gms.reminders.model.DateTime datetime = ((Task) (obj2)).getDueDate();
            l1 = TaskUtils.dateTimeToMillis(Utils.getTimeZone(context), datetime);
        }
        if (l1 > l)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || TaskUtils.isRecurring(((Task) (obj2))))
        {
            obj1.task = Boolean.valueOf(false);
            obj1.task = Boolean.valueOf(true);
            obj1.task = Boolean.valueOf(false);
            obj1.task = Long.valueOf(l);
        } else
        {
            obj1.task = Boolean.valueOf(false);
        }
        if (((Task) (obj2)).getRecurrenceInfo() != null)
        {
            com.google.android.gms.reminders.model.Recurrence recurrence = ((Task) (obj2)).getRecurrenceInfo().getRecurrence();
            com.google.android.gms.reminders.model.DateTime datetime1 = ((Task) (obj2)).getDueDate();
            obj = RecurrenceConverter.fromRfcRecurrenceString(((RecurRulePart)ReminderRecurrenceConverter.toApiRecurrence(recurrence).rrules.get(0)).toRfc5545String(), datetime1, ((com.google.android.calendar.time.DateTimeService) (obj)));
            obj2 = new com.google.android.gms.reminders.model.t>(((Task) (obj2)).getRecurrenceInfo());
            if (obj != null)
            {
                obj = (com.google.android.gms.reminders.model.Recurrence)((com.google.android.gms.reminders.model.Recurrence) (obj)).freeze();
            } else
            {
                obj = null;
            }
            obj2.U = ((com.google.android.gms.reminders.model.Recurrence) (obj));
            obj = new zzab(((com.google.android.gms.reminders.model.U) (obj2)).U, ((com.google.android.gms.reminders.model.U) (obj2)).V, ((com.google.android.gms.reminders.model.V) (obj2)).W, ((com.google.android.gms.reminders.model.W) (obj2)).X, true);
            if (obj != null)
            {
                obj = (RecurrenceInfo)((RecurrenceInfo) (obj)).freeze();
            } else
            {
                obj = null;
            }
            obj1.X = ((RecurrenceInfo) (obj));
        }
        obj = ((com.google.android.gms.reminders.model.X) (obj1)).X();
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        obj1 = TaskDataFactory.instance.getTaskConnection();
        if (((Task) (obj)).getTaskId() == null || Platform.stringIsNullOrEmpty(((Task) (obj)).getTaskId().getClientAssignedId()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            flag1 = ((TaskConnection) (obj1)).createTask(context, s, ((Task) (obj)));
        } else
        if (i == 0)
        {
            flag1 = ((TaskConnection) (obj1)).updateTask(context, s, task, ((Task) (obj)));
        } else
        {
            flag1 = ((TaskConnection) (obj1)).updateRecurrence(context, s, task, ((Task) (obj)), 1);
        }
        return Boolean.valueOf(flag1);
    }

    ter(ReminderSaveFlow remindersaveflow, Context context, int i)
    {
        arg$1 = remindersaveflow;
        arg$2 = context;
        arg$3 = i;
    }
}
