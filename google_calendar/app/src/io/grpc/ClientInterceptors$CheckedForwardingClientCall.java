// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            ForwardingClientCall, ClientInterceptors, Status, Metadata, 
//            ClientCall

public abstract class delegate extends ForwardingClientCall
{

    private ClientCall _flddelegate;

    public abstract void checkedStart(delegate delegate1, Metadata metadata)
        throws Exception;

    public final ClientCall _mthdelegate()
    {
        return _flddelegate;
    }

    public final void start(delegate delegate1, Metadata metadata)
    {
        try
        {
            checkedStart(delegate1, metadata);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Metadata metadata)
        {
            _flddelegate = ClientInterceptors.NOOP_CALL;
        }
        delegate1._mthdelegate(Status.fromThrowable(metadata), new Metadata());
    }

    public (ClientCall clientcall)
    {
        _flddelegate = clientcall;
    }
}
