// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal.framed;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.BufferedSource;
import okio.Source;

// Referenced classes of package io.grpc.okhttp.internal.framed:
//            Http2

final class source
    implements Source
{

    public byte flags;
    public int left;
    public int length;
    public short padding;
    private final BufferedSource source;
    public int streamId;

    public final void close()
        throws IOException
    {
    }

    public final long read(Buffer buffer, long l)
        throws IOException
    {
        while (left == 0) 
        {
            source.skip(padding);
            padding = 0;
            if ((flags & 4) != 0)
            {
                return -1L;
            }
            int i = streamId;
            int j = Http2.readMedium(source);
            left = j;
            length = j;
            byte byte0 = source.readByte();
            flags = source.readByte();
            if (Http2.logger.isLoggable(Level.FINE))
            {
                Http2.logger.logp(Level.FINE, "io.grpc.okhttp.internal.framed.Http2$ContinuationSource", "readContinuationHeader", eader(true, streamId, length, byte0, flags));
            }
            streamId = source.readInt() & 0x7fffffff;
            if (byte0 != 9)
            {
                throw Http2.ioException("%s != TYPE_CONTINUATION", new Object[] {
                    Byte.valueOf(byte0)
                });
            }
            if (streamId != i)
            {
                throw Http2.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
        l = source.read(buffer, Math.min(l, left));
        if (l == -1L)
        {
            return -1L;
        } else
        {
            left = left - (int)l;
            return l;
        }
    }

    public (BufferedSource bufferedsource)
    {
        source = bufferedsource;
    }
}
