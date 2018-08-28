// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.support.v4.app.Fragment;
import com.google.android.calendar.task.TaskConnection;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import com.google.common.base.Optional;
import com.google.common.collect.CollectPreconditions;
import com.google.common.util.concurrent.Futures;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderMarkDoneFlow

final class arg._cls3
    implements Callable
{

    private final ReminderMarkDoneFlow arg$1;
    private final String arg$2;
    private final Task arg$3;

    public final Object call()
    {
        Object obj1 = arg$1;
        String s = arg$2;
        Object obj = arg$3;
        obj1 = ((Fragment) (obj1)).getContext();
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        TaskConnection taskconnection = TaskDataFactory.instance.getTaskConnection();
        String as[];
        int i;
        boolean flag;
        if (!Boolean.TRUE.equals(((Task) (obj)).getArchived()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        as = new String[1];
        as[0] = ((Task) (obj)).getTaskId().getClientAssignedId();
        i = as.length;
        if (i < 3)
        {
            CollectPreconditions.checkNonnegative(i, "expectedSize");
            i++;
        } else
        if (i < 0x40000000)
        {
            i = (int)((float)i / 0.75F + 1.0F);
        } else
        {
            i = 0x7fffffff;
        }
        obj = new HashSet(i);
        Collections.addAll(((java.util.Collection) (obj)), as);
        return (Long)((Optional)Futures.getUnchecked(taskconnection.updateTasksDoneStatus(((android.content.Context) (obj1)), s, flag, ((java.util.Set) (obj))))).orNull();
    }

    (ReminderMarkDoneFlow remindermarkdoneflow, String s, Task task)
    {
        arg$1 = remindermarkdoneflow;
        arg$2 = s;
        arg$3 = task;
    }
}
