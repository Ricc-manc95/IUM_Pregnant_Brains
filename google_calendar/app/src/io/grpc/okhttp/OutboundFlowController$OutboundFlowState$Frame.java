// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;
import java.util.Queue;
import okio.Buffer;

// Referenced classes of package io.grpc.okhttp:
//            OutboundFlowController, OkHttpClientStream

final class endStream
{

    public final Buffer data;
    private final boolean endStream;
    public boolean enqueued;
    public final data this$1;

    final endStream split(int i)
    {
        i = Math.min(i, (int)data.size);
        Object obj = new Buffer();
        ((Buffer) (obj)).write(data, i);
        obj = new <init>(((Buffer) (obj)), false);
        if (enqueued)
        {
            endStream endstream = this._cls1.this;
            endstream.Bytes = endstream.Bytes - i;
        }
        return ((Bytes) (obj));
    }

    final void write()
    {
        boolean flag1 = true;
_L6:
        int i;
        int j;
        j = (int)data.size;
        i = Math.min(j, frameWriter.maxDataLength());
        if (i != j) goto _L2; else goto _L1
_L1:
        Object obj;
        Object obj1;
        connectionState.entStreamWindow(-j);
        entStreamWindow(-j);
        try
        {
            frameWriter.data(endStream, Id, data, j);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw new RuntimeException(((Throwable) (obj)));
        }
        obj1 = .state;
        obj = ((io.grpc.internal.ta) (obj1)).ta;
        obj;
        JVM INSTR monitorenter ;
        if (!((io.grpc.internal.ta) (obj1)).ta)
        {
            throw new IllegalStateException(String.valueOf("onStreamAllocated was not called, but it seems the stream is active"));
        }
        break MISSING_BLOCK_LABEL_153;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        boolean flag;
        if (((io.grpc.internal.ta) (obj1)).d < 32768)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        obj1.d = ((io.grpc.internal.d) (obj1)).d - j;
        if (((io.grpc.internal.d) (obj1)).d < 32768)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L3
_L4:
        obj;
        JVM INSTR monitorexit ;
        if (i != 0)
        {
            ((io.grpc.internal.d) (obj1)).d();
        }
        if (enqueued)
        {
            Bytes bytes = this._cls1.this;
            bytes.Bytes = bytes.Bytes - j;
            gWriteQueue.remove(this);
        }
        return;
_L8:
        i = 0;
          goto _L4
_L2:
        split(i).write();
        if ((int)data.size > 0) goto _L6; else goto _L5
_L5:
        return;
_L3:
        if (i != 0 || !flag) goto _L8; else goto _L7
_L7:
        i = ((flag1) ? 1 : 0);
          goto _L4
    }

    (Buffer buffer, boolean flag)
    {
        this$1 = this._cls1.this;
        super();
        data = buffer;
        endStream = flag;
    }
}
