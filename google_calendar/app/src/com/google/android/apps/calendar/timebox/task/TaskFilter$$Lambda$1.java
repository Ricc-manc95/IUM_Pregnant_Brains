// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.common.base.Predicate;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskItem, TaskData

final class arg._cls1
    implements Predicate
{

    private final Set arg$1;

    public final boolean apply(Object obj)
    {
        Set set = arg$1;
        obj = (TaskItem)obj;
        return ((TaskItem) (obj)).getTaskData().getRecurrenceId() == null || !set.contains(((TaskItem) (obj)).getTaskData().getRecurrenceId());
    }

    (Set set)
    {
        arg$1 = set;
    }
}
