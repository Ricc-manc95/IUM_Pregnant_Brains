// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc:
//            Context

final class ThreadLocalContextStorage extends Context.Storage
{

    private static final ThreadLocal localContext = new ThreadLocal();
    private static final Logger log = Logger.getLogger(io/grpc/ThreadLocalContextStorage.getName());

    ThreadLocalContextStorage()
    {
    }

    public final Context current()
    {
        return (Context)localContext.get();
    }

    public final void detach(Context context, Context context1)
    {
        if (current() != context)
        {
            log.logp(Level.SEVERE, "io.grpc.ThreadLocalContextStorage", "detach", "Context was not attached when detaching", (new Throwable()).fillInStackTrace());
        }
        doAttach(context1);
    }

    public final Context doAttach(Context context)
    {
        Context context1 = current();
        localContext.set(context);
        return context1;
    }

}
