// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            ContextRunnable, ClientCallImpl

final class val.savedTrailers extends ContextRunnable
{

    private final val.savedTrailers this$1;
    private final Status val$savedStatus;
    private final Metadata val$savedTrailers;

    public final void runInContext()
    {
        if (this._cls1.this.savedTrailers)
        {
            return;
        } else
        {
            _mth1(val$savedStatus, val$savedTrailers);
            return;
        }
    }

    ()
    {
        this$1 = final_;
        val$savedStatus = status;
        val$savedTrailers = Metadata.this;
        super(final_.savedTrailers.context);
    }
}
