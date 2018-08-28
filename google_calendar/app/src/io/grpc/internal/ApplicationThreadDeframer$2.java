// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ApplicationThreadDeframer, MessageDeframer, ReadableBuffer

final class val.data
    implements Runnable
{

    private final ApplicationThreadDeframer this$0;
    private final ReadableBuffer val$data;

    public final void run()
    {
        try
        {
            deframer.deframe(val$data);
            return;
        }
        catch (Throwable throwable)
        {
            ApplicationThreadDeframer applicationthreaddeframer = ApplicationThreadDeframer.this;
            applicationthreaddeframer.transportExecutor.runOnTransportThread(new <init>(applicationthreaddeframer, throwable));
            deframer.close();
            return;
        }
    }

    ansportExecutor()
    {
        this$0 = final_applicationthreaddeframer;
        val$data = ReadableBuffer.this;
        super();
    }
}
