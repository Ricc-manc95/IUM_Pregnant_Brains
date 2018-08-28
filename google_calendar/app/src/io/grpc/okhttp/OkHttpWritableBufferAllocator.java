// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.internal.WritableBuffer;
import io.grpc.internal.WritableBufferAllocator;
import okio.Buffer;

// Referenced classes of package io.grpc.okhttp:
//            OkHttpWritableBuffer

final class OkHttpWritableBufferAllocator
    implements WritableBufferAllocator
{

    OkHttpWritableBufferAllocator()
    {
    }

    public final WritableBuffer allocate(int i)
    {
        i = Math.min(0x100000, Math.max(4096, i));
        return new OkHttpWritableBuffer(new Buffer(), i);
    }
}
