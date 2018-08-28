// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;

// Referenced classes of package io.grpc.okhttp:
//            AsyncFrameWriter

final class iteRunnable extends iteRunnable
{

    private final AsyncFrameWriter this$0;
    private final ErrorCode val$errorCode;
    private final int val$streamId;

    public final void doRun()
        throws IOException
    {
        frameWriter.rstStream(val$streamId, val$errorCode);
    }

    orCode()
    {
        this$0 = final_asyncframewriter;
        val$streamId = i;
        val$errorCode = ErrorCode.this;
        super(final_asyncframewriter, (byte)0);
    }
}
