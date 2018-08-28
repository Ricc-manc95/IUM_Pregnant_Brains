// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.net;

import android.content.Context;
import com.google.android.calendar.timely.net.findatime.FindTimeRequestExecutor;
import com.google.android.calendar.timely.net.grpc.GrpcStubException;
import com.google.calendar.suggest.v2.SuggestTimeRequest;
import com.google.calendar.suggest.v2.SuggestTimeResponse;
import io.grpc.Channel;
import io.grpc.stub.AbstractStub;

public final class FakeFindTimeRequestExecutor extends FindTimeRequestExecutor
{

    public FakeFindTimeRequestExecutor(Context context, String s)
    {
        super(context, s, 0);
    }

    protected final String getAuthScope()
    {
        return "";
    }

    protected final com.google.calendar.suggest.v2.TimeServiceGrpc.TimeServiceBlockingStub getStub(Channel channel)
    {
        return null;
    }

    protected final volatile AbstractStub getStub(Channel channel)
    {
        return null;
    }

    protected final void initGrpcStub()
        throws GrpcStubException
    {
        this;
        JVM INSTR monitorenter ;
    }

    public final SuggestTimeResponse suggestTime(SuggestTimeRequest suggesttimerequest)
    {
        return null;
    }
}
