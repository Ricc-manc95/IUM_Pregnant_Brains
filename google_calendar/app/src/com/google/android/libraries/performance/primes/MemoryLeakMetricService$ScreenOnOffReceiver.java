// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            MemoryLeakMetricService, Supplier

final class this._cls0 extends BroadcastReceiver
{

    public final MemoryLeakMetricService this$0;

    public final void onReceive(final Context context, Intent intent)
    {
        if ("android.intent.action.SCREEN_ON".equals(intent.getAction()))
        {
            if (dumpScheduled.get())
            {
                cancelDumpTaskIfAny();
            }
            return;
        } else
        {
            cancelDumpTaskIfAny();
            class _cls1
                implements Runnable
            {

                private final MemoryLeakMetricService.ScreenOnOffReceiver this$1;
                private final Context val$context;

                public final void run()
                {
                    if (dumpScheduled.compareAndSet(true, false))
                    {
                        context.unregisterReceiver(MemoryLeakMetricService.ScreenOnOffReceiver.this);
                        lastSent.set(SystemClock.elapsedRealtime());
                        Object obj1 = leakWatcher;
                        Object obj = PrimesHprofFile.getHprofFile(context);
                        if (((LeakWatcher) (obj1)).leakWatcherThread != null)
                        {
                            obj1 = ((LeakWatcher) (obj1)).leakWatcherThread;
                            if (((LeakWatcherThread) (obj1)).queueForDump.next != null)
                            {
                                if (obj == null)
                                {
                                    throw new NullPointerException();
                                }
                                obj1.hprofFile = (File)obj;
                                ((LeakWatcherThread) (obj1)).interrupt();
                                obj = "Schedule for heap dump";
                                Object aobj[] = new Object[0];
                                if (Log.isLoggable("LeakWatcherThread", 3))
                                {
                                    if (aobj.length != 0)
                                    {
                                        obj = String.format(Locale.US, "Schedule for heap dump", aobj);
                                    }
                                    Log.println(3, "LeakWatcherThread", ((String) (obj)));
                                }
                            } else
                            {
                                String s = "Skip heap dump. No leak suspects found.";
                                Object aobj1[] = new Object[0];
                                if (Log.isLoggable("LeakWatcherThread", 3))
                                {
                                    if (aobj1.length != 0)
                                    {
                                        s = String.format(Locale.US, "Skip heap dump. No leak suspects found.", aobj1);
                                    }
                                    Log.println(3, "LeakWatcherThread", s);
                                    return;
                                }
                            }
                        }
                    }
                }

            _cls1()
            {
                this$1 = MemoryLeakMetricService.ScreenOnOffReceiver.this;
                context = context1;
                super();
            }
            }

            dumpFutureTask = ((ScheduledExecutorService)executorServiceSupplier.get()).schedule(new _cls1(), 5L, TimeUnit.SECONDS);
            return;
        }
    }

    _cls1()
    {
        this$0 = MemoryLeakMetricService.this;
        super();
    }
}
