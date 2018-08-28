// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal.framed;

import java.io.IOException;
import okio.Buffer;
import okio.Segment;

final class out
{

    public final Buffer out;

    final void writeInt(int i, int j, int k)
        throws IOException
    {
        if (i < j)
        {
            Buffer buffer = out;
            Segment segment = buffer.writableSegment(1);
            byte abyte0[] = segment.data;
            j = segment.limit;
            segment.limit = j + 1;
            abyte0[j] = (byte)(i | 0);
            buffer.size = buffer.size + 1L;
            buffer = (Buffer)buffer;
            return;
        }
        Buffer buffer1 = out;
        Segment segment1 = buffer1.writableSegment(1);
        byte abyte1[] = segment1.data;
        k = segment1.limit;
        segment1.limit = k + 1;
        abyte1[k] = (byte)(j | 0);
        buffer1.size = buffer1.size + 1L;
        buffer1 = (Buffer)buffer1;
        for (i -= j; i >= 128; i >>>= 7)
        {
            buffer1 = out;
            segment1 = buffer1.writableSegment(1);
            abyte1 = segment1.data;
            j = segment1.limit;
            segment1.limit = j + 1;
            abyte1[j] = (byte)(i & 0x7f | 0x80);
            buffer1.size = buffer1.size + 1L;
            buffer1 = (Buffer)buffer1;
        }

        buffer1 = out;
        segment1 = buffer1.writableSegment(1);
        abyte1 = segment1.data;
        j = segment1.limit;
        segment1.limit = j + 1;
        abyte1[j] = (byte)i;
        buffer1.size = buffer1.size + 1L;
        buffer1 = (Buffer)buffer1;
    }

    (Buffer buffer)
    {
        out = buffer;
    }
}
