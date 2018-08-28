// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timebox.task.TaskData;
import com.google.android.apps.calendar.timebox.task.TaskItem;
import com.google.android.apps.calendar.timebox.task.TasksApi;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.calendar.Utils;
import com.google.android.calendar.task.alternate.SimpleTaskDataLoader;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.common.collect.Collections2;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

class TaskSearchHandler
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/search/TaskSearchHandler);
    public final TimeBoxToTimelineAdapter adapter;
    public final TasksApi api;
    public final TimeZone timeZone;

    TaskSearchHandler(Context context)
    {
        timeZone = Utils.getTimeZone(context);
        ListenableFutureCache listenablefuturecache = SettingsCache.instance;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            api = new TasksApi(new SimpleTaskDataLoader(context, (ListenableFutureCache)listenablefuturecache), new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(timeZone));
            adapter = new TimeBoxToTimelineAdapter(context);
            return;
        }
    }

    static final boolean lambda$search$0$TaskSearchHandler(String s, TimeRangeEntry timerangeentry)
    {
        if (!(timerangeentry.getValue() instanceof TaskItem))
        {
            return false;
        } else
        {
            return ((TaskItem)timerangeentry.getValue()).getTaskData().getTitle().toLowerCase(Locale.getDefault()).contains(s);
        }
    }

    static final Collection lambda$search$1$TaskSearchHandler(String s, List list)
    {
        class .Lambda._cls3
            implements Predicate
        {

            private final String arg$1;

            public final boolean apply(Object obj)
            {
                return TaskSearchHandler.lambda$search$0$TaskSearchHandler(arg$1, (TimeRangeEntry)obj);
            }

            .Lambda._cls3(String s)
            {
                arg$1 = s;
            }
        }

        return Collections2.filter(list, new .Lambda._cls3(s));
    }

}
