// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timebox.task.TaskSet;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            SwipeHandlerImpl

final class arg._cls5
    implements AsyncFunction
{

    private final SwipeHandlerImpl arg$1;
    private final TimeRangeEntry arg$2;
    private final TaskSet arg$3;
    private final String arg$4;
    private final Set arg$5;

    public final ListenableFuture apply(Object obj)
    {
        obj = arg$1;
        TimeRangeEntry timerangeentry = arg$2;
        TaskSet taskset = arg$3;
        String s = arg$4;
        Set set = arg$5;
        return ((SwipeHandlerImpl) (obj)).store.updateStore(new <init>(((SwipeHandlerImpl) (obj)), timerangeentry, taskset, s, set));
    }

    tore(SwipeHandlerImpl swipehandlerimpl, TimeRangeEntry timerangeentry, TaskSet taskset, String s, Set set)
    {
        arg$1 = swipehandlerimpl;
        arg$2 = timerangeentry;
        arg$3 = taskset;
        arg$4 = s;
        arg$5 = set;
    }
}
