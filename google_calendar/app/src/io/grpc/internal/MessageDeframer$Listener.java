// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


public interface ducer
{

    public abstract void bytesRead(int i);

    public abstract void deframeFailed(Throwable throwable);

    public abstract void deframerClosed(boolean flag);

    public abstract void messagesAvailable(ducer ducer);
}
