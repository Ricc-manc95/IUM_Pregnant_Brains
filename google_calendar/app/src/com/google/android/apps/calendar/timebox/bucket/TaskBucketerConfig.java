// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.bucket;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timebox.task.TaskItem;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timebox.bucket:
//            TaskTypeBucketer, TypeBucketer

public final class TaskBucketerConfig
    implements Bucketer.BucketerConfig
{

    private TaskTypeBucketer bucketer;

    public TaskBucketerConfig()
    {
        bucketer = new TaskTypeBucketer();
    }

    public final TypeBucketer getBucketer(TimeRangeEntry timerangeentry)
    {
        if (timerangeentry.getValue() instanceof TaskItem)
        {
            return bucketer;
        } else
        {
            return null;
        }
    }

    public final List getBucketers()
    {
        return Collections.singletonList(bucketer);
    }
}
