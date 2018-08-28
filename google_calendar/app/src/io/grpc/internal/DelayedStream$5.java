// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            DelayedStream, ClientStream, ClientStreamListener

final class ener
    implements Runnable
{

    private final DelayedStream this$0;
    private final ClientStreamListener val$finalListener;

    public final void run()
    {
        realStream.start(val$finalListener);
    }

    ener()
    {
        this$0 = final_delayedstream;
        val$finalListener = ClientStreamListener.this;
        super();
    }
}
