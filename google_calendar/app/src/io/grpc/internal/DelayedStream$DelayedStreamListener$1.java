// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ClientStreamListener

final class val.producer
    implements Runnable
{

    private final val.producer this$0;
    private final val.producer val$producer;

    public final void run()
    {
        alListener.messagesAvailable(val$producer);
    }

    ()
    {
        this$0 = final_;
        val$producer = val.producer.this;
        super();
    }
}
