// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.collect.ImmutableMap;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            TaskCacheInvalidator

final class arg._cls1
    implements Consumer
{

    private final TaskCacheInvalidator arg$1;

    public final void accept(Object obj)
    {
        TaskCacheInvalidator taskcacheinvalidator = arg$1;
        obj = (ImmutableMap)obj;
        taskcacheinvalidator.taskCache.invalidate();
    }

    (TaskCacheInvalidator taskcacheinvalidator)
    {
        arg$1 = taskcacheinvalidator;
    }
}
