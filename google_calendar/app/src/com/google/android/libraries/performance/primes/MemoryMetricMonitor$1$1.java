// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            MemoryMetricMonitor

final class val.activityName
    implements Runnable
{

    private final val.activityName this$1;
    private final String val$activityName;

    public final void run()
    {
        callback.onEvent(4, val$activityName);
    }

    back()
    {
        this$1 = final_back;
        val$activityName = String.this;
        super();
    }
}
