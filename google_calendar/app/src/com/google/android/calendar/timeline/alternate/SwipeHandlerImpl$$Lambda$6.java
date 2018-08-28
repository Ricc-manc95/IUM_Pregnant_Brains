// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timebox.task.TaskComparators;
import com.google.android.apps.calendar.timebox.task.TaskSet;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timely.interaction.SwipeTaskUtils;
import com.google.common.collect.ImmutableList;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            SwipeHandlerImpl

final class arg._cls5
    implements Consumer
{

    private final SwipeHandlerImpl arg$1;
    private final TimeRangeEntry arg$2;
    private final TaskSet arg$3;
    private final String arg$4;
    private final Set arg$5;

    public final void accept(Object obj)
    {
        SwipeHandlerImpl swipehandlerimpl = arg$1;
        TimeRangeEntry timerangeentry = arg$2;
        Object obj2 = arg$3;
        String s = arg$4;
        Set set = arg$5;
        obj = (com.google.android.apps.calendar.timeline.alternate.store.ransaction)obj;
        ((com.google.android.apps.calendar.timeline.alternate.store.ransaction) (obj)).removeItem(timerangeentry);
        Object obj1 = ((TaskSet) (obj2)).toBuilder();
        obj2 = ((TaskSet) (obj2)).getItems();
        com.google.android.apps.calendar.timebox.task.  = new com.google.android.apps.calendar.timebox.task.veItem(true);
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        if ( == null)
        {
            throw new NullPointerException();
        } else
        {
            obj1 = ((com.google.android.apps.calendar.timebox.task.veItem) (obj1)).veItem(ImmutableList.copyOf(new com.google.common.collect..Builder.setItems(((Iterable) (obj2)), ))).veItem(true).veItem();
            obj1 = ((TaskSet) (obj1)).toBuilder().veItem(ImmutableList.sortedCopyOf(TaskComparators.get(((TaskSet) (obj1)).isDone()), ((TaskSet) (obj1)).getItems())).veItem();
            ((com.google.android.apps.calendar.timeline.alternate.store.ransaction) (obj)).addItem(new AutoValue_TimeRangeEntry(timerangeentry.getKey(), obj1, timerangeentry.getRange()));
            ((com.google.android.apps.calendar.timeline.alternate.store.ransaction) (obj)).blockUpdates(SwipeTaskUtils.updateTaskDoneAsync(swipehandlerimpl.context, s, set));
            swipehandlerimpl.logUserAction("done_all_swipe");
            return;
        }
    }

    tore.StoreTransaction(SwipeHandlerImpl swipehandlerimpl, TimeRangeEntry timerangeentry, TaskSet taskset, String s, Set set)
    {
        arg$1 = swipehandlerimpl;
        arg$2 = timerangeentry;
        arg$3 = taskset;
        arg$4 = s;
        arg$5 = set;
    }
}
