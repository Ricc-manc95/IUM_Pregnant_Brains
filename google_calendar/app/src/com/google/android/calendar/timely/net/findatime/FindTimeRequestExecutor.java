// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net.findatime;

import android.content.Context;
import com.google.android.calendar.timely.net.grpc.GrpcCall;
import com.google.android.calendar.timely.net.grpc.GrpcRequestException;
import com.google.android.calendar.timely.net.grpc.GrpcRequestExecutor;
import com.google.android.calendar.timely.net.grpc.GrpcStubException;
import com.google.calendar.suggest.v2.SuggestTimeRequest;
import com.google.calendar.suggest.v2.SuggestTimeResponse;
import io.grpc.Channel;
import io.grpc.stub.AbstractStub;

public class FindTimeRequestExecutor extends GrpcRequestExecutor
{

    private GrpcCall suggestTimeCall;

    public FindTimeRequestExecutor(Context context, String s, int i)
    {
        super(context, s, i, true);
        class .Lambda._cls0
            implements GrpcCall
        {

            private final FindTimeRequestExecutor arg$1;

            public final Object call(Object obj)
            {
                Object obj1 = arg$1;
                obj = (SuggestTimeRequest)obj;
                obj1 = (com.google.calendar.suggest.v2.TimeServiceGrpc.TimeServiceBlockingStub)((GrpcRequestExecutor) (obj1)).getAuthenticatedStub();
                Object obj2 = TimeUnit.MILLISECONDS;
                Channel channel = ((AbstractStub) (obj1)).channel;
                CallOptions calloptions = ((AbstractStub) (obj1)).callOptions;
                obj2 = Deadline.after(15000L, ((TimeUnit) (obj2)));
                calloptions = new CallOptions(calloptions);
                calloptions.deadline = ((Deadline) (obj2));
                obj1 = (com.google.calendar.suggest.v2.TimeServiceGrpc.TimeServiceBlockingStub)((AbstractStub) (obj1)).build(channel, calloptions);
                return (SuggestTimeResponse)ClientCalls.blockingUnaryCall(((AbstractStub) (obj1)).channel, TimeServiceGrpc.getSuggestTimeMethod(), ((AbstractStub) (obj1)).callOptions, obj);
            }

            .Lambda._cls0()
            {
                arg$1 = FindTimeRequestExecutor.this;
            }
        }

        suggestTimeCall = new .Lambda._cls0();
    }

    public String getAuthScope()
    {
        return "oauth2:https://www.googleapis.com/auth/calendar.readonly";
    }

    protected final String getServerTargetProd()
    {
        return "calendarsuggest.googleapis.com";
    }

    protected final String getServerTargetStaging()
    {
        return "staging-calendarsuggest.sandbox.googleapis.com";
    }

    protected final String getServerTargetTest()
    {
        return "test-calendarsuggest.sandbox.googleapis.com";
    }

    public com.google.calendar.suggest.v2.TimeServiceGrpc.TimeServiceBlockingStub getStub(Channel channel)
    {
        return new com.google.calendar.suggest.v2.TimeServiceGrpc.TimeServiceBlockingStub(channel);
    }

    public volatile AbstractStub getStub(Channel channel)
    {
        return getStub(channel);
    }

    public SuggestTimeResponse suggestTime(SuggestTimeRequest suggesttimerequest)
        throws GrpcRequestException, GrpcStubException
    {
        return (SuggestTimeResponse)call(suggestTimeCall, suggesttimerequest, false);
    }
}
