// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            DelayedStream, ClientTransport, FailingClientStream, ClientStream

final class MetadataApplierImpl
    implements io.grpc.CallCredentials.MetadataApplier
{

    private final CallOptions callOptions;
    private final Context ctx = Context.current();
    private DelayedStream delayedStream;
    private boolean finalized;
    private final Object lock = new Object();
    private final MethodDescriptor method;
    private final Metadata origHeaders;
    private ClientStream returnedStream;
    private final ClientTransport transport;

    MetadataApplierImpl(ClientTransport clienttransport, MethodDescriptor methoddescriptor, Metadata metadata, CallOptions calloptions)
    {
        transport = clienttransport;
        method = methoddescriptor;
        origHeaders = metadata;
        callOptions = calloptions;
    }

    private final void finalizeWith(ClientStream clientstream)
    {
        boolean flag2;
label0:
        {
            flag2 = true;
            boolean flag;
            if (!finalized)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("already finalized"));
            }
            finalized = true;
            synchronized (lock)
            {
                if (returnedStream != null)
                {
                    break label0;
                }
                returnedStream = clientstream;
            }
            return;
        }
        obj;
        JVM INSTR monitorexit ;
        boolean flag1;
        if (delayedStream != null)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalStateException(String.valueOf("delayedStream is null"));
        } else
        {
            delayedStream.setStream(clientstream);
            return;
        }
        clientstream;
        obj;
        JVM INSTR monitorexit ;
        throw clientstream;
    }

    public final void apply(Metadata metadata)
    {
        boolean flag;
        if (!finalized)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("apply() or fail() already called"));
        }
        if (metadata == null)
        {
            throw new NullPointerException(String.valueOf("headers"));
        }
        origHeaders.merge(metadata);
        metadata = ctx.attach();
        ClientStream clientstream = transport.newStream(method, origHeaders, callOptions);
        ctx.detach(metadata);
        finalizeWith(clientstream);
        return;
        Exception exception;
        exception;
        ctx.detach(metadata);
        throw exception;
    }

    public final void fail(Status status)
    {
        boolean flag1 = true;
        boolean flag;
        if (io.grpc.Status.Code.OK == status.code)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Cannot fail with OK status"));
        }
        if (!finalized)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("apply() or fail() already called"));
        } else
        {
            finalizeWith(new FailingClientStream(status));
            return;
        }
    }

    final ClientStream returnStream()
    {
label0:
        {
            DelayedStream delayedstream;
            synchronized (lock)
            {
                if (returnedStream != null)
                {
                    break label0;
                }
                delayedStream = new DelayedStream();
                delayedstream = delayedStream;
                returnedStream = delayedstream;
            }
            return delayedstream;
        }
        ClientStream clientstream = returnedStream;
        obj;
        JVM INSTR monitorexit ;
        return clientstream;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
