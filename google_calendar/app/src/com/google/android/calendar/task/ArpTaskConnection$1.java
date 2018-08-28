// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.reminders.model.RemindersBuffer;
import com.google.common.util.concurrent.FutureCallback;

final class val.listener
    implements FutureCallback
{

    private final LoadListener val$listener;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e("ArpTaskConnection", "loadTasks failed", new Object[0]);
        val$listener.onTasksLoaded(null);
    }

    public final void onSuccess(Object obj)
    {
        obj = (RemindersBuffer)obj;
        val$listener.onTasksLoaded(((RemindersBuffer) (obj)));
    }

    LoadListener()
    {
        val$listener = loadlistener;
        super();
    }
}
