// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import okio.Buffer;

final class flush
{

    public Buffer buffer;
    public boolean endOfStream;
    public boolean flush;

    (Buffer buffer1, boolean flag, boolean flag1)
    {
        buffer = buffer1;
        endOfStream = flag;
        flush = flag1;
    }
}
