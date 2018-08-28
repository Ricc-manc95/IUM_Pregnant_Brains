// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


public abstract class NameResolver
{

    public NameResolver()
    {
    }

    public abstract String getServiceAuthority();

    public void refresh()
    {
    }

    public abstract void shutdown();

    public abstract void start(Listener listener);
}
