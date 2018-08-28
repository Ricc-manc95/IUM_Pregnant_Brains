// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            Status, Metadata

abstract class PartialForwardingClientCallListener extends ClientCall.Listener
{

    PartialForwardingClientCallListener()
    {
    }

    protected abstract ClientCall.Listener _mthdelegate();

    public void onClose(Status status, Metadata metadata)
    {
        _mthdelegate().onClose(status, metadata);
    }

    public void onHeaders(Metadata metadata)
    {
        _mthdelegate().onHeaders(metadata);
    }

    public void onReady()
    {
        _mthdelegate().onReady();
    }

    public String toString()
    {
        return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("delegate", _mthdelegate()).toString();
    }
}
