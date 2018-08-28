// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;


// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskImpl, AutoValue_TaskImpl, TaskData

final class taskData extends taskData
{

    private TaskData taskData;

    public final TaskImpl build()
    {
        String s = "";
        if (taskData == null)
        {
            s = String.valueOf("").concat(" taskData");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_TaskImpl(taskData);
        }
    }

    public final taskData setTaskData(TaskData taskdata)
    {
        if (taskdata == null)
        {
            throw new NullPointerException("Null taskData");
        } else
        {
            taskData = taskdata;
            return this;
        }
    }

    ()
    {
    }

    (TaskImpl taskimpl)
    {
        taskData = taskimpl.getTaskData();
    }
}
