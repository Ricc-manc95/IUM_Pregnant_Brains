// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package io.grpc.internal:
//            WritableBuffer, MessageFramer, WritableBufferAllocator

final class this._cls0 extends OutputStream
{

    public final List bufferList = new ArrayList();
    private WritableBuffer current;
    private final MessageFramer this$0;

    public final void write(int i)
        throws IOException
    {
        if (current != null && current.writableBytes() > 0)
        {
            current.write((byte)i);
            return;
        } else
        {
            write(new byte[] {
                (byte)i
            }, 0, 1);
            return;
        }
    }

    public final void write(byte abyte0[], int i, int j)
    {
        int k = i;
        int l = j;
        if (current == null)
        {
            current = bufferAllocator.allocate(j);
            bufferList.add(current);
            l = j;
            k = i;
        }
        while (l > 0) 
        {
            i = Math.min(l, current.writableBytes());
            if (i == 0)
            {
                i = Math.max(l, current.readableBytes() << 1);
                current = bufferAllocator.allocate(i);
                bufferList.add(current);
            } else
            {
                current.write(abyte0, k, i);
                k += i;
                l -= i;
            }
        }
    }

    ()
    {
        this$0 = MessageFramer.this;
        super();
    }
}
