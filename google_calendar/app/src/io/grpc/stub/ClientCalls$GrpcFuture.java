// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.stub;

import com.google.common.util.concurrent.AbstractFuture;
import io.grpc.ClientCall;

// Referenced classes of package io.grpc.stub:
//            ClientCalls

static final class call extends AbstractFuture
{

    private final ClientCall call;

    protected final void interruptTask()
    {
        call.cancel("GrpcFuture was cancelled", null);
    }

    protected final String pendingToString()
    {
        return (new com.google.common.base.per(getClass().getSimpleName())).add("clientCall", call).toString();
    }

    protected final boolean set(Object obj)
    {
        return super.set(obj);
    }

    protected final boolean setException(Throwable throwable)
    {
        return super.setException(throwable);
    }

    oStringHelper(ClientCall clientcall)
    {
        call = clientcall;
    }
}
