// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.io.InputStream;

// Referenced classes of package io.grpc.internal:
//            ContextRunnable, ClientCallImpl, GrpcUtil, ClientStream

final class val.producer extends ContextRunnable
{

    private final this._cls1 this$1;
    private final this._cls1 val$producer;

    public final void runInContext()
    {
        if (!this._cls1.this.producer) goto _L2; else goto _L1
_L1:
        GrpcUtil.closeQuietly(val$producer);
_L4:
        return;
_L2:
        Object obj;
        try
        {
            obj = val$producer.producer();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            GrpcUtil.closeQuietly(val$producer);
            obj = Status.CANCELLED.withCause(((Throwable) (obj))).withDescription("Failed to read message.");
            stream.cancel(((Status) (obj)));
            val.producer.this.producer(((Status) (obj)), new Metadata());
            return;
        }
        if (obj == null) goto _L4; else goto _L3
_L3:
        this._cls1.this.producer._mth1(method.responseMarshaller._mth1(((InputStream) (obj))));
        ((InputStream) (obj)).close();
          goto _L2
        Throwable throwable;
        throwable;
        GrpcUtil.closeQuietly(((InputStream) (obj)));
        throw throwable;
    }

    Q()
    {
        this$1 = final_q;
        val$producer = val.producer.this;
        super(final_q.producer.context);
    }
}
