// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.libraries.performance.primes.leak.GarbageReference;
import com.google.android.libraries.performance.primes.leak.LeakWatcher;
import com.google.android.libraries.performance.primes.leak.LeakWatcherThread;
import java.io.File;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            MemoryLeakMetricService, PrimesHprofFile

final class val.context
    implements Runnable
{

    private final val.context this$1;
    private final Context val$context;

    public final void run()
    {
        if (dumpScheduled.compareAndSet(true, false))
        {
            val$context.unregisterReceiver(this._cls1.this);
            lastSent.set(SystemClock.elapsedRealtime());
            Object obj1 = leakWatcher;
            Object obj = PrimesHprofFile.getHprofFile(val$context);
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

    ()
    {
        this$1 = final_;
        val$context = Context.this;
        super();
    }
}
