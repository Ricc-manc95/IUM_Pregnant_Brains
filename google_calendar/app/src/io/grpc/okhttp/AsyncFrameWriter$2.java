// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.IOException;

// Referenced classes of package io.grpc.okhttp:
//            AsyncFrameWriter

final class iteRunnable extends iteRunnable
{

    private final AsyncFrameWriter this$0;
    private final Settings val$peerSettings;

    public final void doRun()
        throws IOException
    {
        frameWriter.ackSettings(val$peerSettings);
    }

    tings()
    {
        this$0 = final_asyncframewriter;
        val$peerSettings = Settings.this;
        super(final_asyncframewriter, (byte)0);
    }
}
