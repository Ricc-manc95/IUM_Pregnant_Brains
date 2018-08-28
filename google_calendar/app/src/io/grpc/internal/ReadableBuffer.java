// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.Closeable;

public interface ReadableBuffer
    extends Closeable
{

    public abstract void close();

    public abstract ReadableBuffer readBytes(int i);

    public abstract void readBytes(byte abyte0[], int i, int j);

    public abstract int readUnsignedByte();

    public abstract int readableBytes();
}
