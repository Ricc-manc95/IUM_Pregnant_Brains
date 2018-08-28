// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.executors;

import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.calendar.executors:
//            ThrottlingExecutor

final class arg._cls3
    implements Runnable
{

    private final ThrottlingExecutor arg$1;
    private final int arg$2;
    private final Runnable arg$3;

    public final void run()
    {
        ThrottlingExecutor throttlingexecutor = arg$1;
        int i = arg$2;
        Runnable runnable = arg$3;
        if (i == throttlingexecutor.requestCount.get())
        {
            runnable.run();
        }
    }

    (ThrottlingExecutor throttlingexecutor, int i, Runnable runnable)
    {
        arg$1 = throttlingexecutor;
        arg$2 = i;
        arg$3 = runnable;
    }
}
