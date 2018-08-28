// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.okhttp:
//            AsyncFrameWriter

final class this._cls0
    implements Runnable
{

    private final AsyncFrameWriter this$0;

    public final void run()
    {
        if (frameWriter == null)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        frameWriter.close();
        socket.close();
        return;
        IOException ioexception;
        ioexception;
        AsyncFrameWriter.log.logp(Level.WARNING, "io.grpc.okhttp.AsyncFrameWriter$14", "run", "Failed closing connection", ioexception);
        return;
    }

    eWriter()
    {
        this$0 = AsyncFrameWriter.this;
        super();
    }
}
