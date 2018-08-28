// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl

final class this._cls1
    implements Runnable
{

    private final IdleMode this$1;

    public final void run()
    {
        exitIdleMode();
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
