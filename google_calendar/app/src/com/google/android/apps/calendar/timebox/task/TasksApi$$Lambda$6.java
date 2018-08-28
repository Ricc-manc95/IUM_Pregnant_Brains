// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskData

final class Builder
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        obj = (TaskData)obj;
        return new AutoValue_TimeRangeEntry(((TaskData) (obj)).getId(), (new Builder()).tTaskData(((TaskData) (obj))).ild(), ((TaskData) (obj)).getTimeRange());
    }


    private Builder()
    {
    }
}
