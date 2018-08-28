// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import java.util.concurrent.Executor;

// Referenced classes of package io.grpc.internal:
//            Instrumented, ClientStream

public interface ClientTransport
    extends Instrumented
{

    public abstract ClientStream newStream(MethodDescriptor methoddescriptor, Metadata metadata, CallOptions calloptions);

    public abstract void ping(PingCallback pingcallback, Executor executor);
}
