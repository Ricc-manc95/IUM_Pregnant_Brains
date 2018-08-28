// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Stopwatch;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced classes of package io.grpc.internal:
//            Rescheduler

final class this._cls0
    implements Runnable
{

    private final Rescheduler this$0;

    public final void run()
    {
        if (!enabled)
        {
            wakeUp = null;
            return;
        }
        long l = stopwatch.elapsed(TimeUnit.NANOSECONDS);
        if (runAtNanos - l > 0L)
        {
            wakeUp = scheduler.schedule(new ule(Rescheduler.this), runAtNanos - l, TimeUnit.NANOSECONDS);
            return;
        } else
        {
            enabled = false;
            wakeUp = null;
            runnable.run();
            return;
        }
    }

    ()
    {
        this$0 = Rescheduler.this;
        super();
    }
}
