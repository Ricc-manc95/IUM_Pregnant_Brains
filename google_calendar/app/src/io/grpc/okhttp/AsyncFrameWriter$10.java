// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.IOException;

// Referenced classes of package io.grpc.okhttp:
//            AsyncFrameWriter

final class teRunnable extends teRunnable
{

    private final AsyncFrameWriter this$0;
    private final Settings val$okHttpSettings;

    public final void doRun()
        throws IOException
    {
        frameWriter.settings(val$okHttpSettings);
    }

    ings()
    {
        this$0 = final_asyncframewriter;
        val$okHttpSettings = Settings.this;
        super(final_asyncframewriter, (byte)0);
    }
}
