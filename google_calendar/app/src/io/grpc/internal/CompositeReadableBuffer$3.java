// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ReadableBuffer

final class val.destOffset extends adOperation
{

    private int currentOffset;
    private final byte val$dest[];
    private final int val$destOffset;

    public final int readInternal(ReadableBuffer readablebuffer, int i)
    {
        readablebuffer.readBytes(val$dest, currentOffset, i);
        currentOffset = currentOffset + i;
        return 0;
    }

    adOperation()
    {
        val$destOffset = i;
        val$dest = abyte0;
        super((byte)0);
        currentOffset = val$destOffset;
    }
}
