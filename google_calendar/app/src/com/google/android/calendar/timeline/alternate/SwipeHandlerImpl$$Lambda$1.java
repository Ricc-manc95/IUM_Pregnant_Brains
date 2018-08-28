// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timebox.task.TaskData;
import com.google.android.apps.calendar.timebox.task.TaskItem;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timely.interaction.SwipeTaskUtils;
import java.util.Collections;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            SwipeHandlerImpl

final class arg._cls3
    implements Consumer
{

    private final SwipeHandlerImpl arg$1;
    private final TimeRangeEntry arg$2;
    private final TaskItem arg$3;

    public final void accept(Object obj)
    {
        SwipeHandlerImpl swipehandlerimpl = arg$1;
        TimeRangeEntry timerangeentry = arg$2;
        TaskItem taskitem = arg$3;
        obj = (com.google.android.apps.calendar.timeline.alternate.store.ransaction)obj;
        ((com.google.android.apps.calendar.timeline.alternate.store.ransaction) (obj)).removeItem(timerangeentry);
        TaskItem taskitem1 = taskitem.setDone(true);
        ((com.google.android.apps.calendar.timeline.alternate.store.ransaction) (obj)).addItem(new AutoValue_TimeRangeEntry(timerangeentry.getKey(), taskitem1, timerangeentry.getRange()));
        ((com.google.android.apps.calendar.timeline.alternate.store.ransaction) (obj)).blockUpdates(SwipeTaskUtils.updateTaskDoneAsync(swipehandlerimpl.context, taskitem.getTaskData().getAccountName(), Collections.singleton(taskitem.getTaskData().getId())));
        swipehandlerimpl.logUserAction("done_swipe");
    }

    tore.StoreTransaction(SwipeHandlerImpl swipehandlerimpl, TimeRangeEntry timerangeentry, TaskItem taskitem)
    {
        arg$1 = swipehandlerimpl;
        arg$2 = timerangeentry;
        arg$3 = taskitem;
    }
}
