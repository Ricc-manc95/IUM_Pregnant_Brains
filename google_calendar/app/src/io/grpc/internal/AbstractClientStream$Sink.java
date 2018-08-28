// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            AbstractClientStream, WritableBuffer

public static interface 
{

    public abstract void cancel(Status status);

    public abstract void request(int i);

    public abstract void writeFrame(WritableBuffer writablebuffer, boolean flag, boolean flag1, int i);

    public abstract void writeHeaders(Metadata metadata, byte abyte0[]);
}
