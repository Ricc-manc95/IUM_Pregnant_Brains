// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.stub;

import io.grpc.CallOptions;
import io.grpc.Channel;

public abstract class AbstractStub
{

    public final CallOptions callOptions;
    public final Channel channel;

    public AbstractStub(Channel channel1)
    {
        this(channel1, CallOptions.DEFAULT);
    }

    public AbstractStub(Channel channel1, CallOptions calloptions)
    {
        if (channel1 == null)
        {
            throw new NullPointerException(String.valueOf("channel"));
        }
        channel = (Channel)channel1;
        if (calloptions == null)
        {
            throw new NullPointerException(String.valueOf("callOptions"));
        } else
        {
            callOptions = (CallOptions)calloptions;
            return;
        }
    }

    public abstract AbstractStub build(Channel channel1, CallOptions calloptions);
}
