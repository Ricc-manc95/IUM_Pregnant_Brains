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
//            RoomServiceStatusRequest, RoomServiceStatusResponse, SuggestRoomRequest, SuggestRoomResponse

public final class RoomServiceGrpc
{
    public static final class RoomServiceBlockingStub extends AbstractStub
    {

        protected final AbstractStub build(Channel channel, CallOptions calloptions)
        {
            return new RoomServiceBlockingStub(channel, calloptions);
        }

        public RoomServiceBlockingStub(Channel channel)
        {
            super(channel);
        }

        private RoomServiceBlockingStub(Channel channel, CallOptions calloptions)
        {
            super(channel, calloptions);
        }
    }


    private static volatile MethodDescriptor getGetStatusMethod;
    private static volatile MethodDescriptor getSuggestRoomMethod;

    private RoomServiceGrpc()
    {
    }

    public static MethodDescriptor getGetStatusMethod()
    {
        Object obj;
        obj = getGetStatusMethod;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        com/google/calendar/suggest/v2/RoomServiceGrpc;
        JVM INSTR monitorenter ;
        MethodDescriptor methoddescriptor = getGetStatusMethod;
        obj = methoddescriptor;
        if (methoddescriptor != null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        obj = new io.grpc.MethodDescriptor.Builder();
        obj.requestMarshaller = null;
        obj.responseMarshaller = null;
        obj.type = io.grpc.MethodDescriptor.MethodType.UNARY;
        obj.fullMethodName = MethodDescriptor.generateFullMethodName("google.calendar.suggest.v2.RoomService", "GetStatus");
        obj.sampledToLocalTracing = true;
        obj.requestMarshaller = ProtoLiteUtils.marshaller(RoomServiceStatusRequest.DEFAULT_INSTANCE);
        obj.responseMarshaller = ProtoLiteUtils.marshaller(RoomServiceStatusResponse.DEFAULT_INSTANCE);
        obj = ((io.grpc.MethodDescriptor.Builder) (obj)).build();
        getGetStatusMethod = ((MethodDescriptor) (obj));
        com/google/calendar/suggest/v2/RoomServiceGrpc;
        JVM INSTR monitorexit ;
        return ((MethodDescriptor) (obj));
        obj;
        com/google/calendar/suggest/v2/RoomServiceGrpc;
        JVM INSTR monitorexit ;
        throw obj;
        return ((MethodDescriptor) (obj));
    }

    public static MethodDescriptor getSuggestRoomMethod()
    {
        Object obj;
        obj = getSuggestRoomMethod;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        com/google/calendar/suggest/v2/RoomServiceGrpc;
        JVM INSTR monitorenter ;
        MethodDescriptor methoddescriptor = getSuggestRoomMethod;
        obj = methoddescriptor;
        if (methoddescriptor != null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        obj = new io.grpc.MethodDescriptor.Builder();
        obj.requestMarshaller = null;
        obj.responseMarshaller = null;
        obj.type = io.grpc.MethodDescriptor.MethodType.UNARY;
        obj.fullMethodName = MethodDescriptor.generateFullMethodName("google.calendar.suggest.v2.RoomService", "SuggestRoom");
        obj.sampledToLocalTracing = true;
        obj.requestMarshaller = ProtoLiteUtils.marshaller(SuggestRoomRequest.DEFAULT_INSTANCE);
        obj.responseMarshaller = ProtoLiteUtils.marshaller(SuggestRoomResponse.DEFAULT_INSTANCE);
        obj = ((io.grpc.MethodDescriptor.Builder) (obj)).build();
        getSuggestRoomMethod = ((MethodDescriptor) (obj));
        com/google/calendar/suggest/v2/RoomServiceGrpc;
        JVM INSTR monitorexit ;
        return ((MethodDescriptor) (obj));
        obj;
        com/google/calendar/suggest/v2/RoomServiceGrpc;
        JVM INSTR monitorexit ;
        throw obj;
        return ((MethodDescriptor) (obj));
    }
}
