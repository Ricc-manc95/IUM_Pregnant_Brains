// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.nio.ByteBuffer;

// Referenced classes of package com.google.protobuf:
//            CodedOutputStream

static final class initialPosition extends initialPosition
{

    private final ByteBuffer byteBuffer;
    private int initialPosition;

    public final void flush()
    {
        byteBuffer.position(initialPosition + (super.sition - super.fset));
    }

    (ByteBuffer bytebuffer)
    {
        super(bytebuffer.array(), bytebuffer.arrayOffset() + bytebuffer.position(), bytebuffer.remaining());
        byteBuffer = bytebuffer;
        initialPosition = bytebuffer.position();
    }
}
