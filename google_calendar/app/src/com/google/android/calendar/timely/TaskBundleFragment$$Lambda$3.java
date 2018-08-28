// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.app.Fragment;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timebox.task.TaskItem;
import com.google.android.apps.calendar.timebox.task.TaskSet;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarDay;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarWeek;
import com.google.android.apps.calendar.timeline.alternate.store.VersionedItem;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TaskBundleFragment

final class arg._cls1
    implements Consumer
{

    private final TaskBundleFragment arg$1;

    public final void accept(Object obj)
    {
        TaskBundleFragment taskbundlefragment;
        taskbundlefragment = arg$1;
        obj = ((Collection)obj).iterator();
_L2:
        ImmutableList immutablelist;
        int i;
        int j;
        if (!((Iterator) (obj)).hasNext())
        {
            break MISSING_BLOCK_LABEL_219;
        }
        immutablelist = (ImmutableList)((CalendarWeek)((Iterator) (obj)).next()).getDays();
        j = immutablelist.size();
        i = 0;
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
_L4:
        if (i >= j) goto _L2; else goto _L1
_L1:
        Object obj1;
        obj1 = immutablelist.get(i);
        i++;
        obj1 = (CalendarDay)obj1;
        if (((CalendarDay) (obj1)).getJulianDate() != taskbundlefragment.julianDay) goto _L4; else goto _L3
_L3:
        obj1 = ((CalendarDay) (obj1)).getItems();
        obj = new TimeBoxToTimelineAdapter(taskbundlefragment.getContext());
        ArrayList arraylist = new ArrayList();
        obj1 = (UnmodifiableIterator)((ImmutableCollection) (obj1)).iterator();
        do
        {
            if (!((Iterator) (obj1)).hasNext())
            {
                break;
            }
            VersionedItem versioneditem = (VersionedItem)((Iterator) (obj1)).next();
            if ((((TimeRangeEntry)versioneditem.getItem()).getValue() instanceof TaskItem) || (((TimeRangeEntry)versioneditem.getItem()).getValue() instanceof TaskSet))
            {
                arraylist.add(((TimeBoxToTimelineAdapter) (obj)).createTimelineItem((TimeRangeEntry)versioneditem.getItem()));
            }
        } while (true);
        taskbundlefragment.updateBundle(ImmutableList.copyOf(arraylist), true);
    }

    nedItem(TaskBundleFragment taskbundlefragment)
    {
        arg$1 = taskbundlefragment;
    }
}
