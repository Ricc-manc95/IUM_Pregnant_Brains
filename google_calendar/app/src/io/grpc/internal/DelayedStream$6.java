// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.InputStream;

// Referenced classes of package io.grpc.internal:
//            DelayedStream, ClientStream

final class val.message
    implements Runnable
{

    private final DelayedStream this$0;
    private final InputStream val$message;

    public final void run()
    {
        realStream.writeMessage(val$message);
    }

    ()
    {
        this$0 = final_delayedstream;
        val$message = InputStream.this;
        super();
    }
}
