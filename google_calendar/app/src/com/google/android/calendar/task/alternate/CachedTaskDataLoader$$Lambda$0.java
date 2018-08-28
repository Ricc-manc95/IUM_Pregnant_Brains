// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            CachedTaskDataLoader, SimpleTaskDataLoader

final class arg._cls1
    implements Supplier
{

    private final CachedTaskDataLoader arg$1;

    public final Object get()
    {
        Object obj = arg$1;
        Object obj1 = (TimeZone)((CachedTaskDataLoader) (obj)).timeZone.get();
        int i;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        i = TimeBoxUtil.msToJulianDay(((TimeZone) (obj1)), l);
        obj1 = ((CachedTaskDataLoader) (obj)).simpleLoader;
        obj = ((SimpleTaskDataLoader) (obj1)).settingsCache.getValueAsync();
        if (obj instanceof FluentFuture)
        {
            obj = (FluentFuture)obj;
        } else
        {
            obj = new ForwardingFluentFuture(((com.google.common.util.concurrent.ListenableFuture) (obj)));
        }
        return (FluentFuture)AbstractTransformFuture.create(((com.google.common.util.concurrent.ListenableFuture) (obj)), new <init>(((SimpleTaskDataLoader) (obj1)), i - 365, i + 365), com.google.common.util.concurrent.STANCE);
    }

    (CachedTaskDataLoader cachedtaskdataloader)
    {
        arg$1 = cachedtaskdataloader;
    }
}
