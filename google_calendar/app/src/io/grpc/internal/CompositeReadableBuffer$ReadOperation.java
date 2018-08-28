// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.IOException;

// Referenced classes of package io.grpc.internal:
//            ReadableBuffer

abstract class <init>
{

    public IOException ex;
    public int value;

    abstract int readInternal(ReadableBuffer readablebuffer, int i)
        throws IOException;

    private ()
    {
    }

    (byte byte0)
    {
        this();
    }
}
