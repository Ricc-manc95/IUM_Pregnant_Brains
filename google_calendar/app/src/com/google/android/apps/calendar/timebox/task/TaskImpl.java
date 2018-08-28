// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;


// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskItem, TaskData

public abstract class TaskImpl
    implements TaskItem
{

    public TaskImpl()
    {
    }

    public final com.google.android.apps.calendar.timebox.Item.SortType getSortType()
    {
        if (getTaskData().isDone())
        {
            return com.google.android.apps.calendar.timebox.Item.SortType.DONE_REMINDER;
        } else
        {
            return com.google.android.apps.calendar.timebox.Item.SortType.NOT_DONE_REMINDER;
        }
    }

    public abstract TaskData getTaskData();

    public final TaskItem setDone(boolean flag)
    {
        return toBuilder().setTaskData(getTaskData().toBuilder().setDone(flag).build()).build();
    }

    public abstract Builder toBuilder();

    private class Builder
    {

        public abstract TaskImpl build();

        public abstract Builder setTaskData(TaskData taskdata);

        public Builder()
        {
        }
    }

}
