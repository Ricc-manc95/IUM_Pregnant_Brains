// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.zip.CRC32;

// Referenced classes of package io.grpc.internal:
//            GzipInflatingBuffer, CompositeReadableBuffer

final class this._cls0
{

    public final GzipInflatingBuffer this$0;

    final int readUnsignedByte()
    {
        GzipInflatingBuffer gzipinflatingbuffer1;
        int i;
        if (inflaterInputEnd - inflaterInputStart > 0)
        {
            i = inflaterInput[inflaterInputStart] & 0xff;
            GzipInflatingBuffer gzipinflatingbuffer = GzipInflatingBuffer.this;
            gzipinflatingbuffer.inflaterInputStart = gzipinflatingbuffer.inflaterInputStart + 1;
        } else
        {
            CompositeReadableBuffer compositereadablebuffer = gzippedData;
            this._cls0 _lcls0 = new this._cls0();
            compositereadablebuffer.execute(_lcls0, 1);
            i = ((this._cls0) (_lcls0)).alue;
        }
        crc.update(i);
        gzipinflatingbuffer1 = GzipInflatingBuffer.this;
        gzipinflatingbuffer1.bytesConsumed = gzipinflatingbuffer1.bytesConsumed + 1;
        return i;
    }

    ()
    {
        this$0 = GzipInflatingBuffer.this;
        super();
    }
}
