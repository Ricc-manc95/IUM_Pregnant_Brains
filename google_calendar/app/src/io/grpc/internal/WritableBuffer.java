// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


public interface WritableBuffer
{

    public abstract int readableBytes();

    public abstract void release();

    public abstract int writableBytes();

    public abstract void write(byte byte0);

    public abstract void write(byte abyte0[], int i, int j);
}
