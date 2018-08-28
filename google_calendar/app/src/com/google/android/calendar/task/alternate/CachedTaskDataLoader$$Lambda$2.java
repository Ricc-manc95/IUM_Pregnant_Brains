// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.common.base.Function;
import java.util.List;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            CachedTaskDataLoader

final class arg._cls1
    implements Function
{

    private final TimeRange arg$1;

    public final Object apply(Object obj)
    {
        return CachedTaskDataLoader.lambda$loadAsync$3$CachedTaskDataLoader(arg$1, (List)obj);
    }

    (TimeRange timerange)
    {
        arg$1 = timerange;
    }
}
