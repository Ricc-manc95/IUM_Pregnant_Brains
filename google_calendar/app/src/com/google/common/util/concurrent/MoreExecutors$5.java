// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture

final class thrownFromDelegate
    implements Executor
{

    public boolean thrownFromDelegate;
    private final Executor val$delegate;
    private final AbstractFuture val$future;

    public final void execute(final Runnable command)
    {
        class _cls1
            implements Runnable
        {

            private final MoreExecutors._cls5 this$0;
            private final Runnable val$command;

            public final void run()
            {
                thrownFromDelegate = false;
                command.run();
            }

            _cls1()
            {
                this$0 = MoreExecutors._cls5.this;
                command = runnable;
                super();
            }
        }

        val$delegate.execute(new _cls1());
_L1:
        return;
        command;
        if (thrownFromDelegate)
        {
            val$future.setException(command);
            return;
        }
          goto _L1
    }

    _cls1()
    {
        val$delegate = executor;
        val$future = abstractfuture;
        super();
        thrownFromDelegate = true;
    }
}
