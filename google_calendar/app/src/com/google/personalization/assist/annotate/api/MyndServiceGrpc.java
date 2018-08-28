// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate.api;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import io.grpc.stub.AbstractStub;

// Referenced classes of package com.google.personalization.assist.annotate.api:
//            TaskAssistanceRequest, TaskAssistanceResponse, LoggingRequest, LoggingResponse, 
//            AnnotatedSuggestRequest, AnnotatedSuggestResponse

public final class MyndServiceGrpc
{
    public static final class MyndServiceBlockingStub extends AbstractStub
    {

        protected final AbstractStub build(Channel channel, CallOptions calloptions)
        {
            return new MyndServiceBlockingStub(channel, calloptions);
        }

        public MyndServiceBlockingStub(Channel channel)
        {
            super(channel);
        }

        private MyndServiceBlockingStub(Channel channel, CallOptions calloptions)
        {
            super(channel, calloptions);
        }
    }


    private static volatile MethodDescriptor getAssistMethod;
    private static volatile MethodDescriptor getLogMethod;
    private static volatile MethodDescriptor getSuggestMethod;

    private MyndServiceGrpc()
    {
    }

    public static MethodDescriptor getAssistMethod()
    {
        Object obj;
        obj = getAssistMethod;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        com/google/personalization/assist/annotate/api/MyndServiceGrpc;
        JVM INSTR monitorenter ;
        MethodDescriptor methoddescriptor = getAssistMethod;
        obj = methoddescriptor;
        if (methoddescriptor != null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        obj = new io.grpc.MethodDescriptor.Builder();
        obj.requestMarshaller = null;
        obj.responseMarshaller = null;
        obj.type = io.grpc.MethodDescriptor.MethodType.UNARY;
        obj.fullMethodName = MethodDescriptor.generateFullMethodName("task_assist.MyndService", "Assist");
        obj.sampledToLocalTracing = true;
        obj.requestMarshaller = ProtoLiteUtils.marshaller(TaskAssistanceRequest.DEFAULT_INSTANCE);
        obj.responseMarshaller = ProtoLiteUtils.marshaller(TaskAssistanceResponse.DEFAULT_INSTANCE);
        obj = ((io.grpc.MethodDescriptor.Builder) (obj)).build();
        getAssistMethod = ((MethodDescriptor) (obj));
        com/google/personalization/assist/annotate/api/MyndServiceGrpc;
        JVM INSTR monitorexit ;
        return ((MethodDescriptor) (obj));
        obj;
        com/google/personalization/assist/annotate/api/MyndServiceGrpc;
        JVM INSTR monitorexit ;
        throw obj;
        return ((MethodDescriptor) (obj));
    }

    public static MethodDescriptor getLogMethod()
    {
        Object obj;
        obj = getLogMethod;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        com/google/personalization/assist/annotate/api/MyndServiceGrpc;
        JVM INSTR monitorenter ;
        MethodDescriptor methoddescriptor = getLogMethod;
        obj = methoddescriptor;
        if (methoddescriptor != null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        obj = new io.grpc.MethodDescriptor.Builder();
        obj.requestMarshaller = null;
        obj.responseMarshaller = null;
        obj.type = io.grpc.MethodDescriptor.MethodType.UNARY;
        obj.fullMethodName = MethodDescriptor.generateFullMethodName("task_assist.MyndService", "Log");
        obj.sampledToLocalTracing = true;
        obj.requestMarshaller = ProtoLiteUtils.marshaller(LoggingRequest.DEFAULT_INSTANCE);
        obj.responseMarshaller = ProtoLiteUtils.marshaller(LoggingResponse.DEFAULT_INSTANCE);
        obj = ((io.grpc.MethodDescriptor.Builder) (obj)).build();
        getLogMethod = ((MethodDescriptor) (obj));
        com/google/personalization/assist/annotate/api/MyndServiceGrpc;
        JVM INSTR monitorexit ;
        return ((MethodDescriptor) (obj));
        obj;
        com/google/personalization/assist/annotate/api/MyndServiceGrpc;
        JVM INSTR monitorexit ;
        throw obj;
        return ((MethodDescriptor) (obj));
    }

    public static MethodDescriptor getSuggestMethod()
    {
        Object obj;
        obj = getSuggestMethod;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        com/google/personalization/assist/annotate/api/MyndServiceGrpc;
        JVM INSTR monitorenter ;
        MethodDescriptor methoddescriptor = getSuggestMethod;
        obj = methoddescriptor;
        if (methoddescriptor != null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        obj = new io.grpc.MethodDescriptor.Builder();
        obj.requestMarshaller = null;
        obj.responseMarshaller = null;
        obj.type = io.grpc.MethodDescriptor.MethodType.UNARY;
        obj.fullMethodName = MethodDescriptor.generateFullMethodName("task_assist.MyndService", "Suggest");
        obj.sampledToLocalTracing = true;
        obj.requestMarshaller = ProtoLiteUtils.marshaller(AnnotatedSuggestRequest.DEFAULT_INSTANCE);
        obj.responseMarshaller = ProtoLiteUtils.marshaller(AnnotatedSuggestResponse.DEFAULT_INSTANCE);
        obj = ((io.grpc.MethodDescriptor.Builder) (obj)).build();
        getSuggestMethod = ((MethodDescriptor) (obj));
        com/google/personalization/assist/annotate/api/MyndServiceGrpc;
        JVM INSTR monitorexit ;
        return ((MethodDescriptor) (obj));
        obj;
        com/google/personalization/assist/annotate/api/MyndServiceGrpc;
        JVM INSTR monitorexit ;
        throw obj;
        return ((MethodDescriptor) (obj));
    }
}
