// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.io.IOException;

// Referenced classes of package okio:
//            Source, Buffer, ByteString

public interface BufferedSource
    extends Source
{

    public abstract Buffer buffer();

    public abstract boolean exhausted()
        throws IOException;

    public abstract byte readByte()
        throws IOException;

    public abstract byte[] readByteArray(long l)
        throws IOException;

    public abstract ByteString readByteString(long l)
        throws IOException;

    public abstract int readInt()
        throws IOException;

    public abstract short readShort()
        throws IOException;

    public abstract void require(long l)
        throws IOException;

    public abstract void skip(long l)
        throws IOException;
}