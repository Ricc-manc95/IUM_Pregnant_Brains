// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import android.arch.lifecycle.FullLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.calendar.task.TaskDataFactory;
import java.util.List;

public final class TaskCacheInvalidator
    implements FullLifecycleObserver, com.google.android.calendar.task.TaskDataFactory.OnTasksChangedListener
{

    private final LifecycleOwner lifecycleOwner;
    private final ListenableFutureCache settingsCache;
    private Subscription settingsSubscription;
    public final ListenableFutureCache taskCache;
    private final TaskDataFactory taskDataFactory;

    public TaskCacheInvalidator(ListenableFutureCache listenablefuturecache, TaskDataFactory taskdatafactory, LifecycleOwner lifecycleowner, ListenableFutureCache listenablefuturecache1)
    {
        settingsCache = listenablefuturecache;
        taskDataFactory = taskdatafactory;
        lifecycleOwner = lifecycleowner;
        taskCache = listenablefuturecache1;
        lifecycleowner.getLifecycle().addObserver(this);
    }

    public final void onCreate(LifecycleOwner lifecycleowner)
    {
    }

    public final void onDestroy$51662RJ4E9NMIP1FC5P66Q1FDHKMCPB3F5HMOP9F9HKMCPB3F5HMOPAFETN6ASHR55B0____0()
    {
        lifecycleOwner.getLifecycle().removeObserver(this);
    }

    public final void onPause(LifecycleOwner lifecycleowner)
    {
    }

    public final void onResume(LifecycleOwner lifecycleowner)
    {
    }

    public final void onStart(LifecycleOwner lifecycleowner)
    {
        taskCache.start();
        class .Lambda._cls0
            implements Consumer
        {

            private final TaskCacheInvalidator arg$1;

            public final void accept(Object obj)
            {
                TaskCacheInvalidator taskcacheinvalidator = arg$1;
                obj = (ImmutableMap)obj;
                taskcacheinvalidator.taskCache.invalidate();
            }

            .Lambda._cls0()
            {
                arg$1 = TaskCacheInvalidator.this;
            }
        }

        settingsSubscription = settingsCache.subscribe(new .Lambda._cls0(), CalendarExecutor.BACKGROUND, false);
        taskDataFactory.onTasksChangedListeners.add(this);
    }

    public final void onStop(LifecycleOwner lifecycleowner)
    {
        taskCache.stop();
        settingsSubscription.cancel(false);
        settingsSubscription = null;
        taskDataFactory.onTasksChangedListeners.remove(this);
    }

    public final void onTasksChanged()
    {
        taskCache.invalidate();
    }
}
