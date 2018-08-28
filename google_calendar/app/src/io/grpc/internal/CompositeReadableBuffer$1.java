// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ReadableBuffer

final class adOperation extends adOperation
{

    final int readInternal(ReadableBuffer readablebuffer, int i)
    {
        return readablebuffer.readUnsignedByte();
    }

    adOperation()
    {
        super((byte)0);
    }
}
