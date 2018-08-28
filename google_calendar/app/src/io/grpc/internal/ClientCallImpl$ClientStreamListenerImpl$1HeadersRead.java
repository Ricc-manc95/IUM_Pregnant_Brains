// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            ContextRunnable, ClientCallImpl, ClientStream

final class val.headers extends ContextRunnable
{

    private final val.headers this$1;
    private final Metadata val$headers;

    public final void runInContext()
    {
        if (this._cls1.this.headers)
        {
            return;
        }
        Status status;
        try
        {
            _fld1.rver(val$headers);
            return;
        }
        catch (Throwable throwable)
        {
            status = Status.CANCELLED.withCause(throwable).withDescription("Failed to read headers");
        }
        stream.cancel(status);
        val.headers.this.headers(status, new Metadata());
        return;
    }

    ()
    {
        this$1 = final_;
        val$headers = Metadata.this;
        super(final_.headers.context);
    }
}
