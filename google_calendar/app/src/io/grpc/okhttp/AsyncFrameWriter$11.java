// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;

// Referenced classes of package io.grpc.okhttp:
//            AsyncFrameWriter

final class teRunnable extends teRunnable
{

    private final AsyncFrameWriter this$0;
    private final boolean val$ack;
    private final int val$payload1;
    private final int val$payload2;

    public final void doRun()
        throws IOException
    {
        frameWriter.ping(val$ack, val$payload1, val$payload2);
    }

    eWriter()
    {
        this$0 = final_asyncframewriter;
        val$ack = flag;
        val$payload1 = i;
        val$payload2 = I.this;
        super(final_asyncframewriter, (byte)0);
    }
}
