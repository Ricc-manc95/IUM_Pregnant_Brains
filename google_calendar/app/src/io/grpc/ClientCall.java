// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            Metadata

public abstract class ClientCall
{

    public ClientCall()
    {
    }

    public abstract void cancel(String s, Throwable throwable);

    public abstract void halfClose();

    public abstract void request(int i);

    public abstract void sendMessage(Object obj);

    public abstract void start(Listener listener, Metadata metadata);
}
