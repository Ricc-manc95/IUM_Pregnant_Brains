// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.content.Context;
import com.google.android.gms.reminders.model.RemindersBuffer;
import com.google.android.gms.reminders.model.Task;
import com.google.common.util.concurrent.FluentFuture;
import java.util.Set;

public interface TaskConnection
{

    public abstract boolean createTask(Context context, String s, Task task);

    public abstract boolean deleteRecurrence(Context context, String s, Task task, int i);

    public abstract boolean deleteTask(Context context, String s, Task task);

    public abstract Task loadTaskSynchronous(Context context, String s, String s1);

    public abstract void loadTasks(Context context, String s, long l, long l1, TasksLoadListener tasksloadlistener);

    public abstract FluentFuture loadTasksAsync(Context context, String s, long l, long l1);

    public abstract RemindersBuffer loadTasksSynchronous(Context context, String s, long l, long l1);

    public abstract boolean updateRecurrence(Context context, String s, Task task, Task task1, int i);

    public abstract boolean updateTask(Context context, String s, Task task, Task task1);

    public abstract FluentFuture updateTasksDoneStatus(Context context, String s, boolean flag, Set set);
}
