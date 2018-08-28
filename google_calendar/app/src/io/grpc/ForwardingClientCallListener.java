// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            PartialForwardingClientCallListener, Status, Metadata

public abstract class ForwardingClientCallListener extends PartialForwardingClientCallListener
{

    public ForwardingClientCallListener()
    {
    }

    protected abstract ClientCall.Listener _mthdelegate();

    public volatile void onClose(Status status, Metadata metadata)
    {
        super.onClose(status, metadata);
    }

    public volatile void onHeaders(Metadata metadata)
    {
        super.onHeaders(metadata);
    }

    public final void onMessage(Object obj)
    {
        _mthdelegate().onMessage(obj);
    }

    public volatile void onReady()
    {
        super.onReady();
    }

    public volatile String toString()
    {
        return super.toString();
    }
}
