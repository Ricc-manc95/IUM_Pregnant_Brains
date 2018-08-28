// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;

class ForwardingManagedChannel extends ManagedChannel
{

    private final ManagedChannel _flddelegate;

    ForwardingManagedChannel(ManagedChannel managedchannel)
    {
        _flddelegate = managedchannel;
    }

    public final String authority()
    {
        return _flddelegate.authority();
    }

    public final boolean isTerminated()
    {
        return _flddelegate.isTerminated();
    }

    public final ClientCall newCall(MethodDescriptor methoddescriptor, CallOptions calloptions)
    {
        return _flddelegate.newCall(methoddescriptor, calloptions);
    }

    public ManagedChannel shutdown()
    {
        return _flddelegate.shutdown();
    }

    public String toString()
    {
        return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("delegate", _flddelegate).toString();
    }
}
