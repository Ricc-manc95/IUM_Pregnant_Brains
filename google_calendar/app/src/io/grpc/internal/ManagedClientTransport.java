// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            ClientTransport

public interface ManagedClientTransport
    extends ClientTransport
{

    public abstract void shutdown(Status status);

    public abstract void shutdownNow(Status status);

    public abstract Runnable start(Listener listener);
}
