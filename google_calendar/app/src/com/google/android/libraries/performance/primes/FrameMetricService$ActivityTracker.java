// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.FrameMetrics;
import android.view.Window;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            FrameMetricService, PrimesLog, WithAccountableName, NoPiiString

static final class measuring
    implements android.view.ner, ed, med
{

    private final ack callback;
    public Activity currentActivity;
    public Handler handler;
    public HandlerThread handlerThread;
    public boolean measuring;
    private final boolean monitorActivities;

    private final void attachToCurrentActivity()
    {
        if (currentActivity != null)
        {
            Window window = currentActivity.getWindow();
            if (handler == null)
            {
                handlerThread = new HandlerThread("Primes-Jank");
                handlerThread.start();
                handler = new Handler(handlerThread.getLooper());
            }
            window.addOnFrameMetricsAvailableListener(this, handler);
        }
    }

    final void detachFromCurrentActivity()
    {
        if (currentActivity == null)
        {
            break MISSING_BLOCK_LABEL_18;
        }
        currentActivity.getWindow().removeOnFrameMetricsAvailableListener(this);
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        PrimesLog.log(3, "FrameMetricService", runtimeexception, "remove frame metrics listener failed", new Object[0]);
        return;
    }

    public final void onActivityPaused(Activity activity)
    {
        Object obj = null;
        this;
        JVM INSTR monitorenter ;
        if (measuring)
        {
            detachFromCurrentActivity();
        }
        currentActivity = null;
        this;
        JVM INSTR monitorexit ;
        if (monitorActivities)
        {
            ack ack = callback;
            if (activity instanceof WithAccountableName)
            {
                activity = ((WithAccountableName)activity).getAccountableName();
                if (activity == null)
                {
                    activity = obj;
                } else
                {
                    activity = activity.toString();
                }
            } else
            {
                activity = activity.getClass().getName();
            }
            ack.activityPaused(activity);
        }
        return;
        activity;
        this;
        JVM INSTR monitorexit ;
        throw activity;
    }

    public final void onActivityResumed(Activity activity)
    {
        if (monitorActivities)
        {
            ack ack = callback;
            Object obj;
            if (activity instanceof WithAccountableName)
            {
                obj = ((WithAccountableName)activity).getAccountableName();
                if (obj == null)
                {
                    obj = null;
                } else
                {
                    obj = ((NoPiiString) (obj)).toString();
                }
            } else
            {
                obj = activity.getClass().getName();
            }
            ack.activityResumed(((String) (obj)));
        }
        this;
        JVM INSTR monitorenter ;
        currentActivity = activity;
        if (measuring)
        {
            attachToCurrentActivity();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        activity;
        this;
        JVM INSTR monitorexit ;
        throw activity;
    }

    public final void onFrameMetricsAvailable(Window window, FrameMetrics framemetrics, int i)
    {
        i = (int)((double)framemetrics.getMetric(8) / 1000000D);
        callback.frameRendered(i);
    }

    final void stopCollecting()
    {
        this;
        JVM INSTR monitorenter ;
        measuring = false;
        detachFromCurrentActivity();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ack(ack ack, boolean flag)
    {
        callback = ack;
        monitorActivities = flag;
        if (flag)
        {
            measuring = true;
        }
    }
}
