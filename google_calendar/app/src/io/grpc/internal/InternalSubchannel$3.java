// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            InternalSubchannel

final class this._cls0
    implements Runnable
{

    private final InternalSubchannel this$0;

    public final void run()
    {
        callback.onTerminated(InternalSubchannel.this);
    }

    llback()
    {
        this$0 = InternalSubchannel.this;
        super();
    }
}
