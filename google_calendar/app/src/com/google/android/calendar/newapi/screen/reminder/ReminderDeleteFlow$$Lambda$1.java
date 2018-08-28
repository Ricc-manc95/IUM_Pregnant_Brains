// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.support.v4.app.Fragment;
import com.google.android.calendar.task.TaskConnection;
import com.google.android.gms.reminders.model.Task;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderDeleteFlow

final class arg._cls5
    implements Callable
{

    private final ReminderDeleteFlow arg$1;
    private final int arg$2;
    private final TaskConnection arg$3;
    private final String arg$4;
    private final Task arg$5;

    public final Object call()
    {
        ReminderDeleteFlow reminderdeleteflow = arg$1;
        int i = arg$2;
        TaskConnection taskconnection = arg$3;
        String s = arg$4;
        Task task = arg$5;
        if (i == 0)
        {
            return Boolean.valueOf(taskconnection.deleteTask(reminderdeleteflow.getContext(), s, task));
        } else
        {
            return Boolean.valueOf(taskconnection.deleteRecurrence(reminderdeleteflow.getContext(), s, task, 1));
        }
    }

    (ReminderDeleteFlow reminderdeleteflow, int i, TaskConnection taskconnection, String s, Task task)
    {
        arg$1 = reminderdeleteflow;
        arg$2 = i;
        arg$3 = taskconnection;
        arg$4 = s;
        arg$5 = task;
    }
}
