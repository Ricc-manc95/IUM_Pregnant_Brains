// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            ClientCall

abstract class PartialForwardingClientCall extends ClientCall
{

    PartialForwardingClientCall()
    {
    }

    public void cancel(String s, Throwable throwable)
    {
        _mthdelegate().cancel(s, throwable);
    }

    protected abstract ClientCall _mthdelegate();

    public void halfClose()
    {
        _mthdelegate().halfClose();
    }

    public void request(int i)
    {
        _mthdelegate().request(i);
    }

    public String toString()
    {
        return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("delegate", _mthdelegate()).toString();
    }
}
