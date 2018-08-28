// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ApplicationThreadDeframer, MessageDeframer

final class val.numMessages
    implements Runnable
{

    private final ApplicationThreadDeframer this$0;
    private final int val$numMessages;

    public final void run()
    {
        MessageDeframer messagedeframer = deframer;
        boolean flag;
        if (messagedeframer.unprocessed == null && messagedeframer.fullStreamDecompressor == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return;
        }
        try
        {
            deframer.request(val$numMessages);
            return;
        }
        catch (Throwable throwable)
        {
            storedListener.rameFailed(throwable);
        }
        deframer.close();
    }

    ()
    {
        this$0 = final_applicationthreaddeframer;
        val$numMessages = I.this;
        super();
    }
}
