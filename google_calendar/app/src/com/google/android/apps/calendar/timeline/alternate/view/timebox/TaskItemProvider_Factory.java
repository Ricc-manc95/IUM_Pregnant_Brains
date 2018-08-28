// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.timebox;

import com.google.android.apps.calendar.timebox.task.TasksApi;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.timebox:
//            TaskItemProvider

public final class TaskItemProvider_Factory
    implements Factory
{

    private final Provider apiProvider;

    public TaskItemProvider_Factory(Provider provider)
    {
        apiProvider = provider;
    }

    public final Object get()
    {
        return new TaskItemProvider((TasksApi)apiProvider.get());
    }
}
