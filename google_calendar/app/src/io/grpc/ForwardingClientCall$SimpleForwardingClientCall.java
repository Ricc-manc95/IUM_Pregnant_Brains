// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            ForwardingClientCall, ClientCall

public class delegate extends ForwardingClientCall
{

    private final ClientCall _flddelegate;

    public final ClientCall _mthdelegate()
    {
        return _flddelegate;
    }

    public (ClientCall clientcall)
    {
        _flddelegate = clientcall;
    }
}
