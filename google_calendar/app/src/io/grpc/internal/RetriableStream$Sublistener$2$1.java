// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            RetriableStream

final class this._cls2
    implements Runnable
{

    private final usAttempts this$2;

    public final void run()
    {
        this._cls2 _lcls2 = createSubstream(tream.usAttempts + 1);
        drain(_lcls2);
    }

    ()
    {
        this$2 = this._cls2.this;
        super();
    }
}
