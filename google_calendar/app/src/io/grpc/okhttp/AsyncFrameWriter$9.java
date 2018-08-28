// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;
import okio.Buffer;

// Referenced classes of package io.grpc.okhttp:
//            AsyncFrameWriter

final class iteRunnable extends iteRunnable
{

    private final AsyncFrameWriter this$0;
    private final int val$byteCount;
    private final boolean val$outFinished;
    private final Buffer val$source;
    private final int val$streamId;

    public final void doRun()
        throws IOException
    {
        frameWriter.data(val$outFinished, val$streamId, val$source, val$byteCount);
    }

    meWriter()
    {
        this$0 = final_asyncframewriter;
        val$outFinished = flag;
        val$streamId = i;
        val$source = buffer;
        val$byteCount = I.this;
        super(final_asyncframewriter, (byte)0);
    }
}
