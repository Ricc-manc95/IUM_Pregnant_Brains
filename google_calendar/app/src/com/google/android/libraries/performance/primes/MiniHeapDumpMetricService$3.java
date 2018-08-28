// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import java.util.concurrent.ScheduledFuture;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            MiniHeapDumpMetricService

final class this._cls0
    implements Foreground
{

    private final MiniHeapDumpMetricService this$0;

    public final void onAppToForeground(Activity activity)
    {
        activity = MiniHeapDumpMetricService.this;
        if (((MiniHeapDumpMetricService) (activity)).futureMemoryCollectionTask != null)
        {
            ((MiniHeapDumpMetricService) (activity)).futureMemoryCollectionTask.cancel(true);
            activity.futureMemoryCollectionTask = null;
        }
    }

    Foreground()
    {
        this$0 = MiniHeapDumpMetricService.this;
        super();
    }
}
