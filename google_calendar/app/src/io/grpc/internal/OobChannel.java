// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            Instrumented, InternalSubchannel, LogId

final class OobChannel extends ManagedChannel
    implements Instrumented
{

    static InternalSubchannel getInternalSubchannel()
    {
        throw new NoSuchMethodError();
    }

    public final String authority()
    {
        throw new NoSuchMethodError();
    }

    public final LogId getLogId()
    {
        throw new NoSuchMethodError();
    }

    public final boolean isTerminated()
    {
        throw new NoSuchMethodError();
    }

    public final ClientCall newCall(MethodDescriptor methoddescriptor, CallOptions calloptions)
    {
        throw new NoSuchMethodError();
    }

    public final ManagedChannel shutdown()
    {
        throw new NoSuchMethodError();
    }

    public final String toString()
    {
        throw new NoSuchMethodError();
    }

    static 
    {
        Logger.getLogger(io/grpc/internal/OobChannel.getName());
    }
}
