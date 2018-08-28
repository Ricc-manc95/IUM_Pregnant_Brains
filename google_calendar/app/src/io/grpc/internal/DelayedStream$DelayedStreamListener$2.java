// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ClientStreamListener

final class this._cls0
    implements Runnable
{

    private final alListener this$0;

    public final void run()
    {
        alListener.onReady();
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
