// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ApplicationThreadDeframer

final class val.numBytes
    implements Runnable
{

    private final ApplicationThreadDeframer this$0;
    private final int val$numBytes;

    public final void run()
    {
        storedListener.esRead(val$numBytes);
    }

    ()
    {
        this$0 = final_applicationthreaddeframer;
        val$numBytes = I.this;
        super();
    }
}
