// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.task.TaskData;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            CachedTaskDataLoader

final class arg._cls1
    implements Predicate
{

    private final TimeRange arg$1;

    public final boolean apply(Object obj)
    {
        return CachedTaskDataLoader.lambda$loadAsync$2$CachedTaskDataLoader(arg$1, (TaskData)obj);
    }

    (TimeRange timerange)
    {
        arg$1 = timerange;
    }
}
