// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;

// Referenced classes of package io.grpc.okhttp:
//            AsyncFrameWriter

final class teRunnable extends teRunnable
{

    private final AsyncFrameWriter this$0;
    private final byte val$debugData[];
    private final ErrorCode val$errorCode;
    private final int val$lastGoodStreamId;

    public final void doRun()
        throws IOException
    {
        frameWriter.goAway(val$lastGoodStreamId, val$errorCode, val$debugData);
        frameWriter.flush();
    }

    rCode()
    {
        this$0 = final_asyncframewriter;
        val$lastGoodStreamId = i;
        val$errorCode = errorcode;
        val$debugData = _5B_B.this;
        super(final_asyncframewriter, (byte)0);
    }
}
