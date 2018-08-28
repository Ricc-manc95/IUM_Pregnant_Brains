// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.content.Context;
import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.GoalItem;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.alerts.HabitsIntentServiceHelper;
import com.google.android.calendar.api.event.EventDescriptor;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            SwipeHandlerImpl

final class arg._cls3
    implements Consumer
{

    private final SwipeHandlerImpl arg$1;
    private final GoalItem arg$2;
    private final TimeRangeEntry arg$3;

    public final void accept(Object obj)
    {
        Object obj1 = arg$1;
        GoalItem goalitem = arg$2;
        TimeRangeEntry timerangeentry = arg$3;
        obj = (com.google.android.apps.calendar.timeline.alternate.store.ransaction)obj;
        ((SwipeHandlerImpl) (obj1)).context.startService(HabitsIntentServiceHelper.createCompleteIntent(((SwipeHandlerImpl) (obj1)).context, goalitem.getHabitDescriptor(), goalitem.getEventDescriptor().getKey(), true, "swipe_from_timeline"));
        ((com.google.android.apps.calendar.timeline.alternate.store.ransaction) (obj)).removeItem(timerangeentry);
        obj1 = goalitem.setGoalDone(true);
        ((com.google.android.apps.calendar.timeline.alternate.store.ransaction) (obj)).addItem(new AutoValue_TimeRangeEntry(timerangeentry.getKey(), obj1, timerangeentry.getRange()));
    }

    tore.StoreTransaction(SwipeHandlerImpl swipehandlerimpl, GoalItem goalitem, TimeRangeEntry timerangeentry)
    {
        arg$1 = swipehandlerimpl;
        arg$2 = goalitem;
        arg$3 = timerangeentry;
    }
}
