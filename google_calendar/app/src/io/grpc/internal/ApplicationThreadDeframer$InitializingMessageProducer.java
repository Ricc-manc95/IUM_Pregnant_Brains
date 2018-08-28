// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.InputStream;
import java.util.Queue;

// Referenced classes of package io.grpc.internal:
//            ApplicationThreadDeframer

final class runnable
    implements runnable
{

    private boolean initialized;
    private final Runnable runnable;
    private final ApplicationThreadDeframer this$0;

    public final InputStream next()
    {
        if (!initialized)
        {
            runnable.run();
            initialized = true;
        }
        return (InputStream)messageReadQueue.poll();
    }

    (Runnable runnable1)
    {
        this$0 = ApplicationThreadDeframer.this;
        super();
        initialized = false;
        runnable = runnable1;
    }
}
