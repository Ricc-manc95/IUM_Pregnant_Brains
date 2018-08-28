// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Compressor;
import java.io.InputStream;

public interface Framer
{

    public abstract void close();

    public abstract void flush();

    public abstract boolean isClosed();

    public abstract Framer setCompressor(Compressor compressor);

    public abstract void setMaxOutboundMessageSize(int i);

    public abstract void writePayload(InputStream inputstream);
}
