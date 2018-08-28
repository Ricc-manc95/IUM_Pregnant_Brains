// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Strings;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// Referenced classes of package com.google.common.util.concurrent:
//            GwtFuturesCatchingSpecialization, Uninterruptibles, ExecutionError, UncheckedExecutionException

public final class Futures extends GwtFuturesCatchingSpecialization
{

    public static Object getDone(Future future)
        throws ExecutionException
    {
        if (!future.isDone())
        {
            throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                future
            }));
        } else
        {
            return Uninterruptibles.getUninterruptibly(future);
        }
    }

    public static Object getUnchecked(Future future)
    {
        if (future == null)
        {
            throw new NullPointerException();
        }
        try
        {
            future = ((Future) (Uninterruptibles.getUninterruptibly(future)));
        }
        // Misplaced declaration of an exception variable
        catch (Future future)
        {
            future = future.getCause();
            if (future instanceof Error)
            {
                throw new ExecutionError((Error)future);
            } else
            {
                throw new UncheckedExecutionException(future);
            }
        }
        return future;
    }
}
