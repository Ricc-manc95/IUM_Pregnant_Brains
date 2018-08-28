// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            Channel

public abstract class ManagedChannel extends Channel
{

    public ManagedChannel()
    {
    }

    public abstract boolean isTerminated();

    public abstract ManagedChannel shutdown();
}
