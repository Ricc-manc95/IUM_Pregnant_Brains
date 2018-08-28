// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;

import com.google.common.util.concurrent.AsyncFunction;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.common:
//            Trace

public final class NoOpTrace
    implements Trace
{

    public NoOpTrace()
    {
    }

    public final void begin()
    {
    }

    public final void end()
    {
    }

    public final AsyncFunction propagateAsyncFunction(AsyncFunction asyncfunction)
    {
        return asyncfunction;
    }

    public final Callable propagateCallable(Callable callable)
    {
        return callable;
    }

    public final Runnable propagateRunnable(Runnable runnable)
    {
        return runnable;
    }
}
