// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            PartialForwardingClientCall, ClientCall, Metadata

public abstract class ForwardingClientCall extends PartialForwardingClientCall
{

    public ForwardingClientCall()
    {
    }

    public volatile void cancel(String s, Throwable throwable)
    {
        super.cancel(s, throwable);
    }

    public abstract ClientCall _mthdelegate();

    public volatile void halfClose()
    {
        super.halfClose();
    }

    public volatile void request(int i)
    {
        super.request(i);
    }

    public final void sendMessage(Object obj)
    {
        _mthdelegate().sendMessage(obj);
    }

    public void start(ClientCall.Listener listener, Metadata metadata)
    {
        _mthdelegate().start(listener, metadata);
    }

    public volatile String toString()
    {
        return super.toString();
    }
}
