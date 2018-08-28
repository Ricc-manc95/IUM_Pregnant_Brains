// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timely.net.grpc.GrpcCall;
import com.google.android.calendar.timely.net.grpc.GrpcRequestException;
import com.google.android.calendar.timely.net.grpc.GrpcRequestExecutor;
import com.google.android.calendar.timely.net.grpc.GrpcStubException;
import io.grpc.Channel;
import io.grpc.stub.AbstractStub;

public class RoomsRequestExecutor extends GrpcRequestExecutor
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/rooms/net/RoomsRequestExecutor);
    public GrpcCall getStatusCall;
    public GrpcCall suggestRoomCall;

    public RoomsRequestExecutor(Context context, String s, int i)
    {
        super(context, s, i, true);
        class .Lambda._cls0
            implements GrpcCall
        {

            private final RoomsRequestExecutor arg$1;

            public final Object call(Object obj)
            {
                Object obj1 = arg$1;
                obj = (SuggestRoomRequest)obj;
                obj1 = (com.google.calendar.suggest.v2.RoomServiceGrpc.RoomServiceBlockingStub)((GrpcRequestExecutor) (obj1)).getAuthenticatedStub();
                Object obj2 = TimeUnit.MILLISECONDS;
                Channel channel = ((AbstractStub) (obj1)).channel;
                CallOptions calloptions = ((AbstractStub) (obj1)).callOptions;
                obj2 = Deadline.after(15000L, ((TimeUnit) (obj2)));
                calloptions = new CallOptions(calloptions);
                calloptions.deadline = ((Deadline) (obj2));
                obj1 = (com.google.calendar.suggest.v2.RoomServiceGrpc.RoomServiceBlockingStub)((AbstractStub) (obj1)).build(channel, calloptions);
                return (SuggestRoomResponse)ClientCalls.blockingUnaryCall(((AbstractStub) (obj1)).channel, RoomServiceGrpc.getSuggestRoomMethod(), ((AbstractStub) (obj1)).callOptions, obj);
            }

            .Lambda._cls0()
            {
                arg$1 = RoomsRequestExecutor.this;
            }
        }

        suggestRoomCall = new .Lambda._cls0();
        class .Lambda._cls1
            implements GrpcCall
        {

            private final RoomsRequestExecutor arg$1;

            public final Object call(Object obj)
            {
                Object obj1 = arg$1;
                obj = (RoomServiceStatusRequest)obj;
                obj1 = (com.google.calendar.suggest.v2.RoomServiceGrpc.RoomServiceBlockingStub)((GrpcRequestExecutor) (obj1)).getAuthenticatedStub();
                Object obj2 = TimeUnit.MILLISECONDS;
                Channel channel = ((AbstractStub) (obj1)).channel;
                CallOptions calloptions = ((AbstractStub) (obj1)).callOptions;
                obj2 = Deadline.after(15000L, ((TimeUnit) (obj2)));
                calloptions = new CallOptions(calloptions);
                calloptions.deadline = ((Deadline) (obj2));
                obj1 = (com.google.calendar.suggest.v2.RoomServiceGrpc.RoomServiceBlockingStub)((AbstractStub) (obj1)).build(channel, calloptions);
                return (RoomServiceStatusResponse)ClientCalls.blockingUnaryCall(((AbstractStub) (obj1)).channel, RoomServiceGrpc.getGetStatusMethod(), ((AbstractStub) (obj1)).callOptions, obj);
            }

            .Lambda._cls1()
            {
                arg$1 = RoomsRequestExecutor.this;
            }
        }

        getStatusCall = new .Lambda._cls1();
    }

    protected final String getAuthScope()
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

    protected final AbstractStub getStub(Channel channel)
    {
        return new com.google.calendar.suggest.v2.RoomServiceGrpc.RoomServiceBlockingStub(channel);
    }

    public final Object handleCall(GrpcCall grpccall, Object obj)
        throws GrpcRequestException, GrpcStubException
    {
        LogUtils.d(TAG, "Sending Rooms Request: %s", new Object[] {
            obj
        });
        grpccall = ((GrpcCall) (call(grpccall, obj, false)));
        LogUtils.d(TAG, "Rooms Response: %s", new Object[] {
            grpccall
        });
        return grpccall;
    }

}
