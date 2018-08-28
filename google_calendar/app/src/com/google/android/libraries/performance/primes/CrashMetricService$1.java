// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.text.TextUtils;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            NoPiiString, CrashMetricService, AbstractMetricService, Supplier

final class this._cls0
    implements tivityTracker
{

    public final CrashMetricService this$0;

    public final void onActivityStarted(Activity activity)
    {
        CrashMetricService crashmetricservice = CrashMetricService.this;
        Object obj = activity.getClass();
        if (!TextUtils.isEmpty(null))
        {
            activity = String.valueOf(null);
            obj = String.valueOf(((Class) (obj)).getSimpleName());
            if (((String) (obj)).length() != 0)
            {
                activity = activity.concat(((String) (obj)));
            } else
            {
                activity = new String(activity);
            }
            activity = new NoPiiString(activity);
        } else
        {
            activity = new NoPiiString(((Class) (obj)).getSimpleName());
        }
        crashmetricservice.setActiveComponentName(activity);
    }

    public final void onAppToBackground(Activity activity)
    {
        setActiveComponentName(null);
        class _cls1
            implements Runnable
        {

            private final CrashMetricService._cls1 this$1;

            public final void run()
            {
                CrashMetricService crashmetricservice = this$0;
                if (crashmetricservice.deferPrimesStats.getAndSet(false))
                {
                    crashmetricservice.recordStartupEvent(2, crashmetricservice.deferredPrevCrash);
                    crashmetricservice.recordStartupEvent(3, null);
                }
            }

            _cls1()
            {
                this$1 = CrashMetricService._cls1.this;
                super();
            }
        }

        if (deferPrimesStats.get())
        {
            ((ScheduledExecutorService)executorServiceSupplier.get()).submit(new _cls1());
        }
    }

    _cls1()
    {
        this$0 = CrashMetricService.this;
        super();
    }
}
