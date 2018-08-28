// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            ContextRunnable, ClientCallImpl, ClientStream

final class this._cls1 extends ContextRunnable
{

    private final this._cls1 this$1;

    public final void runInContext()
    {
        Status status;
        try
        {
            _fld1.er();
            return;
        }
        catch (Throwable throwable)
        {
            status = Status.CANCELLED.withCause(throwable).withDescription("Failed to call onReady.");
        }
        stream.cancel(status);
        _mth1(status, new Metadata());
    }

    ()
    {
        this$1 = this._cls1.this;
        super(context);
    }
}
