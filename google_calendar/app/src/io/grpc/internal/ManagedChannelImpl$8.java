// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl

final class this._cls0
    implements Runnable
{

    private final ManagedChannelImpl this$0;

    public final void run()
    {
        cancelIdleTimer(true);
    }

    ()
    {
        this$0 = ManagedChannelImpl.this;
        super();
    }
}
