// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.NameResolver;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl

final class this._cls0
    implements Runnable
{

    public boolean cancelled;
    private final ManagedChannelImpl this$0;

    public final void run()
    {
        if (!cancelled)
        {
            nameResolverRefreshFuture = null;
            nameResolverRefresh = null;
            if (nameResolver != null)
            {
                nameResolver.refresh();
                return;
            }
        }
    }

    ()
    {
        this$0 = ManagedChannelImpl.this;
        super();
    }
}
