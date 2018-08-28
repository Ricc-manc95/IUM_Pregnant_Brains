// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net.findatime;

import com.google.android.calendar.timely.net.grpc.GrpcCall;
import com.google.android.calendar.timely.net.grpc.GrpcRequestExecutor;
import com.google.calendar.suggest.v2.SuggestTimeRequest;
import com.google.calendar.suggest.v2.SuggestTimeResponse;
import com.google.calendar.suggest.v2.TimeServiceGrpc;
import io.grpc.CallOptions;
import io.grpc.Deadline;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.timely.net.findatime:
//            FindTimeRequestExecutor

final class arg._cls1
    implements GrpcCall
{

    private final FindTimeRequestExecutor arg$1;

    public final Object call(Object obj)
    {
        Object obj1 = arg$1;
        obj = (SuggestTimeRequest)obj;
        obj1 = (com.google.calendar.suggest.v2.gStub)((GrpcRequestExecutor) (obj1)).getAuthenticatedStub();
        Object obj2 = TimeUnit.MILLISECONDS;
        io.grpc.Channel channel = ((AbstractStub) (obj1)).channel;
        CallOptions calloptions = ((AbstractStub) (obj1)).callOptions;
        obj2 = Deadline.after(15000L, ((TimeUnit) (obj2)));
        calloptions = new CallOptions(calloptions);
        calloptions.deadline = ((Deadline) (obj2));
        obj1 = (com.google.calendar.suggest.v2.gStub)((AbstractStub) (obj1)).build(channel, calloptions);
        return (SuggestTimeResponse)ClientCalls.blockingUnaryCall(((AbstractStub) (obj1)).channel, TimeServiceGrpc.getSuggestTimeMethod(), ((AbstractStub) (obj1)).callOptions, obj);
    }

    (FindTimeRequestExecutor findtimerequestexecutor)
    {
        arg$1 = findtimerequestexecutor;
    }
}
