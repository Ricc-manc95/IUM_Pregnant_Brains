// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

// Referenced classes of package io.grpc.internal:
//            ClientCallImpl, ClientTransport, RetriableStream

static interface 
{

    public abstract ClientTransport get(io.grpc.Provider provider);

    public abstract RetriableStream newRetriableStream(MethodDescriptor methoddescriptor, CallOptions calloptions, Metadata metadata, Context context);
}
