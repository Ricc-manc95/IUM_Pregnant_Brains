// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Context;
import io.grpc.Contexts;

// Referenced classes of package io.grpc.internal:
//            ClientCallImpl, ClientStream

final class this._cls0
    implements io.grpc.Listener
{

    private final ClientCallImpl this$0;

    public final void cancelled(Context context)
    {
        stream.cancel(Contexts.statusFromCancelled(context));
    }

    A()
    {
        this$0 = ClientCallImpl.this;
        super();
    }
}
