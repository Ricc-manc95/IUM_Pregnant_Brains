// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.timebox;

import com.google.android.apps.calendar.timebox.task.TasksApi;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemProvider;
import com.google.common.util.concurrent.FluentFuture;

public final class TaskItemProvider
    implements ItemProvider
{

    private final TasksApi api;

    public TaskItemProvider(TasksApi tasksapi)
    {
        api = tasksapi;
    }

    public final FluentFuture loadItems(int i, int j)
    {
        return api.getAsync(i, j, true);
    }
}
