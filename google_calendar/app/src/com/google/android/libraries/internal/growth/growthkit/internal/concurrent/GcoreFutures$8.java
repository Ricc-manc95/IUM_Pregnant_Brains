// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.android.libraries.gcoreclient.common.api.GcoreReleasable;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.Uninterruptibles;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

final class val.future
    implements Closeable
{

    private final SettableFuture val$future;

    public final void close()
        throws IOException
    {
        if (!val$future.isCancelled() && val$future.isDone())
        {
            try
            {
                Object obj = val$future;
                if (!((Future) (obj)).isDone())
                {
                    throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                        obj
                    }));
                }
                obj = Uninterruptibles.getUninterruptibly(((Future) (obj)));
                if (obj instanceof GcoreReleasable)
                {
                    ((GcoreReleasable)obj).release();
                    return;
                }
            }
            catch (ExecutionException executionexception) { }
        }
    }

    ()
    {
        val$future = settablefuture;
        super();
    }
}
