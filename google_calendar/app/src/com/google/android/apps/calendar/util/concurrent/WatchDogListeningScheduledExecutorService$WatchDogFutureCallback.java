// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.util.concurrent.FutureCallback;
import java.util.concurrent.CancellationException;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            UnhandledThrowableReference

final class reference
    implements FutureCallback
{

    private final UnhandledThrowableReference reference;

    public final void onFailure(Throwable throwable)
    {
        if (throwable instanceof CancellationException)
        {
            return;
        } else
        {
            reference.setThrowable(Thread.currentThread(), throwable);
            return;
        }
    }

    public final void onSuccess(Object obj)
    {
    }

    (UnhandledThrowableReference unhandledthrowablereference)
    {
        reference = unhandledthrowablereference;
    }
}
