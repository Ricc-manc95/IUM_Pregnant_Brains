// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import io.grpc.stub.AbstractStub;

// Referenced classes of package com.google.calendar.suggest.v2:
//            SuggestTimeRequest, SuggestTimeResponse

public final class TimeServiceGrpc
{
    public static final class TimeServiceBlockingStub extends AbstractStub
    {

        protected final AbstractStub build(Channel channel, CallOptions calloptions)
        {
            return new TimeServiceBlockingStub(channel, calloptions);
        }

        public TimeServiceBlockingStub(Channel channel)
        {
            super(channel);
        }

        private TimeServiceBlockingStub(Channel channel, CallOptions calloptions)
        {
            super(channel, calloptions);
        }
    }


    private static volatile MethodDescriptor getSuggestTimeMethod;

    private TimeServiceGrpc()
    {
    }

    public static MethodDescriptor getSuggestTimeMethod()
    {
        Object obj;
        obj = getSuggestTimeMethod;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        com/google/calendar/suggest/v2/TimeServiceGrpc;
        JVM INSTR monitorenter ;
        MethodDescriptor methoddescriptor = getSuggestTimeMethod;
        obj = methoddescriptor;
        if (methoddescriptor != null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        obj = new io.grpc.MethodDescriptor.Builder();
        obj.requestMarshaller = null;
        obj.responseMarshaller = null;
        obj.type = io.grpc.MethodDescriptor.MethodType.UNARY;
        obj.fullMethodName = MethodDescriptor.generateFullMethodName("google.calendar.suggest.v2.TimeService", "SuggestTime");
        obj.sampledToLocalTracing = true;
        obj.requestMarshaller = ProtoLiteUtils.marshaller(SuggestTimeRequest.DEFAULT_INSTANCE);
        obj.responseMarshaller = ProtoLiteUtils.marshaller(SuggestTimeResponse.DEFAULT_INSTANCE);
        obj = ((io.grpc.MethodDescriptor.Builder) (obj)).build();
        getSuggestTimeMethod = ((MethodDescriptor) (obj));
        com/google/calendar/suggest/v2/TimeServiceGrpc;
        JVM INSTR monitorexit ;
        return ((MethodDescriptor) (obj));
        obj;
        com/google/calendar/suggest/v2/TimeServiceGrpc;
        JVM INSTR monitorexit ;
        throw obj;
        return ((MethodDescriptor) (obj));
    }
}
