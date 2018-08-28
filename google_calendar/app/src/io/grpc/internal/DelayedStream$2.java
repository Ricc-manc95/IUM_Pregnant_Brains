// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            DelayedStream, ClientStream

final class val.maxSize
    implements Runnable
{

    private final DelayedStream this$0;
    private final int val$maxSize;

    public final void run()
    {
        realStream.setMaxOutboundMessageSize(val$maxSize);
    }

    ()
    {
        this$0 = final_delayedstream;
        val$maxSize = I.this;
        super();
    }
}
