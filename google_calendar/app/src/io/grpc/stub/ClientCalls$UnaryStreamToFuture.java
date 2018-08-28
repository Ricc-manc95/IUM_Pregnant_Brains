// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.stub;

import com.google.common.util.concurrent.AbstractFuture;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

// Referenced classes of package io.grpc.stub:
//            ClientCalls

static final class responseFuture extends io.grpc.ture
{

    private final value responseFuture;
    private Object value;

    public final void onClose(Status status, Metadata metadata)
    {
        boolean flag;
        if (io.grpc.ture == status.code)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (value == null)
            {
                responseFuture.setException(new StatusRuntimeException(Status.INTERNAL.withDescription("No value received for unary call"), metadata));
            }
            responseFuture.set(value);
            return;
        } else
        {
            responseFuture.setException(new StatusRuntimeException(status, metadata));
            return;
        }
    }

    public final void onHeaders(Metadata metadata)
    {
    }

    public final void onMessage(Object obj)
    {
        if (value != null)
        {
            throw new StatusRuntimeException(Status.INTERNAL.withDescription("More than one value received for unary call"));
        } else
        {
            value = obj;
            return;
        }
    }

    ure(ure ure)
    {
        responseFuture = ure;
    }
}
