// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.internal.identity.growth.v1;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import io.grpc.stub.AbstractStub;

public final class GrowthApiServiceGrpc
{
    public static final class GrowthApiServiceFutureStub extends AbstractStub
    {

        protected final AbstractStub build(Channel channel, CallOptions calloptions)
        {
            return new GrowthApiServiceFutureStub(channel, calloptions);
        }

        public GrowthApiServiceFutureStub(Channel channel)
        {
            super(channel);
        }

        private GrowthApiServiceFutureStub(Channel channel, CallOptions calloptions)
        {
            super(channel, calloptions);
        }
    }


    private static volatile MethodDescriptor getGetPromosMethod;

    private GrowthApiServiceGrpc()
    {
    }

    public static MethodDescriptor getGetPromosMethod()
    {
        Object obj;
        obj = getGetPromosMethod;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        com/google/internal/identity/growth/v1/GrowthApiServiceGrpc;
        JVM INSTR monitorenter ;
        MethodDescriptor methoddescriptor = getGetPromosMethod;
        obj = methoddescriptor;
        if (methoddescriptor != null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        obj = new io.grpc.MethodDescriptor.Builder();
        obj.requestMarshaller = null;
        obj.responseMarshaller = null;
        obj.type = io.grpc.MethodDescriptor.MethodType.UNARY;
        obj.fullMethodName = MethodDescriptor.generateFullMethodName("google.internal.identity.growth.v1.GrowthApiService", "GetPromos");
        obj.sampledToLocalTracing = true;
        obj.requestMarshaller = ProtoLiteUtils.marshaller(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.DEFAULT_INSTANCE);
        obj.responseMarshaller = ProtoLiteUtils.marshaller(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.DEFAULT_INSTANCE);
        obj = ((io.grpc.MethodDescriptor.Builder) (obj)).build();
        getGetPromosMethod = ((MethodDescriptor) (obj));
        com/google/internal/identity/growth/v1/GrowthApiServiceGrpc;
        JVM INSTR monitorexit ;
        return ((MethodDescriptor) (obj));
        obj;
        com/google/internal/identity/growth/v1/GrowthApiServiceGrpc;
        JVM INSTR monitorexit ;
        throw obj;
        return ((MethodDescriptor) (obj));
    }
}
