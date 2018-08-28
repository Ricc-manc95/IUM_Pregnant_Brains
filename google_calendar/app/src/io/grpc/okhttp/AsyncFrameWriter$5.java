// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;
import java.util.List;

// Referenced classes of package io.grpc.okhttp:
//            AsyncFrameWriter

final class iteRunnable extends iteRunnable
{

    private final AsyncFrameWriter this$0;
    private final int val$associatedStreamId;
    private final List val$headerBlock;
    private final boolean val$inFinished;
    private final boolean val$outFinished;
    private final int val$streamId;

    public final void doRun()
        throws IOException
    {
        frameWriter.synStream(val$outFinished, val$inFinished, val$streamId, val$associatedStreamId, val$headerBlock);
    }

    meWriter()
    {
        this$0 = final_asyncframewriter;
        val$outFinished = flag;
        val$inFinished = flag1;
        val$streamId = i;
        val$associatedStreamId = j;
        val$headerBlock = List.this;
        super(final_asyncframewriter, (byte)0);
    }
}
