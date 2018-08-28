// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common.loader;

import android.content.Context;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.task.TaskConnection;

public final class TaskLoader extends AsyncTaskLoader
{

    private final String accountName;
    private final Context context;
    private final TaskConnection taskConnection;
    private final String taskId;

    public TaskLoader(Context context1, String s, String s1, TaskConnection taskconnection)
    {
        context = context1;
        accountName = s;
        taskId = s1;
        taskConnection = taskconnection;
    }

    protected final Object runInBackground(Object aobj[])
    {
        aobj = taskConnection.loadTaskSynchronous(context, accountName, taskId);
        if (aobj == null)
        {
            super.success = false;
            super.failureMessage = "Error loading task.";
        }
        return ((Object) (aobj));
    }
}
